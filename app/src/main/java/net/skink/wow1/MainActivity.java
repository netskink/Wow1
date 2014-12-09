package net.skink.wow1;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;


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

        // handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_toAscii:
                onActionToAscii();
                return true;

            case R.id.action_toHex:
                onActionToHex();
                return true;

            case R.id.action_Copy:
                onActionCopy();
                return true;

            case R.id.action_Delete:
                onActionDelete();
                return true;

            case R.id.action_SelectAll:
                onActionSelectAll();
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void onActionCopy() {

        View v = getCurrentFocus();
        int id = v.getId();
        EditText editText = (EditText) findViewById(id);



        String text = editText.getText().toString();

        // Gets a handle to the clipboard service.
        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        // Creates a new text clip to put on the clipboard
        ClipData myClip = ClipData.newPlainText("simple text",text);
        //myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();

    }


    public void onActionDelete() {

        View v = getCurrentFocus();
        int id = v.getId();
        EditText editText = (EditText) findViewById(id);
        editText.setText("");
    }


    public void onActionSelectAll() {

        View v = getCurrentFocus();
        int id = v.getId();
        EditText editText = (EditText) findViewById(id);

        editText.selectAll();

    }

    public void onActionToAscii() {


        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();
        String stringHex = stringInput;

        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);


        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < stringHex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = stringHex.substring(i, (i + 2));
            //convert hex to decimal
            try {
                int decimal = Integer.parseInt(output, 16);
                //convert the decimal to character
                sb.append((char) decimal);

                temp.append(decimal);

            }catch( Exception e){
                Log.d("JFD", "Not a hex sequence");
                editTextOutput.setText("error, input sequence must be hex string");
                return;
            }
        }
        Log.d("JFD", "Decimal : " + temp.toString());
        Log.d("JFD", "sb.toString : " + sb.toString());


        String stringConverted = sb.toString();

        editTextOutput.setText(stringConverted);

    }


    public void onActionToHex() {

        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();


        byte[] b = stringInput.getBytes();
        Log.d("JFD", "b = " + b[0] + b[1] + b[2]);

        // b[n] = decimal ascii value

        StringBuilder sb = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < b.length; i++) {

            //grab the decimal as individual decimal ascii values
            //int decimal = Integer.parseInt(b[i], 10);
            //convert the decimal to character
            sb.append(Integer.toHexString(b[i]));

//            int decimal = Integer.parseInt( b[i].toString() , 1);

        }
        Log.d("JFD", "sb.toString : " + sb.toString());

        String stringConverted = sb.toString();
        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
        editTextOutput.setText(stringConverted);

    }




    ///////////////////////////////////////////////
    ///////////////////////////////////////////////



    public void onClickToAscii2() {

        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();

        byte[] b = stringInput.getBytes();
        Log.d("JFD", "b = " + b[0] + b[1] + b[2]);

        String stringConverted = Arrays.toString(b);
        Log.d("JFD", "stringConverted = " + stringConverted);

        //        String response = "[-47, 1, 16, 84, 2, 101, 110, 83, 111, 109, 101, 32, 78, 70, 67, 32, 68, 97, 116, 97]";      // response from the Python script
//
//        String[] byteValues = response.substring(1, response.length() - 1).split(",");
//        byte[] bytes = new byte[byteValues.length];
//
//        for (int i=0, len=bytes.length; i<len; i++) {
//            bytes[i] = Byte.parseByte(byteValues[i].trim());
//        }
//
//        String str = new String(bytes);

        String[] byteValues = stringConverted.substring(1, stringConverted.length() - 1).split(",");
        Log.d("JFD", "byteValues = " + byteValues[0]);
        byte[] bytes = new byte[byteValues.length];
        for (int i=0, len=bytes.length; i<len; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }
        Log.d("JFD", "bytes = " + bytes[0]);
        stringConverted = new String(bytes);


//        example #5
//        byte[] b1 = new byte[] {97, 98, 99};
//
//        String s1 = Arrays.toString(b1);
//        String s2 = new String(b1);
//
//        System.out.println(s1);        // -> "[97, 98, 99]"
//        System.out.println(s2);        // -> "abc";
//
//         s1 holds the string representation of the array b1, while
//         s2 holds the string representation of the bytes contained in b1.
//
//         Your server returns a string similar to s1, therefore to get
//         the array representation back, you need the opposite constructor method.
//
//         If s2.getBytes() is the opposite of new String(b1), you need to
//         find the opposite of Arrays.toString(b1), thus the code I pasted here
//         for example:
//
//        :
//        String response = "[-47, 1, 16, 84, 2, 101, 110, 83, 111, 109, 101, 32, 78, 70, 67, 32, 68, 97, 116, 97]";      // response from the Python script
//
//        String[] byteValues = response.substring(1, response.length() - 1).split(",");
//        byte[] bytes = new byte[byteValues.length];
//
//        for (int i=0, len=bytes.length; i<len; i++) {
//            bytes[i] = Byte.parseByte(byteValues[i].trim());
//        }
//
//        String str = new String(bytes);
//
        ////

        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
        editTextOutput.setText(stringConverted);

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




    public void onClickToClearInput(View view) {

        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextInput.setText("");
    }


    public void onClickToInput(View view) {

        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
        String stringOutput = editTextOutput.getText().toString();


        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextInput.setText(stringOutput);

    }

}
