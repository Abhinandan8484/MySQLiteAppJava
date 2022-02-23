package com.example.mysqliteappjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdper extends RecyclerView.Adapter<MyAdper.MyViewHolder> {

    List<StudentData> studentDataList;
    String str;
    DBHelper dbHelper;


    public MyAdper(List<StudentData> studentDataList) {
        this.studentDataList = studentDataList;

    }

    @NonNull
    @Override
    public MyAdper.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdper.MyViewHolder holder, int position) {
        holder.txtStuName.setText(studentDataList.get(position).getName());
        holder.txtClass.setText(studentDataList.get(position).getStuclass());
        String roll1= String.valueOf(studentDataList.get(position).getRoll());
        holder.txtRoll.setText(roll1);
        str=studentDataList.get(position).getName();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos =holder.getAdapterPosition();
                String name,stuclass;

                name=studentDataList.get(pos).getName();
                int roll=studentDataList.get(pos).getRoll();
                stuclass=studentDataList.get(pos).getStuclass();

                Intent intent=new Intent(view.getContext(),UpdateActivity.class);
                intent.putExtra("stuname",name);
                intent.putExtra("sturoll",roll);
                intent.putExtra("stuclass",stuclass);
                view.getContext().startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                int pos=holder.getAdapterPosition();
                String name=studentDataList.get(pos).getName();
                dbHelper=new DBHelper(view.getContext());
                boolean re= dbHelper.deleteStudent(name);
                if (re){
                    Toast.makeText(view.getContext(), "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(view.getContext(),RecycleActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    Toast.makeText(view.getContext(), "Field to Delete the Record", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtStuName,txtClass,txtRoll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStuName=itemView.findViewById(R.id.txtStuName);
            txtClass=itemView.findViewById(R.id.txtClass);
            txtRoll=itemView.findViewById(R.id.txtRoll);

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//
//                    Toast.makeText(itemView.getContext(), "You Click on: "+str, Toast.LENGTH_SHORT).show();
//
//                    return false;
//                }
//            });

        }
    }
}
