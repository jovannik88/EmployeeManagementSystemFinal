// Admin.java
package employeemanagementsystem;


import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Concrete class representing an administrator
 * Inherits from User and implements CRUD operations for employees
 */
public class Admin extends User{
    
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

  
}