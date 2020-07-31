package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustomerScreen implements Initializable {
    @FXML
    private Label setNameLabel;
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
        //setFoucs and disable bank textfileds!
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
            bankname.requestFocus();
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
            personname.requestFocus();
        }
    }

    public void nextScreen(ActionEvent actionEvent) throws IOException, SQLException {
        //database code to save from textfields

        if (personalcar.isSelected()){
            //Pesonal information are ready for database
            if (personname.getText().isEmpty() || midname_personal.getText().isEmpty() ||lastname_personal.getText().isEmpty()
            ||city_personal.getText().isEmpty() || street_personal.getText().isEmpty()||
            id_perosnal.getText().isEmpty() || phone_personal.getText().isEmpty()){
                JOptionPane.showMessageDialog(null , "Texts should not be EMPTY!!");
            }
            else{

                int id , phone;

                try {
                    if (Character.isLetter(Integer.parseInt(id_perosnal.getText())) || (Character.isLetter(Integer.parseInt(phone_personal.getText())))) {
                        JOptionPane.showMessageDialog(null, "ID or PhoneNumber should not contains charters");
                    } else {
                        id = Integer.parseInt(id_perosnal.getText());
                        phone = Integer.parseInt(phone_personal.getText());
                        //insert database information :

                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                        Connection connection = DriverManager.getConnection(url , "amrproj" , "123456");
                        Statement statement = connection.createStatement();
                        String tes1 = "insert into customer values("+id+",'" +personname.getText()+"','" +midname_personal.getText() + "','"+lastname_personal.getText()+
                                "',"+phone+",'" +street_personal.getText() +"','" +city_personal.getText() + "','" + department_personal.getText() +"'," + UserInformation.getId()+")";

                        statement.executeUpdate(tes1);
                        connection.commit();
                        connection.close();
                        UserInformation.setCustomer_id(id);
                        //moving to next screen
                        Parent root = FXMLLoader.load(getClass().getResource("CustomerNextScreen.fxml"));
                        Scene tablescene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(tablescene);
                        window.show();
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null , "Invalid inputs");
                }
            }
        }//end of persnal car selected
        else if(bankid.isSelected()){

            if(bankname.getText().isEmpty() || bank_street.getText().isEmpty() || bank_city.getText().isEmpty() ||
            bank_first.getText().isEmpty() || bank_mid.getText().isEmpty() || bank_last.getText().isEmpty() || bank_id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null , "Texts cannot be Empty");
            }
            else {
                try {

                    if (Character.isLetter(Integer.parseInt(bank_id.getText()))) {
                        JOptionPane.showMessageDialog(null, "ID should not containes character ");
                    } else {
                        int id = Integer.parseInt(bank_id.getText());

                        //database connection
                        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                        Connection connection = DriverManager.getConnection(url, "amrproj", "123456");
                        Statement statement = connection.createStatement();

                        String items = "insert into BANK values(" + "'" + bankname.getText() + "','" + bank_street.getText() + "','" + bank_city.getText() + "','" +
                                bank_department.getText() + "'," + UserInformation.getId() + ",'" + bank_first.getText() + "','" + bank_mid.getText() + "','" +
                                bank_last.getText() + "'," + id + ")";

                        statement.executeUpdate(items);
                        //System.out.println(items);
                        connection.commit();
                        connection.close();
                        UserInformation.setBank_id(id);
                        //moving to next screen
                        Parent root = FXMLLoader.load(getClass().getResource("CustomerNextScreen.fxml"));
                        Scene tablescene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(tablescene);
                        window.show();

                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null ,"Invalid inputs!");
                }
                }// end of else
        }


        //ends here
        //go to second screen now

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
        setNameLabel.setText(UserInformation.getName()+" " + UserInformation.getLastname());
        personalcar.setSelected(true);

    }
}






