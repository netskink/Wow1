package net.skink.wow1;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity  implements AdapterView.OnItemSelectedListener {

    public final static String EXTRA_MESSAGE = "net.skink.wow1.MESSAGE";
    EditText editTextInput;
    EditText editTextOutput;
    RadioButton radioButtonDefault;

    RadioButton radioButtonInHex;
    RadioButton radioButtonInText;
    RadioButton radioButtonInBase64;

    Spinner spinner;


    // action items we will enable visibility
    MenuItem menuItemToAscii;
    MenuItem menuItemToHex;
    MenuItem menuItemToBase64;
    MenuItem menuItemToDecimal;
    MenuItem menuItemToText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextOutput = (EditText) findViewById(R.id.editTextOutput);

        radioButtonDefault = (RadioButton) findViewById(R.id.radioButtonInHex);
        radioButtonDefault.setChecked(true);
        editTextInput.setHint(R.string.hint_hex);


        radioButtonInHex = (RadioButton) findViewById(R.id.radioButtonInHex);
        radioButtonInText = (RadioButton) findViewById(R.id.radioButtonInText);
        radioButtonInBase64 = (RadioButton) findViewById(R.id.radioButtonInBase64);


        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.output_action_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Set this activity to listen for the spinner events.
        spinner.setOnItemSelectedListener(this);

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

//        menuItemToAscii = menu.findItem(R.id.action_toAscii);;
//        menuItemToHex = menu.findItem(R.id.action_toHex);;
//        menuItemToBase64 = menu.findItem(R.id.action_toBase64);
//        menuItemToDecimal = menu.findItem(R.id.action_toDecimal);
//        menuItemToText = menu.findItem(R.id.action_toText);
//
//        menuItemToHex.setVisible(false);

        return true;
    }


    public void onRadioButtonInClicked(View view) {

        spinner.setSelection(((ArrayAdapter) spinner.getAdapter()).getPosition(""));


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {

            case R.id.radioButtonInHex:
                if (checked) {
                    editTextInput.setHint(R.string.hint_hex);

//                    menuItemToAscii.setVisible(false);
//                    menuItemToHex.setVisible(false);
//                    menuItemToBase64.setVisible(true);
//                    menuItemToDecimal.setVisible(true);
//                    menuItemToText.setVisible(true);
                }
                break;
            case R.id.radioButtonInDecimal:
                if (checked) {
                    editTextInput.setHint(R.string.hint_decimal);

//                    menuItemToAscii.setVisible(false);
//                    menuItemToHex.setVisible(true);
//                    menuItemToBase64.setVisible(false);
//                    menuItemToDecimal.setVisible(false);
//                    menuItemToText.setVisible(true);
                }
                break;
            case R.id.radioButtonInBase64:
                if (checked) {
                    editTextInput.setHint(R.string.hint_base64);
                }
                break;
            case R.id.radioButtonInText:
                if (checked) {
                    editTextInput.setHint(R.string.hint_text);

//                    menuItemToAscii.setVisible(true);
//                    menuItemToHex.setVisible(true);
//                    menuItemToBase64.setVisible(true);
//                    menuItemToDecimal.setVisible(false);
//                    menuItemToText.setVisible(false);
                }
                break;
        }
    }

    public void onRadioButtonOutClicked(View view) {

        spinner.setSelection(((ArrayAdapter) spinner.getAdapter()).getPosition(""));

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {

            case R.id.radioButtonOutHex:
                if (checked) {
                    if (radioButtonInText.isChecked()) {
                        onTextToAsciiHex();
                    }

//                    menuItemToAscii.setVisible(false);
//                    menuItemToHex.setVisible(false);
//                    menuItemToBase64.setVisible(true);
//                    menuItemToDecimal.setVisible(true);
//                    menuItemToText.setVisible(true);
                }
                break;
            case R.id.radioButtonOutDecimal:
                if (checked) {

//                    menuItemToAscii.setVisible(false);
//                    menuItemToHex.setVisible(true);
//                    menuItemToBase64.setVisible(false);
//                    menuItemToDecimal.setVisible(false);
//                    menuItemToText.setVisible(true);
                }
                break;

            case R.id.radioButtonOutBase64:
                if (checked) {
                    if (radioButtonInText.isChecked()) {
                        onTextToBase64();

                    } else if (radioButtonInHex.isChecked()) {

                        String stringInput = editTextInput.getText().toString();
                        String stringConverted = JfdUtils.hexToText(stringInput);

                        stringConverted = JfdUtils.textToBase64(stringConverted);
                        editTextOutput.setText(stringConverted);




                    }

                }
                break;

            case R.id.radioButtonOutText:
                if (checked) {
                    if (radioButtonInHex.isChecked()) {
                        onHexToAscii();
                    } else if (radioButtonInText.isChecked()) {
                        editTextOutput.setText(editTextInput.getText());
                    } else if (radioButtonInBase64.isChecked()) {
                        onBase64ToText();
                    }


//                    menuItemToAscii.setVisible(true);
//                    menuItemToHex.setVisible(true);
//                    menuItemToBase64.setVisible(true);
//                    menuItemToDecimal.setVisible(false);
//                    menuItemToText.setVisible(false);
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // handle presses on the action bar items
        switch (item.getItemId()) {

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
        ClipData myClip = ClipData.newPlainText("simple text", text);
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


    ///////////////////////////////////////////////
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////
    ///////////////////////////////////////////////


    //
    // Button Clicks
    //

    public void onClickToClearInput(View view) {

//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextInput.setText("");
    }

    public void onClickToInput(View view) {

//        EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
        String stringOutput = editTextOutput.getText().toString();
//
//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextInput.setText(stringOutput);
    }




    public void onTextToAsciiHex() {

//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();

        String stringConverted = JfdUtils.textToAsciiHex(stringInput);


        //      EditText editTextOutput = (EditText) findViewById(R.id.editTextOutput);
        editTextOutput.setText(stringConverted);

    }

    public void onHexToAscii() {


//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();

        String stringConverted = JfdUtils.hexToAsciiValues(stringInput);


        editTextOutput.setText(stringConverted);

    }

    public void onTextToBase64() {

//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();

        String stringConverted = JfdUtils.textToBase64(stringInput);

        editTextOutput.setText(stringConverted);

    }


    public void onBase64ToText() {

//        EditText editTextInput = (EditText) findViewById(R.id.editTextInput);
        String stringInput = editTextInput.getText().toString();

        String stringConverted = JfdUtils.base64ToText(stringInput);

        editTextOutput.setText(stringConverted);

    }







    /////////////////
    // Adapter routines

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p/>
     * Impelmenters can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("JFD", parent.getItemAtPosition(position).toString());

        switch (position) {

            case 0:
                break;
            case 1:
                onClickToInput(parent);
                break;
            default:
                Log.d("JFD", "Unimplemented " + parent.getItemAtPosition(position).toString());

        }

    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

