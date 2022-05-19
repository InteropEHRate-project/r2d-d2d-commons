package eu.interopehrate.protocols.provenance;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Media;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.ResourceType;

/**
 *  Author: Engineering S.p.A. (www.eng.it)
 *  Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: ResourceNode wrapping a FHIR Bundle
 */
public class BundleNode extends ResourceNode {
	BundleNode(Bundle resource) {
		super(resource);
	}

	@Override
	public void loadChildren(ResourceNode root) {
		ResourceType resourceType;
		Bundle bundle = (Bundle)resource;
		ResourceNode n;

		for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
			resourceType = entry.getResource().getResourceType();
			if (!NodeFactory.isAllowed(resourceType))
				continue;

			if (entry.getResource().getId() == null)
				continue;

			n = root.searchNodeByResourceId(entry.getResource().getId());
			if (n == null) {
				n = NodeFactory.createNode(entry.getResource());
				n.loadChildren(this);
				n.setParent(this);
			}
		}
	}

	public void print4Debug(int level) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 1; i < level; i++)
			spaces.append("   ");

		System.out.println(spaces.toString() + resource.getResourceType().toString());

		level++;
		for (ResourceNode n : children)
			n.print4Debug(level);
	}
}
