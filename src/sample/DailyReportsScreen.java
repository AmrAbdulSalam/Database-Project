package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class DailyReportsScreen {

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
    @FXML
    private TableView tableView;
    @FXML
    ComboBox comboBox;
    String selected;
    public void initialize(){
     comboBox.getItems().add("Licence Number");
     comboBox.getItems().add("ID");
     comboBox.getItems().add("Start Date");
     comboBox.getItems().add("End Date");

    }
//    public void select(ItemEvent e){
//            selected= comboBox.getSelectionModel().getSelectedItem().toString();
//            if (selected.equals("License Number")) {
//            String l = JOptionPane.showInputDialog("Enter License Number");
//            }
//            else if(selected.equals("ID")){
//                JOptionPane.showInputDialog("Enter ID");
//            }
//            else if(selected.equals("Start Date")){
//                JOptionPane.showInputDialog(new DatePicker());
//            }
//            else if(selected.equals("End Date")){
//                JOptionPane.showInputDialog(new DatePicker(),new Button("ok"));
//            }
//
//        }
    }

