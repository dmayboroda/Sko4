package com.sko4;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utils class.
 * Created by Mayboroda.
 * */
public final class Utils {

    private Utils() {
        throw new AssertionError("Unable to instantiate");
    }

    /** Fonts names. */
    public static final String ROBOTO_LIGHT     = "Roboto-Light.ttf";
    public static final String ROBOTO_MEDIUM    = "Roboto-Medium.ttf";
    public static final String ROBOTO_REGULAR   = "Roboto-Regular.ttf";
    public static final String ROBOTO_THIN      = "Roboto-Thin.ttf";
    public static final String ROBOTO_CON_LIGHT = "RobotoCondensed-Light.ttf";
    public static final String ROBOTO_BLACK     = "Roboto-Black.ttf";

    /** Generating typeface. */
    public static Typeface typeface(Context context, String name) {
        return Typeface.createFromAsset(context.getAssets(), name);
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
