/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martindeligny1
 */
public class CsvFileHelper {
    
    
    
    public static List<String> lecteurFichier(File fichier) {

        List<String> resultat = new ArrayList<>();
        FileReader lecteur;
        
        try {
            lecteur = new FileReader(fichier);
        
            BufferedReader buffer = new BufferedReader(lecteur);

            for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
                resultat.add(line);
            }
        
            buffer.close();
            lecteur.close();
            
        } catch (IOException ex) {
            Logger.getLogger(CsvFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }
}