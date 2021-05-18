package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button Users;

    @FXML
    private Button Product;

    @FXML
    private Button Brands;

    @FXML
    private Button Roles;

    @FXML
    private Button Collection;

    @FXML
    private Button Category;



    @FXML
    void role(ActionEvent actionEvent) {
        Stage stage = (Stage) Roles.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/role.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Role");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void user(ActionEvent actionEvent) {
        Stage stage = (Stage) Users.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/user.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("User");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void product(ActionEvent actionEvent) {
        Stage stage = (Stage) Product.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/product.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Product");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void category(ActionEvent actionEvent) {
        Stage stage = (Stage) Category.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/category.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void brand(ActionEvent actionEvent) {
        Stage stage = (Stage) Brands.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/brand.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Brand");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void collection(ActionEvent actionEvent) {
        Stage stage = (Stage) Collection.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/collection.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Collection");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void category_products(ActionEvent actionEvent) {
        Stage stage = (Stage) Brands.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/category_product.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Category and Product");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void user_brand_roles(ActionEvent actionEvent) {
        Stage stage = (Stage) Brands.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/users_brand_roles.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("User brand role");
        stage.setScene(new Scene(root));
        stage.show();
    }


}


