package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DailyReportsScreen implements Initializable {

    public TextField enteredvalue;
    @FXML
    private TableView < TabelClass> tableView;
    @FXML
    private TableColumn < TabelClass, Integer> licence_co;
    @FXML
    private TableColumn < TabelClass, Integer> owner_co;
    @FXML
    private TableColumn < TabelClass, String> start_co;
    @FXML
    private TableColumn < TabelClass, String> end_co;


    @FXML
    private ComboBox<String> comboBox;
    String selected;
    String items [] = {""};
    @FXML
    private Label setNameLabel;

    private int l_n,c_i;
    private String s_d,e_d;

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

    ObservableList<TabelClass> list = FXCollections.observableArrayList();


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        comboBox.getItems().add(0,"License Number");
        comboBox.getItems().add(1,"ID");
        comboBox.getItems().add(2,"Start Date");
        comboBox.getItems().add(3,"End Date");
        setNameLabel.setText(UserInformation.getName()+" "+UserInformation.getLastname());
        licence_co.setCellValueFactory(new PropertyValueFactory<>("lic"));
        owner_co.setCellValueFactory(new PropertyValueFactory<>("id"));
        start_co .setCellValueFactory(new PropertyValueFactory<>("start"));
        end_co.setCellValueFactory(new PropertyValueFactory<>("end"));

    }

    public void setTableView(javafx.event.ActionEvent actionEvent) {
        OracleDataSource ods = null;
        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            ods.setUser("amrproj");
            ods.setPassword("123456");
            Connection c = null;
            c = ods.getConnection();
            ResultSet rs = c.createStatement().executeQuery("select  viehcle.LICENSE_NO,viehcle.CUSTOMER_ID,insurance.start_date,insurance.end_date From viehcle,customer,privatee,insurance where viehcle.license_no = privatee.lice_no and viehcle.customer_id = customer.customer_id and privatee.paye_id = insurance.payer_id " +
                    "union select  viehcle.LICENSE_NO,viehcle.CUSTOMER_ID,insurance.start_date,insurance.end_date From viehcle,customer,taxi,insurance where viehcle.license_no = taxi.lic2_no and viehcle.customer_id = customer.customer_id and taxi.pay2_id = insurance.payer_id " +
                    "union select  viehcle.LICENSE_NO,viehcle.CUSTOMER_ID,insurance.start_date,insurance.end_date From viehcle,bank,privatee,insurance where viehcle.license_no = privatee.lice_no and viehcle.customer_id = bank.owner_id and privatee.paye_id = insurance.payer_id " +
                    "union select  viehcle.LICENSE_NO,viehcle.CUSTOMER_ID,insurance.start_date,insurance.end_date From viehcle,bank,taxi,insurance where viehcle.license_no = taxi.lic2_no and viehcle.customer_id = bank.owner_id and taxi.pay2_id = insurance.payer_id ");

            //ResultSet rs = c.createStatement().executeQuery("select viehcle.license_no , viehcle.customer_id , insurance.start_date , insurance.end_date" +
                   // "from insurance , viehcle , customer where customer.customer_id = viehcle.customer_id and ");

            while (rs.next()) {
                if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
                    int entered_license = Integer.parseInt(enteredvalue.getText());
                    int tablelicense=rs.getInt("LICENSE_NO");
                    if (entered_license == tablelicense) {
                        l_n = rs.getInt("license_no");
                        s_d = rs.getString("start_date");
                        e_d = rs.getString("end_date");
                        c_i = rs.getInt("CUSTOMER_ID");
                        list.add(new TabelClass(s_d, e_d, l_n, c_i));
                        System.out.println("ssss");
                       // break;
                    }
                } else if (comboBox.getSelectionModel().getSelectedIndex() == 1) {
                    int entered_id = Integer.parseInt(enteredvalue.getText());
                    if (entered_id == rs.getInt("customer_id")) {
                        l_n = rs.getInt("license_no");
                        s_d = rs.getString("start_date");
                        e_d = rs.getString("end_date");
                        c_i = rs.getInt("CUSTOMER_ID");
                        list.add(new TabelClass(s_d, e_d, l_n, c_i));
                        //break;
                    }
                } else if (comboBox.getSelectionModel().getSelectedIndex() == 2) {
                    String entered_startDate = (enteredvalue.getText());
                    if (entered_startDate.equals(rs.getString("start_date"))) {
                        l_n = rs.getInt("license_no");
                        s_d = rs.getString("start_date");
                        e_d = rs.getString("end_date");
                        c_i = rs.getInt("CUSTOMER_ID");
                        list.add(new TabelClass(s_d, e_d, l_n, c_i));
                        //break;
                        System.out.println("start");
                    }
//                  } else {
//                      JOptionPane.showMessageDialog(null, "This Start Date doesn't Exist");
//                  }
                } else if (comboBox.getSelectionModel().getSelectedIndex() == 3) {
                    String entered_endDate = (enteredvalue.getText());
                    System.out.println("55555");
                    if (entered_endDate.equals(rs.getString("end_date"))) {
                        l_n = rs.getInt("license_no");
                        s_d = rs.getString("start_date");
                        e_d = rs.getString("end_date");
                        c_i = rs.getInt("CUSTOMER_ID");
                        list.add(new TabelClass(s_d, e_d, l_n, c_i));
                        //break;
                        System.out.println("end");
                    }
//                  } else {
//                      JOptionPane.showMessageDialog(null, "This End Date doesn't Exist");
//                  }
                }

            }

            c.commit();
            c.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tableView.setItems(list);
        System.out.println("hi");
        System.out.println(list);
    }
}

