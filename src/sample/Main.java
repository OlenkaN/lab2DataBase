package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dao.RequestsDAO;
import sample.model.Requests;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        launch(args);

    }
}
