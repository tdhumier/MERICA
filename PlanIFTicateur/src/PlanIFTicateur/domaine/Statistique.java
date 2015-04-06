/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

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
    private float indiceCovoiturage;
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
