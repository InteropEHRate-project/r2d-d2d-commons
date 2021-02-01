package eu.interopehrate.protocols.common;

import java.util.List;

public class SinglePageResourceIterator<E> implements ResourceIterator<E> {

    private List<E> items;
    private int i;

    public SinglePageResourceIterator(List<E> items) {
        this.items = items;
    }

    @Override
    public int getCurrentPageNumber() {
        return 1;
    }

    @Override
    public boolean hasNext() {
        return i < items.size();
    }

    @Override
    public E next() {
        return items.get(i++);
    }

}
