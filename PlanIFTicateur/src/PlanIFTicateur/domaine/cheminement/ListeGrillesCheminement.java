/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.cheminement;

import PlanIFTicateur.domaine.activite.Activite;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class ListeGrillesCheminement {

    List<GrilleCheminement> grillesCheminement = new ArrayList<>();
    List<Activite> conflits;
    List<Activite> conflitsAvec;
    List<Activite> temp;
    int compteur;

    public ListeGrillesCheminement(List<GrilleCheminement> grilleCheminements) {
        this.grillesCheminement = grilleCheminements;
        conflits = new ArrayList();
        conflitsAvec = new ArrayList();
        temp = new ArrayList();
    }

    public ListeGrillesCheminement() {
        conflits = new ArrayList();
    }
    
    public List<Activite> getConflits() {
        return conflits;
    }
    
    public List<Activite> getConflitsAvec() {
        return conflitsAvec;
    }
    
    public List<GrilleCheminement> getGrillesCheminement() {
        return grillesCheminement;
    }

    public List<Activite> activitesAuMemeHoraire(Activite activite) {
        if (compteur == 0) {
            for (int i = 0; i < conflits.size(); i++){
                temp.add(conflits.get(i));
            }
            compteur = 1;
        }
        grillesCheminement.stream().forEach((grilleCheminement) -> {
            boolean conflit = false;
            for (int i = 0; i < grilleCheminement.activitesAuMemeHoraire(activite).size(); i++)
            {
                if (conflits.contains(grilleCheminement.activitesAuMemeHoraire(activite).get(i)) == false){
                    conflits.add(grilleCheminement.activitesAuMemeHoraire(activite).get(i));
                    conflitsAvec.add(activite);
                } 
                conflit = true;
            }
            if (conflit == false){
                int index = conflits.indexOf(activite);
                if (index != -1){
                    conflitsAvec.remove(index);
                    conflits.remove(activite);
                }
            }
        });
        for (int i = 0; i < temp.size(); i++){
            Activite tempAct =temp.get(i);
            temp.remove(tempAct);
            activitesAuMemeHoraire(tempAct);
        }
        compteur = 0;
        return conflits;
    }

    public List<Activite> activitesCheminementsDejaALHoraire(Activite activite) {
        List<Activite> activitesCheminement = new ArrayList<>();
        for (GrilleCheminement grilleCheminement : grillesCheminement) {
            activitesCheminement.addAll(grilleCheminement.activitesCheminementDejaAlHoraire(activite));
        }
        return activitesCheminement;
    }
}
