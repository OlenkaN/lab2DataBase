package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.model.UserDAO;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class UserController {

    @FXML
    private TextField nameUser;
    @FXML
    private TextField surnameUser;

    @FXML
    private TextField genderUser;

    @FXML
    private TextField birthdayUser;

    @FXML
    private TextField marrigeUser;

    @FXML
    private Button AddUser;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField surnameUpdate;

    @FXML
    private TextField marrigeUpdate;

    @FXML
    private TextField genderUpdate;

    @FXML
    private TextField birthdayUpdate;
    @FXML
    private DatePicker date_birthday;

    @FXML
    private TextField idUser;



    @FXML
    public void insertUser(ActionEvent actionEvent) throws SQLException {
        UserDAO.insertEmployee(nameUser.getText(), surnameUser.getText(), genderUser.getText(), date_birthday.getValue(), Integer.parseInt(marrigeUser.getText()));
    }

}

