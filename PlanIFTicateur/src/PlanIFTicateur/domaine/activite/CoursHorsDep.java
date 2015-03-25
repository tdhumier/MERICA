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
public class CoursHorsDep extends Activite
{
    private Color couleur;
    private String type;
    
    
    public CoursHorsDep(int idActivite,
                  String code,
                  float duree,
                  int jour,
                  float heureDebut,
                  String professeur,
                  float heureDebutMin,
                  float heureDebutMax,
                  float heureFinMax)
    {
         super(idActivite,
              code, 
              duree,
              jour,
              heureDebut,
              professeur,
              heureDebutMin,
              heureDebutMax,
              heureFinMax);
       
        this.type = "CoursHorsDep";
        this.couleur = Color.GRAY; 
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
