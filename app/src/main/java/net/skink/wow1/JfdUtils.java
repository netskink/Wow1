package net.skink.wow1;

import android.util.Base64;
import android.util.Log;

/**
 * Created by john on 12/9/2014.
 */
public class JfdUtils {


    public static String hexToText(String stringInput) {

        String stringConverted;
        String stringHex = stringInput;


        StringBuilder sb = new StringBuilder();

        //StringBuffer hex = new StringBuffer();
        //for(int i = 0; i < chars.length; i++){
        //    hex.append(Integer.toHexString((int)chars[i]));


        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < stringHex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = stringHex.substring(i, (i + 2));
            //convert hex to decimal
            try {
                int decimal = Integer.parseInt(output, 16);
                //convert the decimal to character
                sb.append((char)decimal);

                //sb.append(hex);
            }catch( Exception e){
                Log.d("JFD", "Not a hex sequence");
                stringConverted = "error, input sequence must be hex string";
                return stringConverted;
            }
        }
        Log.d("JFD", "sb.toString : " + sb.toString());


        stringConverted = sb.toString();


        return stringConverted;



    }

    public static String base64ToText(String stringInput) {


        String stringFromBase = new String(Base64.decode(stringInput, Base64.DEFAULT));

        return stringFromBase;
    }

    public static String textToBase64(String stringInput) {



        //          String converted = Base64.encodeToString(toConvert.toString().getBytes(), Base64.DEFAULT));
        String stringConverted = Base64.encodeToString(stringInput.toString().getBytes(), Base64.DEFAULT);


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
        String s = stringInput;
        try {
            byte[] b = s.getBytes("UTF-8");
            stringConverted = Base64.encodeToString(b,Base64.DEFAULT );
            Log.d("JFD", "s = " + s);
        } catch (Exception e ) {
            Log.d("JFD", "UnsupportedEncodingException");
        }

        return stringConverted;
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


//        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
//        base64.setTextSize(20);

    }






    public static String hexToAsciiValues(String stringInputHex) {


        String stringHex = stringInputHex;
        String stringConverted;


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
                stringConverted = "error, input sequence must be hex string";
                return stringConverted;
            }
        }
        Log.d("JFD", "Decimal : " + temp.toString());
        Log.d("JFD", "sb.toString : " + sb.toString());


        stringConverted = sb.toString();

        return (stringConverted);

    }









    public static String textToAsciiHex(String stringInputText) {

        byte[] b = stringInputText.getBytes();
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

        return sb.toString();


    }
}