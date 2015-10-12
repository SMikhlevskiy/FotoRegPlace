package smikhlevskiy.myfirstapplication.UI;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.adapters.AdapterMainList;
import smikhlevskiy.myfirstapplication.model.InterfaceMainActivity;
import smikhlevskiy.myfirstapplication.model.RegPlaceItem;

public class FragmentMainList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Activity masterActivity;


    AdapterMainList adapterMainList = new AdapterMainList();


    private ListView messageListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    public void addMessage(RegPlaceItem itemData) {

        //Toast.makeText(context, s, Toast.LENGTH_LONG);


        adapterMainList.add(itemData);
        adapterMainList.notifyDataSetChanged();


        //adapter.notifyDataSetChanged();

        messageListView.setSelection(adapterMainList.getCount() - 1);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_list, container, false);


        messageListView = (ListView) v.findViewById(R.id.fListMessages);

        adapterMainList.setContext(v.getContext());
        messageListView.setAdapter(adapterMainList);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("MainActivity", "itemClick: position = " + position + ", id = "
                        + ((RegPlaceItem) adapterMainList.getItem(position)).getId());
                ((InterfaceMainActivity) masterActivity).showFragmentAddItem((RegPlaceItem) adapterMainList.getItem(position));
            }
        });


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
        inflater.inflate(R.menu.menu_frag_main_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
