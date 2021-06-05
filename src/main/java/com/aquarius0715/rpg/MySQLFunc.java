package com.aquarius0715.rpg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLFunc {

    String HOST;
    String DB;
    String USER;
    String PASS;
    String PORT;
    private Connection con = null;
    private Statement st = null;
    private boolean closed = false;

    public MySQLFunc(String host, String db, String user, String pass, String port) {
        this.HOST = host;
        this.DB = db;
        this.USER = user;
        this.PASS = pass;
        this.PORT = port;
    }

    public Connection open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT +"/" + this.DB + "?useSSL=false", this.USER, this.PASS );
            this.st = con.createStatement();
            return this.con;
        } catch (SQLException var2) {
            System.out.println("Could not connect to MySQL server, error code: " + var2.getErrorCode());
        } catch (ClassNotFoundException var3) {
            System.out.println("JDBC driver was not found in this machine.");
        }
        return this.con;
    }

    public boolean checkConnection() {
        return this.con != null;
    }

    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closed = true;
    }

    public Statement getSt() {
        return st;
    }

    public boolean isClosed() {
        return closed;
    }

}