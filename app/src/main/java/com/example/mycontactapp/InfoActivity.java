package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {


    MyDBHandler db=new MyDBHandler(this);

    private EditText nameEdt,lastnameEdt, phoneEdt, emailEdt,birthdayEdt;
    private Button buttonEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //initialize variables

        nameEdt = findViewById(R.id.idEdtName);
        lastnameEdt=findViewById(R.id.idEdtLastName);
        phoneEdt = findViewById(R.id.idEdtNumber);
        emailEdt = findViewById(R.id.idEdtEmail);
        birthdayEdt= findViewById(R.id.idEdtBirthday);
        buttonEdt = findViewById(R.id.button);

        //
        buttonEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are getting text from our edit text.
                String name = nameEdt.getText().toString();
                String lastname = lastnameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String birthday = birthdayEdt.getText().toString();

                // on below line we are making a text validation.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone)) {
                    Toast.makeText(InfoActivity.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {

                    // calling a method to add contact.
                    //addContact(name,lastname, email, phone,birthday);
                    CustomerInfo c=new CustomerInfo();
                    c.setName(name);
                    c.setLastName(lastname);
                    c.setEmail(email);
                    c.setNumber(phone);
                    c.setBirthday(birthday);


                    db.addContact(c);
                }
            }
        });
    }


}