package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.model.User;
import sample.model.UserDAO;

import javax.swing.*;
import java.sql.SQLException;

public class UserController {

    @FXML
    private TextField nameUser;

    @FXML
    private TextField surnameUser;

    @FXML
    private TextField genderUser;

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
    private TextField idUser;

    @FXML
    private DatePicker date_birthday;

    @FXML
    private DatePicker date_birthdayUpdate;

    @FXML
    public void insertUser(javafx.event.ActionEvent actionEvent) throws SQLException {
        User user = new User(nameUser.getText(), surnameUser.getText(), genderUser.getText(), date_birthday.getValue(), marrigeUser.getText());
        if (user.userHaveEmptyFields()) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            if (user.getMarrige() != null) {
                if (!UserDAO.dbCheckIfExistUser(user.getMarrige())) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: "+user.getMarrige());
                     return;
                }
            }

            UserDAO.dbInsertUser(user);
        }

    }

    @FXML
    public void updateUser(javafx.event.ActionEvent actionEvent) throws SQLException {
    }
}



