package com.bjpowernode.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * @author dbc
 * @create 2023-01-06 16:34
 */
public class TeacherAddController {

    @FXML
    private TextField tId;

    @FXML
    private TextField tName;

    @FXML
    private TextField tAge;

    @FXML
    private TextField tPw;

    @FXML
    private TextField tSalary;

    @FXML
    private RadioButton rM;

    @FXML
    private RadioButton rW;

    @FXML
    private ToggleGroup tM;
}
