package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;


import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.adapters.GalaryPagerAdapter;

/**
 * Created by tcont98 on 08-Oct-15.
 */
public class FragmentViewPagerGalary extends Fragment {
    private Activity masterActivity;
    private GalaryPagerAdapter galaryPagerAdapter;
    private android.support.v4.app.FragmentManager fragmentManager;

    public FragmentViewPagerGalary(android.support.v4.app.FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        masterActivity = activity;


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fw= inflater.inflate(R.layout.fragment_viewpager_galary, null);
        if (fragmentManager==null)
                Log.i("viewpager", "fragmentManager=null");
        galaryPagerAdapter=new GalaryPagerAdapter(fragmentManager);
        ((ViewPager)fw.findViewById(R.id.viewpager)).setAdapter(galaryPagerAdapter);

        return fw;
    }
}
