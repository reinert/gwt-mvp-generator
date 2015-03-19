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

/**
 * Hello world!
 *
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) throws IOException {

        Package p = new Package("br.com.salesdomain.app.lead");
        Entity e = new Entity("Lead");
        ViewInterface vi = new ViewInterface(p, e);

        vi.asJavaFile().writeTo(System.out);

        FieldSpec s = FieldSpec.builder(String.class, "a").build();

        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);
    }
}
