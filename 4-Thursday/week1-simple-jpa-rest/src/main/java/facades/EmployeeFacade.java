package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
//    Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void setFacade (EntityManagerFactory _emf){
        emf = _emf;
    }
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//
//    public EmployeeFacade(EntityManagerFactory emfTest) {
//        emf = emfTest;
//    }

    public Employee getEmployeeById(Long id) {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        Query result = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
        result.setParameter("id", id);
        return (Employee) result.getSingleResult();
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        query.setParameter("name", name);
        List<Employee> results = query.getResultList();
        return results;
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT e FROM Employee e").getResultList();
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT emp FROM Employee emp WHERE emp.salary = (SELECT MAX(sal.salary) FROM Employee sal)", Employee.class);
        List<Employee> results = query.getResultList();
        return results;
    }

    public Employee createEmployee(Employee emp) {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
        return emp;
    }

    public Long numberOfEmployee() {
        EntityManager em = getEntityManager();
//        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> count = em.createQuery("SELECT COUNT(e) FROM Employee e", Long.class);
        return count.getSingleResult();
    }

}
