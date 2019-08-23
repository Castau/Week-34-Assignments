package entity;

import facade.CustomerFacade;

/**
 *
 * @author Camilla
 */
public class EntityTested {

    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade();
        cf.createCustomer(new Customer("hans", "hansen"));
        cf.createCustomer(new Customer("bodil", "jensen"));
        cf.createCustomer(new Customer("karl", "petersen"));
        cf.createCustomer(new Customer("frits", "jensen"));
        cf.createCustomer(new Customer("eva", "karlsen"));
        cf.createCustomer(new Customer("mogens", "jensen"));
        cf.getAllCustomers().forEach(c -> System.out.println(c));
        Customer byid = cf.findByID(1L);
        cf.findByLastName("jensen").forEach(c -> System.out.println(c));
        System.out.println(cf.getNumberOfCustomers());
        System.out.println(cf.allCustomers());
    }
}
