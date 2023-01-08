package com.bjpowernode.dao.imp;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.dao.ClassroomDao;
import com.bjpowernode.dao.DB;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dbc
 * @create 2023-01-03 19:35
 */
public class ClassroomDaoImp implements ClassroomDao {
    @Override
    public ArrayList<Classroom> getAllClassroom() {
        return DB.classrooms;
    }

    @Override
    public boolean AddClassroom(Classroom classroom) {
        if (classroom != null){
            DB.classrooms.add(classroom);
            return true;
        }
        return false;
    }

    @Override
    public boolean UpdateClassroom(Classroom classroom) {
        Iterator<Classroom> iterator = DB.classrooms.iterator();
        while (iterator.hasNext()) {
            Classroom next = iterator.next();
            if (next.getId() == classroom.getId()) {
                next.setName(classroom.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean DelClassroom(Classroom classroom) {
        return DB.classrooms.remove(classroom);
    }
}
