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
        return horaire.getListeActivite().getListeActivites();
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

    public Horaire getHoraire() {
        return horaire;
    }

    public String getPath() {
        return path;
    }

    public void deplacerActivite(Activite activite, int x, int y) {
        horaire.deplacerActivite(activite, x, y);
        notifyObserversForUpdatedItems();
    }

    public boolean correctionSuperpositionActivite(Activite activite, Point point, double heure) {
        List<Activite> activites = horaire.getListeActivite().getListeActivites();
        for (Activite activiteItem : activites) {
            // si on a deux activités à la même date à la même heure
            if (activiteItem.getPoint().y == point.y && ((activiteItem.getHeureDebut() <= heure && activiteItem.getHeureDebut() + activiteItem.getDuree() > heure) || (heure <= activiteItem.getHeureDebut() && activiteItem.getHeureDebut() < heure + activite.getDuree()))) {
                point.y = point.y + activite.getHeight();
                int positionTemoin = point.y - 20;
                if ((positionTemoin / 8) % 16 == 0) {
                    return false;
                } else {
                    return correctionSuperpositionActivite(activite, point, heure);
                }
            }
        }
        return true;
    }

    public void deplacerActivite(Activite activite, Point point, double heure, int jour) {
        boolean correct = correctionSuperpositionActivite(activite, point, heure);
        if (correct) {
            horaire.deplacerActivite(activite, point, heure, jour);
        } else {
            desassignerActivite(activite);
        }

        notifyObserversForUpdatedItems();
    }

    public void deplacerActiviteAvecVerification(Activite activite, Point point, double heure, int jour, Dimension dimension) {
        boolean correct = correctionSuperpositionActivite(activite, point, heure);
        if (correct) {
            horaire.deplacerActiviteAvecVerification(activite, point, heure, jour, dimension);
        } else {
            desassignerActivite(activite);
        }
        notifyObserversForUpdatedItems();
    }

    public void desassignerActivite(Activite activite) {
        horaire.desassignerActivite(activite);
        notifyObserversForUpdatedItems();
    }

    public void desassignerActivites() {
        horaire.desassignerActivites();
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

    public List<Conflit> getConflits() {
        return horaire.getListeConflits().getListeConflits();
    }

    public void importerFichiers(String path, Dimension dimension) {
        this.path = path;
        GestionnaireFichier gestionnaireFichier = new GestionnaireFichier(path);
        ListeActivites listeActivites = gestionnaireFichier.getListeActivites();
        String session = horaire.getSession();
        ListeGrillesCheminement listeGrillesCheminement = gestionnaireFichier.getGrillesCheminement(listeActivites, session);
        horaire.setListeActivite(listeActivites);
        horaire.setGrillesCheminement(listeGrillesCheminement);
        setCoordonneesActivite(dimension);
        horaire.verifierConflits();
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

    public float getIndiceCongestion(int jour) {
        return horaire.getStatistiques().congestionCirculation(horaire, jour);
    }

    public float getIndiceCovoiturage(ListeGrillesCheminement listeGrilleCheminement, int jour) {
        return horaire.getStatistiques().indiceCovoiturage(horaire, listeGrilleCheminement, jour);
    }

    public int getNbMaxCoursParJour(ListeGrillesCheminement listeGrilleCheminement, int jour) {
        return horaire.getStatistiques().nbMaxCoursParJour(horaire, listeGrilleCheminement, jour);
    }

    public float getNbMoyenCoursParJour(ListeGrillesCheminement listeGrilleCheminement, int jour) {
        return horaire.getStatistiques().nbMoyenCoursParJour(horaire, listeGrilleCheminement, jour);
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
