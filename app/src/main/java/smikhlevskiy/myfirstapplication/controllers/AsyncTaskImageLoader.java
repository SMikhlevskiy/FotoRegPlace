package smikhlevskiy.myfirstapplication.controllers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import smikhlevskiy.myfirstapplication.Util.SMikhlevskiyUtils;

/**
 * Created by tcont98 on 07-Oct-15.
 */
public class AsyncTaskImageLoader extends AsyncTask<String, Void, Bitmap> {
    private WeakReference<ImageView> imageViewReference;
    ContentResolver cr;

    public AsyncTaskImageLoader(ImageView imageView, ContentResolver cr) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
        this.cr = cr;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ContentResolver a=getContentResolver();
        return SMikhlevskiyUtils.loadBitmap(params[0], cr);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null)
                if (bitmap != null)
                    imageView.setImageBitmap(bitmap);
        }
    }
}
