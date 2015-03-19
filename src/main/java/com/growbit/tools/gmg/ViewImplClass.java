package com.growbit.tools.gmg;

public class ViewImplClass implements HasTypeName {

    public static final String SUFFIX = "ViewImpl";

    private final Package pack;
    private final Entity entity;
    private final String typeName;

    public ViewImplClass(Package pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
        this.typeName = entity.getName() + SUFFIX;
    }

    public Package getPackage() {
        return pack;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
