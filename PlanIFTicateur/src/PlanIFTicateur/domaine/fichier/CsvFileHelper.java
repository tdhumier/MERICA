/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martindeligny1
 */
public class CsvFileHelper {

    public static List<String> lecteurFichier(String fichier) {

        List<String> resultat = new ArrayList<>();

        try {

            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fichier), "cp850")); // décodage avec profil cp850 : latin

            for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
                resultat.add(line);
            }

            buffer.close();

        } catch (IOException ex) {
            Logger.getLogger(CsvFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }
}
