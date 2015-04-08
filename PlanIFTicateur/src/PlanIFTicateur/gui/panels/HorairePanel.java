/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.frames.MainWindow;
import PlanIFTicateur.gui.frames.MainWindow;
import PlanIFTicateur.gui.listeners.mouse.MouseHandleListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class HorairePanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private Dimension dimensionsCase;

    public HorairePanel() {
    }

    public HorairePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
        mainWindow.controleur.registerObserver(this);
    }

    private void buildUp() {
        int largeurEcran = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension((int) (largeurEcran * 0.7), 800));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setBackground(Color.WHITE);
        int largeurCase = (int) (largeurEcran * 0.7 - 90) / 28;
        int hauteurCase = (int) 770 / 48;
        dimensionsCase = new Dimension(largeurCase, hauteurCase);
        MouseHandleListener mouseHandleListener = new MouseHandleListener(mainWindow);
        this.addMouseListener(mouseHandleListener);
        this.addMouseMotionListener(mouseHandleListener);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (mainWindow != null) {
            super.paintComponent(g);
            HoraireDrawer horaireDrawer = new HoraireDrawer(mainWindow, dimensionsCase);
            horaireDrawer.draw(g);
        }
    }

    @Override
    public void notifyUpdatedItems() {
        repaint();
    }

    //Obtient l'heure à partir d'une abscisse sur le panel HorairePanel
    public double getHeure(int x) {
        int xo = 80;//Prise en compte de la marge (toujours la même quelque soit la résolution de l'écran)
        int xCase = (x - xo) / dimensionsCase.width + 1;
        double heureCase = ((xCase - 1) / 2) + 8;
        if (x < xo || x > (dimensionsCase.width * 28 + 80)) {
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

    //Obtient le jour à partir d'une ordonnée sur la panel HorairePanel
    public int getJour(int y) {
        int yo = 20;//Prise en compte de la marge (toujours la même quelque soit la résolution de l'écran)
        int yCase = (y - yo) / dimensionsCase.height + 1;
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

    //Retourne l'abscisse exacte pour l'heure la plus proche en fonction d'une abscisse x sur la grille
    public int getXPosition(int x) {
        int xo = 80;
        int xCase = (x - xo) / dimensionsCase.width + 1;
        return ((xCase - 1) * dimensionsCase.width + xo);
    }

    //Retourne l'ordonnée exacte pour l'heure la plus proche en fonction d'une ordonnée y sur la grille
    public int getYPosition(int y) {
        int yo = 20;
        int yCase = (y - yo) / dimensionsCase.height + 1;
        return ((yCase - 1) * dimensionsCase.height + yo);
    }

    public Dimension getDimensionsCase() {
        return this.dimensionsCase;
    }
}
