/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireActiviteControleur;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author tristandhumieres
 */
public class MainWindow extends javax.swing.JFrame {

    public HoraireActiviteControleur controleur;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {

        controleur = new HoraireActiviteControleur();
        initComponents();

        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                setExtendedState(MAXIMIZED_BOTH);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list1 = new java.awt.List();
        mainPanel = new javax.swing.JPanel();
        buttonTopPanel = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT));
        jToggleButton1 = new javax.swing.JToggleButton();
        mainScrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        bottomPanel = new PlanIFTicateur.gui.BottomPanel(this);
        rightPanel = new PlanIFTicateur.gui.RightPanel(this);
        horaireScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        horairePanel = new PlanIFTicateur.gui.HorairePanel(this);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nouveauMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        mainPanel.setLayout(new java.awt.BorderLayout());

        buttonTopPanel.setPreferredSize(new java.awt.Dimension(567, 35));

        jToggleButton1.setText("Validation Automatique");
        buttonTopPanel.add(jToggleButton1);

        mainPanel.add(buttonTopPanel, java.awt.BorderLayout.NORTH);

        panel.setLayout(new java.awt.BorderLayout());

        bottomPanel.setPreferredSize(new java.awt.Dimension(1000, 30));
        panel.add(bottomPanel, java.awt.BorderLayout.SOUTH);

        rightPanel.setPreferredSize(new java.awt.Dimension(300, 244));
        panel.add(rightPanel, java.awt.BorderLayout.EAST);

        horaireScrollPane.setPreferredSize(new java.awt.Dimension(700, 244));

        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout horairePanelLayout = new javax.swing.GroupLayout(horairePanel);
        horairePanel.setLayout(horairePanelLayout);
        horairePanelLayout.setHorizontalGroup(
            horairePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        horairePanelLayout.setVerticalGroup(
            horairePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jPanel1.add(horairePanel, java.awt.BorderLayout.CENTER);

        horaireScrollPane.setViewportView(jPanel1);

        panel.add(horaireScrollPane, java.awt.BorderLayout.CENTER);

        mainScrollPane.setViewportView(panel);

        mainPanel.add(mainScrollPane, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Fichier");

        nouveauMenuItem.setText("Nouveau");
        nouveauMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouveauMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(nouveauMenuItem);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Sauvegarder");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Sauvegarder Sous");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator2);

        jMenuItem4.setText("Quitter");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void nouveauMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nouveauMenuItemActionPerformed
        JFileChooser dialogue = new JFileChooser(new File("/"));
        File fichier;
        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            controleur.importerFichiers(fichier.getAbsolutePath());
        }
    }//GEN-LAST:event_nouveauMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public PlanIFTicateur.gui.BottomPanel bottomPanel;
    private javax.swing.JPanel buttonTopPanel;
    public PlanIFTicateur.gui.HorairePanel horairePanel;
    private javax.swing.JScrollPane horaireScrollPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToggleButton jToggleButton1;
    private java.awt.List list1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JMenuItem nouveauMenuItem;
    private javax.swing.JPanel panel;
    public PlanIFTicateur.gui.RightPanel rightPanel;
    // End of variables declaration//GEN-END:variables



}
