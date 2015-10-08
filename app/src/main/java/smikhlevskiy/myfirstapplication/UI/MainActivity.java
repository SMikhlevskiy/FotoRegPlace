package smikhlevskiy.myfirstapplication.UI;

import android.app.AlertDialog;
import android.app.Dialog;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.Util.SMikhlevskiyUtils;
import smikhlevskiy.myfirstapplication.model.InterfaceMainActivity;
import smikhlevskiy.myfirstapplication.model.RegPlaceDB;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

public class MainActivity extends AppCompatActivity implements InterfaceMainActivity {


    Fragment fragmentAddItem;
    Fragment fragList;
    Fragment fragmentSettings;
    Fragment fragmentFotoList;
    RegPlaceDB regPlaceDB;

    public void showFragmentAddItem(RegPlaceItem regPlaceItem) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.MainFrame, fragmentAddItem);
        ft.commit();
        ((FragmentAddItem) fragmentAddItem).setRegPlaceItem(regPlaceItem);
    }
    public void showFragmentFotoList() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.MainFrame, fragmentFotoList);
        ft.commit();

    }

    public void showSettings() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.MainFrame, fragmentSettings);
        ft.commit();

    }

    public void showFragmentList() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.MainFrame, fragList);
        ft.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "onCreate:Begin");
        super.onCreate(savedInstanceState);
/*
        for (Fragment aFrag : getFragmentManager().get .getFragments()) {
            getFragmentManager().beginTransaction().remove(aFrag).commit();
        }
        */

        setContentView(R.layout.activity_main);
        regPlaceDB = new RegPlaceDB(this, "dbpafp2", null, 3);
        SharedPreferences setting = getSharedPreferences(getString(R.string.regFotoSharedPreferencesName), 0);
        //if (savedInstanceState == null) {

        fragmentAddItem = new FragmentAddItem();
        fragmentSettings = new FragmentSettings();
        fragmentFotoList = new FragmentViewPagerGalary(getSupportFragmentManager());
        fragList = new FragmentList();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.MainFrame, fragList);
        ft.commit();

        Log.i("MainActivity", "Create DB");
        ((FragmentList) fragList).adapterRegPlaceList.setSharedPrefernces(setting);
        ((FragmentList) fragList).adapterRegPlaceList.setRegPlaceList(regPlaceDB.getSavedItems());


        Log.i("MainActivity", "onCreate:End");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ok: {
                RegPlaceItem rpi = ((FragmentAddItem) fragmentAddItem).getResult();
                regPlaceDB.SaveItem(rpi);

                ((FragmentList) fragList).adapterRegPlaceList.setRegPlaceList(regPlaceDB.getSavedItems());

                showFragmentList();

                break;
            }
            case R.id.ok_fragsettings: {


                ((FragmentList) fragList).adapterRegPlaceList.setRegPlaceList(regPlaceDB.getSavedItems());

                showFragmentList();

                break;
            }

            case R.id.plus: {

                showFragmentAddItem(null);

                break;
            }
            case R.id.delete: {

                regPlaceDB.deleteItem(((FragmentAddItem) fragmentAddItem).getRegPlaceItem());
                ((FragmentList) fragList).adapterRegPlaceList.setRegPlaceList(regPlaceDB.getSavedItems());
                showFragmentList();
                break;
            }
            case R.id.settings: {
                showSettings();
                break;
            }
            case R.id.show_galary:{
                showFragmentFotoList();
                break;
            }


        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
        if (resultCode == RESULT_OK) {
            if (intent == null) {

            } else {

                Bundle bndl = intent.getExtras();
                if (bndl != null) {
                    Object obj = intent.getExtras().get("data");
                    if (obj instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) obj;

                        ((FragmentAddItem) fragmentAddItem).setBitmap(bitmap);
                    }
                }
            }
        } else if (resultCode == RESULT_CANCELED) {


        }


    }

    protected Dialog onCreateDialog(int id) {
        Log.i(getString(R.string.nameMainActivityLog), "show Dialog");
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        //String a[]={"aaa","bb"};

        ArrayList<String> countryList = SMikhlevskiyUtils.getCountrysList();
        String countryArray[] = countryList.toArray(new String[countryList.size()]);

        adb.setItems(countryArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((FragmentAddItem) fragmentAddItem).setTextSpinnerCountry(which);
            }
        });

        return adb.create();
    }
}
