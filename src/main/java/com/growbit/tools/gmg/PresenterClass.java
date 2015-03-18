package com.growbit.tools.gmg;

import java.util.Set;

public class PresenterClass {

    private final ViewInterface viewInterface;
    private final PlaceClass placeClass;

    public PresenterClass(ViewInterface viewInterface) {
        this(viewInterface, null);
    }

    public PresenterClass(ViewInterface viewInterface, Set<PlaceParam> params) {
        this.viewInterface = viewInterface;
        this.placeClass = new PlaceClass(this, params);
    }

    public Package getPackage() {
        return viewInterface.getPackage();
    }

    public Entity getEntity() {
        return viewInterface.getEntity();
    }

    public boolean mustInjectPlace() {
        return placeClass.hasParams();
    }

    public ViewInterface getViewInterface() {
        return viewInterface;
    }
}
