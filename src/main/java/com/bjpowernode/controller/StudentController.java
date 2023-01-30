package com.bjpowernode.controller;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.beans.Stu;
import com.bjpowernode.dao.DB;
import com.bjpowernode.exception.MyException;
import com.bjpowernode.services.ClassroomService;
import com.bjpowernode.services.StuService;
import com.bjpowernode.services.imp.ClassroomServiceImp;
import com.bjpowernode.services.imp.StuServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.bjpowernode.dao.DB.stus;

/**
 * @author dbc
 * @create 2023-01-04 11:42
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<Stu> table;

    @FXML
    private TableColumn<Stu, Integer> Colum1;

    @FXML
    private TableColumn<Stu, String> Colum2;

    @FXML
    private TableColumn<Stu, String> Colum3;

    @FXML
    private TableColumn<Stu, Integer> Colum4;

    @FXML
    private TableColumn<Stu, Integer> Colum5;

    @FXML
    private TableColumn<Stu, Classroom> Colum6;

    @FXML
    private TextField count;

    StuService stuService = new StuServiceImp();
    ClassroomService classroomService = new ClassroomServiceImp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //列绑定
        Colum1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Colum2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Colum3.setCellValueFactory(new PropertyValueFactory<>("sex"));
        Colum4.setCellValueFactory(new PropertyValueFactory<>("age"));
        Colum5.setCellValueFactory(new PropertyValueFactory<>("score"));
        Colum6.setCellValueFactory(new PropertyValueFactory<>("classroom"));
        select();
    }

    @FXML
    void select() {
        //清空表格
        table.getItems().clear();

        //从业务获取学生的数据
        ArrayList<Stu> allStu = stuService.getAllStu();

        //将数据转换成表格接收的形式
        ObservableList<Stu> stus = FXCollections.observableArrayList(allStu);
        table.setItems(stus);
    }

    @FXML
    void add() throws IOException {
        URL resource = StudentController.class.getResource("/studentAdd.fxml");
        Node node = FXMLLoader.load(resource);

        //从fxml文件中获取组件
        TextField sId = (TextField) node.lookup("#sId");
        sId.setDisable(true);
        sId.setPromptText(String.valueOf(stuService.getIntegers()));
        TextField sName = (TextField) node.lookup("#sName");
        RadioButton sSex = (RadioButton) node.lookup("#sM");
        sSex.setSelected(true);
        TextField sAge = (TextField) node.lookup("#sAge");
        TextField sScore = (TextField) node.lookup("#sScore");
        ComboBox<Classroom> sClass = (ComboBox<Classroom>) node.lookup("#sClass");

        //初始化下拉框
        ArrayList<Classroom> allClassroom = classroomService.getAllClassroom();
        ObservableList<Classroom> classrooms = FXCollections.observableArrayList(allClassroom);
        sClass.setItems(classrooms);

        //创建弹出对话框
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);

        //设置按钮
        dialogPane.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        //创建弹出框架构
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("学生信息添加");
        Optional<ButtonType> buttonType = dialog.showAndWait();

        if (buttonType.get() == ButtonType.NO) {
            return;
        }
        //让业务添加数据
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        alert.setTitle("学生信息添加");
        //从文本框中获取数据
        int id = stuService.getIntegers();
        String name = sName.getText();
        String sex = sSex.isSelected() ? "男" : "女";
        String ageText = sAge.getText();
        String scoreText = sScore.getText();
        int score = 0;
        int age = 0;
        try {
            age = Integer.parseInt(ageText);
            score = Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            alert.setHeaderText("输入不能为空！");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.getButtonTypes().setAll(ButtonType.YES);
            alert.showAndWait();
            return;
        }
        Classroom classroom = sClass.getValue();


        boolean b;
        try {
            b = stuService.AddStu(new Stu(id, name, sex, age, score, classroom));
        } catch (MyException e) {
            alert.setHeaderText(e.getMessage());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.getButtonTypes().setAll(ButtonType.YES);
            alert.showAndWait();
            return;
        }

        if (b) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.setHeaderText("学生信息添加成功");
            alert.show();
        } else {
            alert.setHeaderText("学生信息添加失败");
            alert.show();
        }
        //刷新表格
        select();
    }

    @FXML
    void del() {
        //获取选中行的数据
        TableView.TableViewSelectionModel<Stu> selectionModel = table.getSelectionModel();
        Stu stu = selectionModel.getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.CLOSE);
        alert.setTitle("学生信息删除");
        if (stu == null) {
            alert.setHeaderText("您还没有选中一行");
            alert.show();
            return;
        }

        //删除提醒
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("是否确认删除该学生信息");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.NO) {
            return;
        }

        //调用业务删除学生信息
        if (stuService.DelStu(stu)) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.setHeaderText("删除学生信息成功");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("删除学生信息失败");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        }
        select();
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        //从表格中获取数据
        TableView.TableViewSelectionModel<Stu> selectionModel = table.getSelectionModel();
        Stu stu = selectionModel.getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.CLOSE);
        alert.setTitle("学生信息删除");
        if (stu == null) {
            alert.setHeaderText("您还没有选中一行");
            alert.show();
            return;
        }

        //获取fxml文件
        URL resource = StudentController.class.getResource("/studentAdd.fxml");
        Node node = FXMLLoader.load(resource);

        //从fxml文件中获取组件
        TextField sId = (TextField) node.lookup("#sId");
        TextField sName = (TextField) node.lookup("#sName");
        RadioButton sM = (RadioButton) node.lookup("#sM");
        RadioButton sW = (RadioButton) node.lookup("#sW");
        TextField sAge = (TextField) node.lookup("#sAge");
        TextField sScore = (TextField) node.lookup("#sScore");
        ComboBox<Classroom> sClass = (ComboBox<Classroom>) node.lookup("#sClass");

        //初始化下拉框
        ArrayList<Classroom> allClassroom = classroomService.getAllClassroom();
        ObservableList<Classroom> classrooms = FXCollections.observableArrayList(allClassroom);
        sClass.setItems(classrooms);
        sId.setDisable(true);

        //初始化窗口组件
        sId.setDisable(true);
        sId.setText(String.valueOf(stu.getId()));
        sName.setText(stu.getName());
        if ("男".equals(stu.getSex()) || "man".equals(stu.getSex())) {
            sM.setSelected(true);
        } else {
            sW.setSelected(true);
        }
        sAge.setText(String.valueOf(stu.getAge()));
        sScore.setText(String.valueOf(stu.getScore()));
        sClass.setValue(stu.getClassroom());

        //创建对话框窗口
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);
        dialogPane.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);

        //创建对话框架构
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("学生信息修改");
        dialog.setDialogPane(dialogPane);
        Optional<ButtonType> buttonType = dialog.showAndWait();

        if (buttonType.get() == ButtonType.NO) {
            return;
        }

        //从组件中获取数据
        String idText = sId.getText();
        String name = sName.getText();
        String sex = sM.isSelected() ? "男" : "女";
        String ageText = sAge.getText();
        String scoreText = sScore.getText();
        Classroom classroom = sClass.getValue();

        int id = Integer.parseInt(idText);
        int age = Integer.parseInt(ageText);
        int score = Integer.parseInt(scoreText);

        boolean b;
        try {
            b = stuService.UpdateStu(new Stu(id, name, sex, age, score, classroom));
        } catch (MyException e) {
            alert.setHeaderText(e.getMessage());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.getButtonTypes().setAll(ButtonType.YES);
            alert.showAndWait();
            return;
        }

        //调用业务修改数据
        if (b) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.setHeaderText("学生信息修改成功");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("学生信息修改失败");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        }

        //刷新表格
        select();
    }

    @FXML
    void takeRoll(ActionEvent event) throws IOException {

        //未输入点名的个数
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.CLOSE);
        alert.setTitle("学生点名");
        if (count.getText().isEmpty()) {
            alert.setHeaderText("您还没有输入点名学生的个数");
            alert.show();
            return;
        }

        int count = Integer.parseInt(this.count.getText());

        ArrayList<Stu> call = stuService.call(count);

        if (call == null) {
            alert.setHeaderText("点名数不合法或超出范围");
            alert.show();
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Stu stu : call) {      //循环中不能加号直接字符串累加
            stringBuilder.append(stu.getId());
            stringBuilder.append(":");
            stringBuilder.append(stu.getName());
            stringBuilder.append("\n");
        }

        String content = "回答问题的学生是：\n" + stringBuilder.toString();

        alert.setAlertType(Alert.AlertType.NONE);
        alert.setHeaderText(content);
        alert.show();
    }
}
