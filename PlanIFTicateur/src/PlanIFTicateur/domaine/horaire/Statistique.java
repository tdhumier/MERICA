/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class Statistique {

    private int nbCours;
    private int nbMaxCoursEtudiantMemeJour;
    private int nbMoyenCoursEtudiantMemeJour;
    private float indiceCongestion;
    private ArrayList<Integer> coursSemaine;

    public int getNbCoursParJour(Horaire horaire, int jour) {
        nbCours = horaire.getListeActivite().getActivitesByJour(jour).size();
        return nbCours;
    }

    public ArrayList<Integer> getNbCoursSemaine(Horaire horaire) {
        coursSemaine = new ArrayList<Integer>();
        for (int i = 1; i < 7; i++) {
            coursSemaine.add(getNbCoursParJour(horaire, i));
        }

        return coursSemaine;
    }

    public int nbMaxCoursParJour(Horaire horaire)
    {
        
        return nbMaxCoursEtudiantMemeJour;
    }

    public int nbMoyenCoursParJour(Horaire horaire) {
        return nbMoyenCoursEtudiantMemeJour;
    }

    public float congestionCirculation(Horaire horaire, int jour) 
    {
       int compteurCoursDebut = 0;
       List<Activite> listeCoursJournee = horaire.getListeActivite().getActivitesByJour(jour);
       
             if(listeCoursJournee.isEmpty())
           {
               return 0;
           }
       System.out.println(listeCoursJournee);
       for (int i = 0; i < listeCoursJournee.size(); i++)
       {
     
           if(listeCoursJournee.get(i).getHeureDebut() == 8.5)
           {
               compteurCoursDebut++;
           }
       }
       indiceCongestion = (compteurCoursDebut * 100) / listeCoursJournee.size();
        
        return indiceCongestion;
    }

}
