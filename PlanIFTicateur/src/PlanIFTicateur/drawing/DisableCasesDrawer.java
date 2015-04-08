/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.drawing;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tristandhumieres
 */
public class DisableCasesDrawer {

    private Dimension dimensionsCase;
    private HashMap<Integer, List<Double>> plagesHoraireAGriser;
    private Activite activite;

    public DisableCasesDrawer(Dimension dimensionsCase, HashMap<Integer, List<Double>> plagesHoraireAGriser, Activite activite) {
        this.dimensionsCase = dimensionsCase;
        this.plagesHoraireAGriser = plagesHoraireAGriser;
        this.activite = activite;
    }

    public void disableCases(Graphics g) {
        int largeurCase = dimensionsCase.width;
        int hauteurCase = dimensionsCase.height;
        double heureDebutMinTest = activite.getHeureDebutMin();
        double heureFinMaxTest = activite.getHeureFinMax();
        int caseDebutMin;
        int caseFinMax;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);

        if (!plagesHoraireAGriser.isEmpty()) {
            Iterator<Integer> keySetIterator = plagesHoraireAGriser.keySet().iterator();

            while (keySetIterator.hasNext()) {
                Integer key = keySetIterator.next();
                int jour = key;
                double heureDebutTest = plagesHoraireAGriser.get(key).get(0);
                int caseDebut;
                if ((int) heureDebutTest == heureDebutTest) {
                    caseDebut = ((int) heureDebutTest - 8) * 2 * largeurCase;
                } else {
                    caseDebut = (((int) heureDebutTest - 8) * 2 + 1) * largeurCase;
                }
                double duree = plagesHoraireAGriser.get(key).get(1);

                g2.fillRect(80 + caseDebut, 20 + (jour - 1) * 8 * hauteurCase, (int) duree * 2 * largeurCase, 8 * hauteurCase);
            }
        }

        if ((int) heureDebutMinTest == heureDebutMinTest) {
            caseDebutMin = ((int) heureDebutMinTest - 8) * 2 + 1;
        } else {
            caseDebutMin = ((int) heureDebutMinTest - 8) * 2 + 2;
        }

        if ((int) heureFinMaxTest == heureFinMaxTest) {
            caseFinMax = ((int) heureFinMaxTest - 8) * 2;
        } else {
            caseFinMax = ((int) heureFinMaxTest - 8) * 2 + 1;
        }

        g2.fillRect(80, 20, largeurCase * (caseDebutMin - 1), hauteurCase * 48);
        g2.fillRect(80 + caseFinMax * largeurCase, 20, largeurCase * (28 - caseFinMax), hauteurCase * 48);
    }

}
