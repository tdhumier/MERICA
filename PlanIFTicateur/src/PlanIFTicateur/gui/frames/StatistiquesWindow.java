/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.frames;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.panels.StatistiquesFenetrePanel;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author tristandhumieres
 */
public class StatistiquesWindow extends JFrame{

    private StatistiquesFenetrePanel statistiquesPanel;
        
    private MainWindow mainWindow;

    public StatistiquesWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSize(800, 800);
        setTitle("Statistiques");
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0,2));
        
          statistiquesPanel = new StatistiquesFenetrePanel(mainWindow);
        add(statistiquesPanel);
        setVisible(true);
       
    }

}
