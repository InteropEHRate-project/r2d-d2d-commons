package eu.interopehrate.protocols.server;

import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Resource;

import java.util.Date;

import eu.interopehrate.protocols.common.ResourceCategory;
import eu.interopehrate.protocols.common.ResourceIterator;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: interface of a listener .
 */
public interface ResourceServerListener {

    /**
     * Method invoked to retrieve an array of Resources identified by their unique IDs.
     *
     * @param ids: array of IDs of the resources to be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<Resource> onResourcesRequested(String ids);


    /**
     * Method invoked to execute a search of Resources produced after a certain date.
     *
     * @param from: mandatory argument used to specify the date beyond which resources must be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<Resource> onResourcesRequested(Date from);


    /**
     * Method invoked to execute a search of Resources belonging to a specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the type of the
     *                 requested resources.
     * @param type:    optional code identifying, within the requested <strong>category</strong>,
     *                 what instances of resources must be retrieved.
     *                 The code should be part of a standard coding system and should have this form:
     *                 <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *                 "http://snomed.org|7162445".
     * @param from: argument used to specify the date beyond which resources must be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<Resource> onResourcesRequested(ResourceCategory category,
                                                           String type,
                                                           Date from);

    /**
     *
     * Method invoked to execute a search of Resources belonging to one or more specified categories.
     *
     * @param from: optional argument used to specify the date beyond which resources must be retrieved.
     * @param categories: mandatory set of categories.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<Resource> onResourcesRequested(Date from,
                                                           ResourceCategory... categories);

    /**
     *
     * Method invoked to execute a search of the mostRecentSize Resources belonging to a specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the type of the
     *                 requested resources.
     * @param type:    optional code identifying, within the requested <strong>category</strong>,
     *                 what instances of resources must be retrieved.
     *                 The code should be part of a standard coding system and should have this form:
     *                 <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *                 "http://snomed.org|7162445".
     * @param mostRecentSize: mandatory argument specifying the number of resources to be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<Resource> onResourcesRequested(ResourceCategory category,
                                                           String type,
                                                           int mostRecentSize);

    /**
     * Method invoked to retrieve DocumentReference(s) produced after a certain date.
     *
     * @param from: mandatory argument used to specify the date beyond which Document References
     *           must be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<DocumentReference> onDocumentReferencesRequested(Date from);

    /**
     * Method invoked to retrieve DocumentReference(s) belonging to one category having a
     * certain (optional) type and and produced after a certain (optional) date.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the type of the
     *                 requested Document References.
     * @param type:    optional code identifying, within the requested <strong>category</strong>,
     *                 what instances of resources must be retrieved.
     *                 The code should be part of a standard coding system and should have this form:
     *                 <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *                 "http://snomed.org|7162445".
     * @param from: optional argument used to specify the date beyond which resources must be retrieved.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<DocumentReference> onDocumentReferencesRequested(ResourceCategory category,
                                                                             String type,
                                                                             Date from);


    /**
     * Method invoked to retrieve DocumentReference(s) belonging to one or more category and produced
     * after a certain (optional) date.
     *
     * @param from: optional argument used to specify the date beyond which resources must be retrieved.
     * @param categories: mandatory set of categories.
     * @return an instance of ResourceIterator containing the matching resources.
     */
    public ResourceIterator<DocumentReference> onDocumentReferencesRequested(Date from,
                                                           ResourceCategory... categories);



}
