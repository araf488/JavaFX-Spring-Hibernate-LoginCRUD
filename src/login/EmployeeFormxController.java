/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import jdbc.Employee;
import jdbc.HibernateDao;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * FXML Controller class
 *
 * @author B3
 */
public class EmployeeFormxController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton editBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private TableView<Employee> table;
    ObservableList<Employee> emplist;
    @FXML
    private JFXTextField filterInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        filterInput.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterEmployeeList((String) oldValue, (String) newValue);
            }
            
        });
        createColumns();
        fillTable();
    
    }
    
    public void filterEmployeeList(String oldValue, String newValue) {
        ObservableList<Employee> filteredList = FXCollections.observableArrayList();
        if (filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(getEmployeeTable());
        } else {
            newValue = newValue.toUpperCase();
            for (Employee employee : table.getItems()) {
                String filterName = employee.getName();
                String filterId = Integer.toString(employee.getId());
                if (filterName.toUpperCase().contains(newValue) || filterId.toUpperCase().contains(newValue)) {
                    filteredList.add(employee);
                }
            }
            table.setItems(filteredList);
        }
    }

    public void createColumns() throws BeansException {
        TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));

        TableColumn<Employee, Double> salColumn = new TableColumn<>("Salary");
        salColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));

        TableColumn<Employee, String> passColumn = new TableColumn<>("Password");
        passColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("pass"));

        table.getColumns().addAll(idColumn, nameColumn, salColumn, passColumn);
        
    }
    private void fillTable() {
        
       table.setItems(getEmployeeTable());
    }

    public ObservableList<Employee> getEmployeeTable() throws BeansException {
        // TODO
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        emplist = FXCollections.observableArrayList(hd.getEmployee());
//        for (Employee e : emplist) {
//            System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary() + " " + e.getPass());
//
//        }
        return emplist;
    }

    @FXML
    private void updateEmployee(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.updateEmployee(new Employee(Integer.parseInt(id.getText()), name.getText(), password.getText(), Double.parseDouble(salary.getText())));
        id.clear();
        name.clear();
        password.clear();
        salary.clear();
        fillTable();
    }

    @FXML
    private void saveEmployee(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.saveEmployee(new Employee(name.getText(), password.getText(), Double.parseDouble(salary.getText())));
        id.clear();
        name.clear();
        password.clear();
        salary.clear();
        fillTable();
    }

    @FXML
    private void deleteEmployee(ActionEvent event) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        hd.deleteEmployee(Integer.parseInt(id.getText()));
        id.clear();
        name.clear();
        password.clear();
        salary.clear();
        fillTable();
    }

    @FXML
    private void setOnField(MouseEvent me) {
        if (me.getClickCount() >= 1) {
                           int  i = table.getItems().get(table.getSelectionModel().getSelectedIndex()).getId();
                           id.setText(Integer.toString(i));
                           String  n = table.getItems().get(table.getSelectionModel().getSelectedIndex()).getName();
                           name.setText(n);
                           Double  s = table.getItems().get(table.getSelectionModel().getSelectedIndex()).getSalary();
                           salary.setText(Double.toString(s));
                           String  p = table.getItems().get(table.getSelectionModel().getSelectedIndex()).getPass();
                           password.setText(p);
                          }
        
    }

    

}
