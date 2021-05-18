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
import sample.dao.RequestsDAO;
import sample.model.Collections;
import sample.model.Products;
import sample.model.Requests;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class RequestController {

    @FXML
    private TextField parameter;

    @FXML
    private TableView<Requests> table;

    @FXML
    private TableColumn<Requests, Integer> colYear;

    @FXML
    private TableColumn<Requests, Integer> colVolume;
    @FXML
    private TableColumn<Requests, String> colName;

    @FXML
    private TableColumn<Requests, String> colSurname;

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
    void search1(ActionEvent event) throws SQLException {
        colVolume.setCellValueFactory(cellData -> cellData.getValue().volumePropertyProperty().asObject());
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty().asObject());
        if (parameter.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                ObservableList<Requests> requests = RequestsDAO.dbRequest1(Integer.parseInt(parameter.getText()));
                populateTable(requests);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Parameter should be integer numbers");
            } catch (SQLException throwables) {
                System.out.println("Error occurred while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }
    }

    @FXML
    void search2(ActionEvent event) throws SQLException {
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        colSurname.setCellValueFactory(cellData -> cellData.getValue().surnamePropertyProperty());
        if (parameter.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There some empty fields that should be initialized");
        } else {
            try {
                ObservableList<Requests> requests = RequestsDAO.dbRequest2(Integer.parseInt(parameter.getText()));
                populateTable(requests);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Parameter should be integer numbers");
            } catch (SQLException throwables) {
                System.out.println("Error occurred while insert the records in DB" + throwables);
                throwables.printStackTrace();
                throw throwables;
            }

        }
    }

    private void populateTable(ObservableList<Requests> collectionsObservableList) {
        table.setItems(collectionsObservableList);
    }

    /*@FXML
    private void initialize() throws Exception {
        try {
            colVolume.setCellValueFactory(cellData -> cellData.getValue().volumePropertyProperty().asObject());

            colYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty().asObject());
            colName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
            colSurname.setCellValueFactory(cellData -> cellData.getValue().surnamePropertyProperty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/

}