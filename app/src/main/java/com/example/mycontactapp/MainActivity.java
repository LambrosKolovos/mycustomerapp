package com.example.mycontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyDBHandler handler;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<CustomerInfo> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        customerList=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.idRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //


        FloatingActionButton addNewContact= findViewById(R.id.idAddButton);
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,InsertInfoActivity.class);
                startActivity(i);
            }
        }
        );
        //
        adapter=new RecyclerAdapter(this,customerList);
        recyclerView.setAdapter(adapter);
    }



}