package eu.interopehrate.protocols.client;

import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Resource;

import java.util.Date;

import eu.interopehrate.protocols.common.ResourceCategory;
import eu.interopehrate.protocols.common.ResourceIterator;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Interface of a local service for requesting Resources.
 */
public interface ResourceReader {

    /**
     *
     * @param from: mandatory argument used to retrieve only resources produced after a specific date.
     *
     * @return an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<Resource> getResources(Date from);

    /**
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the category of the
     *                requested resources.
     * @param type: optional code identifying, within the requested <strong>category</strong>,
     *            what instances of resources must be retrieved.
     *            The code should be part of a standard coding system and should have this form:
     *            <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *            "http://snomed.org|7162445".
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @return an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<Resource> getResourcesByCategory(ResourceCategory category,
                                                      String type,
                                                      Date from);

    /**
     *
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     * @param categories: array of FHIRResourceCategory identifying the type of the requested resources.
     *
     * @return an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<Resource> getResourcesByCategories(Date from,
                                                        ResourceCategory... categories);

    /**
     *
     * @param category: instance of FHIRResourceCategory identifying the category of the requested resources.
     * @param type: optional code identifying, within the requested <strong>category</strong>, the
     *             what instances of resources must be retrieved.
     *             The code should be part of a standard coding system and should have this form:
     *            <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *             "http://snomed.org|7162445".
     * @param mostRecentSize: number of instances that must be retrieved.
     *
     * @return an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<Resource> getMostRecentResources(ResourceCategory category,
                                                      String type,
                                                      int mostRecentSize);

    /**
     *
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @return  an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<DocumentReference> getDocumentReferences(Date from);


    /**
     *
     * @param category: instance of FHIRResourceCategory identifying the type of the requested resources.
     * @param type: optional code identifying, within the requested <strong>category</strong>, the
     *             what instances of resources must be retrieved.
     *             The code should be part of a standard coding system and should have this form:
     *            <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *             "http://snomed.org|7162445".
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @return  an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<DocumentReference> getDocumentReferencesByCategory(ResourceCategory category,
                                                               String type,
                                                               Date from);

    /**
     *
     * @param type: optional code identifying, within the requested <strong>category</strong>, the
     *             what instances of resources must be retrieved.
     *             The code should be part of a standard coding system and should have this form:
     *            <system name>|<code>. Valid examples are "http://loinc.org|31455", or
     *             "http://snomed.org|7162445".
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     * @param categories: array of FHIRResourceCategory identifying the type of the requested resources.
     *
     * @return  an instance of ResourceIterator containing the results of the request.
     */
    ResourceIterator<DocumentReference> getDocumentReferencesByCategories(String type,
                                                                Date from,
                                                                ResourceCategory... categories);

    /**
     *
     * @param ids: array of id of the resources to be retrieved.
     *
     * @return an instance of ResourceIterator containing the requested resources.
     */
    ResourceIterator<Resource> getResourcesById(String... ids);

}
