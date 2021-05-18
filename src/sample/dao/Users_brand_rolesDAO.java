package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;
import sample.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users_brand_rolesDAO {
    public static boolean dbCheckIfExist(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM users_brand_roles WHERE id =" + id + ";";
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

    public static void dbInsertConnection(Integer user, Integer brand, String name) throws SQLException {


        String insert = "insert into users_brand_roles(user_id, brand_id,roles_name) " +
                "values('" + user + "','" + brand + "','" + name + "');";
        ConnectionUtil.dbExcecuteQuery(insert);
    }

    public static void dbUpdateConnection(Integer id, Integer user, Integer brand, String name) throws SQLException {
        String update = String.format("update users_brand_roles  " +
                "set user_id='" + user + "'," +
                "brand_id='" + brand + "'," +
                "roles_name='" + name +
                "' where id = " + id + ";");
        System.out.println(update);
        ConnectionUtil.dbExcecuteQuery(update);
    }

    public static void dbDeleteConnection(Integer id) throws SQLException {
        String deleteBrand = "DELETE FROM  users_brand_roles WHERE id ='" + id + "';";
        ConnectionUtil.dbExcecuteQuery(deleteBrand);
    }

    public static ObservableList<Users_brand_roles> getAllUserBrandRole() throws SQLException {
        String sql = "select * from users_brand_roles ;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Users_brand_roles> users_brand_roles = getUserBrandRolesObjects(resultSet);
            return users_brand_roles;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Users_brand_roles> getUserBrandRolesObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Users_brand_roles> users_brand_roles = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Users_brand_roles usersBrandRoles = new Users_brand_roles();
                usersBrandRoles.setIdProperty(resultSet.getInt("id"));
                usersBrandRoles.setBrandIdProperty(resultSet.getInt("brand_id"));
                usersBrandRoles.setUserIdProperty(resultSet.getInt("user_id"));
                usersBrandRoles.setNameRoleProperty(resultSet.getString("roles_name"));
                ObservableList<Brands> brands =BrandDAO.searchBrand("" + resultSet.getInt("brand_id"));
                usersBrandRoles.setNameBrandProperty(brands.get(0).getNameProperty());

                ObservableList<Users> users = UserDAO.searchUsers("" + resultSet.getInt("user_id"));
                usersBrandRoles.setNameUserProperty(users.get(0).getNameProperty());

                users_brand_roles.add(usersBrandRoles);
            }
            return users_brand_roles;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Users_brand_roles> searchUserBrandRoles(String id) throws SQLException {
        try {
            String search = "select * from users_brand_roles where id=" + id+";";
            ResultSet resultSet = ConnectionUtil.dbExecute(search);
            ObservableList<Users_brand_roles> users_brand_roles = getUserBrandRolesObjects(resultSet);
            return users_brand_roles;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Users_brand_roles> searchByUser(String id) throws SQLException {
        try {
            String search = "select * from users_brand_roles where user_id=" + id+";";
            ResultSet resultSet = ConnectionUtil.dbExecute(search);
            ObservableList<Users_brand_roles> users_brand_roles = getUserBrandRolesObjects(resultSet);
            return users_brand_roles;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Users_brand_roles> searchByBrand(String id) throws SQLException {
        try {
            String search = "select * from users_brand_roles where brand_id=" + id+";";
            System.out.println(search);
            ResultSet resultSet = ConnectionUtil.dbExecute(search);
            ObservableList<Users_brand_roles> users_brand_roles = getUserBrandRolesObjects(resultSet);
            return users_brand_roles;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static ObservableList<Users_brand_roles> searchByRole(String name) throws SQLException {
        try {
            String search = "select * from users_brand_roles where roles_name= '" + name+"';";
            System.out.println(search);
            ResultSet resultSet = ConnectionUtil.dbExecute(search);
            ObservableList<Users_brand_roles> users_brand_roles = getUserBrandRolesObjects(resultSet);
            return users_brand_roles;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
