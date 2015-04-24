/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.conflit;

import PlanIFTicateur.domaine.activite.Activite;

/**
 *
 * @author tristandhumieres
 */
public interface Conflit {

    public String afficherConflit();

    public boolean activitePresente(Activite activite);
}
