/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask;

import elsewedytask.Modal.Material;
import elsewedytask.View.MainView;
import java.util.ArrayList;

/**
 *
 * @author SAM
 */
public class ElsewedyTask {

    /**
     * @param args the command line arguments
     */
    ArrayList<Material> materials = new ArrayList<>();
    public static void main(String[] args) {
        // TODO code application logic here

        MainView mainView = null;
        java.awt.EventQueue.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }

}
