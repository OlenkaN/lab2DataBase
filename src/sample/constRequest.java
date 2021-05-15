package sample;

public class constRequest {
    public static final String checkIdExist = "SELECT * FROM users WHERE id = ?";
    public static final String insertUser = "insert into users(name,surname,gender,birthday%s) " +
            "values(?,?,?,?%s);";
    public static final String updateUser = "update users  " +
            "set name = ?, " +
            "set surname = ?," +
            "set gender = ?," +
            "set birthday = ?," +
            "set user_id = ? where id = ? ";


}
