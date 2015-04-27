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

    private final ActivitePanel activitePanel;
    private final MainWindow mainWindow;
    private final String title;
    
    public ActiviteWindow(String title, MainWindow mainWindow) {
      this.title = title;
      this.mainWindow = mainWindow;
      
        setSize(500, 500);
        setLocationRelativeTo(null);
        activitePanel = new ActivitePanel(mainWindow, this);
        setTitle(title);
        add(activitePanel);
        setVisible(true);
    }


}
