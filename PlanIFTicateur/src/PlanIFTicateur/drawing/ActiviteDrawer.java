/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.drawing;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author tristandhumieres
 */
public class ActiviteDrawer {

    private Activite activite;

    public ActiviteDrawer(Activite activite) {
        this.activite = activite;
    }

    public void drawActivite(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(activite.getCouleur().getRed(), activite.getCouleur().getGreen(), activite.getCouleur().getBlue(), 150));
        
        g2.fill(new Rectangle.Double(activite.getPoint().x, activite.getPoint().y, activite.getWidth(), activite.getHeight()));
        g2.setColor(Color.black);
        g2.drawString(activite.getCode().toUpperCase(), (int) (activite.getPoint().x + (activite.getWidth() / 2 - activite.getWidth() / (activite.getDuree() * 2))), activite.getPoint().y + activite.getHeight() - 4);
        g2.setColor(Color.YELLOW);
        if (activite.isSelected() && activite.getPoint().x != 0) {
            g2.drawRect(activite.getPoint().x, activite.getPoint().y, activite.getWidth(), activite.getHeight());
        }
    }

}
