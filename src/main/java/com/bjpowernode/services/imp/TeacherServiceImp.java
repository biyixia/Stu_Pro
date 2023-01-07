package com.bjpowernode.services.imp;

import com.bjpowernode.beans.Teacher;
import com.bjpowernode.dao.TeacherDao;
import com.bjpowernode.dao.imp.TeacherDaoImp;
import com.bjpowernode.services.TeacherService;

import java.util.ArrayList;

/**
 * @author dbc
 * @create 2023-01-04 20:05
 */
public class TeacherServiceImp implements TeacherService {

    TeacherDao teacherDao = new TeacherDaoImp();

    @Override
    public ArrayList<Teacher> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public Teacher getIdTeacher(int id) {
        return teacherDao.getIdTeacher(id);
    }

    @Override
    public boolean AddTeacher(Teacher teacher) {
        return teacherDao.AddTeacher(teacher);
    }

    @Override
    public boolean UpdateTeacher(Teacher teacher) {
        return teacherDao.UpdateTeacher(teacher);
    }

    @Override
    public boolean DelTeacher(Teacher teacher) {
        return teacherDao.DelTeacher(teacher);
    }
}
