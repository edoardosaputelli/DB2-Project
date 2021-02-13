package it.polimi.db2.Exceptions;

public class AlreadyDoneException  extends Exception{

    public AlreadyDoneException(){
        super("The user already submitted or cancelled the questionnaire");
    }

}
