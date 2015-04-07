/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.MainWindow;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Alexandre
 */
public class SecondMouseHandleListener extends MouseAdapter implements MouseMotionListener {

    private boolean nouveauDragged;
    private MainWindow mainWindow;

    public SecondMouseHandleListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.nouveauDragged = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
        int x = e.getX();
        int y = e.getY();
        if (mainWindow.horairePanel.getNouveauDragged()) {
            Point point = new Point(getXPosition(x), getYPosition(y));
            Activite activite = mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue();
            if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) { // Si on est en mode vérif auto
                mainWindow.controleur.deplacerActiviteAvecVerification(activite, point, getHeure(x), getJour(y), mainWindow.horairePanel.getInitialDimension());
            } else {
                mainWindow.controleur.deplacerActivite(activite, point, getHeure(x), getJour(y));
            }
            mainWindow.controleur.setActiviteSelectionnee(activite, false);
        }
        mainWindow.horairePanel.setNouveauDragged(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mainWindow.horairePanel.setNouveauDragged(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private double getHeure(int x) {
        Dimension initialDimension = mainWindow.horairePanel.getInitialDimension();
        int xo = 80;
        int xCase = (x - xo) / initialDimension.width + 1;
        double heureCase = ((xCase - 1) / 2) + 8;
        if (x < xo || x > (initialDimension.width * 28 + 80)) {
            return 0.0d;
        } else {
            if (xCase % 2 == 0) {
                double heureCaseBefore = heureCase;
                return heureCaseBefore + 0.5;
            } else {
                return heureCase;
            }
        }
    }

    private int getJour(int y) {
        Dimension initialDimension = mainWindow.horairePanel.getInitialDimension();

        int yo = 20;

        int yCase = (y - yo) / initialDimension.height + 1;

        if ((yCase - 1) / 8 == 0) {
            return 1;
        }
        if ((yCase - 1) / 8 == 1) {
            return 2;
        }
        if ((yCase - 1) / 8 == 2) {
            return 3;
        }
        if ((yCase - 1) / 8 == 3) {
            return 4;
        }
        if ((yCase - 1) / 8 == 4) {
            return 5;
        }
        if ((yCase - 1) / 8 == 5) {
            return 6;
        }

        return 0;
    }

    private int getXPosition(int x) {
        Dimension initialDimension = mainWindow.horairePanel.getInitialDimension();
        int xo = 80;
        int xCase = (x - xo) / initialDimension.width + 1;
        return ((xCase - 1) * initialDimension.width + xo);
    }

    private int getYPosition(int y) {
        Dimension initialDimension = mainWindow.horairePanel.getInitialDimension();
        int yo = 20;
        int yCase = (y - yo) / initialDimension.height + 1;
        return ((yCase - 1) * initialDimension.height + yo);
    }

}
