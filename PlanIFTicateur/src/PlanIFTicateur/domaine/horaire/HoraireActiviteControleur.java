/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
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
    private String path;

    
    public List<Activite> getActivites() {
        System.out.println("Dans controleur / getActivites");
        return horaire.getListeActivite().getListeActivites();
    }
    
     public List<Activite> getActivitesConflitCheminement() {
        return horaire.getActivitesConflitCheminement();
    }

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

    public void correctionSuperpositionActivite(Activite activite, Point point, double heure) {
        List<Activite> activites = horaire.getListeActivite().getListeActivites();
        for (Activite activiteItem : activites) {
            // si on a deux activités à la même date à la même heure
            if (activiteItem.getPoint().y == point.y && ((activiteItem.getHeureDebut() <= heure && activiteItem.getHeureDebut() + activiteItem.getDuree() > heure) || (heure <= activiteItem.getHeureDebut() && activiteItem.getHeureDebut() < heure + activite.getDuree()))) {
                point.y = point.y + activite.getHeight();
                correctionSuperpositionActivite(activite, point, heure);
            }
        }
    }

    public void deplacerActivite(Activite activite, Point point, double heure, int jour) {
        correctionSuperpositionActivite(activite, point, heure);
        horaire.deplacerActivite(activite, point, heure, jour);
        notifyObserversForUpdatedItems();
    }

    public void deplacerActiviteAvecVerification(Activite activite, Point point, double heure, int jour, Dimension dimension) {
        correctionSuperpositionActivite(activite, point, heure);
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
        List<Activite> activites = horaire.getListeActivite().getActivitesAssignees();
        Optional<Activite> activite = horaire.getListeActivite().getActiviteSelectionnee();
        if (activite.isPresent()) {
            activites.add(activite.get());
            return activites.stream().distinct().collect(Collectors.toList());
        }
        return activites;
    }

    public void importerFichiers(String path, Dimension dimension) {
        this.path = path;
        GestionnaireFichier gestionnaireFichier = new GestionnaireFichier(path);
        ListeActivites listeActivites = gestionnaireFichier.getListeActivites();
        ListeGrillesCheminement listeGrillesCheminement = gestionnaireFichier.getGrillesCheminement(listeActivites);
        horaire.setListeActivite(listeActivites);
        horaire.setGrillesCheminement(listeGrillesCheminement);
        setCoordonneesActivite(dimension);
        notifyObserversForUpdatedItems();
    }

    public void enregistrerFichier(List<Activite> activites) {
        GestionnaireFichier gestionnaireFichier = new GestionnaireFichier(path);
        gestionnaireFichier.enregistrerFichier(activites);
    }

    public void enregistrerFichier(List<Activite> activites, String file) {
        GestionnaireFichier gestionnaireFichier = new GestionnaireFichier(path);
        gestionnaireFichier.enregistrerFichier(activites, file);
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

    public Boolean horaireEstValide() {
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
