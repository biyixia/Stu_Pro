package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbc
 * @create 2023-01-03 19:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Comparable<Stu>{
    int id;
    String name;
    String sex;
    int age;
    int score;
    Classroom classroom;

    @Override
    public int compareTo(Stu o) {
        if (getId() == o.getId()){
            return 0;
        }else if (getId() > o.getId()){
            return 1;
        }else {
            return -1;
        }
    }
}
