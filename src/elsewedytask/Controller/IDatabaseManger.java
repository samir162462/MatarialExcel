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
public interface IDatabaseManger {

    public static Connection getConnectionDerby() throws Exception {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby://localhost:5432/postgres";
        String user = "tom";
        String password = "secret";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
