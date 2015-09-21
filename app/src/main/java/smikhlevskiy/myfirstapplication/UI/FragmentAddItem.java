package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.model.RegPlaceItemData;

public class FragmentAddItem extends Fragment {


    private Activity masterActivity;
    private ImageView imageViewFoto;
    private String fotoPath;

    EditText editTextName;
    EditText editTextAddress;


    EditText editTextTime;
    EditText editTextDate;
    EditText editTextComment;

    Button changeDate;
    Button changeTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy";
    public static final String TIME_FORMAT_NOW = "HH:mm:ss";

    public String nowDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public String nowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        fotoPath = "";

        masterActivity = activity;


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_item, null);
        imageViewFoto = (ImageView) v.findViewById(R.id.imageViewFoto);

        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextAddress = (EditText) v.findViewById(R.id.editTextAddress);


        editTextTime = (EditText) v.findViewById(R.id.editTextTime);
        editTextDate = (EditText) v.findViewById(R.id.editTextDate);
        editTextComment = (EditText) v.findViewById(R.id.editTextComment);

        changeDate = (Button) v.findViewById(R.id.buttonChangeDate);
        changeTime = (Button) v.findViewById(R.id.buttonChangeTime);

        editTextDate.setText(nowDate());
        editTextTime.setText(nowTime());


        return v;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragadditem, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public RegPlaceItemData getResult() {
        RegPlaceItemData regPlaceItemData = new RegPlaceItemData();

        regPlaceItemData.setName(editTextName.getText().toString());
        regPlaceItemData.setAddress(editTextAddress.getText().toString());
        regPlaceItemData.setComment(editTextComment.getText().toString());
        regPlaceItemData.setFileName(fotoPath);

        regPlaceItemData.setDate(editTextDate.getText().toString());
        regPlaceItemData.setTime(editTextTime.getText().toString());


        return regPlaceItemData;
    }


}
