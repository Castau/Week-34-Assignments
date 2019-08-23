package dto;

import entities.BankCustomer;
import java.util.Objects;

/**
 *
 * @author Camilla
 */
public class CustomerDTO {

    private int customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer customer) {
        this.customerID = customer.getId() == null ? 0 : customer.getId().intValue();
        this.fullName = customer.getFirstName() + " " + customer.getLastName();
        this.accountNumber = customer.getAccountNumber();
        this.balance = customer.getBalance();
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.fullName);
        hash = 97 * hash + Objects.hashCode(this.accountNumber);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDTO other = (CustomerDTO) obj;
        
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.accountNumber, other.accountNumber)) {
            return false;
        }
        return true;
    }
    
    

}
