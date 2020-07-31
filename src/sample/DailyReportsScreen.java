package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class DailyReportsScreen implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private ComboBox comboBox;
    String selected;
    String items [] = {""};
    @FXML
    private Label setNameLabel;

    public DailyReportsScreen(){

    }

    public void checkFields(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void goToDailyReports(MouseEvent mouseEvent) {
        //do nothing!!
    }

    public void goToUpdate(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void goToCustomer(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void personalInfo(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PersonalInformation.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNameLabel.setText(UserInformation.getName()+" "+UserInformation.getLastname());
    }
}

