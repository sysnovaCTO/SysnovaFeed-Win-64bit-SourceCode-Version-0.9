/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysnovaFeed;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class Ingredient {
    
    
    
        public Ingredient(){
        }
    
        public void loadIngredientData(ArrayList tableListData,JTable jTable1) {
        try {
            tableListData.clear();
            tableListData=sqlManager.getIngrediantdata();
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                    Vector tableline =(Vector) tableListData.get(i);

                    String cod = (String) tableline.get(0);
                    model1.setValueAt((Object)cod, i, 0);
                    String nam = (String) tableline.get(1);
                    model1.setValueAt((Object)nam, i, 1);
                    String scientifictxt = (String) tableline.get(2);
                    model1.setValueAt((Object)scientifictxt, i, 2);
                    String groupval = (String) tableline.get(3);
                    model1.setValueAt((Object)groupval, i, 3);
                    String typeVal = (String) tableline.get(5);
                    model1.setValueAt((Object)typeVal, i, 4);
                    String updatedby = (String) tableline.get(8);
                    model1.setValueAt((Object)updatedby, i, 5);
                    String updated = (String) tableline.get(9);
                    if(updated.length()>20)
                         updated = updated.substring(0,19);
                    model1.setValueAt((Object)updated, i, 6);
                    Boolean isact = (Boolean) tableline.get(7);
                    model1.setValueAt((Object)isact, i, 7);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
