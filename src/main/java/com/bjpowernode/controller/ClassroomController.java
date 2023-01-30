package com.bjpowernode.controller;

import com.bjpowernode.beans.Classroom;
import com.bjpowernode.exception.MyException;
import com.bjpowernode.services.ClassroomService;
import com.bjpowernode.services.StuService;
import com.bjpowernode.services.imp.ClassroomServiceImp;
import com.bjpowernode.services.imp.StuServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @create 2023-01-03 19:43
 */
public class ClassroomController implements Initializable {
    ClassroomService classroomService = new ClassroomServiceImp();

    StuService stuService = new StuServiceImp();


    @FXML
    private TableColumn<Classroom, Integer> Colum1;
    @FXML
    private TableColumn<Classroom, String> Colum2;

    @FXML
    private TableView<Classroom> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //列绑定
        Colum1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Colum2.setCellValueFactory(new PropertyValueFactory<>("name"));
        select();
    }

    @FXML
    void select() {
        //清空表格
        table.getItems().clear();

        //从业务获取数据
        ArrayList<Classroom> all = classroomService.getAllClassroom();

        //将数据转换成表格能接受的形式
        ObservableList<Classroom> classrooms = FXCollections.observableArrayList(all);

        //将数据在表格上显示
        table.setItems(classrooms);
    }


    @FXML
    void add() throws IOException {
        //弹出面板设置设计好fxml界面
        URL resource = ClassroomController.class.getResource("/classroomAdd.fxml");
        Node node = FXMLLoader.load(resource);

        //从fxml文件中获取文本框数据
        TextField cName = (TextField) node.lookup("#cName");
        TextField cId = (TextField) node.lookup("#cId");
        Integer id = classroomService.getIntegers();
        cId.setPromptText(String.valueOf(id));

        //弹出面板
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);
        dialogPane.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        //弹出框架构
        Dialog<ButtonType> dialog = new Dialog<>();
        //弹出框架构设置弹出框面板
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("班级信息添加");
        Optional<ButtonType> buttonType = dialog.showAndWait();

        if (buttonType.get() == ButtonType.NO) {
            return;
        }
        String name = cName.getText();

        try {
            classroomService.AddClassroom(new Classroom(id, name));
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.YES);
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }

        //刷新表格
        select();
    }

    @FXML
    void del() {
        //从表格中获取选中的行
        TableView.TableViewSelectionModel<Classroom> selectionModel = table.getSelectionModel();
        Classroom classroom = selectionModel.getSelectedItem();

        //判断选中的行是否为空
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "",
                ButtonType.CLOSE);
        if (classroom == null) {
            //选中空行弹窗
            alert.setHeaderText("您还没有选中一行");
            alert.setTitle("班级信息删除");
            alert.show();
            return;
        }

        //删除提醒
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("删除班级数据提醒：");
        Optional<ButtonType> buttonType;
        //查看该班级下是否还有学生
        if (stuService.isClassStu(classroom)) {
            alert.setHeaderText("删除班级也将删除班级下的学生");  //班级中的学生一起删除
            buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.NO) {
                return;
            }
            stuService.DelClassStu(classroom);              //删除班级中的学生
        } else {
            alert.setHeaderText("删除班级数据将不可恢复");
            buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.NO) {
                return;
            }
        }
        //调用业务删除班级数据
        if (classroomService.DelClassroom(classroom)) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.setTitle("班级信息删除");
            alert.setHeaderText("删除班级信息成功");
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.setTitle("班级信息删除");
            alert.setHeaderText("删除班级信息失败");
            alert.show();
        }

        //刷新表格
        select();
    }

    @FXML
    void update() throws IOException {
        //获取选中行的数据
        TableView.TableViewSelectionModel<Classroom> selectionModel = table.getSelectionModel();
        Classroom classroom = selectionModel.getSelectedItem();             //此处是真实数据的引用，但还是要调用业务层

        //还没有选中行
        Alert alert = new Alert(Alert.AlertType.ERROR, "",
                ButtonType.CLOSE);
        alert.setTitle("班级信息修改");
        if (classroom == null) {
            alert.setHeaderText("您还没有选中一行");
            alert.show();
            return;
        }

        //获取fxml文件的url
        URL resource = ClassroomController.class.getResource("/classroomAdd.fxml");
        Node node = FXMLLoader.load(resource);

        //从fxml中获取组件
        TextField cId = (TextField) node.lookup("#cId");
        TextField cName = (TextField) node.lookup("#cName");

        int id = classroom.getId();
        String name = classroom.getName();
        cId.setPromptText(String.valueOf(id));
        cName.setText(name);

        //创建对话框窗口
        DialogPane dialogPane = new DialogPane();
        dialogPane.setGraphic(node);
        //加入按钮
        dialogPane.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        //创建对话框架构
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("班级信息修改");
        Optional<ButtonType> buttonType = dialog.showAndWait();

        //取消修改
        if (buttonType.get() == ButtonType.NO) {
            return;
        }

        //调用业务修改班级信息
        String name1 = cName.getText();
        boolean b = false;
        try {
            b = classroomService.UpdateClassroom(new Classroom(id, name1));
        } catch (MyException e) {
            alert.setHeaderText(e.getMessage());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.getButtonTypes().setAll(ButtonType.YES);
            alert.showAndWait();
            return;
        }
        if (b) {
            alert.setAlertType(Alert.AlertType.NONE);
            alert.setHeaderText("班级信息修改成功");
            alert.show();
        } else {
            alert.setHeaderText("班级信息修改失败");
            alert.show();
        }
        //刷新表格
        select();
    }
}
