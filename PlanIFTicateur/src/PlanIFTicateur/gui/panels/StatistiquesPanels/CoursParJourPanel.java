/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.StatistiquesPanels;

import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class CoursParJourPanel extends JPanel{
    
    private MainWindow mainWindow;
    private JLabel coursParJourTitre;
    private JLabel lundiCPJLabel;
    private JLabel mardiCPJLabel;
    private JLabel mercrediCPJLabel;
    private JLabel jeudiCPJLabel;
    private JLabel vendrediCPJLabel;
    private JLabel samediCPJLabel;
    private JLabel moyenneCPJLabel;
    
    private int codeStat;
    private  ArrayList<Integer> nbCoursParJour;
    String moyenneText;
    
    public CoursParJourPanel(MainWindow mainWindow, JLabel[] listeLabel, String[] jourModes)
    {
        this.mainWindow = mainWindow;
        setLayout(new GridLayout(8,1));
        buildUp(listeLabel, jourModes);
    }
    
    
    private void buildUp(JLabel[] listeLabel, String[] jourModes)
    {
        coursParJourTitre = new JLabel("Nombre de cours par jour");
        lundiCPJLabel = new JLabel();
        mardiCPJLabel = new JLabel();
        mercrediCPJLabel = new JLabel();
        jeudiCPJLabel = new JLabel();
        vendrediCPJLabel = new JLabel();
        samediCPJLabel = new JLabel();
        moyenneCPJLabel = new JLabel();
        
        ajouterCoursParJour(listeLabel, jourModes);
    }
    
     private void ajouterCoursParJour(JLabel[] listeLabel, String[] jourModes)
    {
        codeStat = 0;
        
        float moyenne = 0;
        
        nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        for(int i = 0; i < 6; i++)
        {
            moyenne += nbCoursParJour.get(i);
        }
        moyenne = moyenne/6;
        moyenneText = Float.toString(moyenne);
        
        mainWindow.controleur.getHoraire().getStatistiques().modificationIntegerLabel(jourModes, listeLabel, nbCoursParJour, " Cours");
        
        lundiCPJLabel.setText(listeLabel[0].getText() + nbCoursParJour.get(0) + " Cours");
        mardiCPJLabel.setText(listeLabel[1].getText() + nbCoursParJour.get(1) + " Cours");
        mercrediCPJLabel.setText(listeLabel[2].getText() + nbCoursParJour.get(2) + " Cours");
        jeudiCPJLabel.setText(listeLabel[3].getText() + nbCoursParJour.get(3) + " Cours");
        vendrediCPJLabel.setText(listeLabel[4].getText() + nbCoursParJour.get(4) + " Cours");
        samediCPJLabel.setText(listeLabel[5].getText() + nbCoursParJour.get(5) + " Cours");
        
        moyenneCPJLabel.setText(listeLabel[6].getText() + moyenneText + " Cours");
        
        add(coursParJourTitre);
        add(lundiCPJLabel);
        add(mardiCPJLabel);
        add(mercrediCPJLabel);
        add(jeudiCPJLabel);
        add(vendrediCPJLabel);
        add(samediCPJLabel);
        add(moyenneCPJLabel);
    }
}
