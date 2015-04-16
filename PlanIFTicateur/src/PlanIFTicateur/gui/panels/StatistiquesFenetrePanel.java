/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class StatistiquesFenetrePanel extends JPanel implements HoraireControleurObserveur {

    
    private MainWindow mainWindow;
    private JPanel coursParJourPanel;
    private JPanel indiceCongestionPanel;
    private JPanel statistiquePanel;
    
    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;
    private JLabel moyenneLabel;
    
    private JLabel coursParJourTitre;
    private JLabel lundiCPJLabel;
    private JLabel mardiCPJLabel;
    private JLabel mercrediCPJLabel;
    private JLabel jeudiCPJLabel;
    private JLabel vendrediCPJLabel;
    private JLabel samediCPJLabel;
    private JLabel moyenneCPJLabel;
    
    
    
    private JLabel indiceTitre;
    private JLabel lundiIndiceLabel;
    private JLabel mardiIndiceLabel;
    private JLabel mercrediIndiceLabel;
    private JLabel jeudiIndiceLabel;
    private JLabel vendrediIndiceLabel;
    private JLabel samediIndiceLabel;
    private JLabel moyenneIndiceLabel;
    
    private int codeStat;
    private  ArrayList<Integer> nbCoursParJour;
    private String moyenneText;
    
    public StatistiquesFenetrePanel()
    {
    }
    public StatistiquesFenetrePanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }
    
    private void buildUp()
    {
        setLayout(new BorderLayout());
        
        statistiquePanel = new JPanel();
        statistiquePanel.setLayout(new GridLayout(0,2));
        
        coursParJourPanel = new JPanel();
        coursParJourPanel.setLayout(new GridLayout(8,1));
        
        indiceCongestionPanel = new JPanel();
        indiceCongestionPanel.setLayout(new GridLayout(8,1));
      
        lundiLabel = new JLabel("Lundi : ");
        mardiLabel = new JLabel("Mardi : ");
        mercrediLabel = new JLabel("Mercredi : ");
        jeudiLabel = new JLabel("Jeudi : ");
        vendrediLabel = new JLabel("Vendredi : ");
        samediLabel = new JLabel("Samedi : ");
         moyenneLabel = new JLabel("Moyenne : ");
        
        coursParJourTitre = new JLabel("Nombre de cours par jour");
        lundiCPJLabel = new JLabel();
        mardiCPJLabel = new JLabel();
        mercrediCPJLabel = new JLabel();
        jeudiCPJLabel = new JLabel();
        vendrediCPJLabel = new JLabel();
        samediCPJLabel = new JLabel();
        moyenneCPJLabel = new JLabel();
     
        
        indiceTitre = new JLabel("Nombre de cours par jour");
        lundiIndiceLabel = new JLabel();
        mardiIndiceLabel = new JLabel();
        mercrediIndiceLabel = new JLabel();
        jeudiIndiceLabel = new JLabel();
        vendrediIndiceLabel = new JLabel();
        samediIndiceLabel = new JLabel();
        moyenneIndiceLabel = new JLabel();
        
        ajouterCoursParJour();
        ajouterIndiceCongestion();
        
        
        
        statistiquePanel.add(coursParJourPanel);
        statistiquePanel.add(indiceCongestionPanel);
        
        
        add(statistiquePanel);
    }
    
    
    
    private void ajouterCoursParJour()
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
        
        lundiCPJLabel.setText(lundiLabel.getText() + nbCoursParJour.get(0) + " Cours");
        mardiCPJLabel.setText(mardiLabel.getText() + nbCoursParJour.get(1) + " Cours");
        mercrediCPJLabel.setText(mercrediLabel.getText() + nbCoursParJour.get(2) + " Cours");
        jeudiCPJLabel.setText(jeudiLabel.getText() + nbCoursParJour.get(3) + " Cours");
        vendrediCPJLabel.setText(vendrediLabel.getText() + nbCoursParJour.get(4) + " Cours");
        samediCPJLabel.setText(samediLabel.getText() + nbCoursParJour.get(5) + " Cours");
        
        moyenneCPJLabel.setText(moyenneLabel.getText() + moyenneText + " Cours");
        
        coursParJourPanel.add(coursParJourTitre);
        coursParJourPanel.add(lundiCPJLabel);
        coursParJourPanel.add(mardiCPJLabel);
        coursParJourPanel.add(mercrediCPJLabel);
        coursParJourPanel.add(jeudiCPJLabel);
        coursParJourPanel.add(vendrediCPJLabel);
        coursParJourPanel.add(samediCPJLabel);
        coursParJourPanel.add(moyenneCPJLabel);
    }
    

    
    
    
    private void ajouterIndiceCongestion()
    {
        lundiIndiceLabel.setText(lundiLabel.getText() + nbCoursParJour.get(0) + " %");
        mardiIndiceLabel.setText(mardiLabel.getText() + nbCoursParJour.get(1) + " %");
        mercrediIndiceLabel.setText(mercrediLabel.getText() + nbCoursParJour.get(2) + " %");
        jeudiIndiceLabel.setText(jeudiLabel.getText() + nbCoursParJour.get(3) + " %");
        vendrediIndiceLabel.setText(vendrediLabel.getText() + nbCoursParJour.get(4) + " %");
        samediIndiceLabel.setText(samediLabel.getText() + nbCoursParJour.get(5) + " %");
        moyenneIndiceLabel.setText(moyenneLabel.getText() + " 10%");
        
        indiceCongestionPanel.add(indiceTitre);
        indiceCongestionPanel.add(lundiIndiceLabel);
        indiceCongestionPanel.add(mardiIndiceLabel);
        indiceCongestionPanel.add(mercrediIndiceLabel);
        indiceCongestionPanel.add(jeudiIndiceLabel);
        indiceCongestionPanel.add(vendrediIndiceLabel);
        indiceCongestionPanel.add(samediIndiceLabel);
       
    }

    @Override
    public void notifyUpdatedItems() {
        ajouterCoursParJour();
        ajouterIndiceCongestion();
        repaint();
    }
   
}
