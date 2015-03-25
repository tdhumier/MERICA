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
public class ConflitCheminement extends Conflit
{
    protected Activite autreActivite;
    
    public ConflitCheminement(Activite activite, Activite autreActivite)
    {
        super(activite);
        this.autreActivite = autreActivite;
    }

    public Activite getAutreActivite() {
        return autreActivite;
    }

    public void setAutreActivite(Activite autreActivite) {
        this.autreActivite = autreActivite;
    }
    
}
