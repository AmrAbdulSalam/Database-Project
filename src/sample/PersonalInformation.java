package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalInformation implements Initializable {
    @FXML
    private Label specialName;
    @FXML
    private TextField  birthday , username , salary , name;


    public PersonalInformation(){
        salary = new TextField();
        birthday = new TextField();
        username = new TextField();
        name = new TextField();
    }



    public void setName(String one, double two , String three , String four) {
        name.setText(one);
        salary.setText(two+"");
        birthday.setText(three);
        username.setText(four);
        System.out.println(one);
        name.setText("dsfdsffds");
    }

    public void goToCustomer(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void goToUpdate(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void goToDailyReports(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DailyReportsScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void checkFields(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salary.setText("                         "+UserInformation.getSalary());
        name.setText("                         " + UserInformation.getName()+" "+UserInformation.getLastname());
        birthday.setText("                         "+UserInformation.getBirthday());
        username.setText("                         "+UserInformation.getUsername());
        specialName.setText(UserInformation.getName()+" "+UserInformation.getLastname());
    }
}
