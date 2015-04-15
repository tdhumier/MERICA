/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.GrilleCheminement;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    ListeGrillesCheminement importerGrillesCheminement(ListeActivites listeActivites) {
        List<GrilleCheminement> grilles = new ArrayList<>();
        List<String[]> donnees = lecteurCsv.getData();
        donnees.stream().map((donnee) -> formaterGrille(donnee, listeActivites)).forEach((grille) -> {
            grilles.add(grille);
        });
        return new ListeGrillesCheminement(grilles);
    }

    private GrilleCheminement formaterGrille(String[] tab, ListeActivites activites) { // retourne la grille de cheminement formatée

        GrilleCheminement grille = new GrilleCheminement();
        ArrayList<Activite> listeActivites = new ArrayList<>();
        Integer taille = tab.length;
        for (int i = 3; i < taille; i++) {
            if (activites.getActiviteByCode(tab[i]) != null) {
                listeActivites.add(activites.getActiviteByCode(tab[i]));
            }
        }
        grille.setNomProgramme(tab[0]);
        grille.setVersion(tab[1]);
        grille.setSession(tab[2]);
        grille.setListeActivite(listeActivites);

        return grille;
    }

    // ECRITURE
    public void writeFile(List<GrilleCheminement> grillesDeCheminement, File file) {
        if (grillesDeCheminement == null) {
            throw new IllegalArgumentException("La liste de grilles de cheminement ne peut pas être nulle");
        }

        if (file == null) {
            throw new IllegalArgumentException("Le fichier ne peut pas être nul");
        }

        ecritureCsv = new EcritureCsv(file);

        List<Map<String, String>> mappedData = new ArrayList<>();

        for (GrilleCheminement grilleDeCheminement : grillesDeCheminement) {

            Map<String, String> oneData = grilleDeCheminementToMap(grilleDeCheminement);
            mappedData.add(oneData);
        }
        try {
            ecritureCsv.write(mappedData);
        } catch (IOException ex) {
            Logger.getLogger(CheminementDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Map<String, String> grilleDeCheminementToMap(GrilleCheminement grille) {

        Map<String, String> oneData = new HashMap<>();
        List<Activite> activites = grille.getListeActivites();

        oneData.put("Programme", grille.getNomProgramme());
        oneData.put("Version", grille.getVersion());
        oneData.put("Session", grille.getSession());

        for (Activite activite : activites) {
            oneData.put("Cours", activite.getCode());
        }

        return oneData;
    }
}
