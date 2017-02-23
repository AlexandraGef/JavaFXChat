/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JOptionPane;
import message.MessageException;
import message.RequestChatMessage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Ola
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TextField chatMessage;
    @FXML
    TextField userNameField;
    @FXML
    Button connectButton;
    @FXML
    Button sendButton;
    @FXML
    ListView userNameList;
    @FXML
    CheckBox privateMessage;
    @FXML
    ColorPicker choiseFontColor;
    @FXML
    ChoiceBox choiseFontFamily;
    @FXML
    WebView webView;
    @FXML
    HBox Hboxlogin;
    @FXML
    Label wpiszLog;

    ClientBackEnd backEnd;
    Thread backThread;
    private String name;
    HTMLEditor chatMessageArea = new HTMLEditor();
    WebEngine webEngine;

    @FXML
    public void sendChatMessage(ActionEvent ae) {
        RequestChatMessage cm = new RequestChatMessage();
        try {
            cm.setUserName(name);
        } catch (MessageException ex) {
            ex.printStackTrace();
        }
        cm.setFontFamily((String) choiseFontFamily.getSelectionModel().getSelectedItem());
        String value = choiseFontColor.getValue().toString();
        String avalue = value.split("0x")[1];
        avalue = "#" + avalue.split("ff")[0];
        cm.setMessageColor(avalue);
        cm.setChatMessage(chatMessage.getText());

        if (privateMessage.isSelected()) {
            cm.setIsPrivate(true);
            String name = (String) userNameList.getSelectionModel().getSelectedItem();
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wybierz imię z listy!");
                return;
            } else {
                cm.setPrivateName(name);
                chatMessage.clear();
            }
        }
        backEnd.sendMessage(cm);
        chatMessage.clear();

    }

    @FXML
    public void enableSendButton(KeyEvent e) {
        if (!userNameField.getText().isEmpty()) {
            connectButton.setDisable(false);

        } else {
            connectButton.setDisable(true);
        }
    }

    @FXML
    public void connectToServer(ActionEvent e) {
        try {
            name = userNameField.getText();
            backEnd.connectToServer(name);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Nie można połączyć się z serwerem:" + ex.getMessage());
            Platform.exit();
        }
        sendButton.setDisable(false);
        backThread = new Thread(backEnd);
        backThread.setDaemon(true);
        backThread.start();
        connectButton.setVisible(false);

        userNameField.setVisible(false);
        wpiszLog.setVisible(false);

    }

    public String getName() {
        return name;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //wklada to w EDT (Event Dispatcher Thread) kolejki i uruchamia
        //kiedy EDT jest gotowy (lub dostaje czas na uruchomienie)
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userNameField.requestFocus();
                choiseFontFamily.getSelectionModel().select(0);
                choiseFontColor.setValue(Color.BLACK);

            }
        });
        ObservableList<String> family
                = FXCollections.observableArrayList("Antiqua", "Architect", "Arial",
                        "Courier", "Monospace", "Script");
        choiseFontFamily.setItems(family);
        webEngine = webView.getEngine();
        backEnd = new ClientBackEnd(this);
        String st = chatMessageArea.getHtmlText();
        if (st.contains("contenteditable=\"true\"")) {
            st = st.replace("contenteditable=\"true\"", "contenteditable=\"false\"");
        }
        webEngine.loadContent(st);
    }

    public void updateTextArea(RequestChatMessage m) {

        String textAppend = "<p style='color:" + m.getMessageColor() + ";font-family:"
                + m.getFontFamily() + ";font-size:" + m.getFontSize() + "px;'>"
                + m.getUserName() + ":" + m.getChatMessage() + "</p>";
        chatMessageArea.setHtmlText(chatMessageArea.getHtmlText() + "\n" + textAppend);
        webEngine.loadContent(chatMessageArea.getHtmlText());
        webEngine = webView.getEngine();

    }

    public void setUserNameList(ObservableList<String> names) {
        userNameList.getItems().removeAll(userNameList.getItems());
        userNameList.setItems(names);
    }

}
