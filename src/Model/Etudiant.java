/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author junior
 */
public class Etudiant {
    private String ncin;
    private String nom;
    private String prenom;

    public String getNcin() {
        return ncin;
    }

    public void setNcin(String ncin) {
        this.ncin = ncin;
    }

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

    public Etudiant() {
    }

    public Etudiant(String ncin, String nom, String prenom) {
        this.ncin = ncin;
        this.nom = nom;
        this.prenom = prenom;
    }
    
}
