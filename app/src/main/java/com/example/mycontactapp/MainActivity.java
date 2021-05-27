package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHandler handler;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<CustomerInfo> customerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        customerList=new ArrayList<>();
        handler=new MyDBHandler(MainActivity.this);
        customerList=handler.readCustomers();
        //
        adapter=new RecyclerAdapter(this,customerList);
        //
        recyclerView=(RecyclerView)findViewById(R.id.idRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton addNewContact= findViewById(R.id.idAddButton);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,InsertInfoActivity.class);
                startActivity(i);
            }
        }
        );

    }



}