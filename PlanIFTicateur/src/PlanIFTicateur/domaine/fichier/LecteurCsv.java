/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class LecteurCsv {

    public final static List<Character> SEPARATEURS = Collections.unmodifiableList(new ArrayList<Character>(Arrays.asList(',', ';', '\t', '|')));

    private File fichier;
    private List<String> lignes;
    private List<String> lignesImport;
    private List<String[]> donnees;

    private LecteurCsv() {
    }

    public LecteurCsv(File file) {
        this.fichier = file;

        // Init
        init();
    }

    public Character choisirMeilleurSeparateur() {

        Character separateurMajorite = ';';
        int occurenceSeparateurMajorite = 0;

        for (Character separateur : SEPARATEURS) {
            final String ligneTitre = lignes.get(0);
            final String ligne1 = lignes.get(1);

            final int nbSeparateurLigneTitre = compterSeperateurs(ligneTitre, separateur);
            final int nbSeparateurLigne1 = compterSeperateurs(ligne1, separateur);

            if (occurenceSeparateurMajorite < nbSeparateurLigneTitre) {
                occurenceSeparateurMajorite = nbSeparateurLigneTitre;
                separateurMajorite = separateur;
            }

            if (nbSeparateurLigneTitre == 0 || nbSeparateurLigne1 == 0) {
                continue;
            }

            if (nbSeparateurLigneTitre == nbSeparateurLigne1) {
                return separateur;
            }
        }

        return separateurMajorite;
    }

    public int compterSeperateurs(String ligne, char separateur) { // Permet de compter le nombre d'occurence d'un séparateur sur une ligne
        int occurence = 0;

        int pos = ligne.indexOf(separateur);
        while (pos != -1) {
            occurence++;
            ligne = ligne.substring(pos + 1);
            pos = ligne.indexOf(separateur);
        }
        return occurence;
    }

    private void init() {
        lignesImport = CsvFileHelper.lecteurFichier(fichier);

        lignes = new ArrayList<String>();

        // Nettoyage des lignes
        for (String ligne : lignesImport) {

            ligne = ligne.trim();

            if (ligne.length() == 0) {
                continue;
            }

            if (ligne.startsWith("#")) {
                continue;
            }

            if (ligne.startsWith(";")) {
                continue;
            }

            lignes.add(ligne);
        }

        char meilleurSeparateur = choisirMeilleurSeparateur(); // Choix du meilleur séparateur
        lignes.remove(0); // Retire la ligne contenant les titres

        donnees = new ArrayList<>();

        String separateur = new Character(meilleurSeparateur).toString();

        for (String ligne : lignes) {

            String[] oneData = ligne.split(separateur);
            donnees.add(oneData);

        }
    }

    public List<String[]> getData() {
        return donnees;
    }
}
