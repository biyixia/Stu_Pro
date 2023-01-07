package com.bjpowernode.dao;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.beans.Stu;
import com.bjpowernode.beans.Teacher;
import sun.security.util.Password;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-03 19:30
 */
public class DB {
    public static ArrayList<Classroom> classrooms = new ArrayList<>();
    public static ArrayList<Stu> stus = new ArrayList<>();
    public static ArrayList<Teacher> teachers = new ArrayList<>();

    public static String id = "123";
    public static String pw = "123";

    public static Boolean selected = true;

    static {
        classrooms.add(new Classroom(101,"SZ2212"));
        classrooms.add(new Classroom(102,"SZ2213"));
        classrooms.add(new Classroom(103,"SZ2214"));

        stus.add(new Stu(101,"Tom1","男",18,100,classrooms.get(0)));
        stus.add(new Stu(102,"Tom2","男",18,100,classrooms.get(0)));
        stus.add(new Stu(103,"Tom3","男",18,100,classrooms.get(1)));
        stus.add(new Stu(104,"Tom4","女",18,100,classrooms.get(2)));
        stus.add(new Stu(105,"Tom5","女",18,100,classrooms.get(2)));

        teachers.add(new Teacher(123, 123, "Tom1", "男", 18, 10000));
        teachers.add(new Teacher(1234, 123, "Tom2", "男", 18, 10000));
        teachers.add(new Teacher(12345, 123, "Tom3", "男", 18, 10000));
        teachers.add(new Teacher(123456, 123, "Tom4", "男", 18, 10000));
        teachers.add(new Teacher(1234567, 123, "Tom5", "男", 18, 10000));
    }
}
