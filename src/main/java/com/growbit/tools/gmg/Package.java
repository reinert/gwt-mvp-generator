package com.growbit.tools.gmg;

public class Package {

    private final String canonicalName;
    private final String[] parts;

    public Package(String canonicalName) {
        this.canonicalName = canonicalName;
        this.parts = canonicalName.split("\\.");
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String[] getParts() {
        return parts;
    }
}
