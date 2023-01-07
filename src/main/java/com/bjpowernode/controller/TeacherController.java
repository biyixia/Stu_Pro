package com.bjpowernode.controller;

import com.bjpowernode.beans.Stu;
import com.bjpowernode.beans.Teacher;
import com.bjpowernode.dao.DB;
import com.bjpowernode.services.TeacherService;
import com.bjpowernode.services.imp.TeacherServiceImp;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author dbc
 * @create 2023-01-05 17:08
 */
public class TeacherController implements Initializable {
    @FXML
    private Label label1;

    @FXML
    private TableView<Teacher> table;

    @FXML
    private TableColumn<Teacher, Integer> Colum1;

    @FXML
    private TableColumn<Teacher, Integer> Colum2;

    @FXML
    private TableColumn<Teacher, String> Colum3;

    @FXML
    private TableColumn<Teacher, String> Colum4;

    @FXML
    private TableColumn<Teacher, Integer> Colum5;

    @FXML
    private TableColumn<Teacher, Integer> Colum6;

    @FXML
    private TextField tId;

    @FXML
    private TextField tPw;

    @FXML
    private TextField tName;

    @FXML
    private TextField tSex;

    @FXML
    private TextField tAge;

    @FXML
    private TextField tSalary;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpdate;

    TeacherService teacherService = new TeacherServiceImp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //列绑定
        Colum1.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("id"));
        Colum2.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("password"));
        Colum3.setCellValueFactory(new PropertyValueFactory<Teacher,String>("name"));
        Colum4.setCellValueFactory(new PropertyValueFactory<Teacher,String>("sex"));
        Colum5.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("age"));
        Colum6.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("salary"));
        select();
    }

    @FXML
    void select() {
        //清空表格
        table.getItems().clear();
        //调用业务获取数据
        ArrayList<Teacher> allTeacher = teacherService.getAllTeacher();
        //转换为表格接收的形式
        ObservableList<Teacher> teachers = FXCollections.observableArrayList(allTeacher);
        table.setItems(teachers);
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        //获取fxml文件url
        URL resource = TeacherController.class.getResource("/teacherAdd.fxml");
        Node node = FXMLLoader.load(resource);

        //获取xml下的组件
        TextField tId = (TextField) node.lookup("#tId");
        TextField tPw = (TextField) node.lookup("#tPw");
        TextField tName = (TextField) node.lookup("#tName");
        TextField tAge = (TextField) node.lookup("#tAge");
        RadioButton tM = (RadioButton) node.lookup("#rM");
        RadioButton tW = (RadioButton) node.lookup("#rW");
        TextField tSalary = (TextField) node.lookup("#tSalary");

        //对话框窗口的创建
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        //对话框架构的创建
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("教师信息添加");
        Optional<ButtonType> buttonType = dialog.showAndWait();

        if (buttonType.get() == ButtonType.NO) {
            return;
        }

        int idText = Integer.parseInt(tId.getText());
        int pwText = Integer.parseInt(tPw.getText());
        String nameText = tName.getText();
        int ageText = Integer.parseInt(tAge.getText());
        int salaryText = Integer.parseInt(tSalary.getText());
        String sex = tM.isSelected() ? "男" : "女";

        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        alert.setTitle("教师信息添加");
        if (teacherService.AddTeacher(new Teacher(idText, pwText, nameText, sex, ageText, salaryText))) {
            alert.setHeaderText("教室信息添加成功");
            alert.show();
        } else{
            alert.setHeaderText("教室信息添加失败");
            alert.show();
        }
        select();
    }

    @FXML
    void del(ActionEvent event) {
        //获取选中行的数据
        TableView.TableViewSelectionModel<Teacher> selectionModel = table.getSelectionModel();
        Teacher teacher = selectionModel.getSelectedItem();

        //选中空行
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        alert.setTitle("教师信息删除");
        if (teacher == null){
            alert.setHeaderText("您还没有选中一行");
            alert.show();
            return;
        }
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("是否确认删除该教师的信息");
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.NO){
            return;
        }

        //调用业务删除信息
        if (teacherService.DelTeacher(teacher)) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.setHeaderText("删除教师信息成功");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        }else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("删除教师信息失败");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.show();
        }
        select();
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        //获取选中行的数据
        TableView.TableViewSelectionModel<Teacher> selectionModel = table.getSelectionModel();
        Teacher teacher = selectionModel.getSelectedItem();

        //还没有选中行报错
        Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.CLOSE);
        if (teacher == null){
            alert.setHeaderText("您还没有选中一行");
            alert.show();
        }

        //获取fxml文件的url形式
        URL resource = TeacherController.class.getResource("/teacherAdd.fxml");
        assert resource != null;
        Node node = FXMLLoader.load(resource);

        //从node中获取fxml文件中的组件
        TextField tId = (TextField) node.lookup("#tId");
        TextField tName = (TextField) node.lookup("#tName");
        TextField tAge = (TextField) node.lookup("#tAge");
        TextField tPw = (TextField) node.lookup("#tPw");
        TextField tSalary = (TextField) node.lookup("#tSalary");
        RadioButton rM = (RadioButton) node.lookup("#rM");
        RadioButton rW = (RadioButton) node.lookup("#rW");

        //将数据显示在组件上
        tId.setText(String.valueOf(teacher.getId()));
        tName.setText(teacher.getName());
        tAge.setText(String.valueOf(teacher.getAge()));
        tPw.setText(String.valueOf(teacher.getPassword()));
        tSalary.setText(String.valueOf(teacher.getSalary()));
        if ("男".equals(teacher.getSex())) {
            rM.setSelected(true);
        }else{
            rW.setSelected(true);
        }

        //创建对话框窗口
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);
        dialogPane.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);

        //创建对话框窗口架构
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("教师信息修改");
        Optional<ButtonType> buttonType = dialog.showAndWait();

        if (buttonType.get() == ButtonType.NO){
            return;
        }

        //获取组件上修改后的数据
        int id = Integer.parseInt(tId.getText());
        String name = tName.getText();
        int age = Integer.parseInt(tAge.getText());
        int pw = Integer.parseInt(tPw.getText());
        int salary = Integer.parseInt(tSalary.getText());
        String sex = rM.isSelected()? "男":"女";

        if (teacherService.UpdateTeacher(new Teacher(id,pw,name,sex,age,salary))) {
            System.out.println("教师信息修改成功");
        }

        select();
    }
}
