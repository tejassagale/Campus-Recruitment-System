package com.android.letsgetplaced;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Student>students;


    public MyAdapter(Context c,ArrayList<Student> s){
        context=c;
        students=s;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int position) {
                myViewHolder.name.setText(students.get(position).getname());
                myViewHolder.contact.setText(students.get(position).getContact());
                myViewHolder.age.setText(students.get(position).getAge());
        myViewHolder.gender.setText(students.get(position).getGender());
        myViewHolder.cgpa.setText(String.valueOf(students.get(position).getCgpa()));
       // myViewHolder.branch.setText(students.get(position).getBranch());
        myViewHolder.ssc.setText(students.get(position).getSSC());
        myViewHolder.hsc.setText(students.get(position).getHSC());


    }


    @Override
    public int getItemCount() {
        return students.size();
    }



class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name,contact,age,gender,cgpa,ssc,hsc,diploma;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.name);
        contact=(TextView)itemView.findViewById(R.id.contact);
        age=(TextView)itemView.findViewById(R.id.age);
        gender=(TextView)itemView.findViewById(R.id.gender);
        cgpa=(TextView)itemView.findViewById(R.id.cgpi);
        //branch=(TextView)itemView.findViewById(R.id.branch);
        ssc=(TextView)itemView.findViewById(R.id.ssc);
        hsc=(TextView)itemView.findViewById(R.id.hsc);



    }
}
}
