/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import message.RequestChatMessage;
import message.RequestRegisterUserResponse;

/**
 *
 * @author Ola
 */
public class ChatServer2 {

    static ArrayList<ServerClientBackEnd> clients = new ArrayList();

    public ChatServer2() {
        try {
            //uruchomienie serwera w porcie 3010
            ServerSocket server = new ServerSocket(3010);

            //uruchomienie nasluchiwania i rozpoczecie polaczenia
            while (true) {
                //oczekiwanie na klientow w petli
                Socket temp = server.accept();

                ServerClientBackEnd backEnd = new ServerClientBackEnd(temp);
                clients.add(backEnd);
                Thread t = new Thread(backEnd);
                t.setDaemon(true);
                t.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void broadcastMessage(RequestChatMessage cm) {

        if (cm.isIsPrivate()) {
            for (ServerClientBackEnd temp : clients) {
                if (cm.getPrivateName().equals(temp.getName())) {
                    temp.sendMessage(cm);
                }
            }
        } else {
            for (ServerClientBackEnd temp : clients) {
                temp.sendMessage(cm);
            }
        }
    }

    public static void sendUserList() {
        RequestRegisterUserResponse userList = new RequestRegisterUserResponse();
        for (ServerClientBackEnd temp : clients) {
            userList.setName(temp.getName());
        }

        for (ServerClientBackEnd temp : clients) {
            temp.sendMessage(userList);
        }
    }
}
