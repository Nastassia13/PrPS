package controller;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import additionalSystems.SecretData;
import additionalSystems.Shake;
import additionalSystems.Window;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mainObjects.Document;
import mainObjects.User;
import mainObjects.Department;
import mainObjects.Room;
import sun.util.calendar.BaseCalendar;

public class ActController {
    private boolean checkRoom;
    private boolean check;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField departField;

    @FXML
    private TextField roomField;

    @FXML
    private RadioButton transferBut;

    @FXML
    private RadioButton fixBut;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/MainMenuForm.fxml"));

        ToggleGroup group = new ToggleGroup();
        transferBut.setToggleGroup(group);
        fixBut.setToggleGroup(group);
        group.selectedToggleProperty().addListener((ov, t, t1) -> {
            RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle();
            if (chk.getText().equals("Transfer")) {
                confirmButton.setOnAction(event -> {
                    checkAll();
                    if (check && checkRoom) {
                        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
                             Statement statement = connection.createStatement()) {
                            String depInput = departField.getText().trim();
                            String roomInput = roomField.getText().trim();
                            Date date = new Date();
                            statement.executeUpdate("delete from document where roomNumber = " + roomInput);
                            statement.executeUpdate("insert into document(date, enterprisenumber, act, roomnumber, departmentnumber) values('" + date.toString() + "' , 12345,'Transfer', '" + roomInput + "', '" + depInput + "')");
                            } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        new Window().getWindow(confirmButton, "../forms/DocumentForm.fxml");
                    } else {
                        Shake depShake = new Shake(departField);
                        depShake.playAnim();
                        departField.setText("");
                        Shake roomShake = new Shake(roomField);
                        roomShake.playAnim();
                        roomField.setText("");
                    }
                });
            } else if (chk.getText().equals("Fix")) {
                confirmButton.setOnAction(event -> {
                    checkAll();
                    if (check && !checkRoom) {
                        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
                             Statement statement = connection.createStatement()) {
                            String depInput = departField.getText().trim();
                            String roomInput = roomField.getText().trim();
                            Date date = new Date();
                            statement.executeUpdate("insert into document(date, enterprisenumber, act, roomnumber, departmentnumber) values('" + date.toString() + "' , 12345,'Fix', '" + roomInput + "', '" + depInput + "')");
                            } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        new Window().getWindow(confirmButton, "../forms/DocumentForm.fxml");
                    } else {
                        Shake depShake = new Shake(departField);
                        depShake.playAnim();
                        departField.setText("");
                        Shake roomShake = new Shake(roomField);
                        roomShake.playAnim();
                        roomField.setText("");
                    }
                });
            }
        });

    }

    private void checkAll() {
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword);
             Statement statement = connection.createStatement()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM department");
            ResultSet resultSet = ps.executeQuery();
            String code;
            Department dep = null;
            checkRoom = false;
            check = true;
            while (resultSet.next()) {
                code = resultSet.getString("code");
                if (code.equals(departField.getText().trim())) {
                    dep = new Department(Integer.parseInt(code),
                            resultSet.getString("fullName"),
                            resultSet.getString("shortName"),
                            resultSet.getString("head"));
                    break;
                }
            }

            ps = connection.prepareStatement("SELECT * FROM room");
            resultSet = ps.executeQuery();
            String num;
            Room room = null;
            while (resultSet.next()) {
                num = resultSet.getString("number");
                if (num.equals(roomField.getText().trim())) {
                    room = new Room(Integer.parseInt(num),
                            resultSet.getString("type"),
                            Double.parseDouble(resultSet.getString("area")),
                            resultSet.getString("responsible"));
                    break;
                }
            }
            if (dep == null || room == null) {
                check = false;
                return;
            }

            ps = connection.prepareStatement("SELECT * FROM document where roomNumber = " + roomField.getText());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                checkRoom = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
