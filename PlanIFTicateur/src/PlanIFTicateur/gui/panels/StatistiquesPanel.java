/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
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
    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;

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
        lundiLabel = new JLabel("");
        mardiLabel = new JLabel("");
        mercrediLabel = new JLabel("");
        jeudiLabel = new JLabel("");
        vendrediLabel = new JLabel("");
        samediLabel = new JLabel("");

        add(statistiqueLabel);
        add(lundiLabel);
        add(mardiLabel);
        add(mercrediLabel);
        add(jeudiLabel);
        add(vendrediLabel);
        add(samediLabel);
    }

    @Override
    public void notifyUpdatedItems() {

        ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        lundiLabel.setText(jourModes[0] + " : " + nbCoursParJour.get(0) + "cours");
        mardiLabel.setText(jourModes[1] + " : " + nbCoursParJour.get(1) + "cours");
        mercrediLabel.setText(jourModes[2] + " : " + nbCoursParJour.get(2) + "cours");
        jeudiLabel.setText(jourModes[3] + " : " + nbCoursParJour.get(3) + "cours");
        vendrediLabel.setText(jourModes[4] + " : " + nbCoursParJour.get(4) + "cours");
        samediLabel.setText(jourModes[5] + " : " + nbCoursParJour.get(5) + "cours");

        repaint();
    }
}
