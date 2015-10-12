package smikhlevskiy.myfirstapplication.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;


import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.UI.FragmentGalaryItem;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

/**
 * Created by tcont98 on 08-Oct-15.
 */
public class GalaryPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<RegPlaceItem> regPlaceList;


    public GalaryPagerAdapter(FragmentManager fm, ArrayList<RegPlaceItem> regPlaceList) {
        //RegPlaceDB regPlaceDB = new RegPlaceDB(this, SMikhlevskiyUtils.regFotoPlaceDBName , null, 3);
        //regPlaceList=regPlaceDB.getSavedItems();

        super(fm);
        this.regPlaceList=regPlaceList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentGalaryItem=new FragmentGalaryItem();

        Bundle args=new Bundle();
        //args.putString(((FragmentGalaryItem)fragmentGalaryItem).ArgObject,regPlaceList.get(position).getName());
        args.putParcelable(RegPlaceItem.class.getName(), regPlaceList.get(position));
        Log.i("viewpager", regPlaceList.get(position).getName());

        fragmentGalaryItem.setArguments(args);


        return (Fragment)fragmentGalaryItem;
    }

    @Override
    public int getCount() {
        return regPlaceList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return regPlaceList.get(position).getName();
    }
}
