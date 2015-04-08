/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.panels.ListeActivitesPanel;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Dimension;
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

    private ListeActivitesPanel listeActivitesPanel;

    private MainWindow mainWindow;
    private Activite activiteSelectionnee;

    public ListActiviteMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.activiteSelectionnee = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        //    mainWindow.horairePanel.setNouveauDragged(true);
//        Point point = e.getPoint();
//        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), point, mainWindow.horairePanel);
//        System.out.println(pointHorairePanel);
        activiteSelectionnee = (Activite) mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue();
        mainWindow.controleur.setActiviteSelectionnee(activiteSelectionnee, true);
        e.getComponent().setEnabled(false);
        System.out.println(activiteSelectionnee);
        //if (activiteSelectionnee != null) {
//            mainWindow.controleur.setActiviteSelectionnee(activiteSelectionnee, true);
//            mainWindow.horairePanel.repaint();
//        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), point, mainWindow.horairePanel);
        System.out.println("Drag : " + activiteSelectionnee);
        if (activiteSelectionnee != null) {
            mainWindow.controleur.deplacerActivite(activiteSelectionnee, pointHorairePanel.x, pointHorairePanel.y);
            mainWindow.horairePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        Point point = e.getPoint();
//        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), point, mainWindow.horairePanel);
//        System.out.println(pointHorairePanel);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();
        Point pointHorairePanel = SwingUtilities.convertPoint(e.getComponent(), point, mainWindow.horairePanel);
        System.out.println(pointHorairePanel);
        Activite activite = mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue();
        //e.getComponent().setEnabled(false);
        mainWindow.controleur.deplacerActivite(activiteSelectionnee, pointHorairePanel, getHeure(pointHorairePanel.x), getJour(pointHorairePanel.y));
        mainWindow.horairePanel.repaint();
        e.getComponent().setEnabled(true);
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
