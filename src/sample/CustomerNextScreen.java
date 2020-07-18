package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerNextScreen {
    @FXML
    private RadioButton privatebtn, taxibtn ;
    @FXML
    private TextField License_number , car_color , vin_number, manufactured_company;
    @FXML
    private TextField p_model , p_price,engine_power;

    @FXML
    private TextField t_model , t_price , t_name, t_phone, t_location, t_no ;

    public void nextScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("insuranceScreen.fxml"));
        Scene tablescene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tablescene);
        window.show();
    }

    public void clearFields(ActionEvent actionEvent) {
        License_number.setText("");
        car_color.setText("");
        vin_number.setText("");
        manufactured_company.setText("");

        if (privatebtn.isSelected()){
            License_number.setText("");
            car_color .setText("");
            manufactured_company.setText("");
            vin_number.setText("");

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

}
