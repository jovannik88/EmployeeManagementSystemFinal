package employeemanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPageController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private double x = 0;
    private double y = 0;

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
     * Handles the admin login process.
     * <p>
     * This method retrieves the username and password from the input fields, validates them,
     * and attempts to authenticate the admin using the {@code validateAdmin} method.
     * If the login is successful, it opens the dashboard window. Otherwise, it displays an error alert.
     * </p>
     */
    public void loginAdmin() {
        try {
            Admin admin = new Admin(username.getText(), password.getText());

            // Check if username or password fields are empty
            if (admin.getUsername().isEmpty() || admin.getPassword().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                return;
            }

            // Validate admin credentials
            if (validateAdmin(admin)) {
                getData.username = admin.getUsername();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Logged In");
                alert.showAndWait();

                // Hide the current login window
                login.getScene().getWindow().hide();

                // Load the dashboard window
                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                // Make the window draggable
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                // Set window style and show the dashboard
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            } else {
                // Show an error alert if credentials are incorrect
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username or Password");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Validates the admin credentials by checking if a record with the given
     * username and password exists in the database.
     *
     * @param admin the Admin object containing the username and password to validate
     * @return true if the admin credentials are valid (i.e., a matching record is found), false otherwise
     * @throws SQLException if a database access error occurs
     */
    private boolean validateAdmin(Admin admin) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";

        try (Connection conn = database.connectdb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // returns true if a matching admin is found
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation if useful
    }
}
