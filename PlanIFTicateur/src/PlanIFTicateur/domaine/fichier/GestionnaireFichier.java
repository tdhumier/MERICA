/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.ListeGrillesCheminement;
import java.io.File;

/**
 *
 * @author martindeligny1
 */
public class GestionnaireFichier {
    
    public ListeGrillesCheminement getListeGrillesCheminement(String adresseActivite){ // Lancer la récupération des données à partir de l'emplacement du fichier .COU passé en string
        
        System.out.println("Dans GestionnaireFichier / ChargementFichier");
        
        File fichierActivite = new File(adresseActivite);
        ActiviteDao activiteDao = new ActiviteDao(fichierActivite);
        
        String adresseCheminement = adresseActivite.substring(0, adresseActivite.length()-4)+".CHE"; // on retire les 4 derniers caractères (.COU) de l'adresse du fichier activite pour ajouter la fin du fichier .CHE
        File fichierCheminement = new File(adresseCheminement);
        
        return (ListeGrillesCheminement) activiteDao.importerFichier(fichierActivite,fichierCheminement);
    }
    
}
