package com.growbit.tools.gmg;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class ViewInterface implements HasTypeName, IsJavaFile {

    public static final String SUFFIX = "View";

    private final Package pack;
    private final Entity entity;
    private final String typeName;

    public ViewInterface(Package pack, Entity entity) {
        this.pack = pack;
        this.entity = entity;
        this.typeName = entity.getName() + SUFFIX;
    }

    public Package getPackage() {
        return pack;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

//    package br.com.salesdomain.app.lead;
//
//    import com.google.inject.ImplementedBy;
//    import br.com.salesdomain.mvp.View;
//
//    @ImplementedBy(LeadViewImpl.class)
//    public interface LeadView extends View<LeadView.Presenter> {
//
//        interface Presenter extends br.com.salesdomain.mvp.Presenter {
//        }
//    }

    @Override
    public JavaFile asJavaFile() {
        final TypeSpec presenterItf = TypeSpec.interfaceBuilder("Presenter")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addSuperinterface(Constants.PRESENTER_TYPE_NAME)
                .build();

        final ClassName ownPresenterTypeName = ClassName.get(pack.getCanonicalName(), typeName, "Presenter");
        final ClassName viewImplTypeName = ClassName.get(pack.getCanonicalName(), typeName + "Impl");

        final TypeSpec viewItf = TypeSpec.interfaceBuilder(typeName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(Constants.VIEW_TYPE_NAME, ownPresenterTypeName))
                .addAnnotation(AnnotationSpec.builder(Constants.IMPLEMENTED_BY_TYPE_NAME)
                        .addMember("value", "$T.class", viewImplTypeName)
                        .build())
                .addType(presenterItf)
                .build();

        return JavaFile.builder(pack.getCanonicalName(), viewItf).build();
    }
}
