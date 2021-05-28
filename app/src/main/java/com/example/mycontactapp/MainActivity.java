package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHandler handler;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<CustomerInfo> customerList;
    TextView noCustomers;

    private boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shouldExecuteOnResume = false;
        recyclerView = findViewById(R.id.idRecyclerView);
        noCustomers = findViewById(R.id.idNoContacts);
        initRecycler();

        FloatingActionButton addNewContact = findViewById(R.id.idAddButton);
        addNewContact.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent i = new Intent(MainActivity.this, InsertInfoActivity.class);
                                                 startActivity(i);
                                             }
                                         }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            initRecycler();

        } else
            shouldExecuteOnResume = true;

    }

    private void initRecycler() {
        customerList = new ArrayList<>();
        handler = new MyDBHandler(MainActivity.this);
        customerList = handler.readCustomers();

        System.out.println("Costumer list size: " + customerList.size());

        adapter = new RecyclerAdapter(this, customerList);
        //
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (customerList.size() > 0) {
            noCustomers.setVisibility(View.GONE);
        } else {

            noCustomers.setVisibility(View.VISIBLE);
        }
    }
}