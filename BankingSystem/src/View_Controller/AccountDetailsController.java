/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Classes.LogIn;
import static View_Controller.LogInController.ids;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AccountDetailsController implements Initializable {

    @FXML
    ListView ListView;
    @FXML
    TextField username, password, id, type;
    // String username, password;
    @FXML
            MenuButton myMenuBar;

    String gm;
    String la;
    static String acc;
     String account;

    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      System.out.println(ids+"id");
        try {
                Statement statements=null;
             
                   Class.forName("com.mysql.jdbc.Driver");
        Connection cons=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
        statements=cons.createStatement();
        ResultSet rss=statements.executeQuery("SELECT AccountType FROM newuser WHERE ID= " + "'" + ids + "'"); 
           
            
            
            
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
            
            
            
            
            
            
            
            
            
            
            
            
            Statement statemen=null;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection co=DriverManager.getConnection("jdbc:mysql://den1.mysql6.gear.host:3306/khamdu", "khamdu", "Sq1HK_2S_fn0");
            statemen=co.createStatement();
            
     //       SELECT
  //(SELECT SUM(deposit.depositamount) FROM khamdu.deposit  WHERE deposit.accountno = 13) -
   //(SELECT SUM(withdraw.amount) FROM khamdu.withdraw  WHERE withdraw.accountno = 13);
   
    ResultSet rs=statemen.executeQuery("SELECT(SELECT COALESCE(SUM(deposit.depositamount),0) FROM khamdu.deposit  WHERE deposit.accountno = " + ids + ")- (SELECT COALESCE(SUM(withdraw.amount),0)FROM khamdu.withdraw  WHERE withdraw.accountno = " + ids + ")") ;
   //  ResultSet r=statemen.executeQuery("SELECT DepositAmount FROM deposit WHERE AccountNo= " + "'" + ids + "'");
            
            
            
            if(rs.next()){
                String ba=rs.getString(1);
                String st=(account +"- "+ ids + "     "+ba);
                list.add(st);
                ListView.setItems(list);
                
            System.out.println("13");
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            

    }

//    public void setusernames(String accs) {
//
//        this.acc = accs;
//        list.add(acc);
//        ListView.setItems(list);
//    }
//
//    public String getusernames() {
//        return acc + "kk";
//
//    }

    @FXML
    void listclicked(MouseEvent event) throws IOException {
//  ListView.getSelectionModel().getSelectedItems();
//  ObservableList <ListView> s= FXCollections.observableArrayList();
//  s.add(ListView);
//  if(ListView.getSelectionModel().getSelectedItems().contains("77")){
//      System.out.println("cc");
//      
//  }

        String ja = ListView.getSelectionModel().getSelectedItems().toString();
        System.out.println(ja + "jj");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/MoreAccountDetails.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML
    void withdraws(MouseEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/WithDraw.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    void deposit(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/Deposit.fxml"));
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
//      @FXML
//    void Logout(ActionEvent event) throws IOException {
//           FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/View_Controller/LogIn.fxml"));
//        Parent tableViewParent = loader.load();
//
//        Scene tableViewScene = new Scene(tableViewParent);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        window.setScene(tableViewScene);
//        window.show();
//
//    }
    
         @FXML
    void Logout(ActionEvent event) throws IOException {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/View_Controller/LogIn.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
        Stage stage1 = (Stage) myMenuBar.getScene().getWindow();
       stage1.setScene(home_page_scene);
        stage1.show();

    }

}
