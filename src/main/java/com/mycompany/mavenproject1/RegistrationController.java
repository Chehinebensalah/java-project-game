/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class RegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField mail_;

    @FXML
    private TextField mdp_;

    @FXML
    private TextField nom_;

    @FXML
    private Button open_login_page;

    @FXML
    private TextField prenom_;
    
    @FXML
    private Button registerbtn;
    @FXML
    private TextField username_;
    //DATABASE TOOLS 
    private Connection connect ;
    private PreparedStatement statement ;
    private ResultSet result ;
    
    public Connection connectdb(){
        try{
            Connection connect =DriverManager.getConnection("jdbc:mysql://localhost/jdbcdemo","root","");
            return connect; 
        }catch(Exception e){
            e.printStackTrace();
            
            return null; 
        }
    }
    
    public void registration(ActionEvent event )throws IOException, SQLException{
    
        String nom =nom_.getText();
        String prenom =prenom_.getText();
        String mail =mail_.getText();
        String mdp =mdp_.getText();
        String username =username_.getText();
        if(nom.isEmpty()||prenom.isEmpty()||mail.isEmpty()||mdp.isEmpty()||username.isEmpty()){
            JOptionPane.showMessageDialog(null,"TOUT LES CHAMPS SONT OBLIGATOIRES",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
        }else if(mdp.length()<8){
            JOptionPane.showMessageDialog(null,"Mot de passe trés court minimum 8 caractéres",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
        }
        else{
                connect =connectdb();
                String sql1="select * from joueur where username = ? ";
                statement=connect.prepareStatement(sql1);
                statement.setString(1, mail); 
                statement.setString(2, username); 
                result =statement.executeQuery();
                if(result.next()){
                    JOptionPane.showMessageDialog(null,"Compte existant!",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
                }else{
                    try{ 
                connect=connectdb();
                String sql="insert into joueur(nom,prenom,mail,username,mdp,score,level)values(?,?,?,?,?,?,?)";
              statement= connect.prepareStatement(sql);
              statement.setString(1,nom);
              statement.setString(2 ,prenom);
              statement.setString(3 ,mail);
              statement.setString(4 ,username);
              statement.setString(5 ,mdp);
              statement.setString(6 ,"0");
              statement.setString(7 ,"BEGINNER");
              statement.executeUpdate();
              JOptionPane.showMessageDialog(null,"inscription reussite");
         }
              catch (Exception e){e.printStackTrace();}
                }
            
        }
        /*  try{ 
                connect=connectdb();
                String sql="insert into joueur(nom,prenom,mail,mdp,score,level)values(?,?,?,?,?,?)";
              statement= connect.prepareStatement(sql);
              statement.setString(1,nom);
              statement.setString(2 ,prenom);
              statement.setString(3 ,mail);
              statement.setString(4 ,mdp);
              statement.setString(5 ,"0");
              statement.setString(6 ,"BEGINNER");
              statement.executeUpdate();
              JOptionPane.showMessageDialog(null,"inscription reussite");
         }
              catch (Exception e){e.printStackTrace();}*/
        
    }
    
    public void open_login_page(ActionEvent event) throws IOException{
            open_login_page.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
           // DashboardController dc = new DashboardController();
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
