package com.aquarius0715.rpg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MySQLManager {

    private boolean connected = false;
    private String conName;
    private boolean debugMode = false;
    private String HOST = "localhost";
    private String DB = "rpgdatabase";
    private String USER = "root";
    private String PASS = "mk871396";
    private String PORT = "3306";

    private HashMap<Integer, MySQLFunc> connects;

    /*
    コンストラクタ
     */

    public MySQLManager(String name) {
        this.connects = new HashMap<>();
        this.conName = name;
        this.connected = false;

        int result = Connect(HOST, DB, USER, PASS, PORT);
        if (result == -1) {
            System.out.println("Unable to establish a MySQL connection");
        }
    }

    /*
    接続
     */

    public int Connect(String host, String db, String user, String pass, String port) {
        this.HOST = host;
        this.DB = db;
        this.USER = user;
        this.PASS = pass;
        int data = connects.size() + 1;
        connects.put(connects.size() + 1, new MySQLFunc(host, db, user, pass, port));
        if (connects.get(data).open() == null) {
            System.out.println("failed to open MYSQL");
            return -1;
        }
        System.out.println("[" + this.conName + "] Connected to the database.");
        return data;
    }

    /*
    実行
     */

    public boolean execute(String query) {
        int data = connects.size() + 1;
        connects.put(connects.size() + 1, new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS, this.PORT));
        if (connects.get(data).open() == null) {
            System.out.println("failed to open MYSQL");
            return false;
        }
        boolean ret = true;
        if (debugMode) {
            System.out.println("execute: " + query);
        }
        try {
            connects.get(data).getSt().execute(query);
        } catch (SQLException var3) {
            System.out.println("[" + this.conName + "] Error executing query: " + var3.getErrorCode());
            System.out.println(query);
            ret = false;
        }
        connects.get(data).close();
        return ret;
    }

    /*
    クエリ
     */

    public Query query(String query) {
        int data = connects.size() + 1;
        connects.put(connects.size() + 1, new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS, this.PORT));
        if (connects.get(data).open() == null) {
            System.out.println("failed to open MYSQL");
            return null;
        }
        ResultSet resultSet = null;
        if (debugMode) {
            System.out.println("query: " + query);
        }
        try {
            resultSet = connects.get(data).getSt().executeQuery(query);
        } catch (SQLException var4) {
            System.out.println("[" + this.conName + "] Error executing query: " + var4.getErrorCode());
            System.out.println(query);
        }
        return new Query(resultSet, connects.get(data));
    }

    public static class Query {
        private ResultSet resultSet = null;
        private final MySQLFunc connect;

        public Query(ResultSet resultSet, MySQLFunc connect) {
            this.connect = connect;
            this.resultSet = resultSet;
        }

        public void close() {
            try {
                resultSet.close();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
