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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context ct1;
    ArrayList<Pojo1> list2;
    DatabaseReference reference;

    public Adapter(DocViewData docViewData, ArrayList<Pojo1> list2) {
        ct1=docViewData;
        this.list2=list2;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Adapter.ViewHolder(LayoutInflater.from(ct1).inflate(R.layout.docrow,
                viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        viewHolder.dn1.setText(list2.get(i).getDocname());
        viewHolder.dhn1.setText(list2.get(i).getHosname());
        viewHolder.dt1.setText(list2.get(i).getDoctype());
        viewHolder.dcn1.setText(list2.get(i).getHcn());
        viewHolder.df1.setText(list2.get(i).getFee());
        viewHolder.dat1.setText(list2.get(i).getTime());
        viewHolder.dha1.setText(list2.get(i).getAddress());
        viewHolder.ds.setText(list2.get(i).getDocspecial());
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dn1,dhn1,dt1,dcn1,df1,dat1,dha1,ds;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dn1=itemView.findViewById(R.id.doctv);
            dhn1=itemView.findViewById(R.id.doctv1);
            dt1=itemView.findViewById(R.id.doctv2);
            dcn1=itemView.findViewById(R.id.doctv3);
            df1=itemView.findViewById(R.id.doctv4);
            dat1=itemView.findViewById(R.id.doctv5);
            dha1=itemView.findViewById(R.id.doctv6);
            ds=itemView.findViewById(R.id.doctv7);
        }
    }
}
