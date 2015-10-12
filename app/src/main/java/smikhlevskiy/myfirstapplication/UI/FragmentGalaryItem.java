package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

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
        //((TextView)fw.findViewById(R.id.textView)).setText(args.getString(ArgObject));
        RegPlaceItem regPlaceItem=args.getParcelable(RegPlaceItem.class.getName());
        ((TextView)fw.findViewById(R.id.textView)).setText(regPlaceItem.getName());
        regPlaceItem.setImageViewBitmap((ImageView)fw.findViewById(R.id.galaryImageView),fw.getContext().getContentResolver());



        return fw;
    }
}
