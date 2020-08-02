package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustomerNextScreen implements Initializable {
    private int index;
    @FXML
    private RadioButton privatebtn, taxibtn ;
    @FXML
    private TextField  licnese_number, car_color , vin_number, manufactured_company;
    @FXML
    private TextField p_model , p_price,engine_power;

    @FXML
    private TextField t_model , t_price , t_name, t_phone, t_location, t_no ;

    public CustomerNextScreen (){
        p_model = new TextField();
        t_model = new TextField();
        p_price = new TextField();
        engine_power = new TextField();
        t_price = new TextField();
        t_name = new TextField();
        t_phone = new TextField();
        t_location = new TextField();
        t_no = new TextField();
        licnese_number = new TextField();
        licnese_number.requestFocus();

    }

    public void nextScreen(ActionEvent actionEvent) throws IOException {

        try{


            //check the radio button :
            if (privatebtn.isSelected()){
                if (p_model.getText().isEmpty() || p_price.getText().isEmpty() || engine_power.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null , "Text cannot be Empty!");
                }
                else {

                    if(Character.isLetter(Integer.parseInt(licnese_number.getText()))){
                        JOptionPane.showMessageDialog(null , "License number should not contain a letter");
                    }
                    else {
                        if (licnese_number.getText().isEmpty() || car_color.getText().isEmpty() || vin_number.getText().isEmpty() || manufactured_company.getText().isEmpty() ||p_model.getText().isEmpty()
                                ||p_price.getText().isEmpty() || engine_power.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null , "Texts cannot be empty!");
                        }
                        else{
                            int id = Integer.parseInt(licnese_number.getText());
                            //save to database

                            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                            Connection connection = DriverManager.getConnection(url , "amrproj" , "123456");
                            Statement statement = connection.createStatement();
                            String insert_item = "insert into viehcle values ("+id+ ",'" +vin_number.getText() +"','" +car_color.getText() +"','" +
                                    manufactured_company.getText() +"',"+UserInformation.getId()+","+UserInformation.getCustomer_id()+","+UserInformation.getBank_id()+")";
                            statement.executeUpdate(insert_item);
                            connection.commit();
                            connection.close();
                        }

                    }

                    PrivateCar.setEngine_pow(engine_power.getText());
                    PrivateCar.setModel(p_model.getText());
                    PrivateCar.setPrice(p_price.getText());
                    PrivateCar.setLicence_no(Integer.parseInt(licnese_number.getText()));

                    TaxiInfo.setSelectTaxi(false);
                    PrivateCar.setSelectPrivate(true);

                    //moving to next screen
                    Parent root = FXMLLoader.load(getClass().getResource("insuranceScreen.fxml"));
                    Scene tablescene = new Scene(root);
                    Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(tablescene);
                    window.show();
                }
            }
            else if(taxibtn.isSelected()){
                if (t_model.getText().isEmpty() || t_price.getText().isEmpty() || t_no.getText().isEmpty() || t_phone.getText().isEmpty() ||
                t_location.getText().isEmpty() || t_name.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null , "Text cannot be Empty");
                }
                else if (Character.isLetter(Integer.parseInt(t_phone.getText()))){
                    JOptionPane.showMessageDialog(null , "Phone should not contain characters");
                }else{

                    if(Character.isLetter(Integer.parseInt(licnese_number.getText()))){
                        JOptionPane.showMessageDialog(null , "License number should not contain a letter");
                    }
                    else {
                        if (licnese_number.getText().isEmpty() || car_color.getText().isEmpty() || vin_number.getText().isEmpty() || manufactured_company.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null , "Texts cannot be empty!");
                        }
                        else{

                            int id = Integer.parseInt(licnese_number.getText());
                            //save to database

                            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                            Connection connection = DriverManager.getConnection(url , "amrproj" , "123456");
                            Statement statement = connection.createStatement();
                            String insert_item = "insert into viehcle values ("+id+ ",'" +vin_number.getText() +"','" +car_color.getText() +"','" +
                                    manufactured_company.getText() +"',"+UserInformation.getId()+","+UserInformation.getCustomer_id()+","+UserInformation.getBank_id()+")";
                            statement.executeUpdate(insert_item);
                            connection.commit();
                            connection.close();
                        }

                    }


                    TaxiInfo.setLocation(t_location.getText());
                    TaxiInfo.setModel(t_model.getText());
                    TaxiInfo.setNo_dirver(t_no.getText());
                    TaxiInfo.setPhone(Integer.parseInt(t_phone.getText()));
                    TaxiInfo.setName(t_name.getText());
                    TaxiInfo.setLicenc_no(Integer.parseInt(licnese_number.getText()));
                    TaxiInfo.setPrice(t_price.getText());
                    TaxiInfo.setSelectTaxi(true);
                    PrivateCar.setSelectPrivate(false);
                    //moving to next screen
                    Parent root = FXMLLoader.load(getClass().getResource("insuranceScreen.fxml"));
                    Scene tablescene = new Scene(root);
                    Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(tablescene);
                    window.show();
                }
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid inputs!!");
        }



    }
    public void back (javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void clearFields(ActionEvent actionEvent) {
        licnese_number.setText("");
        car_color.setText("");
        vin_number.setText("");
        manufactured_company.setText("");

        if (privatebtn.isSelected()){
            p_model.clear();
            p_price.clear();
            engine_power.clear();

        }
        else if (taxibtn.isSelected()){
            t_model.setText("");
            t_price.setText("");
            t_name.setText("");
            t_phone.setText("");
            t_location.setText("");
            t_no.setText("");
        }
    }

    public void priavateCar(ActionEvent actionEvent) {
        t_model.setDisable(true);
        t_price.setDisable(true);
        t_no.setDisable(true);
        t_phone.setDisable(true);
        t_location.setDisable(true);
        t_name.setDisable(true);

        p_model.setDisable(false);
        p_price.setDisable(false);
        engine_power.setDisable(false);
        p_model.requestFocus();

    }

    public void taxi(ActionEvent actionEvent) {


        t_model.setDisable(false);
        t_location.setDisable(false);
        t_no.setDisable(false);
        t_name.setDisable(false);
        t_phone.setDisable(false);
        t_price.setDisable(false);


        p_model.setDisable(true);
        p_price.setDisable(true);
        engine_power.setDisable(true);
        t_model.requestFocus();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        privatebtn.setSelected(true);
    }
}
