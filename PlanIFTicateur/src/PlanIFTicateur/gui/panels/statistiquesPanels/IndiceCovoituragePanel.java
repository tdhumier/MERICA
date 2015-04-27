/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquesPanels;

import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
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
public class IndiceCovoituragePanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;

    private JLabel covoiturageTitre;
    private JLabel lundiCovoiturageLabel;
    private JLabel mardiCovoiturageLabel;
    private JLabel mercrediCovoiturageLabel;
    private JLabel jeudiCovoiturageLabel;
    private JLabel vendrediCovoiturageLabel;
    private JLabel samediCovoiturageLabel;
    private JLabel moyenneCovoiturageLabel;
    private JLabel[] listeLabel;
    private String[] jourModes;
    private ArrayList<Float> indiceCovoiturageParJour;

    public IndiceCovoituragePanel(MainWindow mainWindow, String[] jourModes) {
        this.jourModes = jourModes;
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        setLayout(new GridLayout(8, 1));
        buildUp();
    }

    private void buildUp() {
        covoiturageTitre = new JLabel("Indice de covoiturage");
        covoiturageTitre.setFont(new Font("Helvetica", Font.BOLD, 16));
        lundiCovoiturageLabel = new JLabel();
        mardiCovoiturageLabel = new JLabel();
        mercrediCovoiturageLabel = new JLabel();
        jeudiCovoiturageLabel = new JLabel();
        vendrediCovoiturageLabel = new JLabel();
        samediCovoiturageLabel = new JLabel();
        moyenneCovoiturageLabel = new JLabel();

        listeLabel = new JLabel[]{lundiCovoiturageLabel, mardiCovoiturageLabel, mercrediCovoiturageLabel, jeudiCovoiturageLabel, vendrediCovoiturageLabel, samediCovoiturageLabel};

        ajouterIndiceCovoiturage(listeLabel);
    }

    private void ajouterIndiceCovoiturage(JLabel[] listeLabel) {
        ListeGrillesCheminement listeGrilleCheminement = mainWindow.controleur.getHoraire().getListeGrillesCheminement();
        float moyenne = 0;

        indiceCovoiturageParJour = new ArrayList<Float>();
        for (int i = 0; i < 6; i++) {
            indiceCovoiturageParJour.add(mainWindow.controleur.getIndiceCovoiturage(listeGrilleCheminement, i+1));
            moyenne += indiceCovoiturageParJour.get(i);
        }
        listeLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, listeLabel, indiceCovoiturageParJour, " %");

        moyenne = moyenne / 6;
        moyenneCovoiturageLabel.setText("Moyenne : " + moyenne + " %");
        
        add(covoiturageTitre);
        for(int j = 0; j < 6; j++)
        {
            add(listeLabel[j]);
        }
        add(moyenneCovoiturageLabel);
    }

    @Override
    public void notifyUpdatedItems() {
        ajouterIndiceCovoiturage(listeLabel);
    }

}
