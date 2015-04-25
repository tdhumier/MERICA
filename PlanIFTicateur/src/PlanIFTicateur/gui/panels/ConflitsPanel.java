/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.conflit.Conflit;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tristandhumieres
 */
public class ConflitsPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;

    public ConflitsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        setLayout(new GridLayout(0, 1));
        afficherConflits();
    }

    private void afficherConflits() {
        List<Conflit> conflits = mainWindow.controleur.getConflits();
        conflits.stream().map((conflit) -> conflit.afficherConflit()).forEach((label) -> {
            this.add(new JLabel(label));
        });
    }

    @Override
    public void notifyUpdatedItems() {
        this.removeAll();
        afficherConflits();
        revalidate();
        repaint();
    }
}
