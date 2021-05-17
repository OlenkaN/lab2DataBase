package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.Collections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionDAO {
    public static boolean dbCheckIfExistCollection(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM collection WHERE id =" + id + ";";
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

    public static void dbInsertCollection(String name, Integer year, Integer brand_id) throws SQLException {


        String insertBrand = "insert into collection(name,brand_id, year_of_creation) " +
                "values('" + name + "','" +brand_id+"','" + year + "');";
        ConnectionUtil.dbExcecuteQuery(insertBrand);
    }

    public static void dbUpdateCollection(Integer id, Integer year, String name, Integer brand_id) throws SQLException {
        String updateBrand = String.format("update collection  " +
                "set name='" + name + "'," +
                "brand_id='"+brand_id+"',"+
                "year_of_creation='" + year + " 'where id = " + id + ";");
        ConnectionUtil.dbExcecuteQuery(updateBrand);
    }

    public static void dbDeleteCollection(Integer id) throws SQLException {
        String deleteBrand = "DELETE FROM collection WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteBrand);
    }

    public static ObservableList<Collections> getAllCollection() throws SQLException {
        String sql = "select * from collection;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Collections> collectionsObservableList = getCollectionsObjects(resultSet);
            return collectionsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Collections> getCollectionsObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Collections> collectionsObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Collections collection = new Collections();
                collection.setIdProperty(resultSet.getInt("id"));
                collection.setNameProperty(resultSet.getString("name"));
                collection.setBrandIdProperty(resultSet.getInt("brand_id"));
                collection.setYearProperty(resultSet.getInt("year_of_creation"));
                collectionsObservableList.add(collection);
            }
            return collectionsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Collections> searchCollection(String id) throws SQLException {
        try {
            String searchUser = "select * from collection where id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Collections> collectionsObservableList= getCollectionsObjects(resultSet);
            return collectionsObservableList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
