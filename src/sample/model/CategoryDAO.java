package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {
    public static boolean dbCheckIfExistCategory(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM category WHERE id =" + id + ";";
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

    public static void dbInsertCategory(String name, String description) throws SQLException {

        String descriptionNew=description.equals("")?" , NULL":",'"+description+"'";
        String insertCategory = "insert into category(name, description) " +
                "values('" + name + "'" + descriptionNew + ");";
        ConnectionUtil.dbExcecuteQuery(insertCategory);
    }

    public static void dbUpdateCategoty(Integer id, String name, String description) throws SQLException {
        String descriptionNew=description.equals("")?"NULL ":"'"+description+"'";
        String updateCategory = String.format("update category  " +
                "set name='" + name + "'," +
                "description=" + descriptionNew + "where id = " + id + ";");
        ConnectionUtil.dbExcecuteQuery(updateCategory);
    }

    public static void dbDeleteCategory(Integer id) throws SQLException {
        String deleteCategory = "DELETE FROM category WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteCategory);
    }

    public static ObservableList<Categories> getAllCategory() throws SQLException {
        String sql = "select * from category;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Categories> categoriesObservableList = getCategoriesObjects(resultSet);
            return categoriesObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Categories> getCategoriesObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Categories> categoriesObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Categories categories = new Categories();
                categories.setIdProperty(resultSet.getInt("id"));
                categories.setNameProperty(resultSet.getString("name"));
                categories.setDescriptionProperty(resultSet.getString("description"));
                categoriesObservableList.add(categories);
            }
            return categoriesObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Categories> searchCategory(String id) throws SQLException {
        try {
            String searchUser = "select * from category where id=" + id;
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Categories> categoriesObjects= getCategoriesObjects(resultSet);
            return categoriesObjects;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
