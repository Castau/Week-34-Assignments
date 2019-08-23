package entities;

import facades.CustomerFacade;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilla
 */
public class MakeTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        ArrayList<BankCustomer> allCustomers = new ArrayList();
        allCustomers.add(new BankCustomer("Ole", "Hansen", "6547895", 1300.05, 5, "Afsoner røveridom"));
        allCustomers.add(new BankCustomer("Gerda", "Karlsen", "1785463", 810.00, 4, "Mange børn, ustabil indtægt"));
        allCustomers.add(new BankCustomer("Tenna", "Ibsen", "6958743", 60.25, 5, "RKI-registreret"));
        allCustomers.add(new BankCustomer("Birgit", "Smidth", "6584424", 16000.00, 3, "Kan åbne kassekredit"));
        allCustomers.add(new BankCustomer("Karl", "Jensen", "5847533", 81050.50, 2, "Tilbyd attraktivt lån"));
        allCustomers.add(new BankCustomer("Ib", "Henningsen", "6112548", 863000.14, 1, "Investeringsrådgivning skal tilbydes"));

        try {
            for (BankCustomer b : allCustomers) {
                em.getTransaction().begin();
                em.persist(b);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
        CustomerFacade cf = CustomerFacade.getFacade(emf);
        System.out.println(cf.getCustomerByID(2L).getFullName());
    }
}
