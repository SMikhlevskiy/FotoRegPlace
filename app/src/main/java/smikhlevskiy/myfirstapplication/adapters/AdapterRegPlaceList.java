package smikhlevskiy.myfirstapplication.adapters;

import android.content.Context;
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
public class AdapterRegPlaceList extends BaseAdapter {

    public void setRegPlaceList(ArrayList<RegPlaceItem> regPlaceList) {
        this.regPlaceList = regPlaceList;
    }

    private ArrayList <RegPlaceItem>regPlaceList = new ArrayList();


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
            convertView = lInflater.inflate(R.layout.item_regplacelist, parent, false);
            RegPlaceItem itemData=regPlaceList.get(position);
            ((TextView) convertView.findViewById(R.id.adress)).setText(itemData.getAddress());
            ((TextView) convertView.findViewById(R.id.regPlaceItemName)).setText(itemData.getName());
            ((TextView) convertView.findViewById(R.id.date)).setText(itemData.getDate()+" "+itemData.getTime());
            if (itemData.getBitmap()!=null)
            ((ImageView) convertView.findViewById(R.id.imageFoto)).setImageBitmap(itemData.getBitmap());


        }


        return convertView;
    }
}
