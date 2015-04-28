/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.ListeActivites;
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


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

    public ListeGrillesCheminement getGrillesCheminement(ListeActivites listeActivites, String session) {
        String adresseCheminement = adresseFichierCOU.substring(0, adresseFichierCOU.length() - 4) + ".CHE"; // on retire les 4 derniers caractères (.COU) de l'adresse du fichier activite pour ajouter la fin du fichier .CHE
        CheminementDao cheminementDao = new CheminementDao(adresseCheminement);
        return cheminementDao.importerGrillesCheminement(listeActivites, session);
    }

    public void enregistrerFichier(List<Activite> activites) {
        ActiviteDao activiteDao = new ActiviteDao(adresseFichierCOU);
        activiteDao.writeFile(activites, adresseFichierCOU);
    }

    public void enregistrerFichier(List<Activite> activites, String fichier) {
        ActiviteDao activiteDao = new ActiviteDao(adresseFichierCOU);
        activiteDao.writeFile(activites, fichier);
    }
    
    public ArrayList<String> getNotes(){
        String adresseNotes = adresseFichierCOU.substring(0, adresseFichierCOU.length()-4) + ".txt";
        NotesDao notesDao = new NotesDao(adresseNotes);
        return notesDao.importerNotes();
    }
    
    public void enregisterNotes(ArrayList<String> notes){
        String adresseNotes = adresseFichierCOU.substring(0, adresseFichierCOU.length()-4) + ".txt";
        NotesDao notesDao = new NotesDao(adresseNotes);
        notesDao.sauvegarderNotes(notes);
    }
    
    public void enregisterNotes(ArrayList<String> notes, String file){
        String adresseNotes = file/*.substring(0, file.length()-4)*/ + ".txt";
        NotesDao notesDao = new NotesDao(adresseNotes);
        notesDao.sauvegarderNotes(notes);
    }
    
    public void copierCHE(String fichier, String path) throws IOException
    {
        String adresseCHE = path.substring(0, path.length()-4) + ".CHE";
        File fileDebut = new File(adresseCHE);
        String adresseOut = fichier + ".CHE";
        File fileFin = new File(adresseOut);
        Files.copy(fileDebut.toPath(), fileFin.toPath());
    }
}
