package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import smikhlevskiy.myfirstapplication.R;

/**
 * Created by tcont98 on 08-Oct-15.
 */
public class FragmentGalaryItem extends Fragment {
    public static final String ArgObject="object";
    private Activity masterActivity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        masterActivity = activity;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fw= inflater.inflate(R.layout.fragment_galary_item, null);

        Bundle args = getArguments();
        ((TextView)fw.findViewById(R.id.textView)).setText(Integer.toString(args.getInt(ArgObject)));

        return fw;
    }
}
