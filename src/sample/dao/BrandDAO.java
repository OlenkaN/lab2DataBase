package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.Brands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class BrandDAO {
    public static boolean dbCheckIfExistBrand(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM brand WHERE id =" + id + ";";
            stmt = ConnectionUtil.conn.prepareStatement(checkIdExist);
            return (stmt.executeQuery().next());
        } catch (SQLException e) {
            System.out.println("Problems with dbExecuteQuery operation" + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            ConnectionUtil.dbDisconnect();
        }

    }

    public static void dbInsertBrand(String name, Integer year) throws SQLException {


            String insertBrand = "insert into brand(name, year_of_foundation) " +
                    "values('" + name + "','" + year + "');";
        ConnectionUtil.dbExcecuteQuery(insertBrand);
    }

    public static void dbUpdateBrand(Integer id, Integer year, String name) throws SQLException {
        String updateBrand = String.format("update brand  " +
                "set name='" + name + "'," +
                "year_of_foundation='" + year + " 'where id = " + id + ";");
        ConnectionUtil.dbExcecuteQuery(updateBrand);
    }

    public static void dbDeleteBrand(Integer id) throws SQLException {
        String deleteBrand = "DELETE FROM brand WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteBrand);
    }

    public static ObservableList<Brands> getAllBrand() throws SQLException {
        String sql = "select * from brand;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Brands> brandsObservableList = getBrandsObjects(resultSet);
            return brandsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Brands> getBrandsObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Brands> brandsObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Brands brands = new Brands();
                brands.setIdProperty(resultSet.getInt("id"));
                brands.setNameProperty(resultSet.getString("name"));
                brands.setYearProperty(resultSet.getInt("year_of_foundation"));
                brandsObservableList.add(brands);
            }
            return brandsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Brands> searchBrand(String id) throws SQLException {
        try {
            String searchUser = "select * from brand where id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Brands> brandsObservableList= getBrandsObjects(resultSet);
            return brandsObservableList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
