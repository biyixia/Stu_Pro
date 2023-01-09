package com.bjpowernode.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbc
 * @create 2023-01-03 19:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    int id;
    String name;
    @Override
    public String toString() {
        return name;
    }
}
