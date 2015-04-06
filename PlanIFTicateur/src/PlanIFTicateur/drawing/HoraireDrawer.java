/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.drawing;

import PlanIFTicateur.domaine.HoraireActiviteControleur;
import PlanIFTicateur.domaine.activite.Activite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class HoraireDrawer {

    private HoraireActiviteControleur controleur;
    private Dimension initialDimension;

    public HoraireDrawer(HoraireActiviteControleur controleur, Dimension initialDimension) {
        this.controleur = controleur;
        this.initialDimension = initialDimension;
    }

    public void draw(Graphics g) {
        drawGrille(g);
        drawActivite(g);
    }

    public void drawGrille(Graphics g) {
        int largeurCase = initialDimension.width;
        int hauteurCase = initialDimension.height;
        g.setColor(Color.LIGHT_GRAY);

        Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke();

        //Dessine les lignes verticale
        for (int x = 0; x <= 28; x++) {
            g2.setStroke(s);
            if (x % 2 == 0) {
                g2.setStroke(new BasicStroke(2));
            }
            g2.drawLine(x * largeurCase + 80, 20, x * largeurCase + 80, hauteurCase * 48 + 20);
        }

        g2.setStroke(new BasicStroke(2));
        g2.drawLine(10, 20, 10, hauteurCase * 48 + 20);

        //Dessine les lignes horizontale
        for (int y = 0; y <= 48; y++) {
            g2.setStroke(s);
            if (y % 8 == 0) {
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(10, y * hauteurCase + 20, largeurCase * 28 + 80, y * hauteurCase + 20);
            } else {
                g2.drawLine(80, y * hauteurCase + 20, largeurCase * 28 + 80, y * hauteurCase + 20);
            }

        }

        //Affiche les heures
        for (int i = 8; i <= 22; i++) {
            g2.drawString(i + "h", 70 + 2 * largeurCase * (i - 8), 15);
        }
    }

    public void drawActivite(Graphics g) {
        List<Activite> activites = controleur.getActiviteListe();
        for (int i = 0; i < activites.size(); i++) {
            Activite activite = activites.get(i);
            Color couleur = activite.getCouleur();
            g.setColor(couleur);

            g.drawRect((int) activite.getHeureDebut(), (int) activite.getJour(), 80, 50);
        }
    }
}
