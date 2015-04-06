/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tristandhumieres
 */
public class StatistiquesPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel statistiqueLabel;

    public StatistiquesPanel() {
    }

    public StatistiquesPanel(MainWindow maindow) {
        this.mainWindow = maindow;
        maindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        setLayout(new GridLayout(0, 1));
        statistiqueLabel = new JLabel("Statistiques");

        add(statistiqueLabel);
    }

    @Override
    public void notifyUpdatedItems() {

        ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        for (int i = 0; i < 6; i++) {
            JLabel statistiqueNbCoursParJourLabel = new JLabel(jourModes[i] + " : " + nbCoursParJour.get(i) + " cours");
            add(statistiqueNbCoursParJourLabel);
        }

        repaint();
    }
}
