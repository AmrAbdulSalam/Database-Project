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
        Parent root = FXMLLoader.load(getClass().getResource("insuranceScreen.fxml"));
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
}
