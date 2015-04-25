/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.conflit.Conflit;
import PlanIFTicateur.domaine.conflit.ConflitCheminement;
import PlanIFTicateur.domaine.conflit.ConflitHoraire;
import PlanIFTicateur.domaine.conflit.ListeConflits;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Horaire {

    private boolean valide;
    private String session;
    private ListeActivites listeActivite;
    private ListeGrillesCheminement grillesCheminement;
    private ListeConflits listeConflits;
    private Statistique statistique;

    public Horaire() {
        this.listeActivite = new ListeActivites();
        this.grillesCheminement = new ListeGrillesCheminement();
        this.listeConflits = new ListeConflits();
        this.statistique = new Statistique();
        this.valide = true;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public ListeActivites getListeActivite() {
        return listeActivite;
    }

    public Statistique getStatistiques() {
        return statistique;
    }

    public ListeConflits getListeConflits() {
        return listeConflits;
    }

    public void setListeActivite(ListeActivites listeActivite) {
        this.listeActivite = listeActivite;
    }

    public void setGrillesCheminement(ListeGrillesCheminement grillesCheminement) {
        this.grillesCheminement = grillesCheminement;
    }

    public ListeGrillesCheminement getListeGrillesCheminement() {
        return grillesCheminement;
    }

    public void deplacerActivite(Activite activite, int x, int y) {
        activite.deplacerActivite(x, y);
    }

    public void deplacerActivite(Activite activite, Point point, double heure, int jour) {
        activite.deplacerActivite(point, heure, jour);
        activite.setIsSelected(false);
        verifierConflits(activite);
    }

    public void deplacerActiviteAvecVerification(Activite activite, Point point, double heure, int jour, Dimension dimension) {
        double oldHeure = activite.getHeureDebut();
        int oldJour = activite.getJour();
        activite.setHeureDebut(heure);
        activite.setJour(jour);
        if (deplacementEstValide(activite)) {
            activite.deplacerActivite(point.x, point.y);
        } else {
            activite.setHeureDebut(oldHeure);
            activite.setJour(oldJour);
            resetPosition(activite, dimension);
        }
        activite.setIsSelected(false);
        verifierConflits(activite);
    }

    public void verifierConflits(Activite activite) {
        listeConflits.supprimerConflitsActivite(activite);
        if (!activite.horaireEstValide()) {
            Conflit conflitHoraire = new ConflitHoraire(activite);
            listeConflits.ajouterConflit(conflitHoraire);
        }
        List<Activite> activitesConflitCheminement = grillesCheminement.activitesAuMemeHoraire(activite);
        for (Activite activiteEnConflit : activitesConflitCheminement) {
            Conflit conflitCheminement = new ConflitCheminement(activite, activiteEnConflit);
            listeConflits.ajouterConflit(conflitCheminement);
        }
    }

    public void verifierConflits() {
        listeActivite.getActivitesAssignees().stream().forEach((activite) -> {
            verifierConflits(activite);
        });
    }

    public boolean deplacementEstValide(Activite activite) {
        return activite.horaireEstValide() && grillesCheminement.aucuneActivitesEnConflitdeCheminement(activite);
    }

    public boolean estValide() {
        return listeConflits.getListeConflits().isEmpty();
    }

    public HashMap<Integer, List<Double>> getPlagesHoraireAGriser(Activite activite) {
        HashMap<Integer, List<Double>> result = new HashMap<>();
        List<Activite> activitesCheminement = grillesCheminement.activitesCheminementsDejaALHoraire(activite);
        activitesCheminement.stream().forEach((item) -> {
            List<Double> liste = new ArrayList<>();
            liste.add(item.getHeureDebut());
            liste.add(item.getDuree());
            result.put(item.getJour(), liste);
        });
        return result;
    }

    public void desassignerActivite(Activite activite) {
        activite.desassigner();
        listeConflits.supprimerConflitsActivite(activite);
    }

    public void desassignerActivites() {
        listeActivite.getActivitesAssignees().stream().forEach((activite) -> {
            desassignerActivite(activite);
        });
    }

    public void resetPosition(Activite activite, Dimension dimension) {
        activite.setPoint(dimension);
        activite.setIsSelected(false);
    }
}
