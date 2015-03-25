/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;
import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.conflit.Conflit;
import PlanIFTicateur.domaine.conflit.ConflitCheminement;
import PlanIFTicateur.domaine.conflit.ConflitHoraire;
import java.util.Optional;
/**
 *
 * @author Alexandre
 */
public class Horaire
{
  
    private boolean valide;
    private ListeActivites listeActivite;
    private ListeGrillesCheminement grillesCheminement;
    private ListeConflits listeConflits;

    public ListeActivites getListeActivite() {
        return listeActivite;
    }
    
    public void genererAutomatiquement()
    {
        
    }
    public void deplacerActivite(Activite activite)
    {
        
    }
    
    public void verifierHoraireActivite(Activite activite)
    {
        if(!activite.horaireValide()){
            Conflit conflitHoraire = new ConflitHoraire(activite);
            listeConflits.ajouterConflit(conflitHoraire);
        }
        for(GrilleCheminement grilleCheminement : grillesCheminement.getGrillesCheminement()){
            Optional<Activite> autreActivite = grilleCheminement.activitePresente(activite);
            if(autreActivite.isPresent()){
                Conflit conflitCheminement = new ConflitCheminement(activite,autreActivite.get());
                listeConflits.ajouterConflit(conflitCheminement);
                break;
            }
        }
    }
}
