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
    
    
    
    public static List<String> readFile(File file) {

        List<String> result = new ArrayList<>();
        FileReader fr;
        
        try {
            fr = new FileReader(file);
        
            BufferedReader br = new BufferedReader(fr);

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                result.add(line);
            }
        
            br.close();
            fr.close();
            
        } catch (IOException ex) {
            Logger.getLogger(CsvFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}