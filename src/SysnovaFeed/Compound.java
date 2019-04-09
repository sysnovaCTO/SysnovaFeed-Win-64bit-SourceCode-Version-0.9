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
public class Compound {
    
    
        public void loadCompoundData(ArrayList tableListData,JTable jTable1) {
        try {
            tableListData.clear();
            tableListData=sqlManager.getCompoundList();
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {                    
                
                    Vector tableline =(Vector) tableListData.get(i);
                    
                    String cod = (String) tableline.get(0);
                    model1.setValueAt((Object)cod, i, 0);
                    
                    String name = (String) tableline.get(1);
                    model1.setValueAt((Object)name, i, 1);
                    
                    String group = (String) tableline.get(8);
                    model1.setValueAt((Object)group, i, 2);
                    
                    //String type = (String) tableline.get(6);
                    //model1.setValueAt((Object)type, i, 3);
                    String type = (String) tableline.get(17);
                    model1.setValueAt((Object)type, i, 3);
                    
                    String updatedby = (String) tableline.get(9);
                    model1.setValueAt((Object)updatedby, i, 4);
                    String updated = (String) tableline.get(10);
                    if(updated.length()>20)
                         updated = updated.substring(0,19);
                    model1.setValueAt((Object)updated, i, 5);
                    Boolean isact = (Boolean) tableline.get(3);
                    model1.setValueAt((Object)isact, i, 6);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
