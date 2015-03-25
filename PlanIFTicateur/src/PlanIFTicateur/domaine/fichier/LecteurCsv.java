/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class LecteurCsv {
    
    public final static char SEPARATOR = ';';

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

    private void init() {
        lignes = CsvFileHelper.readFile(file);
        lignes.remove(0); // Retire la ligne contenant les titres

        data = new ArrayList<>();
        String separateur = new Character(SEPARATOR).toString();
        
        for (String ligne : lignes) {
                  
            String[] oneData = ligne.split(separateur);
            data.add(oneData);
           
        }
    }
    
    public List<String[]> getData() {
        return data;
    }
}
