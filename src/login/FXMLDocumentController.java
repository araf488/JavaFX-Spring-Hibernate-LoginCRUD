/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdbc.Employee;
import jdbc.EmployeeForm;
import jdbc.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Hridoy
 */
public class FXMLDocumentController implements Initializable {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton signin;
    @FXML
    private JFXButton signup;
    
    private void nextStage(String fxml, String title, boolean resizable) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.show();

    }
    
    @FXML
    void makeSignin(ActionEvent event) throws IOException{
//       String username = user.getText();
//       String password = pass.getText();
//       try {
//            ps = con.prepareStatement("Select * from users where username=? and password=?");
//            ps.setString(1, user.getText());
//            ps.setString(2, pass.getText());
//            rs = ps.executeQuery();
//            rs.next();
//            user.setText(rs.getString(1));
//            pass.setText(rs.getString(2));
//            
//            user.setText("Signed in");
//            pass.setText("");
//
//            System.out.println("Sign in successful!");
//
//        } catch (Exception e) {
//            user.setText("Illegal Info!!");
//            pass.setText("");
//            user.requestFocus();
//        }

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        if (hd.login(new Employee(user.getText(), pass.getText()))) {
//            new EmployeeForm().show();
//        this.dispose();
        Stage current = (Stage) signin.getScene().getWindow();
        nextStage(Login.Employee, "EMPLOYEE PANEL", true);
        current.hide();
        } else {
//            user.setText("");
//            user.setText("Wrong!!");
              Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Login failed");
                        alert.setHeaderText("Warning");
                        alert.setContentText("User Name and Password not matched");
                        alert.initStyle(StageStyle.TRANSPARENT);
                        alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        connectToDatabase();
    }    



    public void connectToDatabase() {

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginfx", "root", "1234");

        } catch (Exception e) {
            user.setText("Error in Connection ");
        }
    }

    @FXML
    private void makeSignup(ActionEvent event) {
        try {
            ps = con.prepareStatement("insert into users(username, password) values(?,?) ");
            ps.setString(1, user.getText());
            ps.setString(2, pass.getText());
            ps.executeUpdate();
            
            
            user.setText("Registered!! ");
            pass.setText("");

            System.out.println("Sign up successful!");

        } catch (Exception e) {
            e.printStackTrace();
            user.setText("Illegal Info!!");
            pass.setText("");
            user.requestFocus();
        }
    }
    
}
