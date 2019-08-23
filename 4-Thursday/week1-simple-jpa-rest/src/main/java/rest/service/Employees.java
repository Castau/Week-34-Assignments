package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employees")
public class Employees {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getFacade(emf);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/data")
    public String data() {
        EntityManager em = emf.createEntityManager();
        ArrayList<Employee> allEmployees = new ArrayList();
        allEmployees.add(new Employee("Ole Hansen", "Tingvej 17", 20000));
        allEmployees.add(new Employee("Gerda Karlsen", "Birkegade 2", 18000));
        allEmployees.add(new Employee("Tenna Ibsen", "Torvegade 234", 21000));
        allEmployees.add(new Employee("Birgit Smidth", "Mosen 6", 17000));
        allEmployees.add(new Employee("Karl Jensen", "Kirkevej 1", 17000));
        allEmployees.add(new Employee("Ib Henningsen", "Hovedvejen 15", 18000));

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
        return "Employees created";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Employee> employees = facade.getAllEmployees();
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        employees.forEach((e) -> {
            employeeDTO.add(new EmployeeDTO(e));
        });
        return gson.toJson(employeeDTO);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployee(@PathParam("id") Long id) {
        return gson.toJson(new EmployeeDTO(facade.getEmployeeById(id)));
    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        List<Employee> employees = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        employees.forEach((e) -> {
            employeeDTO.add(new EmployeeDTO(e));
        });
        return gson.toJson(employeeDTO);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByName(@PathParam("name") String name) {
        List<Employee> employees = facade.getEmployeesByName(name);
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        employees.forEach((e) -> {
            employeeDTO.add(new EmployeeDTO(e));
        });
        return gson.toJson(employeeDTO);
    }

}
