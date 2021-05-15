package sample.model;

import sample.ConnectionUtil;
import sample.constRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class UserDAO {
    public static void dbInsertUser(User user) throws SQLException {

        PreparedStatement stmt = null;
        boolean flag = user.getMarrige() == null;
        try {

            ConnectionUtil.connectdb();
            stmt = ConnectionUtil.conn.prepareStatement(String.format(constRequest.insertUser, flag ? "" : ",user_id", flag ? "" : ",'" + user.getMarrige() + "'"));


            stmt.setString(1, user.getName());
            System.out.println("1");
            stmt.setString(2, user.getSurname());
            System.out.println("2");
            stmt.setString(3, user.getGender());
            System.out.println("3");
            stmt.setObject(4, user.getBirthday());
            System.out.println("4");
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
    public static void dbUpdateUser(User user) throws SQLException {

        PreparedStatement stmt = null;
        boolean flag = user.getMarrige() == null;
        try {

            ConnectionUtil.connectdb();
            stmt = ConnectionUtil.conn.prepareStatement(String.format(constRequest.insertUser, flag ? "" : ",user_id", flag ? "" : ",'" + user.getMarrige() + "'"));


            stmt.setString(1, user.getName());
            System.out.println("1");
            stmt.setString(2, user.getSurname());
            System.out.println("2");
            stmt.setString(3, user.getGender());
            System.out.println("3");
            stmt.setObject(4, user.getBirthday());
            System.out.println("4");
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
            stmt = ConnectionUtil.conn.prepareStatement(constRequest.checkIdExist);

            //stmt.setString(1, "users");
            stmt.setObject(1, id);
            return(stmt.executeQuery().next());
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
