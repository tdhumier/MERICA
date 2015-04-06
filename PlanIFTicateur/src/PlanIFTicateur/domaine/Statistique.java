/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import java.util.HashMap;

/**
 *
 * @author Alexandre
 */
public class Statistique {

    private int nbCours;
    private int nbMaxCoursEtudiantMemeJour;
    private int nbMoyenCoursEtudiantMemeJour;
    private float indiceCongestion;
    private float indiceCovoiturage;
    private HashMap<String, Integer> coursSemaine;

    public int getNbCoursParJour(Horaire horaire, int jour) {
        nbCours = horaire.getListeActivite().getActivitesByJour(jour).size();
        return nbCours;
    }

    public HashMap getNbCoursSemaine(Horaire horaire) {
        coursSemaine = new HashMap<String, Integer>();
        coursSemaine.put("Lundi", getNbCoursParJour(horaire, 1));
        coursSemaine.put("Mardi", getNbCoursParJour(horaire, 2));
        coursSemaine.put("Mercredi", getNbCoursParJour(horaire, 3));
        coursSemaine.put("Jeudi", getNbCoursParJour(horaire, 4));
        coursSemaine.put("Vendredi", getNbCoursParJour(horaire, 5));
        coursSemaine.put("Samedi", getNbCoursParJour(horaire, 6));
        coursSemaine.put("Dimanche", getNbCoursParJour(horaire, 7));
        return coursSemaine;
    }

    public int nbMaxCoursParJour(Horaire horaire) {
        return nbMaxCoursEtudiantMemeJour;
    }

    public int nbMoyenCoursParJour(Horaire horaire) {
        return nbMoyenCoursEtudiantMemeJour;
    }

    public float congestionCirculation(Horaire horaire) {
        return indiceCongestion;
    }

}
