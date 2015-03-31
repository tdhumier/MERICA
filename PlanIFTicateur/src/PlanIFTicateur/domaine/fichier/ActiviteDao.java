/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.ListeActivites;
import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.CoursClasse;
import PlanIFTicateur.domaine.activite.CoursDistance;
import PlanIFTicateur.domaine.activite.CoursHorsDep;
import PlanIFTicateur.domaine.activite.Laboratoire;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class ActiviteDao {

    private LecteurCsv lecteurCsv;

    public ActiviteDao(File file) {
        this.lecteurCsv = new LecteurCsv(file);
    }

    ListeActivites importerActivites() {
        ArrayList<Activite> activites = new ArrayList<>();
        List<String[]> donnees = lecteurCsv.getData();
        donnees.stream().map((donnee) -> formaterActivite(donnee)).forEach((activite) -> {
            activites.add(activite);
        });
        return new ListeActivites(activites);
    }

    private Activite formaterActivite(String[] tab) {  // Retourne une activité formatée à parir d'une ligne du CSV

        System.out.println("Dans ActiviteDao / formaterActivite");

        Activite activite = null;
        System.out.println("Longueur tab :" + tab.length);
        if (tab[4].equals("Classe")) {
            activite = new CoursClasse(tab[0], tab[1], tab[2], tab[3], tab[4], Double.parseDouble(tab[5]), Double.parseDouble(tab[6]), Double.parseDouble(tab[7]), Double.parseDouble(tab[8]), Integer.parseInt(tab[9]), Double.parseDouble(tab[10]));
        } else if (tab[4].equals("Distance")) {
            activite = new CoursDistance(tab[0], tab[1], tab[2], tab[3], tab[4], Double.parseDouble(tab[5]), Double.parseDouble(tab[6]), Double.parseDouble(tab[7]), Double.parseDouble(tab[8]), Integer.parseInt(tab[9]), Double.parseDouble(tab[10]));
        } else if (tab[4].equals("HorsDep")) {
            activite = new CoursHorsDep(tab[0], tab[1], tab[2], tab[3], tab[4], Double.parseDouble(tab[5]), Double.parseDouble(tab[6]), Double.parseDouble(tab[7]), Double.parseDouble(tab[8]), Integer.parseInt(tab[9]), Double.parseDouble(tab[10]));
        } else if (tab[4].equals("Laboratoire")) {
            activite = new Laboratoire(tab[0], tab[1], tab[2], tab[3], tab[4], Double.parseDouble(tab[5]), Double.parseDouble(tab[6]), Double.parseDouble(tab[7]), Double.parseDouble(tab[8]), Integer.parseInt(tab[9]), Double.parseDouble(tab[10]));
        }

        return activite;
    }
}
