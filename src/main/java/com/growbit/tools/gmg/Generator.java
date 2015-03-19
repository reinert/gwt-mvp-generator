package com.growbit.tools.gmg;

import java.io.IOException;
import java.util.Arrays;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public final class Generator {

    private Generator() {
    }

    public static void main(String[] args) throws IOException {
        Package p = new Package("br.com.salesdomain.app.lead");
        Entity e = new Entity("Lead");

        ViewInterface vi = new ViewInterface(p, e);
        vi.asJavaFile().writeTo(System.out);
    }
}
