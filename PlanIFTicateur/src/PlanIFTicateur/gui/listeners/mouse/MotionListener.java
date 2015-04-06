/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.gui.MainWindow;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author martindeligny1
 */
public class MotionListener implements MouseMotionListener {

    private MainWindow mainWindow;

    public MotionListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Dimension initialDimension = mainWindow.horairePanel.getInitialDimension();
        int x = e.getX();
        int y = e.getY();
        int xo = 80;
        int yo = 20;
        int xCase = (x - xo) / initialDimension.width + 1;
        int yCase = (y - yo) / initialDimension.height + 1;
        int heureCase = ((xCase - 1) / 2) + 8;
        String jour = "";
        String heure;
        if (xCase % 2 == 0) {
            int heureCaseBefore = heureCase;
            heureCase++;
            heure = heureCaseBefore + "h30-" + heureCase + "h00";
        } else {
            heure = heureCase + "h00-" + heureCase + "h30";
        }

        if ((yCase - 1) / 8 == 0) {
            jour = "Lundi";
        }
        if ((yCase - 1) / 8 == 1) {
            jour = "Mardi";
        }
        if ((yCase - 1) / 8 == 2) {
            jour = "Mercredi";
        }
        if ((yCase - 1) / 8 == 3) {
            jour = "Jeudi";
        }
        if ((yCase - 1) / 8 == 4) {
            jour = "Vendredi";
        }
        if ((yCase - 1) / 8 == 5) {
            jour = "Samedi";
        }

        if (x > xo && x < (initialDimension.width * 28 + 80) && y > yo && y < (initialDimension.height * 48 + 20)) {
            mainWindow.bottomPanel.afficherJourHeure(jour + ", " + heure);
            mainWindow.bottomPanel.repaint();
        } else {
            mainWindow.bottomPanel.afficherJourHeure("");
            mainWindow.bottomPanel.repaint();
        }

    }

}
