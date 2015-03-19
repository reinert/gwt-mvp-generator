package com.growbit.tools.gmg;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class ViewInterface implements HasName, HasTypeName, HasTypeSpec, IsJavaFile {

    public static final String SUFFIX = "View";

    private final Package pack;
    private final Entity entity;
    private final String name;
    private final TypeName typeName;
    private final ClassName innerPresenterTypeName;
    private final TypeSpec typeSpec;

    public ViewInterface(Package pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
        this.name = entity.getName() + SUFFIX;
        this.typeName = ClassName.get(pack.getCanonicalName(), name);
        this.innerPresenterTypeName = ClassName.get(pack.getCanonicalName(), name, "Presenter");
        this.typeSpec = createTypeSpec();
    }

    public Package getPackage() {
        return pack;
    }

    public Entity getEntity() {
        return entity;
    }

    public TypeName getInnerPresenterTypeName() {
        return innerPresenterTypeName;
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
        return JavaFile.builder(pack.getCanonicalName(), typeSpec).build();
    }

    private TypeSpec createTypeSpec() {
        final TypeSpec presenterItf = TypeSpec.interfaceBuilder("Presenter")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addSuperinterface(TypeNames.PRESENTER)
                .build();

        final ClassName viewImplTypeName = ClassName.get(pack.getCanonicalName(), name + "Impl");

        return TypeSpec.interfaceBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeNames.VIEW, innerPresenterTypeName))
                .addAnnotation(AnnotationSpec.builder(TypeNames.IMPLEMENTED_BY)
                        .addMember("value", "$T.class", viewImplTypeName)
                        .build())
                .addType(presenterItf)
                .build();
    }
}
