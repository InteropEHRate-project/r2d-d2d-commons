package eu.interopehrate.protocols.common;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Enumeration defining the FHIR Resource types handled by the APIs.
 */
public enum FHIRResourceCategory  implements ResourceCategory {

    DOCUMENT_REFERENCE
    ,DOCUMENT_MANIFEST
    ,OBSERVATION
    ,DIAGNOSTIC_REPORT
    ,MEDICATION_REQUEST
    /*
    ,CONDITION
    ,MEDICATION
    ,ALLERGIES_INTOLERANCE
    ,ENCOUNTER
     */

}
