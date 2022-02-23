package com.example.mysqliteappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText edStuName,edStuRoll,edStuClass;
    Button btnUpdate;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edStuName=findViewById(R.id.edUpStuName);
        edStuRoll=findViewById(R.id.edUpStuRoll);
        edStuClass=findViewById(R.id.edUpStuClass);
        btnUpdate=findViewById(R.id.btnUpdate);
        dbHelper=new DBHelper(this);

        String name=getIntent().getExtras().getString("stuname");
        String stuclass=getIntent().getExtras().getString("stuclass");
        int roll=getIntent().getExtras().getInt("sturoll");
        String strRoll= String.valueOf(roll);

        edStuName.setText(name);
        edStuClass.setText(stuclass);
        edStuRoll.setText(strRoll);

        btnUpdate.setOnClickListener(view -> {

            String upName=edStuName.getText().toString();
            String upClass=edStuClass.getText().toString();
            int upRoll=Integer.parseInt(edStuRoll.getText().toString());

            boolean re=dbHelper.updateStudant(upName,upRoll,upClass,name);
            if (re){
                Toast.makeText(this, " Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,RecycleActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Filed to Update", Toast.LENGTH_SHORT).show();
            }

        });

    }
}