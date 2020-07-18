package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField usernametext;

    public void checkFields(ActionEvent actionEvent) throws IOException {
        if (passwordText.getText().isEmpty() || usernametext.getText().isEmpty()){
            JOptionPane.showMessageDialog(null , "You should sign in with your username and password");
        }
        else if (passwordText.getText().equals("123") && usernametext.getText().equals(" ")){

            Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Scene tablescene = new Scene(root);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(tablescene);
            window.show();
        }
        else{
            JOptionPane.showMessageDialog(null , "Undefined Username or Password");
        }
    }

    public void aboutUs(MouseEvent mouseEvent) {
        JOptionPane.showMessageDialog(null , "tsss");
    }
}
