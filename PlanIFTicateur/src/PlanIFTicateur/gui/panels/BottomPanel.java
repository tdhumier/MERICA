/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class BottomPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel labelBottomPanel;
    private JLabel validiteHorraireLabel;
    private Boolean isValide;

    public BottomPanel() {
    }

    public BottomPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension(width, 30));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setLayout(new FlowLayout());
        labelBottomPanel = new JLabel("Prêt");
        validiteHorraireLabel = new JLabel("");
        add(labelBottomPanel);
        add(new JLabel(" - "));
        add(validiteHorraireLabel);
        setVisible(true);
    }

    public void setHeureEtJour(int jour, double heure) {
        if (jour != 0 && heure != 0.0d) {
            String heureLabel = convertHeureToString(heure);
            String jourLabel = getNomJour(jour);
            labelBottomPanel.setText(jourLabel + ", " + heureLabel);
        } else {
            labelBottomPanel.setText("Prêt");
        }
        repaint();
    }

    private String convertHeureToString(double heure) {
        String heureLabel;
        if (heure == (int) heure) {
            heureLabel = ((int) heure) + "h00-" + ((int) heure) + "h30";
        } else {
            heureLabel = ((int) heure) + "h30-" + ((int) (heure + 1)) + "h00";
        }
        return heureLabel;
    }

    private String getNomJour(int i) {
        String[] JourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        return JourModes[i - 1];
    }

    @Override
    public void notifyUpdatedItems() {
        isValide = mainWindow.controleur.horaireEstValide();
        if (isValide) {
            validiteHorraireLabel.setText("Horaire valide");
            validiteHorraireLabel.setForeground(Color.GREEN);
        } else {
            validiteHorraireLabel.setText("Horaire non valide !");
            validiteHorraireLabel.setForeground(Color.RED);
        }
        repaint();
    }

}
