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
                " where product.volume > " + volume + " ;";
        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
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
                " where(((users.id)=users_brand_roles.user_id) AND ((brand.id)=users_brand_roles.brand_id) AND ((brand.year_of_foundation) > " + year + "));";
        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
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

    public static ObservableList<Requests> dbRequest3(String sex) throws SQLException {
        String request =
                " SELECT DISTINCT X.name  " +
                        " FROM brand AS X" +
                        " WHERE NOT EXISTS" +
                        " ( SELECT * FROM users INNER JOIN users_brand_roles ON  (users.id)=users_brand_roles.user_id " +
                        "WHERE users_brand_roles.brand_id = X.id AND users.gender <> '" + sex + "');";

        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setNameProperty(resultSet.getString("name"));
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

    public static ObservableList<Requests> dbRequest4(String product) throws SQLException {
        String request =
                " SELECT X.name " +
                        "FROM product AS X" +
                        " WHERE NOT EXISTS" +
                        "(SELECT Y.category_id " +
                        "FROM product " +
                        "INNER JOIN category_product AS Y" +
                        " ON Y.product_id = product.id " +
                        "WHERE product.name = '" + product + "' AND " +
                        "NOT EXISTS( SELECT * FROM category_product " +
                        "WHERE category_product.product_id = X.id AND category_product.category_id = Y.category_id));";
        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setNameProperty(resultSet.getString("name"));
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

    public static ObservableList<Requests> dbRequest5(String name) throws SQLException {
        String request = "SELECT product.name, product.volume " +
                "FROM collection " +
                "INNER JOIN product" +
                " ON collection.id = product.collection_id" +
                " WHERE collection.name ='" + name + "';";
        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setNameProperty(resultSet.getString("name"));
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

    public static ObservableList<Requests> dbRequest6(String name, String surname) throws SQLException {
        String request = " SELECT DISTINCT roles.name, roles.description " +
                "FROM roles" +
                " WHERE roles.name NOT IN" +
                "( SELECT users_brand_roles.roles_name " +
                "FROM users_brand_roles " +
                "INNER JOIN users " +
                "ON users_brand_roles.id =  users.id " +
                "WHERE users.surname = '" + surname + "'" +
                "AND users.name='" + name + "');";
        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setNameProperty(resultSet.getString("name"));
                requests1.setSurnameProperty(resultSet.getString("description"));
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

    public static ObservableList<Requests> dbRequest7(String name) throws SQLException {
        String request = " SELECT X.name" +
                " FROM brand AS X " +
                "WHERE NOT EXISTS " +
                "(SELECT Y.roles_name" +
                " FROM users_brand_roles AS Y " +
                "WHERE Y.brand_id = X.id AND NOT EXISTS " +
                "( SELECT * FROM users_brand_roles " +
                "INNER JOIN brand" +
                " ON users_brand_roles.brand_id = brand.id" +
                " WHERE users_brand_roles.roles_name = Y.roles_name AND brand.name = '" + name + "'));";

        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                requests1.setNameProperty(resultSet.getString("name"));
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

    public static ObservableList<Requests> dbRequest8(Integer volume) throws SQLException {
        String request = " SELECT X.name " +
                "FROM category as X" +
                " WHERE NOT EXISTS ("+
                "SELECT * " +
                "FROM product AS Y " +
                " WHERE Y.volume > " + volume + " AND NOT EXISTS (" +
                " SELECT *" +
                " FROM category_product" +
                " WHERE category_product.category_id = X.id AND category_product.product_id =Y.id));";

        System.out.println(request);
        ResultSet resultSet = ConnectionUtil.dbExecute(request);
        System.out.println(resultSet);

        try {
            ObservableList<Requests> requests = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Requests requests1 = new Requests();
                System.out.println(resultSet.getString("name"));
                requests1.setNameProperty(resultSet.getString("name"));
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
