package com.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CustomList<E> extends ArrayList<E> {
    public CustomList() {
        super();
    }

    public CustomList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<E>(this);
    }
}
