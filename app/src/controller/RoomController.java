package controller;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import additionalSystems.SecretData;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import mainObjects.User;

public class RoomController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label name;

    @FXML
    private Label head;

    @FXML
    private Label dep;

    @FXML
    private Label resp;

    @FXML
    private Text descript;

    @FXML
    private Button next;

    @FXML
    private Button prev;

    @FXML
    private Label square;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/MainMenuForm.fxml"));

        if (User.getType().equals("director")) {
            actionButtons("select number from room");
        } else {
            actionButtons("select number from room where responsible = '" + User.getName() + "'");
        }
    }

    private void actionButtons(String sql) {
        ArrayList<String> number = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                number.add(resultSet.getString("number"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        print(number.get(0));
        AtomicInteger i = new AtomicInteger(0);
        next.setOnAction(event -> {
            i.getAndIncrement();
            if (i.get() < number.size())
                print(number.get(i.get()));
            else
                i.getAndDecrement();
        });
        prev.setOnAction(event -> {
            i.getAndDecrement();
            if (i.get() >= 0)
                print(number.get(i.get()));
            else
                i.getAndIncrement();
        });
    }

    private void print(String number) {
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
            PreparedStatement ps = connection.prepareStatement("select * from room where number = " + number);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                name.setText("Room #" + resultSet.getString("number"));
                square.setText("Area: " + resultSet.getString("area") + " square meters");
                descript.setText("Description: " + resultSet.getString("type"));
                resp.setText("Responsible for the premise: " + resultSet.getString("responsible"));
                PreparedStatement pstat = connection.prepareStatement("select * from department right outer join document on code = departmentNumber where roomNumber = " + number);
                ResultSet rs = pstat.executeQuery();
                dep.setText("Department: -");
                head.setText("Head of the department: -");
                while (rs.next()) {
                    dep.setText("Department: " + rs.getString("fullName"));
                    head.setText("Head of the department: " + rs.getString("head"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
