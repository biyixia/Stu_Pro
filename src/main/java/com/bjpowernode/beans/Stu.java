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
public class Stu {
    int id;
    String name;
    String sex;
    int age;
    int score;
    Classroom classroom;
}
