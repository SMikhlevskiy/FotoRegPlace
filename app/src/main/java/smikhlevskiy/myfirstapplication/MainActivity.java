package smikhlevskiy.myfirstapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        ;
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


                                            adapter.add(textViewUser.getText().toString()+": "+editTextMessage.getText().toString());

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


}
