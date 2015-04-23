/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.CoursHorsDep;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.GrilleCheminement;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class CheminementDao {

    private LecteurCsv lecteurCsv;
    private EcritureCsv ecritureCsv;

    private String fichier;

    public CheminementDao(String file) {
        this.fichier = file;
        this.lecteurCsv = new LecteurCsv(file);
    }

    // LECTURE
    ListeGrillesCheminement importerGrillesCheminement(ListeActivites listeActivites, String session) {
        List<GrilleCheminement> grilles = new ArrayList<>();
        List<String[]> donnees = lecteurCsv.getData();
        donnees.stream().map((donnee) -> formaterGrille(donnee, listeActivites)).forEach((grille) -> {
            if (grille.getSession().equals(session)) {
                grilles.add(grille);
            }

        });
        return new ListeGrillesCheminement(grilles);
    }

    private GrilleCheminement formaterGrille(String[] tab, ListeActivites activites) { // retourne la grille de cheminement formatée

        GrilleCheminement grille = new GrilleCheminement();
        ArrayList<Activite> listeActivites = new ArrayList<>();
        Integer taille = tab.length;
        for (int i = 3; i < taille; i++) {
            Activite activite = activites.getActiviteByCode(tab[i]);
            if (activite == null) {
                activite = new CoursHorsDep(tab[i], "NC", "NC", "NC", "NC", 3, 8, 22, 0, 0.0d);
                activites.ajouterActivite(activite);
            }
            listeActivites.add(activite);
        }
        grille.setNomProgramme(tab[0]);
        grille.setVersion(tab[1]);
        grille.setSession(tab[2].substring(0, 1)); // récupère uniquement la session voulue, sans l'indice de la grille (A, H ou E)
        grille.setListeActivite(listeActivites);

        return grille;
    }
}
