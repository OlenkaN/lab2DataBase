package sample.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dao.RoleDAO;
import sample.model.Roles;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class RoleController {

    @FXML
    private TextField nameRole;

    @FXML
    private TableView<Roles> rolesTable;

    @FXML
    private TableColumn<Roles, String> colRoleName;

    @FXML
    private TableColumn<Roles, String> colRoleDescription;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField descriptionUpdate;

    @FXML
    private TextField descritionRole;
    @FXML
    private Button backHome;
    @FXML
    void backHome(ActionEvent actionEvent) {
        Stage stage = (Stage) backHome.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/home.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Home page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void deleteRole(ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Name field is empty and should be initialized");
        } else {
            try {

                if (!RoleDAO.dbCheckIfExistRole(nameUpdate.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with name: " + nameUpdate.getText());
                    return;
                }
                RoleDAO.dbDeleteRole(nameUpdate.getText());
                ObservableList<Roles> rolesObservableList = RoleDAO.getAllRoles();
                populateTable(rolesObservableList);
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }


    }

    @FXML
    public void insertRole(ActionEvent actionEvent) throws SQLException {
        if (nameRole.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (RoleDAO.dbCheckIfExistRole(nameRole.getText())) {
                    JOptionPane.showMessageDialog(null, "There is already exist  role with name: " + nameRole.getText());
                    return;
                }
                RoleDAO.dbInsertRole(nameRole.getText(), descritionRole.getText());
                ObservableList<Roles> rolesObservableList = RoleDAO.getAllRoles();
                populateTable(rolesObservableList);
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }


    }

    @FXML
    public void updateRole(ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!RoleDAO.dbCheckIfExistRole(nameUpdate.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with name: " + nameRole.getText());
                    return;
                }
                RoleDAO.dbUpdateRole(nameUpdate.getText(), descriptionUpdate.getText());
                ObservableList<Roles> rolesObservableList = RoleDAO.getAllRoles();
                populateTable(rolesObservableList);
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }

    @FXML
    private void initialize() throws Exception {

        colRoleName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colRoleDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionPropertyProperty());
        ObservableList<Roles> rolesObservableList = RoleDAO.getAllRoles();
        populateTable(rolesObservableList);

    }

    private void populateTable(ObservableList<Roles> rolesObservableList) {
        rolesTable.setItems(rolesObservableList);
    }

    @FXML
    private void searchRole(ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There name field is empty and that should be filled");
        } else {

                if (!RoleDAO.dbCheckIfExistRole(nameUpdate.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with name: " + nameRole.getText());
                    return;
                }
                ObservableList<Roles> rolesObservableList = RoleDAO.searchRole(nameUpdate.getText());
                populateTable(rolesObservableList);

        }
    }

    @FXML
    private void SearchAllRoles(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Roles> rolesObservableList = RoleDAO.getAllRoles();
        populateTable(rolesObservableList);
    }
}
