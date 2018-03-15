package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, numberField, businessField, addressField, provinceField;;
    Contact receivedPersonInfo;
    private MyApplicationData appState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberField = (EditText) findViewById(R.id.number);
        businessField = (EditText) findViewById(R.id.business);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText)findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            numberField.setText(receivedPersonInfo.number);
            businessField.setText(receivedPersonInfo.business);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     *
     * @param v  view for the update contact button
     */
    public void updateContact(View v){
        //TODO: Update contact funcionality
        receivedPersonInfo.name = String.valueOf(nameField.getText());
        receivedPersonInfo.email = String.valueOf(emailField.getText());
        receivedPersonInfo.number = String.valueOf(numberField.getText());
        receivedPersonInfo.address = String.valueOf(addressField.getText());
        receivedPersonInfo.province = String.valueOf(provinceField.getText());
        receivedPersonInfo.business = String.valueOf(businessField.getText());
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(receivedPersonInfo);
        finish();
    }

    /**
     *
     * @param v for the erase contact button
     */
    public void eraseContact(View v) {
        //TODO: Erase contact functionality
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
