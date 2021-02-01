package eu.interopehrate.protocols.client;

import org.hl7.fhir.r4.model.Bundle;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Interface of a local service for sending writing requests to a
 *  ResourceServer.
 */
public interface ResourceWriter {

    /**
     * Method used to sent a bundle of resources to a ResourceServer.
     *
     * @param healthData: bundle of data sent to the ResourceServer.
     */
    void sendHealthData(Bundle healthData);

}
