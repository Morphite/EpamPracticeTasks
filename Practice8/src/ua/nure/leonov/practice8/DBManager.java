package ua.nure.leonov.practice8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    Connection conn;
    private static DBManager manager;

    public DBManager() throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?" +
                "user=root&password=root");
    }

    public void insertUser(User user) {
        User temp = user;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO users (login) VALUES ('"+temp.getLogin()+"');",
                    Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            temp.setUser_id(rs.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static DBManager getInstance() throws SQLException {
        if (manager == null)
            manager = new DBManager();
        return manager;
    }

    public List findAllUsers() {
        ArrayList list = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()){
                list.add(new User(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
