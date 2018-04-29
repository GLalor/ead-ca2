// X00122026
// Graham Lalor

// NOTE: asked Karen about selecting the currency to convert to whether should be "USD" or
// "EUR/USD" she was unsure and said the "EUR/USD" was okay.

package com.example.g_laptop.ca2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    private TextView resultDisplay;
    private EditText amount;
    private ArrayList<CurrencyPair> data;
    private CurrencyPair conversionCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultDisplay = findViewById(R.id.textViewResult);
        amount = findViewById(R.id.editTextAmount);
        resultDisplay.setVisibility(View.INVISIBLE);
        data = new ArrayList<>();
        data.add(new CurrencyPair("EUR/USD", 1.07, "$"));
        data.add(new CurrencyPair("EUR/GBP", 0.83, "£"));

        // Spinner set up
        Spinner conversionCodes = findViewById(R.id.spinnerCurrencies);
        List<String> currencyCodes = new ArrayList<>();
        for( int i = 0 ; i < data.size(); i++){
            currencyCodes.add(data.get(i).GetCode());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, currencyCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionCodes.setAdapter(adapter);
        conversionCodes.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String code = parent.getItemAtPosition(pos).toString();
        for(CurrencyPair i : data){
            if(code == i.GetCode()){
                conversionCode = i; // setting variable based on user selection on spinner
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void callConvert(View v) {
        resultDisplay.setText(""); // Reset result each time
        double result = 0.0;
        if (!amount.getText().toString().matches((""))) {
            double currencyAmount = (Double.parseDouble(amount.getText().toString().trim()));
            if(currencyAmount > 0){
                result = conversionCode.convert(currencyAmount);
                resultDisplay.setText(conversionCode.GetCurrencySymbol() + String.format("%.2f", result)); // Format result to 2 decimal places
                resultDisplay.setVisibility(View.VISIBLE); // Show result
            }else{
                toastInvalidInput(); // Call toast message
            }
        }
        else{
            toastInvalidInput(); // Call toast message
        }
    }


    public void toastInvalidInput(){ // Will use a few times so placing in method
        Context context = getApplicationContext();
        CharSequence text = "Invalid input!";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
