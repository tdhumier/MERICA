/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import java.awt.Dimension;

/**
 *
 * @author tristandhumieres
 */
public class MousePositionHelper {

    private Dimension dimensionsCase;

    public MousePositionHelper(Dimension dimensionsCase) {
        this.dimensionsCase = dimensionsCase;
    }

    //Obtient l'heure à partir d'une abscisse sur le panel HorairePanel
    public double getHeure(int x) {
        int xo = 80;//Prise en compte de la marge (toujours la même quelque soit la résolution de l'écran)
        int xCase = (x - xo) / dimensionsCase.width + 1;
        double heureCase = ((xCase - 1) / 2) + 8;
        if (x < xo || x > (dimensionsCase.width * 28 + 80)) {
            return 0.0d;
        } else {
            if (xCase % 2 == 0) {
                double heureCaseBefore = heureCase;
                return heureCaseBefore + 0.5;
            } else {
                return heureCase;
            }
        }
    }

    //Obtient le jour à partir d'une ordonnée sur la panel HorairePanel
    public int getJour(int y) {
        int yo = 20;//Prise en compte de la marge (toujours la même quelque soit la résolution de l'écran)
        int yCase = (y - yo) / dimensionsCase.height + 1;
        if ((yCase - 1) / 8 == 0) {
            return 1;
        }
        if ((yCase - 1) / 8 == 1) {
            return 2;
        }
        if ((yCase - 1) / 8 == 2) {
            return 3;
        }
        if ((yCase - 1) / 8 == 3) {
            return 4;
        }
        if ((yCase - 1) / 8 == 4) {
            return 5;
        }
        if ((yCase - 1) / 8 == 5) {
            return 6;
        }
        return 0;
    }

    //Retourne l'abscisse exacte pour l'heure la plus proche en fonction d'une abscisse x sur la grille
    public int getXPosition(int x) {
        int xo = 80;
        int xCase = (x - xo) / dimensionsCase.width + 1;
        return ((xCase - 1) * dimensionsCase.width + xo);
    }

    //Retourne l'ordonnée exacte pour l'heure la plus proche en fonction d'une ordonnée y sur la grille
    public int getYPosition(int y) {
        int yo = 20;
        int yCase = (y - yo) / dimensionsCase.height + 1;
        return ((yCase - 1) * dimensionsCase.height + yo);
    }

}
