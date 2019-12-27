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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MoreAccountDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String deposit, deposits,depositss;
    int deposit1, deposit2, deposit3;
    String availablebalance;
    String depositdate, depositamount,withdrawcol,with,total,notes,confirmationno;
       String depositdate1, depositamount1,withdrawcol1,with1,total1,notes1,confirmationno1;
    
    @FXML
    Label BalanceLabel;
    
    @FXML
    TextField ConfirmationNo;
    
    @FXML
    ListView ListView;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
          
       
            Random r= new Random();
            int a[]=new int[1];
          //  r.nextLong();
          for(int i=0; i<a.length; i++){
              a[i]=r.nextInt(50000);
          }
          for (int i: a){
              System.out.println(i+ "ravv");
          }
          //  System.out.println(r.nextLong()+ "r");
        
          
        
        try{
        
        
         Statement statemen=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection co=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statemen=co.createStatement();
            

   
    ResultSet rs=statemen.executeQuery("SELECT(SELECT SUM(deposit.depositamount) FROM khamdu.deposit  WHERE deposit.accountno = " + ids + ")- (SELECT SUM(withdraw.amount) FROM khamdu.withdraw  WHERE withdraw.accountno = " + ids + ")") ;
 
            
            
            
            if(rs.next()){
                availablebalance=rs.getString(1);
               
                
         //   System.out.println("available");
                
                
                
                
                
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MoreAccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MoreAccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         BalanceLabel.setText("Available Balance-  $" +availablebalance);  
         
        
      
       try{
        
        
         Statement statements=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection cos=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statements=cos.createStatement();
            
 
     ResultSet rss=statements.executeQuery("SELECT AccountNo,Notes,ConfirmationNo, DepositDate, DepositAmount,depositcol FROM deposit\n" +
"WHERE accountno= " + "'" + ids + "'"+"\n" +
"UNION ALL\n" +
"SELECT AccountNo,Notes,ConfirmationNo,WithDrawDate,Amount, withdrawcol AS wi FROM withdraw\n" +
"WHERE accountno = " + "'" + ids + "'" +"ORDER BY DepositDate desc") ;
            
            
            
            while(rss.next()){
                depositdate=rss.getString("DepositDate");
                depositamount=rss.getString("depositAmount");
                withdrawcol=rss.getString("depositcol");
                notes=rss.getString("Notes");
                confirmationno=rss.getString("ConfirmationNo");
                
                if(withdrawcol.equals("w")){
                    with="-$";
                }
              
                else{
                    with="$";
                }
          
                
            
                      total=depositdate+ "                                  "+ with+depositamount+"\n"+notes+"\n"+"id:"+confirmationno;
                      list.add(total);
              //  list.addAll(depositdate+ with+depositamount);
             
              // list.addAll(total);
                
                ListView.setItems(list);
             //   String s=ListView.toString();
             
            
              
                
            }
         
         
         
         
         
            
    
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MoreAccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MoreAccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
//    public static int Random(){
//        Random r = new Random();
//        return r.nextInt();
//    }
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
    void Withdraws(MouseEvent event) throws IOException {
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
    void Search(ActionEvent event) {
               try{
             
                      Statement statemen=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection co=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statemen=co.createStatement();
            
     //       SELECT
  //(SELECT SUM(deposit.depositamount) FROM khamdu.deposit  WHERE deposit.accountno = 13) -
   //(SELECT SUM(withdraw.amount) FROM khamdu.withdraw  WHERE withdraw.accountno = 13);
   
//    ResultSet rs=statemen.executeQuery("SELECT AccountNo,Notes,ConfirmationNo, DepositDate, DepositAmount,depositcol FROM deposit\n" +
//"WHERE accountno= " +  ids +   "AND ConfirmationNo= "  + ConfirmationNo.getText() +"\n" +
//"UNION ALL\n" +
//"SELECT AccountNo,Notes,ConfirmationNo,WithDrawDate,Amount, withdrawcol FROM withdraw\n" +
//"WHERE accountno = " + ids  +"AND ConfirmationNo= " +  ConfirmationNo.getText()) ;
   //  ResultSet r=statemen.executeQuery("SELECT DepositAmount FROM deposit WHERE AccountNo= " + "'" + ids + "'");
            
//                ResultSet rs=statemen.executeQuery("SELECT AccountNo,Notes,ConfirmationNo, DepositDate, DepositAmount,depositcol FROM deposit\n" +
//"WHERE accountno= " + "'" + ids + "'"+  "AND ConfirmationNo= " + "'" + ConfirmationNo.getText() + "'"+       "\n" +
//"UNION ALL\n" +
//"SELECT AccountNo,Notes,ConfirmationNo,WithDrawDate,Amount, withdrawcol AS wi FROM withdraw\n" +
//"WHERE accountno = " + "'" + ids + "'" +"AND ConfirmationNo= " + "'" + ConfirmationNo.getText()) ;
                
                    ResultSet rs=statemen.executeQuery("SELECT AccountNo,Notes,ConfirmationNo, DepositDate, DepositAmount,depositcol FROM deposit\n" +
"WHERE accountno= " + "'" + ids + "'"+  "AND ConfirmationNo= " + "'" + ConfirmationNo.getText() + "'"+ "\n" +
"UNION ALL\n" +
"SELECT AccountNo,Notes,ConfirmationNo,WithDrawDate,Amount, withdrawcol AS wi FROM withdraw\n" +
"WHERE accountno = " + "'" + ids + "'" + "AND ConfirmationNo= " + "'" + ConfirmationNo.getText()+ "'");

            
            
            if(rs.next()){
            
                list.clear();
                 depositdate1=rs.getString("DepositDate");
                depositamount1=rs.getString("depositAmount");
                withdrawcol1=rs.getString("depositcol");
                notes1=rs.getString("Notes");
                confirmationno1=rs.getString("ConfirmationNo");
                System.out.println("yahhh"+depositdate1+ withdrawcol1+ confirmationno1);
                
                if(withdrawcol1.equals("w")){
                    with1="-$";
                }
              
                else{
                    with1="$";
                }
          
                
            
                      total1=depositdate1+ "                                  "+ with1+depositamount1+"\n"+notes1+"\n"+"id:"+confirmationno1;
                      list.add(total1);
              //  list.addAll(depositdate+ with+depositamount);
             
              // list.addAll(total);
                
                ListView.setItems(list);
             //   String s=ListView.toStri
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
     @FXML
     void Clear(ActionEvent event)   {
  list.clear();
         try{
        
        
         Statement statements=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection cos=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statements=cos.createStatement();
            
 
     ResultSet rss=statements.executeQuery("SELECT AccountNo,Notes,ConfirmationNo, DepositDate, DepositAmount,depositcol FROM deposit\n" +
"WHERE accountno= " + "'" + ids + "'"+"\n" +
"UNION ALL\n" +
"SELECT AccountNo,Notes,ConfirmationNo,WithDrawDate,Amount, withdrawcol AS wi FROM withdraw\n" +
"WHERE accountno = " + "'" + ids + "'" +"ORDER BY DepositDate desc") ;
            
            
            
            while(rss.next()){
                
                depositdate=rss.getString("DepositDate");
                depositamount=rss.getString("depositAmount");
                withdrawcol=rss.getString("depositcol");
                notes=rss.getString("Notes");
                confirmationno=rss.getString("ConfirmationNo");
                
                if(withdrawcol.equals("w")){
                    with="-$";
                }
              
                else{
                    with="$";
                }
          
                
            
                      total=depositdate+ "                                  "+ with+depositamount+"\n"+notes+"\n"+"id:"+confirmationno;
                      list.add(total);
              //  list.addAll(depositdate+ with+depositamount);
             
              // list.addAll(total);
                
                ListView.setItems(list);
        
            
            }
            }catch (SQLException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
      
}