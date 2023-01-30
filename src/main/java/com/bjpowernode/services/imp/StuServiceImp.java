package com.bjpowernode.services.imp;
import com.bjpowernode.beans.Classroom;
import com.bjpowernode.beans.Stu;
import com.bjpowernode.dao.StuDao;
import com.bjpowernode.dao.imp.StuDaoImp;
import com.bjpowernode.exception.MyException;
import com.bjpowernode.services.StuService;

import java.util.*;

/**
 * @author dbc
 * @create 2023-01-03 19:40
 */
public class StuServiceImp implements StuService {

    public static ArrayList<String> cName = new ArrayList<>();
    static {
        cName.add("SZ2212");
        cName.add("SZ2212");
        cName.add("SZ2213");
        cName.add("SZ2214");
        cName.add("SZ2214");
    }
    public static LinkedList<Integer> integers = new LinkedList<>();
    static {
        integers.add(106);
    }

    StuDao stuDao = new StuDaoImp();

    @Override
    public ArrayList<Stu> getAllStu() {
        return stuDao.getAllStu();
    }

    @Override
    public boolean AddStu(Stu stu) throws MyException {
        if (stu.getSex() == null){
            throw new MyException("您还没有输入性别！");
        }
        if (stu.getClassroom() == null){
            throw new MyException("您还没有输入班级！");
        }
        if (stu.getName().length() < 1 || stu.getName().length() > 10){
            throw new MyException("学生姓名必须为1-10个字符");
        }
        if(stu.getAge() < 0 || stu.getAge() > 100){
            throw new MyException("您输入的年龄有误");
        }
        if (stu.getScore() < 0 || stu.getScore() > 150){
            throw new MyException("您输入的成绩有误");
        }
        integers.add(integers.getFirst()+1);
        stu.setId(integers.pollFirst());
        cName.add(stu.getClassroom().getName());
        return stuDao.AddStu(stu);
    }

    @Override
    public boolean UpdateStu(Stu stu) throws MyException {
        if (stu.getName().length() < 1 || stu.getName().length() > 10){
            throw new MyException("学生姓名必须为1-10个字符");
        }
        if(stu.getAge() < 0 || stu.getAge() > 100){
            throw new MyException("您输入的年龄有误");
        }
        if (stu.getScore() < 0 || stu.getScore() > 150){
            throw new MyException("您输入的成绩有误");
        }
        return stuDao.UpdateStu(stu);
    }

    @Override
    public boolean DelStu(Stu stu) {
        integers.offerFirst(stu.getId());
        cName.remove(stu.getClassroom().getName());
        return stuDao.DelStu(stu);
    }

    @Override
    public void DelClassStu(Classroom classroom) {
        ArrayList<Stu> allStu = getAllStu();
        Iterator<Stu> iterator = allStu.iterator();
        while (iterator.hasNext()) {
            Stu stu = iterator.next();
            if (stu.getClassroom().getId() == classroom.getId()){
                iterator.remove();
            }
        }
        while (cName.remove(classroom.getName()));
    }

    @Override
    public Integer getIntegers() {
        return integers.peekFirst();
    }



    @Override
    public boolean isClassStu(Classroom classroom) {
        return cName.contains(classroom.getName());
    }

    @Override
    public ArrayList<String> getCName() {
        return cName;
    }

    @Override
    public ArrayList<Stu> call(int num) {
        ArrayList<Stu> allStu = getAllStu();
        if (num <= 0 || num > allStu.size()){
            return null;
        }
        HashSet<Stu> stus = new HashSet<>();
        for (int i = 0; i < num; i++) {
            while (!stus.add(allStu.get(new Random().nextInt(allStu.size()))));
        }
        ArrayList<Stu> stus1 = new ArrayList<>(stus);
        Collections.sort(stus1);
        return stus1;
    }
}
