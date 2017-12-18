package lapr.project.ui;

import lapr.project.utils.Session;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session s = new Session();
        /*Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mockup(s).setVisible(true);
            }
        });
    }

}
