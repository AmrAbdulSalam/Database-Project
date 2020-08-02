package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;

public class insuranceScreen implements Initializable {
    @FXML
    private RadioButton act , full;
    @FXML
    private TextField payer_id , f_name , m_name ,l_name , days;

    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;

    private String act_text , full_text;

    private int index = 0 ;
    public void backHome(javafx.event.ActionEvent actionEvent) throws IOException {

    try {
        if (days.getText().isEmpty() || payer_id.getText().isEmpty() || f_name.getText().isEmpty() || m_name.getText().isEmpty() || l_name.getText().isEmpty()){
            JOptionPane.showMessageDialog(null , "Text cannot be Empty!");

        }
        else if(Character.isLetter(Integer.parseInt(payer_id.getText()))){
            JOptionPane.showMessageDialog(null , "ID should not contain characters!");
        }
        else {
            if (full.isSelected()){
                full_text = "1";
                act_text = "0";
            }
            else if (act.isSelected()){
                full_text = "0";
                act_text = "1";
            }

            PrivateCar.setPayer_id(Integer.parseInt(payer_id.getText()));

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            Connection connection = DriverManager.getConnection(url, "amrproj", "123456");
            Statement statement = connection.createStatement();
            //adding to insurance table
            String insert_item = "insert into insurance values(" + Integer.parseInt(payer_id.getText()) + ",'" + start.getValue() + "','"
                    + end.getValue() + "','" + days.getText() + "','" + full_text + "','" + act_text + "','" + f_name.getText() + "','" + m_name.getText() + "','" + l_name.getText() + "')";
            statement.executeUpdate(insert_item);
            connection.commit();

            if (PrivateCar.isSelectPrivate()) {


                //generate new index :
                String searchIndex = "select indexx from privatee ";
                ResultSet indsearch = statement.executeQuery(searchIndex);
                while (indsearch.next()) {
                    index = indsearch.getInt(1);
                }
                index += 1;
                //insert into private cars table
                String insert_private = "insert into privatee values(" + index + ",'" + PrivateCar.getPrice() + "','" + PrivateCar.getEngine_pow() + "','" +
                        PrivateCar.getModel() + "'," + PrivateCar.getPayer_id() + "," + PrivateCar.getLicence_no() + ")";
                statement.executeUpdate(insert_private);
                connection.commit();
                connection.close();

                Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Scene tablescene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(tablescene);
                window.show();

            }
            else if (TaxiInfo.isSelectTaxi()){
                String insert_taxi = "insert into taxi values ("+TaxiInfo.getPhone()+",'" +TaxiInfo.getLocation() +"','"+TaxiInfo.getPrice()+"','"+
                        TaxiInfo.getNo_dirver()+"',"+TaxiInfo.getLicenc_no() +","+Integer.parseInt(payer_id.getText()) + ",'" +TaxiInfo.getModel() +"','"
                        +TaxiInfo.getName()+"')";

                statement.executeUpdate(insert_taxi);
                connection.commit();
                connection.close();

                Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                Scene tablescene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(tablescene);
                window.show();
            }
            //go to the main page

        }
    }catch (Exception e){
        JOptionPane.showMessageDialog(null , e.toString());
    }
    }

    public void back (javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerNextScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}