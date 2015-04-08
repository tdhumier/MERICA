/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alexandre
 */
public class ListActiviteMouseListener extends MouseAdapter implements MouseMotionListener {

    private MainWindow mainWindow;
    private Activite activiteSelectionnee;
    private boolean isDragged;

    public ListActiviteMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.activiteSelectionnee = null;
        this.isDragged = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        activiteSelectionnee = (Activite) mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue();
        mainWindow.controleur.setActiviteSelectionnee(activiteSelectionnee, true);
        e.getComponent().setEnabled(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), point, mainWindow.horairePanel);
        if (activiteSelectionnee != null) {
            isDragged = true;
            mainWindow.controleur.deplacerActivite(activiteSelectionnee, pointHorairePanel.x, pointHorairePanel.y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), mainWindow.horairePanel);
        Point point = new Point(mainWindow.horairePanel.getXPosition(pointHorairePanel.x), mainWindow.horairePanel.getYPosition(pointHorairePanel.y));
        double heure = mainWindow.horairePanel.getHeure(pointHorairePanel.x);
        int jour = mainWindow.horairePanel.getJour(pointHorairePanel.y);
        if (isDragged) {
            if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) { // Si on est en mode vérif auto
                mainWindow.controleur.deplacerActiviteAvecVerification(activiteSelectionnee, point, heure, jour, mainWindow.horairePanel.getDimensionsCase());
            } else {
                mainWindow.controleur.deplacerActivite(activiteSelectionnee, point, heure, jour);
            }
        }
        isDragged = false;
        e.getComponent().setEnabled(true);
    }
}
