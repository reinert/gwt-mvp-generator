package com.growbit.tools.gmg;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.lang.reflect.ParameterizedType;

public class ViewImplClass implements HasName, HasTypeName, IsJavaFile {

    public static final String SUFFIX = "ViewImpl";

    private final ViewInterface viewInterface;
    private final String name;
    private final TypeName typeName;

    public ViewImplClass(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.name = viewInterface.getEntity().getName() + SUFFIX;
        this.typeName = ClassName.get(viewInterface.getPackage().getCanonicalName(), name);
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

//    @Singleton
//    public class LeadViewImpl extends AbstractView<LeadView.Presenter> implements LeadView {
//        interface LeadViewUiBinder extends UiBinder<HTMLPanel, LeadViewImpl> {}
//
//        private static LeadViewUiBinder ourUiBinder = GWT.create(LeadViewUiBinder.class);
//
//        public LeadViewImpl() {
//            initWidget(ourUiBinder.createAndBindUi(this));
//        }
//    }

    @Override
    public JavaFile asJavaFile() {
        final ClassName viewImplTypeName = ClassName.get(getPackage().getCanonicalName(), name);

        final TypeSpec uiBinderItf = TypeSpec.interfaceBuilder("UiBinder")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeNames.UI_BINDER, TypeNames.WIDGET, viewImplTypeName))
                .build();

        final TypeSpec viewImplCls = TypeSpec.classBuilder(name)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(viewInterface.getTypeName())
                .addSuperinterface(
                        ParameterizedTypeName.get(TypeNames.ABSTRACT_VIEW, viewInterface.getInnerPresenterTypeName()))
                .addAnnotation(TypeNames.SINGLETON)
                .addType(uiBinderItf)
                .build();

        return JavaFile.builder(getPackage().getCanonicalName(), viewImplCls).build();
    }
}
