/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask.Controller;

import elsewedytask.Modal.Material;
import elsewedytask.View.MainView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author SAM
 */
public class ImportFileController {

    private ArrayList<Material> materialsArrayList;
    private final MainView mainView;

    public ArrayList<Material> getMaterials() {
        return materialsArrayList;
    }

    public ImportFileController(MainView mainView) {
        this.mainView = mainView;
    }

    public void getExcelFromFile() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "C:\\Users\\SAM\\Desktop";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        DataFormatter fmt = new DataFormatter();

        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            materialsArrayList = new ArrayList<>();
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    int excelId = Integer.valueOf(fmt.formatCellValue(excelRow.getCell(0))) ;
                    String excelName = fmt.formatCellValue(excelRow.getCell(1)) ;
                    int excelQuantity = Integer.valueOf(fmt.formatCellValue(excelRow.getCell(2))) ;
                    Material material = new Material( excelId, excelName,excelQuantity );
                    if (!handleIsRedundant(material)) {
                     this.materialsArrayList.add(material);

                    }
                    System.out.println("name : "+excelName);
                }
                handleExcelFileToJtable();
                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }

    private void handleExcelFileToJtable() {
        JTable jTableExcel = this.mainView.getJtableExcelFile();
        String col[] = {"ID","Name","Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(col,0);
        for (int i = 0; i < this.materialsArrayList.size(); i++) {
            int id = materialsArrayList.get(i).getId();
            String name = materialsArrayList.get(i).getName();
            int quantity = materialsArrayList.get(i).getQuantity();
            Object[] data = {id, name, quantity};
            tableModel.addRow(data);
        }
        jTableExcel.setModel(tableModel);
        this.mainView.setJtableExcelFile(jTableExcel);
    }
    
    private boolean handleIsRedundant(Material material)
    {
        for (int i = 0; i < materialsArrayList.size(); i++) {
            Material mat = materialsArrayList.get(i);
            if (mat.getName().equals(material.getName())) {
                int total = 0 ; 
                total = mat.getQuantity() + material.getQuantity();
                material.setQuantity(total);
                materialsArrayList.set(i,material);
                return true;
            } 
        }
        
        return false;
    }

}
