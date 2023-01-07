package com.bjpowernode.controller;

import com.bjpowernode.beans.Stu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author dbc
 * @create 2023-01-07 10:52
 */
public class StudentTakeRollController implements Initializable {
    @FXML
    private static TableView<Stu> table;

    @FXML
    private TableColumn<Stu,String> sName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //列绑定
        sName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
