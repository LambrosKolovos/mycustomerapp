package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowInfoActivity extends AppCompatActivity {


    MyDBHandler db=new MyDBHandler(this);

    private TextView nameid,lastnameid, phoneid, emailid,birthdayid;
    private Button buttonid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        //initialize variables

        nameid = findViewById(R.id.idName);
        lastnameid=findViewById(R.id.idLastName);
        phoneid = findViewById(R.id.idPhone);
        emailid = findViewById(R.id.idEmail);
        birthdayid= findViewById(R.id.idBirthday);
        buttonid = findViewById(R.id.del_button);
        //delete button

        buttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are getting text from our edit text.
                String name = nameid.getText().toString();
                String lastname = lastnameid.getText().toString();
                String phone = phoneid.getText().toString();
                String email = emailid.getText().toString();
                String birthday = birthdayid.getText().toString();

                // on below line we are making a text validation.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone)) {
                    Toast.makeText(ShowInfoActivity.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {

                    // calling a method to delete contact.

                    CustomerInfo c=new CustomerInfo();
                    c.setName(name);
                    c.setLastName(lastname);
                    c.setEmail(email);
                    c.setNumber(phone);
                    c.setBirthday(birthday);


                    db.deleteCustomer(c.getName());
                }
            }
        });
    }
}