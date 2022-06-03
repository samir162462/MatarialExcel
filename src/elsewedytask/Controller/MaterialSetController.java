/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask.Controller;

import elsewedytask.Modal.Material;
import elsewedytask.Modal.MaterialSet;
import elsewedytask.View.MainView;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author SAM
 */
public class MaterialSetController {

    //Singleton design pattern
    private static MaterialSetController materialSetController;
    ArrayList<MaterialSet> arrayListMaterialSets;

    private MaterialSetController() {
        MaterialSet ms1 = new MaterialSet("set1", new ArrayList<>());
        MaterialSet ms2 = new MaterialSet("set2", new ArrayList<>());
        MaterialSet ms3 = new MaterialSet("set3", new ArrayList<>());
        this.arrayListMaterialSets = new ArrayList<>(); // for three static sets
        this.arrayListMaterialSets.add(ms1);
        this.arrayListMaterialSets.add(ms2);
        this.arrayListMaterialSets.add(ms3);

    }

    public static MaterialSetController getInstance() {
        if (materialSetController == null) {
            materialSetController = new MaterialSetController();
        }
        return materialSetController;
    }

    public void handleSelectedRowToSet(MainView mainView) {
        try {
            int row = mainView.getJtableExcelFile().getSelectedRow();
            int valueId = (int) mainView.getJtableExcelFile().getModel().getValueAt(row, 0);
            String valueName = mainView.getJtableExcelFile().getModel().getValueAt(row, 1).toString();
            int valueQuantity = (int) mainView.getJtableExcelFile().getModel().getValueAt(row, 2);
            Material material = new Material(valueId, valueName, valueQuantity);
            if (mainView.getjCheckBoxMainSet().isSelected()) {
                setElmentToJlist(mainView.getjListM1(), material, 0);
            }
            if (mainView.getjCheckBoxSet2().isSelected()) {
                setElmentToJlist(mainView.getjListM2(), material, 1);
            }
            if (mainView.getjCheckBoxSet3().isSelected()) {
                setElmentToJlist(mainView.getjListM3(), material, 2);
            }
            System.out.println("getjCheckBoxSet3().isSelected()" + mainView.getjCheckBoxSet3().isSelected());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Row First! ");
        }
    }

    private JList<String> setElmentToJlist(JList jList, Material material, int jSetIndex) {
        String elment = material.getName() + " - Qun: " + material.getQuantity();
        try {
            DefaultListModel listModel = (DefaultListModel) jList.getModel();
            if (handleIsRedundant(jList, elment)) {
                listModel.addElement(elment);  // add -> to jList 
                arrayListMaterialSets.get(jSetIndex).getMaterialsSet().add(material);

            }
            jList.setModel(listModel);
            return jList;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            DefaultListModel listModel = new DefaultListModel();
            listModel.addElement(elment);
            arrayListMaterialSets.get(jSetIndex).getMaterialsSet().add(material);

            jList.setModel(listModel);
            return jList;
        }
    }

    public JList<String> removeElmentFromJlistModal(JList jList) {

        DefaultListModel listModel = (DefaultListModel) jList.getModel();
        removeSelectedFromJlist(jList, listModel);
        jList.setModel(listModel);
        return jList;

    }

    private DefaultListModel removeSelectedFromJlist(JList jlist, DefaultListModel model) {// remove jlist elment
        int index = jlist.getSelectedIndex();
        if (index >= 0) { //Remove only if a particular item is selected
            model.removeElementAt(index);
        }
        return model;
    }

    private boolean handleIsRedundant(JList jlist, String element) {
        for (int i = 0; i < jlist.getModel().getSize(); i++) {
            String ele = (String) jlist.getModel().getElementAt(i);
            if (ele.equals(element)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<MaterialSet> getArrayListMaterialSets() {
        return arrayListMaterialSets;
    }

}
