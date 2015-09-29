package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.Util.SMikhlevskiyUtils;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

public class FragmentAddItem extends Fragment {


    public Activity masterActivity;
    private ImageView imageViewFoto;


    private EditText editTextName;
    private EditText editTextAddress;


    private EditText editTextTime;
    private EditText editTextDate;
    private EditText editTextComment;

    private Button changeDate;
    private Button changeTime;
    private Spinner spinnerCountrys;


    private Bitmap bitmap;
    private RegPlaceItem regPlaceItem=null;
    public void setBitmap(Bitmap bitmap) {

        this.bitmap = bitmap;
        imageViewFoto.setImageBitmap(bitmap);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


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


        spinnerCountrys = (Spinner) v.findViewById(R.id.spinnerCountry);
        ArrayList<String> countrysList = SMikhlevskiyUtils.getCountrysList();

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(masterActivity, android.R.layout.simple_spinner_item, countrysList);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCountrys.setAdapter(arrayAdapter);


        for (int i = 0; i < countrysList.size(); i++)
            if (((String) countrysList.get(i)).equals("Ukraine"))
                spinnerCountrys.setSelection(i);


        imageViewFoto = (ImageView) v.findViewById(R.id.imageViewFoto);

        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextAddress = (EditText) v.findViewById(R.id.editTextAddress);


        editTextTime = (EditText) v.findViewById(R.id.editTextTime);
        editTextDate = (EditText) v.findViewById(R.id.editTextDate);
        editTextComment = (EditText) v.findViewById(R.id.editTextComment);

        changeDate = (Button) v.findViewById(R.id.buttonChangeDate);
        changeTime = (Button) v.findViewById(R.id.buttonChangeTime);

        editTextDate.setText(SMikhlevskiyUtils.nowDate());
        editTextTime.setText(SMikhlevskiyUtils.nowTime());
        bitmap = null;
        imageViewFoto.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 Intent intent = new Intent();
                                                 intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                                                 startActivityForResult(intent, 1);

                                             }
                                         }
        );
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextTime.setText(hourOfDay + ":" + minute + ":00");
                    }


                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });



        if (regPlaceItem != null) {

            editTextName.setText(regPlaceItem.getName());

            editTextAddress.setText(regPlaceItem.getAddress());
            editTextComment.setText(regPlaceItem.getComment());
            setBitmap(regPlaceItem.getBitmap());


            editTextDate.setText(regPlaceItem.getDate());
            editTextTime.setText(regPlaceItem.getTime());

            for (int i = 0; i < countrysList.size(); i++)
                if (((String) countrysList.get(i)).equals(regPlaceItem.getCountry()))
                    spinnerCountrys.setSelection(i);

            //spinnerCountrys.setSe regPlaceItem.setCountry(spinnerCountrys.getSelectedItem().toString());

        }

        return v;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragadditem, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void setRegPlaceItem(RegPlaceItem regPlaceItem) {
        this.regPlaceItem=regPlaceItem;

    }


    public RegPlaceItem getResult() {
        RegPlaceItem regPlaceItem = new RegPlaceItem();

        regPlaceItem.setName(editTextName.getText().toString());
        regPlaceItem.setAddress(editTextAddress.getText().toString());
        regPlaceItem.setComment(editTextComment.getText().toString());
        if (bitmap != null)
            regPlaceItem.setBitmap(bitmap);

        regPlaceItem.setDate(editTextDate.getText().toString());
        regPlaceItem.setTime(editTextTime.getText().toString());
        regPlaceItem.setCountry(spinnerCountrys.getSelectedItem().toString());

        if (this.regPlaceItem==null)//add
            regPlaceItem.setId(-1); else
            regPlaceItem.setId(this.regPlaceItem.getId());//update



        return regPlaceItem;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        //Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
        if (resultCode == Activity.RESULT_OK) {

            if (intent == null) {

            } else {

                Bundle bndl = intent.getExtras();
                if (bndl != null) {
                    Object obj = intent.getExtras().get("data");
                    if (obj instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) obj;

                        setBitmap(bitmap);
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            editTextComment.setText("cancel");

        }


    }

}
