/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import PlanIFTicateur.domaine.conflit.Conflit;
import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class ListeConflits {
    
    private List<Conflit> listeConflits ;
    
    public ListeConflits(List<Conflit> listeConflits){
        this.listeConflits = listeConflits;
    }

    public List<Conflit> getListeConflits() {
        return listeConflits;
    }
    
    public void ajouterConflit(Conflit conflit){
        listeConflits.add(conflit);
    }
}
