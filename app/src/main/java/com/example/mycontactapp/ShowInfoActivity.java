package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *This class is responsible for displaying the customer info and
 * has a button for deleting the contact.
 */
public class ShowInfoActivity extends AppCompatActivity {


    MyDBHandler db=new MyDBHandler(this);

    private TextView nameid,lastnameid, phoneid, emailid,birthdayid;
    private Button buttonid;
    private int _id;

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

        //setting the data for display
        Bundle extras = getIntent().getExtras();
        _id = extras.getInt("_id");
        nameid.setText(extras.getString("first_name"));
        lastnameid.setText(extras.getString("last_name"));
        phoneid.setText(extras.getString("phone"));
        emailid.setText(extras.getString("email"));
        birthdayid.setText(extras.getString("birthday"));


        //delete button and action listener
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

                    // calling  method to delete contact.
                    CustomerInfo c=new CustomerInfo();
                    c.setID(_id);
                    c.setName(name);
                    c.setLastName(lastname);
                    c.setEmail(email);
                    c.setNumber(phone);
                    c.setBirthday(birthday);

                    boolean deleted = db.deleteCustomer(c.getID());
                    if (deleted){
                        finish();
                    }
                    //pop up message to confirm that contact was deleted
                    Toast.makeText(ShowInfoActivity.this, "Contact Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}