/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquespanels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class StatistiquesFenetrePanel extends JPanel implements HoraireControleurObserveur{

    
    private MainWindow mainWindow;
    private CoursParJourPanel coursParJourPanel;
    private IndiceCongestionPanel indiceCongestionPanel;
    private IndiceCovoituragePanel indiceCovoituragePanel;
    private MaxCoursParJourPanel maxCoursParJourPanel;
    private MoyenCoursParJourPanel moyenCoursParJourPanel;
    private JPanel statistiquePanel;
    
    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;
    private JLabel moyenneLabel;

    private String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

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
        statistiquePanel.setPreferredSize(new Dimension(800,800));
        statistiquePanel.setLayout(new GridLayout(0,3));
      
        lundiLabel = new JLabel("Lundi : ");
        mardiLabel = new JLabel("Mardi : ");
        mercrediLabel = new JLabel("Mercredi : ");
        jeudiLabel = new JLabel("Jeudi : ");
        vendrediLabel = new JLabel("Vendredi : ");
        samediLabel = new JLabel("Samedi : ");
        moyenneLabel = new JLabel("Moyenne : ");
        
     
        
        coursParJourPanel = new CoursParJourPanel(mainWindow, jourModes);
        maxCoursParJourPanel = new MaxCoursParJourPanel(mainWindow, jourModes);  
        indiceCongestionPanel = new IndiceCongestionPanel(mainWindow, jourModes );
        moyenCoursParJourPanel = new MoyenCoursParJourPanel(mainWindow, jourModes);
       
        
        statistiquePanel.add(coursParJourPanel);
        statistiquePanel.add(indiceCongestionPanel);
        statistiquePanel.add(moyenCoursParJourPanel);
        statistiquePanel.add(maxCoursParJourPanel);
        
        
        add(statistiquePanel);
 
    }
   
    
   public String[] getJourModes()
        {
            return jourModes;
        }   

    @Override
    public void notifyUpdatedItems() {
    
       repaint();
     }
    
    
}
