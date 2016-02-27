package main.setup;

import main.dao.CustomerDAO;

import java.sql.*;

/** Проект etaxi
 * * первоначальное создание базы данных MySQL
 */

public class DatabaseCreation {

        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static String DB_URL = "jdbc:mysql://localhost:3306/";  // 3311

        //  Database credentials
        static final String USER = "root";
        static final String PASS = "toor";

        public static void main(String[] args) {
            Connection connection = null;
            Statement stmt = null;
            try{
                //STEP 2:  Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //STEP 3:  Open a connection
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating...");
                stmt = connection.createStatement();
                String sql;

//              IF DATABASE EXIST  - DELETE
//              sql = "DROP DATABASE etaxi;";
//              stmt.executeUpdate(sql);

                sql = "CREATE DATABASE etaxi IF NOT EXISTS;";
                stmt.executeUpdate(sql);

                sql = "USE etaxi;";
                stmt.executeUpdate(sql);

                sql = "CREATE TABLE taxis IF NOT EXISTS (" +
                        "  taxiId int(6) NOT NULL auto_increment," +
                        "  name text," +
                        "  phone text," +
                        "  taxiStatus int(1)," +
                        "  location text," +
                        "  car text," +
                        "  login text," +
                        "  password text," +
                        "  rating double," +
                        "  PRIMARY KEY  (taxiId)" +
                        ");";
                stmt.executeUpdate(sql);


                CustomerDAO customerDao = new CustomerDAO(connection);
                customerDao.createTable();


                sql = "CREATE TABLE orders IF NOT EXISTS (" +
                        "  orderId int(6) NOT NULL auto_increment," +
                        "  customerId int(6)," +
                        "  datetime datetime," +
                        "  orderStatus int(1)," +
                        "  fromAdress text," +
                        "  toAdress text," +
                        "  taxiId int(6)," +
                        "  distance double," +
                        "  price double," +
                        "  rate int(1)," +
                        "  feedback text," +
                        "  PRIMARY KEY  (orderId)" +
                        ");";
                stmt.executeUpdate(sql);

                stmt.close();
                connection.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(connection!=null)
                        connection.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }//end main
    }//end FirstExample



