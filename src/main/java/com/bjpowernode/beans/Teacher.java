package com.bjpowernode.beans;

import sun.security.util.Password;

/**
 * @author dbc
 * @create 2023-01-04 19:35
 */
public class Teacher {
    int id;
    int password;
    String name;
    String sex;
    int age;
    int salary;

    public Teacher() {
    }

    public Teacher(int id, int password, String name, String sex, int age, int salary) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", password=" + password +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
