package com.growbit.tools.gmg;

import com.squareup.javapoet.ClassName;

public final class Constants {

    // growbit.mvp
    public static final ClassName PRESENTER_TYPE_NAME = ClassName.get("br.com.salesdomain.mvp", "Presenter");
    public static final ClassName VIEW_TYPE_NAME = ClassName.get("br.com.salesdomain.mvp", "View");

    // google.inject
    public static final ClassName IMPLEMENTED_BY_TYPE_NAME = ClassName.get("com.google.inject", "ImplementedBy");
}
