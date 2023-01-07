package com.bjpowernode.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dbc
 * @create 2023-01-05 16:17
 */
public class MainController implements Initializable {
    @FXML
    private Button btnClassroomView;

    @FXML
    private Button btnStuView;

    @FXML
    private Button btnExit;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnTeacherView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showClassroomView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: 2023/1/5 点击按钮退出界面
    @FXML
    void exit(ActionEvent event) throws IOException {
        //获取教室URL
        URL resource = MainController.this.getClass().getResource("/login.fxml");
        Parent parent = FXMLLoader.load(resource);

        Scene scene = new Scene(parent, 600, 400);
        ObservableList<Stage> stages = FXRobotHelper.getStages();
        stages.get(0).setScene(scene);
    }


    @FXML
    void showTeacherView() throws IOException {
        URL resource = MainController.this.getClass().getResource("/teacher.fxml");
        Node node = (Node) FXMLLoader.load(resource);
        borderPane.setCenter(node);
    }

    // TODO: 2023/1/5  点击按钮显示教室界面
    @FXML
    void showClassroomView() throws IOException {
        //Node节点类型，javaFX上面所有组件对象都是Node对象
        URL resource = MainController.this.getClass().getResource("/classroom.fxml");
        Node node = (Node)FXMLLoader.load(resource);
        borderPane.setCenter(node);
    }

    // TODO: 2023/1/5 点击按钮显示学生界面
    @FXML
    void showStuVIew() throws IOException {
        URL resource = MainController.this.getClass().getResource("/student.fxml");
        Node node = (Node)FXMLLoader.load(resource);
        borderPane.setCenter(node);
    }
}
