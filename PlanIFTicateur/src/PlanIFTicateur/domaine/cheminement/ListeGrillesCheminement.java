/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.cheminement;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.horaire.Horaire;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class ListeGrillesCheminement {

    List<GrilleCheminement> grillesCheminement = new ArrayList<>();

    public ListeGrillesCheminement(List<GrilleCheminement> grilleCheminements) {
        this.grillesCheminement = grilleCheminements;
    }

    public ListeGrillesCheminement() {
    }

    public List<GrilleCheminement> getListeGrillesCheminement() {
        return grillesCheminement;
    }
    
    public GrilleCheminement getGrillesCheminement(int index)
    {
        return grillesCheminement.get(index);
    }
    
    public int nbActiviteDansGrilleCheminement(Horaire horaire, GrilleCheminement grilleCheminement,int jour)
    {
        int nbCours = 0;
        List<Activite> grilleActivite = grilleCheminement.getListeActivites();
        List<Activite> listeActivite = horaire.getListeActivite().getActivitesByJour(jour);
        
   
        for(int i = 0; i < listeActivite.size(); i ++)
        {
               
            for(int j = 0; j < grilleActivite.size(); j ++)
            {
                if(listeActivite.get(i).getCode().equals(grilleActivite.get(j).getCode()) /*&& horaire.getSession().equals(grilleCheminement.getSession())*/)
                {     
                    nbCours++;
                }
            }
        }
     
        
        return nbCours;
    }
    
    
    public int size()
    {
        return grillesCheminement.size();
    }

    public List<Activite> activitesAuMemeHoraire(Activite activite) {
        List<Activite> activitesAuMemeHoraire = new ArrayList();
        grillesCheminement.stream().forEach((grilleCheminement) -> {
            activitesAuMemeHoraire.addAll(grilleCheminement.activitesAuMemeHoraire(activite));
        });
        return activitesAuMemeHoraire;
    }

    public List<Activite> activitesCheminementsDejaALHoraire(Activite activite) {
        List<Activite> activitesCheminement = new ArrayList<>();
        for (GrilleCheminement grilleCheminement : grillesCheminement) {
            activitesCheminement.addAll(grilleCheminement.activitesCheminementDejaAlHoraire(activite));
        }
        return activitesCheminement;
    }
}
