package com.example.lw4;

public class Person {
    public int id;
    public String name;
    public String last_name;
    public int age;
    public String education;
    public String passport;

    public Person(){}
    public Person(int id, String name, String lastName, int age, String edu, String passport) {
        this.id = id;
        this.name = name;
        this.last_name = lastName;
        this.age = age;
        this.education = edu;
        this.passport = passport;
    }
}
