/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.ListeActivitesPanel;
import PlanIFTicateur.gui.MainWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Alexandre
 */
public class ListActiviteMouseListener extends MouseAdapter implements MouseMotionListener {

    private ListeActivitesPanel listeActivitesPanel;

    private MainWindow mainWindow;

    public ListActiviteMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mainWindow.horairePanel.setNouveauDragged(true);
        Activite activite = (Activite) mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue();
        if (activite != null) {
            mainWindow.controleur.setActiviteSelectionnee(activite, true);
            mainWindow.horairePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
