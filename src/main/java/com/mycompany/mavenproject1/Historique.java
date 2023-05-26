
package com.mycompany.mavenproject1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Historique {

  
   private final StringProperty date;
  private  final IntegerProperty joueur_1;

    Historique() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getJoueur_1() {
        return joueur_1.get();
    }

    public void setJoueur_1(int j) {
        joueur_1.set(j);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String d) {
       date.set(d);
    }

    public Historique( String date,int joueur_1) {
    this.date = new SimpleStringProperty(date);
        this.joueur_1 = new SimpleIntegerProperty(joueur_1);
   
     }
   
    public StringProperty dateProperty(){return date;}
    public IntegerProperty scoreProperty(){return joueur_1;}


}
