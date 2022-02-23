package com.example.mysqliteappjava;

public class StudentData {

    String name,stuclass;
    int roll;

    public StudentData(String name, String stuclass, int roll) {
        this.name = name;
        this.stuclass = stuclass;
        this.roll = roll;
    }

    public StudentData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}
