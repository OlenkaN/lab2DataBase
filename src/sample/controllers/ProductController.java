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
import sample.dao.CollectionDAO;
import sample.dao.ProductDAO;
import sample.model.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProductController {

    @FXML
    private TextField nameProduct;

    @FXML
    private TextField volumeProduct;

    @FXML
    private TextField collection_id;


    @FXML
    private TableView<Products> productsTable;

    @FXML
    private TableColumn<Products, Integer> colProductId;

    @FXML
    private TableColumn<Products, String> colProductName;

    @FXML
    private TableColumn<Products, Integer> coproductsVolume;

    @FXML
    private TableColumn<Products, Integer> colCollectionId;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField volumeUpdate;

    @FXML
    private TextField collectionUpdate;

    @FXML
    private TextField idProduct;
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
    void deleteProduct(ActionEvent actionEvent) throws SQLException {
        if (idProduct.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(idProduct.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + idProduct.getText());
                    return;
                }
                ProductDAO.dbDeleteProduct(Integer.parseInt(idProduct.getText()));
                ObservableList<Products> productsObservableList = ProductDAO.getAllProducts();
                populateTable(productsObservableList);
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
    public void insertProduct(ActionEvent actionEvent) throws SQLException {
        if (nameProduct.getText().equals("") || volumeProduct.getText().equals("") || collection_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (!CollectionDAO.dbCheckIfExistCollection(Integer.parseInt(collection_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such collection with id: " + collection_id.getText());
                    return;
                }
                ProductDAO.dbInsertProduct(nameProduct.getText(), Integer.parseInt(volumeProduct.getText()), Integer.parseInt(collection_id.getText()));
                ObservableList<Products> productsObservableList = ProductDAO.getAllProducts();
                populateTable(productsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id,volume should be integer numbers");

            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }


    }

    @FXML
    public void updateProduct(ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("") || volumeUpdate.getText().equals("") || collectionUpdate.getText().equals("") || idProduct.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(idProduct.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + idProduct.getText());
                    return;
                }
                if (!CollectionDAO.dbCheckIfExistCollection(Integer.parseInt(collectionUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such collection with id: " + collectionUpdate.getText());
                    return;
                }
                ProductDAO.dbUpdateProduct(Integer.parseInt(idProduct.getText()),
                        nameUpdate.getText(),
                        Integer.parseInt(volumeUpdate.getText()),
                        Integer.parseInt(collectionUpdate.getText()));
                ObservableList<Products> productsObservableList = ProductDAO.getAllProducts();
                populateTable(productsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Volume ,collection,id should be integer numbers");
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }

    @FXML
    private void initialize() throws Exception {
        colProductId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colProductName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        coproductsVolume.setCellValueFactory(cellData -> cellData.getValue().volumePropertyProperty().asObject());
        colCollectionId.setCellValueFactory(cellData -> cellData.getValue().collectionIdPropertyProperty().asObject());
        ObservableList<Products> productsObservableList = ProductDAO.getAllProducts();
        populateTable(productsObservableList);
    }

    private void populateTable(ObservableList<Products> productsObservableList) {
        productsTable.setItems(productsObservableList);
    }

    @FXML
    private void searchProducts(ActionEvent actionEvent) throws SQLException {
        if (idProduct.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!ProductDAO.dbCheckIfExistProduct(Integer.parseInt(idProduct.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such product with id: " + idProduct.getText());
                    return;
                }
                ObservableList<Products> collectionsObservableList = ProductDAO.searchProducts(idProduct.getText());
                populateTable(collectionsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAllProduct(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Products> productsObservableList = ProductDAO.getAllProducts();
        populateTable(productsObservableList);
    }
}
