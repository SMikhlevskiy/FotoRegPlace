package smikhlevskiy.myfirstapplication.UI;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import smikhlevskiy.myfirstapplication.R;

public class MainActivity extends AppCompatActivity {


    Fragment fragmentAddItem;
    Fragment fragList;


    public void showFragmentAddItem() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.MainFrame, fragmentAddItem);
        ft.commit();
    }

    public void showFragmentList() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.MainFrame, fragList);
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

                ((FragmentList) fragList).addMessage(((FragmentAddItem) fragmentAddItem).getResult());
                showFragmentList();

                break;
            }
            case R.id.plus: {
                showFragmentAddItem();

                break;
            }

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        Toast.makeText(MainActivity.this,"1",Toast.LENGTH_LONG).show();
        if (resultCode == RESULT_OK) {
            if (intent == null) {

            } else {

                Bundle bndl = intent.getExtras();
                if (bndl != null) {
                    Object obj = intent.getExtras().get("data");
                    if (obj instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) obj;

                        ((FragmentAddItem) fragmentAddItem).setBitmap(bitmap);
                    }
                }
            }
        } else if (resultCode == RESULT_CANCELED) {


        }


    }
}
