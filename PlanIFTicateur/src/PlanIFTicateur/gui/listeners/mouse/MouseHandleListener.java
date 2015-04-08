/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.MainWindow;
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
        Dimension initialDimension = mainWindow.horairePanel.getDimensionsCase();
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
            int x = mainWindow.horairePanel.getXPosition(e.getX());
            int y = mainWindow.horairePanel.getYPosition(e.getY());
            double heure = mainWindow.horairePanel.getHeure(e.getX());
            int jour = mainWindow.horairePanel.getJour(e.getY());
            Optional<Activite> activite = mainWindow.controleur.getActiviteSelectionnee();
            if (activite.isPresent() && heure != 0.0d && jour != 0 && heure + activite.get().getDuree() <= 22) { // Si on a una activité présente et on a affecté un heure et un jour et la case ne dépasse pas de la grille
                Point point = new Point(x, y);
                if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) { // Si on est en mode vérif auto
                    mainWindow.controleur.deplacerActiviteAvecVerification(activite.get(), point, heure, jour, mainWindow.horairePanel.getDimensionsCase());
                } else {
                    mainWindow.controleur.deplacerActivite(activite.get(), point, heure, jour);
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
}
