/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.horaire;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.MainWindow;
import PlanIFTicateur.gui.listeners.mouse.MousePositionHelper;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Alexandre
 */
public class PlanificationAutomatique 
{
    
    private MainWindow mainWindow;
    private MousePositionHelper mousePositionHelper;
    private List<Activite> listeActivitesNonAssignees;
    private Dimension dimensionCase;
    private Dimension dimensionActivite;
    private  Point position;
    private int jour;
    private double heure;

    public PlanificationAutomatique(MainWindow mainWindow)
    {
      this.mainWindow = mainWindow;
      //mousePositionHelper = new MousePositionHelper(mainWindow.horairePanel.getDimensionsCase());
      listeActivitesNonAssignees = mainWindow.controleur.getHoraire().getListeActivite().getActivitesNonAssignees();
      dimensionCase = mainWindow.horairePanel.getDimensionsCase();
    }
    
    public void genereAutomatique()
    {
        int compteur;
        jour = 1;
        heure = 8;
        position = getPosition(heure,jour);
        
         for (Activite activite : listeActivitesNonAssignees) 
        { 
            compteur = 0;
            while(activite.getJour() == 0 && compteur < 168)
            {
               mainWindow.controleur.deplacerActiviteAvecVerification(activite, position, heure, jour, dimensionCase );
               if(activite.getJour()== 0)
               {
                   avancerPosition();
                   compteur++;
               }
            }
            if(compteur == 168)
                JOptionPane.showMessageDialog(null, "Impossible d'ajouté ce cours sans créer de conflit.");
            
            compteur = 0;
        }
    }
    
    public Point getPosition(double heure, int jour)
    {
        
        int xDepart = 80;
        int xCase = ((int)(heure*2 - 16) * dimensionCase.width) + xDepart;
        
        int yDepart = 20;
        int yCase = ((jour - 1) * 8 * dimensionCase.height) + yDepart;
        
        Point point = new Point(xCase, yCase);
        
        return point;
    }
    
    public void avancerPosition()
    {
        if(heure < 22)
        {
            heure = heure + 0.5;
            position = getPosition(heure, jour);
        }
        else
        {
           if(jour < 7)
                jour = jour + 1; 
           else
               jour = 1;
               
            heure = 8;
            position = getPosition(heure, jour);
        }
    }
}
