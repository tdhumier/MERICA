/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.GrilleCheminement;
import PlanIFTicateur.domaine.activite.Activite;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class CheminementDao {
    
    private LecteurCsv lecteurCsv;
    private File file;
    
    public CheminementDao(File file) {
        this.file = file;
        this.lecteurCsv = new LecteurCsv(file);
    }
    
    public List<GrilleCheminement> importerFichier(File fichier, ArrayList<Activite> activites) { // retourne une liste des grilles de cheminements complète contenue dans le fichier .CHE
        
        System.out.println("Dans CheminementDao / ImporterFichier");
        
        List<GrilleCheminement> grilles = new ArrayList<>();
        List<String[]> data = lecteurCsv.getData();
        
        for(String[] oneData : data) {
            GrilleCheminement grille = formaterGrille(oneData, activites);
            grilles.add(grille);
        }

        return grilles;
    }
    
    private GrilleCheminement formaterGrille(String[] tab, ArrayList<Activite> activites) { // retourne la grille de cheminement formatée
        
        System.out.println("Dans CheminementDao / formaterGrille");
        
        ActiviteDao activiteDao = new ActiviteDao(file);
        
        GrilleCheminement grille = new GrilleCheminement();
        ArrayList<Activite> listeActivites = new ArrayList<>();
        Integer taille = tab.length;
    
        for (int i = 3; i < taille ; i++ ){
            listeActivites.add(activiteDao.getActiviteByCode(tab[i], activites));
        }

        grille.setNomProgramme(tab[0]);
        grille.setVersion(tab[1]);
        grille.setSession(tab[2]);
        grille.setListeActivite(listeActivites);
    
        return grille;
    }
    
}
