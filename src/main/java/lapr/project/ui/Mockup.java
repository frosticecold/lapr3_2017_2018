/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

/**
 *
 * @author MarioDias
 */
public class Mockup extends javax.swing.JFrame {

    private static final long serialVersionUID = 1;

    /**
     * Creates new form Mockup
     */
    public Mockup() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btnClose = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        itemProject = new javax.swing.JMenu();
        menuItemCreateProject = new javax.swing.JMenuItem();
        menuItemOpenProject = new javax.swing.JMenuItem();
        itemProjectEditProject = new javax.swing.JMenuItem();
        itemProjectCopyProject = new javax.swing.JMenuItem();
        menuNetworkAnalysis = new javax.swing.JMenu();
        menuItemBestPath = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuResults = new javax.swing.JMenu();
        menuItemSave = new javax.swing.JMenu();
        menuItemExportShow = new javax.swing.JMenu();
        subMenuItemExport = new javax.swing.JMenu();
        subMenuItemExportCSV = new javax.swing.JMenu();
        subItemMenuShow = new javax.swing.JMenu();
        menuItemFile = new javax.swing.JMenu();
        menuItemSaveFile = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();

        jCheckBox1.setText("jCheckBox1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        itemProject.setText("Project");

        menuItemCreateProject.setText("Create new project");
        menuItemCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCreateProjectActionPerformed(evt);
            }
        });
        itemProject.add(menuItemCreateProject);

        menuItemOpenProject.setText("Open project");
        menuItemOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenProjectActionPerformed(evt);
            }
        });
        itemProject.add(menuItemOpenProject);

        itemProjectEditProject.setText("Edit project");
        itemProjectEditProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProjectEditProjectActionPerformed(evt);
            }
        });
        itemProject.add(itemProjectEditProject);

        itemProjectCopyProject.setText("Copy project");
        itemProjectCopyProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProjectCopyProjectActionPerformed(evt);
            }
        });
        itemProject.add(itemProjectCopyProject);

        jMenuBar1.add(itemProject);

        menuNetworkAnalysis.setText("Network Analysis");

        menuItemBestPath.setText("Shortest/Best path");
        menuNetworkAnalysis.add(menuItemBestPath);

        jMenuItem4.setText("Fastest Path");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuNetworkAnalysis.add(jMenuItem4);

        jMenuBar1.add(menuNetworkAnalysis);

        menuResults.setText("Results");

        menuItemSave.setText("Save current results into project");
        menuResults.add(menuItemSave);

        menuItemExportShow.setText("Export/Show");

        subMenuItemExport.setText("Export HTML");
        menuItemExportShow.add(subMenuItemExport);

        subMenuItemExportCSV.setText("Export CSV");
        menuItemExportShow.add(subMenuItemExportCSV);

        subItemMenuShow.setText("Show results");
        menuItemExportShow.add(subItemMenuShow);

        menuResults.add(menuItemExportShow);

        jMenuBar1.add(menuResults);

        menuItemFile.setText("File");

        menuItemSaveFile.setText("Save current project");
        menuItemFile.add(menuItemSaveFile);

        jMenuBar1.add(menuItemFile);

        menuHelp.setText("Help");
        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    
    private void menuItemOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenProjectActionPerformed
        OpenProjectUI ui = new OpenProjectUI(this, rootPaneCheckingEnabled);
        ui.setLocationRelativeTo(this);
        ui.setVisible(true);
    }//GEN-LAST:event_menuItemOpenProjectActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void itemProjectEditProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProjectEditProjectActionPerformed
        new EditProjectUI().setVisible(true);
    }//GEN-LAST:event_itemProjectEditProjectActionPerformed

    private void itemProjectCopyProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProjectCopyProjectActionPerformed
        new CopyProjectUI().setVisible(true);
    }//GEN-LAST:event_itemProjectCopyProjectActionPerformed

    private void menuItemCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCreateProjectActionPerformed
        new CreateProjectUI().setVisible(true);
    }//GEN-LAST:event_menuItemCreateProjectActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new FastestPathUI().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(Mockup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mockup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mockup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mockup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mockup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JMenu itemProject;
    private javax.swing.JMenuItem itemProjectCopyProject;
    private javax.swing.JMenuItem itemProjectEditProject;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuItemBestPath;
    private javax.swing.JMenuItem menuItemCreateProject;
    private javax.swing.JMenu menuItemExportShow;
    private javax.swing.JMenu menuItemFile;
    private javax.swing.JMenuItem menuItemOpenProject;
    private javax.swing.JMenu menuItemSave;
    private javax.swing.JMenuItem menuItemSaveFile;
    private javax.swing.JMenu menuNetworkAnalysis;
    private javax.swing.JMenu menuResults;
    private javax.swing.JMenu subItemMenuShow;
    private javax.swing.JMenu subMenuItemExport;
    private javax.swing.JMenu subMenuItemExportCSV;
    // End of variables declaration//GEN-END:variables
}
