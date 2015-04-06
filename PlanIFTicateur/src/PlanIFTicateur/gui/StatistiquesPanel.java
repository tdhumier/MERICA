/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tristandhumieres
 */
public class StatistiquesPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel label;

    public StatistiquesPanel() {
    }

    public StatistiquesPanel(MainWindow maindow) {
        this.mainWindow = maindow;
        maindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        label = new JLabel("Stats");
        add(label);
    }

    @Override
    public void notifyUpdatedItems() {
        HashMap map = mainWindow.controleur.getNbCoursSemaine();

        label.setText("coucou");
        repaint();
    }
}
