/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.cheminement;

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
        return listeActivite.stream().filter(x -> x.equals(activite)).findFirst().isPresent();
    }

    public List<Activite> activitesAuMemeHoraire(Activite activite) {
        List<Activite> activitesAuMemeHoraire = new ArrayList();
        listeActivite.stream().filter((autreActivite) -> (!activite.equals(autreActivite) && activitePresente(activite) && activite.estAuMemeHoraire(autreActivite))).forEach((autreActivite) -> {
            activitesAuMemeHoraire.add(autreActivite);
            activitesAuMemeHoraire.add(activite);
        });
        return activitesAuMemeHoraire;
    }

    public List<Activite> activitesCheminementDejaAlHoraire(Activite activite) {
        List<Activite> activitesCheminement = new ArrayList<>();
        if (activitePresente(activite)) {
            activitesCheminement = listeActivite.stream().filter(x -> x.isAssignee() && !x.equals(activite)).collect(Collectors.toList());
        }
        return activitesCheminement;
    }

    /**
     * Fonction qui retourne l'activite de la grille de cheminement qui commence
     * le plus tôt À modifier en conséquent afin d'être utile au calcul de
     * l'indice de covoiturage
     *
     * @return l,activité qui commence le plus tôt
     */
    public Activite activitePlusTot() {
        Activite activite = getListeActivites().get(0);
        for (int i = 1; i < getListeActivites().size(); i++) {
            if (getListeActivites().get(i).getHeureDebut() < activite.getHeureDebut()) {
                activite = getListeActivites().get(i);
            }
        }
        return activite;
    }

    /**
     * Fonction qui renvoie le cours de la grille de cheminement qui commence le
     * plus tard À modifier en conséquent afin d'être utile au calcul de
     * l'indice de covoiturage
     *
     * @return l'activié qui commence le plus tard
     */
    public Activite activitePlusTard() {
        Activite activite = getListeActivites().get(0);
        for (int i = 1; i < getListeActivites().size(); i++) {
            if (getListeActivites().get(i).getHeureDebut() < activite.getHeureDebut()) {
                activite = getListeActivites().get(i);
            }
        }
        return activite;
    }

    // Pour avoir l'activite qui finit le plus tard en fonction du jour.
    public Activite getActiviteFiniPlusTardParJour(int jour) {
        Activite activitePlusTard = null;
        double heureFin = 0;
        List<Activite> activiteList = getListeActivites();
        for (Activite activite : activiteList) {
            if (activite.getJour() == jour && activite.getHeureDebut() != 0.0d && activite.getHeureDebut() + activite.getDuree() > heureFin) {
                activitePlusTard = activite;
                heureFin = activite.getHeureDebut() + activite.getDuree();
            }
        }
        return activitePlusTard;
    }

    // Pour avoir l'activite qui finit le plus tard en fonction du jour.
    public Activite getActiviteCommencePlusTotParJour(int jour) {
        Activite activitePlusTot = null;
        double heureDebut = 22;
        List<Activite> activiteList = getListeActivites();
        for (Activite activite : activiteList) {
            if (activite.getJour() == jour && activite.getHeureDebut() != 0.0d && activite.getHeureDebut() < heureDebut) {
                activitePlusTot = activite;
                heureDebut = activite.getHeureDebut();
            }
        }
        return activitePlusTot;
    }
}
