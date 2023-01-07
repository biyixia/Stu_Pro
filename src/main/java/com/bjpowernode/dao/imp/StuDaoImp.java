package com.bjpowernode.dao.imp;
import com.bjpowernode.beans.Stu;
import com.bjpowernode.dao.DB;
import com.bjpowernode.dao.StuDao;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dbc
 * @create 2023-01-03 19:37
 */
public class StuDaoImp implements StuDao {
    @Override
    public ArrayList<Stu> getAllStu() {
        return DB.stus;
    }

    @Override
    public boolean AddStu(Stu stu) {
        if (stu != null){
            DB.stus.add(stu);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean UpdateStu(Stu stu) {
        Iterator<Stu> iterator = DB.stus.iterator();
        while (iterator.hasNext()) {
            Stu next = iterator.next();
            if (next.getId() == stu.getId()) {
                int index = DB.stus.indexOf(next);
                DB.stus.set(index,stu);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean DelStu(Stu stu) {
        return DB.stus.remove(stu);
    }

    @Override
    public void setStu(ArrayList<Stu> stus) {
        DB.stus = stus;
    }
}
