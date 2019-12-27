/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class NewAccountQuestionsController implements Initializable {

   @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField Confirm;

    @FXML
    private ComboBox<String> SecretQuestion1;

    @FXML
    private TextField SecretAnswer1;

    @FXML
    private ComboBox<String> SecretQuestion2;

    @FXML
    private TextField SecretAnswer2;

    @FXML
    private TextField PhoneNumber;
    @FXML
    private Label error;
    
    @FXML
    private ComboBox<String> AccountType;
   
  
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list= FXCollections.observableArrayList();
         list.add("What is your GrandMother last Name");
         list.add("What is your highSchool Mascot");
         list.add("What is your favorite color");
    SecretQuestion1.setItems(list);
       
    
     ObservableList<String> list2= FXCollections.observableArrayList();
         list2.add("What is your favorite Car Name");
         list2.add("What is your favorite Sport");
         list2.add("Which city were you born");
    SecretQuestion2.setItems(list2);
    
     ObservableList<String> list3= FXCollections.observableArrayList();
         list3.add("Checking");
         list3.add("Saving");
         list3.add("Business");
    AccountType.setItems(list3);        
    }    
 
        @FXML
    public void signUp(ActionEvent event) throws IOException{
        


       
        
        String emailfield=Email.getText();
        
        String passwordfield=Password.getText();
        
        String secretanswer1=SecretAnswer1.getText();
        String secretanswer2=SecretAnswer2.getText();
        String phonenumber=PhoneNumber.getText();
        
        
        if (isValid()){
            
            
            
               Statement statement=null;
              try{
        Connection con=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statement=con.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM newuser WHERE Email= " + "'" + Email.getText() + "'" 
            + "AND Password=" + "'" + Password.getText() + "'");
        if(rs.next()){
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Already exiss");
        
            alert.setContentText("password and username already exist ");

            alert.showAndWait();
            Email.setText("");
            Password.setText("");
            Confirm.setText("");
           
            }
        else{
                
             Class.forName("com.mysql.jdbc.Driver");
            
           Connection DBConn = DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu?autoReconnect=true&useSSL=false", "khamdu", "Sq1HK_2S_fn0");

            
            
            
            
            
            String query = " insert into newuser (Email, Password, SecretQuestion1,"
                    + "SecretAnswer1, SecretQuestion2, SecretAnswer2,"
                    + "PhoneNumber, AccountType)"
        + " values (?, ?,?,?,?,?,?,?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = DBConn.prepareStatement(query);
      preparedStmt.setString (1, emailfield);
      preparedStmt.setString (2, passwordfield);
//        if (SecretQuestion1.getSelectionModel().getSelectedIndex()==0){
//         System.out.println("it is 1");
//      }
      if (SecretQuestion1.getSelectionModel().getSelectedIndex()==0){
          preparedStmt.setInt(3, 0);
      }
       if (SecretQuestion1.getSelectionModel().getSelectedIndex()==1){
          preparedStmt.setInt(3, 1);
      }
        if (SecretQuestion1.getSelectionModel().getSelectedIndex()==2){
          preparedStmt.setInt(3, 2);
      }
      preparedStmt.setString(4, secretanswer1);
      
       if (SecretQuestion2.getSelectionModel().getSelectedIndex()==0){
          preparedStmt.setInt(5, 0);
      }
       if (SecretQuestion2.getSelectionModel().getSelectedIndex()==1){
          preparedStmt.setInt(5, 1);
      }
        if (SecretQuestion2.getSelectionModel().getSelectedIndex()==2){
          preparedStmt.setInt(5, 2);
      }
           preparedStmt.setString(6, secretanswer2);
           
              preparedStmt.setString(7, phonenumber);
              
        if (AccountType.getSelectionModel().getSelectedIndex()==0){
          preparedStmt.setInt(8, 0);
      }
       if (AccountType.getSelectionModel().getSelectedIndex()==1){
          preparedStmt.setInt(8, 1);
      }
        if (AccountType.getSelectionModel().getSelectedIndex()==2){
          preparedStmt.setInt(8, 2);
      }       
        
     

      // execute the preparedstatement
      preparedStmt.execute();
      
      
           Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/LogIn.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();      
                
                
                }
        }
       
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                
            }
            
            
            
            
            
            
            
            
        

          
    
            
            
            
            
            
            
        }       
            
            
            
      }
      
        
    
    
    
    public boolean isValid(){
        
        boolean logins=false;
        String errors ="";
         
        Pattern specialCharPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern UpperCasePattern = Pattern.compile("[A-Z ]");
    Pattern lowerCasePattern = Pattern.compile("[a-z ]");
    Pattern digitCasePattern = Pattern.compile("[0-9 ]");

        
        
        String emailfield=Email.getText();
        
        String passwordfield=Password.getText();
        
        String confirmfield=Confirm.getText();
    
        
        String secretanswer1=SecretAnswer1.getText();
        String secretanswer2=SecretAnswer2.getText();
        String phonenumber=PhoneNumber.getText();
        
        
        if(secretanswer1.isEmpty()){
            errors +="Answer1 field is empty \n";
        }
        if(secretanswer2.isEmpty()){
            errors +="Answer2 field is empty \n";
        }
        if(phonenumber.isEmpty()){
            errors +="phonenumber field is empty \n";
        }
        if(!emailfield.contains("@")){
            errors +="invalid email \n";
        }
        
        
        if(!passwordfield.equals(confirmfield) ){
              // Alert alert = new Alert(Alert.AlertType.ERROR);
           
           // alert.setTitle("Invalid Fields");
            errors +="password field is not matched \n ";
        
           // alert.setContentText(error);

            //alert.showAndWait();
            
        }
        
      if ( passwordfield.length() <8){
          
             //Alert alert = new Alert(Alert.AlertType.ERROR);
           
           // alert.setTitle("Invalid Fields");
           errors +="password length is < than 8 \n";
        
          //  alert.setContentText(error);

            //alert.showAndWait();
         //   return false;
          
      }
      
      if (!specialCharPattern.matcher(passwordfield).find()){
         //    Alert alert = new Alert(Alert.AlertType.ERROR);
           
       //     alert.setTitle("Invalid Fields");
       
       errors += "missing special character \n ";
        
            //error.setText(errors);
      }
      
      if (!UpperCasePattern.matcher(passwordfield).find()){
           //  Alert alert = new Alert(Alert.AlertType.ERROR);
           
            //alert.setTitle("Invalid Fields");
        
            //alert.setContentText("missing uppercase letter ");

            //alert.showAndWait();
            
                   errors +="missing uppercase letter \n";
        
            //error.setText(errors);
         //   return false;
          
      }
      
      if (!lowerCasePattern.matcher(passwordfield).find()){
          //   Alert alert = new Alert(Alert.AlertType.ERROR);
           
            //alert.setTitle("Invalid Fields");
        
            //alert.setContentText("missing lower case letter ");

            //alert.showAndWait();
             errors +="missing lower case letter \n";
        
            //error.setText(errors);
            //return false;
          
      }
      
      if (!digitCasePattern.matcher(passwordfield).find()){
        //   Alert alert = new Alert(Alert.AlertType.ERROR);
           
          //  alert.setTitle("Invalid Fields");
        
            //alert.setContentText("missing numeric digit ");

            //alert.showAndWait(); 
                errors +="missing numeric digit \n";
        
           // error.setText(errors);
            //return false;
          
      }
      if (passwordfield.isEmpty()){
        //   Alert alert = new Alert(Alert.AlertType.ERROR);
           
          //  alert.setTitle("Invalid Fields");
        
            //alert.setContentText("password field is empty ");

            //alert.showAndWait();
                errors +="password field is empty \n";
        
            //error.setText(errors);
           // return false;
          
          
      }
      
      
      
      
      
      
      
      
    Statement statement=null;
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu?autoReconnect=true&useSSL=false", "khamdu", "Sq1HK_2S_fn0");
        statement=con.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM newuser WHERE Email= " + "'" + Email.getText() + "'" 
            + "AND Password=" + "'" + Password.getText() + "'");
        while(rs.next()){
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Already exiss");
        
            alert.setContentText("password and username already exist ");

            //alert.showAndWait();
           
            }
            rs.close();
        }
       
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                
            }
      
      
      
      
      if (errors.length()==0){
      
      
      return true;
          
      }
      
      else{
          error.setText(errors);
                  return false;
      }
        
    }
    
   
    
    
    
    
}

