package smikhlevskiy.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

public class MainActivity extends ActionBarActivity {
    private ArrayList<String> listItemsMessage=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Button btnSend;
    private EditText editTextMessage;
    private ListView messageListView;
    private Button buttonRegUser;
    private TextView textViewUser;
    private EditText editTextUser;
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i("MainActivity", "Hello");
        Log.d("MainActivity", "Hello");
        Log.e("MainActivity", "Hello");

        //Toast a=Toast.makeText(this,"Hello!!",Toast.LENGTH_LONG);
        //a.setGravity();
        //Toast. s .setGravity(Gravity.LEFT)


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItemsMessage);
        messageListView=(ListView)findViewById(R.id.listViewMessages);
        messageListView.setAdapter(adapter);

        btnSend = (Button) findViewById(R.id.buttonSendMessage);
        editTextMessage =(EditText)findViewById(R.id.editTextMessage);
        buttonRegUser=(Button)findViewById(R.id.buttonRegUser);
        textViewUser=(TextView)findViewById(R.id.textViewUser);
        editTextUser=(EditText)findViewById(R.id.editTextUser);

        btnSend.setOnClickListener( new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if (editTextMessage.getText().toString().trim().length()==0)
                                            {
                                                Toast.makeText(MainActivity.this,"Please, input message's text",Toast.LENGTH_LONG).show();
                                                return;
                                            }
                                            adapter.add(textViewUser.getText().toString() + ": " + editTextMessage.getText().toString());

                                            adapter.notifyDataSetChanged();
                                            editTextMessage.setText("");
                                            messageListView.setSelection(adapter.getCount() - 1);


                                        }
                                    }
        );


        buttonRegUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //textViewUser.setText();
                textViewUser.setText(editTextUser.getText().toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.plus: {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent,0);

                break;
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                
        // --------Get Function from ActivitySelectFunction--------------
        if (data != null) {

            /*if (requestCode == 1) */{
                adapter.add(data.getStringExtra("address") + ", " +
                        data.getStringExtra("time") + ", " +
                        data.getStringExtra("description"));

                adapter.notifyDataSetChanged();
                editTextMessage.setText("");
                messageListView.setSelection(adapter.getCount() - 1);

            }
        }
                /*
                editTextFunction.setText(editTextFunction.getText()
                        + formulaDrawController.formFunctionInsert(data.getStringExtra("name").toLowerCase()));
                        */

            super.onActivityResult(requestCode, resultCode, data);
        }


    }
