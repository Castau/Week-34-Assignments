package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Camilla
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerFacadeTest {

    private static CustomerFacade facade;
    private static EntityManagerFactory emfTest;
    private static EntityManager em;
    private static List<BankCustomer> allCustomers;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emfTest = Persistence.createEntityManagerFactory("puTest");
        em = emfTest.createEntityManager();
        facade = CustomerFacade.getFacade(emfTest);
        allCustomers = new ArrayList();
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
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        Long id = 3L;
        CustomerDTO expResult = new CustomerDTO(new BankCustomer("Tenna", "Ibsen", "6958743", 60.25, 5, "RKI-registreret"));
        CustomerDTO result = facade.getCustomerByID(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        CustomerDTO expResult = new CustomerDTO(new BankCustomer("Ole", "Hansen", "6547895", 1300.05, 5, "Afsoner røveridom"));
        List<CustomerDTO> result = facade.getCustomerByName("Ole Hansen");
        assertEquals(expResult, result.get(0));
    }

    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        List<BankCustomer> expResult = allCustomers;
        List<BankCustomer> result = facade.getAllBankCustomers();
        assertEquals(expResult, result);
    }

    @Test
    public void testzAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer cust = new BankCustomer("Test", "Tester", "1111111", 0.00, 0, "TEST");
        CustomerDTO result = new CustomerDTO(facade.addCustomer(cust));
        CustomerDTO expResult = facade.getCustomerByID(7L);
        assertEquals(expResult, result);

    }

}
