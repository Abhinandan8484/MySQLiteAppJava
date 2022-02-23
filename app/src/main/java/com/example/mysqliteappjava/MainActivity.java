package com.example.mysqliteappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edname,edclass,edroll;
    Button btnsave,btnshow;
    TextView txtdisplayTV;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edname=findViewById(R.id.Stuname);
        edclass=findViewById(R.id.Stuclass);
        edroll=findViewById(R.id.Sturoll);
        btnsave=findViewById(R.id.btnsave);
        btnshow=findViewById(R.id.btnshow);
        txtdisplayTV=findViewById(R.id.dsplayTV);
        dbHelper=new DBHelper(MainActivity.this);


        btnsave.setOnClickListener(view -> {

            String name=edname.getText().toString();
            String stuclass=edclass.getText().toString();
            int roll=Integer.parseInt(edroll.getText().toString());
            boolean re=dbHelper.addStudent(name,stuclass,roll);
            if (re){
                Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Record not Added", Toast.LENGTH_SHORT).show();
            }

            show();
        });

        btnshow.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,RecycleActivity.class);
            startActivity(intent);
        });


    }

    private void show() {
        List<StudentData> studentDataList= dbHelper.getData();
        txtdisplayTV.setText("");
        for (int i=0;i<studentDataList.size();i++){

            txtdisplayTV.append("\nStudent Name: "+studentDataList.get(i).getName()+" \nStudent Class: "+studentDataList.get(i).getStuclass()+" \nStudent Roll: "+studentDataList.get(i).getRoll()+"\n");

        }
    }
}