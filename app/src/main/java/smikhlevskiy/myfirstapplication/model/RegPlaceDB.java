package smikhlevskiy.myfirstapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.Util.SMikhlevskiyUtils;

/**
 * Created by tcont98 on 24-Sep-15.
 */
public class RegPlaceDB extends SQLiteOpenHelper {

    public void deleteItem(RegPlaceItem regPlaceItem) {
        SQLiteDatabase db = getWritableDatabase();
        if (regPlaceItem.getId() >= 0) {
            Log.i("MainActivity", "Delete Record id="+ regPlaceItem.getId());
            db.delete(RegPlaceItem.TABLE_NAME, "id=" + regPlaceItem.getId(), null);

        }
    }

    public RegPlaceDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RegPlaceItem.TABLE_NAME + " ( " +
                        RegPlaceItem.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        RegPlaceItem.KEY_NAME + " TEXT, " +
                        RegPlaceItem.KEY_ADDRESS + " TEXT, " +
                        RegPlaceItem.KEY_TIME + " TEXT, " +
                        RegPlaceItem.KEY_DATE + " TEXT, " +
                        RegPlaceItem.KEY_COMMENT + " TEXT, " +
                        RegPlaceItem.KEY_COUNTRY + " TEXT, " +
                        RegPlaceItem.KEY_URI + " TEXT, " +
                        RegPlaceItem.KEY_BITMAP + " BLOB)"


        );
    }

    public ArrayList<RegPlaceItem> getSavedItems() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<RegPlaceItem> rpiList = new ArrayList<RegPlaceItem>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + RegPlaceItem.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                RegPlaceItem rpi = new RegPlaceItem();
                rpi.setId(cursor.getInt(0));
                rpi.setName(cursor.getString(1));
                rpi.setAddress(cursor.getString(2));
                rpi.setTime(cursor.getString(3));
                rpi.setDate(cursor.getString(4));
                rpi.setComment(cursor.getString(5));
                rpi.setCountry(cursor.getString(6));
                rpi.setUri(cursor.getString(7));
                if (cursor.getBlob(8) != null) {
                    rpi.setBitmap(SMikhlevskiyUtils.byteArraytoBitmap(cursor.getBlob(8)));

                }
                rpiList.add(rpi);


            } while (cursor.moveToNext());
        }
        db.close();
        return rpiList;
    }

    //----------------------------------------
    public void SaveItem(RegPlaceItem regPlaceItem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RegPlaceItem.KEY_NAME, regPlaceItem.getName());
        cv.put(RegPlaceItem.KEY_ADDRESS, regPlaceItem.getAddress());
        cv.put(RegPlaceItem.KEY_DATE, regPlaceItem.getDate());
        cv.put(RegPlaceItem.KEY_TIME, regPlaceItem.getTime());
        cv.put(RegPlaceItem.KEY_COMMENT, regPlaceItem.getComment());

        cv.put(RegPlaceItem.KEY_COUNTRY, regPlaceItem.getCountry());
        cv.put(RegPlaceItem.KEY_URI, regPlaceItem.getUri());

        if (regPlaceItem.getBitmap() != null)
            cv.put(RegPlaceItem.KEY_BITMAP, SMikhlevskiyUtils.bitmapToByteArray(regPlaceItem.getBitmap()));

        if (regPlaceItem.getId() >= 0) {
            Log.i("MainActivity", "Update Record Id=" + regPlaceItem.getId());
            db.update(RegPlaceItem.TABLE_NAME, cv, "id=" + regPlaceItem.getId(), null);

        } else {
            Log.i("MainActivity", "Add Record");
            db.insert(RegPlaceItem.TABLE_NAME, null, cv);
        }
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
