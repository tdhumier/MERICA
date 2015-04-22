/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Alexandre
 */
public class Statistique {

    private int nbCours;
    private int nbMaxCoursEtudiantMemeJour;
    private float nbMoyenCoursEtudiantMemeJour;
    private float indiceCongestion;
    private float indiceCovoiturage;
    private ArrayList<Integer> coursSemaine;
    private List<Activite> listeCoursJournee;

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

    public int nbMaxCoursParJour(Horaire horaire, ListeGrillesCheminement listeGrillesCheminement, int jour) {
        nbMaxCoursEtudiantMemeJour = 0;
        listeCoursJournee = horaire.getListeActivite().getActivitesByJour(jour);

        int nombreEnCalculation;

        for (int i = 0; i < listeGrillesCheminement.size(); i++) {
            nombreEnCalculation = listeGrillesCheminement.nbActiviteDansGrilleCheminement(horaire, listeGrillesCheminement.getGrillesCheminement(i), jour);

            if (nombreEnCalculation > nbMaxCoursEtudiantMemeJour) {
                nbMaxCoursEtudiantMemeJour = nombreEnCalculation;
            }
        }

        return nbMaxCoursEtudiantMemeJour;
    }

    public float nbMoyenCoursParJour(Horaire horaire, ListeGrillesCheminement listeGrillesCheminement, int jour) {
        int nombreEnCalculation;
        int nombreTotalActivites = 0;
        nbMoyenCoursEtudiantMemeJour = 0;
        for (int i = 0; i < listeGrillesCheminement.size(); i++) {
            nombreEnCalculation = listeGrillesCheminement.nbActiviteDansGrilleCheminement(horaire, listeGrillesCheminement.getGrillesCheminement(i), jour);
            if (nombreEnCalculation != 0) {
                nombreTotalActivites++;
                nbMoyenCoursEtudiantMemeJour += nombreEnCalculation;
            }
        }

        nbMoyenCoursEtudiantMemeJour = nbMoyenCoursEtudiantMemeJour / nombreTotalActivites;
        if (nombreTotalActivites == 0) {
            nbMoyenCoursEtudiantMemeJour = 0;
        }

        return nbMoyenCoursEtudiantMemeJour;
    }

    public float congestionCirculation(Horaire horaire, int jour) {
        int compteurCoursDebut = 0;
        listeCoursJournee = horaire.getListeActivite().getActivitesByJour(jour);

        if (listeCoursJournee.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listeCoursJournee.size(); i++) {

            if (listeCoursJournee.get(i).getHeureDebut() == 8.5) {
                compteurCoursDebut++;
            }
        }
        indiceCongestion = (compteurCoursDebut * 100) / listeCoursJournee.size();

        return indiceCongestion;
    }

    /**
     * Fonction qui calcul l'indice de covoiturage pour une journée donnée à
     * partir de l'horaire et de la liste des grilles de cheminement
     *
     * @param horaire
     * @param listeGrillesCheminement
     * @param jour
     * @return l'indice de covoiturage pour la journée donnée
     */
    public float indiceCovoiturage(Horaire horaire, ListeGrillesCheminement listeGrillesCheminement, int jour) {
        listeCoursJournee = horaire.getListeActivite().getActivitesByJour(jour);

        if (listeCoursJournee.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < listeCoursJournee.size(); i++) {

        }
        /* for(int i = 0; i < listeGrillesCheminement.size(); i++)
         {
         for(int j = i+1; j < listeGrillesCheminement.size(); j++)
         {
         if(activitePlusTot(listeGrillesCheminement.getGrillesCheminement(i)) == activitePlusTot(listeGrillesCheminement.getGrillesCheminement(j))
         && (activitePlusTard(listeGrillesCheminement.getGrillesCheminement(j)) ==  activitePlusTard(listeGrillesCheminement.getGrillesCheminement(j))))
         {
         indiceCovoiturage++;
         }
         }
         }*/

        return indiceCovoiturage;
    }

    public JLabel[] modificationIntegerLabel(String[] jourModes, JLabel[] labelModes, ArrayList<Integer> list, String texte) {

        for (int i = 0; i < list.size(); i++) {
            labelModes[i].setText(jourModes[i] + " : " + list.get(i) + texte);
        }
        return labelModes;
    }

    public JLabel[] modificationFloatLabel(String[] jourModes, JLabel[] labelModes, ArrayList<Float> list, String texte) {

        for (int i = 0; i < list.size(); i++) {
            labelModes[i].setText(jourModes[i] + " : " + list.get(i) + texte);
        }
        return labelModes;
    }

}
