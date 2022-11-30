package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoFactory {
	private String url;
    private String username;
    private String password;
    private static final Logger logger 
	= LoggerFactory.getLogger(DaoFactory.class);
    private Properties props = new Properties();
    
	DaoFactory() {
		
		try {
			props.load(DaoFactory.class.getResourceAsStream("/Application.properties"));
		} catch (IOException e) {
			logger.info("Cannot read properties file");
		}
		
        this.url = props.getProperty("databaseURL");
        this.username = props.getProperty("databaseUsername");
        this.password = props.getProperty("databasePassword");
    }
    
    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        DaoFactory instance = new DaoFactory();
        return instance;
    }

    public java.sql.Connection getConnection() throws SQLException {
    	java.sql.Connection connexion= DriverManager.getConnection(url, username, password);
    	System.out.println(url);
    	connexion.setAutoCommit(false);
        return connexion;
    }
}
