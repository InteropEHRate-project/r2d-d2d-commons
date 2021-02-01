package eu.interopehrate.protocols.server;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Marker interface of a local service acting as owner of Resources.
 */
public interface ResourceServer {

    /**
     * Needed to register an instance of ResourceServerListener
     * @param listener
     */
    public void registerListener(ResourceServerListener listener);

    /**
     * Needed to deregister an instance of ResourceServerListener
     * @param listener
     */
    public void deregisterListener(ResourceServerListener listener);

}
