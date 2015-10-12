package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Log;
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
import android.widget.Toast;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    private Button changeCountryButton;
    private Button fullSizedFotoButton;

    private View fragmentView;

    private Uri mImageUri = null;


    private Bitmap bitmap;
    private RegPlaceItem regPlaceItem = null;

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

    public void setTextSpinnerCountry(int id) {

        spinnerCountrys.setSelection(id);
    }

    public void setTextSpinnerCountry(String text) {
        ArrayList<String> countrysList = SMikhlevskiyUtils.getCountrysList();

        for (int i = 0; i < countrysList.size(); i++)
            if (((String) countrysList.get(i)).equals(text))
                spinnerCountrys.setSelection(i);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onStart() {
        Log.i("fragAddItem", "onStart");
        if (regPlaceItem != null) {

            editTextName.setText(regPlaceItem.getName());
            Log.i("MainActivity", "Edit item:" + regPlaceItem.getName());

            editTextAddress.setText(regPlaceItem.getAddress());
            editTextComment.setText(regPlaceItem.getComment());


            setBitmap(regPlaceItem.getBitmap());
            if (regPlaceItem.getUri().length() > 0) {
                imageViewFoto.setImageBitmap(SMikhlevskiyUtils.loadBitmap(regPlaceItem.getUri(),masterActivity.getContentResolver()));
                mImageUri = Uri.parse(regPlaceItem.getUri());
                /*
                mImageUri = Uri.parse(regPlaceItem.getUri());
                SMikhlevskiyUtils.grabImage(masterActivity, imageViewFoto, mImageUri);
                */
                Log.i("MainActivity", "URI");
            }


            editTextDate.setText(regPlaceItem.getDate());
            editTextTime.setText(regPlaceItem.getTime());

            setTextSpinnerCountry(regPlaceItem.getCountry());

            //spinnerCountrys.setSe regPlaceItem.setCountry(spinnerCountrys.getSelectedItem().toString());

        } else {
            setTextSpinnerCountry("Ukraine");

        }

        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("frag", "onCreateView");

        fragmentView = inflater.inflate(R.layout.fragment_add_item, null);


        spinnerCountrys = (Spinner) fragmentView.findViewById(R.id.spinnerCountry);
        ArrayList<String> countrysList = SMikhlevskiyUtils.getCountrysList();

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(masterActivity, android.R.layout.simple_spinner_item, countrysList);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCountrys.setAdapter(arrayAdapter);


        imageViewFoto = (ImageView) fragmentView.findViewById(R.id.imageViewFoto);

        editTextName = (EditText) fragmentView.findViewById(R.id.editTextName);
        editTextAddress = (EditText) fragmentView.findViewById(R.id.editTextAddress);


        editTextTime = (EditText) fragmentView.findViewById(R.id.editTextTime);
        editTextDate = (EditText) fragmentView.findViewById(R.id.editTextDate);
        editTextComment = (EditText) fragmentView.findViewById(R.id.editTextComment);

        changeDate = (Button) fragmentView.findViewById(R.id.buttonChangeDate);
        changeTime = (Button) fragmentView.findViewById(R.id.buttonChangeTime);

        changeCountryButton = (Button) fragmentView.findViewById(R.id.changeCountryButton);
        fullSizedFotoButton = (Button) fragmentView.findViewById(R.id.fullSizedFotoButton);

        editTextDate.setText(SMikhlevskiyUtils.nowDate());
        editTextTime.setText(SMikhlevskiyUtils.nowTime());
        bitmap = null;
        mImageUri = null;
        //-----------------------------------
        imageViewFoto.setOnClickListener(new View.OnClickListener() {
                                             public void onClick(View v) {
                                                 Intent intent = new Intent();
                                                 intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                                                 startActivityForResult(intent, SMikhlevskiyUtils.smallFotoIntent);

                                             }
                                         }
        );
        //------------------------------------
        fullSizedFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo;
                try {
                    // place where to store camera taken picture
                    photo = SMikhlevskiyUtils.createTemporaryFile("img_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), ".jpg");
                    photo.delete();
                } catch (Exception e) {
                    Toast.makeText(masterActivity, "Please check SD card! Image shot is impossible!", Toast.LENGTH_LONG);
                    return;

                }
                mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                //start camera intent
                startActivityForResult(intent, SMikhlevskiyUtils.fullSizedFotoIntent);
            }
        });
        //---------------------------------------
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
        //--------------------------------
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
        //------------------------
        changeCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masterActivity.showDialog(0);
            }
        });


        return fragmentView;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragadditem, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void setRegPlaceItem(RegPlaceItem regPlaceItem) {
        this.regPlaceItem = regPlaceItem;

    }

    public RegPlaceItem getRegPlaceItem() {
        return this.regPlaceItem;

    }

    //-----------------------------------------------------------------------------------------
    public RegPlaceItem getResult() {
        RegPlaceItem regPlaceItem = new RegPlaceItem();

        regPlaceItem.setName(editTextName.getText().toString());
        regPlaceItem.setAddress(editTextAddress.getText().toString());
        regPlaceItem.setComment(editTextComment.getText().toString());
        if (bitmap != null) {
            regPlaceItem.setBitmap(bitmap);
            Log.i(getString(R.string.nameMainActivityLog), "saveBitmap");
        } else if (mImageUri != null) {
            regPlaceItem.setUri(mImageUri.toString());
            Log.i(getString(R.string.nameMainActivityLog), "saveUri");
        } else
            regPlaceItem.setUri("");

        regPlaceItem.setDate(editTextDate.getText().toString());
        regPlaceItem.setTime(editTextTime.getText().toString());
        regPlaceItem.setCountry(spinnerCountrys.getSelectedItem().toString());

        if (this.regPlaceItem == null)//add
            regPlaceItem.setId(-1);
        else
            regPlaceItem.setId(this.regPlaceItem.getId());//update


        return regPlaceItem;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        Log.i(getString(R.string.nameMainActivityLog), "getResult:" + requestCode);
        if (requestCode == SMikhlevskiyUtils.smallFotoIntent) {
            if (resultCode == Activity.RESULT_OK) {

                if (intent == null) {

                } else {

                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            mImageUri = null;
                            setBitmap(bitmap);
                            Log.i(getString(R.string.nameMainActivityLog), "SmallFoto");
                        }
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {


            }
        } else if (requestCode == SMikhlevskiyUtils.fullSizedFotoIntent && resultCode == Activity.RESULT_OK) {

            //... some code to inflate/create/find appropriate ImageView to place grabbed image
            imageViewFoto.setImageBitmap(SMikhlevskiyUtils.loadBitmap(mImageUri.toString(),masterActivity.getContentResolver()));
            //SMikhlevskiyUtils.grabImage(masterActivity, imageViewFoto, mImageUri);


            Log.i(getString(R.string.nameMainActivityLog), "fullSizedFoto");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RegPlaceItem", regPlaceItem);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {

            regPlaceItem = (RegPlaceItem) savedInstanceState.getParcelable("RegPlaceItem");
        }

    }


}
