/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.CoursHorsDep;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre
 */
public class ListeActivites {

    private List<Activite> listeActivites = new ArrayList<>();

    public ListeActivites(List<Activite> listeActivites) {
        this.listeActivites = listeActivites;
    }

    ListeActivites() {
    }

    public List<Activite> getListeActivites() {
        return listeActivites;
    }

    public int getTailleListeActivites() {
        return listeActivites.size();
    }

    public Activite getActiviteByCode(String code) {
        for (Activite activite : listeActivites) {
            if (activite.getCode().equals(code)) {
                return activite;
            } else {
                return new CoursHorsDep(code, "NC", "NC", "NC", "NC", 3, 8, 22, 0, 0.0d);
            }
        }
        return null;
    }

    public List<Activite> getActivitesByJour(int jour) {
        List<Activite> activites = new ArrayList<>();
        for (Activite activite : listeActivites) {
            if (activite.getJour() == jour) {
                activites.add(activite);
            }
        }
        return activites;
    }

    public List<Activite> getActivitesNonAssignees() {
        return listeActivites.stream().filter(x -> !x.isAssignee()).collect(Collectors.toList());
    }

    public List<Activite> getActiviteAssignees() {
        return listeActivites.stream().filter(x -> x.isAssignee()).collect(Collectors.toList());
    }

    public void modifierStatutSelectionActivite(int x, int y) {
        for (Activite activite : listeActivites) {
            activite.modifierStatutSelection(x, y);
        }
    }

    public void setCoordonneesActivites(Dimension dimension) {
        for (Activite activite : listeActivites) {
            activite.setDimension(dimension);
            if (activite.isAssignee()) {
                activite.setPoint(dimension);
            }
        }
    }

    public Optional<Activite> getActiviteSelectionnee() {
        return listeActivites.stream().filter(x -> x.isSelected()).findFirst();
    }

    public void setActivitesANonSlectionnee() {
        listeActivites.stream().forEach((activite) -> {
            activite.setIsSelected(false);
        });
    }
}
