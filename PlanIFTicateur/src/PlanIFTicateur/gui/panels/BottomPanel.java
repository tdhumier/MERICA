/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
        setLayout(new BorderLayout());
        labelBottomPanel = new JLabel("");
        validiteHorraireLabel = new JLabel("");
        add(labelBottomPanel, BorderLayout.WEST);
        add(validiteHorraireLabel, BorderLayout.EAST);
        setVisible(true);
    }

    public void setText(String s) {
        labelBottomPanel.setText(s);
    }

    @Override
    public void notifyUpdatedItems() {
        isValide = mainWindow.controleur.isHoraireValide();
        if (isValide) {
            validiteHorraireLabel.setText("Horaire valide");
        } else {
            validiteHorraireLabel.setText("Horaire non valide !");
        }
        repaint();
    }

}
