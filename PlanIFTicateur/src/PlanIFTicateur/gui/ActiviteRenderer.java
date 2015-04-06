/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author tristandhumieres
 */
public class ActiviteRenderer extends JLabel implements ListCellRenderer<Activite> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Activite> list, Activite activite, int index, boolean isSelected, boolean cellHasFocus) {

        setText(activite.getCode());

        return this;
    }
}
