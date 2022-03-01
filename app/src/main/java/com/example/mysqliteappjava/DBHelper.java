package com.example.mysqliteappjava;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName="StudentDatabase";
    private static final int DBVersion=1;
    private static final String TableName="StudentInfo";
    private static final String ColID="ID";
    private static final String ColName="Student_Name";
    private static final String ColClass="Student_Class";
    private static final String ColRoll="Student_Roll_Number";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String q1="CREATE TABLE "+TableName+"( "+
                    ColID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    ColName+" TEXT, "+
                    ColClass+" TEXT, "+
                    ColRoll+" INTEGER"+" )";

        sqLiteDatabase.execSQL(q1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(sqLiteDatabase);

    }


    public boolean addStudent(String name, String stuclass, int roll) {

        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(ColName,name);
        contentValues.put(ColClass,stuclass);
        contentValues.put(ColRoll,roll);

        long result= database.insert(TableName,null,contentValues);

        database.close();
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    @SuppressLint("Range")
    public List<StudentData> getData(){

        SQLiteDatabase database=this.getReadableDatabase();
        List<StudentData> studentDataList=new ArrayList<>();
        String q1="SELECT * FROM "+TableName;

        Cursor result= database.rawQuery(q1,null);
        if (result.moveToFirst()){

            do {

                StudentData sdata =new StudentData();

                sdata.name=result.getString(result.getColumnIndex(ColName));
                sdata.stuclass=result.getString(result.getColumnIndex(ColClass));
                sdata.roll=result.getInt(result.getColumnIndex(ColRoll));

                studentDataList.add(sdata);
            }while (result.moveToNext());
        }
        database.close();
        return studentDataList;

    }


    public boolean updateStudant(String upName, int upRoll, String upClass,String oldName) {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put(ColClass,upClass);
        contentValues.put(ColName,upName);
        contentValues.put(ColRoll,upRoll);

        Log.i("sqlupdate","Roll: "+upRoll);
        Log.i("sqlupdate","Class: "+upClass);


        int re= sqLiteDatabase.update(TableName,contentValues,ColName+"=?",new String[]{oldName});
        Log.i("sqlupdate","re: "+re);

        sqLiteDatabase.close();
        if (re==-1){
            return false;
        }else{
            return true;
        }


    }

    public boolean deleteStudent(String name) {
        SQLiteDatabase database=this.getWritableDatabase();
        long re=database.delete(TableName,ColName+"=?",new String[]{name});

        if (re==-1){
            return false;
        }else{
            return true;
        }


    }
}
