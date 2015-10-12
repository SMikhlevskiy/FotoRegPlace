package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.adapters.GalaryPagerAdapter;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;
import smikhlevskiy.myfirstapplication.services.ServicePlayMusic;

/**
 * Created by tcont98 on 08-Oct-15.
 */
public class FragmentGalary extends Fragment {
    private Activity masterActivity;
    private GalaryPagerAdapter galaryPagerAdapter;
    private android.support.v4.app.FragmentManager fragmentManager;
    private ArrayList<RegPlaceItem> regPlaceList;

    public FragmentGalary(android.support.v4.app.FragmentManager fragmentManager,ArrayList<RegPlaceItem> regPlaceList) {
        this.fragmentManager = fragmentManager;
        this.regPlaceList=regPlaceList;


    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        masterActivity = activity;


            ServicePlayMusic servicePlayMusic = new ServicePlayMusic();
            Intent intent = new Intent();
        masterActivity.startService(intent);





    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fw= inflater.inflate(R.layout.fragment_galary, null);


        galaryPagerAdapter=new GalaryPagerAdapter(fragmentManager,regPlaceList);


        ViewPager viewPager=((ViewPager) fw.findViewById(R.id.viewpager));
        viewPager.setAdapter(galaryPagerAdapter);

        TabLayout tabLayout = (TabLayout) fw.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return fw;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_frag_galary, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.play_music: {
                Log.i(this.getClass().getName(), "startPlay music");


                Intent intentBR=new Intent(ServicePlayMusic.PLAY_HTTP_MUSIC);
                masterActivity.sendBroadcast(intentBR);

                break;
            }
            default:
            super.onOptionsItemSelected(item);


        }
        return true;
    }
}
