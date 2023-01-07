package com.bjpowernode.services.imp;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.dao.ClassroomDao;
import com.bjpowernode.dao.imp.ClassroomDaoImp;
import com.bjpowernode.services.ClassroomService;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author dbc
 * @create 2023-01-03 19:40
 */
public class ClassroomServiceImp implements ClassroomService {

    public static  LinkedList<Integer> integers = new LinkedList<>();
    static {
        integers.add(104);
    }
    ClassroomDao classroomDao = new ClassroomDaoImp();

    @Override
    public ArrayList<Classroom> getAllClassroom() {
        return classroomDao.getAllClassroom();
    }

    @Override
    public boolean AddClassroom(Classroom classroom){
        integers.add(integers.getFirst()+1);
        classroom.setId(integers.pollFirst());
        return classroomDao.AddClassroom(classroom);
    }

    @Override
    public boolean UpdateClassroom(Classroom classroom) {
        return classroomDao.UpdateClassroom(classroom);
    }


    @Override
    public boolean DelClassroom(Classroom classroom) {
        integers.offerFirst(classroom.getId());
        return classroomDao.DelClassroom(classroom);
    }

    @Override
    public Integer getIntegers() {
        return integers.peekFirst();
    }
}