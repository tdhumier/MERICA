/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.GrilleCheminement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class CheminementDao {
    
    private LecteurCsv lecteurCsv;
    
    public CheminementDao(File file) {
        this.lecteurCsv = new LecteurCsv(file);
    }
    
    public List<GrilleCheminement> importerFichier(File fichier) { 
        
        List<GrilleCheminement> grilles = new ArrayList<>();
        List<String[]> data = lecteurCsv.getData();
        
        for(String[] oneData : data) {
            GrilleCheminement grille = formaterGrille(oneData);
            grilles.add(grille);
        }

        return grilles;
    }
    
    private GrilleCheminement formaterGrille(String[] tab) {
        GrilleCheminement grille = new GrilleCheminement();
        ArrayList<String> listeActivites = new ArrayList<>();
        Integer taille = tab.length;
    
        for (int i = 3; i <= taille ; i++ ){
        
            listeActivites.add(tab[i]);
        }

        grille.setNomProgramme(tab[0]);
        grille.setVersion(tab[1]);
        grille.setSession(tab[2]);
        //grille.setListeActivite(listeActivites);
    
        return grille;
    }
    
}
