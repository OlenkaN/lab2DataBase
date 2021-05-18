package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.Products;
import sample.model.Requests;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestsDAO {
    public static ObservableList<Requests> dbRequest1(Integer volume) throws SQLException {
        String request = "SELECT collection.year_of_creation, product.volume" +
                " FROM collection" +
                " INNER JOIN product ON collection.id = product.collection_id" +
                " where product.volume > "+volume+" ;";
        System.out.println(request);
        ResultSet resultSet=ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setYearProperty(resultSet.getInt("year_of_creation"));
                requests1.setVolumeProperty(resultSet.getInt("volume"));
                requests.add(requests1);
            }
            System.out.println(request.length());
            return requests;

        } catch (SQLException e) {
            System.out.println("Error occurred while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Requests> dbRequest2(Integer year) throws SQLException {
        String request = "SELECT DISTINCT users.name, users.surname ,brand.year_of_foundation" +
                " FROM users, brand, users_brand_roles" +
                " where(((users.id)=users_brand_roles.user_id) AND ((brand.id)=users_brand_roles.brand_id) AND ((brand.year_of_foundation) > "+year+"));";
        System.out.println(request);
        ResultSet resultSet=ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setYearProperty(resultSet.getInt("year_of_foundation"));
                requests1.setNameProperty(resultSet.getString("name"));
                requests1.setSurnameProperty(resultSet.getString("surname"));
                requests.add(requests1);
            }
            System.out.println(request.length());
            return requests;

        } catch (SQLException e) {
            System.out.println("Error occurred while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
