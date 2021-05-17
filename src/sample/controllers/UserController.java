package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.User;
import sample.dao.UserDAO;
import sample.model.Users;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
    private TableColumn<Users, Integer> colUserId;
    @FXML
    private TableColumn<Users, String> colUserName;
    @FXML
    private TableColumn<Users, String> colUserSurname;
    @FXML
    private TableColumn<Users, String> colUserGender;
    @FXML
    private TableColumn<Users, LocalDateTime> colUserBitrhday;
    @FXML
    private TableColumn<Users, Integer> colUser_ID;
    @FXML
    private TableView usersTable;

    @FXML
    public void insertUser(javafx.event.ActionEvent actionEvent) throws SQLException {
        try {
            User user = new User(nameUser.getText(), surnameUser.getText(), genderUser.getText(), date_birthday.getValue(), marrigeUser.getText());
            if (user.userHaveEmptyFields()) {
                JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
            } else {

                if (user.getMarriage() != null) {
                    if (!UserDAO.dbCheckIfExistUser(user.getMarriage())) {
                        JOptionPane.showMessageDialog(null, "There no such user with id: " + user.getMarriage());
                        return;
                    }
                    if (UserDAO.dbCheckIftUserIsMarried(user.getMarriage())) {
                        JOptionPane.showMessageDialog(null, "There user with id: " + user.getMarriage() + " is already married");
                        return;
                    }
                }

                UserDAO.dbInsertUser(user);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id should be integer numbers");
        }

        ObservableList<Users> usersObservableList = UserDAO.getAllRecords();
        populateTable(usersObservableList);

    }

    @FXML
    public void updateUser(javafx.event.ActionEvent actionEvent) throws SQLException {

        try {
            User user = new User(nameUpdate.getText(), surnameUpdate.getText(), genderUpdate.getText(), date_birthdayUpdate.getValue(), marrigeUpdate.getText(), idUser.getText());
            if (user.userHaveEmptyFields()) {
                JOptionPane.showMessageDialog(null, "There some empty fields that should be filled");
            } else {
                if (user.getMarriage() != null) {
                    if (!UserDAO.dbCheckIfExistUser(user.getMarriage())) {
                        JOptionPane.showMessageDialog(null, "There no such user with id: " + user.getMarriage());
                        return;
                    }
                    if (UserDAO.dbCheckIftUserIsMarried(user.getMarriage())) {
                        JOptionPane.showMessageDialog(null, "There user with id : " + user.getMarriage() + " is already married");
                        return;
                    }
                    if (!UserDAO.dbCheckIfExistUser(user.getId())) {
                        JOptionPane.showMessageDialog(null, "There no such user with id: " + user.getId());
                        return;
                    }
                }

                UserDAO.dbUpdateUser(user);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id should be integer numbers");
        }


        ObservableList<Users> usersObservableList = UserDAO.getAllRecords();
        populateTable(usersObservableList);

    }

    @FXML
    public void deleteUser(javafx.event.ActionEvent actionEvent) throws SQLException {

        if (idUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be filled");
        } else {

            try {
                Integer id = Integer.parseInt(idUser.getText());
                System.out.println(id);
                if (!UserDAO.dbCheckIfExistUser(id)) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: " + id);
                    return;
                }
                UserDAO.dbDeleteUser(id);

                ObservableList<Users> usersObservableList = UserDAO.getAllRecords();
                populateTable(usersObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer numbers");
            }
        }

    }

    @FXML
    private void initialize() throws Exception {
        colUserId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colUserName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colUserSurname.setCellValueFactory(cellData -> cellData.getValue().surnamePropertyProperty());
        colUserBitrhday.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        colUserGender.setCellValueFactory(cellData -> cellData.getValue().genderPropertyProperty());
        colUser_ID.setCellValueFactory(cellData -> cellData.getValue().userPropertyProperty().asObject());
        ObservableList<Users> usersObservableList = UserDAO.getAllRecords();
        populateTable(usersObservableList);
    }

    private void populateTable(ObservableList<Users> usersObservableList) {
        usersTable.setItems(usersObservableList);
    }

    @FXML
    private void searchUser(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (idUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty that should be filled");
        } else {
            try {
                if (!UserDAO.dbCheckIfExistUser(Integer.parseInt(idUser.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: " + idUser.getText());
                    return;
                }
                ObservableList<Users> usersObservableList = UserDAO.searchUsers(idUser.getText());
                populateTable(usersObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer numbers");
            }
        }
    }

    @FXML
    private void SearchAllUsers(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Users> usersObservableList = UserDAO.getAllRecords();
        populateTable(usersObservableList);
    }
}




