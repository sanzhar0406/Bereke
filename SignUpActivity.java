package com.example.sanzharaubakir.bereke;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private Spinner countrySpinner;
    private Button nextButton;
    private EditText phoneNumberET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();



    }

    private void initViews() {
        countrySpinner = (Spinner) findViewById(R.id.input_country);
        initCountrySpinner();

        phoneNumberET = (EditText) findViewById(R.id.input_phone);
        nextButton = (Button) findViewById(R.id.btn_signup);
        nextButton.setOnClickListener(this);


    }

    private void initCountrySpinner() {
        ArrayList<CountryItemData> list = getCountryItemList();
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinner_item,R.id.name,list);
        countrySpinner.setAdapter(adapter);
    }


    private void initCountrySpinnerOld() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        // set the view for the Drop down list
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        countrySpinner.setAdapter(dataAdapter);
        //countrySpinner.setSelection(37);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_signup:
                signUp();
        }
    }

    private void signUp() {
        String phoneNumber = phoneNumberET.getText().toString();
        int countryPosition = countrySpinner.getSelectedItemPosition();
        // TODO add rolling view of downloading and make server request
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public ArrayList<CountryItemData> getCountryItemList() {
        ArrayList<CountryItemData> countryItemList = new ArrayList<>();
        JSONArray countryItemJsonArray;
        try{
            InputStream inputStream = getAssets().open("countrycodes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonFile = new String(buffer,"UTF-8");
            Log.d(TAG, jsonFile);
            countryItemJsonArray = new JSONArray(jsonFile);
            JSONObject countryJson;
            Integer flagId;
            Resources resources = getResources();
            for (int i = 0; i < countryItemJsonArray.length(); ++i){
                countryJson = countryItemJsonArray.getJSONObject(i);
                flagId = resources.getIdentifier(getPackageName() + "drawable/countries/" + countryJson.getString("code") + ".png", null, null);
                countryItemList.add(new CountryItemData(countryJson.getString("name"), countryJson.getString("dial_code"), flagId));
                Log.d(TAG, "name - " + countryJson.getString("name"));
                Log.d(TAG, "dial_code - " + countryJson.getString("dial_code"));
                Log.d(TAG, "img - " + flagId);
            }
            //countryItemList.add();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return countryItemList;
    }
}
