package com.utils;

import java.util.*;

public class CustomIterator<E> implements Iterator<E> {
    int cursor;       // index of next element to return
    CustomList<E> list;

    // prevent creating a synthetic constructor
    public CustomIterator (CustomList<E> list) {
        this.list = new CustomList<>(list);
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public E next() {
        if (cursor >= list.size())
            throw new NoSuchElementException();
        cursor += 1;
        return list.get(cursor - 1);
    }
}
