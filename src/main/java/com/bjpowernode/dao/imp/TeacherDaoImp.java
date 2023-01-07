package com.bjpowernode.dao.imp;

import com.bjpowernode.beans.Teacher;
import com.bjpowernode.dao.DB;
import com.bjpowernode.dao.TeacherDao;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dbc
 * @create 2023-01-04 19:47
 */
public class TeacherDaoImp implements TeacherDao {
    @Override
    public ArrayList<Teacher> getAllTeacher() {
        return DB.teachers;
    }


    @Override
    public boolean AddTeacher(Teacher teacher) {
        if (teacher != null){
            DB.teachers.add(teacher);
            return true;
        }
        return false;
    }

    @Override
    public Teacher getIdTeacher(int id) {
        Iterator<Teacher> iterator = DB.teachers.iterator();
        while (iterator.hasNext()) {
            Teacher teacher = iterator.next();
            if (teacher.getId() == id)
                return teacher;
        }
        return null;
    }

    @Override
    public boolean UpdateTeacher(Teacher teacher) {
        Iterator<Teacher> iterator = DB.teachers.iterator();
        while (iterator.hasNext()) {
            Teacher next = iterator.next();
            if (next.getId() == teacher.getId()) {
                int index = DB.teachers.indexOf(next);
                DB.teachers.set(index,teacher);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean DelTeacher(Teacher teacher) {
        return DB.teachers.remove(teacher);
    }
}
