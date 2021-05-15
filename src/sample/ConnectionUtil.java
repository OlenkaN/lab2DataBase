package sample;



//import javax.sql.rowset.CachedRowSet;
//import javax.sql.rowset.RowSetProvider;
import sample.model.User;

import java.sql.*;
import javax.swing.*;

public class ConnectionUtil {
    public static Connection conn = null;
    public static final String JDBS_DRIVER = "com.mysql.jdbc.Driver";
    public static final String connStr = "jdbc:mysql://localhost:3306/lab2db";

    public static Connection connectdb() {
        try {
            Class.forName(JDBS_DRIVER);
            conn = (Connection) DriverManager.getConnection(connStr, "root", "olenka221366");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throw throwables;
        }
    }

    /*public static ResultSet dbExecute(String sqlStmt) throws SQLException {
        Statement stmt=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;
        try {

            connectdb();
            stmt = conn.createStatement();
            resultSet=stmt.executeQuery(sqlStmt);
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(resultSet);
        }
        catch (SQLException e)
        {
            System.out.println("Problems with dbExecute operation"+e);
            throw e;
        }
        finally {
            if(resultSet!=null)
            {
                resultSet.close();
            }
            if(stmt!=null)
            {
                stmt.close();
            }
            dbDisconnect();
        }
        return resultSet;


    }*/
}