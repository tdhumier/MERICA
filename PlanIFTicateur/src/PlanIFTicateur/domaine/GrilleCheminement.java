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
public class GrilleCheminement {

    private String nomProgramme;
    private String version;
    private String session;
    private ArrayList<Activite> listeActivite;

    public GrilleCheminement() {
    }

    public GrilleCheminement(String nomProgramme, String version, String session) {
        this.nomProgramme = nomProgramme;
        this.version = version;
        this.session = session;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setListeActivite(ArrayList<Activite> listeActivite) {
        this.listeActivite = listeActivite;
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

    public boolean activitePresente(Activite activite) {
        return listeActivite.stream().filter(x -> x.equals(x)).findFirst().isPresent();
    }

    public List<Activite> activitesAuMemeHoraire(Activite activite) {
        List<Activite> activitesAuMemeHoraire = new ArrayList();
        listeActivite.stream().filter((autreActivite) -> (!activite.equals(autreActivite) && activite.memeHoraire(autreActivite))).forEach((autreActivite) -> {
            activitesAuMemeHoraire.add(autreActivite);
        });
        return activitesAuMemeHoraire;
    }

    public List<Activite> activitesCheminementDejaAlHoraire(Activite activite) {
        List<Activite> activitesCheminement = new ArrayList<>();
        if (activitePresente(activite)) {
            activitesCheminement.stream().filter(x -> x.isAssignee()).collect(Collectors.toList());
        }
        return activitesCheminement;
    }
}
