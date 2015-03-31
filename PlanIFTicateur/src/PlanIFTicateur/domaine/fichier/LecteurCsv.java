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

    private File file;
    private List<String> lignes;
    private List<String[] > data;
    
    private LecteurCsv() {
    }

    public LecteurCsv(File file) {
        this.file = file;

        // Init
        init();
    }
    
    public Character choisirMeilleurSeparateur() {

        for (Character separateur : SEPARATEURS) {
            final String ligneTitre = lignes.get(0);
            final String ligne1 = lignes.get(1);

            final int nbSeparateurLigneTitre = compterSeperateurs(ligneTitre, separateur); 
            final int nbSeparateurLigne1 = compterSeperateurs(ligne1, separateur);

            if (nbSeparateurLigneTitre == 0 || nbSeparateurLigne1 == 0) { 
                continue;
            }

            if (nbSeparateurLigneTitre == nbSeparateurLigne1) {
                return separateur;
            }
        }

        return null;
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
        lignes = CsvFileHelper.readFile(file);
        char meilleurSeparateur = choisirMeilleurSeparateur(); // Choix du meilleur séparateur
       
        if (meilleurSeparateur == 0){ // Si on ne trouve pas de meilleure séparateur
            meilleurSeparateur = ';';
        }
        
        lignes.remove(0); // Retire la ligne contenant les titres

        data = new ArrayList<>();
        
        String separateur = new Character(meilleurSeparateur).toString();
        
        for (String ligne : lignes) {
                  
            String[] oneData = ligne.split(separateur);
            data.add(oneData);
           
        }
    }
    
    public List<String[]> getData() {
        return data;
    }
}
