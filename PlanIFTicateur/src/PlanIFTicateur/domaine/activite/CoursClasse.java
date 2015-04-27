/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.activite;

import java.awt.Color;

/**
 *
 * @author Alexandre
 */
public class CoursClasse extends Activite {

    private String type;
    private Color couleur = new Color(255, 0, 0, 50);

    public CoursClasse(String code, String section, String titre, String professeur, String type, double duree, double heureDebutMin, double heureFinMax, int jour, double heureDebut) {
        super(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
        this.type = "Classe";
        this.couleur = couleur;
        int i = 0;
    }

    public Color getCouleur() {
        return couleur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setCouleurSelectionner() {
        this.couleur = Color.gray; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reinitCouleur() {
        this.couleur = new Color(255, 0, 0, 50);    
    }

    @Override
    public Activite clone() {
       return this.clone();
    }
}
