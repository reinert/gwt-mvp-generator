package com.growbit.tools.gmg;

import java.util.Set;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class PresenterClass implements HasName, HasTypeName, HasTypeSpec, IsJavaFile {

    public static final String SUFFIX = "Activity";

    private final ViewInterface viewInterface;
    private final PlaceClass placeClass;
    private final String name;
    private final TypeName typeName;
    private final TypeSpec typeSpec;

    public PresenterClass(ViewInterface viewInterface) {
        this(viewInterface, null);
    }

    public PresenterClass(ViewInterface viewInterface, Set<PlaceParam> params) {
        this.viewInterface = viewInterface;
        this.placeClass = new PlaceClass(this, params);
        this.name = viewInterface.getEntity().getName() + SUFFIX;
        this.typeName = ClassName.get(viewInterface.getPackage().getCanonicalName(), name);
        this.typeSpec = createTypeSpec();
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TypeName getTypeName() {
        return typeName;
    }

    @Override
    public TypeSpec getTypeSpec() {
        return typeSpec;
    }

    @Override
    public JavaFile asJavaFile() {
        return JavaFile.builder(getPackage().getCanonicalName(), typeSpec).build();
    }

    private TypeSpec createTypeSpec() {
        final MethodSpec constructor = MethodSpec.constructorBuilder()
                .addAnnotation(TypeNames.INJECT)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(viewInterface.getTypeName(), "view")
                .addParameter(TypeNames.PLACE_CONTROLLER, "placeController")
                .addStatement("super(view, placeController)")
                .build();

        final MethodSpec onStart = MethodSpec.methodBuilder("onStart")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PROTECTED)
                .addParameter(TypeNames.ACCEPTS_ONE_WIDGET, "panel")
                .addStatement("panel.setWidget(getView())")
                .build();

        return TypeSpec.classBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(TypeNames.ABSTRACT_PRESENTER, viewInterface.getTypeName()))
                .addSuperinterface(viewInterface.getInnerPresenterTypeName())
                .addMethod(constructor)
                .addMethod(onStart)
                .build();
    }
}
