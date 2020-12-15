package it.polimi.db2.Exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("no subscriber has that username");
    }
}
