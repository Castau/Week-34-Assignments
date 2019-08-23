package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Camilla
 */
public class CustomerFacadeTest {
    private static CustomerFacade facade;
    private static EntityManagerFactory emfTest;
    private static EntityManager em;
    
    public CustomerFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emfTest = Persistence.createEntityManagerFactory("puTest");
        em = emfTest.createEntityManager();
        facade = new CustomerFacade(emfTest);
        Customer customer1 = new Customer("Ole", "Hansen");
        Customer customer2 = new Customer("Gerda", "Karlsen");
        
        try {
            em.getTransaction().begin();
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();
            
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
    public void testFindByID() {
        Long id = 2L;
        Customer expResult = new Customer("Gerda", "Karlsen");
        Customer result = facade.findByID(id);
        assertEquals(expResult.getFirstname(), result.getFirstname());
    }
    
    @Test
    public void testFindByLastName() {
        String lastname = "Hansen";
        Customer expResult = new Customer("Ole", lastname);
        List<Customer> resultList = facade.findByLastName(lastname);
        Customer result = resultList.get(0);
        assertEquals(expResult.getFirstname(), result.getFirstname());
    }
    
    @Test
    public void testGetNumberOfCustomers() {
        int expResult = 2;
        int result = facade.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddCustomer() {
        Customer expResult = facade.addCustomer("Ursula", "Mogensen");
        assertEquals(3, expResult.getId().intValue());
    }
    
//    @Test
//    public void testCreateCustomer() {
//        System.out.println("createCustomer");
//        Customer c = null;
//        CustomerFacade instance = new CustomerFacade();
//        Customer expResult = null;
//        Customer result = instance.createCustomer(c);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAllCustomers() {
//        System.out.println("getAllCustomers");
//        CustomerFacade instance = new CustomerFacade();
//        List<Customer> expResult = null;
//        List<Customer> result = instance.getAllCustomers();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAllCustomers() {
//        System.out.println("allCustomers");
//        CustomerFacade instance = new CustomerFacade();
//        List<Customer> expResult = null;
//        List<Customer> result = instance.allCustomers();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
