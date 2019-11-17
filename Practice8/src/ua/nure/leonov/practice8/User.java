package ua.nure.leonov.practice8;

import java.sql.PreparedStatement;

public class User {

    private int user_id;
    private String login;

    public User(int user_id, String login) {
        this.user_id = user_id;
        this.login = login;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static User createUser(String login){
        return new User(0, login);
    }

    @Override
    public String toString() {
        return login;
    }
}
