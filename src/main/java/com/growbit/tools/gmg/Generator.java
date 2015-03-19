package com.growbit.tools.gmg;

import java.io.IOException;

public final class Generator {

    private Generator() {
    }

    public static void main(String[] args) throws IOException {
        Package p = new Package("br.com.salesdomain.app.lead");
        Entity e = new Entity("Lead");

        ViewInterface vi = new ViewInterface(p, e);
//        vi.asJavaFile().writeTo(System.out);

        ViewImplClass vic = new ViewImplClass(vi);
//        vic.asJavaFile().writeTo(System.out);

        ViewImplUiXml viux = new ViewImplUiXml(vic);
//        System.out.println(viux.asString());

        PresenterClass pc = new PresenterClass(vi);
        pc.asJavaFile().writeTo(System.out);
    }
}
