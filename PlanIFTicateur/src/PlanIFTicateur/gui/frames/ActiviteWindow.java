/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.frames;

import PlanIFTicateur.gui.panels.ActivitePanel;
import javax.swing.JFrame;

/**
 *
 * @author tristandhumieres
 */
public class ActiviteWindow extends JFrame {

    private ActivitePanel activitePanel;

    public ActiviteWindow(String title) {
        super(title);
        setSize(500, 500);
        setLocationRelativeTo(null);
        activitePanel = new ActivitePanel();
        add(activitePanel);
        setVisible(true);
    }

}
