/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.sql.Date;

/**
 *
 * @author mamy
 */

public class EmployeeData {

    private Integer employeeId;
    private String firstName;
    private String lastname;
    private int age;
    private String gender;
    private int phoneNumber;
    private String position;
    private Date date;
    private double salary;

    // Partial constructor without salary
    public EmployeeData(Integer employeeId, String firstName, String lastname, int age, String gender, int phoneNumber, String position, Date date) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.date = date;
    }

    // Minimal constructor with only some fields
    public EmployeeData(Integer employeeId, String firstName, String lastname, String position, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.position = position;
        this.salary = salary;
    }

    //Full constructor
    public EmployeeData(int employeeId, String firstName, String lastName, int age, String gender,
            int phoneNumber, String position, Date date, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastname = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.date = date;
        this.salary = salary;
    }

    //Getters and setters
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public Date getDate() {
        return date;
    }

}
