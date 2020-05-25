package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import additionalSystems.SecretData;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import mainObjects.User;

public class EditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameText;

    @FXML
    private TextArea textField;

    @FXML
    private Button okButton;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        nameText.setText(User.getName());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/AccountForm.fxml"));

        textField.setText(User.getBio());
        okButton.setOnAction(event -> {
            try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
                 Statement statement = connection.createStatement()) {
                String bio = textField.getText().trim();
                statement.executeUpdate("update user set description = '" + bio + "' where login = '" + User.getLogin() + "'");
                User.setBio(bio);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            new Window().getWindow(okButton, "../forms/AccountForm.fxml");
        });
    }
}
