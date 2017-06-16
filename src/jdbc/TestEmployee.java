package jdbc;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestEmployee {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
//        EmployeeDao ed = (EmployeeDao)context.getBean(EmployeeDao.class);
//        System.out.println(ed.getEmployeebyIdRM(7).getSalary());
//        List<Employee> emplist = ed.getAllEmployeeList();
//        for (Employee e: emplist) {
//            System.out.println(e.getName());
//            
//        }
//        EmployeeSupportDao ed = (EmployeeSupportDao)context.getBean("employeeSupportDao");
//        System.out.println(ed.saveEmployee(new Employee("A", 10000)));
        HibernateDao hd = (HibernateDao)context.getBean("hibernateDao");
//        hd.saveEmployee(new Employee("C", "3", 3000));
//            hd.updateEmployee(new Employee(10,"D", "4", 3000));
//            hd.deleteEmployee(10);
//        System.out.println(hd.getEmployee());

//        hd.login(new Employee("a", "21"));

//        List<Employee> emplist = hd.getEmployee();
//        for (Employee e: emplist) {
//            System.out.println(e.getId()+" " +e.getName()+" " +e.getSalary()+" " +e.getPass());
//            
//        }
    }
}
