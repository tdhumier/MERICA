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
    public void writeFile(List<Activite> activites, File file) {
        if (activites == null) {
            throw new IllegalArgumentException("La liste d'activités ne peut pas être nulle");
        }

        if (file == null) {
            throw new IllegalArgumentException("Le fichier ne peut pas être nul");
        }

        ecritureCsv = new EcritureCsv(file);

        List<Map<String, String>> mappedData = new ArrayList<>();
        for (Activite activite : activites) {
            Map<String, String> oneData = activiteToMap(activite);
            mappedData.add(oneData);
        }
        try {
            ecritureCsv.write(mappedData);
        } catch (IOException ex) {
            Logger.getLogger(CheminementDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Map<String, String> activiteToMap(Activite activite) {

        Map<String, String> oneData = new HashMap<>();

        oneData.put("Code", activite.getCode());
        oneData.put("Section", activite.getSection());
        oneData.put("Titre", activite.getTitre());
        oneData.put("PROF", activite.getProfesseur());
        oneData.put("Type", activite.getType());
        oneData.put("Durée", String.valueOf(activite.getDuree()));
        oneData.put("DébutMin", String.valueOf(activite.getHeureDebutMin()));
        oneData.put("FinMax", String.valueOf(activite.getHeureFinMax()));
        oneData.put("Jour", Integer.toString(activite.getJour()));
        oneData.put("Heure", String.valueOf(activite.getHeureDebut()));

        return oneData;
    }
}
