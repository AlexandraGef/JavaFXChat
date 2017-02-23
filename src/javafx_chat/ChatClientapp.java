/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.main;

/**
 *
 * @author Ola
 */
public class ChatClientapp {

    private Stage primaryStage;

    public ChatClientapp(Stage inS) {
        primaryStage = inS;
    }

    public void showGUI(String FXMLpath, String csspath, String title) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.getIcons().add(new Image(ChatClientapp.class.getResourceAsStream("czatt.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showClientGUI() {
        showGUI("../javafx_chat/FXMLDocument.fxml", "../javafx_chat/application.css", "JavaFX Chat Client");
    }

}
