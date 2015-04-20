/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
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
    private ListeActivites listeActivite;
    private ListeGrillesCheminement grillesCheminement;
    private List<Activite> activitesConflitCheminement;
    private List<Activite> activitesConflitCheminementAvec;
    private Statistique statistique;

    public Horaire() {
        this.listeActivite = new ListeActivites();
        this.grillesCheminement = new ListeGrillesCheminement();
        this.statistique = new Statistique();
        this.valide = true;
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
        if (horaireEstValide(activite)) {
            activite.deplacerActivite(point.x, point.y);
        } else {
            activite.setHeureDebut(oldHeure);
            activite.setJour(oldJour);
            resetPosition(activite, dimension);
        }
        activite.setIsSelected(false);
    }

    public boolean horaireEstValide(Activite activite) {
        activitesConflitCheminement = grillesCheminement.activitesAuMemeHoraire(activite);
        return activite.horaireEstValide() && activitesConflitCheminement.isEmpty();
    }
    
     public List<Activite> getActivitesConflitCheminement() {
        
        return activitesConflitCheminement;
    }
     
    public List<Activite> getActivitesConflitCheminementAvec() {
        
        return activitesConflitCheminementAvec;
    }

    public boolean estValide() {
        boolean returned;
        List<Activite> activitesAssignees = listeActivite.getActivitesAssignees();
        returned = activitesAssignees.stream().noneMatch((activite) -> (!activite.horaireEstValide() || !grillesCheminement.activitesAuMemeHoraire(activite).isEmpty()));
        try{
            activitesConflitCheminement = grillesCheminement.getConflits();
            activitesConflitCheminementAvec = grillesCheminement.getConflitsAvec();
        }catch (Exception e){
        }
        
        return returned;
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

    public void resetPosition(Activite activite) {
        activite.setPoint(new Point());
        activite.setIsSelected(false);
        activite.setJour(0);
        activite.setHeureDebut(0.0d);
    }

    public void resetPosition(Activite activite, Dimension dimension) {
        activite.setPoint(dimension);
        activite.setIsSelected(false);
    }
}
