package com.growbit.tools.gmg;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class ViewInterface implements HasName, HasTypeName, IsJavaFile {

    public static final String SUFFIX = "View";

    private final Package pack;
    private final Entity entity;
    private final String name;
    private final TypeName typeName;
    private final ClassName innerPresenterTypeName;

    public ViewInterface(Package pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
        this.name = entity.getName() + SUFFIX;
        this.typeName = ClassName.get(pack.getCanonicalName(), name);
        this.innerPresenterTypeName = ClassName.get(pack.getCanonicalName(), name, "Presenter");
    }

    public Package getPackage() {
        return pack;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TypeName getTypeName() {
        return typeName;
    }

    public TypeName getInnerPresenterTypeName() {
        return innerPresenterTypeName;
    }

    @Override
    public JavaFile asJavaFile() {
        final TypeSpec presenterItf = TypeSpec.interfaceBuilder("Presenter")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addSuperinterface(TypeNames.PRESENTER)
                .build();

        final ClassName viewImplTypeName = ClassName.get(pack.getCanonicalName(), name + "Impl");

        final TypeSpec viewItf = TypeSpec.interfaceBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeNames.VIEW, innerPresenterTypeName))
                .addAnnotation(AnnotationSpec.builder(TypeNames.IMPLEMENTED_BY)
                        .addMember("value", "$T.class", viewImplTypeName)
                        .build())
                .addType(presenterItf)
                .build();

        return JavaFile.builder(pack.getCanonicalName(), viewItf).build();
    }
}
