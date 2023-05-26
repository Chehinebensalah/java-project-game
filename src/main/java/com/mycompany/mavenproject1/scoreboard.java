package com.mycompany.mavenproject1;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class scoreboard {
// private  final IntegerProperty rank;
 private final StringProperty username;
  private  final IntegerProperty bestscore;
 // private final StringProperty level1;

   
  /*  public int getRank() {
        return rank.get();
    }

    public void setRank(int r) {
       rank.set(r);
    }*/
    
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String d) {
       username.set(d);
    }
     public int getBestscore() {
        return bestscore.get();
    }

    public void setBestscore(int r) {
       bestscore.set(r);
    }
  /*     public String getLevel() {
        return level1.get();
    }

    public void setLevel(String d) {
       level1.set(d);
    }
*/
    public scoreboard( String username , int bestscore) {
        this.username = new SimpleStringProperty(username);
        this.bestscore = new SimpleIntegerProperty(bestscore);
 }
  /*  public scoreboard( int rank,String username , int bestscore , String level1) {
     this.rank = new SimpleIntegerProperty(rank);
        this.username = new SimpleStringProperty(username);
        this.bestscore = new SimpleIntegerProperty(bestscore);
           this.level1 = new SimpleStringProperty(level1);
 }*/
  // public IntegerProperty rankProperty(){return rank;}
    public StringProperty usernameProperty(){return username;}
    public IntegerProperty bestscoreProperty(){return bestscore;}
   // public StringProperty level1Property(){return level1;}

    
}

