/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx_chat.ChatClientapp;
import chatserver2.ChatServer2;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Ola
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        startProgram(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startProgram(Stage primaryStage) throws IOException {

        Label text = new Label("\nWybierz serwer lub aplikacje klienta\n");
        text.setStyle(
                "-fx-text-fill: #b60074;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 20px;"
        );
        Button serverButton = new Button("Serwer");
        serverButton.setPrefSize(125, 30);

        serverButton.setStyle("-fx-background-color: linear-gradient(#990099, #ffd9f9),radial-gradient(center 50% -40%, radius 200%, #fd4cbd 45%, #b60074 50%);"
                + "-fx-background-radius: 6, 5;"
                + "-fx-background-insets: 0, 1;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "-fx-text-fill: #fce5f4;"
                + "-fx-font-weight: bold;"
        );
        serverButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                ChatServer2 s = new ChatServer2();
                s.getClass();

            }
        });
        Button clientButton = new Button("Aplikacja Klienta");
        clientButton.setPrefSize(125, 30);
        clientButton.setStyle("-fx-background-color: linear-gradient(#990099, #ffd9f9),radial-gradient(center 50% -40%, radius 200%, #fd4cbd 45%, #b60074 50%);"
                + "-fx-background-radius: 6, 5;"
                + "-fx-background-insets: 0, 1;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                + "-fx-text-fill: #fce5f4;"
                + "-fx-font-weight: bold;"
        );
        clientButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ChatClientapp gl = new ChatClientapp(primaryStage);
                gl.showClientGUI();
            }
        });
        BorderPane rootPane = new BorderPane();
        rootPane.setPrefSize(500, 250);
        rootPane.setStyle(
                "-fx-background-color: linear-gradient(#990099, #ffd9f9),radial-gradient(center 60% -80%, radius 300%, #ffffff 45%, #ffbeee 50%);"
                + "-fx-hgap: 30px;"
                + "-fx-vgap:40px");

        rootPane.setTop(text);

        HBox hbox2 = new HBox();

        HBox hBox = new HBox();
        hBox.setSpacing(90.0);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(serverButton, clientButton);

        hbox2.getChildren().add(text);
        hbox2.setAlignment(Pos.CENTER);

        rootPane.setCenter(hBox);
        rootPane.setTop(hbox2);
        Scene scene = new Scene(rootPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Co chcesz uruchomić ?");
        primaryStage.getIcons().add(new Image(main.class.getResourceAsStream("quest.png")));
        primaryStage.show();

    }
}
