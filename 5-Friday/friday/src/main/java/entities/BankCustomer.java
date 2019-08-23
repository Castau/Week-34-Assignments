package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private double balance;
    private int customerRanking; 
    private String internalInfo; 

    public BankCustomer() {
    }

    public BankCustomer(String firstName, String lastName, String accountNumber, double balance, int customerRanking, String internalInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerRanking() {
        return customerRanking;
    }

    public void setCustomerRanking(int customerRanking) {
        this.customerRanking = customerRanking;
    }

    public String getInternalInfo() {
        return internalInfo;
    }

    public void setInternalInfo(String internalInfo) {
        this.internalInfo = internalInfo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.accountNumber);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 23 * hash + this.customerRanking;
        hash = 23 * hash + Objects.hashCode(this.internalInfo);
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
        final BankCustomer other = (BankCustomer) obj;
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (this.customerRanking != other.customerRanking) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.accountNumber, other.accountNumber)) {
            return false;
        }
        if (!Objects.equals(this.internalInfo, other.internalInfo)) {
            return false;
        }
        return true;
    }
    
    
    
}
