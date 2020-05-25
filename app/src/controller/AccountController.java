package controller;

import java.net.URL;
import java.util.ResourceBundle;

import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import mainObjects.User;

import javax.jws.soap.SOAPBinding;

public class AccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameText;

    @FXML
    private Label posLabel;

    @FXML
    private Text bioText;

    @FXML
    private Button editButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        nameText.setText(User.getName());
        posLabel.setText("Position: " + User.getType());
        bioText.setText("Biography: \n" + User.getBio());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/MainMenuForm.fxml"));
        editButton.setOnAction(event -> new Window().getWindow(editButton, "../forms/EditForm.fxml"));
        changeButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/ChangeForm.fxml"));
    }
}
