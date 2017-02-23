package message;

/**
 *
 * @author Ola
 */
import java.io.Serializable;

public abstract class MessageBase implements Serializable {

    private String userName;

    /**
     * 
     * Zwaraca imie uzytkownika klienta czatu
     * @return String reprezentujacy nazwe klienta
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Ustawia nazwę użytkownika klienta czatu. Musi wynosić co najmniej 1 znak
     * Inaczej wyrzuca
     *
     * @param userName
     * @throws message.MessageException jezeli nazwa uzytkownika jest za krotka
     */
    public void setUserName(String userName) throws MessageException {

        if (!userName.isEmpty()) {
            this.userName = userName;
        } else {
            throw new MessageException("Zbyt krótka nazwa użytkownika");
        }
    }

}
