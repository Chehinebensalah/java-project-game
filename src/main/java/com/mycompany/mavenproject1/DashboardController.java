/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.game.*;
import java.awt.Desktop;
import java.awt.Label;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class DashboardController implements Initializable {

   

        @FXML
    private Button acceuilbtn;

    @FXML
    private Pane acceuilpane;

    @FXML
    private TextField confirmnewmdpconfig;

    @FXML
    private Button disconnect;

    @FXML
    private Button historiquebtn;

    @FXML
    private Pane historiquepane;

    @FXML
    private Text levelconfig;

    @FXML
    private Text mailconfig;

    @FXML
    private TextField mdpconfig;

    @FXML
    private Button modifierprofilbtn;

    @FXML
    private Pane modifierprofilpane;

    @FXML
    private Text name;

    @FXML
    private TextField newmdpconfig;

    @FXML
    private Text nomconfig;

    @FXML
    private Button play;

    @FXML
    private Text prenomconfig;

    @FXML
    private Button scoreboardbtn;

    @FXML
    private Pane scoreboardpane;

    @FXML
    private Text usernameconfig;
    @FXML
    private Button aproposbtn;

    @FXML
    private Pane apropospane;
    
    @FXML
    private Hyperlink chehinelinkedin;
    @FXML
    private Hyperlink mariemlinkedin;
    @FXML
    private Hyperlink linkedinmanel;
    
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
    
    public void chehineOLink(ActionEvent event) throws URISyntaxException,IOException{
        Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/ben-salah-chehine-b0bb90216/"));
    }
    public void manelOLink(ActionEvent event) throws URISyntaxException,IOException{
        Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/manel-ben-mouloud-6a588a220/"));
    }
    public void mariemOLink(ActionEvent event) throws URISyntaxException,IOException{
        Desktop.getDesktop().browse(    new URI("https://www.linkedin.com/in/mariem-fekih-03b406229/")
);
    }
    
    public void disconnect(ActionEvent event) throws IOException{
            disconnect.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            joueur j = (joueur) stage.getUserData();
            //System.out.println(j.toString());
            stage.setScene(scene);
            stage.show();
            
                 
    }
    public void startgame(ActionEvent event) throws IOException{
        GameFrame frame = new GameFrame();
    }
    
    public void handclicks(ActionEvent event){
        if(event.getSource()==acceuilbtn){
            acceuilpane.toFront();
        }
        if(event.getSource()==modifierprofilbtn){
            setconfigprofile(event);
            modifierprofilpane.toFront();
        }
        if(event.getSource()==historiquebtn){
            loadData(event);
            historiquepane.toFront();
        }
        if(event.getSource()==scoreboardbtn){
            loadscoreboard(event);
            scoreboardpane.toFront();
        }
        if(event.getSource()==aproposbtn){
            apropospane.toFront();
        }
    }
    public void setconfigprofile(ActionEvent event ){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            joueur j = (joueur) stage.getUserData();
            //System.out.println(j.toString());
            nomconfig.setText(j.getNom().toString());
            prenomconfig.setText(j.getPrenom().toString());
            mailconfig.setText(j.getMail().toString());
            usernameconfig.setText(j.getUsername().toString());
            levelconfig.setText(j.getLevel().toString());
            //mdpconfig.setText(j.getPwd().toString());
    }
    
    
    public void newmdp(ActionEvent event) throws SQLException{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            joueur j = (joueur) stage.getUserData();
            System.out.print(j.getPwd().toString());
            connect =connectdb();
            
            if(j.getPwd().compareTo(mdpconfig.getText())!=0){
                JOptionPane.showMessageDialog(null,"Ancien mot de passe incorrecte",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                if(newmdpconfig.getText().toString().compareTo(confirmnewmdpconfig.getText().toString())!=0){
                    JOptionPane.showMessageDialog(null,"Confirmer le mdp",
                       "ERREUR " , JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                    String sql="update joueur set mdp =? where username =? ";
                    try {
                        statement=connect.prepareStatement(sql);
                        statement.setString(1, newmdpconfig.getText());
                        statement.setString(2, j.getUsername());
                        if(statement.executeUpdate()>0){
                            JOptionPane.showMessageDialog(null,"MODIFICATION TERMINE",
                       "MESSAGE " , JOptionPane.INFORMATION_MESSAGE);
                        };
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
    }
    
     
    private Connection connecthisto ;
    private PreparedStatement statementhisto ;
    private ResultSet resulthisto ;
    
    public ObservableList<Historique>data; 
        @FXML
    private TableView<Historique> table;
 
   @FXML
    private TableColumn<Historique, String> date;
       @FXML
    private TableColumn<Historique, Integer> joueur_1;

   

    @FXML
    private Text level;
    
    public Connection histoconnectdb(){
        try{
            connecthisto =DriverManager.getConnection("jdbc:mysql://localhost/jdbcdemo","root","");
            return connecthisto; 
        }catch(Exception e){
            e.printStackTrace();
            
            return null; 
        }
    }
      @FXML
    void loadData(ActionEvent event) {
     try {
         connecthisto =histoconnectdb();
         data=FXCollections.observableArrayList();
         
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            joueur j = (joueur) stage.getUserData();
                String sql2="select date,joueur_1 from historique WHERE nom_joueur_1= ?  ";
                statement=connecthisto.prepareStatement(sql2);
                statement.setString(1, j.getUsername());
                result=statement.executeQuery();
          
         while (result.next()){
          data.add(new Historique(result.getString(1),result.getInt(2)));
          
          }
     
     
     } catch (SQLException ex) {System.err.println( "Error"+ ex ); }
joueur_1.setCellValueFactory(new PropertyValueFactory<Historique,Integer>("score"));
       date.setCellValueFactory(new PropertyValueFactory<Historique,String>("date"));
 table.setItems(null );
  table.setItems(data);
 
     ///leveeeeel
   
   ArrayList a = new ArrayList<Integer>() ;
    for(int i=0; i<table.getItems().size(); i++ )
    {
      a.add((int) joueur_1.getCellData( i ));}
    int max = (int) a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (max <(int) a.get(i))
                max = (int)a.get(i);
        }
      if( max<10 ){level.setText("DÉBUTANT");} 
      else if(max>=10 || max<30) { level.setText("AMATEUR");}
       else if(max>=30 || max<50) { level.setText("INTERMÉDIAIRE");}
      else{level.setText(" EXPERT ");}
  }
                
    //scoreboard 
    
    @FXML
    private TableColumn<scoreboard, Integer> bestscore;
    @FXML
    private TableView<scoreboard> tablesc;

    @FXML
    private TableColumn<scoreboard, String> username;
    
   /* @FXML
    private TableColumn<scoreboard, Integer> rank;
        @FXML
    private TableColumn<scoreboard, String> level1;*/
        
   public ObservableList<scoreboard>testdata; 
   private Connection  connects;
         
    public Connection scoreconnectdb(){
        try{
            connects =DriverManager.getConnection("jdbc:mysql://localhost/jdbcdemo","root","");
            return connects; 
        }catch(SQLException e){
            
            return null; 
        }
    }
    public void loadscoreboard(ActionEvent event){
            try {
         connects =scoreconnectdb();
         testdata=FXCollections.observableArrayList();
         result=connects.createStatement().executeQuery("select  nom_joueur_1,max(joueur_1) from historique group by nom_joueur_1");
          while (result.next()){
         testdata.add(new scoreboard(result.getString(1),result.getInt(2)));
          }
     } catch (SQLException ex) {System.err.println( "Error"+ ex ); }

username.setCellValueFactory(new PropertyValueFactory<>("username"));
bestscore.setCellValueFactory(new PropertyValueFactory<>("bestscore"));


 tablesc.setItems(null );
  tablesc.setItems(testdata);
 
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        acceuilpane.toFront();
    
    
        
        
        

        
        
    }    
    
}



/*public void setname(ActionEvent event ) throws IOException{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            joueur j = (joueur) stage.getUserData();
            System.out.println(j.toString());
            name.setText("Bienvenue " + j.getNom() + " " + j.getPrenom());
            
    }*/