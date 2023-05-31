package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField txtUser;
    public TextField txtPassword;
    public JFXButton btnLogin;
    public Hyperlink linkRegister;
    public Text verification;

    UsuariosDAO usuariosDAO;
    public static Usuarios user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calling UsuariosDAO in order to get the users loged
        usuariosDAO = new UsuariosDAO();
        usuariosDAO.getConnection();
        verification.setText(" ");

        //Creating Key Events for forms
        btnLogin.setOnAction(event -> btnLogin()); // Asociate the action to the button

        txtUser.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin();
            }
        });
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogin();
            }
        });

    }

    public void btnLogin() {
        //Check credentials
        String username = txtUser.getText();
        String password = txtPassword.getText();
        if (usuariosDAO.comprobarUsuario(username, password)) {
            System.out.println("Usuario encontrado\n");
            user = usuariosDAO.getUser(username);
            //Scene Home
            Scene sceneHome = TaskFlowApplication.sceneHome;
            Stage stageHome = (Stage) btnLogin.getScene().getWindow();

            stageHome.setScene(sceneHome);
            stageHome.show();

            //Change the name of the user in all the scenes i want :)
            TaskFlowApplication.controladorHome.nombreUsuario.setText("Hola " + UsuariosDAO.getUser(user.getUsername()).getNombre());
            //  TaskFlowApplication.controladorCategorias.nombreUsuario.setText("Hola " + UsuariosDAO.getUser(user.getUsername()).getNombre());

            try {
                TaskFlowApplication.controladorHome.mostrarTareas();
            } catch (SQLException e) {
                System.out.printf("Error al mostrar las tareas. \n Error: " + e.getMessage());
            }
            TaskFlowApplication.controladorHome.busquedaDinamica();
        } else {
            eliminarCampos();
            verification.setText("Usuario no encontrado");
            verification.setStyle("-fx-font-size: 16px;");
            verification.setFill(Color.RED);
        }
    }

    public void linkRegister(ActionEvent actionEvent) {
        verification.setText(" ");

        //Scene Register
        Scene sceneRegister = TaskFlowApplication.sceneRegister;
        Stage stageRegister = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageRegister.setScene(sceneRegister);
        stageRegister.show();
    }

    //Set all fields null
    public void eliminarCampos() {
        txtUser.clear();
        txtPassword.clear();
    }

    public void onButtonMousePressed(MouseEvent mouseEvent) {
        btnLogin.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
    }

    public void onButtonMouseReleased(MouseEvent mouseEvent) {
        btnLogin.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
    }
}
