package com.bjpowernode.controller;

import com.bjpowernode.beans.Classroom;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * @author dbc
 * @create 2023-01-06 15:10
 */
public class StudentAddController {

    @FXML
    private TextField sId;

    @FXML
    private TextField sName;

    @FXML
    private RadioButton sM;

    @FXML
    private ToggleGroup sSex;

    @FXML
    private RadioButton sW;

    @FXML
    private TextField sAge;

    @FXML
    private TextField sScore;

    @FXML
    private ComboBox<Classroom> sClass;
}
