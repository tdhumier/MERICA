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
public abstract class Conflit 
{
    protected Activite activite;

    public Activite getActivite() {
        return activite;
    }

    
    
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    public Conflit(Activite activite)
    {
        this.activite = activite;
    }
}
