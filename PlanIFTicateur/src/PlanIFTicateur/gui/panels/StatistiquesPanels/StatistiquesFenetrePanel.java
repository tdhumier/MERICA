/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.StatistiquesPanels;

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
public class StatistiquesFenetrePanel extends JPanel{

    
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
        buildUp();
    }
    
    private void buildUp()
    {
        setLayout(new BorderLayout());
        
        statistiquePanel = new JPanel();
        statistiquePanel.setLayout(new GridLayout(0,2));
      
        lundiLabel = new JLabel("Lundi : ");
        mardiLabel = new JLabel("Mardi : ");
        mercrediLabel = new JLabel("Mercredi : ");
        jeudiLabel = new JLabel("Jeudi : ");
        vendrediLabel = new JLabel("Vendredi : ");
        samediLabel = new JLabel("Samedi : ");
        moyenneLabel = new JLabel("Moyenne : ");
        
     
        
        coursParJourPanel = new CoursParJourPanel(mainWindow, jourModes);
          
        indiceCongestionPanel = new IndiceCongestionPanel(mainWindow, jourModes );
       
        
        statistiquePanel.add(coursParJourPanel);
        statistiquePanel.add(indiceCongestionPanel);
        
        
        add(statistiquePanel);
    
        
    }
   public String[] getJourModes()
        {
            return jourModes;
        }   
}
