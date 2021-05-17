package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.dao.BrandDAO;
import sample.dao.CollectionDAO;
import sample.model.*;

import javax.swing.*;
import java.sql.SQLException;

public class CollectionController {

    @FXML
    private TextField nameCollection;

    @FXML
    private TextField yearCollection;

    @FXML
    private TextField brand_id;

    @FXML
    private Button AddCollection;

    @FXML
    private TableView<Collections> collectionTable;

    @FXML
    private TableColumn<Collections, Integer> colCollectionId;

    @FXML
    private TableColumn<Collections,String> colCollectionName;

    @FXML
    private TableColumn<Collections, Integer> colCollectionYear;

    @FXML
    private TableColumn<Collections, Integer> colBrand_ID;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField yearUpdate;

    @FXML
    private TextField brandUpdate;

    @FXML
    private TextField idCollection;

    @FXML
    void deleteCollection(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (idCollection.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!CollectionDAO.dbCheckIfExistCollection(Integer.parseInt(idCollection.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such collection with id: " + idCollection.getText());
                    return;
                }
                CollectionDAO.dbDeleteCollection(Integer.parseInt(idCollection.getText()));
                ObservableList<Collections> collectionsObservableList = CollectionDAO.getAllCollection();
                populateTable(collectionsObservableList);
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
    public void insertCollection(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (nameCollection.getText().equals("") || yearCollection.getText().equals("") || brand_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(brand_id.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + brand_id.getText());
                    return;
                }
                CollectionDAO.dbInsertCollection(nameCollection.getText(), Integer.parseInt(yearCollection.getText()), Integer.parseInt(brand_id.getText()));
                ObservableList<Collections> collectionsObservableList = CollectionDAO.getAllCollection();
                populateTable(collectionsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id,year,brand should be integer numbers");

            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }


    }

    @FXML
    public void updateCollection(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("") || yearUpdate.getText().equals("") || brandUpdate.getText().equals("") || idCollection.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!CollectionDAO.dbCheckIfExistCollection(Integer.parseInt(idCollection.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such collection with id: " + idCollection.getText());
                    return;
                }
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(brandUpdate.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + brand_id.getText());
                    return;
                }
                CollectionDAO.dbUpdateCollection(Integer.parseInt(idCollection.getText()),
                        Integer.parseInt(yearUpdate.getText()),
                        nameUpdate.getText(),
                        Integer.parseInt(brandUpdate.getText()));
                ObservableList<Collections> collectionsObservableList = CollectionDAO.getAllCollection();
                populateTable(collectionsObservableList);
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
        colCollectionId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colCollectionName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colCollectionYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty().asObject());
        colBrand_ID.setCellValueFactory(cellData -> cellData.getValue().brandIdPropertyProperty().asObject());
        ObservableList<Collections> collectionsObservableList = CollectionDAO.getAllCollection();
        populateTable(collectionsObservableList);
    }

    private void populateTable(ObservableList<Collections> collectionsObservableList) {
        collectionTable.setItems(collectionsObservableList);
    }

    @FXML
    private void searchCollection(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (idCollection.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!CollectionDAO.dbCheckIfExistCollection(Integer.parseInt(idCollection.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such collection with id: " + idCollection.getText());
                    return;
                }
                ObservableList<Collections> collectionsObservableList = CollectionDAO.searchCollection(idCollection.getText());
                populateTable(collectionsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAllCollection(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Collections> collectionsObservableList = CollectionDAO.getAllCollection();
        populateTable(collectionsObservableList);
    }


}

