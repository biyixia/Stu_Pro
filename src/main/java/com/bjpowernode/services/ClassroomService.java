package com.bjpowernode.services;

import com.bjpowernode.beans.Classroom;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-03 19:39
 */
public interface ClassroomService {
    //获取全部班级
    ArrayList<Classroom> getAllClassroom();
    //添加班级
    boolean AddClassroom(Classroom classroom);
    //修改班级
    boolean UpdateClassroom(Classroom classroom);
    boolean DelClassroom(Classroom classroom);
    Integer getIntegers();
}
