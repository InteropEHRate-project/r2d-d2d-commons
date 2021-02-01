package eu.interopehrate.protocols.common;

import java.util.Iterator;

/**
 *       Author: Engineering Ingegneria Informatica - University of Piraeus (UPRC)
 *      Project: InteropEHRate - www.interopehrate.eu
 *
 *  Description: Interface of a local service for requesting Resources.
 */
public interface ResourceIterator<E> extends Iterator<E> {

    public int getCurrentPageNumber();

}
