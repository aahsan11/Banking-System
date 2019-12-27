/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.LogInController.ids;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DepositController implements Initializable {
         @FXML
    private TextField AccountNo;

    @FXML
    private TextField PersonName;
//
//    @FXML
//    private TextField DepositDates;

    @FXML
    private TextField Amount,Notes;
    
    @FXML
    private DatePicker DepositDates;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      AccountNo.setText(ids);
    }   
    public void setAccountNo(String AccountNO){
      // AccountNo.setText(AccountNO);
    }
    
    @FXML
    void onClick(ActionEvent event) throws SQLException, ClassNotFoundException {
// AccountDetailsController a= new AccountDetailsController();
//  //  a.getusernames();
//   System.out.println(a.getusernames()+"ll");
    System.out.println(ids);
    Class.forName("com.mysql.jdbc.Driver");
            
           Connection DBConn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu?autoReconnect=true&useSSL=false", "khamdu", "Sq1HK_2S_fn0");

            
              String salt="/#$%^&/+-_@~abcdefghijklmnipqrstuvwxyz1234567890";
          StringBuilder salts=new StringBuilder();
          for (int i=0; i<14;i++){
          Random rnd= new Random();
      
              int index=(int) (rnd.nextFloat()*salt.length());
              salts.append(salt.charAt(index));
             
              String str=salts.toString();
            //  System.out.println(str+ "       number");
          }
           System.out.println(salts);
           
           
           
           
          
            
            String query = " insert into deposit(AccountNo,DepositDate, DepositAmount,ConfirmationNo,Notes,depositcol)"
        + " values (?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = DBConn.prepareStatement(query);
      preparedStmt.setString(1, AccountNo.getText());
      preparedStmt.setString(2, DepositDates.getValue().toString());
      preparedStmt.setString(3, Amount.getText());
      preparedStmt.setString(4, salts.toString());
        preparedStmt.setString(5, Notes.getText());
          preparedStmt.setString(6, "d");
      preparedStmt.execute();
      
    
    
    }
      @FXML
    void Account(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/AccountDetails.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    
     @FXML
    void Withdraw(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/WithDraw.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
            @FXML
    void Transfer(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/Transfer.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}
