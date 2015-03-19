package com.growbit.tools.gmg;

import java.io.IOException;
import java.io.InputStream;

public class ViewImplUiXml implements IsString {

    private final ViewImplClass viewImplClass;
    private final String str;

    public ViewImplUiXml(ViewImplClass viewImplClass) {
        this.viewImplClass = viewImplClass;
        this.str = createStr();
    }

    public Package getPackage() {
        return viewImplClass.getPackage();
    }

    public Entity getEntity() {
        return viewImplClass.getEntity();
    }

    public ViewImplClass getViewImplClass() {
        return viewImplClass;
    }

    @Override
    public String asString() {
        return str;
    }

    private String createStr() {
        final InputStream stream = getClass().getClassLoader()
                .getResourceAsStream("com/growbit/tools/gmg/ViewImplUiXml.template");
        final String s = Util.convertStreamToString(stream);
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException("It wasn't possible to create ViewImplUiXml." +
                    " Error while loading ViewImplUiXml.template.", e);
        }
        return s;
    }
}
