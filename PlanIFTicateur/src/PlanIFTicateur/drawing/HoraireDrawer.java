/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.drawing;

import PlanIFTicateur.domaine.HoraireActiviteControleur;
import PlanIFTicateur.domaine.activite.Activite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
        int largeurCase = (int) (initialDimension.width - 90) / 28;
        int hauteurCase = (int) (initialDimension.height - 30) / 48;
        g.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x <= 28; x++) {
            g.drawLine(x * largeurCase + 80, 20, x * largeurCase + 80, hauteurCase * 48 + 20);
        }

        for (int y = 0; y <= 48; y++) {
            g.drawLine(80, y * hauteurCase + 20, largeurCase * 28 + 80, y * hauteurCase + 20);
        }
        //g.drawLine(largeur - 10, 10, largeur - 10, hauteur - 10);
        for (int i = 8; i <= 22; i++) {
            g.drawString(i + "h", 70 + 2 * largeurCase * (i - 8), 15);
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
