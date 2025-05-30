/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mamy
 */

/**
 * Utility class for establishing a connection to the MySQL database.
 */
public class database {

    /**
     * Establishes and returns a connection to the employee database.
     *
     * @return a {@link Connection} object if the connection is successful, otherwise {@code null}
     */
    public static Connection connectdb() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the "employee" database with username "root" and no password
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "");
            return connect;

        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        }

        // Return null if connection fails
        return null;
    }
}

