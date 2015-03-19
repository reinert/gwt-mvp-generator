package com.growbit.tools.gmg;

import com.squareup.javapoet.ClassName;

public final class TypeNames {

    // growbit.mvp
    public static final ClassName PRESENTER = ClassName.get("br.com.salesdomain.mvp", "Presenter");
    public static final ClassName ABSTRACT_PRESENTER = ClassName.get("br.com.salesdomain.mvp", "AbstractPresenter");
    public static final ClassName VIEW = ClassName.get("br.com.salesdomain.mvp", "View");
    public static final ClassName ABSTRACT_VIEW = ClassName.get("br.com.salesdomain.mvp", "AbstractView");

    // google.inject
    public static final ClassName IMPLEMENTED_BY = ClassName.get("com.google.inject", "ImplementedBy");
    public static final ClassName SINGLETON = ClassName.get("com.google.inject", "Singleton");

    // google.gwt
    public static final ClassName UI_BINDER = ClassName.get("com.google.gwt.uibinder.client", "UiBinder");
    public static final ClassName UI_FIELD = ClassName.get("com.google.gwt.uibinder.client", "UiField");
    public static final ClassName UI_HANDLER = ClassName.get("com.google.gwt.uibinder.client", "UiHandler");
    // google.gwt widgets
    public static final ClassName WIDGET = ClassName.get("com.google.gwt.user.client.ui", "Widget");
    public static final ClassName HTMLPANEL = ClassName.get("com.google.gwt.user.client.ui", "HTMLPanel");
}
