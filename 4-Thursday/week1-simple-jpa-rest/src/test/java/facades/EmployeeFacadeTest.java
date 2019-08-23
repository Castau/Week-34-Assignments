package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Camilla
 */
public class EmployeeFacadeTest {

    private static EmployeeFacade facade;
    private static EntityManagerFactory emfTest;
    private static EntityManager em;
    private static List<Employee> allEmployees;
    private static List<Employee> highSalEmp;

    public EmployeeFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emfTest = Persistence.createEntityManagerFactory("puTest");
        em = emfTest.createEntityManager();
        facade = EmployeeFacade.getFacade(emfTest);
        allEmployees = new ArrayList();
        allEmployees.add(new Employee("Ole Hansen", "Tingvej 17", 20000));
        allEmployees.add(new Employee("Gerda Karlsen", "Birkegade 2", 18000));
        allEmployees.add(new Employee("Tenna Ibsen", "Torvegade 234", 20000));
        allEmployees.add(new Employee("Birgit Smidth", "Mosen 6", 17000));
        allEmployees.add(new Employee("Karl Jensen", "Kirkevej 1", 17000));
        allEmployees.add(new Employee("Ib Henningsen", "Hovedvejen 15", 18000));
        highSalEmp = new ArrayList();
        highSalEmp.add(new Employee("Ole Hansen", "Tingvej 17", 20000));
        highSalEmp.add(new Employee("Tenna Ibsen", "Torvegade 234", 20000));
        
        try {

            for (Employee e : allEmployees) {
                em.getTransaction().begin();
                em.persist(e);
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

    @org.junit.Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        Long id = 2L;
        Employee expResult = new Employee("Gerda Karlsen", "Birkegade 2", 18000);
        Employee result = facade.getEmployeeById(id);
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testGetEmployeesByName() {
        System.out.println("getEmployeesByName");
        String name = "Tenna Ibsen";
        Employee expResult = new Employee("Tenna Ibsen", "Torvegade 234", 20000);
        List<Employee> resultList = facade.getEmployeesByName(name);
        Employee result = resultList.get(0);
        assertEquals(expResult.getName(), result.getName());
    }

    @org.junit.Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        List<Employee> expResult = allEmployees;
        List<Employee> resultList = facade.getAllEmployees();
        assertEquals(expResult, resultList);
    }

    @org.junit.Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        List<Employee> expResult = highSalEmp;
        List<Employee> result = facade.getEmployeesWithHighestSalary();
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testzCreateEmployee() {
        System.out.println("createEmployee");
        Employee empTest = new Employee("createTest", "Test", 0);
        Employee result = facade.createEmployee(empTest);
        Employee expResult = facade.getEmployeeById(7L);
        assertEquals(expResult, result);
    }

}
