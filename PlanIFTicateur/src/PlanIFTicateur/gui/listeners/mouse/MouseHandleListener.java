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
import java.util.Optional;

/**
 *
 * @author tristandhumieres
 */
public class MouseHandleListener extends MouseAdapter implements MouseMotionListener {

    private MainWindow mainWindow;
    private boolean isDragged;
    private boolean nouveauDragged = false;

    public MouseHandleListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.isDragged = false;
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
            mainWindow.bottomPanel.setText(jour + ", " + heure);
            mainWindow.bottomPanel.repaint();
        } else {
            mainWindow.bottomPanel.setText("Prêt");
            mainWindow.bottomPanel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Optional<Activite> activite = mainWindow.controleur.getActiviteSelectionnee();
        if (activite.isPresent()) {
            isDragged = true;
            mainWindow.controleur.deplacerActivite(activite.get(), x, y);
            mainWindow.horairePanel.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragged) { // Si on déplace avec la souris
            int x = e.getX();
            int y = e.getY();
            Optional<Activite> activite = mainWindow.controleur.getActiviteSelectionnee();
            if (activite.isPresent() && getHeure(x) != 0.0d && getJour(y) != 0 && getHeure(x) + activite.get().getDuree() <= 22) { // Si on a una activité présente et on a affecté un heure et un jour et la case ne dépasse pas de la grille
                Point point = new Point(getXPosition(x), getYPosition(y));
                if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) { // Si on est en mode vérif auto
                    mainWindow.controleur.deplacerActiviteAvecVerification(activite.get(), point, getHeure(x), getJour(y), mainWindow.horairePanel.getInitialDimension());
                } else {
                    mainWindow.controleur.deplacerActivite(activite.get(), point, getHeure(x), getJour(y));
                }
            } else if (activite.isPresent()) { // Si on n'a pas affecté de jour ni d'heure (c'est que l'horaire n'est pas correct), on replace à l'endroit initial
                mainWindow.controleur.unasignActivite(activite.get());
            }
            isDragged = false; // La souris est relachée donc on ne drag plus
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        mainWindow.controleur.modifierStatutSelectionActivite(x, y);
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
