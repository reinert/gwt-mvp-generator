package com.growbit.tools.gmg;

public class ViewImplUiXml {

    private final Package pack;
    private final Entity entity;

    public ViewImplUiXml(Package pack, Entity entity) {
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
