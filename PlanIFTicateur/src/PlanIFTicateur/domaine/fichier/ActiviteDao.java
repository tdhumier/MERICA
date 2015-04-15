/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.CoursClasse;
import PlanIFTicateur.domaine.activite.CoursDistance;
import PlanIFTicateur.domaine.activite.CoursHorsDep;
import PlanIFTicateur.domaine.activite.Laboratoire;
import PlanIFTicateur.domaine.activite.ListeActivites;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martindeligny1
 */
public class ActiviteDao {

    private LecteurCsv lecteurCsv;
    private EcritureCsv ecritureCsv;

    public ActiviteDao(String file) {
        this.lecteurCsv = new LecteurCsv(file);
    }

    // LECTURE
    ListeActivites importerActivites() {
        ArrayList<Activite> activites = new ArrayList<>();
        List<String[]> donnees = lecteurCsv.getData();
        donnees.stream().map((donnee) -> formaterActivite(donnee)).forEach((activite) -> {
            activites.add(activite);
        });
        return new ListeActivites(activites);
    }

    private Activite formaterActivite(String[] tab) {  // Retourne une activité formatée à parir d'une ligne du CSV

        Activite activite = null;

        String code = tab[0];
        String section = tab[1];
        String titre = tab[2];
        String professeur = tab[3];
        String type = tab[4];
        double duree = Double.parseDouble(tab[5]);
        double heureDebutMin = Double.parseDouble(tab[6]);
        double heureFinMax = Double.parseDouble(tab[7]);
        int jour = 0;
        if (tab.length > 8) {
            jour = Integer.parseInt(tab[8]);
        }
        double heureDebut = 0.0d;
        if (tab.length > 9) {
            heureDebut = Double.parseDouble(tab[9]);
        }

        switch (type) {
            case "Classe":
                activite = new CoursClasse(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            case "À distance":
                activite = new CoursDistance(code, section, titre, professeur, type, duree, heureDebutMin, heureFinMax, jour, heureDebut);
                break;
            case "Hors département":
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

    // ECRITURE
    public void writeFile(List<Activite> activites, String path) {
        ecritureCsv = new EcritureCsv(path);

        if (activites == null) {
            throw new IllegalArgumentException("La liste d'activités ne peut pas être nulle");
        }

        List<ArrayList<String>> mappedData = new ArrayList<ArrayList<String>>();
        for (Activite activite : activites) {
            List<String> oneData = activiteToList(activite);
            if (oneData != null) {
                mappedData.add((ArrayList<String>) oneData);
            }
        }

        try {

            ecritureCsv.write(mappedData);
        } catch (IOException ex) {
            Logger.getLogger(ActiviteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<String> activiteToList(Activite activite) {

        if (!activite.getType().equals("Hors département") || (activite.getType().equals("Hors département") && activite.getHeureDebut() != 0.0d && activite.getJour() != 0)) {
            List<String> oneData = new ArrayList<>();

            Integer jour = 0;
            if (activite.getJour() != 0) {
                jour = activite.getJour();
            }
            Double heureDeb = 0.0;
            if (activite.getHeureDebut() != 0.0d) {
                heureDeb = activite.getHeureDebut();
            }

            oneData.add(activite.getCode());
            oneData.add(activite.getSection());
            oneData.add(activite.getTitre());
            oneData.add(activite.getProfesseur());
            oneData.add(activite.getType());
            oneData.add(String.valueOf(activite.getDuree()));
            oneData.add(String.valueOf(activite.getHeureDebutMin()));
            oneData.add(String.valueOf(activite.getHeureFinMax()));
            oneData.add(Integer.toString(jour));
            oneData.add(String.valueOf(heureDeb));
            return oneData;
        } else {
            return null;
        }
    }
}
