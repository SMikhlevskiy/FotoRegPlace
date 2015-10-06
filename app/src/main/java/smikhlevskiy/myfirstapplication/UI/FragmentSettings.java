package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import smikhlevskiy.myfirstapplication.R;

/**
 * Created by tcont98 on 03-Oct-15.
 */
public class FragmentSettings extends PreferenceFragment{
    private Activity masterActivity;
    private View fragmentView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        masterActivity = activity;
    }
    @Override
    public void onCreate(
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        PreferenceManager pm=getPreferenceManager();
        pm.setSharedPreferencesName(getString(R.string.regFotoSharedPreferencesName));
        addPreferencesFromResource(R.xml.settings_list_item);


    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragsettings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
