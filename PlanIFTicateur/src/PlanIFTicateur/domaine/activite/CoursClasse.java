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
public class CoursClasse extends Activite
{
    private Color couleur;
    private String type;
    
    
    public CoursClasse(
                  String code,
                  String section,
                  String titre,
                  String professeur,
                  String type,
                  float duree,
                  float heureDebutMin,
                  float heureDebutMax,
                  float heureFinMin,
                  float heureFinMax,
                  int jour,
                  float heureDebut
                  )
    {
         super(code,
              section,
              titre,
              professeur,
              type,
              duree,
              heureDebutMin,
              heureDebutMax,
              heureFinMin,
              heureFinMax,
              jour,
              heureDebut
              );
       
        this.type = "CoursClasse";
        this.couleur = Color.RED; 
    }
    
        public Color getCouleur()
    {
        return couleur;
    }
    public String getType()
    {
        return type;
    }
}

