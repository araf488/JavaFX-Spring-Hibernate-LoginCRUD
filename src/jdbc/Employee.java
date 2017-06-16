package jdbc;


import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

//@Component
//@Entity
public class Employee {
//    @Id
    private int id;
    private String name;
    private String pass;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, String pass, double salary) {
        this.name = name;
        this.pass = pass;
        this.salary = salary;
    }

    public Employee(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public Employee() {
    }

//    @Override
//    public String toString() {
//        return id + " " + name + " " + salary;
//    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(int id, String name, String pass, double salary) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.salary = salary;
    }

    public Employee(int id) {
        this.id = id;
    }
    
    
    
}
