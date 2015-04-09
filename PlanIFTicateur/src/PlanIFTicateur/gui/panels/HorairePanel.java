/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.frames.MainWindow;
import PlanIFTicateur.gui.listeners.mouse.HorairePanelMouseListener;
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
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setBackground(Color.WHITE);
        int largeurCase = (int) (largeurEcran * 0.7 - 90) / 28;
        int hauteurCase = (int) 770 / 48;
        dimensionsCase = new Dimension(largeurCase, hauteurCase);
        HorairePanelMouseListener mouseHandleListener = new HorairePanelMouseListener(mainWindow);
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

    public Dimension getDimensionsCase() {
        return this.dimensionsCase;
    }
}
