/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.conflit;

import PlanIFTicateur.domaine.activite.Activite;

/**
 *
 * @author Alexandre
 */
public class ConflitCheminement implements Conflit {

    private Activite activite;
    private Activite autreActivite;

    public Activite getActivite() {
        return activite;
    }

    public Activite getAutreActivite() {
        return autreActivite;
    }

    public ConflitCheminement(Activite activite, Activite autreActivite) {
        this.activite = activite;
        this.autreActivite = autreActivite;
    }

    @Override
    public String afficherConflit() {
        return activite.getCode() + " et " + autreActivite.getCode() + " sont en conflit.";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConflitCheminement other = (ConflitCheminement) obj;
        return (this.activite.equals(other.activite) && this.autreActivite.equals(other.autreActivite)) || (this.activite.equals(other.autreActivite) && this.autreActivite.equals(other.activite));
    }

    @Override
    public boolean activitePresente(Activite activite) {
        return this.activite.equals(activite) || this.autreActivite.equals(activite);
    }

}
