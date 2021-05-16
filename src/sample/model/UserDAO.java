package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.ConnectionUtil;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


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
                    dbUpdateUserMarriage(id.toString(), user_id);
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


        String user_id = user.getMarriage() == null ? "NULL" : user.getMarriage().toString();
        String updateUser = String.format("update users  " +
                "set name='" + user.getName() + "'," +
                "surname='" + user.getSurname() + "'," +
                "gender='" + user.getGender() + "'," +
                "birthday='" + user.getBirthday() + "'," +
                "user_id=" + user_id + " where id = " + user.getId() + ";");
        ConnectionUtil.dbExcecuteQuery(updateUser);


    }

    public static void dbUpdateUserMarriage(String id, String marriage_id) throws SQLException {


        String updateUserMarrige = "update users  " +
                " set user_id=" + id + " where id =" + marriage_id + ";";
        ConnectionUtil.dbExcecuteQuery(updateUserMarrige);


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

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            return resultSet.getString("user_id") != null;


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

            String deleteUser = "DELETE FROM users WHERE id ='" + id + "';";
            if (dbCheckIftUserIsMarried(id)) {
                System.out.println("is married");
                String checkIdExist = "SELECT user_id FROM users WHERE id =" + id + ";";
                ConnectionUtil.connectdb();
                ResultSet resultSet = ConnectionUtil.conn.prepareStatement(checkIdExist).executeQuery();
                resultSet.next();
                String user_id = resultSet.getString("user_id");
                System.out.println(user_id);
                dbUpdateUserMarriage("NULL", user_id);
                System.out.println("is null");

            }
            ConnectionUtil.dbExcecuteQuery(deleteUser);

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

    public static ObservableList<Users> getAllRecords() throws SQLException {
        String sql = "select * from users;";
        try {
            ResultSet resultSet = ConnectionUtil.dbExecute(sql);
            ObservableList<Users> usersObservableList = getUsersObjects(resultSet);
            return  usersObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Users> getUsersObjects(ResultSet resultSet) throws SQLException {

        try {
            ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Users users = new Users();
                users.setIdProperty(resultSet.getInt("id"));
                users.setNameProperty(resultSet.getString("name"));
                users.setSurnameProperty(resultSet.getString("surname"));
                users.setGenderProperty(resultSet.getString("gender"));
                users.setBirthday((LocalDate) resultSet.getObject("birthday"));
                users.setUserProperty(resultSet.getInt("user_id"));
                usersObservableList.add(users);
            }
            return  usersObservableList;

        } catch (SQLException e) {
            System.out.println("Error occured while fetching the records from DB" + e);
            e.printStackTrace();
            throw e;
        }

    }
}
