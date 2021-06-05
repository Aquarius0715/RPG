package com.aquarius0715.rpg;

import java.util.Scanner;

public class Login {

    MySQLManager sql = new MySQLManager("login");

    public static void login() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("username: ");
        String username = scanner.next();
        System.out.print("password: ");
        String password = scanner.next();



    }

}
