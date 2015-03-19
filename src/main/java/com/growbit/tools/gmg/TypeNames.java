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
    public static final ClassName INJECT = ClassName.get("com.google.inject", "Inject");
    public static final ClassName SINGLETON = ClassName.get("com.google.inject", "Singleton");
    public static final ClassName ASSISTED = ClassName.get("com.google.inject.assistedinject", "Assisted");

    // google.gwt
    public static final ClassName GWT = ClassName.get("com.google.gwt.core.client", "GWT");
    public static final ClassName UI_BINDER = ClassName.get("com.google.gwt.uibinder.client", "UiBinder");
    public static final ClassName UI_FIELD = ClassName.get("com.google.gwt.uibinder.client", "UiField");
    public static final ClassName UI_HANDLER = ClassName.get("com.google.gwt.uibinder.client", "UiHandler");
    // google.gwt widgets
    public static final ClassName WIDGET = ClassName.get("com.google.gwt.user.client.ui", "Widget");
    public static final ClassName ACCEPTS_ONE_WIDGET = ClassName.get("com.google.gwt.user.client.ui", "AcceptsOneWidget");
    public static final ClassName HTMLPANEL = ClassName.get("com.google.gwt.user.client.ui", "HTMLPanel");
    // google.gwt place
    public static final ClassName PLACE = ClassName.get("com.google.gwt.place.shared", "Place");
    public static final ClassName PLACE_CONTROLLER = ClassName.get("com.google.gwt.place.shared", "PlaceController");

}
