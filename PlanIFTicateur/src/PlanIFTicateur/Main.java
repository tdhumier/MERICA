/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur;

/**
 *
 * @author Alexandre
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PlanIFTicateur.gui.frames.MainWindow mainWindow = new PlanIFTicateur.gui.frames.MainWindow();
        mainWindow.setVisible(true);
      //  mainWindow.controleur.importerFichiers("/Users/Alexandre/Desktop/test/casHiver2015.COU", mainWindow.horairePanel.getDimensionsCase());
    }

}
