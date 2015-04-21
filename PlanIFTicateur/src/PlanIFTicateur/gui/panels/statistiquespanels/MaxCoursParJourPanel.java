/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquespanels;

import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Alexandre
 */
public class MaxCoursParJourPanel extends JPanel implements HoraireControleurObserveur
{
     private MainWindow mainWindow;
    private JTextArea maxCoursParJourTitre;
    private JLabel lundiMCPJLabel;
    private JLabel mardiMCPJLabel;
    private JLabel mercrediMCPJLabel;
    private JLabel jeudiMCPJLabel;
    private JLabel vendrediMCPJLabel;
    private JLabel samediMCPJLabel;
    private JLabel moyenneMCPJLabel;
    private JLabel[] listeLabel;
    private String[] jourModes;
    
    private  ArrayList<Integer> nbMaxCoursParJour;
    String moyenneText;

    public MaxCoursParJourPanel(MainWindow mainWindow, String[] jourModes)
    {
        this.mainWindow = mainWindow;
        this.jourModes = jourModes;
        mainWindow.controleur.registerObserver(this);
        setLayout(new GridLayout(8,1));
        buildUp();
    }
    
    private void buildUp()
    {
        maxCoursParJourTitre = new JTextArea("Nombre maximal de cours \n par jours");
        maxCoursParJourTitre.setEditable(false);
        maxCoursParJourTitre.setBackground(this.getBackground());
        maxCoursParJourTitre.setFont(new Font("Helvetica", Font.BOLD, 16));
        maxCoursParJourTitre.setAlignmentX(CENTER_ALIGNMENT);
        lundiMCPJLabel = new JLabel();
        mardiMCPJLabel = new JLabel();
        mercrediMCPJLabel = new JLabel();
        jeudiMCPJLabel = new JLabel();
        vendrediMCPJLabel = new JLabel();
        samediMCPJLabel = new JLabel();
        moyenneMCPJLabel = new JLabel();
        
         
        listeLabel = new JLabel[]{lundiMCPJLabel, mardiMCPJLabel, mercrediMCPJLabel, jeudiMCPJLabel, vendrediMCPJLabel, samediMCPJLabel};
       
        ajouterMaxCoursParJour(listeLabel);
    }
    
    private void ajouterMaxCoursParJour(JLabel[] listeLabel)
    {
        float moyenne = 0;
        ListeGrillesCheminement listeGrilleCheminement = mainWindow.controleur.getHoraire().getListeGrillesCheminement();
       
        
        nbMaxCoursParJour = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++)
        {
            nbMaxCoursParJour.add(mainWindow.controleur.getNbMaxCoursParJour(listeGrilleCheminement, i+1));
            moyenne += nbMaxCoursParJour.get(i);
        }
         
        moyenne = moyenne/6;
        moyenneText = Float.toString(moyenne);
        
        listeLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationIntegerLabel(jourModes, listeLabel, nbMaxCoursParJour, " Cours");
           
        moyenneMCPJLabel.setText("Moyenne : " + moyenneText + " Cours");
        
        add(maxCoursParJourTitre);
        
         for(int j = 0; j < 6; j++)
            {
                add(listeLabel[j]);
            }
         add(moyenneMCPJLabel);
    }
    
    @Override
    public void notifyUpdatedItems() 
    {
       ajouterMaxCoursParJour(listeLabel);
    }
    
}
