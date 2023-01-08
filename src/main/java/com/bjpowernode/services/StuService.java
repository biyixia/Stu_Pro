package com.bjpowernode.services;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.beans.Stu;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-03 19:39
 */
public interface StuService {
    //获取全部学生
    ArrayList<Stu> getAllStu();
    //添加学生
    boolean AddStu(Stu stu);
    //修改学生
    boolean UpdateStu(Stu stu);
    //删除学生
    boolean DelStu(Stu stu);
    //根据班级编号删除学生
    void DelClassStu(Classroom classroom);
    //获取id链表
    Integer getIntegers();
    //查找缓存集合中是否存在某班级下的学生
    boolean isClassStu(Classroom classroom);
    //获取缓存集合
    ArrayList<String> getCName();
}
