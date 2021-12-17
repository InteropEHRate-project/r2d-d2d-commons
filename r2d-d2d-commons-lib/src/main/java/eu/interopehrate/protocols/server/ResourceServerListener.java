package eu.interopehrate.protocols.server;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Resource;

import java.util.Date;
import java.util.Iterator;

import eu.interopehrate.protocols.common.ResourceCategory;

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
     * @throws Exception in case of failure
     */
    Bundle onResourcesRequested(String... ids) throws Exception;


    /**
     * Method invoked to execute a search of Resources belonging of whatever type produced after
     * a certain date.
     *
     * @param from: mandatory argument used to specify the date beyond which resources must be retrieved.
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     * @return an instance of ResourceIterator containing the matching resources.
     * @throws Exception in case of failure
     */
    Bundle onResourcesRequested(Date from, boolean isSummary) throws Exception;


    /**
     *
     * Method invoked to execute a search of Resources belonging to one or more specified categories.
     *
     * @param from: optional argument used to specify the date beyond which resources must be retrieved.
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     * @param categories: mandatory set of categories.
     * @return an instance of ResourceIterator containing the matching resources.
     * @throws Exception in case of failure
     */
    Bundle onResourcesRequested(Date from,
                                boolean isSummary,
                                ResourceCategory... categories) throws Exception;


    /**
     * Method invoked to execute a search of Resources belonging to a specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the type of the
     *                 requested resources.
     * @param subCategory: optional parameter used to filter on a specific sub category of the provided
     *                   <strong>category</strong> argument. For instance, this parameter may be useful
     *                   to retrieve only <strong>Observation</strong> whose category is
     *                   <strong>vital-signs</strong>, or to retrieve <strong>DiagnosticReport</strong>
     *                   whose category is <strong>LAB</strong> (laboratory reports).
     * @param type:    optional code identifying, within the requested <strong>category</strong>,
     *                 what instances of resources must be retrieved.
     *                 The code should be part of a standard coding system and should have this form:
     *                 "&lt;system name&gt;|&lt;code&gt;". Valid examples are "http://loinc.org|31455", or
     *                 "http://snomed.org|7162445".
     * @param from: argument used to specify the date beyond which resources must be retrieved.
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     * @return an instance of ResourceIterator containing the matching resources.
     * @throws Exception in case of failure
     */
    Bundle onResourcesRequested(ResourceCategory category,
                                String subCategory,
                                String type,
                                Date from,
                                boolean isSummary) throws Exception;


    /**
     *
     * Method invoked to execute a search of the mostRecentSize Resources belonging to a specific category.
     *
     * @param category: mandatory instance of FHIRResourceCategory identifying the type of the
     *                 requested resources.
     * @param subCategory: optional parameter used to filter on a specific sub category of the provided
     *                   <strong>category</strong> argument. For instance, this parameter may be useful
     *                   to retrieve only <strong>Observation</strong> whose category is
     *                   <strong>vital-signs</strong>, or to retrieve <strong>DiagnosticReport</strong>
     *                   whose category is <strong>LAB</strong> (laboratory reports).
     * @param type:    optional code identifying, within the requested <strong>category</strong>,
     *                 what instances of resources must be retrieved.
     *                 The code should be part of a standard coding system and should have this form:
     *                 "&lt;system name&gt;|&lt;code&gt;". Valid examples are "http://loinc.org|31455", or
     *                 "http://snomed.org|7162445".
     * @param mostRecentSize: mandatory argument specifying the number of resources to be retrieved.
     *                        must be &lt;= 10.
     * @param isSummary: optional argument used to specify if the client needs the entire
     *                   resource or only a portion of it.
     * @return an instance of ResourceIterator containing the matching resources.
     * @throws Exception in case of failure
     */
    Bundle onResourcesRequested(ResourceCategory category,
                                String subCategory,
                                String type,
                                int mostRecentSize,
                                boolean isSummary) throws Exception;


    /**
     * Metod invoked to notify the listener about the fact the new health data produced by the HCP
     * App have received. These data should be stored in the S-EHR App database.
     *
     * @param healthDataBundle: bundle of heterogeneous FHIR resources
     * @throws Exception in case of failure
     */
    public void onResourcesReceived(Bundle healthDataBundle) throws Exception;
}
