/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SAM
 */
public class DBConnection {

    public static Connection getConnectionPostgres() throws Exception {

       //Online Database Heroku
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://ec2-52-215-22-82.eu-west-1.compute.amazonaws.com:5432/dcp1madep62ah3?sslmode=require";
                Connection conn = DriverManager.getConnection(url, "xdbclqyeafclrb", "0961698bab96b15477229c7686b5598da85b35387127bc9c98e3012e5429260f");
                return conn;
    }
}
