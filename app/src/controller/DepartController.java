package controller;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import additionalSystems.DepTable;
import additionalSystems.SecretData;
import additionalSystems.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import mainObjects.User;

public class DepartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DepTable> table;

    @FXML
    private TableColumn<DepTable, String> room;

    @FXML
    private TableColumn<DepTable, String> resp;

    @FXML
    private Label name;

    @FXML
    private Label num;

    @FXML
    private Label head;

    @FXML
    private Text descript;

    @FXML
    private Button next;

    @FXML
    private Button prev;

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
            ArrayList<String> code = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
                PreparedStatement ps = connection.prepareStatement("select code from department");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    code.add(resultSet.getString("code"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            print(code.get(0));
            AtomicInteger i = new AtomicInteger(0);
            next.setOnAction(event -> {
                i.getAndIncrement();
                if (i.get() < code.size())
                    print(code.get(i.get()));
                else
                    i.getAndDecrement();
            });
            prev.setOnAction(event -> {
                i.getAndDecrement();
                if (i.get() >= 0)
                    print(code.get(i.get()));
                else
                    i.getAndIncrement();
            });
        } else {
            next.setVisible(false);
            prev.setVisible(false);
            String code;
            try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
                PreparedStatement ps = connection.prepareStatement("select code from department where head = '" + User.getName() + "'");
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    code = resultSet.getString("code");
                    print(code);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int count;

    private void print(String code) {
        count = 0;
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
            PreparedStatement ps = connection.prepareStatement("select * from department where code = " + code);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                name.setText(resultSet.getString("fullName"));
                head.setText("Head of the department: " + resultSet.getString("head"));
                descript.setText("Description: \n" + resultSet.getString("description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        table.setItems(getTable(code));
        resp.setCellValueFactory(new PropertyValueFactory<>("respName"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        num.setText("Number of premises: " + count);
    }

    private ObservableList<DepTable> getTable(String code) {
        ObservableList<DepTable> dep = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
            PreparedStatement ps = connection.prepareStatement("select * from document where departmentNumber = " + code);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PreparedStatement pstat = connection.prepareStatement("select * from room where number = " + resultSet.getString("roomNumber"));
                ResultSet rs = pstat.executeQuery();
                while (rs.next()) {
                    dep.add(new DepTable(resultSet.getString("roomNumber"), rs.getString("responsible")));
                    count++;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dep;
    }
}
