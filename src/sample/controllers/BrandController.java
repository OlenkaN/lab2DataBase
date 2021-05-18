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
import sample.dao.BrandDAO;
import sample.model.Brands;


import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


public class BrandController {

    @FXML
    private TextField nameBrand;

    @FXML
    private TextField nameUpdate;

    @FXML
    private TextField yearUpdate;

    @FXML
    private TextField yearBrand;

    @FXML
    private TextField idBrand;
    @FXML
    private TableColumn<Brands, Integer> colBrandId;
    @FXML
    private TableColumn<Brands, String> colBrandName;
    @FXML
    private TableColumn<Brands, Integer> colBrandYear;
    @FXML
    private TableView brandTable;
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
    void deleteBrand(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (idBrand.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Id field is empty and should be initialized");
        } else {
            try {

                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(idBrand.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + idBrand.getText());
                    return;
                }
                BrandDAO.dbDeleteBrand(Integer.parseInt(idBrand.getText()));
                ObservableList<Brands> brandsObservableList = BrandDAO.getAllBrand();
                populateTable(brandsObservableList);
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
    public void insertBrand(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (nameBrand.getText().equals("") || yearBrand.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                BrandDAO.dbInsertBrand(nameBrand.getText(), Integer.parseInt(yearBrand.getText()));
                ObservableList<Brands> brandsObservableList = BrandDAO.getAllBrand();
                populateTable(brandsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Year should be integer number");

            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }


    }

    @FXML
    public void updateBrand(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (nameUpdate.getText().equals("") || yearUpdate.getText().equals("") || idBrand.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {

                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(idBrand.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + idBrand.getText());
                    return;
                }
                BrandDAO.dbUpdateBrand(Integer.parseInt(idBrand.getText()), Integer.parseInt(yearUpdate.getText()), nameUpdate.getText());
                ObservableList<Brands> brandsObservableList = BrandDAO.getAllBrand();
                populateTable(brandsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Year  and id should be integer number");
            } catch (SQLException throwables) {
                System.out.println("Error occured while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }
        }

    }

    @FXML
    private void initialize() throws Exception {
        colBrandId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colBrandName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colBrandYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty().asObject());
        ObservableList<Brands> brandsObservableList = BrandDAO.getAllBrand();
        populateTable(brandsObservableList);
    }

    private void populateTable(ObservableList<Brands> brandsObservableList) {
        brandTable.setItems(brandsObservableList);
    }

    @FXML
    private void searchBrand(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (idBrand.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There id field is empty and that should be filled");
        } else {
            try {
                if (!BrandDAO.dbCheckIfExistBrand(Integer.parseInt(idBrand.getText()))) {
                    JOptionPane.showMessageDialog(null, "There no such brand with id: " + idBrand.getText());
                    return;
                }
                ObservableList<Brands> brandsObservableList = BrandDAO.searchBrand(idBrand.getText());
                populateTable(brandsObservableList);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id should be integer number");
            }
        }
    }

    @FXML
    private void SearchAllBrands(javafx.event.ActionEvent actionEvent) throws SQLException {
        ObservableList<Brands> brandsObservableList = BrandDAO.getAllBrand();
        populateTable(brandsObservableList);
    }

}
