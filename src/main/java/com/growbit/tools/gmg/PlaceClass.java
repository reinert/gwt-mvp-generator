package com.growbit.tools.gmg;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class PlaceClass implements Iterable<PlaceParam> {

    private final PresenterClass presenterClass;
    private final Set<PlaceParam> params;

    public PlaceClass(PresenterClass presenterClass, Set<PlaceParam> params) {
        this.presenterClass = presenterClass;
        this.params = params;
    }

    public PresenterClass getPresenterClass() {
        return presenterClass;
    }

    public boolean hasParams() {
        return params != null && !params.isEmpty();
    }

    @Override
    public Iterator<PlaceParam> iterator() {
        return params != null ? params.iterator() : Collections.<PlaceParam>emptyIterator();
    }
}
