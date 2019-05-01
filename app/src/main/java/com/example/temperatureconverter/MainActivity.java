package com.example.temperatureconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements OnEditorActionListener {

    // define variables for the widgets
    private EditText fTemperature;
    private TextView cTemperature;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    // define instance variables that should be saved
    private String fDegree_String = "";
    private float c_Degree = 0;
    private float f_Degree = 0;

    /**
     * Decimal Format Class
     * Link 1: https://developer.android.com/reference/java/text/DecimalFormat
     * Link 2: https://stackoverflow.com/questions/8065114/how-to-print-a-double-with-two-decimals-in-android
     */
    private DecimalFormat precision = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Event Input Handler - Android Studio
         * https://developer.android.com/training/keyboard-input/style#java
         */
        // get reference to widget
        fTemperature = (EditText) findViewById(R.id.fDegree);
        cTemperature = (TextView) findViewById(R.id.cDegree);

        // set the listeners
        fTemperature.setOnEditorActionListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    /**
     * This method will listen and respond to event after input
     *
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            convertFtoC();
            handled = true;
        }
        return handled;
    }

    @Override
    public void onPause() {
        // save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("fDegree_String", fDegree_String);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        fDegree_String = savedValues.getString("fDegree_String", "");

        // set the F temperature on its widget
        fTemperature.setText(fDegree_String);

        // calculate F to C temperature
        convertFtoC();
    }

    public void convertFtoC() {
        // F to C Calculations
        fDegree_String = fTemperature.getText().toString();

        if (fDegree_String.equals("")) {
            f_Degree = 0;
        } else {
            f_Degree = Float.parseFloat(fDegree_String);
        }
        c_Degree = (f_Degree - 32) * 5 / 9;

        // Display the calculation temperature
        cTemperature.setText(precision.format(c_Degree));
    }
}