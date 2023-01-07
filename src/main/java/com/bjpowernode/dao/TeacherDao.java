package com.bjpowernode.dao;

import com.bjpowernode.beans.Teacher;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-04 19:45
 */
public interface TeacherDao {
    ArrayList<Teacher> getAllTeacher();

    boolean AddTeacher(Teacher teacher);

    boolean UpdateTeacher(Teacher teacher);

    boolean DelTeacher(Teacher teacher);

    Teacher getIdTeacher(int id);
}
