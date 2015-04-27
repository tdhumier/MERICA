/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alexandre Rivest
 */
public class NotesPanel extends JPanel implements HoraireControleurObserveur {

    private JTable table;
    private MainWindow mainWindow;
    private DefaultTableModel model;
    private String[] nomColonnes;

    public NotesPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
        mainWindow.controleur.registerObserver(this);
    }
    public void clear(){
        for (int i = 1; i < table.getRowCount(); i++){
            for (int j = 0; j < table.getColumnCount(); j++){
               table.setValueAt(null,i, j);  
            }
        }
    }
    public ArrayList<String> toStringList(){
        ArrayList<String> notes = new ArrayList();
        for (int i = 1; i < table.getRowCount(); i++){
            String ligne = "";
            for (int j = 0; j < table.getColumnCount(); j++){
                ligne += table.getValueAt(i, j) + ";";  
            }
            notes.add(ligne);
        }
        return notes;
    }
    
   public void setNotes(ArrayList<String> notes){
        for (int i = 1; i < notes.size()+1; i++){
            String ligne = notes.get(i-1);
            int positionTokenDebut = 0;
            for (int j = 0; j < 4; j++){
                int positionTokenFin = ligne.indexOf(';', positionTokenDebut);
                String valeur = ligne.substring(positionTokenDebut, positionTokenFin);
                if (valeur.compareTo("null") != 0){
                    table.setValueAt(valeur, i, j);
                } else {
                    table.setValueAt(null, i, j);
                }
                positionTokenDebut = positionTokenFin + 1;
            }
        }
   }
    private void buildUp() {

        nomColonnes = new String[]{"Date", "Cours", "Version", "Description"};
        model = new DefaultTableModel(nomColonnes, 200) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (row == 0) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                } else {
                    return true;
                }
            }

        };
        table = new JTable(model);
        //table = new JTable(200, 4);
        table.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        table.getModel().setValueAt("Date", 0, 0);
        table.getModel().setValueAt("Cours", 0, 1);
        table.getModel().setValueAt("Version", 0, 2);
        table.getModel().setValueAt("Description", 0, 3);

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(280);

        table.setRowHeight(20);

        table.getColumnModel().getColumn(3).setCellRenderer(new TableCellLongTextRenderer());
        ArrayList<String> notes = mainWindow.controleur.getHoraire().getNotes();
        setNotes(notes);
        add(table);

    }

    @Override
    public void notifyUpdatedItems() {
        setNotes(mainWindow.controleur.getHoraire().getNotes());
    }

    private class TableCellLongTextRenderer extends JTextArea implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.setText((String) value);
            this.setWrapStyleWord(true);
            this.setLineWrap(true);

            int nbLigne = 1;
            String cell = "";

            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            setFont(table.getFont());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            if (table.getModel().getValueAt(row, column) != null) {
                cell = table.getModel().getValueAt(row, column).toString();
              
                if (nbLigne * (getPreferredSize().height + 4) < table.getRowHeight(row) && table.getRowHeight(row) > 20) {
                    table.setRowHeight(row, 20);
                    nbLigne = 1;
                }
            }

            if (table.getRowHeight(row) < nbLigne * (getPreferredSize().height - 5) && table.getModel().getValueAt(row, column) != "") {
                table.setRowHeight(row, 20 + table.getRowHeight(row));
                nbLigne++;
            }

            return this;
        }
    }

}
