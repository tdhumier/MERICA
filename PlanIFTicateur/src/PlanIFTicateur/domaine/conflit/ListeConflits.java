/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.conflit;

import PlanIFTicateur.domaine.activite.Activite;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Conflit> conflit = listeConflits.stream().filter(x -> x.activitePresente(activite)).findFirst();
        if (conflit.isPresent()) {
            listeConflits.remove(conflit.get());
        }
    }
    
    public void clear(){
        listeConflits.clear();
    }
}
