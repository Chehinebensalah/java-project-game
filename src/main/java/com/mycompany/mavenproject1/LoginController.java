/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.*;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnnewaccount;
        
    @FXML
    private Button btnvalider;

    @FXML
    private TextField mail;

     @FXML
    private PasswordField password;
    
    //DATABASE TOOLS 
    private Connection connect ;
    private PreparedStatement statement ;
    private ResultSet result ;
    
    
    
    
    public Connection connectdb(){
        try{
            connect =DriverManager.getConnection("jdbc:mysql://localhost/jdbcdemo","root","");
            return connect; 
        }catch(Exception e){
            e.printStackTrace();
            
            return null; 
        }
    }
    public void login(ActionEvent event){
        connect =connectdb();
        try{
            String sql="select * from joueur where username = ? and mdp = ? ";
            statement=connect.prepareStatement(sql);
            statement.setString(1, mail.getText());
            statement.setString(2, password.getText());
            result =statement.executeQuery();
            
            if(result.next()){
                joueur j = new joueur(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6));
                Node node = (Node) event.getSource(); //bch ab3eth les donnees mn scene l scene
                Stage stage = (Stage) node.getScene().getWindow();
                
                btnvalider.getScene().getWindow().hide();
                
                UserShared.user_mail = mail.getText();

                Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                stage.setUserData(j);
                Scene scene = new Scene(root);
               
                //DashboardController dc = new DashboardController();
                //dc.setname(event);
                stage.setScene(scene);
                stage.close();
                
                stage.show();
                
                
            }else{
                
                JOptionPane.showMessageDialog(null,"MAIL OU MOT DE PASSE INCORRECT",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void open_reg_page(ActionEvent event) throws IOException{
            btnnewaccount.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
    }    
    
}
