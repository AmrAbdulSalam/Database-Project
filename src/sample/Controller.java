package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField usernametext;
    private boolean checkUser = false;



    public void checkFields(ActionEvent actionEvent) throws IOException {
        if (passwordText.getText().isEmpty() || usernametext.getText().isEmpty()){
            JOptionPane.showMessageDialog(null , "You should sign in with your username and password");
        }
        else{
            try{
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                Connection connection = DriverManager.getConnection(url , "amrproj" , "123456");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from employee");

                while (resultSet.next()){
                    if ((usernametext.getText().equals(resultSet.getString(3))) && (passwordText.getText().equals(resultSet.getString(4)))){
                        checkUser = true;
                        UserInformation.setId(resultSet.getInt(1));
                        UserInformation.setBirthday(resultSet.getString(2));
                        UserInformation.setUsername(resultSet.getString(3));
                        UserInformation.setPassword(resultSet.getString(4));
                        UserInformation.setSalary(resultSet.getDouble(5));
                        UserInformation.setName(resultSet.getString(6));
                        UserInformation.setLastname(resultSet.getString(7));
                    }
                }

                connection.commit();
                connection.close();


            }catch (Exception e){
                e.printStackTrace();
            }

            if (checkUser) {
                Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Scene tablescene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(tablescene);
                window.show();
            }
            else
                JOptionPane.showMessageDialog(null , "Wrong Username or Password please try again ...");
        }
    }

    public void aboutUs(MouseEvent mouseEvent) {
        JOptionPane.showMessageDialog(null , "Vehicle insurance \n" +
                "(also known as car insurance) is insurance for cars, \n" +
                "and other road vehicles.Its primary use is to provide\n" +
                "financial protection against physical damage or bodily injury \n" +
                "resulting from traffic collisions and against liability\n" +
                "that could also arise from incidents in a vehicle.\n" +
                "Vehicle insurance may additionally offer financial \n" +
                "protection against theft of the vehicle, and against damage\n" +
                "to the vehicle sustained from events other \n" +
                "than traffic collisions, such as keying,\n" +
                "weather or natural disasters,and damage sustained by\n" +
                "colliding with stationary objects. ");
    }


    public void register(ActionEvent Event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) Event.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

}
