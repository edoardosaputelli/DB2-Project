package it.polimi;

import it.polimi.db2.ejb.AdminManager;
import it.polimi.db2.entities.AdminEntity;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import java.sql.Date;


public class TestMain {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    AdminManager admin = new AdminManager();

    public static void main(String[] args) {/*

        Date date = new Date(10, 10, 2000);
        byte[] img = new byte[10];
        try {
            admin.addProduct("chinotto", date, img);
        }catch (Exception e ) {
            e.printStackTrace();
        }
*/
    }
}
