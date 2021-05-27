package com.example.mycontactapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.TextDrawableHelper;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
   private Context context;
   private List<CustomerInfo> customerList;

    public RecyclerAdapter(Context context,List<CustomerInfo> customerList) {
        this.context = context;
        this.customerList=customerList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.activity_main,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
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

            itemName=itemView.findViewById(R.id.idEdtName);
            itemLastName=itemView.findViewById(R.id.idEdtLastName);
        }

    }
}
