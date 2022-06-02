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

    private final MainView mainView;
    ArrayList<MaterialSet> materialSets ;
    
    public MaterialSetController(MainView mainView) {
        this.mainView = mainView;
    }

    public void handleSelectedRowToSet() {

        try {
            int row = mainView.getJtableExcelFile().getSelectedRow();
            int valueId = (int) mainView.getJtableExcelFile().getModel().getValueAt(row, 0);
            String valueName = mainView.getJtableExcelFile().getModel().getValueAt(row, 1).toString();
            int valueQuantity = (int) mainView.getJtableExcelFile().getModel().getValueAt(row, 2);
            Material material = new Material(valueId, valueName, valueQuantity);
            if(mainView.getjCheckBoxMainSet().isSelected()) setElmentToJlist(mainView.getjListM1(),material.getName()+" - Qun: "+material.getQuantity());
            if(mainView.getjCheckBoxSet2().isSelected()) setElmentToJlist(mainView.getjListM2(),material.getName()+" - Qun: "+material.getQuantity());
            if(mainView.getjCheckBoxSet3().isSelected()) setElmentToJlist(mainView.getjListM3(),material.getName()+" - Qun: "+material.getQuantity());
            System.out.println("getjCheckBoxSet3().isSelected()" + mainView.getjCheckBoxSet3().isSelected());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Row First! ");

        }

    }

    
    private JList<String> setElmentToJlist(JList jList , String elment) {
        try {
         DefaultListModel listModel = (DefaultListModel) jList.getModel();
            if (handleIsRedundant(jList, elment)) listModel.addElement(elment);
            jList.setModel(listModel);
            return jList;
        } catch (Exception e) {
             DefaultListModel listModel = new DefaultListModel();
            listModel.addElement(elment);
            jList.setModel(listModel);
            return jList;
        }
    }
    
    
    private boolean handleIsRedundant(JList jlist, String element)
    {
        for (int i = 0; i < jlist.getModel().getSize(); i++) {
            String ele = (String) jlist.getModel().getElementAt(i);
            if (ele.equals(element)) return false;
        }
        return true;
    }

}
