package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.adapters.AdapterRegPlaceList;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

public class FragmentList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Activity masterActivity;


    AdapterRegPlaceList adapterRegPlaceList = new AdapterRegPlaceList();


    private ListView messageListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    public void addMessage(RegPlaceItem itemData) {

        //Toast.makeText(context, s, Toast.LENGTH_LONG);


        adapterRegPlaceList.add(itemData);
        adapterRegPlaceList.notifyDataSetChanged();


        //adapter.notifyDataSetChanged();

        messageListView.setSelection(adapterRegPlaceList.getCount() - 1);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);


        messageListView = (ListView) v.findViewById(R.id.fListMessages);

        adapterRegPlaceList.setContext(v.getContext());
        messageListView.setAdapter(adapterRegPlaceList);


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        masterActivity = activity;
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
