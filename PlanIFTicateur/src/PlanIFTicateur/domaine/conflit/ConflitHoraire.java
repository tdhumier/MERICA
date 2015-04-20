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
public class ConflitHoraire extends Conflit
{
    protected Activite activite;
    
    public ConflitHoraire(Activite activite)
    {
        super(activite);
        System.out.println("activite");
    }
}
