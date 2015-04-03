/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;
import PlanIFTicateur.domaine.activite.Activite;
import java.util.List;
/**
 *
 * @author Alexandre
 */
public class ListeActivites 
{
    private List<Activite> listeActivites;
    
    public ListeActivites(List<Activite> listeActivites)
    {
        this.listeActivites = listeActivites;
    }

    public List<Activite> getListeActivites() {
        return listeActivites;
    }
    
    public Activite getActivite(int i)
    {
        return listeActivites.get(i);
    }
    
    public int size()
    {
        return listeActivites.size();
    }
    
}
