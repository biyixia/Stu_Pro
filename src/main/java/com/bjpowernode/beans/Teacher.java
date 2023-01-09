package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.util.Password;

/**
 * @author dbc
 * @create 2023-01-04 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    int id;
    int password;
    String name;
    String sex;
    int age;
    int salary;
}
