package com.bjpowernode.services;

import com.bjpowernode.beans.Teacher;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-04 20:04
 */
public interface TeacherService {
    ArrayList<Teacher> getAllTeacher();

    boolean AddTeacher(Teacher teacher);

    boolean UpdateTeacher(Teacher teacher);

    boolean DelTeacher(Teacher teacher);

    Teacher getIdTeacher(int id);
}
