package com.demoapp.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rupesh on 23/6/16.
 */
public class StringUtils {
    public static String readJsonFile(Context context, String language) {
        InputStream is;
        String bufferString = null;
        try {
            is = context.getAssets().open(language);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            bufferString = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferString;
    }
}
