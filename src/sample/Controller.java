package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Random;


public class Controller {
/* FXML Reference object - variables*/
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleGroup group;
    @FXML
    private Button signUpBtn;

/*Bank Unique Code*/
    private final String bankCode = "1005";


    public int generateRandomNumber(){
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public String generateAccountNumber(){
        return bankCode +  Integer.toString(generateRandomNumber());
    }

    @FXML
    public void  handleSignUp() throws SQLException, NullPointerException {
        String firstname = firstName.getText().trim();
        String lastname = lastName.getText().trim();
        String phonenumber = phoneNumber.getText().trim();
        String pass = password.getText().trim();
        String accountType = ((RadioButton)group.getSelectedToggle()).getText();

        DataSource datasource = new DataSource();


        if(firstname != "" &&  lastname != "" &&  phonenumber != "" &&
                pass != "" && accountType != ""){
                if(!datasource.open()){
                    System.out.println("Can't open datasource");
                    return;
                }

            datasource.insertUsers(generateAccountNumber(), firstname, lastname, phonenumber, accountType, pass);
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            password.setText("");
            datasource.close();
        }else {
            System.out.println("Can't take any empty fields");
            throw new SQLException("Can't take any empty fields");
        }
    }
}
