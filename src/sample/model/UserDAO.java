package sample.model;

import sample.ConnectionUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class UserDAO {
    public static void insertEmployee(String name, String surname, String gender, LocalDate birthday, Integer marrige) throws SQLException {
        String sql = "insert into users(name,surname,gender,birthday) values('" + name + "','" + surname + "','" + gender + "','" + birthday + "');";
        try {
            ConnectionUtil.dbExcecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Exception occur while inserting the data" + e);
            throw e;
        }
    }
}
