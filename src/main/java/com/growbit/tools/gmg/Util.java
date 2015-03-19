package com.growbit.tools.gmg;

public final class Util {

    public static String convertStreamToString(java.io.InputStream is) {
        final java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
