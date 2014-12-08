package net.skink.wow1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;


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

        //          String converted = Base64.encodeToString(toConvert.toString().getBytes(), Base64.DEFAULT));
        String stringConverted = Base64.encodeToString(stringHex.toString().getBytes(),Base64.DEFAULT );



        // example code #1
        String Str1 = new String("49276d20");
        Log.d("JFD", stringConverted );

        try{
            byte[] Str2 = Str1.getBytes();
            Log.d("JFD", "Returned  Value " + Str2 );

            Str2 = Str1.getBytes( "UTF-8" );
            Log.d("JFD", "Returned  Value " + Str2 );

            Str2 = Str1.getBytes( "ISO-8859-1" );
            Log.d("JFD", "Returned  Value " + Str2 );
        }catch( Exception e){
            Log.d("JFD", "Unsupported character set");
        }

        // example #2
        // byte[] _bytes;
        // String stringFoo = new String(_bytes, "UTF-8");

        // #example #3
        String s = stringHex;
        try {
            byte[] b = s.getBytes("UTF-8");
            stringConverted = Base64.encodeToString(b,Base64.DEFAULT );
            Log.d("JFD", "s = " + s);
        } catch (Exception e ) {
            Log.d("JFD", "UnsupportedEncodingException");
        }


        //The string:
        // 49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d
        // Should produce:
        //
        // SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t
        // So go ahead and make that happen. You'll need to use this code for the rest of the exercises.



        //The following is a good solution -
//
//                String converted = Base64.encodeToString(toConvert.toString().getBytes(), Base64.DEFAULT));
//
        //String stringFromBase = new String(Base64.decode(converted, Base64.DEFAULT));
        //That's it. A single line encoding and decoding.




        EditText editTextBase64 = (EditText) findViewById(R.id.editTextBase64);
//        base64.setTextSize(20);
        editTextBase64.setText(stringConverted);

    }

    public void onClickToHex(View view) {

        EditText editTextBase64 = (EditText) findViewById(R.id.editTextBase64);
        String stringBase64 = editTextBase64.getText().toString();
        EditText editTextHex = (EditText) findViewById(R.id.editTextHex);
        editTextHex.setText(stringBase64);


    }
}
