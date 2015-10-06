package smikhlevskiy.myfirstapplication.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by tcont98 on 15-Sep-15.
 */
public class RegPlaceItem implements Parcelable {
    public static final String TABLE_NAME = "itemsrpi";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_BITMAP = "bitmap";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_URI = "uri";


    private int id;
    private String name;
    private String address;
    private String date;
    private String time;
    private String country;
    private String comment;

    private String uri="";
    private Bitmap bitmap = null;

    public static final Creator<RegPlaceItem> CREATOR = new Creator<RegPlaceItem>() {
        @Override
        public RegPlaceItem createFromParcel(Parcel in) {
            return new RegPlaceItem(in);
        }

        @Override
        public RegPlaceItem[] newArray(int size) {
            return new RegPlaceItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(country);
        dest.writeString(comment);
        dest.writeParcelable(bitmap, flags);
        dest.writeString(uri);

    }

    public RegPlaceItem() {

    }

    public RegPlaceItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        date = in.readString();
        time = in.readString();
        country = in.readString();
        comment = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        uri =  in.readString();
    }

    public String getName() {
        return name;
    }

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


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
