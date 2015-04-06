/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.Color;
import java.awt.Component;
import java.text.Normalizer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author tristandhumieres
 */
public class ActiviteRenderer extends JLabel implements ListCellRenderer<Activite> {

    public ActiviteRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Activite> list, Activite activite, int index, boolean isSelected, boolean cellHasFocus) {

        String texteActivite = "";
        activite.setCode(activite.getCode().toUpperCase());
        texteActivite += activite.getCode() + "   " + activite.getTitre() + "   (" + activite.getType() + ")";
        texteActivite = Normalizer.normalize(texteActivite, Normalizer.Form.NFD);
        setText(texteActivite);
        setBackground(activite.getCouleur());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(activite.getCouleur());
            setForeground(Color.BLACK);

        }

        return this;
    }

}
