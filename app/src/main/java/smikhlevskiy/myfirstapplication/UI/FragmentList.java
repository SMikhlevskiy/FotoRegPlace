package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.adapters.AdapterRegPlaceList;
import smikhlevskiy.myfirstapplication.model.MainActivityInterface;
import smikhlevskiy.myfirstapplication.model.RegPlaceItemData;

public class FragmentList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Activity masterActivity;

    private ArrayList<String> listItemsMessage = new ArrayList<String>();

    AdapterRegPlaceList adapterRegPlaceList=new AdapterRegPlaceList();

    private ArrayAdapter<String> adapter;
    private ListView messageListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    public void addMessage(Context context, String s) {

        //Toast.makeText(context, s, Toast.LENGTH_LONG);
        adapter.add(s);

        RegPlaceItemData itemData=new RegPlaceItemData();
        itemData.setName("aaaa");
        adapterRegPlaceList.add(itemData);
        adapterRegPlaceList.notifyDataSetChanged();


        //adapter.notifyDataSetChanged();
        messageListView.setSelection(adapter.getCount() - 1);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);

        // Inflate the layout for this fragment
        adapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,
                listItemsMessage);

        messageListView = (ListView) v.findViewById(R.id.fListMessages);

        adapterRegPlaceList.setContext(v.getContext());
        messageListView.setAdapter(adapterRegPlaceList);


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        masterActivity=activity;
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {

        super.onDetach();

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fraglist, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
