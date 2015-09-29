package smikhlevskiy.myfirstapplication.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by tcont98 on 15-Sep-15.
 */
public class RegPlaceItem {
    public static final String TABLE_NAME = "itemsrpi";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_BITMAP = "bitmap";
    public static final String KEY_COUNTRY = "country";


    public String getName() {
        return name;
    }


    private int id;
    private String name;
    private String address;
    private String date;
    private String time;




    private String country;
    private String comment;
    private Bitmap bitmap = null;

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

    public int getId() {
        return id;
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


    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
