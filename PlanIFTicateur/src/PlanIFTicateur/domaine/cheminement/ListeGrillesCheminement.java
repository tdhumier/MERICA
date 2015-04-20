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

    public ListeGrillesCheminement(List<GrilleCheminement> grilleCheminements) {
        this.grillesCheminement = grilleCheminements;
    }

    public ListeGrillesCheminement() {
    }
    
    public List<Activite> getConflits() {
        return conflits;
    }
    
    public List<GrilleCheminement> getGrillesCheminement() {
        return grillesCheminement;
    }

    public List<Activite> activitesAuMemeHoraire(Activite activite) {
        conflits = new ArrayList();
        grillesCheminement.stream().forEach((grilleCheminement) -> {
            for (int i = 0; i < grilleCheminement.activitesAuMemeHoraire(activite).size(); i++)
            {
                if (conflits.contains(grilleCheminement.activitesAuMemeHoraire(activite).get(i)) == false){
                    conflits.add(grilleCheminement.activitesAuMemeHoraire(activite).get(i));
                }
            }
        });
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
