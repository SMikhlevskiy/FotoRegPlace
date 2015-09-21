package smikhlevskiy.myfirstapplication.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by tcont98 on 15-Sep-15.
 */
public class RegPlaceItemData {
    public String getName() {
        return name;
    }

    private String name;
    private String address;
    private String date;
    private String time;
    private String comment;
    private Bitmap bitmap=null;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }



    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
