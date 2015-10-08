package smikhlevskiy.myfirstapplication.Util;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
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
    public static final int fullSizedFotoIntent = 1;
    public static final int smallFotoIntent = 2;

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

    public static File createTemporaryFile(String part, String ext) throws Exception {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }

    /*
        public static void grabImage(Context context, ImageView imageView, Uri mImageUri) {
            context.getContentResolver().notifyChange(mImageUri, null);
            ContentResolver cr = context.getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {

            }
        }
        */
    public static Bitmap loadBitmap(String mImageUri, ContentResolver cr) {


        //context.getContentResolver().notifyChange(mImageUri, null);
        //ContentResolver cr = context.getContentResolver();
        cr.notifyChange(Uri.parse(mImageUri), null);

        try {
            return android.provider.MediaStore.Images.Media.getBitmap(cr, Uri.parse(mImageUri));
            //imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            return null;
        }


    }
}
