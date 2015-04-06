/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.listeners.mouse.MotionListener;
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

    public Dimension getInitialDimension() {
        return this.initialDimension;
    }

    public HorairePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension((int) (width * 0.7), 800));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setVisible(true);
        int largeurCase = (int) (width * 0.7 - 90) / 28;
        int hauteurCase = (int) 770 / 48;
        initialDimension = new Dimension(largeurCase, hauteurCase);
        System.out.println("width: " + initialDimension.width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (mainWindow != null) {
            super.paintComponent(g);
            HoraireDrawer horaireDrawer = new HoraireDrawer(mainWindow.controleur, initialDimension);
            horaireDrawer.draw(g);
        }
        this.addMouseMotionListener(new MotionListener(mainWindow));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
