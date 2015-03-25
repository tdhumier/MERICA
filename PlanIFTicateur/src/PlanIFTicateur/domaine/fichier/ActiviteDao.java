/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.activite.CoursClasse;
import PlanIFTicateur.domaine.activite.CoursDistance;
import PlanIFTicateur.domaine.activite.CoursHorsDep;
import PlanIFTicateur.domaine.activite.Laboratoire;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class ActiviteDao {
    
    private LecteurCsv lecteurCsv;
    
    public ActiviteDao(File file) {
        this.lecteurCsv = new LecteurCsv(file);
    }
    
    public void importerFichier(File fichier) {  // retourne une liste des activités présentes dans le fichier .COU
        
        CheminementDao cheminementDao = new CheminementDao(fichier);
        
        ArrayList<Activite> activites = new ArrayList<>();
        List<String[]> data = lecteurCsv.getData();
        
        for(String[] oneData : data) {
            Activite activite = formaterActivite(oneData);
            activites.add(activite);
        }
        
        cheminementDao.importerFichier(fichier,activites);
        
    }
    
    private Activite formaterActivite(String[] tab) {  // Retourne une activité formatée à parir d'une ligne du CSV
        
        Activite activite = null;
        
        if(tab[4].equals("Classe")){
            activite = new CoursClasse(tab[0],tab[1],tab[2],tab[3],tab[4],Float.valueOf(tab[5]),Float.valueOf(tab[6]),Float.valueOf(tab[7]),Float.valueOf(tab[8]),Float.valueOf(tab[9]),Integer.parseInt(tab[10]),Float.valueOf(tab[11]));
        }
        else if(tab[4].equals("Distance")){
            activite = new CoursDistance(tab[0],tab[1],tab[2],tab[3],tab[4],Float.valueOf(tab[5]),Float.valueOf(tab[6]),Float.valueOf(tab[7]),Float.valueOf(tab[8]),Float.valueOf(tab[9]),Integer.parseInt(tab[10]),Float.valueOf(tab[11]));
        }
        else if(tab[4].equals("HorsDep")){
            activite = new CoursHorsDep(tab[0],tab[1],tab[2],tab[3],tab[4],Float.valueOf(tab[5]),Float.valueOf(tab[6]),Float.valueOf(tab[7]),Float.valueOf(tab[8]),Float.valueOf(tab[9]),Integer.parseInt(tab[10]),Float.valueOf(tab[11]));
        }
        else if(tab[4].equals("Laboratoire")){
            activite = new Laboratoire(tab[0],tab[1],tab[2],tab[3],tab[4],Float.valueOf(tab[5]),Float.valueOf(tab[6]),Float.valueOf(tab[7]),Float.valueOf(tab[8]),Float.valueOf(tab[9]),Integer.parseInt(tab[10]),Float.valueOf(tab[11]));
        }
        
        return activite;
    }
    
    public Activite getActiviteByCode(String code, ArrayList<Activite> activites){
        
        for (Activite activite : activites) {
            if(activite.getCode().equals(code)){
                return activite;
            }
        }
        
        return null;
    }
    
}
