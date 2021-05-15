package sample;

public class constRequest {
    public static final String checkIdExist = "SELECT * FROM users WHERE id = ?";
    public static final String insertUser = "insert into users(name,surname,gender,birthday,user_id) " +
            "values(?,?,?,?%s);";
    public static final String updateUser = "update users  " +
            "set name=?, " +
            "surname=?," +
            "gender=?," +
            "birthday=?," +
            "user_id=? where id = ?; ";
    public static final String updateUserMarrige = "update users  " +
            " set user_id=? where id = ?; ";


}
