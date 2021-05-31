package jicheng;

import java.sql.SQLOutput;

public class bean {
    private String name;
    private int age;
    private char gender;
    private double tizhong;

    public bean() {
    }

    public bean(String name, int age, char gender, double tizhong) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tizhong = tizhong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getTizhong() {
        return tizhong;
    }

    public void setTizhong(double tizhong) {
        this.tizhong = tizhong;
    }

}
