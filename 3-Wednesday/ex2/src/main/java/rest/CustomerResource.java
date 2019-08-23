/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import entity.Customer;
import facade.CustomerFacade;
import java.util.List;
import java.util.Random;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Camilla
 */
@Path("customer")
public class CustomerResource {

    CustomerFacade cf = new CustomerFacade();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String allCustomers() {
        //TODO return proper representation object
        List<Customer> customers = cf.allCustomers();
        return new Gson().toJson(customers);
    }

    public String getRandomCustomer() {
        Random rnd = new Random();
        long id = rnd.nextInt(cf.getNumberOfCustomers() -1 ) + 1;
        System.out.println("ID IS: " + id);
        return new Gson().toJson(cf.findByID(id));
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String randomCustomer() {
        //TODO return proper representation object
        return new Gson().toJson(getRandomCustomer());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String customerByID(@PathParam("id") long id) {
        return new Gson().toJson(cf.findByID(id));
    }

}
