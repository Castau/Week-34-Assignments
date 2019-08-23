package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilla
 */
public class CustomerFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public Customer createCustomer(Customer c){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }
    
    public List<Customer> getAllCustomers(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT c FROM Customer c").getResultList();
        
    }
    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade();
        cf.createCustomer(new Customer(3L, "hans", "vejen"));
        cf.createCustomer(new Customer(4L, "bodil", "sorø"));
        cf.getAllCustomers().forEach(c->System.out.println(c));
        
    }
}
