/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

/**
 *
 * @author Ola
 */
import java.util.ArrayList;

public class RequestRegisterUserResponse extends MessageBase {

    private ArrayList<String> users = new ArrayList();

    public void setName(String user) {
        users.add(user);
    }

    public ArrayList<String> getUserList() {
        return users;
    }
}
