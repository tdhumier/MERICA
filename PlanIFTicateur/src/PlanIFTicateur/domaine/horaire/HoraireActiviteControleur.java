/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.fichier.GestionnaireFichier;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre
 */
public class HoraireActiviteControleur {

    private Horaire horaire;
    private List<HoraireControleurObserveur> observers;

    public enum ActiviteModes {

        CoursClasse, CoursDistance, CoursHorsDep, Laboratoire
    }

    public HoraireActiviteControleur(Horaire horaire) {
        this.horaire = horaire;
    }

    public HoraireActiviteControleur() {
        horaire = new Horaire();
        observers = new LinkedList<>();
    }

    public void deplacerActivite(Activite activite, int x, int y) {
        horaire.deplacerActivite(activite, x, y);
        notifyObserversForUpdatedItems();
    }

    public void deplacerActivite(Activite activite, Point point, double heure, int jour) {
        horaire.deplacerActivite(activite, point, heure, jour);
        notifyObserversForUpdatedItems();
    }

    public void deplacerActiviteAvecVerification(Activite activite, Point point, double heure, int jour, Dimension dimension) {
        horaire.deplacerActiviteAvecVerification(activite, point, heure, jour, dimension);
        notifyObserversForUpdatedItems();
    }

    public void resetPosition(Activite activite, Dimension dimension) {
        horaire.resetPosition(activite, dimension);
        notifyObserversForUpdatedItems();
    }

    public void unasignActivite(Activite activite) {
        horaire.resetPosition(activite);
        notifyObserversForUpdatedItems();
    }

    public Optional<Activite> getActiviteSelectionnee() {
        return horaire.getListeActivite().getActiviteSelectionnee();
    }

    public List<Activite> getActivitesAssignees() {
        List<Activite> activites = horaire.getListeActivite().getActiviteAssignees();
        Optional<Activite> activite = horaire.getListeActivite().getActiviteSelectionnee();
        if (activite.isPresent()) {
            activites.add(activite.get());
            return activites.stream().distinct().collect(Collectors.toList());
        }
        return activites;
    }

    public void importerFichiers(String path, Dimension dimension) {
        GestionnaireFichier gestionnaireFichier = new GestionnaireFichier(path);
        ListeActivites listeActivites = gestionnaireFichier.getListeActivites();
        ListeGrillesCheminement listeGrillesCheminement = gestionnaireFichier.getGrillesCheminement(listeActivites);
        horaire.setListeActivite(listeActivites);
        horaire.setGrillesCheminement(listeGrillesCheminement);
        setCoordonneesActivite(dimension);
        notifyObserversForUpdatedItems();
    }

    public void setCoordonneesActivite(Dimension dimension) {
        horaire.getListeActivite().setCoordonneesActivites(dimension);
    }

    public List<Activite> getActivitesNonAssignees() {
        return horaire.getListeActivite().getActivitesNonAssignees();
    }

    public HashMap<Integer, List<Double>> getPlagesHoraireAGriser(Activite activite) {
        return horaire.getPlagesHoraireAGriser(activite);
    }

    public ArrayList<Integer> getNbCoursSemaine() {
        return horaire.getStatistiques().getNbCoursSemaine(horaire);
    }

    public void modifierStatutSelectionActivite(int x, int y) {
        horaire.getListeActivite().modifierStatutSelectionActivite(x, y);
        notifyObserversForUpdatedItems();
    }

    public Boolean isHoraireValide() {
        return horaire.estValide();
    }

    public void setActiviteSelectionnee(Activite activite, Boolean state) {
        horaire.getListeActivite().setActivitesANonSlectionnee();
        activite.setIsSelected(state);
        notifyObserversForUpdatedItems();
    }

    public void registerObserver(HoraireControleurObserveur newListener) {
        observers.add(newListener);
    }

    public void notifyObserversForUpdatedItems() {
        for (HoraireControleurObserveur observer : observers) {
            observer.notifyUpdatedItems();
        }
    }
}
