/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.StatistiquesPanels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class CoursParJourPanel extends JPanel implements HoraireControleurObserveur{
    
    private MainWindow mainWindow;
    private JLabel coursParJourTitre;
    private JLabel lundiCPJLabel;
    private JLabel mardiCPJLabel;
    private JLabel mercrediCPJLabel;
    private JLabel jeudiCPJLabel;
    private JLabel vendrediCPJLabel;
    private JLabel samediCPJLabel;
    private JLabel moyenneCPJLabel;
    private JLabel[] CPJLabel;
    private String[] jourModes;
    
    private  ArrayList<Integer> nbCoursParJour;
    String moyenneText;
    
    public CoursParJourPanel(MainWindow mainWindow, String[] jourModes)
    {
        this.mainWindow = mainWindow;
        this.jourModes = jourModes;
        setLayout(new GridLayout(8,1));
        buildUp();
    }
    
    
    private void buildUp()
    {
        System.out.println("Test de debut");
        coursParJourTitre = new JLabel("Nombre de cours par jour");
        lundiCPJLabel = new JLabel();
        mardiCPJLabel = new JLabel();
        mercrediCPJLabel = new JLabel();
        jeudiCPJLabel = new JLabel();
        vendrediCPJLabel = new JLabel();
        samediCPJLabel = new JLabel();
        moyenneCPJLabel = new JLabel("Moyenne : ");
        
         
        CPJLabel = new JLabel[]{lundiCPJLabel, mardiCPJLabel, mercrediCPJLabel, jeudiCPJLabel, vendrediCPJLabel, samediCPJLabel};
       
        ajouterCoursParJour(CPJLabel);
    }
    
     private void ajouterCoursParJour(JLabel[] CPJLabel)
    {
  
        float moyenne = 0;
        
        nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        for(int i = 0; i < 6; i++)
        {
            moyenne += nbCoursParJour.get(i);
        }
         
        moyenne = moyenne/6;
        moyenneText = Float.toString(moyenne);
        
        CPJLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationIntegerLabel(jourModes, CPJLabel, nbCoursParJour, " Cours");
        
        
      /*  lundiCPJLabel.setText(listeLabel[0].getText() + nbCoursParJour.get(0) + " Cours");
        mardiCPJLabel.setText(listeLabel[1].getText() + nbCoursParJour.get(1) + " Cours");
        mercrediCPJLabel.setText(listeLabel[2].getText() + nbCoursParJour.get(2) + " Cours");
        jeudiCPJLabel.setText(listeLabel[3].getText() + nbCoursParJour.get(3) + " Cours");
        vendrediCPJLabel.setText(listeLabel[4].getText() + nbCoursParJour.get(4) + " Cours");
        samediCPJLabel.setText(listeLabel[5].getText() + nbCoursParJour.get(5) + " Cours");*/
        
        moyenneCPJLabel.setText(moyenneCPJLabel.getText() + moyenneText + " Cours");
        
        add(coursParJourTitre);
        
         for(int j = 0; j < 6; j++)
            {
                add(CPJLabel[j]);
            }
         add(moyenneCPJLabel);
      
    }

    @Override
    public void notifyUpdatedItems() 
    {
         ajouterCoursParJour(CPJLabel);
         repaint();
    }
     

}
