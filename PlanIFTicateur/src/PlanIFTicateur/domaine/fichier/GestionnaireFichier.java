/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.ListeActivites;
import PlanIFTicateur.domaine.ListeGrillesCheminement;
import java.io.File;

/**
 *
 * @author martindeligny1
 */
public class GestionnaireFichier {

    private String addresseFichierCOU;

    public GestionnaireFichier(String addresseFichierCOU) {
        this.addresseFichierCOU = addresseFichierCOU;
    }

    public ListeActivites getListeActivites() {
        File fichierActivite = new File(addresseFichierCOU);
        ActiviteDao activiteDao = new ActiviteDao(fichierActivite);
        return activiteDao.importerActivites();
    }

    public ListeGrillesCheminement getGrillesCheminement(ListeActivites listeActivites) {
        String adresseCheminement = addresseFichierCOU.substring(0, addresseFichierCOU.length() - 4) + ".CHE"; // on retire les 4 derniers caractères (.COU) de l'adresse du fichier activite pour ajouter la fin du fichier .CHE
        File fichierCheminement = new File(adresseCheminement);
        CheminementDao cheminementDao = new CheminementDao(fichierCheminement);
        return cheminementDao.importerGrillesCheminement(listeActivites);
    }
}
