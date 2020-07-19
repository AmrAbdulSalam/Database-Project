package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CustomerScreen {
    //text fields for perosnal insurenace!!
    @FXML
    private TextField phone_personal , department_personal , city_personal , street_personal , lastname_personal , midname_personal , id_perosnal;
    @FXML
    private RadioButton personalcar , bankid;
    @FXML
    private TextField personname , bankname;

    //text fileds for bank
    @FXML
    private TextField bank_street , bank_city , bank_department , bank_first , bank_mid , bank_last , bank_id;


    private void setBankVisable(){
        bankname.setDisable(true);
        bank_city.setDisable(true);
        bank_department.setDisable(true);
        bank_street.setDisable(true);
        bank_first.setDisable(true);
        bank_mid.setDisable(true);
        bank_last.setDisable(true);
        bank_id.setDisable(true);
    }

    public CustomerScreen(){

    }


    public void checkFields(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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

    public void goToUpdate(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void goToCustomer(MouseEvent mouseEvent) {
        //do nothing!
    }

    public void BankButton(ActionEvent actionEvent) {

        id_perosnal.setDisable(true);
        phone_personal.setDisable(true);
        midname_personal.setDisable(true);
        lastname_personal.setDisable(true);
        street_personal.setDisable(true);
        department_personal.setDisable(true);
        city_personal.setDisable(true);
        personname.setDisable(true);

        bankname.setDisable(false);
        bank_city.setDisable(false);
        bank_department.setDisable(false);
        bank_street.setDisable(false);
        bank_first.setDisable(false);
        bank_mid.setDisable(false);
        bank_last.setDisable(false);
        bank_id.setDisable(false);
        bankname.requestFocus();

    }

    public void personalButton(ActionEvent actionEvent) {


        setBankVisable();

        id_perosnal.setDisable(false);
        phone_personal.setDisable(false);
        midname_personal.setDisable(false);
        lastname_personal.setDisable(false);
        street_personal.setDisable(false);
        department_personal.setDisable(false);
        city_personal.setDisable(false);
        personname.setDisable(false);
        personname.requestFocus();
    }

    public void clearFields(ActionEvent actionEvent) {
        if (bankid.isSelected()){
            bankname.setText("");
            bank_city.setText("");
            bank_department.setText("");
            bank_street.setText("");
            bank_first.setText("");
            bank_mid.setText("");
            bank_last.setText("");
            bank_id.setText("");
        }
        else if (personalcar.isSelected()){
            id_perosnal.setText("");
            phone_personal.setText("");
            midname_personal.setText("");
            lastname_personal.setText("");
            street_personal.setText("");
            department_personal.setText("");
            city_personal.setText("");
            personname.setText("");
        }
    }

    public void nextScreen(ActionEvent actionEvent) throws IOException {
        //database code to save from textfields

        //ends here
        //go to second screen now
        Parent root = FXMLLoader.load(getClass().getResource("CustomerNextScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();

    }
}






