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
import java.awt.Rectangle;
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
        drawActivites(g);
        //disableCases(g, new CoursClasse("NTM", "ZZZZ", "Supertest", "JoeyStarr", "Cours en classe", 3.0, 11.50, 17.50, 4, 12.50));
    }

    public void drawGrille(Graphics g) {
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
            g2.drawString(i + "h", 70 + 2 * largeurCase * (i - 8), 15);
        }

        //Affiche les jours
        for (int i = 0; i < 6; i++) {
            String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
            g2.setColor(Color.GRAY);
            g2.drawString(jourModes[i], 20, 90 + i * hauteurCase * 8);
        }
    }

    public void drawActivites(Graphics g) {
        List<Activite> activites = controleur.getActivitesAssignees();
        for (int i = 0; i < activites.size(); i++) {
            drawActivite(g, activites.get(i));
            if (activites.get(i).isSelected()) {
                drawSelection(g, activites.get(i));
            }
        }
    }

    private void drawActivite(Graphics g, Activite activite) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(activite.getCouleur());
        g2.fill(new Rectangle.Double(activite.getPoint().x, activite.getPoint().y, activite.getWidth(), activite.getHeight()));
        g2.setColor(Color.black);
        g2.drawString(activite.getCode(), (int) (activite.getPoint().x + (activite.getWidth() / 2 - activite.getWidth() / (activite.getDuree() * 2))), activite.getPoint().y + activite.getHeight() - 4);
    }

    private void drawSelection(Graphics g, Activite activite) {
        g.setColor(Color.YELLOW);
        g.drawRect(activite.getPoint().x, activite.getPoint().y, activite.getWidth(), activite.getHeight());
    }

    private void disableCases(Graphics g, Activite activite) { // grise les cases qui ne respectent pas les contraintes
        int largeurCase = initialDimension.width;
        int hauteurCase = initialDimension.height;
        double heureDebutMinTest = activite.getHeureDebutMin();
        double heureFinMaxTest = activite.getHeureFinMax();
        int caseDebutMin;
        int caseFinMax;

        if ((int) heureDebutMinTest == heureDebutMinTest) {
            caseDebutMin = ((int) heureDebutMinTest - 8) * 2 + 1;
        } else {
            caseDebutMin = ((int) heureDebutMinTest - 8) * 2 + 2;
        }

        if ((int) heureFinMaxTest == heureFinMaxTest) {
            caseFinMax = ((int) heureFinMaxTest - 8) * 2;
        } else {
            caseFinMax = ((int) heureFinMaxTest - 8) * 2 + 1;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(80, 20, largeurCase * (caseDebutMin - 1), hauteurCase * 48);
        g2.fillRect(80 + caseFinMax * largeurCase, 20, largeurCase * (28 - caseFinMax), hauteurCase * 48);
    }
    
    public void ajouterActivite(Activite activite,int x, int y)
    {
        activite.setWidth(x);
        activite.setHeight(y);
        controleur.getActivitesAssignees().add(activite);
    }
}
