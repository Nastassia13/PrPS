package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import additionalSystems.Shake;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mainObjects.User;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button departButton;

    @FXML
    private Button roomButton;

    @FXML
    private Button actionButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "/forms/LoginForm.fxml"));
        if (User.getType().equals("head")) {
            reportButton.setVisible(false);
            departButton.setLayoutY(130);
            roomButton.setVisible(false);
            actionButton.setVisible(false);
        } else if (User.getType().equals("responsible")) {
            reportButton.setVisible(false);
            roomButton.setLayoutY(130);
            departButton.setVisible(false);
            actionButton.setVisible(false);
        }
        accButton.setOnAction(event -> new Window().getWindow(accButton, "../forms/AccountForm.fxml"));
        reportButton.setOnAction(event -> new Window().getWindow(reportButton, "../forms/ReportForm.fxml"));
        departButton.setOnAction(event -> new Window().getWindow(departButton, "../forms/DepartForm.fxml"));
        roomButton.setOnAction(event -> new Window().getWindow(roomButton, "../forms/RoomForm.fxml"));
        actionButton.setOnAction(event -> new Window().getWindow(actionButton, "../forms/ActForm.fxml"));
    }
}
