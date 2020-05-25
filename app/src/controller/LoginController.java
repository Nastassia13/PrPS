package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import additionalSystems.SecretData;
import additionalSystems.Shake;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainObjects.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button inButton;

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    void initialize() {
        inButton.setOnAction(event -> {
            try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
                 Statement statement = connection.createStatement()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM user where login = ?");
                String login = loginInput.getText().trim();
                ps.setString(1, login);
                ResultSet resultSet = ps.executeQuery();
                String password = null;
                String name = null;
                String type = null;
                String bio = null;
                while (resultSet.next()) {
                    password = resultSet.getString("password");
                    name = resultSet.getString("name");
                    type = resultSet.getString("type");
                    bio = resultSet.getString("description");
                }
                if (password != null && password.equals(passwordInput.getText())) {
                    User.setName(name);
                    User.setType(type);
                    User.setBio(bio);
                    User.setLogin(login);
                    new Window().getWindow(inButton, "../forms/MainMenuForm.fxml");
                } else {
                    Shake loginShake = new Shake(loginInput);
                    Shake passordShake = new Shake(passwordInput);
                    loginShake.playAnim();
                    passordShake.playAnim();
                    loginInput.setText("");
                    passwordInput.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
