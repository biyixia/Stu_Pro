package com.bjpowernode.dao;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.beans.Stu;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-03 19:33
 */
public interface StuDao {
    //获取全部学生
    ArrayList<Stu> getAllStu();
    //添加学生
    boolean AddStu(Stu stu);
    //修改学生
    boolean UpdateStu(Stu stu);
    //删除学生
    boolean DelStu(Stu stu);

    void setStu(ArrayList<Stu> stus);
}
