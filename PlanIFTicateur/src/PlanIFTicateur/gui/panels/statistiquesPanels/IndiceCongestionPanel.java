/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquesPanels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class IndiceCongestionPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel indiceTitre;
    private JLabel lundiIndiceLabel;
    private JLabel mardiIndiceLabel;
    private JLabel mercrediIndiceLabel;
    private JLabel jeudiIndiceLabel;
    private JLabel vendrediIndiceLabel;
    private JLabel samediIndiceLabel;
    private JLabel moyenneIndiceLabel;
    private JLabel[] listeLabel;
    private String[] jourModes;
    private ArrayList<Float> indiceCongestionParJour;

    public IndiceCongestionPanel(MainWindow mainWindow, String[] jourModes) {
        this.mainWindow = mainWindow;
        this.jourModes = jourModes;
        mainWindow.controleur.registerObserver(this);
        setLayout(new GridLayout(8, 1));
        buildUp();
    }

    private void buildUp() {
        indiceTitre = new JLabel("Indice de congestion");
        indiceTitre.setFont(new Font("Helvetica", Font.BOLD, 16));
        lundiIndiceLabel = new JLabel();
        mardiIndiceLabel = new JLabel();
        mercrediIndiceLabel = new JLabel();
        jeudiIndiceLabel = new JLabel();
        vendrediIndiceLabel = new JLabel();
        samediIndiceLabel = new JLabel();
        moyenneIndiceLabel = new JLabel();

        listeLabel = new JLabel[]{lundiIndiceLabel, mardiIndiceLabel, mercrediIndiceLabel, jeudiIndiceLabel, vendrediIndiceLabel, samediIndiceLabel};

        ajouterIndiceCongestion(listeLabel);
    }

    private void ajouterIndiceCongestion(JLabel[] listeLabel) {
        int moyenne = 0;

        indiceCongestionParJour = new ArrayList<Float>();
        for (int i = 0; i < 6; i++) {
            indiceCongestionParJour.add(mainWindow.controleur.getIndiceCongestion(i + 1));
            moyenne += indiceCongestionParJour.get(i);
        }

        listeLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, listeLabel, indiceCongestionParJour, " %");

        moyenne = moyenne / 6;
        moyenneIndiceLabel.setText("Moyenne : " + moyenne + " %");

        add(indiceTitre);
        for (int j = 0; j < 6; j++) {
            add(listeLabel[j]);
        }
        add(moyenneIndiceLabel);

    }

    @Override
    public void notifyUpdatedItems() {
        ajouterIndiceCongestion(listeLabel);
    }

}
