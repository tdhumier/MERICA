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
        int largeur = initialDimension.width;
        int hauteur = initialDimension.height;

        g.setColor(Color.LIGHT_GRAY);
        for (int x = 100; x < largeur; x += initialDimension.width / 30) {
            g.drawLine(x, initialDimension.height / 47, x, hauteur);
        }
        for (int y = 50; y < hauteur; y += initialDimension.height / 47) {
            g.drawLine(100, y, largeur, y);
        }
    }

    public void drawActivite(Graphics g) {
        List<Activite> activites = controleur.getActiviteListe();
        for (int i = 0; i < activites.size(); i++) {
            Activite activite = activites.get(i);
            Color couleur = activite.getCouleur();
            g.setColor(couleur);
            g.fillOval((int) activite.getHeureDebut(), (int) activite.getJour(), (int) activite.getDuree(), 50);
        }
    }
}
