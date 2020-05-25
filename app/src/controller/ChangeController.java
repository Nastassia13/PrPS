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
import javafx.scene.control.TextField;
import mainObjects.User;

import javax.jws.soap.SOAPBinding;

public class ChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameText;

    @FXML
    private Button okButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passField;

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

        okButton.setOnAction(event -> {
            try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
                 Statement statement = connection.createStatement()) {
                String login = loginField.getText().trim();
                String pass = passField.getText().trim();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
                ResultSet resultSet = ps.executeQuery();
                boolean check = false;
                while (resultSet.next()) {
                    if (login.equals(resultSet.getString("login")) && !login.equals(User.getLogin()))
                        check = true;
                }
                if (!check) {
                    statement.executeUpdate("update user set login = '" + login + "', password = '" + pass + "' where login = '" + User.getLogin() + "'");
                    User.setLogin(login);
                    new Window().getWindow(okButton, "../forms/AccountForm.fxml");
                } else {
                    Shake loginShake = new Shake(loginField);
                    loginShake.playAnim();
                    loginField.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
