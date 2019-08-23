package dto;

import entities.Employee;

/**
 *
 * @author Camilla
 */
public class EmployeeDTO {

    private String name;
    private Long id;
    private String address;

    public EmployeeDTO(Employee employee) {
        this.name = employee.getName();
        this.id = employee.getId();
        this.address = employee.getAddress();
    }

    public EmployeeDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
