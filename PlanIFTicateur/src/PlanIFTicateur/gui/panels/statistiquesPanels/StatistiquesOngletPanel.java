/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquesPanels;

import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tristandhumieres
 */
public class StatistiquesOngletPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JPanel statPanel;

    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;
    private JComboBox comboBox;

    private String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

    public StatistiquesOngletPanel() {
    }

    public StatistiquesOngletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {

        setLayout(new BorderLayout());

        comboBox = new JComboBox();

        lundiLabel = new JLabel("");
        mardiLabel = new JLabel("");
        mercrediLabel = new JLabel("");
        jeudiLabel = new JLabel("");
        vendrediLabel = new JLabel("");
        samediLabel = new JLabel("");
        statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(0, 1));

        comboBox.setBackground(Color.WHITE);
        comboBox.addItem("Cours par jour");
        comboBox.addItem("Indice de congestion");
        comboBox.addItem("Nombre maximal de cours");
        comboBox.addItem("Nombre moyen de cours");
        comboBox.addItem("Indice de covoiturage");
        add(comboBox, BorderLayout.NORTH);

        statPanel.add(lundiLabel);
        statPanel.add(mardiLabel);
        statPanel.add(mercrediLabel);
        statPanel.add(jeudiLabel);
        statPanel.add(vendrediLabel);
        statPanel.add(samediLabel);

        add(statPanel, BorderLayout.CENTER);

        comboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                statistiqueActionPerformed(evt);

            }
        });
    }

    private void statistiqueActionPerformed(java.awt.event.ActionEvent evt) {
        mainWindow.controleur.notifyObserversForUpdatedItems();
    }

    public void updateOnglet() {
        JLabel[] labelModes = {lundiLabel, mardiLabel, mercrediLabel, jeudiLabel, vendrediLabel, samediLabel};
        ListeGrillesCheminement listeGrilleCheminement = mainWindow.controleur.getHoraire().getListeGrillesCheminement();

        if (comboBox.getSelectedItem() == "Cours par jour") {
            ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
            mainWindow.controleur.getHoraire().getStatistiques().modificationIntegerLabel(jourModes, labelModes, nbCoursParJour, " Cours");
            repaint();
        } else if (comboBox.getSelectedItem() == "Indice de congestion") {
            ArrayList<Float> indiceCongestionParJour = new ArrayList<Float>();
            for (int i = 1; i < 7; i++) {
                indiceCongestionParJour.add(mainWindow.controleur.getIndiceCongestion(i));
            }

            mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, labelModes, indiceCongestionParJour, " %");
            repaint();
        } else if (comboBox.getSelectedItem() == "Nombre maximal de cours") {
            ArrayList<Integer> nbMaxCoursParJour = new ArrayList<Integer>();

            for (int i = 1; i < 7; i++) {
                nbMaxCoursParJour.add(mainWindow.controleur.getNbMaxCoursParJour(listeGrilleCheminement, i));
            }

            mainWindow.controleur.getHoraire().getStatistiques().modificationIntegerLabel(jourModes, labelModes, nbMaxCoursParJour, " Cours");
            repaint();
        } else if (comboBox.getSelectedItem() == "Nombre moyen de cours") {
            ArrayList<Float> nbMoyenCoursParJour = new ArrayList<Float>();

            for (int i = 1; i < 7; i++) {
                nbMoyenCoursParJour.add(mainWindow.controleur.getNbMoyenCoursParJour(listeGrilleCheminement, i));
            }

            mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, labelModes, nbMoyenCoursParJour, " Cours");
            repaint();
        } 
        else if (comboBox.getSelectedItem() == "Indice de covoiturage") {
            ArrayList<Float> indiceCovoiturage = new ArrayList<Float>();
            float test;
            for (int i = 1; i < 7; i++) {
                test = mainWindow.controleur.getIndiceCovoiturage(listeGrilleCheminement, i);
                indiceCovoiturage.add(test);
            }
            mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, labelModes, indiceCovoiturage, " %");

            repaint();
        }
    }

    @Override
    public void notifyUpdatedItems() {
        updateOnglet();
    }

}
