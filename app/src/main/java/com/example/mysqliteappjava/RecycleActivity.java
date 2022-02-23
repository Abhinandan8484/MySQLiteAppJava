package com.example.mysqliteappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<StudentData> studentDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView=findViewById(R.id.recyclerView);
        dbHelper=new DBHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentDataList =dbHelper.getData();

        recyclerView.setAdapter(new MyAdper(studentDataList));
    }
}