package smikhlevskiy.myfirstapplication.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

/**
 * Created by tcont98 on 21-Sep-15.
 */
public class AdapterMainList extends BaseAdapter {


    private ArrayList<RegPlaceItem> regPlaceList = new ArrayList();

    private SharedPreferences sharedPreferences;
    private Context context;


    public void setContext(Context context) {
        this.context = context;
    }

    public void add(RegPlaceItem itemData) {
        regPlaceList.add(itemData);
    }

    @Override
    public int getCount() {
        return regPlaceList.size();
    }

    @Override
    public Object getItem(int position) {

        return regPlaceList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = lInflater.inflate(R.layout.main_list_item, parent, false);
            RegPlaceItem itemData = regPlaceList.get(position);
            TextView textViewAddress = (TextView) convertView.findViewById(R.id.adress);
            if (!sharedPreferences.getBoolean("pref_show_address", true))
                textViewAddress.setVisibility(View.GONE);
            else
                textViewAddress.setVisibility(View.VISIBLE);
            textViewAddress.setText(itemData.getAddress());

            TextView textViewName = (TextView) convertView.findViewById(R.id.regPlaceItemName);
            textViewName.setText(itemData.getName());

            TextView textViewTime = (TextView) convertView.findViewById(R.id.date);
            textViewTime.setText(itemData.getDate() + " " + itemData.getTime());

            if (!sharedPreferences.getBoolean("pref_show_datetime", true)) {

                textViewTime.setVisibility(View.GONE);
            } else {

                textViewTime.setVisibility(View.VISIBLE);
            }

            itemData.setImageViewBitmap((ImageView) convertView.findViewById(R.id.imageFoto),convertView.getContext().getContentResolver());
            /*
            if (itemData.getBitmap() != null) {
                ((ImageView) convertView.findViewById(R.id.imageFoto)).setImageBitmap(itemData.getBitmap());
                Log.i("MainActivity", "Bitamp!!!!!");
            } else if (itemData.getUri().trim().length() > 0) {
                Log.i("MainActivity", itemData.getUri());
                //((ImageView) convertView.findViewById(R.id.imageFoto)).setImageBitmap(SMikhlevskiyUtils.loadBitmap(itemData.getUri(), convertView.getContext().getContentResolver()));
                AsyncTaskImageLoader asyncTaskImageLoader=new AsyncTaskImageLoader((ImageView) convertView.findViewById(R.id.imageFoto),convertView.getContext().getContentResolver());
                asyncTaskImageLoader.execute(itemData.getUri());
                //SMikhlevskiyUtils.grabImage(convertView.getContext(), (ImageView) convertView.findViewById(R.id.imageFoto), Uri.parse(itemData.getUri()));

            }
            */


        }

/*
<CheckBoxPreference
        android:key="pref_show_address"
        android:summary="Show Address in List"
        android:title="Show Address"
        android:defaultValue="true"/>
    <CheckBoxPreference
        android:key="pref_show_datetime"
        android:summary="Show Date and Time in List"
        android:title="Show Date and Time"
        android:defaultValue="false"/>
    <CheckBoxPreference
        android:key="pref_show_country"
        android:summary="Show Country in List"
        android:title="Show Country"
        android:defaultValue="false"/>
    <CheckBoxPreference
        android:key="pref_show_comment"
        android:summary="Show Comment in List"
        android:title="Show Comment"
        android:defaultValue="false"/>

 */
        return convertView;
    }

    public void setRegPlaceList(ArrayList<RegPlaceItem> regPlaceList) {


        this.regPlaceList = regPlaceList;


    }

    public void setSharedPrefernces(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

}
