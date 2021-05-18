package sample.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dao.*;
import sample.model.Category_Products;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.model.Users_brand_roles;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Users_brand_rolesController {

    @FXML
    private TextField user_id;

    @FXML
    private TextField brand_id;


    @FXML
    private TableView<Users_brand_roles> userBrandsRoleTable;

    @FXML
    private TableColumn<Users_brand_roles, Integer> colUBRId;

    @FXML
    private TableColumn<Users_brand_roles, Integer> colUserId;

    @FXML
    private TableColumn<Users_brand_roles, String> colUserName;

    @FXML
    private TableColumn<Users_brand_roles, Integer> colBrandId;

    @FXML
    private TableColumn<Users_brand_roles, String> colBrandName;

    @FXML
    private TableColumn<Users_brand_roles, String> colRole;

    @FXML
    private TextField id;

    @FXML
    private TextField role_id;

    @FXML
    private TextField userUpdate;

    @FXML
    private TextField brandUpdate;

    @FXML
    private TextField roleUpdate;
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
    void delete(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!Users_brand_rolesDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }
                Users_brand_rolesDAO.dbDeleteConnection(Integer.parseInt(id.getText()));
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.getAllUserBrandRole();
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }


    }

    @FXML
    public void insertUserBrandCategory(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (user_id.getText().equals("") || brand_id.getText().equals("")|| role_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (!UserDAO.dbCheckIfExistUser(Integer.parseInt(user_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: " + user_id.getText());
                    return;
                }
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(brand_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + brand_id.getText());
                    return;
                }
                if (!RoleDAO.dbCheckIfExistRole(role_id.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with id: " + role_id.getText());
                    return;
                }

                Users_brand_rolesDAO.dbInsertConnection(Integer.parseInt(user_id.getText()), Integer.parseInt(brand_id.getText()),role_id.getText());
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.getAllUserBrandRole();
                populateTable(users_brand_roles);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "User_is and brand_id should be integer numbers");

            } catch (SQLException throwables) {
                System.out.println("Error occurred while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }


    @FXML
    public void update(ActionEvent actionEvent) throws SQLException {
        if (userUpdate.getText().equals("") || brandUpdate.getText().equals("")|| roleUpdate.getText().equals("")||id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (!UserDAO.dbCheckIfExistUser(Integer.parseInt(userUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: " + userUpdate.getText());
                    return;
                }
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(brandUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + brandUpdate.getText());
                    return;
                }
                if (!RoleDAO.dbCheckIfExistRole(roleUpdate.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with id: " + roleUpdate.getText());
                    return;
                }
                if (!Users_brand_rolesDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }

                Users_brand_rolesDAO.dbUpdateConnection(Integer.parseInt(id.getText()),
                        Integer.parseInt(userUpdate.getText()),
                        Integer.parseInt(brandUpdate.getText()),
                        roleUpdate.getText());
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.getAllUserBrandRole();
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "User_is, brand_id, id  should be integer numbers");
            } catch (SQLException throwables) {
                System.out.println("Error occurred while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }

    @FXML
    private void initialize() throws Exception {
        colUBRId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colUserId.setCellValueFactory(cellData -> cellData.getValue().userIdPropertyProperty().asObject());
        colBrandId.setCellValueFactory(cellData -> cellData.getValue().brandIdPropertyProperty().asObject());
        colRole.setCellValueFactory(cellData -> cellData.getValue().nameRolePropertyProperty());
        colBrandName.setCellValueFactory(cellData -> cellData.getValue().nameBrandPropertyProperty());
        colUserName.setCellValueFactory(cellData -> cellData.getValue().nameUserPropertyProperty());
        ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.getAllUserBrandRole();
        populateTable(users_brand_roles);
    }

    private void populateTable(ObservableList<Users_brand_roles> collectionsObservableList) {
        userBrandsRoleTable.setItems(collectionsObservableList);
    }

    @FXML
    private void searchByID(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!Users_brand_rolesDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.searchUserBrandRoles(id.getText());
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void searchByUser(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (userUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There user field is empty and that should be filled");
        } else {
            try {
                if (!UserDAO.dbCheckIfExistUser(Integer.parseInt(userUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such user with id: " + userUpdate.getText());
                    return;
                }

                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.searchByUser(userUpdate.getText());
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }
    @FXML
    private void searchByBrand(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (brandUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(brandUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + brandUpdate.getText());
                    return;
                }
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.searchByBrand(brandUpdate.getText());
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void searchByRole(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (roleUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!RoleDAO.dbCheckIfExistRole(roleUpdate.getText())) {
                    JOptionPane.showMessageDialog(null, "There no such role with id: " + roleUpdate.getText());
                    return;
                }
                ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.searchByRole(roleUpdate.getText());
                populateTable(users_brand_roles);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAll(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Users_brand_roles> users_brand_roles = Users_brand_rolesDAO.getAllUserBrandRole();
        populateTable(users_brand_roles);
    }


}
