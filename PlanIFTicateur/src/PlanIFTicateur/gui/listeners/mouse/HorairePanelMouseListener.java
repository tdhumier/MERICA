/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.ActiviteWindow;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

/**
 *
 * @author tristandhumieres
 */
public class HorairePanelMouseListener extends MouseAdapter implements MouseMotionListener {

    private MainWindow mainWindow;
    private boolean isDragged;

    public HorairePanelMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.isDragged = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateBottomPanel(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateBottomPanel(e.getPoint());
        Optional<Activite> activite = mainWindow.controleur.getActiviteSelectionnee();
        if (activite.isPresent()) {
            isDragged = true;
            mainWindow.controleur.deplacerActivite(activite.get(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragged) { // Si on déplace avec la souris
            MousePositionHelper mousePositionHelper = new MousePositionHelper(mainWindow.horairePanel.getDimensionsCase());
            int jour = mousePositionHelper.getJour(e.getY());
            double heure = mousePositionHelper.getHeure(e.getX());
            int x = mousePositionHelper.getXPosition(e.getX());
            int y = mousePositionHelper.getYPosition(e.getY());
            Optional<Activite> activite = mainWindow.controleur.getActiviteSelectionnee();
            if (activite.isPresent() && heure != 0.0d && jour != 0 && heure + activite.get().getDuree() <= 22) { // Si on a una activité présente et on a affecté un heure et un jour et la case ne dépasse pas de la grille
                Point point = new Point(x, y);
                if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) { // Si on est en mode vérif auto
                    mainWindow.controleur.deplacerActiviteAvecVerification(activite.get(), point, heure, jour, mainWindow.horairePanel.getDimensionsCase());
                } else {
                    mainWindow.controleur.deplacerActivite(activite.get(), point, heure, jour);
                }
            } else if (activite.isPresent()) { // Si on n'a pas affecté de jour ni d'heure (c'est que l'horaire n'est pas correct), on replace à l'endroit initial
                mainWindow.controleur.desassignerActivite(activite.get());
            }
            isDragged = false; // La souris est relachée donc on ne drag plus
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mainWindow.controleur.getHoraire().reinitColor();
        int x = e.getX();
        int y = e.getY();
        mainWindow.controleur.modifierStatutSelectionActivite(x, y);
    }

    private void updateBottomPanel(Point mousePoint) {
        MousePositionHelper mousePositionHelper = new MousePositionHelper(mainWindow.horairePanel.getDimensionsCase());
        int jour = mousePositionHelper.getJour(mousePoint.y);
        double heure = mousePositionHelper.getHeure(mousePoint.x);
        mainWindow.bottomPanel.setHeureEtJour(jour, heure);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2) {
            if (mainWindow.controleur.getActiviteSelectionnee().isPresent()) {
                String activiteWindowTitle = mainWindow.controleur.getActiviteSelectionnee().get().getCode() + " - " + mainWindow.controleur.getActiviteSelectionnee().get().getTitre();
                new ActiviteWindow(activiteWindowTitle, mainWindow);
            }

        }
    }

}
