/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.conflit;

import PlanIFTicateur.domaine.activite.Activite;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class ListeConflits {

    private List<Conflit> listeConflits = new ArrayList<>();

    public List<Conflit> getListeConflits() {
        return listeConflits;
    }

    public void ajouterConflit(Conflit conflit) {
        if (!listeConflits.stream().anyMatch(x -> x.equals(conflit))) {
            listeConflits.add(conflit);
        }
    }

    public void supprimerConflitsActivite(Activite activite) {
        listeConflits.stream().filter((conflit) -> (conflit.activitePresente(activite))).forEach((conflit) -> {
            listeConflits.remove(conflit);
        });
    }
}
