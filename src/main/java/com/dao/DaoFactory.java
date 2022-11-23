package com.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
    private String username;
    private String password;
    
	DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/villes?useUnicode=true&characterEncoding=utf8", "root", "");
        return instance;
    }

    public java.sql.Connection getConnection() throws SQLException {
    	java.sql.Connection connexion= DriverManager.getConnection(url, username, password);
    	connexion.setAutoCommit(false);
        return connexion;
    }
}
