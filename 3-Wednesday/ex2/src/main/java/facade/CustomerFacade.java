package facade;

import entity.Customer;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Camilla
 */
public class CustomerFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public CustomerFacade(EntityManagerFactory emfTest) {
        emf = emfTest;
    }

    public CustomerFacade() {
    }
   
    public Customer createCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT c FROM Customer c").getResultList();

    }

    public Customer findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Query result = em.createQuery("SELECT c FROM Customer c WHERE c.id = :id");
        result.setParameter("id", id);
        return (Customer) result.getSingleResult();
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Customer> query
                = em.createQuery("SELECT c FROM Customer c WHERE c.lastname = :name", Customer.class);
        query.setParameter("name", name);
        List<Customer> results = query.getResultList();
        return results;

    }

    public Integer getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Customer> query
                = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> results = query.getResultList();
        return results.size();
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Customer> query
                = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> results = query.getResultList();
        return results;
    }

    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer(fName, lName);
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }
    
    
    // SELECT COUNT(*) FROM ex2customer.CUSTOMER;
    public long numberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Long> count = em.createQuery("SELECT COUNT(c) FROM Customer c", long.class);
        return count.getSingleResult();
    }

}
