package it.polimi.db2.Exceptions;

public class DatabaseFailException extends Exception {
    public DatabaseFailException () {
        super("The entity manager failed one of its operations");
    }
}
