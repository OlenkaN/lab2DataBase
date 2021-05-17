package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.Products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {

    public static boolean dbCheckIfExistProduct(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM product WHERE id =" + id + ";";
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

    public static void dbInsertProduct(String name, Integer volume, Integer collectoion_id) throws SQLException {


        String insertProduct = "insert into product(name,volume,collection_id) " +
                "values('" + name + "','" + volume + "','" + collectoion_id + "');";
        ConnectionUtil.dbExcecuteQuery(insertProduct);
    }

    public static void dbUpdateProduct(Integer id, String name, Integer volume, Integer collection_id) throws SQLException {
        String updateProduct = String.format("update product  " +
                "set name='" + name + "'," +
                "volume='" + volume + "'," +
                "collection_id='" + collection_id + " 'where id = " + id + ";");
        ConnectionUtil.dbExcecuteQuery(updateProduct);
    }

    public static void dbDeleteProduct(Integer id) throws SQLException {
        String deleteProduct = "DELETE FROM product WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteProduct);
    }

    public static ObservableList<Products> getAllProducts() throws SQLException {
        String sql = "select * from product;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Products> productsObservableList = getProductsObjects(resultSet);
            return productsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Products> getProductsObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Products> productsObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Products products = new Products();
                products.setIdProperty(resultSet.getInt("id"));
                products.setNameProperty(resultSet.getString("name"));
                products.setVolumeProperty(resultSet.getInt("volume"));
                products.setCollectionIdProperty(resultSet.getInt("collection_id"));
                productsObservableList.add(products);
            }
            return productsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Products> searchProducts(String id) throws SQLException {
        try {
            String searchUser = "select * from product where id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Products> productsObservableList = getProductsObjects(resultSet);
            return productsObservableList;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}

