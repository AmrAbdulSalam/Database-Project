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
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.util.ResourceBundle;

public class Price implements Initializable {



    @FXML
    private RadioButton tax , full , priv , act;
    @FXML
    private TextField ptxt, drivers , motor;


    @FXML
    private Label setNameLabel;

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

    public void goToPrice(MouseEvent mouseEvent) {
        //do nothing
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
    }

    public void customerInfo(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void calculatePrice(ActionEvent actionEvent) {
        int car_price = Integer.parseInt(ptxt.getText());
        int e_p = Integer.parseInt(motor.getText());
        if (priv.isSelected()) {
            if (full.isSelected()) {
                if (car_price == 57000) {
                    JOptionPane.showMessageDialog(null, "1000");
                } else if (car_price > 57000) {
                    Double pp = ((car_price * 1.75) / 100);
                    JOptionPane.showMessageDialog(null, pp);
                }

            } else if (act.isSelected()) {

                if (e_p == 1000) {
                    JOptionPane.showMessageDialog(null, "936");
                } else if (e_p > 1000 && e_p <= 1500) {
                    JOptionPane.showMessageDialog(null, "1036");
                } else if (e_p > 1500 && e_p <= 2000) {
                    JOptionPane.showMessageDialog(null, "1340");
                } else if (e_p > 2000) {
                    JOptionPane.showMessageDialog(null, "1690");
                }

            }
        } else if (tax.isSelected()) {
            int taxi_price = Integer.parseInt(ptxt.getText());
            int no_driver=Integer.parseInt(drivers.getText());
            if (full.isSelected()) {
                if (taxi_price <= 60000) {
                    JOptionPane.showMessageDialog(null, "1500");
                } else if (taxi_price > 60000) {
                    Double tp = ((taxi_price*2.5)/100);
                    JOptionPane.showMessageDialog(null, tp);
                }
            }
            else if (act.isSelected()){
                if (no_driver==1) {
                    JOptionPane.showMessageDialog(null,"1750");
                } else if (no_driver == 2) {
                    JOptionPane.showMessageDialog(null,"1905");
                } else if (no_driver > 2) {
                    JOptionPane.showMessageDialog(null,"2020");
                }
            }
        }
    }

    public void taxiFunc(ActionEvent actionEvent) {
        drivers.setDisable(false);
        motor.setDisable(true);
    }

    public void privFunc(ActionEvent actionEvent) {
        drivers.setDisable(true);
        motor.setDisable(false);
    }
}
