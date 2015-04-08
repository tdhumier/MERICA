/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import PlanIFTicateur.domaine.activite.Activite;
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
    private ListeActivites listeActivite;
    private ListeGrillesCheminement grillesCheminement;
    private ListeConflits listeConflits;
    private Statistique statistique;

    public Horaire() {
        this.listeActivite = new ListeActivites();
        this.grillesCheminement = new ListeGrillesCheminement();
        this.listeConflits = new ListeConflits();
        this.statistique = new Statistique();

    }

    public ListeActivites getListeActivite() {
        return listeActivite;
    }

    public Statistique getStatistiques() {
        return statistique;
    }

    public void setListeActivite(ListeActivites listeActivite) {
        this.listeActivite = listeActivite;
    }

    public void setGrillesCheminement(ListeGrillesCheminement grillesCheminement) {
        this.grillesCheminement = grillesCheminement;
    }

    public void deplacerActivite(Activite activite, int x, int y) {
        activite.deplacerActivite(x, y);
    }

    public void deplacerActivite(Activite activite, Point point, double heure, int jour) {
        activite.deplacerActivite(point, heure, jour);
        activite.setIsSelected(false);
    }

    public void deplacerActiviteAvecVerification(Activite activite, Point point, double heure, int jour, Dimension dimension) {
        double oldHeure = activite.getHeureDebut();
        int oldJour = activite.getJour();
        activite.setHeureDebut(heure);
        activite.setJour(jour);
        verifierHoraireActivite(activite);
        if (this.estValide()) {
            activite.deplacerActivite(point.x, point.y);
        } else {
            activite.setHeureDebut(oldHeure);
            activite.setJour(oldJour);
            resetPosition(activite, dimension);
        }
        activite.setIsSelected(false);
    }

    public void verifierHoraireActivite(Activite activite) {
        List<Activite> activitesConflitCheminement = grillesCheminement.activitesAuMemeHoraire(activite);
        this.valide = !(!activite.horaireValide() || !activitesConflitCheminement.isEmpty());
    }

    public boolean estValide() {
        return this.valide;
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

    void resetPosition(Activite activite) {

        activite.setPoint(new Point());
        activite.setIsSelected(false);
        activite.setJour(0);
        activite.setHeureDebut(0.0d);
    }

    void resetPosition(Activite activite, Dimension dimension) {
        activite.setPoint(dimension);
        activite.setIsSelected(false);
    }
}
