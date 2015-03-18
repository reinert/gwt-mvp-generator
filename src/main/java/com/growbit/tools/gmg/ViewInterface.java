package com.growbit.tools.gmg;

public class ViewInterface {

    private final Package pack;
    private final Entity entity;

    public ViewInterface(Package pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
    }

    public Package getPackage() {
        return pack;
    }

    public Entity getEntity() {
        return entity;
    }
}
