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

        String code = tab[0];
        String section = tab[1];
        String titre = tab[2];
        String professeur = tab[3];
        String type = tab[4];
        double duree = Double.parseDouble(tab[5]);
        double heureDebutMin = Double.parseDouble(tab[6]);
        double heureFinMax = Double.parseDouble(tab[7]);
        int jour = 0;
        if (tab[8] != null || !tab[8].equals("")) {
            jour = Integer.parseInt(tab[8]);
        }
        double heureDebut = 0;
        if (tab[9] != null || !tab[9].equals("")) {
            heureDebut = Double.parseDouble(tab[9]);
        }

        switch (type) {
            case "Classe":
                activite = new CoursClasse(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            case "Distance":
                activite = new CoursDistance(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            case "HorsDep":
                activite = new CoursHorsDep(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            case "Laboratoire":
                activite = new Laboratoire(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            default:
                activite = new CoursClasse(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
        }

        return activite;
    }
}
