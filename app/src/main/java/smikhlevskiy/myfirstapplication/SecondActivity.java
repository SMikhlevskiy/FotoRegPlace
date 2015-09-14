package smikhlevskiy.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private EditText textEditAddress;
    private EditText textEditTime;
    private EditText textEditDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textEditAddress=(EditText)findViewById(R.id.editTextAddress);
        textEditTime=(EditText)findViewById(R.id.editTextTime);
        textEditDescription=(EditText)findViewById(R.id.editTextDescription);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.ok: {
                if ((textEditAddress.getText().toString().trim().length()==0) ||
                        (textEditTime.getText().toString().trim().length()==0) ||
                        (textEditDescription.getText().toString().trim().length()==0)
                        ){
                    Toast.makeText(this,"Fields length must be not null",Toast.LENGTH_LONG).show();
                    return true;
                }
                Intent answerInent = new Intent();
                answerInent.putExtra("address",textEditAddress.getText().toString());
                answerInent.putExtra("time",textEditTime.getText().toString());
                answerInent.putExtra("description", textEditDescription.getText().toString());

                setResult(RESULT_OK, answerInent);
                finish();

                break;
            }
        }
        return true;
    }

}
