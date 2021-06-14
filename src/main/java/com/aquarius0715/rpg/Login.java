package com.aquarius0715.rpg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    static MySQLManager sql = new MySQLManager("login");

    public static boolean login() throws SQLException {

        for (int i = 0; i < 10 ; i++) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("username: ");
            String username = scanner.next();
            System.out.print("password: ");
            String password = scanner.next();

            boolean isExistRecode = isExistsRecode("SELECT EXISTS(SELECT USERNAME FROM LOGIN WHERE USERNAME = '" + username + "');");

            if (isExistRecode) {
                for (int ii = 0; ii < 10; ii++) {
                    if (checkPass(username, password)) {
                        System.out.println("Login successfully");
                        return true;
                    } else {
                        System.out.println("Login denied (mistake password)");
                    }
                }
            } else {
                System.out.println("Login denied (do not exists username)");
            }
        }
        return false;
    }

    public static boolean isExistsRecode(String query) throws SQLException {
        ResultSet resultSet = sql.query(query);
        return resultSet.next();
    }

    public static boolean createDefaultRecode() {
        String sql = "";
        return true;
    }

    public static boolean checkPass(String username, String password) throws SQLException {
        String query = "SELECT PASSWORD FROM LOGIN WHERE USERNAME = ' " + username + "';";
        ResultSet resultSet = sql.query(query);
        String pass = resultSet.getString("PASSWORD");

        return pass.equals(password);
    }

}
