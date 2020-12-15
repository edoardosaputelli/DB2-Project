package it.polimi.db2.Exceptions;

public class NothingThatDateException extends Exception {

    public NothingThatDateException (){
        super("No questionnaire or product associated to that day");
    }
}
