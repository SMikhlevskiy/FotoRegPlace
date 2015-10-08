package smikhlevskiy.myfirstapplication.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import smikhlevskiy.myfirstapplication.UI.FragmentGalaryItem;
import smikhlevskiy.myfirstapplication.UI.FragmentViewPagerGalary;

/**
 * Created by tcont98 on 08-Oct-15.
 */
public class GalaryPagerAdapter extends FragmentStatePagerAdapter {
    public GalaryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentGalaryItem=new FragmentGalaryItem();

        Bundle args=new Bundle();
        args.putInt(((FragmentGalaryItem)fragmentGalaryItem).ArgObject,position+1);

        fragmentGalaryItem.setArguments(args);


        return (Fragment)fragmentGalaryItem;
    }

    @Override
    public int getCount() {
        return 100;
    }
}
