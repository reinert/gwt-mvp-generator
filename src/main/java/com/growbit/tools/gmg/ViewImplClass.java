package com.growbit.tools.gmg;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class ViewImplClass implements HasName, HasTypeName, HasTypeSpec, IsJavaFile {

    public static final String SUFFIX = "ViewImpl";

    private final ViewInterface viewInterface;
    private final String name;
    private final TypeName typeName;
    private final TypeSpec typeSpec;

    public ViewImplClass(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
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
        final ClassName viewImplTypeName = ClassName.get(getPackage().getCanonicalName(), name);

        final TypeSpec uiBinderItf = TypeSpec.interfaceBuilder("Binder")
                .addModifiers(Modifier.STATIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeNames.UI_BINDER, TypeNames.WIDGET, viewImplTypeName))
                .build();

        final TypeName binderTypeName = ClassName.get(getPackage().getCanonicalName(), name, "Binder");

        final FieldSpec binderFieldSpec = FieldSpec.builder(binderTypeName, "binder", Modifier.PRIVATE, Modifier.STATIC)
                .addModifiers(Modifier.FINAL)
                .initializer("$T.create($T.class)", TypeNames.GWT, binderTypeName)
                .build();

        final MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("initWidget($N.createAndBindUi(this))", binderFieldSpec)
                .build();

        return TypeSpec.classBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .superclass(
                        ParameterizedTypeName.get(TypeNames.ABSTRACT_VIEW, viewInterface.getInnerPresenterTypeName()))
                .addSuperinterface(viewInterface.getTypeName())
                .addAnnotation(TypeNames.SINGLETON)
                .addField(binderFieldSpec)
                .addType(uiBinderItf)
                .addMethod(constructor)
                .build();
    }
}
