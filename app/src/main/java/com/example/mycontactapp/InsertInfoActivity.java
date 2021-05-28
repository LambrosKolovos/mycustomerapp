package com.example.mycontactapp;
/**
 * This class is used to insert info for a new contact/customer.It takes the info from
 * the user and stores it to the database.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertInfoActivity extends AppCompatActivity {


    MyDBHandler db=new MyDBHandler(this);

    private EditText nameEdt,lastnameEdt, phoneEdt, emailEdt,birthdayEdt;
    private Button buttonEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_info);

        //initialize variables

        nameEdt = findViewById(R.id.idEdtName);
        lastnameEdt=findViewById(R.id.idEdtLastName);
        phoneEdt = findViewById(R.id.idEdtNumber);
        emailEdt = findViewById(R.id.idEdtEmail);
        birthdayEdt= findViewById(R.id.idEdtBirthday);
        buttonEdt = findViewById(R.id.button);

        //add action listener to the button

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
                    Toast.makeText(InsertInfoActivity.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {

                    //creating an CustomerInfo object
                    //and then calling the  addContact() method from MyDBHandler class

                    CustomerInfo c=new CustomerInfo();
                    c.setName(name);
                    c.setLastName(lastname);
                    c.setEmail(email);
                    c.setNumber(phone);
                    c.setBirthday(birthday);

                    db.addContact(c);
                    finish();
                }
                //a pop up text message to reassure that the contact is saved
                Toast.makeText(InsertInfoActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}