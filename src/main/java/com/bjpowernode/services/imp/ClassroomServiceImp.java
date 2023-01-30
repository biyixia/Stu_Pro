package com.bjpowernode.services.imp;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.dao.ClassroomDao;
import com.bjpowernode.dao.imp.ClassroomDaoImp;
import com.bjpowernode.exception.MyException;
import com.bjpowernode.services.ClassroomService;
import com.bjpowernode.services.StuService;

import java.util.ArrayList;
import java.util.Iterator;
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
    StuService stuService = new StuServiceImp();

    @Override
    public ArrayList<Classroom> getAllClassroom() {
        return classroomDao.getAllClassroom();
    }

    @Override
    public Classroom getIdClassroom(int cid) {
        ArrayList<Classroom> allClassroom = getAllClassroom();
        Iterator<Classroom> iterator = allClassroom.iterator();
        while (iterator.hasNext()) {
            Classroom classroom = iterator.next();
            if (classroom.getId() == cid)
                return classroom;
        }
        return null;
    }

    @Override
    public boolean AddClassroom(Classroom classroom) throws MyException {
        if (classroom.getName().length() < 4 || classroom.getName().length() > 10){
            throw new MyException("班级名称必须为4-10个字符");
        }
        integers.add(integers.getFirst()+1);
        classroom.setId(integers.pollFirst());
        return classroomDao.AddClassroom(classroom);
    }

    @Override
    public boolean UpdateClassroom(Classroom classroom) throws MyException {
        if (classroom.getName().length() < 4 || classroom.getName().length() > 10){
            throw new MyException("班级名称必须为4-10个字符");
        }
        Classroom classroom1 = getIdClassroom(classroom.getId());
        ArrayList<String> cName = stuService.getCName();
        int count = 0;
        while (cName.remove(classroom1.getName())) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            cName.add(classroom.getName());
        }
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
