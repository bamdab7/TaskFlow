package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    public TextField txtUser;
    public TextField txtPassword;
    public TextField txtName;
    public TextField txtEmail;
    public Hyperlink linkRegister;
    public JFXButton btnSignup;

    UsuariosDAO usuariosDAO;
    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //Calling UsuariosDAO in order to add users
            usuariosDAO = new UsuariosDAO();
            usuariosDAO.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Conexion conseguida con bd usuarios\n");
    }

    public void btnSignup(ActionEvent actionEvent) throws SQLException {
        //Collect all data from textfield and insert into database and then redirect to Login
        String estilo = "-fx-border-color: red;";
        Usuarios user = new Usuarios();
        if (txtUser.getText().isEmpty()) {
            System.out.println("Falta user");
            //txtUser.setStyle(estilo);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Introduzca el usuario");
            //alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setPrefWidth(200);
             alert.getDialogPane().setPrefHeight(50);
            alert.showAndWait();
            txtUser.clear();
        }else if(txtPassword.getText().isEmpty()){
            System.out.println("Falta contraseña");
            txtPassword.clear();}
        else if (txtEmail.getText().isEmpty()) {
            System.out.println("Falta email");
            txtEmail.clear();
        } else if (txtName.getText().isEmpty()) {
            System.out.println("Falta nombre");
            txtName.clear();

        } else {
            //Insert
            user.setId_usuario(id);
            user.setNombre(txtName.getText());
            user.setUsername(txtUser.getText());
            user.setPassword(txtPassword.getText());
            user.setEmail(txtEmail.getText());

            usuariosDAO.insertUsuario(user);
            id = 0;

            eliminarCampos();

            //Scene Login
             Scene sceneLogin = TaskFlowApplication.sceneLogin;
             Stage stageLogin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        }
    }

    public void linkRegister(ActionEvent actionEvent) {
        //Scene Login
         Scene sceneLogin = TaskFlowApplication.sceneLogin;
         Stage stageLogin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageLogin.setScene(sceneLogin);
        stageLogin.show();

        eliminarCampos();
    }

    public void eliminarCampos(){
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtUser.clear();
    }
}
