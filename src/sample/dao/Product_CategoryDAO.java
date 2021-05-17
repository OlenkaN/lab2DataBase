package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.Categories;
import sample.model.Category_Products;
import sample.model.Collections;
import sample.model.Products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product_CategoryDAO {
    public static boolean dbCheckIfExist(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM category_product WHERE id =" + id + ";";
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

    public static void dbInsertConnection( Integer category, Integer product) throws SQLException {


        String insert = "insert into category_product(category_id, product_id) " +
                "values('"  +category+"','" + product + "');";
        ConnectionUtil.dbExcecuteQuery(insert);
    }

    public static void dbUpdateConnection(Integer id, Integer category, Integer product) throws SQLException {
        String update = String.format("update category_product  " +
                "set category_id='" + category + "'," +
                "product_id='"+product+ " 'where id = " + id + ";");
        ConnectionUtil.dbExcecuteQuery(update);
    }

    public static void dbDeleteConnection(Integer id) throws SQLException {
        String deleteBrand = "DELETE FROM category_product WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteBrand);
    }

    public static ObservableList<Category_Products> getAllCategoryProducts() throws SQLException {
        String sql = "select * from category_product;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Category_Products> category_products = getCategoryProductsObjects(resultSet);
            return category_products;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Category_Products> getCategoryProductsObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Category_Products> category_productsObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Category_Products category_products = new Category_Products();
                category_products.setIdProperty(resultSet.getInt("id"));
                category_products.setCategoryIdProperty(resultSet.getInt("category_id"));
                category_products.setProductIdProperty(resultSet.getInt("product_id"));
                ObservableList<Products> products=ProductDAO.searchProducts(""+resultSet.getInt("product_id"));
                category_products.setNameProductProperty(products.get(0).getNameProperty());

                ObservableList<Products> productsObservableList=ProductDAO.searchProducts(""+resultSet.getInt("product_id"));
                category_products.setNameProductProperty(productsObservableList.get(0).getNameProperty());

                ObservableList<Categories> categoriesObservableList=CategoryDAO.searchCategory(""+resultSet.getInt("category_id"));
                category_products.setNameCategoryProperty(categoriesObservableList.get(0).getNameProperty());
                category_productsObservableList.add(category_products);
            }
            return category_productsObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Category_Products> searchCategoryProducts(String id) throws SQLException {
        try {
            String searchUser = "select * from category_product where id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Category_Products> category_products= getCategoryProductsObjects(resultSet);
            return category_products;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Category_Products> searchByCategory(String id) throws SQLException {
        try {
            String searchUser = "select * from category_product where category_id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Category_Products> category_products= getCategoryProductsObjects(resultSet);
            return category_products;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Category_Products> searchByProduct(String id) throws SQLException {
        try {
            String searchUser = "select * from category_product where product_id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Category_Products> category_products= getCategoryProductsObjects(resultSet);
            return category_products;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
