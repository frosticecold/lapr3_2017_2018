/*
 */
package lapr.project.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import lapr.project.controller.OpenProjectController;

/**
 *
 * @author pc asus
 */
public class OpenProjectUI extends javax.swing.JDialog {

    private static final long serialVersionUID = 1;

    private final OpenProjectController controller;
    private DefaultComboBoxModel<String> projectComboBox = new DefaultComboBoxModel<>();

    /**
     * Creates new form OpenProjectUI
     *
     * @param parent
     * @param modal
     */
    public OpenProjectUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        controller = new OpenProjectController();

        List<String> projectsList = new ArrayList<>();
        try {
            projectsList = controller.getProjects();
        } catch (SQLException ex) {
            Logger.getLogger(OpenProjectUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Oops, something went wrong!\n\nVerify the project is functional.", "Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        for (String p : projectsList) {
            projectComboBox.addElement(p);
        }
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

        jLabel1 = new javax.swing.JLabel();
        jcb_projects = new javax.swing.JComboBox<>();
        jb_save = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Project");

        jLabel1.setText("Projects:");

        jcb_projects.setModel(projectComboBox);

        jb_save.setText("Save");
        jb_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_saveActionPerformed(evt);
            }
        });

        jb_cancel.setText("Cancel");
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcb_projects, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addComponent(jb_cancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_save)
                        .addGap(109, 109, 109)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcb_projects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_save)
                    .addComponent(jb_cancel))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jb_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_saveActionPerformed
        try {
            String proj = projectComboBox.getSelectedItem().toString();
            controller.setActiveProject(proj);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(OpenProjectUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Couldn't load the selected project.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_saveActionPerformed

    private void jb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jb_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JButton jb_save;
    private javax.swing.JComboBox<String> jcb_projects;
    // End of variables declaration//GEN-END:variables
}
