/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mamy
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button logout;

    @FXML
    private TextField addEmployee_LastName;

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private TextField addEmployee_age;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_LastName;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_age;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_dataMember;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_id;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_phoneNumber;

    @FXML
    private TableColumn<EmployeeData, String> addEmployee_col_posistion;

    @FXML
    private TableView<EmployeeData> addEmployee_col_tableauView;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private TextField addEmployee_employeeId;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private TextField addEmployee_phoneNumber;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button add_employeeBtn;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployee;

    @FXML
    private Label home_totalInactiveEmployee;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Button salary_btn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private TableView<EmployeeData> salary_tableView;
    @FXML
    private TableColumn<EmployeeData, String> salary_col_employeeID;

    @FXML
    private TableColumn<EmployeeData, String> salary_col_firstName;

    @FXML
    private TableColumn<EmployeeData, String> salary_col_lastName;

    @FXML
    private TableColumn<EmployeeData, String> salary_col_posistion;

    @FXML
    private TableColumn<EmployeeData, String> salary_col_salary;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private TableView<EmployeeData> ShowAllEmployee_view;
    @FXML
    private Button showAllEmployee_btn;

    @FXML
    private AnchorPane showAllEmployee_form;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_LastName1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_age1;
    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_dataMember1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_dataMember11;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_firstName1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_gender1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_id1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_phoneNumber1;

    @FXML
    private TableColumn<EmployeeData, String> showEmployee_col_posistion1;
    
    @FXML
    private AnchorPane welcome_form;
    
    @FXML
    private Button back_add;

    @FXML
    private Button back_home;

    @FXML
    private Button back_salary;

    @FXML
    private Button back_showAll;

    @FXML
    private Button showEmployeeSearch;


    @FXML
    private Button showResetSearch;



    //@FXML
   // private Button search_showAll;

    @FXML
    private Label username;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private HashMap<Integer, EmployeeData> employeeMap = new HashMap<>();
    private TreeSet<EmployeeData> employeeTreeSet = new TreeSet<>(Comparator.comparingInt(EmployeeData::getEmployeeId));

    /**
     * Updates an existing employee's information in the database.
     * It updates both 'employee_data' and 'employee_info' tables,
     * preserving the salary while updating other fields.
     */
    public void addEmployeeUpdate() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "UPDATE employee_data SET first_name= '"
                + addEmployee_firstName.getText() + "', last_name= '"
                + addEmployee_LastName.getText() + "', age = '"
                + addEmployee_age.getText() +  "', gender = '"
                + addEmployee_gender.getSelectionModel().getSelectedItem() + "', position= '"
                + addEmployee_position.getSelectionModel().getSelectedItem() + "', phone_number= '"
                + addEmployee_phoneNumber.getText() + "', date = '" + sqlDate + "' WHERE employee_id= '"
                + addEmployee_employeeId.getText() + "'";

        connect = database.connectdb();
        try {

            Alert alert;
            if (addEmployee_firstName.getText().isEmpty()
                    || addEmployee_LastName.getText().isEmpty()
                    || addEmployee_employeeId.getText().isEmpty()
                    || addEmployee_age.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNumber.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + addEmployee_employeeId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    double salary = 0;
                    String checkData = "SELECT * FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeId.getText() + "'";
                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();
                    while (result.next()) {
                        salary = result.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName= '"
                            + addEmployee_firstName.getText() + "', LastName = '"
                            + addEmployee_LastName.getText() + "', position = '"
                            + addEmployee_position.getSelectionModel().getSelectedItem()
                            + "',salary = '" + salary + "',date = '" + sqlDate + "' WHERE employee_id = '"
                            + addEmployee_employeeId.getText() + "'";
                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an existing employee from the database.
     * It removes the employee record from both 'employee_data' and 'employee_info' tables
     * using the provided employee ID.
     * The method also deletes the corresponding entry from the local HashMap 'employeeMap'.
     */
    public void addEmployeeDelete() {
        // SQL query to delete employee from the primary table
        String sql = "DELETE FROM employee_data WHERE employee_id= '"
                + addEmployee_employeeId.getText() + "'";

        // Establish database connection
        connect = database.connectdb();

        try {
            Alert alert;

            // Check if the Employee ID field is empty
            if (addEmployee_employeeId.getText().isEmpty()) {
                // Show error alert if the ID field is blank
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the Employee ID to delete.");
                alert.showAndWait();
                return;
            }

            // Parse the employee ID and ensure it's a valid integer
            int employeeId;
            try {
                employeeId = Integer.parseInt(addEmployee_employeeId.getText());
            } catch (NumberFormatException e) {
                // Show error alert if ID format is invalid
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Employee ID format.");
                alert.showAndWait();
                return;
            }

            // Check if the employee exists in the local HashMap
            if (!employeeMap.containsKey(employeeId)) {
                // Alert if employee ID does not exist in memory
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Employee ID not found in system.");
                alert.showAndWait();
                return;
            }

            // Ask for confirmation before deletion
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Employee ID: " + employeeId + "?");
            Optional<ButtonType> option = alert.showAndWait();

            // If user confirms the deletion
            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                // Delete employee from 'employee_data' table
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                // Also delete related data from 'employee_info' table
                String deleteInfo = "DELETE FROM employee_info WHERE employee_id= '" + employeeId + "'";
                prepare = connect.prepareStatement(deleteInfo);
                prepare.executeUpdate();

                // Remove employee from local HashMap
                employeeMap.remove(employeeId);

                // Show success message
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Refresh the table view and reset the form
                addEmployeeShowListData();
                addEmployeeReset();
            }

        } catch (Exception e) {
            // Print any exception for debugging
            e.printStackTrace();
        }
    }

    /**
     * Adds a new employee to the system.
     * This method inserts the employee's data into both the 'employee_data' and 'employee_info' tables.
     * It also adds the new employee to the local HashMap 'employeeMap' for in-memory tracking.
     * Duplicate Employee IDs are checked in both the database and local memory before insertion.
     */
    public void addEmployeeAdd() {
        // Get the current date for database insertion
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        // SQL statement to insert data into the employee_data table
        String sql = "INSERT INTO employee_data ("
                + "employee_id, first_name, last_name, age, gender, phone_number, position, date"
                + ") VALUES (?,?,?,?,?,?,?,?)";

        // Establish a database connection
        connect = database.connectdb();

        try {
            Alert alert;

            // Check if any required fields are empty
            if (addEmployee_firstName.getText().isEmpty()
                    || addEmployee_LastName.getText().isEmpty()
                    || addEmployee_employeeId.getText().isEmpty()
                    || addEmployee_age.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNumber.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null) {

                // Show error if any field is empty
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                // Convert employee ID to integer and check if it already exists in the local map
                int employeeId = Integer.parseInt(addEmployee_employeeId.getText());
                if (employeeMap.containsKey(employeeId)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + employeeId + " already exists");
                    alert.showAndWait();
                    return;
                }

                // Check if employee ID already exists in the database
                String check = "SELECT employee_id FROM employee_data WHERE employee_id= '"
                        + addEmployee_employeeId.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    // Employee ID already exists in database
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeId.getText() + " already exists");
                    alert.showAndWait();

                } else {
                    // Insert new employee into the employee_data table
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeId.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_LastName.getText());
                    prepare.setString(4, addEmployee_age.getText());
                    prepare.setString(5, addEmployee_gender.getSelectionModel().getSelectedItem().toString());
                    prepare.setString(6, addEmployee_phoneNumber.getText());
                    prepare.setString(7, addEmployee_position.getSelectionModel().getSelectedItem().toString());
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    // Also insert initial salary and additional info into employee_info table
                    String insertInfo = "INSERT INTO employee_info"
                            + "(employee_id,firstName,LastName,position,salary,date)"
                            + "VALUES(?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addEmployee_employeeId.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_LastName.getText());
                    prepare.setString(4, addEmployee_position.getSelectionModel().getSelectedItem().toString());
                    prepare.setString(5, "0.0"); // Default salary
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    // Create and add the new employee to the local HashMap
                    EmployeeData newEmployee = new EmployeeData(
                            employeeId,
                            addEmployee_firstName.getText(),
                            addEmployee_LastName.getText(),
                            Integer.parseInt(addEmployee_age.getText()),
                            addEmployee_gender.getSelectionModel().getSelectedItem().toString(),
                            Integer.parseInt(addEmployee_phoneNumber.getText()),
                            addEmployee_position.getSelectionModel().getSelectedItem().toString(),
                            null // image (if used)
                    );
                    employeeMap.put(employeeId, newEmployee);

                    // Show success message
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Refresh the employee table view and reset the form
                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }
        } catch (SQLException e) {
            // Handle SQL-related errors
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Error while adding employee: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Clears all input fields in the Add Employee form.
     * This ensures the form is reset and ready for a new entry.
     */
    public void addEmployeeReset() {
        addEmployee_employeeId.setText("");
        addEmployee_firstName.setText("");
        addEmployee_LastName.setText("");
        addEmployee_age.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_phoneNumber.setText("");
        addEmployee_position.getSelectionModel().clearSelection();
    }

    // Predefined list of employee positions
    private final String[] positionList = {
            "Marketer Coordinator",
            "Web Developer (Front-End)",
            "Web Developer (Back-End)",
            "App Developer"
    };
    /**
     * Populates the Position ComboBox with a predefined list of positions.
     * These positions are defined in the 'positionList' array.
     */
    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();
        for (String data : positionList) {
            listP.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listP);
        addEmployee_position.setItems(listData);
    }


    // Predefined gender options for employees
    private String[] listGender = {
            "Male",
            "Female",
            "Others"
    };
    /**
     * Populates the Gender ComboBox with a predefined list of gender options.
     * These options are defined in the 'listGender' array.
     */
    public void addEmployeeGender() {
        List<String> listG = new ArrayList<>();
        for (String data : listGender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);
    }
    /**
     * Retrieves all employee data from the database.
     * @return An ObservableList of EmployeeData from the employee_data table.
     */
    public ObservableList<EmployeeData> addEmployeeListData() {
        ObservableList<EmployeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee_data";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                EmployeeData employeeD = new EmployeeData(
                        result.getInt("employee_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getInt("phone_number"),
                        result.getString("position"),
                        result.getDate("date")
                );

                listData.add(employeeD);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employee data: " + e.getMessage());
            e.printStackTrace();
        }
        return listData;
    }


    // ObservableList to store and manage the employee data shown in the TableView
    private ObservableList<EmployeeData> addEmployeeList;

    /**
     * Initializes the TableView with employee data.
     * This method retrieves the list of employees from the database
     * and binds each column of the TableView to a corresponding property
     * of the EmployeeData model.
     */
    public void addEmployeeShowListData() {
        // Retrieve the employee data and store it in the observable list
        addEmployeeList = addEmployeeListData();

        // Bind each table column to the corresponding field in the EmployeeData model
        addEmployee_col_id.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_LastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        addEmployee_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addEmployee_col_posistion.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_dataMember.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Set the observable list into the TableView
        addEmployee_col_tableauView.setItems(addEmployeeList);
    }

    /**
     * Handles the selection of an employee from the TableView.
     * When a row is selected, the corresponding employee's data is loaded
     * into the form fields for viewing or editing.
     */
    public void addEmployeeSelect() {
        // Get the selected employee object from the TableView
        EmployeeData employeeD = addEmployee_col_tableauView.getSelectionModel().getSelectedItem();
        int index = addEmployee_col_tableauView.getSelectionModel().getSelectedIndex();

        // If no valid row is selected, exit the method
        if (index < 0 || employeeD == null) {
            return;
        }

        // Populate the input fields with the selected employee's details
        addEmployee_employeeId.setText(String.valueOf(employeeD.getEmployeeId()));
        addEmployee_firstName.setText(employeeD.getFirstName());
        addEmployee_LastName.setText(employeeD.getLastname());
        addEmployee_age.setText(String.valueOf(employeeD.getAge()));
        addEmployee_phoneNumber.setText(String.valueOf(employeeD.getPhoneNumber()));
    }


//-------------------------------------------------------------------------------------------
    // Field EmployeeSalary

    /**
     * SALARY UPDATE
     * This method updates the salary of an employee in the database based on the provided employee ID.
     * It checks if all necessary fields are filled before executing the update.
     */
    public void salaryUpdate() {
        // SQL query to update the salary for the given employee ID
        String sql = "UPDATE employee_info SET salary = '" + salary_salary.getText()
                + "' WHERE employee_id = '" + salary_employeeID.getText() + "'";

        connect = database.connectdb();

        try {
            Alert alert;

            // Check if the required fields are not empty
            if (salary_col_employeeID.getText().isEmpty()
                    || salary_firstName.getText().isEmpty()
                    || salary_lastName.getText().isEmpty()
                    || salary_position.getText().isEmpty()) {

                // Show error message if fields are missing
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();

            } else {
                // Execute the SQL update query
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                // Show success message
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated");
                alert.showAndWait();

                // Refresh the table/list with updated data
                salaryShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * SALARY RESET
     * This method clears all the input fields in the salary section of the form.
     * It is used to reset the form to its default empty state.
     */
    public void salaryReset() {
        salary_employeeID.setText("");
        salary_firstName.setText("");
        salary_lastName.setText("");
        salary_position.setText("");
        salary_salary.setText("");
    }
    /**
     * Displays the list of employee salary records in the TableView.
     *
     * This method retrieves salary data from the database, sorts it by employee ID
     * using a TreeSet, and then binds the sorted list to the TableView.
     * Each column is associated with a corresponding property from the EmployeeData model.
     */
    private ObservableList<EmployeeData> salaryList;
    private TreeSet<EmployeeData> salaryTreeSet; // Ajoutez cette déclaration en haut de votre classe

    public void salaryShowListData() {
        // Retrieve unsorted salary data from the database
        ObservableList<EmployeeData> unsortedList = salaryListData();

        // Use a TreeSet to automatically sort employees by ID (ascending order)
        salaryTreeSet = new TreeSet<>(Comparator.comparingInt(EmployeeData::getEmployeeId));
        salaryTreeSet.addAll(unsortedList);

        // Convert the sorted TreeSet into an ObservableList for TableView binding
        salaryList = FXCollections.observableArrayList(salaryTreeSet);

        // Set up the column-cell bindings with EmployeeData model properties
        salary_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        salary_col_posistion.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Load the sorted data into the TableView
        salary_tableView.setItems(salaryList);
    }

    /**
     * Handles the selection of an employee in the salary TableView.
     *
     * When a user clicks on a row in the TableView, this method extracts the employee's
     * information and fills the corresponding fields in the salary form.
     */
    public void salarySelect() {
        // Get the selected employee from the TableView
        EmployeeData selectedEmployee = salary_tableView.getSelectionModel().getSelectedItem();
        int selectedIndex = salary_tableView.getSelectionModel().getSelectedIndex();

        // If no valid row is selected, exit the method
        if (selectedIndex < 0 || selectedEmployee == null) {
            return;
        }

        // Fill in the salary form fields with the selected employee's data
        salary_employeeID.setText(String.valueOf(selectedEmployee.getEmployeeId()));
        salary_firstName.setText(selectedEmployee.getFirstName());
        salary_lastName.setText(selectedEmployee.getLastname());
        salary_position.setText(selectedEmployee.getPosition());
        salary_salary.setText(String.valueOf(selectedEmployee.getSalary()));
    }

    /**
     * Retrieves all employee salary records from the 'employee_info' table.
     *
     * This method queries the database and constructs a list of EmployeeData objects,
     * each containing relevant salary details for display in the UI.
     *
     * @return ObservableList<EmployeeData> the list of employee salary records
     */
    public ObservableList<EmployeeData> salaryListData() {
        // Create an observable list to store salary data
        ObservableList<EmployeeData> salaryDataList = FXCollections.observableArrayList();

        // SQL query to fetch employee salary information
        String sql = "SELECT * FROM employee_info";

        // Connect to the database
        connect = database.connectdb();

        try {
            // Prepare and execute the SQL statement
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            // Iterate through the result set and create EmployeeData objects
            while (result.next()) {
                EmployeeData employee = new EmployeeData(
                        result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("LastName"),
                        result.getString("position"),
                        result.getDouble("salary")
                );
                // Add the employee to the list
                salaryDataList.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching salary data: " + e.getMessage());
            e.printStackTrace();
        }

        // Return the list of employee salary data
        return salaryDataList;
    }


    //------------------------------------------------------------------------------------------------------
    // field Home_form
    /**
     * Retrieves the total number of employees from the 'employee_data' table
     * and updates the `home_totalEmployee` label in the UI.
     */
    public void homeTotalEmployees() {
        final String sql = "SELECT COUNT(id) AS total FROM employee_data";
        int totalEmployees = 0;

        // Establish database connection
        connect = database.connectdb();

        try {
            // Prepare and execute SQL query
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            // Retrieve the total count
            if (result.next()) {
                totalEmployees = result.getInt("total");
            }

            // Display the result in the UI
            home_totalEmployee.setText(String.valueOf(totalEmployees));

        } catch (SQLException e) {
            System.err.println("Error retrieving total employee count: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Counts all employees from the 'employee_info' table
     * who have a salary greater than 0 (i.e., considered present/active),
     * and updates the `home_totalPresents` label in the UI.
     */
    public void homeEmployeeTotalPresent() {
        final String sql = "SELECT COUNT(id) AS total FROM employee_info WHERE salary > 0";
        int totalPresentEmployees = 0;

        connect = database.connectdb();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                totalPresentEmployees = result.getInt("total");
            }

            home_totalPresents.setText(String.valueOf(totalPresentEmployees));

        } catch (SQLException e) {
            System.err.println("Error counting present employees: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Counts all employees from the 'employee_info' table
     * who have a salary equal to 0.0 (i.e., considered inactive),
     * and updates the `home_totalInactiveEmployee` label in the UI.
     */
    public void homeTotalInactive() {
        final String sql = "SELECT COUNT(id) AS total FROM employee_info WHERE salary = 0.0";
        int totalInactiveEmployees = 0;

        connect = database.connectdb();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                totalInactiveEmployees = result.getInt("total");
            }

            home_totalInactiveEmployee.setText(String.valueOf(totalInactiveEmployees));

        } catch (SQLException e) {
            System.err.println("Error counting inactive employees: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //---------------------------------------------------------------------------------------------
    // Field ShowAllEmployee_form

    /**
     * Retrieves a complete list of all employees with detailed information by performing
     * an inner join between 'employee_data' and 'employee_info' using 'employee_id'.
     *
     * @return ObservableList<EmployeeData> containing full employee records including salary.
     */
    public ObservableList<EmployeeData> showAllEmployeeListData() {
        ObservableList<EmployeeData> listData = FXCollections.observableArrayList();

        final String sql = """
        SELECT ed.employee_id, ed.first_name, ed.last_name, ed.age, ed.gender,
               ed.phone_number, ed.position, ed.date, ei.salary
        FROM employee_data ed
        JOIN employee_info ei ON ed.employee_id = ei.employee_id
    """;

        connect = database.connectdb();

        try (PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                EmployeeData emp = new EmployeeData(
                        result.getInt("employee_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("age"),
                        result.getString("gender"),
                        result.getInt("phone_number"),
                        result.getString("position"),
                        result.getDate("date"),
                        result.getDouble("salary")
                );
                listData.add(emp);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching employee data: " + e.getMessage());
            e.printStackTrace();
        }

        return listData;
    }

    /**
     * Displays all employees in a TableView sorted by employee ID using a TreeSet.
     * Data is retrieved via showAllEmployeeListData(), sorted, and then displayed.
     */
    public void showAllEmployeeDisplay() {
        // Get unsorted employee list
        ObservableList<EmployeeData> unsortedList = showAllEmployeeListData();

        // Sort using TreeSet based on employee ID
        TreeSet<EmployeeData> sortedEmployees = new TreeSet<>(Comparator.comparingInt(EmployeeData::getEmployeeId));
        sortedEmployees.addAll(unsortedList);

        // Convert to ObservableList
        ObservableList<EmployeeData> sortedList = FXCollections.observableArrayList(sortedEmployees);

        // Bind data to table columns
        showEmployee_col_id1.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        showEmployee_col_firstName1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        showEmployee_col_LastName1.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        showEmployee_col_age1.setCellValueFactory(new PropertyValueFactory<>("age"));
        showEmployee_col_gender1.setCellValueFactory(new PropertyValueFactory<>("gender"));
        showEmployee_col_phoneNumber1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        showEmployee_col_posistion1.setCellValueFactory(new PropertyValueFactory<>("position"));
        showEmployee_col_dataMember1.setCellValueFactory(new PropertyValueFactory<>("date"));
        showEmployee_col_dataMember11.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Display sorted list in TableView
        ShowAllEmployee_view.setItems(sortedList);
    }

    /**
     * Searches for an employee by their ID entered in the search text field.
     * If the employee is found, displays only their data in the TableView.
     * Otherwise, shows an appropriate alert message.
     */
    private void searchEmployeeById() {
        // Get the input text and remove leading/trailing whitespace
        String input = addEmployee_search.getText().trim();

        // Show a warning if the input is empty
        if (input.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Required", "Please enter an employee ID.");
            return;
        }

        try {
            // Try to convert input to an integer (employee ID)
            int searchId = Integer.parseInt(input);

            // Reload the full employee list from the database (ensures salary is included)
            ObservableList<EmployeeData> allEmployees = showAllEmployeeListData();

            // Find the employee with the given ID
            EmployeeData found = null;
            for (EmployeeData emp : allEmployees) {
                if (emp.getEmployeeId() == searchId) {
                    found = emp;
                    break;
                }
            }

            if (found != null) {
                // If found, wrap the single result in an ObservableList
                ObservableList<EmployeeData> resultList = FXCollections.observableArrayList(found);

                // Sort the list (even though it’s just one element) and bind it to the TableView
                SortedList<EmployeeData> sortedList = new SortedList<>(resultList);
                sortedList.comparatorProperty().bind(ShowAllEmployee_view.comparatorProperty());

                // Set the TableView to show only the found employee
                ShowAllEmployee_view.setItems(sortedList);

                // Show success alert
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee found!");
            } else {
                // If no employee matches, clear the TableView and show an error alert
                ShowAllEmployee_view.setItems(FXCollections.observableArrayList());
                showAlert(Alert.AlertType.ERROR, "Not Found", "Employee with ID " + searchId + " not found.");
            }

        } catch (NumberFormatException e) {
            // If the input is not a valid number, show an error alert
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
        }
    }

    /**
     * Utility method to show alert dialogs.
     *
     * @param type    The type of alert (INFORMATION, WARNING, ERROR, etc.).
     * @param title   The title of the alert dialog.
     * @param message The content/message of the alert dialog.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Main message
        alert.showAndWait(); // Display the alert and wait for user confirmation
    }


    /**
     * Resets the employee search and displays all employees in the TableView.
     * The data is reloaded from the database and sorted by employee ID in ascending order.
     * It also clears the search input field and ensures the salary data remains intact.
     */
    @FXML
    private void resetEmployeeSearch(ActionEvent event) {
        // Reload all employee records from the database
        ObservableList<EmployeeData> fullList = showAllEmployeeListData();

        // Sort the employee list by employee ID (ascending order)
        FXCollections.sort(fullList, Comparator.comparingInt(EmployeeData::getEmployeeId));

        // Update the TableView with the sorted employee list
        ShowAllEmployee_view.setItems(fullList);

        // Clear the search input field
        addEmployee_search.clear();

        // (Optional) Update employeeMap if used for search functionality
        employeeMap.clear();
        for (EmployeeData emp : fullList) {
            employeeMap.put(emp.getEmployeeId(), emp);
        }
    }




    //-----------------------------------------------------------------------------------------
    // Welcome_form
    // Get the username
    public void displayUsername() {
        username.setText(getData.username);
    }
    // SWITCH between different forms based on the button clicked
    public void switchForm(ActionEvent event) {
        // Hide all forms initially
        welcome_form.setVisible(false);
        home_form.setVisible(false);
        addEmployee_form.setVisible(false);
        salary_form.setVisible(false);
        showAllEmployee_form.setVisible(false);

        // Show the appropriate form based on which button was clicked
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            // Update home dashboard stats
            homeTotalEmployees();
            homeEmployeeTotalPresent();
            homeTotalInactive();

        } else if (event.getSource() == add_employeeBtn) {
            addEmployee_form.setVisible(true);
            // Prepare the add employee form with position and gender options and search functionality
            addEmployeePositionList();
            addEmployeeGender();
            searchEmployeeById();

        } else if (event.getSource() == salary_btn) {
            salary_form.setVisible(true);
            // Load and display salary data
            salaryShowListData();

        } else if (event.getSource() == showAllEmployee_btn) {
            showAllEmployee_form.setVisible(true);
            // Display the full list of employees
            showAllEmployeeDisplay();
        }
    }


    private double x = 0;
    private double y = 0;

    /**
     * Handles the back button action.
     * Hides all other forms and returns to the welcome page.
     */
    public void handleBack(ActionEvent event) {
        // Hide all forms
        home_form.setVisible(false);
        addEmployee_form.setVisible(false);
        salary_form.setVisible(false);
        showAllEmployee_form.setVisible(false);

        // Show the welcome form
        welcome_form.setVisible(true);
}

    //LOGOUT
    public void logout() {
        Locale.setDefault(Locale.ENGLISH);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> option = alert.showAndWait();

        try {
            if (option.isPresent() && option.get() == ButtonType.OK) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                root.setOnMousePressed((MouseEvent event) -> {

                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getSceneX() - x);
                    stage.setY(event.getSceneY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Closes the entire application.
     * <p>
     * This method calls {@code System.exit(0)} to terminate the application.
     * </p>
     */
    public void close() {
        System.exit(0);
    }

    /**
     * Minimizes the current window.
     * <p>
     * This method retrieves the current stage from {@code main_form} and minimizes it.
     * </p>
     */
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    /**
     * initialize
     * This method is automatically called when the controller is loaded.
     * It initializes the employee map from the database, displays sorted data in the TableView,
     * and sets up the forms and UI elements.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the HashMap for quick access by employee ID
    employeeMap = new HashMap<>();

    String sql = "SELECT * FROM employee_data";
    connect = database.connectdb();
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        // Populate the employee map from the database
        while (result.next()) {
            EmployeeData emp = new EmployeeData(
                result.getInt("employee_id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getInt("age"),
                result.getString("gender"),
                result.getInt("phone_number"),
                result.getString("position"),
                result.getDate("date")
            );
            employeeMap.put(emp.getEmployeeId(), emp);
        }
        // Sort and display employees in the TableView
        SortedList<EmployeeData> sortedEmployeeList = new SortedList<>(
            FXCollections.observableArrayList(employeeMap.values())
        );
        sortedEmployeeList.comparatorProperty().bind(ShowAllEmployee_view.comparatorProperty());
        ShowAllEmployee_view.setItems(sortedEmployeeList);

    } catch (Exception e) {
        e.printStackTrace();


    }



        // Call other initialization methods for UI components and dashboard
        displayUsername();   // Display logged-in username

        homeTotalEmployees(); // Count total employees
        homeEmployeeTotalPresent(); // Count present employees
        homeTotalInactive();  // Count employees with salary = 0.0

        addEmployeeShowListData();  // Load employees into the Add Employee table
        addEmployeePositionList();  // Load position choices into ComboBox
        addEmployeeGender();   // Load gender options into ComboBox

        salaryShowListData();  // Load salary table data
        showAllEmployeeDisplay(); // Display all employees sorted in main view

        // Bind search button to search logic
        showEmployeeSearch.setOnAction(event ->  searchEmployeeById());

        // Reset the search field on load (optional)
        resetEmployeeSearch(null);
    }

    private static class AlertType {

        private static Alert.AlertType CONFIRMATION;

        public AlertType() {
        }
    }

}
