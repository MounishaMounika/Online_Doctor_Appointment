package com.example.onlinedoctorappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context ct;
    ArrayList<Pojo>list;
    DatabaseReference reference;



    public MyAdapter(ViewData viewData, ArrayList<Pojo> list) {
        ct=viewData;
        this.list=list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(ct).inflate(R.layout.row,
                viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(list.get(i).getName());
        viewHolder.tv1.setText(list.get(i).getMobile());
        viewHolder.tv2.setText(list.get(i).getState());
        viewHolder.tv3.setText(list.get(i).getDistrict());
        viewHolder.tv4.setText(list.get(i).getCity());
        viewHolder.tv5.setText(list.get(i).getPincode());
        viewHolder.tv6.setText(list.get(i).getStreet());
        viewHolder.tv7.setText(list.get(i).getMail());
        reference= FirebaseDatabase.getInstance().getReference("Database");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv7,p1,p2,p3,p4,p5,p6,p7,p8;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
            tv6 = itemView.findViewById(R.id.tv6);
            tv7=itemView.findViewById(R.id.tv7);
            p1=itemView.findViewById(R.id.p1);p2=itemView.findViewById(R.id.p2);p3=itemView.findViewById(R.id.p3);p4=itemView.findViewById(R.id.p4);
            p5=itemView.findViewById(R.id.p5);p6=itemView.findViewById(R.id.p6);p7=itemView.findViewById(R.id.p7);p8=itemView.findViewById(R.id.p8);

        }
    }
}
