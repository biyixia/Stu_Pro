package com.bjpowernode.beans;


/**
 * @author dbc
 * @create 2023-01-03 19:28
 */
public class Classroom {
    int id;
    String name;

    public Classroom() {
   }

    public Classroom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "班级编号：" + id +
                "   班级名称：" + name;
    }
}
