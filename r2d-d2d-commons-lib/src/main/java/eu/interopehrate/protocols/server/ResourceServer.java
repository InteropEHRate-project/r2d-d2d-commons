package eu.interopehrate.protocols.server;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Marker interface of a running service able to reply to requests that comply
 *              to the D2D protocol or to the R2D Access protocol defined by the
 *              InteropEHRate specifications.
 */
public interface ResourceServer {

    /**
     * Method used to register the listener used by the ResourceServer
     * to forward to the App the requests coming from the HCP App.
     *
     * @param listener: the listener that must be registered to handle callbacks.
     */
    void setResourceServerListener(ResourceServerListener listener);

}
