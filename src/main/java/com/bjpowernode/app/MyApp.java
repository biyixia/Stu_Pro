package com.bjpowernode.app;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author dbc
 * @create 2023-01-03 19:28
 */
/*
    1.继承Application类
    2.重写start方法
    3.main方法启动
*/

public class MyApp extends Application{
    public static void main(String[] args) {
        try {
            Application.launch(args);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //显示主窗口，并设置标题
        primaryStage.show();
        primaryStage.setTitle("学生管理系统");

        //将FXML文件转换文url形式
        URL resource = MyApp.this.getClass().getResource("/login.fxml");
        assert resource != null;
        Parent parent = FXMLLoader.load(resource);

        //配置场景
        Scene scene = new Scene(parent,600,400);
        primaryStage.setScene(scene);
    }
}
