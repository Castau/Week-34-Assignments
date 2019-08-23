package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bank")
public class BankCustomerResource {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private CustomerFacade facade = CustomerFacade.getFacade(emf);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

     @GET
    @Path("/data")
    public String data() {
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
        return "Customers created";
    }
       
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerDTO(@PathParam("id") int id) {
        return gson.toJson(facade.getCustomerByID(Long.valueOf(id)));
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        return gson.toJson(facade.getAllBankCustomers());
    }
   
}
