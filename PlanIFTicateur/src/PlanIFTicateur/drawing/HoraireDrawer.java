/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.drawing;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class HoraireDrawer {

    private MainWindow mainWindow;
    private Dimension initialDimension;

    public HoraireDrawer(MainWindow mainWindow, Dimension initialDimension) {
        this.mainWindow = mainWindow;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g) {
        drawGrille(g);
        drawActivites(g);
        if (mainWindow.getVerificationMode() == MainWindow.VerificationMode.CHECKED) {
            disableCases(g);
        }
    }

    private void drawGrille(Graphics g) {
        int largeurCase = initialDimension.width;
        int hauteurCase = initialDimension.height;
        g.setColor(Color.LIGHT_GRAY);

        Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke();

        //Dessine les lignes verticale
        for (int x = 0; x <= 28; x++) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(s);
            if (x % 2 == 0) {
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(2));
            }
            g2.drawLine(x * largeurCase + 80, 20, x * largeurCase + 80, hauteurCase * 48 + 20);
        }
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(10, 20, 10, hauteurCase * 48 + 20);

        //Dessine les lignes horizontale
        for (int y = 0; y <= 48; y++) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(s);
            if (y % 8 == 0) {
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(10, y * hauteurCase + 20, largeurCase * 28 + 80, y * hauteurCase + 20);
            } else {
                g2.drawLine(80, y * hauteurCase + 20, largeurCase * 28 + 80, y * hauteurCase + 20);
            }
        }

        //Affiche les heures
        for (int i = 8; i <= 22; i++) {
            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Helvetica", Font.BOLD, 16));
            g2.drawString(i + "h", 70 + 2 * largeurCase * (i - 8), 15);
        }

        //Affiche les jours
        for (int i = 0; i < 6; i++) {

            String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
            g2.setColor(Color.DARK_GRAY);
            g2.setFont(new Font("Helvetica", Font.BOLD, 13));
            g2.drawString(jourModes[i], 17, 90 + i * hauteurCase * 8);
        }
    }

    //Dessine les activités présentes dans la grille
    private void drawActivites(Graphics g) {
        List<Activite> activites = mainWindow.controleur.getActivitesAssignees();
        activites.stream().filter((activite) -> (activite.getPoint().x != 0 && activite.getPoint().y != 0)).map((activite) -> new ActiviteDrawer(activite)).forEach((activiteDrawer) -> {
            activiteDrawer.drawActivite(g);
        });
    }

    private void disableCases(Graphics g) {
        if (mainWindow.controleur.getActiviteSelectionnee().isPresent()) {
            Activite activite = mainWindow.controleur.getActiviteSelectionnee().get();
            HashMap<Integer, List<Double>> plagesHoraireAGriser = mainWindow.controleur.getPlagesHoraireAGriser(activite);
            DisableCasesDrawer disableCasesDrawer = new DisableCasesDrawer(initialDimension, plagesHoraireAGriser, activite);
            disableCasesDrawer.disableCases(g);
        }
    }
}
