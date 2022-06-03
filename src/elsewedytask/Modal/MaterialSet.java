/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask.Modal;

import java.util.ArrayList;

/**
 *
 * @author SAM
 */
public class MaterialSet {

    String listName ; 
    ArrayList<Material> materialsSet ;

    
    public MaterialSet(String listName, ArrayList<Material> materialsSet) {
        this.listName = listName;
        this.materialsSet = materialsSet;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<Material> getMaterialsSet() {
        return materialsSet;
    }

    public void setMaterialsSet(ArrayList<Material> materialsSet) {
        this.materialsSet = materialsSet;
    }
    
    public int getTotalQuantity()
    {
        return this.materialsSet.stream().mapToInt(Material::getQuantity).sum();
    }

    @Override
    public String toString() {
        return "MaterialSet{" + "listName=" + listName + ", materialsSet=" + materialsSet + '}';
    }

    
    
    
}
