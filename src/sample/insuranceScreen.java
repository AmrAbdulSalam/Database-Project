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
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

import static sample.CustomerNextScreen.private_or_taxi;

public class insuranceScreen {
    @FXML
    private RadioButton act , full;
    @FXML
    private TextField payer_id , f_name , m_name ,l_name , days;

    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private Label pricelabel;
    @FXML
    private Label home;
    @FXML
    private Button taxibtn,privatebtn;

    private String act_text , full_text;

    private int index = 0 ;
  public void backHome(javafx.event.ActionEvent actionEvent) throws Exception {

    try {
       if (days.getText().isEmpty() || payer_id.getText().isEmpty() || f_name.getText().isEmpty() || m_name.getText().isEmpty() || l_name.getText().isEmpty()){
          JOptionPane.showMessageDialog(null , "Text cannot be Empty!");
     }
            else if(Character.isLetter(Integer.parseInt(payer_id.getText()))){
                JOptionPane.showMessageDialog(null , "ID should not contain characters!");
            }
            else {
           if (full.isSelected()) {
               full_text = "1";
               act_text = "0";
           } else if (act.isSelected()) {
               full_text = "0";
               act_text = "1";
           }


           PrivateCar.setPayer_id(Integer.parseInt(payer_id.getText()));

           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String url = "jdbc:oracle:thin:@localhost:1521:orcl";
           Connection connection = DriverManager.getConnection(url, "amrproj", "123456");
           Statement statement = connection.createStatement();
           //adding to insurance table
           int nd=Integer.parseInt(days.getText());
           String insert_item = "insert into insurance values(" + Integer.parseInt(payer_id.getText()) + ",'" + start.getValue() + "','"
                   + end.getValue() + "'," + nd + ",'" + full_text + "','" + act_text + "','" + f_name.getText() + "','" + m_name.getText() + "','" + l_name.getText() + "')";
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


                }
                else if (TaxiInfo.isSelectTaxi()){
                    String insert_taxi = "insert into taxi values ("+TaxiInfo.getPhone()+",'" +TaxiInfo.getLocation() +"','"+TaxiInfo.getPrice()+"','"+
                            TaxiInfo.getNo_dirver()+"',"+TaxiInfo.getLicenc_no() +","+Integer.parseInt(payer_id.getText()) + ",'" +TaxiInfo.getModel() +"','"
                            +TaxiInfo.getName()+"')";

                    statement.executeUpdate(insert_taxi);
                    connection.commit();
                    connection.close();

                }
                //go to the main page
                int license_number= (PrivateCar.getLicence_no());
                int license_number_taxi=(TaxiInfo.getLicenc_no());
                OracleDataSource ods = null;
                ods = new OracleDataSource();
                ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                ods.setUser("amrproj");
                ods.setPassword("123456");
                Connection c = null;
                c = ods.getConnection();
               if (act.isSelected()){
                   if(private_or_taxi.equals("private")) {

                       ResultSet rs = c.createStatement().executeQuery("select engine_power,no_days,lice_no From privatee,insurance,viehcle where privatee.paye_id = insurance.payer_id ");
                        while (rs.next()) {
                            int e_p = rs.getInt("engine_power");
                            int n_d = rs.getInt("no_days");
                            int license_number_wanted=(rs.getInt("lice_no"));
                            if(license_number==license_number_wanted) {

                                if (e_p == 1000) {
                                    pricelabel.setText(String.valueOf(((936 / 2) * n_d) / 180));
                                } else if (e_p > 1000 && e_p <= 1500) {
                                    pricelabel.setText(String.valueOf(((1036 / 2) * n_d) / 180));
                                } else if (e_p > 1500 && e_p <= 2000) {
                                    pricelabel.setText(String.valueOf(((1340 / 2) * n_d) / 180));
                                } else if (e_p > 2000) {
                                    pricelabel.setText(String.valueOf(((1690 / 2) * n_d) / 180));
                                }
                            }
                        }
                    }
                    else if (private_or_taxi.equals("taxi")){
                       ResultSet rs = c.createStatement().executeQuery("select no_driver,no_days,lic2_no From taxi,insurance,viehcle where taxi.pay2_id = insurance.payer_id ");
                       while (rs.next()) {
                            int no_driver = Integer.parseInt(rs.getString("no_driver"));
                            int n_days = rs.getInt("no_days");
                            int license_number_wanted = (rs.getInt("lic2_no"));
                            if (license_number_taxi == license_number_wanted) {
                               if (no_driver == 1) {
                                    pricelabel.setText(String.valueOf(((1750 / 2) * n_days) / 180));
                                } else if (no_driver == 2) {
                                    pricelabel.setText(String.valueOf(((1905 / 2) * n_days) / 180));
                                } else if (no_driver > 2) {
                                    pricelabel.setText(String.valueOf(((2020 / 2) * n_days) / 180));
                                }
                            }
                        }

                    }

                }
                else if (full.isSelected()){
                    if(private_or_taxi.equals("private")) {

                       ResultSet rs = c.createStatement().executeQuery("select car_price, lice_no From privatee,insurance,viehcle where privatee.paye_id = insurance.payer_id ");
                        while (rs.next()) {
                            int car_price = Integer.parseInt(rs.getString("car_price"));
                            int license_number_wanted=Integer.parseInt(rs.getString("lice_no"));
                           if(license_number==license_number_wanted) {

                               if (car_price == 57000) {
                                   pricelabel.setText(String.valueOf(1000));
                                } else if (car_price > 57000 ) {
                                    pricelabel.setText(String.valueOf(((car_price*1.75)/100)));
                                }
                            }
                        }
                    }
                    else if (private_or_taxi.equals("taxi")){
                        ResultSet rs = c.createStatement().executeQuery("select car_price,lic2_no From taxi,insurance,viehcle where taxi.pay2_id = insurance.payer_id ");
                        while (rs.next()) {
                            int taxi_price=Integer.parseInt(rs.getString("car_price"));
                            int license_number_wanted = (rs.getInt("lic2_no"));
                            if (license_number_taxi == license_number_wanted) {
                                if (taxi_price <= 60000) {
                                    pricelabel.setText(String.valueOf((1500)));
                                } else if (taxi_price > 60000) {
                                    pricelabel.setText(String.valueOf(((taxi_price*2.5)/100)));
                                }
                            }
                        }
                    }
                }
                c.commit();
                c.close();

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
    public void home (MouseEvent Event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }
}