/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author MSI
 */
public class joueur {
    private String nom ; 
    private String prenom ; 
    private String username ;
    private String pwd ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    private String mail ;
    private String level; 
    private int score=0;

    public joueur(String nom, String prenom, String mail, String username, String pwd, String level) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.username = username;
        this.pwd = pwd;
        this.level = level;
    }

    @Override
    public String toString() {
        return "joueur{" + "nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", pwd=" + pwd + ", mail=" + mail + ", level=" + level + ", score=" + score + '}';
    }
}
