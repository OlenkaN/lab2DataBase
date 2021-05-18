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
import sample.model.Categories;
import sample.dao.CategoryDAO;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class CategoryController {

    @FXML
    private TextField nameCategory;

    @FXML
    private TableView<Categories> categoryTable;

    @FXML
    private TableColumn<Categories, Integer> colCategoryId;

    @FXML
    private TableColumn<Categories, String> colCategoryName;

    @FXML
    private TableColumn<Categories, String> colCategoryDescription;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField descriptionUpdate;

    @FXML
    private TextField descritionCategory;

    @FXML
    private TextField idCategory;
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
    void deleteCategory(ActionEvent actionEvent) throws SQLException {
        if (idCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(idCategory.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + idCategory.getText());
                    return;
                }
                CategoryDAO.dbDeleteCategory(Integer.parseInt(idCategory.getText()));
                ObservableList<Categories> categoriesObservableList = CategoryDAO.getAllCategory();
                populateTable(categoriesObservableList);
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
    public void insertCategory(ActionEvent actionEvent) throws SQLException {
        if (nameCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                CategoryDAO.dbInsertCategory(nameCategory.getText(), descritionCategory.getText());
                ObservableList<Categories> categoriesObservableList = CategoryDAO.getAllCategory();
                populateTable(categoriesObservableList);
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }


    }

    @FXML
    public void updateCategory(ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("") || idCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(idCategory.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + idCategory.getText());
                    return;
                }
                CategoryDAO.dbUpdateCategoty(Integer.parseInt(idCategory.getText()),nameUpdate.getText(), descriptionUpdate.getText());
                ObservableList<Categories> categoriesObservableList = CategoryDAO.getAllCategory();
                populateTable(categoriesObservableList);
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
    private void initialize() throws Exception {
        colCategoryId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colCategoryName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colCategoryDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionPropertyProperty());
        ObservableList<Categories> categoriesObservableList = CategoryDAO.getAllCategory();
        populateTable(categoriesObservableList);
    }

    private void populateTable(ObservableList<Categories> categoriesObservableList) {
        categoryTable.setItems(categoriesObservableList);
    }

    @FXML
    private void searchCategory(ActionEvent actionEvent) throws SQLException {
        if (idCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(idCategory.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + idCategory.getText());
                    return;
                }
                ObservableList<Categories> categoriesObservableList =CategoryDAO.searchCategory(idCategory.getText());
                populateTable(categoriesObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAllCategories(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Categories> categoriesObservableList = CategoryDAO.getAllCategory();
        populateTable(categoriesObservableList);
    }


}
