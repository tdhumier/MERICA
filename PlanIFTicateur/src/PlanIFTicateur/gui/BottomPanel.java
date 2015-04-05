/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import java.awt.Dimension;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class BottomPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    private JLabel labelBottomPanel;

    public BottomPanel() {
    }

    public BottomPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
    }

    private void buildUp() {
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension(1, 30));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        labelBottomPanel = new JLabel("Barre d'état");
        add(labelBottomPanel);
        setVisible(true);
    }

}
