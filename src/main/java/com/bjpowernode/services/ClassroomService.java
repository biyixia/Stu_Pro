package com.bjpowernode.services;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.exception.MyException;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-03 19:39
 */
public interface ClassroomService {
    //获取全部班级
    ArrayList<Classroom> getAllClassroom();
    //根据id查找班级
    Classroom getIdClassroom(int cid);
    //添加班级
    boolean AddClassroom(Classroom classroom) throws MyException;
    //修改班级
    boolean UpdateClassroom(Classroom classroom) throws MyException;
    boolean DelClassroom(Classroom classroom);
    Integer getIntegers();
}
