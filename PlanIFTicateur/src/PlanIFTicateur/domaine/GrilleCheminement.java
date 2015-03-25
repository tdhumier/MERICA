/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import PlanIFTicateur.domaine.activite.Activite;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre
 */
public class GrilleCheminement
{
    private String nomProgramme;
    private String version;
    private String session;
    private ArrayList<Activite> listeActivite;

    public String getNomProgramme() {
        return nomProgramme;
    }

    public String getVersion() {
        return version;
    }

    public String getSession() {
        return session;
    }

    public ArrayList<Activite> getListeActivites() {
        return listeActivite;
    }
    
    public List<Activite> activitesAuMemeHoraire (Activite activite){
        List<Activite> activitesAuMemeHoraire = new ArrayList();
        listeActivite.stream().filter((autreActivite) -> (!activite.equals(autreActivite) && activite.memeHoraire(autreActivite))).forEach((autreActivite) -> {
            activitesAuMemeHoraire.add(autreActivite);
        });
        return activitesAuMemeHoraire;
    }
    
}
