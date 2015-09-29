package smikhlevskiy.myfirstapplication.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by tcont98 on 29-Sep-15.
 */
public abstract class SMikhlevskiyUtils {
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy";
    public static final String TIME_FORMAT_NOW = "HH:mm:ss";

    public static String nowDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static String nowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static byte[] bitmapToByteArray(Bitmap b) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap byteArraytoBitmap(byte[] barray) {
        return BitmapFactory.decodeByteArray(barray, 0, barray.length);
    }

    public static ArrayList<String> getCountrysList() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);

        return countries;
    }

}
