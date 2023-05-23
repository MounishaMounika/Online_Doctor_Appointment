package com.example.onlinedoctorappointment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {
    ArrayList<Pojo1>list0;
    Context cxt;
    DatabaseReference reference;




    public DocAdapter(profile profile1, ArrayList<Pojo1> list1) {
        cxt=profile1;
        this.list0=list1;
    }

    public DocAdapter(DocProfile docProfile, ArrayList<Pojo1> list1) {
        cxt=docProfile;
        this.list0=list1;
    }


    @NonNull
    @Override
    public DocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DocAdapter.ViewHolder(LayoutInflater.from(cxt).inflate(R.layout.docpersonal,
                viewGroup,false));

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.dn.setText(list0.get(i).getDocname());
        viewHolder.dhn.setText(list0.get(i).getHosname());
        viewHolder.dt.setText(list0.get(i).getDoctype());
        viewHolder.dcn.setText(list0.get(i).getHcn());
        viewHolder.df.setText(list0.get(i).getFee());
        viewHolder.dat.setText(list0.get(i).getTime());
        viewHolder.dha.setText(list0.get(i).getAddress());
        viewHolder.ds.setText(list0.get(i).getDocspecial());
        reference= FirebaseDatabase.getInstance().getReference("Doctors");
    }

    @Override
    public int getItemCount() {
        return list0.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dn, dhn, dt, dcn, df, dat, dha,t1,t2,t3,t4,t5,t6,t7,ds,t8;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dn = itemView.findViewById(R.id.doctv);
            dhn = itemView.findViewById(R.id.doctv1);
            dt = itemView.findViewById(R.id.doctv2);
            dcn = itemView.findViewById(R.id.doctv3);
            df = itemView.findViewById(R.id.doctv4);
            dat = itemView.findViewById(R.id.doctv5);
            dha = itemView.findViewById(R.id.doctv6);
            ds=itemView.findViewById(R.id.doctv7);
            t1 = itemView.findViewById(R.id.t1);t2 = itemView.findViewById(R.id.t2);t3 = itemView.findViewById(R.id.t3);t4 = itemView.findViewById(R.id.t4);
            t5 = itemView.findViewById(R.id.t5);t6 = itemView.findViewById(R.id.t6);t7 = itemView.findViewById(R.id.t7);t8=itemView.findViewById(R.id.t8);


        }
    }
}
