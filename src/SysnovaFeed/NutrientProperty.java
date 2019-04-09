/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysnovaFeed;

import java.awt.Toolkit;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class NutrientProperty {
    
    public NutrientProperty(ArrayList tableListData,JTable jTable1,boolean Saveremaining,JButton saveallbtn
            ,JButton editBtn,JButton  addnewbtn,JButton  addListBtn,JButton Deletebtn,JTextField NuPropertyNameTextField
            ,JCheckBox isActiveCheckBox,JTextField createdOnTextField,JTextField updatedOnTextField
            ,JTextField CreatedByTextField,JTextField UpdatedByTextField){
        
        
        Saveremaining=false;
        saveallbtn.setEnabled(false);
        tableListData.clear();
//        userNameLabel.setText("");
//        userNameLabel.setText("Hello, "+sqlManager.getUserName());
        loadNuPropertyData(tableListData,jTable1);
        NuPropertyNameTextField.setText("");
        isActiveCheckBox.setSelected(true);
        editBtn.setEnabled(false);
        addnewbtn.setEnabled(false);
        addListBtn.setEnabled(true);
        Deletebtn.setEnabled(false);
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
    }
    
        public void loadNuPropertyData(ArrayList tableListData,JTable jTable1) {
        
        try {
            tableListData=sqlManager.getNuPropertydata();
            
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                    Vector tableline =(Vector) tableListData.get(i);
                    String name = (String) tableline.get(0);
                    model1.setValueAt((Object)name, i, 0);
                    Boolean isact = (Boolean) tableline.get(1);
                    model1.setValueAt((Object)isact, i, 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
public void OnAddButtonActionPerformed(JTextField NuPropertyNameTextField,JCheckBox isActiveCheckBox
             ,ArrayList tableListData,int editProperty_ID,int insertID,int status_ID,JTable jTable1,boolean Saveremaining,JButton saveallbtn
            ,JButton editBtn,JButton  addnewbtn,JButton  addListBtn,JButton Deletebtn,int editnow_ID){
         
               
       
        String nu_propertyname=NuPropertyNameTextField.getText();
        if(nu_propertyname.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Nutrient Name", "Nutrient Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }      
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        for(int i=0;i<tableListData.size();i++)
        {
            Vector tableline =(Vector) tableListData.get(i);
            int NutrientProperty_ID=(int) tableline.get(2);
            if(editProperty_ID==NutrientProperty_ID) continue;
            
            String name = (String) tableline.get(0);
            if(nu_propertyname.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Nutrient Name Already Exists", "Nutrient Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            } 

        }
        if(editProperty_ID<1){
            Vector line = new Vector();
            line.add(nu_propertyname); //0         
            line.add(isActiveCheck);//1
            line.add(insertID);//2 ======== NutrientProperty_ID previous
            line.add(status_ID);//3========1 newadd 2update 0 for exits 
            line.add(""); //4
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            tableListData.add(line);
            insertID++;
        }
        else{
            for(int i=0;i<tableListData.size();i++)
            {
                Vector line =(Vector) tableListData.get(i);
                int NuProperty_ID=(int) line.get(2);
                if(editProperty_ID==NuProperty_ID){
                    status_ID=2;
                    line.set(0, nu_propertyname);
                    line.set(1,isActiveCheck);//2
                    line.set(2,editProperty_ID);//3 ======== NutrientProperty_ID previous
                    line.set(3,status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        model1.setRowCount(tableListData.size());
        for(int i=0;i<tableListData.size();i++)
        {
                Vector tableline =(Vector) tableListData.get(i);               
                String name = (String) tableline.get(0);
                model1.setValueAt((Object)name, i, 0);
                Boolean isact = (Boolean) tableline.get(1);
                model1.setValueAt((Object)isact, i, 1);
                
        }

        NuPropertyNameTextField.setText("");
        NuPropertyNameTextField.setEditable(true);
        isActiveCheckBox.setSelected(true);       
        editBtn.setEnabled(true);
        addnewbtn.setEnabled(true);
        addListBtn.setEnabled(true);
        Deletebtn.setEnabled(false);
        editProperty_ID=-1;
        status_ID=-1;
        editnow_ID=0;
        saveallbtn.setEnabled(true);
        Saveremaining=true;
         
         
     }

public void OnSaveAllButtonActionPerformed(ArrayList tableListData,JTable jTable1 ,JButton saveallbtn,boolean Saveremaining)
{
    
            boolean isSuccess;
        try {
            isSuccess = sqlManager.saveNuPropertydata(tableListData);
            if(isSuccess)
        {
            JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
            loadNuPropertyData(tableListData,jTable1);
            saveallbtn.setEnabled(false);
            Saveremaining=false;
        }
        } catch (SQLException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }


public void OnDeleteButtonActionPerformed(ArrayList tableListData ,JTable jTable1){
    
    
               int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
               if(dialogDelete==JOptionPane.YES_OPTION){     
                
                DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
                int row = jTable1.getSelectedRow();
                Vector tableline =(Vector) tableListData.get(row);
                int editproperty_ID=(int) tableline.get(2);//3
                     
             boolean isSuccess;
      try {
          isSuccess = sqlManager.deleteNuPropertyGroupdata(editproperty_ID);
          if(isSuccess){ 

                JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                loadNuPropertyData(tableListData,jTable1);
             //Saveremaining = false;
                         }
             } catch (SQLException ex) {
          Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
      catch (UnknownHostException ex) {
          Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
                       }
        } 
    
}

    public void TableMouseorKeyChange(int row,JTextField createdOnTextField,JTextField updatedOnTextField
            ,JTextField CreatedByTextField,JTextField UpdatedByTextField,ArrayList tableListData,JTextField NuPropertyNameTextField
            ,JCheckBox isActiveCheckBox,JButton editBtn,JButton  addnewbtn,JButton  addListBtn,JButton Deletebtn
            ,int editProperty_ID,int status_ID ){
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
        
      
       Vector tableline =(Vector) tableListData.get(row);
               
        String propertyname = (String) tableline.get(0);
        Boolean isact = (Boolean) tableline.get(1);
        String createdOn = (String) tableline.get(4);
     
        String createdby = (String) tableline.get(6);
        createdOnTextField.setText(""+createdOn);
        updatedOnTextField.setText((String) tableline.get(5));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(7);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        CreatedByTextField.setText(""+createdby);
        UpdatedByTextField.setText(""+updatedby);
        NuPropertyNameTextField.setEditable(false);
        isActiveCheckBox.setEnabled(false);

        NuPropertyNameTextField.setText(""+propertyname);
        isActiveCheckBox.setSelected(isact);
        
        editProperty_ID=(int) tableline.get(2);
        status_ID=(int) tableline.get(3);
        editBtn.setEnabled(true);
        addnewbtn.setEnabled(true);
        addListBtn.setEnabled(true);
        Deletebtn.setEnabled(true);
        
    }
    
}
