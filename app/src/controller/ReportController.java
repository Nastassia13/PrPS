package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import additionalSystems.DocTable;
import additionalSystems.SecretData;
import additionalSystems.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainObjects.User;

import javax.print.Doc;

public class ReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DocTable> table;

    @FXML
    private TableColumn<DocTable, String> dep;

    @FXML
    private TableColumn<DocTable, Integer> num;

    @FXML
    private TableColumn<DocTable, String> room;

    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        exitButton.setText(User.getName() + ", " + User.getType());
        exitButton.setOnAction(event -> new Window().getWindow(exitButton, "../forms/LoginForm.fxml"));
        backButton.setOnAction(event -> new Window().getWindow(backButton, "../forms/MainMenuForm.fxml"));

        table.setItems(getTable());
        dep.setCellValueFactory(new PropertyValueFactory<>("departmentNumber"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
    }

    private ObservableList<DocTable> getTable() {
        ObservableList<DocTable> doc = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(SecretData.connectionURL, SecretData.rootName, SecretData.rootPassword)) {
            PreparedStatement ps = connection.prepareStatement("select * from document");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int size = doc.size();
                for (int i = 0; i < size; i++) {
                    DocTable d = new DocTable(resultSet.getString("roomNumber"), resultSet.getString("departmentNumber"));
                    if (doc.get(i).getDepartmentNumber().equals(d.getDepartmentNumber())) {
                        doc.get(i).setRoomNumber(resultSet.getString("roomNumber"));
                        doc.get(i).setNum();
                    } else {
                        doc.add(new DocTable(resultSet.getString("roomNumber"), resultSet.getString("departmentNumber")));
                    }
                }
                if (doc.size() == 0) {
                    System.out.println(resultSet.getString("roomNumber") + " " + resultSet.getString("departmentNumber"));
                    doc.add(new DocTable(resultSet.getString("roomNumber"), resultSet.getString("departmentNumber")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doc;
    }
}
