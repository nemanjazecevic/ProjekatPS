/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import entities.IDomenskiObjekat;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class Broker {
    private Connection connection;
    private String pass;
    private String drive;
    private String url;
    private String user;
    

    public Broker() {
       this.postavka();

    }
    
    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void postavka() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config/db.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.url = properties.getProperty("url");
            this.drive = properties.getProperty("driver");
            this.user = properties.getProperty("user");
            this.pass = properties.getProperty("password");
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
     public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                this.connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }


   

    public Long insertObject(IDomenskiObjekat idomenskiObjekat) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + idomenskiObjekat.nazivTabeleBaza()+ " (" + idomenskiObjekat.naziviKolonaUnosBaza() + ")" + " VALUES ("+ idomenskiObjekat.vrednostiUnosBaza() + ")";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return -1l;
    }

    
    public void deleteObject(IDomenskiObjekat idomenskiobjekat) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM "+ idomenskiobjekat.nazivTabeleBaza()+ " WHERE " +idomenskiobjekat.whereUslovBaza();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
     public List<IDomenskiObjekat> getObjects(IDomenskiObjekat idomenskiobjekat) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + idomenskiobjekat.nazivTabeleBazaPlus() + " "+ idomenskiobjekat.joinUslovBaza()+ ";";
            ResultSet resultSet = statement.executeQuery(query);
            return idomenskiobjekat.objektiBaza(resultSet);
        } catch (SQLException ex) {
            throw ex;
        }
    }
     
    public void updateObject(IDomenskiObjekat idomenskiobjekat) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + idomenskiobjekat.nazivTabeleBaza() + " SET " + idomenskiobjekat.vrednostiIzmenaBaza()+ " WHERE " + idomenskiobjekat.whereUslovBaza();
            if(statement.executeUpdate(query) == 0) throw new SQLException("Greska update");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
