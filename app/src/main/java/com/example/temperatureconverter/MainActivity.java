package com.example.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    /**
     * Decimal Format Class
     * Link 1: https://developer.android.com/reference/java/text/DecimalFormat
     * Link 2: https://stackoverflow.com/questions/8065114/how-to-print-a-double-with-two-decimals-in-android
     */
    DecimalFormat precision = new DecimalFormat("0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Event Input Handler - Android Studio
         * https://developer.android.com/training/keyboard-input/style#java
         */
        final EditText fTemperature = (EditText) findViewById(R.id.fDegree);
        final TextView cTemperature = findViewById(R.id.cDegree);
        fTemperature.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    convertFtoC();
                    handled = true;
                }
                return handled;
            }

            private void convertFtoC() {
                // F to C Calculations
                float get_F_Temperature = Float.valueOf(fTemperature.getText().toString());
                float cDegree = (get_F_Temperature - 32) * 5 / 9;

                // Display the calculation temperature
                cTemperature.setText(precision.format(cDegree));
            }
        });
    }
}