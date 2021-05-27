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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
   private Context context;
   private ArrayList<CustomerInfo> customerList;


    public RecyclerAdapter(Context context,ArrayList<CustomerInfo> customerList) {
        this.context = context;
        this.customerList=customerList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RecyclerAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        CustomerInfo customer=customerList.get(position);
        holder.itemName.setText(customer.getName());
        holder.itemLastName.setText(customer.getLastName());
        //

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(context,ShowInfoActivity.class);
               i.putExtra("name",customer.getName());
               i.putExtra("Last name",customer.getLastName());
               context.startActivity(i);
           }
       });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView itemLastName;

        public ViewHolder(View itemView){
            super(itemView);

            itemName=itemView.findViewById(R.id.idContactName);
            itemLastName=itemView.findViewById(R.id.idContactLastName);
        }

    }
}
