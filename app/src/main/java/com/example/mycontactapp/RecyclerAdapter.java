package com.example.mycontactapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is the adapter needed for the Recycler View.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
   private Context context;
   private ArrayList<CustomerInfo> customerList;

    //constructor
    public RecyclerAdapter(Context context,ArrayList<CustomerInfo> customerList) {
        this.context = context;
        this.customerList=customerList;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing layout file for displaying card item
        return new RecyclerAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // getting data from array list
        CustomerInfo customer=customerList.get(position);

        //setting data to  text view
        holder.itemName.setText(customer.getName());
        holder.itemLastName.setText(customer.getLastName());
        holder.itemPhone.setText(customer.getNumber());

        //add action listener to item of recycler view
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //start a new activity and pass data to it

               Intent i=new Intent(context,ShowInfoActivity.class);
               i.putExtra("_id", customer.getID());
               i.putExtra("first_name", customer.getName());
               i.putExtra("last_name", customer.getLastName());
               i.putExtra("phone", customer.getNumber());
               i.putExtra("email", customer.getEmail());
               i.putExtra("birthday", customer.getBirthday());
               context.startActivity(i);
           }
       });
    }

    //returns the total number of list items
    @Override
    public int getItemCount() {
        return customerList.size();
    }


    //Class that holds the items to be displayed
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView itemLastName;
        private TextView itemPhone;

        public ViewHolder(View itemView){
            super(itemView);

            itemName=itemView.findViewById(R.id.idContactName);
            itemLastName=itemView.findViewById(R.id.idContactLastName);
            itemPhone=itemView.findViewById(R.id.idPhone);
        }

    }
}
