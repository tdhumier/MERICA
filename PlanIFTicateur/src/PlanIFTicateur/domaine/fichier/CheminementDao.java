/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.GrilleCheminement;
import PlanIFTicateur.domaine.ListeActivites;
import PlanIFTicateur.domaine.ListeGrillesCheminement;
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
    private File fichier;

    public CheminementDao(File file) {
        this.fichier = file;
        this.lecteurCsv = new LecteurCsv(file);
    }

    ListeGrillesCheminement importerGrillesCheminement(ListeActivites listeActivites) {
        List<GrilleCheminement> grilles = new ArrayList<>();
        List<String[]> donnees = lecteurCsv.getData();
        donnees.stream().map((donnee) -> formaterGrille(donnee, listeActivites)).forEach((grille) -> {
            grilles.add(grille);
        });
        return new ListeGrillesCheminement(grilles);
    }

    private GrilleCheminement formaterGrille(String[] tab, ListeActivites activites) { // retourne la grille de cheminement formatée

        System.out.println("Dans CheminementDao / formaterGrille");

        GrilleCheminement grille = new GrilleCheminement();
        ArrayList<Activite> listeActivites = new ArrayList<>();
        Integer taille = tab.length;

        for (int i = 3; i < taille; i++) {
            listeActivites.add(activites.getActiviteByCode(tab[i]));
        }
        grille.setNomProgramme(tab[0]);
        grille.setVersion(tab[1]);
        grille.setSession(tab[2]);
        grille.setListeActivite(listeActivites);

        return grille;
    }
}
