/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elsewedytask.Controller;

import elsewedytask.Modal.Material;
import elsewedytask.Modal.MaterialSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author SAM
 */
public class DatabaseController {

    static public String addMaterialSet(MaterialSet materialSet) {
        if (materialSet.getTotalQuantity() == 0) {
            return "Empty List";
        }
        try {

            Connection conn = DBConnection.getConnectionPostgres();
            //insert into main class
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO \"elswedyTask\".\"MaterialSet\" ( \"ms_setName\", ms_total) \n"
                    + "	VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, materialSet.getListName());
            stmt.setInt(2, materialSet.getTotalQuantity());

            int i = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            long key = 0;
            if (rs.next()) {
                key = rs.getLong(1);
            }

            System.out.println(i + " records inserted MAIN - > Key : " + key);

            //Insert Data to subClass
            StringBuilder sb = new StringBuilder("INSERT INTO \"elswedyTask\".\"Material\" ( m_name, m_quantity, ms_id) VALUES  ");
            for (int j = 0; j < materialSet.getMaterialsSet().size(); j++) {
                Material mat = materialSet.getMaterialsSet().get(j);
                sb.append("('").append(mat.getName()).append("', ").append(mat.getQuantity()).append(" ,").append(key).append(" )");
                if (j != materialSet.getMaterialsSet().size() - 1) {
                    sb.append(" , ");
                }
            }
            stmt = conn.prepareStatement(sb.toString());

            System.out.println("" + sb);
            int i1 = stmt.executeUpdate();
            System.out.println(i + " records inserted SubMain - > Key : " + key);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("err - " + e.getMessage());

            return "$ the Sets not added By error!";

        }
        JOptionPane.showMessageDialog(null, materialSet.getListName()+" Saved Successfully! ");

        return "* the Sets added! ";
    }

    public static ArrayList<MaterialSet> getMainMatarialSet(String setName) {
        try {
            Connection conn = DBConnection.getConnectionPostgres();

            String query = "SELECT ms_id, \"ms_setName\", ms_total\n"
                    + "	FROM \"elswedyTask\".\"MaterialSet\";";

            // create the java statement
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                // print the results
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("err in get user img : " + e.getMessage());

        }
        return null;
    }

}
