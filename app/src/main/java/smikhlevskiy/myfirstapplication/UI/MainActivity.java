package smikhlevskiy.myfirstapplication.UI;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import smikhlevskiy.myfirstapplication.R;
import smikhlevskiy.myfirstapplication.model.MainActivityInterface;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {



    Fragment fragmentAddItem;
    Fragment fragList;

    //private
    public void sendListItem(String s) {
        ((FragmentList) fragList).addMessage(MainActivity.this, s);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.MainFrame, fragList);
        ft.commit();

    }

    public void showFragmentAddItem(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.MainFrame, fragmentAddItem);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "onCreate:Begin");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        fragmentAddItem = new FragmentAddItem();
        fragList = new FragmentList();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.MainFrame, fragList);
        ft.commit();


        Log.i("MainActivity", "onCreate:End");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ok: {
                sendListItem("aaa");


                break;
            }
            case R.id.plus: {
                showFragmentAddItem();
                break;
            }

        }
        return true;
    }

}
