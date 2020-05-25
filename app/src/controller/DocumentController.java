package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import additionalSystems.SecretData;
import additionalSystems.Shake;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import mainObjects.User;

public class DocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label title;

    @FXML
    private Text text;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/MainMenuForm.fxml"));
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
             Statement statement = connection.createStatement()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM document order by idDocument desc limit 1");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                text.setText(resultSet.getString("act") + " to department " +
                        resultSet.getString("departmentNumber") + " premise No." +
                        resultSet.getString("roomNumber") + ".\nDate: " +
                        resultSet.getString("date") + "\t\t\t" + User.getName() + ", " + User.getType());
                title.setText("Order No." + resultSet.getString("idDocument"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
