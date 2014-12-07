package net.skink.wow1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "net.skink.wow1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // JFD The original code.
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);

        // JFD The adding an activity bar code
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        // JFD the add activity bar method
        // but this does not compile
        //return super.OnCreateOptionsMenu(menu);

        // JFD the original code
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickToBase64(View view) {
        EditText editTextHex = (EditText) findViewById(R.id.editTextHex);
        String stringHex = editTextHex.getText().toString();
        EditText editTextBase64 = (EditText) findViewById(R.id.editTextBase64);
//        base64.setTextSize(20);
        editTextBase64.setText(stringHex);

    }

    public void onClickToHex(View view) {

        EditText editTextBase64 = (EditText) findViewById(R.id.editTextBase64);
        String stringBase64 = editTextBase64.getText().toString();
        EditText editTextHex = (EditText) findViewById(R.id.editTextHex);
        editTextHex.setText(stringBase64);


    }
}
