package com.unisul.leitor.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndroidUtils {
    public static boolean isNetworkAvailable(final Context context) {
        if (context == null) {
            return false;
        }

        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

        return false;
    }
}
