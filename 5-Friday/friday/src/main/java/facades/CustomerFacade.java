package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    private CustomerFacade() {
    }

    public static CustomerFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(Long id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery result = em.createQuery("SELECT bc FROM BankCustomer bc WHERE bc.id = :id", BankCustomer.class);
            result.setParameter("id", id);
            BankCustomer customer = (BankCustomer) result.getSingleResult();
            CustomerDTO dto = new CustomerDTO(customer);
            return dto;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        String[] names = name.split(" ");
        try {
            TypedQuery<BankCustomer> query = em.createQuery("SELECT bc FROM BankCustomer bc WHERE bc.firstName = :firstName AND bc.lastName = :lastName", BankCustomer.class);
            query.setParameter("firstName", names[0]);
            query.setParameter("lastName", names[1]);
            List<BankCustomer> results = query.getResultList();
            List<CustomerDTO> customerDTOs = new ArrayList<>();
            for (BankCustomer bc : results) {
                customerDTOs.add(new CustomerDTO(bc));
            }
            return customerDTOs;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return cust;
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("select bc from BankCustomer bc", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
