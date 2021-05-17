package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {
    public static boolean dbCheckIfExistRole(String role) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM roles WHERE name = '" + role + "';";
            System.out.println(checkIdExist);
            stmt = ConnectionUtil.conn.prepareStatement(checkIdExist);
            System.out.println(stmt);
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

    public static void dbInsertRole(String name, String description) throws SQLException {

        String descriptionNew=description.equals("")?" , NULL":",'"+description+"'";
        String insertRole = "insert into roles(name, description) " +
                "values('" + name + "'" + descriptionNew + ");";
        ConnectionUtil.dbExcecuteQuery(insertRole);
    }

    public static void dbUpdateRole( String name, String description) throws SQLException {
        String descriptionNew=description.equals("")?"NULL ":"'"+description+"'";
        String updateRole = String.format("update roles  " +
                "set description=" + descriptionNew + "where name = '" + name + "';");
        ConnectionUtil.dbExcecuteQuery(updateRole);
    }

    public static void dbDeleteRole(String name) throws SQLException {
        String deleteRole = "DELETE FROM roles WHERE name ='" + name + "';";
        ConnectionUtil.dbExcecuteQuery(deleteRole);
    }

    public static ObservableList<Roles> getAllRoles() throws SQLException {
        String sql = "select * from roles;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Roles> rolesObservableList = getRolesObjects(resultSet);
            return rolesObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Roles> getRolesObjects(ResultSet resultSet) throws SQLException {
        try {
            ObservableList<Roles> rolesObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Roles roles = new Roles();
                roles.setNameProperty(resultSet.getString("name"));
                roles.setDescriptionProperty(resultSet.getString("description"));
                rolesObservableList.add(roles);
            }
            return rolesObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Roles> searchRole(String name) throws SQLException {
        try {
            String searchUser = "select * from roles where name='" + name+"';";
            ResultSet resultSet = ConnectionUtil.dbExecute(searchUser);
            ObservableList<Roles> rolesObjects= getRolesObjects(resultSet);
            return rolesObjects;
        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }
}
