/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
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

        HashMap nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        Set cles = nbCoursParJour.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()) {
            Object jour = it.next(); // tu peux typer plus finement ici
            Object nbCours = nbCoursParJour.get(jour); // tu peux typer plus finement ici
            JLabel statistiqueNbCoursParJourLabel = new JLabel(jour + " : " + nbCours + " cours");
            add(statistiqueNbCoursParJourLabel);
        }

        repaint();
    }
}
