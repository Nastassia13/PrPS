package sample;

import additionalSystems.AccountingSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;
import mainObjects.User;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../forms/LoginForm.fxml"));
        primaryStage.setTitle("Electronic register of premises");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
