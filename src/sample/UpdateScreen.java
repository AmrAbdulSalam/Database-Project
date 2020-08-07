package sample;

import javafx.event.ActionEvent;
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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UpdateScreen implements Initializable  {


    @FXML
    private TextField color , days ,location , licNum;
    @FXML
    private DatePicker start , end;

    public ToggleGroup tst;
    @FXML
    private RadioButton act , full ,priv;

    @FXML
    private Label setNameLabel;

    private boolean isPriv = true;

    public UpdateScreen(){
        start = new DatePicker();
        end = new DatePicker();

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

    public void goToUpdate(MouseEvent mouseEvent) {
        // do nothing!
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
        act.setSelected(true);
        priv.setSelected(true);
        location.setDisable(true);
    }


    public void updateInformation(ActionEvent actionEvent) {
        //data base starmtnets
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            Connection connection = DriverManager.getConnection(url, "amrproj", "123456");
            Statement statement = connection.createStatement();


            if (licNum.getText().isEmpty()){
                JOptionPane.showMessageDialog(null , "Enter licences number");
            }
            else {
                String select_st = "";
                int lic = Integer.parseInt(licNum.getText());
                int payid = 0;
                if (isPriv) { //if it is private car
                    select_st = "select paye_id from privatee where lice_no =" +lic;
                }
                else {
                    select_st = "select pay2_id from taxi where lic2_no = "+lic;
                }

                ResultSet rs = statement.executeQuery(select_st);
                while (rs.next()){
                    payid = rs.getInt(1);
                }
                //if statements for text fields
                if (!color.getText().isEmpty()) {
                    String changeColor = "update viehcle set color = '"+color.getText() +"' where license_no = " + lic;
                    statement.executeUpdate(changeColor);
                    connection.commit();
                }
                if (!days.getText().isEmpty()) {
                    String changeDay = "update insurance set no_days = '"+days.getText()+"' where payer_id = " + payid;
                    statement.executeUpdate(changeDay);
                    connection.commit();
                }
                if (!location.getText().isEmpty()) {
                    String changeLocation = "update taxi set locaiton = '"+location.getText()+"' where lic2_no = " + lic;
                    statement.executeUpdate(changeLocation);
                    connection.commit();
                }

                if (start.getValue() != null) {
                    String changeStartDate = "update insurance set start_date = '"+start.getValue()+"' where payer_id = " + payid;
                    statement.executeUpdate(changeStartDate);
                    connection.commit();
                }
                if (end.getValue() != null) {
                    String changeStartDate = "update insurance set end_date = '"+end.getValue()+"' where payer_id = " + payid;
                    statement.executeUpdate(changeStartDate);
                    connection.commit();
                }
                if(act.isSelected()){
                    String insurance_type = "update insurance set act_tp = '1' , full_insurance = '0' where payer_id =" +payid;
                    statement.executeUpdate(insurance_type);
                    connection.commit();
                }
                if (full.isSelected()){
                    String insurance_type = "update insurance set act_tp = '0' , full_insurance = '1' where payer_id =" +payid;
                    statement.executeUpdate(insurance_type);
                    connection.commit();
                }

                //closing database
                connection.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        }//end update function


    public void privateBut(ActionEvent actionEvent) {
        location.setDisable(true);
        color.setDisable(false);
        isPriv = true;
    }

    public void taxiBut(ActionEvent actionEvent) {
        color.setDisable(true);
        location.setDisable(false);
        isPriv = false;
    }
    public void report (MouseEvent mouseEvent) {

        printreport viewreport=new printreport();
        try {
            viewreport.showReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}//end class
