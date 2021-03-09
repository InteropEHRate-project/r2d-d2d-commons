package eu.interopehrate.protocols.client;

import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Resource;

import java.util.Date;
import java.util.Iterator;

import eu.interopehrate.protocols.common.ResourceCategory;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Interface of a local service for requesting Resources.
 */
public interface ResourceReader {

    /**
     * Method used to retrieve all categories (all values of Enum FHIRResourceCategories) of health
     * data of the citizen from a certain date.
     *
     * @param from: mandatory argument used to retrieve only resources produced after a specific date.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     *
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getResources(Date from, boolean isSummary);


    /**
     * Method used to retrieve health data of the citizen belonging to the provided categories,
     * (argument <strong>categories</strong>) from a certain date.
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @param categories: array of FHIRResourceCategory identifying the type of the requested resources.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getResourcesByCategories(Date from,
                                                boolean isSummary,
                                                ResourceCategory... categories);


    /**
     * Method used to retrieve health data of the citizen belonging to one specific category. This
     * method allow to apply several filter criteria but just to one specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the category of the
     *                requested resources.
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     *
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getResourcesByCategory(ResourceCategory category,
                                              Date from,
                                              boolean isSummary);

    /**
     * Method used to retrieve health data of the citizen belonging to one specific category. This
     * method allow to apply several filter criteria but just to one specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the category of the
     *                requested resources.
     * @param subCategory: optional parameter used to filter on a specific sub category of the provided
     *                   <strong>category</strong> argument. For instance, this parameter may be useful
     *                   to retrieve only <strong>Observation</strong> whose category is
     *                   <strong>vital-signs</strong>, or to retrieve <strong>DiagnosticReport</strong>
     *                   whose category is <strong>LAB</strong> (laboratory reports).
     * @param type: optional code identifying, within the requested <strong>category</strong>,
     *            what instances of resources must be retrieved.
     *            The code should be part of a standard coding system and should have this form:
     *            "&lt;system name&gt;|&lt;code&gt;". Valid examples are "http://loinc.org|31455", or
     *            "http://snomed.org|7162445".
     *
     * @param from: optional argument used to retrieve only resources produced after a specific date.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     *
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getResourcesByCategory(ResourceCategory category,
                                              String subCategory,
                                              String type,
                                              Date from,
                                              boolean isSummary);


    /**
     * Method used to retrieve the most recent health data of the citizen belonging to one specific
     * category. This
     *
     * @param category: instance of FHIRResourceCategory identifying the category of the requested resources.
     * @param mostRecentSize: number of instances that must be retrieved.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     *
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getMostRecentResources(ResourceCategory category,
                                              int mostRecentSize,
                                              boolean isSummary);


    /**
     * Method used to retrieve the most recent health data of the citizen belonging to one specific
     * category. This
     *
     * @param category: instance of FHIRResourceCategory identifying the category of the requested resources.
     * @param subCategory: optional parameter used to filter on a specific sub category of the provided
     *                   <strong>category</strong> argument. For instance, this parameter may be useful
     *                   to retrieve only <strong>Observation</strong> whose category is
     *                   <strong>vital-signs</strong>, or to retrieve <strong>DiagnosticReport</strong>
     *                   whose category is <strong>LAB</strong> (laboratory reports).
     * @param type: optional code identifying, within the requested <strong>category</strong>, the
     *             what instances of resources must be retrieved.
     *             The code should be part of a standard coding system and should have this form:
     *            "&lt;system name&gt;|&lt;code&gt;". Valid examples are "http://loinc.org|31455", or
     *             "http://snomed.org|7162445".
     * @param mostRecentSize: number of instances that must be retrieved.
     *
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     *
     * @return an instance of Iterator containing the results of the request.
     */
    Iterator<Resource> getMostRecentResources(ResourceCategory category,
                                              String subCategory,
                                              String type,
                                              int mostRecentSize,
                                              boolean isSummary);


    /**
     *
     * @param ids: array of id of the resources to be retrieved.
     *
     * @return an instance of Iterator containing the requested resources. The id
     * must be valid within the scope of the connected instance of ResourceServer.
     */
    Iterator<Resource> getResourcesById(String... ids);

}
