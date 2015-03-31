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
public class Laboratoire extends Activite
{
    protected Color couleur;
    private String type;
    
    public Laboratoire(String code,
                  String section,
                  String titre,
                  String professeur,
                  String type,
                  float duree,
                  float heureDebutMin,
                  float heureDebutMax,
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
              heureFinMax,
              jour,
              heureDebut
              );
         
        this.type = "Laboratoire";
        this.couleur = Color.GREEN;
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
