package eu.interopehrate.protocols.provenance;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.Provenance;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Signature;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Create instances of Provenance used to add information about the provenance of a
 *               resource representing a certified health data.
 */
public class ProvenanceBuilder {

    public static final String PROV_EXT_NAME = "http://interopehrate.eu/fhir/StructureDefinition/ProvenanceExtension-IEHR";
    public static final String PROV_PARTICIPANT_TYPE = "http://terminology.hl7.org/CodeSystem/provenance-participant-type";

    /**
     * Method used to create an instance of Provenance compliant to FHIR specifications and
     * adds it to the resource to be signed.
     *
     * @param resourceToSign
     * @param author
     * @param provider
     * @return
     */
    public static Provenance build(DomainResource resourceToSign, DomainResource author, DomainResource provider) {
        if (resourceToSign == null || author == null || provider == null)
            throw new IllegalArgumentException("Invalid arguments: the resource to be signed, "
                    + "the author and the provider cannot be null.");

        // Checks for valid author and provider
        if (!(author instanceof Organization || author instanceof Practitioner ||
                author instanceof PractitionerRole || author instanceof Patient))
            throw new IllegalArgumentException("Invalid author: must be an instance of "
                    + " Organization or Practitioner or "
                    + " PractitionerRole or Patient");

        if (!(provider instanceof Organization || provider instanceof Practitioner ||
                provider instanceof PractitionerRole || provider instanceof Patient))
            throw new IllegalArgumentException("Invalid author: must be an instance of "
                    + " Organization or Practitioner or "
                    + " PractitionerRole or Patient");

        // 1. create the Provenance instance and assigns an ID to it
        Provenance prov = new Provenance();
        prov.setId(UUID.randomUUID().toString());

        // 1.1 Adds the profile, target and recorded to the Provenance
        Meta profile = new Meta();
        profile.addProfile("http://interopehrate.eu/fhir/StructureDefinition/Provenance-IEHR");
        prov.setMeta(profile);
        prov.addTarget(new Reference(resourceToSign));
        prov.setRecorded(new Date());

        // 1.2 Adds the author to the Provenance
        Provenance.ProvenanceAgentComponent authorAgent = new Provenance.ProvenanceAgentComponent();
        CodeableConcept authorType = new CodeableConcept(
                new Coding(PROV_PARTICIPANT_TYPE, "author", "Author"));
        authorAgent.setType(authorType);
        authorAgent.setWho(new Reference(author));
        prov.addAgent(authorAgent);

        // 1.3 Adds the provider to the Provenance
        Provenance.ProvenanceAgentComponent providerAgent = new Provenance.ProvenanceAgentComponent();
        CodeableConcept providerType = new CodeableConcept(
                new Coding(PROV_PARTICIPANT_TYPE, "custodian", "Custodian"));
        providerAgent.setType(providerType);
        providerAgent.setWho(new Reference(provider));
        prov.addAgent(providerAgent);

        // 1.4 sets the reference from the resourceToSign to the Provenance
        Extension provExtension = new Extension();
        provExtension.setUrl(PROV_EXT_NAME);
        provExtension.setValue(new Reference(prov));
        // Add the extension to the resource
        resourceToSign.addExtension(provExtension);

        // creates the Signature element
        Signature signature = new Signature();
        signature.setId(UUID.randomUUID().toString());
        signature.setWho(new Reference(provider));
        signature.addType(new Coding("urn:iso-astm:E1762-95:2013",
                "1.2.840.10065.1.12.1.5",
                "Verification Signature"));
        signature.setWhen(new Date());
        signature.setTargetFormat("json");
        signature.setSigFormat("application/jose");

        // stores the signature in the Provenance
        prov.addSignature(signature);

        return prov;
    }

    /**
     * Method used to add to a resource (resourceToSign) getting the Provenance form an already
     * signed resource (signedResource).
     * This methdo must bes used when propapagating a Provenance from a container resource to its
     * children.
     *
     * @param signedResource
     * @param resourceToSign
     */
    public static void addProvenanceExtension(DomainResource signedResource, DomainResource resourceToSign) {
        resourceToSign.addExtension(signedResource.getExtensionByUrl(PROV_EXT_NAME));
    }
}
