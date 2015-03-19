package com.growbit.tools.gmg;

import java.io.IOException;
import java.io.InputStream;

public class ViewImplUiXml implements HasName, IsGenericFile {

    public static final String SUFFIX = "ViewImpl";
    private final ViewImplClass viewImplClass;
    private final String name;
    private final String content;

    public ViewImplUiXml(ViewImplClass viewImplClass) {
        this.viewImplClass = viewImplClass;
        this.name = viewImplClass.getEntity().getName() + SUFFIX;
        this.content = createContent();
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
    public String getName() {
        return name;
    }

    @Override
    public GenericFile asGenericFile() {
        return new GenericFile(getPackage().getCanonicalName(), name, "ui.xml", content);
    }

    private String createContent() {
        final InputStream stream = getClass().getClassLoader()
                .getResourceAsStream("com/growbit/tools/gmg/ViewImplUiXml.template");
        final String s = Util.convertStreamToString(stream);
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException("It wasn't possible to create ViewImplUiXml."
                    + " Error while loading ViewImplUiXml.template.", e);
        }
        return s;
    }
}
