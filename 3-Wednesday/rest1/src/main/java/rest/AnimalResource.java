package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Camilla
 */
@Path("animal")
public class AnimalResource {

    private static List<Animal> animals = new ArrayList();

    {
        animals.add(new Animal(2006, "Dog", "Woof"));
        animals.add(new Animal(1987, "Elephant", "Trumpet"));
        animals.add(new Animal(2013, "Wolf", "Howl"));
        animals.add(new Animal(2018, "Dolphins ", "Whistle"));
    }
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello from my first web service";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String secondString() {
        return "{\"msg\":\"\test\"}";
    }
    
    public Animal getRandomAnimal(){
        Random rnd = new Random();
        return animals.get(rnd.nextInt(4));
    }
    
    @GET
    @Path("/randomAnimal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal() {
        //TODO return proper representation object
        return new Gson().toJson(getRandomAnimal());
    }
}
