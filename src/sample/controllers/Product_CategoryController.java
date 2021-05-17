package sample.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.dao.*;
import sample.model.Category_Products;
import sample.model.Collections;

import javax.swing.*;
import java.sql.SQLException;

public class Product_CategoryController {

    @FXML
    private TextField category_id;

    @FXML
    private TextField product_id;

    @FXML
    private Button Add;

    @FXML
    private TableView<Category_Products> productCategoryTable;

    @FXML
    private TableColumn<Category_Products, Integer> colProduct_CategoryId;

    @FXML
    private TableColumn<Category_Products, String> colProductName;

    @FXML
    private TableColumn<Category_Products, Integer> colProductId;

    @FXML
    private TableColumn<Category_Products, String> colCategoryName;

    @FXML
    private TableColumn<Category_Products, Integer> colCategoryId;

    @FXML
    private TextField categoryUpdate;

    @FXML
    private TextField productUpdate;

    @FXML
    private TextField id;


    @FXML
    void delete(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!Product_CategoryDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }
                Product_CategoryDAO.dbDeleteConnection(Integer.parseInt(id.getText()));
                ObservableList<Category_Products> category_products = Product_CategoryDAO.getAllCategoryProducts();
                populateTable(category_products);
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
    public void insertProductCategory(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (product_id.getText().equals("") || category_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(category_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + category_id.getText());
                    return;
                }
                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(product_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + product_id.getText());
                    return;
                }

                    Product_CategoryDAO.dbInsertConnection(Integer.parseInt(category_id.getText()), Integer.parseInt(product_id.getText()));
                    ObservableList<Category_Products> category_products = Product_CategoryDAO.getAllCategoryProducts();
                    populateTable(category_products);
                }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id,year,brand should be integer numbers");

            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }


    @FXML
    public void update(ActionEvent actionEvent) throws SQLException {
        if (productUpdate.getText().equals("") || categoryUpdate.getText().equals("") || id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!Product_CategoryDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }
                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(productUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + productUpdate.getText());
                    return;
                }
                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(categoryUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + categoryUpdate.getText());
                    return;
                }
                Product_CategoryDAO.dbUpdateConnection(Integer.parseInt(id.getText()),
                        Integer.parseInt(categoryUpdate.getText()),
                        Integer.parseInt(productUpdate.getText()));
                ObservableList<Category_Products> category_products = Product_CategoryDAO.getAllCategoryProducts();
                populateTable(category_products);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Year ,brand,id should be integer numbers");
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }

    @FXML
    private void initialize() throws Exception {
        colProduct_CategoryId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colCategoryName.setCellValueFactory(cellData -> cellData.getValue().nameCategoryPropertyProperty());
        colProductName.setCellValueFactory(cellData -> cellData.getValue().nameProductPropertyProperty());
        colCategoryId.setCellValueFactory(cellData -> cellData.getValue().categoryIdPropertyProperty().asObject());
        colProductId.setCellValueFactory(cellData -> cellData.getValue().productIdPropertyProperty().asObject());
        ObservableList<Category_Products> category_products = Product_CategoryDAO.getAllCategoryProducts();
        populateTable(category_products);
    }

    private void populateTable(ObservableList<Category_Products> collectionsObservableList) {
        productCategoryTable.setItems(collectionsObservableList);
    }

    @FXML
    private void searchByID(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!Product_CategoryDAO.dbCheckIfExist(Integer.parseInt(id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such connection with id: " + id.getText());
                    return;
                }
                ObservableList<Category_Products> category_products = Product_CategoryDAO.searchCategoryProducts(id.getText());
                populateTable(category_products);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void searchByProduct(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (productUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(productUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + productUpdate.getText());
                    return;
                }
                ObservableList<Category_Products> category_products = Product_CategoryDAO.searchByProduct(productUpdate.getText());
                populateTable(category_products);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void searchbyCategory(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (categoryUpdate.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!CategoryDAO.dbCheckIfExistCategory(Integer.parseInt(categoryUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such category with id: " + categoryUpdate.getText());
                    return;
                }
                ObservableList<Category_Products> category_products = Product_CategoryDAO.searchByCategory(categoryUpdate.getText());
                populateTable(category_products);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAll(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Category_Products> category_products = Product_CategoryDAO.getAllCategoryProducts();
        populateTable(category_products);
    }

}

