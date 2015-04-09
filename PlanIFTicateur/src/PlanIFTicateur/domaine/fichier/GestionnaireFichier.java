/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;

/**
 *
 * @author martindeligny1
 */
public class GestionnaireFichier {

    private String adresseFichierCOU;

    public GestionnaireFichier(String adresseFichierCOU) {
        this.adresseFichierCOU = adresseFichierCOU;
    }

    public ListeActivites getListeActivites() {
        ActiviteDao activiteDao = new ActiviteDao(adresseFichierCOU);
        return activiteDao.importerActivites();
    }

    public ListeGrillesCheminement getGrillesCheminement(ListeActivites listeActivites) {
        String adresseCheminement = adresseFichierCOU.substring(0, adresseFichierCOU.length() - 4) + ".CHE"; // on retire les 4 derniers caractères (.COU) de l'adresse du fichier activite pour ajouter la fin du fichier .CHE
        CheminementDao cheminementDao = new CheminementDao(adresseCheminement);
        return cheminementDao.importerGrillesCheminement(listeActivites);
    }
}
