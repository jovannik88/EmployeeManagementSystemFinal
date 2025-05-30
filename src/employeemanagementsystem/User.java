/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;


public abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return The user's role in the system
     */
    public abstract String getRole();

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
