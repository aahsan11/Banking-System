/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Classes.LogIn;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class LogInController implements Initializable {

    @FXML
    TextField username, password;
    String account;
    String id;
    String depositamount;
     static String ids;
    
    static String pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
    } 
    
    
    
    
    
    @FXML
    public void login(ActionEvent e) throws IOException, ClassNotFoundException, SQLException{
        
      //  String username1= username.getText();
        
        //String password1=password.getText();
    
        
            LogIn log= new LogIn();
            log.setUserName(username.getText());
            log.setPassword(password.getText());
        
         Statement statement=null;
             
                   Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statement=con.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM newuser WHERE Email= " + "'" + log.getUserName() + "'" 
            + "AND Password=" + "'" + log.getPassword() + "'");
        if(rs.next()){
            
            
            
            
            
            
            
         Statement statements=null;
             
                   Class.forName("com.mysql.jdbc.Driver");
        Connection cons=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statements=cons.createStatement();
        ResultSet rss=statements.executeQuery("SELECT AccountType FROM newuser WHERE Email= " + "'" + log.getUserName() + "'" 
            + "AND Password=" + "'" + log.getPassword() + "'");
            
            
            
            if(rss.next()){
                String ja = rss.getString("AccountType");
                System.out.println(ja + "jj");
                
                
                
                if(ja.equals("0")){
                     account="Checking";
                     System.out.println(account +"checking");
                }
                else if(ja.equals("1")){
                  
                     account="Saving";
                       System.out.println(account);
                }
                else if(ja.equals("2")){
                    account="business";
                    System.out.println(account);
                }
                
                
            }
            
            
            
              Statement statementss=null;
             
                   Class.forName("com.mysql.jdbc.Driver");
        Connection conss=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statementss=conss.createStatement();
        ResultSet rsss=statementss.executeQuery("SELECT ID FROM newuser WHERE Email= " + "'" + log.getUserName() + "'" 
            + "AND Password=" + "'" + log.getPassword() + "'");
            
            
            
            if(rsss.next()){
                 id = rsss.getString("ID");
                System.out.println(id + "jj");
                
                
                ids=rsss.getString("ID");
            
             
                
            }
            
            
               Statement statemen=null;
             
                   Class.forName("com.mysql.jdbc.Driver");
        Connection co=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statemen=co.createStatement();
ResultSet r=statemen.executeQuery("SELECT DepositAmount FROM deposit WHERE AccountNo= " + "'" + ids + "'");
            
          
            
            if(r.next()){
                 
            
            depositamount=r.getString("DepositAmount");
            
            
            
            
            
            }
           
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/AccountDetails.fxml"));
      Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
      
    
//       
//       AccountDetailsController display=loader.getController();
//              display.setusernames(account +"- "+ id + "     "+depositamount);
//              
             Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
       window.show();
            }
        else{
                
           
             Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Already exiss");
        
            alert.setContentText("Invalid username or password ");

            alert.showAndWait();
          
            
            
            

      
                
                
                
                }
        }
       
              
        
        


        
        
        @FXML
    void NewUser(ActionEvent e) throws IOException {
        
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/NewAccountWizard.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();

    }

}
    
  



    
