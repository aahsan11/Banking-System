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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class TransferController implements Initializable {
  @FXML
    private ComboBox<String> AccountCombo;

    @FXML
    private TextField TransferAmount;

    @FXML
    private DatePicker TransferDate;


    @FXML
    private TextField Notes;
    String value;
           ObservableList <String> list=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try{
             
                      Statement statemen=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection co=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statemen=co.createStatement();
            
     //       SELECT
  //(SELECT SUM(deposit.depositamount) FROM khamdu.deposit  WHERE deposit.accountno = 13) -
   //(SELECT SUM(withdraw.amount) FROM khamdu.withdraw  WHERE withdraw.accountno = 13);
   
    ResultSet rs=statemen.executeQuery("SELECT ID FROM NEWUSER WHERE ID!=" + "'" + ids + "'") ;
   //  ResultSet r=statemen.executeQuery("SELECT DepositAmount FROM deposit WHERE AccountNo= " + "'" + ids + "'");
            
            
            
            while(rs.next()){
                 value=rs.getString(1);
                 System.out.println(value+"vv");
                 
      
               
                list.addAll(value);
             AccountCombo.setItems(list);
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
      
    }  
     @FXML
    void Submit(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         Class.forName("com.mysql.jdbc.Driver");
            
           Connection DBConn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu?autoReconnect=true&useSSL=false", "khamdu", "Sq1HK_2S_fn0");

                  String salt="/#$%^&/+-_@~abcdefghijklmnipqrstuvwxyz1234567890";
          StringBuilder salts=new StringBuilder();
          for (int i=0; i<14;i++){
          Random rnd= new Random();
          System.out.println("salts cu"+salts);
      
              int index=(int) (rnd.nextFloat()*salt.length());
              salts.append(salt.charAt(index));
              System.out.println(index+"ind");
             
              String str=salts.toString();
            //  System.out.println(str+ "       number");
          }
           System.out.println(salts);
           
            
            
            
            
            String query = " insert into deposit(AccountNo,DepositDate, DepositAmount,ConfirmationNo,Notes,depositcol)"
        + " values (?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = DBConn.prepareStatement(query);
      preparedStmt.setString(1, AccountCombo.getSelectionModel().getSelectedItem());
      preparedStmt.setString(2, TransferDate.getValue().toString());
      preparedStmt.setString(3, TransferAmount.getText());
       preparedStmt.setString(4, salts.toString());
        preparedStmt.setString(5, Notes.getText());
          preparedStmt.setString(6, "d");
      preparedStmt.execute();
      
      
      
      
      
      
      
      
      
      
        Class.forName("com.mysql.jdbc.Driver");
            
           Connection DBConnt = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu?autoReconnect=true&useSSL=false", "khamdu", "Sq1HK_2S_fn0");

                  String saltt="/#$%^&/+-_@~abcdefghijklmnipqrstuvwxyz1234567890";
          StringBuilder saltst=new StringBuilder();
          for (int i=0; i<14;i++){
          Random rndt= new Random();
      
              int indext=(int) (rndt.nextFloat()*saltt.length());
              saltst.append(saltt.charAt(indext));
             
              String str=salts.toString();
            //  System.out.println(str+ "       number");
          }
           System.out.println(saltst);
           
            
            
            
            
            String queryt = " insert into withdraw(AccountNo, WithdrawDate, Amount,ConfirmationNo,Notes,withdrawcol)"
        + " values (?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmtt = DBConnt.prepareStatement(queryt);
      preparedStmtt.setString(1, ids);
      preparedStmtt.setString(2, TransferDate.getValue().toString());
      preparedStmtt.setString(3, TransferAmount.getText());
       preparedStmtt.setString(4, saltst.toString());
        preparedStmtt.setString(5, Notes.getText());
          preparedStmtt.setString(6, "w");
      preparedStmtt.execute();
    
      
      
    
    
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
    void Deposit(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/Deposit.fxml"));
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

    }
    

