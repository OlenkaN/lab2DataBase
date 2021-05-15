package sample.model;

import sample.ConnectionUtil;
import sample.constRequest;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAO {


    public static void dbInsertUser(User user) throws SQLException {

        PreparedStatement stmt = null;
        String user_id = user.getMarriage() == null ? "NULL" : user.getMarriage().toString();
        Integer id;
        try {

            ConnectionUtil.connectdb();
            String insertUser = "insert into users(name,surname,gender,birthday,user_id) " +
                    "values('" + user.getName() + "','" + user.getSurname() + "','"
                    + user.getGender() + "','" + user.getBirthday() + "'," + user_id + ");";
            stmt = ConnectionUtil.conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
                if (!user_id.equals("NULL")) {
                    dbUpdateUserMarriage(id, user_id);
                }

            }


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

    public static void dbUpdateUser(User user) throws SQLException {

        PreparedStatement stmt = null;
        String user_id = user.getMarriage() == null ? "NULL" : user.getMarriage().toString();
        try {
            String updateUser = String.format("update users  " +
                    "set name='" + user.getName() + "'," +
                    "surname='" + user.getSurname() + "'," +
                    "gender='" + user.getGender() + "'," +
                    "birthday='" + user.getBirthday() + "'," +
                    "user_id=" + user_id + " where id = " + user.getId() + ";");

            ConnectionUtil.connectdb();
            stmt = ConnectionUtil.conn.prepareStatement(updateUser);
            System.out.println(stmt);
            stmt.executeUpdate();
            dbUpdateUserMarriage(user.getId(), user_id);
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

    public static void dbUpdateUserMarriage(Integer id, String marriage_id) throws SQLException {

        PreparedStatement stmt = null;

        try {

            ConnectionUtil.connectdb();
            String updateUserMarrige = "update users  " +
                    " set user_id=" + id + " where id =" + marriage_id + ";";
            stmt = ConnectionUtil.conn.prepareStatement(updateUserMarrige);

            System.out.println(stmt);
            stmt.executeUpdate();
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

    public static boolean dbCheckIfExistUser(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT * FROM users WHERE id =" + id + ";";
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

    public static boolean dbCheckIftUserIsMarried(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String checkIdExist = "SELECT user_id FROM users WHERE id =" + id + ";";
            stmt = ConnectionUtil.conn.prepareStatement(checkIdExist);

            return stmt.executeQuery().next();

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

    public static void dbDeleteUser(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        try {

            ConnectionUtil.connectdb();
            String deleteUser = "DELETE FROM users WHERE id =" + id + ";";
            stmt = ConnectionUtil.conn.prepareStatement(deleteUser);

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
}
