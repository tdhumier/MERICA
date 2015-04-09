/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.frames;

import PlanIFTicateur.gui.panels.StatistiquesPanel;
import javax.swing.JFrame;

/**
 *
 * @author tristandhumieres
 */
public class StatistiquesWindow extends JFrame {

    private StatistiquesPanel statistiquesPanel;

    public StatistiquesWindow(MainWindow mainWindow) {
        setSize(500, 500);
        setTitle("Statistiques");
        setLocationRelativeTo(null);
        statistiquesPanel = new StatistiquesPanel(mainWindow);
        add(statistiquesPanel);
        setVisible(true);
    }

}
