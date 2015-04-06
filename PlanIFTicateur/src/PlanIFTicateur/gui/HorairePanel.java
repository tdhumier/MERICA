/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.drawing.HoraireDrawer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class HorairePanel extends JPanel implements MouseMotionListener {

    private Dimension initialDimension;
    private MainWindow mainWindow;

    public HorairePanel() {
    }

    public HorairePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension((int) (width * 0.7), 800));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setVisible(true);
        initialDimension = new Dimension((int) (width * 0.7), 800);
        System.out.println("width: " + initialDimension.width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (mainWindow != null) {
            super.paintComponent(g);
            HoraireDrawer horaireDrawer = new HoraireDrawer(mainWindow.controleur, initialDimension);
            horaireDrawer.draw(g);
        }
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        int largeurCase = (int) (initialDimension.width - 90) / 28;
        int hauteurCase = (int) (initialDimension.height - 30) / 48;
        int x = e.getX();
        int y = e.getY();
        int xo = 80;
        int yo = 20;
        int xCase = (x - xo) / largeurCase + 1;
        int yCase = (y - yo) / hauteurCase + 1;
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

        if (x > xo && x < (initialDimension.width - 20) && y > yo && y < (initialDimension.height - 10)) {
            System.out.println("case : " + xCase + "," + yCase);
            System.out.println(jour);
            System.out.println(heure);
        } else {
            System.out.println("out");
        }

    }

}
