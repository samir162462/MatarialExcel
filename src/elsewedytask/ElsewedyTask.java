/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask;

import elsewedytask.Modal.Material;
import elsewedytask.Modal.MaterialSet;
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
   static ArrayList<Material> set1 = new ArrayList<>();
   static ArrayList<Material> set2 = new ArrayList<>();
   static ArrayList<Material> set3 = new ArrayList<>();
    public static void main(String[] args) {
        // TODO code application logic here
        MaterialSet materialSet1 = new MaterialSet("set1", set1);
        
        MainView mainView = null;
        java.awt.EventQueue.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }

}
