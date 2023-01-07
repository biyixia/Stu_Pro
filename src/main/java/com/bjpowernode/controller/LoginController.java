package com.bjpowernode.controller;

import com.bjpowernode.app.MyApp;
import com.bjpowernode.beans.Teacher;
import com.bjpowernode.dao.DB;
import com.bjpowernode.services.TeacherService;
import com.bjpowernode.services.imp.TeacherServiceImp;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author dbc
 * @create 2023-01-04 20:07
 */
public class LoginController implements Initializable {
    public static ObservableList<Stage> stages = FXRobotHelper.getStages();
    @FXML
    private TextField tId;

    @FXML
    private PasswordField pw;

    @FXML
    private CheckBox rbpw;

    @FXML
    private Button signUp;

    @FXML
    private Button signIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tId.setText(DB.id);
        pw.setText(DB.pw);
        rbpw.setSelected(DB.selected);
    }

    @FXML
    void logIn() throws IOException {
        //获取文本框的数据
        String idText = tId.getText();
        String psw = this.pw.getText();
        int id = Integer.parseInt(idText);
        int password = Integer.parseInt(psw);
         DB.selected = rbpw.isSelected();

        //验证账号,通过id找到账号
        TeacherService teacherService = new TeacherServiceImp();
        Teacher idTeacher = teacherService.getIdTeacher(id);

        if (idTeacher != null && idTeacher.getPassword() == password){//登录成功
            if (DB.selected){
                DB.id = idText;
                DB.pw = psw;
            }else {
                DB.id = "";
                DB.pw = "";
            }
            //获取教室URL
            URL resource = LoginController.this.getClass().getResource("/stu_pro.fxml");
            Parent parent = FXMLLoader.load(resource);

            Scene scene = new Scene(parent, 800, 600);

            stages.get(0).setScene(scene);
            stages.get(0).setMinWidth(800);
            stages.get(0).setMaxHeight(600);
        }else {//登录失败
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提醒：");
            alert.setHeaderText("账号或密码错误，请重新输入");
            alert.showAndWait();
        }
    }

    @FXML
    void signUp1(ActionEvent event) {

    }
}
