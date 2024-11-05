import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> options;

    @FXML
    private Label fName, lName, email;

    @FXML
    private TextField fNameInput, lNameInput, emailInput;


    private ResourceBundle resourceBundle;

    private String languageCode;
    private String countryCode;


    @FXML
    private void onSave(){
        System.out.println(fNameInput.getText());
        System.out.println(lNameInput.getText());
        Connection connection = SQLConnection.getConn();
        String sql = "INSERT INTO employee_" + languageCode + " (first_name, last_name, email)" +
                     "VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fNameInput.getText());
            ps.setString(2, lNameInput.getText());
            ps.setString(3, emailInput.getText());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void loadLocale(String country){

        switch (country){
            case "English":
                languageCode = "en";
                countryCode = "UK";
                break;
            case "Farsi":
                languageCode = "fa";
                countryCode = "IR";
                break;
            case "Japanese":
                languageCode = "ja";
                countryCode = "JP";
                break;
            default:
                throw new IllegalArgumentException("No language found for: " + country);
        }

        resourceBundle = ResourceBundle.getBundle("translation", new Locale(languageCode, countryCode));

    }

    public void switchLanguage() {
        loadLocale(options.getValue());
        fName.setText(resourceBundle.getString("firstName") + ": ");
        lName.setText(resourceBundle.getString("lastName") + ": ");
        email.setText(resourceBundle.getString("email") + ": ");
    }
}