/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class ListeGrillesCheminement {
    
    List<GrilleCheminement> grillesCheminement;

    public ListeGrillesCheminement(List<GrilleCheminement> grillesCheminement) {
        this.grillesCheminement = grillesCheminement;
    }

    public List<GrilleCheminement> getGrillesCheminement() {
        return grillesCheminement;
    }
    
    
    
}
