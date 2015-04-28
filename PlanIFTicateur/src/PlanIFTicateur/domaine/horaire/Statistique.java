/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.GrilleCheminement;
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
        coursSemaine = new ArrayList<>();
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
                if(listeGrillesCheminement.getGrillesCheminement(i).getSession().equals(horaire.getSession()))
                {
                    nombreEnCalculation = listeGrillesCheminement.nbActiviteDansGrilleCheminement(horaire, listeGrillesCheminement.getGrillesCheminement(i), jour);

                     if (nombreEnCalculation > nbMaxCoursEtudiantMemeJour) {
                            nbMaxCoursEtudiantMemeJour = nombreEnCalculation;
                }
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
            if (nombreEnCalculation > 0) {
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
        compteurCoursDebut = listeCoursJournee.stream().filter((listeCoursJournee1) -> (listeCoursJournee1.getHeureDebut() == 8.5 && !listeCoursJournee1.getType().equals("À distance"))).map((_item) -> 1).reduce(compteurCoursDebut, Integer::sum);
        indiceCongestion = (compteurCoursDebut * 100) / listeCoursJournee.size();

        return indiceCongestion;
    }

    public float indiceCovoiturage(Horaire horaire, ListeGrillesCheminement listeGrillesCheminement, int jour) {
        ListeActivites listeCours = new ListeActivites(horaire.getListeActivite().getActivitesByJour(jour));

        if (listeCours.getListeActivites().isEmpty()) {
            return 0;
        }

        Activite coursJourLePlusTot = listeCours.activitePlusTot();
        Activite coursJourLePlusTard = listeCours.getActiviteFiniPlusTard();

        int tot = 0;
        int cov = 0;
        float indice;

        for (GrilleCheminement grilleCheminement : listeGrillesCheminement.getListeGrillesCheminement()) {
            Activite coursLePlusTot = grilleCheminement.getActiviteCommencePlusTotParJour(jour);
            Activite coursLePlusTard = grilleCheminement.getActiviteFiniPlusTardParJour(jour);

            if (coursLePlusTot != null && coursLePlusTard != null) {
                if (coursLePlusTot != coursJourLePlusTot && coursJourLePlusTard != coursLePlusTard) {
                    if (coursLePlusTot.getHeureDebut() == coursJourLePlusTot.getHeureDebut() && coursLePlusTard.getHeureDebut() + coursLePlusTard.getDuree() == coursJourLePlusTard.getHeureDebut() + coursJourLePlusTard.getDuree()) {
                        cov++;
                    }
                }
            }
            tot++;
        }
        if(tot != 0)
            indice = ((float) cov / (float) tot) * 100;
        else
            indice = 0;
        
        return indice;
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
