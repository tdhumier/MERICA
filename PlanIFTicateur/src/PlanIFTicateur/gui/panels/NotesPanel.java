/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;


import java.awt.Component;
import java.awt.Font;

import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alexandre Rivest
 */
public class NotesPanel extends JPanel
{
    private JTable table;
    private RightPanel rightPanel;
    private DefaultTableModel model;
    private String[] nomColonnes;
    
    public NotesPanel(RightPanel rightPanel)
    {
        this.rightPanel = rightPanel;
    
        buildUp();
    }
    
    private void buildUp()
    {
        
        nomColonnes = new String[] {"Date", "Cours", "Version", "Description"};
       model = new DefaultTableModel(nomColonnes,200);
        table = new JTable(model);
        //able = new JTable(200, 4);
        table.setFont(new Font( "TimesRoman", Font.PLAIN, 16));

     /*   table.getModel().setValueAt("Date", 0, 0);
        table.getModel().setValueAt("Cours", 1, 0);
        table.getModel().setValueAt("Version", 2, 0);
        table.getModel().setValueAt("Description", 3, 0);*/
        
        
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
     
      

        table.setRowHeight(20);
        
        table.getColumnModel().getColumn(3).setCellRenderer(new TableCellLongTextRenderer());
        //table.getColumnModel().getColumn(1).setCel
        
        
        
        add(table);
    }
    
    
private class TableCellLongTextRenderer extends JTextArea implements TableCellRenderer{  
  
    @Override  
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
        this.setText((String)value);  
        this.setWrapStyleWord(true);                      
        this.setLineWrap(true);  
        
        int nbLigne = 1;
        String cell = "";
        
            setSize(table.getColumnModel().getColumn(column).getWidth(),getPreferredSize().height); 
        
        if(table.getModel().getValueAt(row, column) != null)
        {
            cell =  table.getModel().getValueAt(row, column).toString();
            System.out.println(table.getSelectedRow() + " " + table.getRowHeight(row));
            
            if(nbLigne * (getPreferredSize().height + 4) < table.getRowHeight(row) && table.getRowHeight(row) > 20)
             {  
                 table.setRowHeight(row, 20); 
                 nbLigne = 1;
             }
        }
        
 
        if (table.getRowHeight(row) < nbLigne * (getPreferredSize().height + 4) && table.getModel().getValueAt(row, column) != "")
        {  
            table.setRowHeight(row, 20 + table.getRowHeight(row)); 
            nbLigne++;
        }  
        return this;  
    }  
}

    
}
