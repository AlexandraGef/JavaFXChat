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
/**
 * To żądanie wyslane od klienta do serwera nadania wiadmosci do innego
 * uzytkownika Jezeli jest to wiadomosc prywatna jest wyslana TYLKO do jednego
 * wybranego uzytkownika zdefiniowanego w privateName. Uzytkownik moze rowniez
 * zdefiniowac rozmiar czcionki, kolor i wyglad. Rozmiar czcionki jest
 * ograniczony od 12px do 25px.
 *
 */
public class RequestChatMessage extends MessageBase {

    private int fontSize;
    private String fontFamily;
    private boolean isPrivate;
    private String privateName;
    private String messageColor;
    private String chatMessage;
    private final int MAX_FONT_SIZE = 25;
    private final int MIN_FONT_SIZE = 12;

    public RequestChatMessage() {
        fontSize = 18;
        isPrivate = false;
        messageColor = "black";
        fontFamily = "Arial";
    }

    /**
     * Returns rozmiar czcionki
     *
     * @return rozmiar czcionki jest liczba calkowita
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Ustawienie rozmiaru czcionki. Musi byc od 12px do 25px.
     *
     * @param fontSize
     */
    public void setFontSize(int fontSize) {
        if (fontSize >= MIN_FONT_SIZE && fontSize <= MAX_FONT_SIZE) {
            this.fontSize = fontSize;
        }
    }

    /**
     * Zwraca wyglad czcionki ustawiony przez uzytkownika.
     *
     * @return String zawierajacy fontFamily
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * Ustawienie wygladu czcionki wybranego przez uzytkownika
     *
     * @param fontFamily
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * Zwraca wartosc boolean czy jest ustawiona wiadomosc prywatna czy tez nie
     *
     * @return
     */
    public boolean isIsPrivate() {
        return isPrivate;
    }

    /**
     * Ustawia wiadomosc jako prywatna
     *
     * @param isPrivate
     */
    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Zwraca nazwę odbiorcy w prywatnej wiadomosci
     *
     * @return String zawierajacy prywatna nazwe uzytkownika
     */
    public String getPrivateName() {
        return privateName;
    }

    /**
     * Ustawia imie odbiorcy w wiadomosci prywatnej
     *
     * @param privateName zawierajacy nazwe
     */
    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    /**
     * zwraca kolor wiadomosci
     *
     * @retun String zawierajacy kolor wiadomosci
     */
    public String getMessageColor() {
        return messageColor;
    }

    /**
     * ustawia kolor wiadomosci
     *
     * @param messageColor
     */
    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    /**
     * zwraca wiadomosc w czacie
     *
     * @retun String zawierajacy wadomosc w czacie
     */
    public String getChatMessage() {
        return chatMessage;
    }

    /**
     * ustawia wiadomosc czatu
     *
     * @param chatMessage
     */
    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

}
