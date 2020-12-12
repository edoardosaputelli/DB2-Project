package it.polimi.db2.Exceptions;

public class BadLanguageException extends Exception{

    public BadLanguageException (){
        super("The user provided answers with forbidden words");
    }
}
