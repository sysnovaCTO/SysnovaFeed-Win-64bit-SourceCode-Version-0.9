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
public class Compoundgroup {
    
    
    public Compoundgroup(boolean Saveremaining,ArrayList tableListData,JTable jTable1,JTextField nameTextField
                        ,JCheckBox isActiveCheckBox,JButton addListBtn,JTextField createdOnTextField
                        ,JTextField updatedOnTextField,JTextField CreatedByTextField, JTextField UpdatedByTextField){
        
        Saveremaining = false;
        //saveallbtn.setEnabled(false);
        tableListData.clear();
//        userNameLabel.setText("");
//        userNameLabel.setText("Hello, "+sqlManager.getUserName());
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        loadCompoundgroupData(tableListData,jTable1);
        //codeTextField.setText("");
        nameTextField.setText("");
        
        isActiveCheckBox.setSelected(true);
        
        //editBtn.setEnabled(false);
        //addnewbtn.setEnabled(false);
        addListBtn.setEnabled(true);
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
        
    }
    
    
    
        public void loadCompoundgroupData(ArrayList tableListData,JTable  jTable1) {
        
        try {
            tableListData=sqlManager.getCompoundgroupdata();
            
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                    Vector tableline =(Vector) tableListData.get(i);
                    String nam = (String) tableline.get(0);//1
                    model1.setValueAt((Object)nam, i, 0);//1
                    
                    Boolean isact = (Boolean) tableline.get(1);//2
                    model1.setValueAt((Object)isact, i, 1);//2

            }
        } catch (SQLException ex) {
           
        } catch (UnknownHostException ex) {
            
        }
    }
        
        
        public void OnAddLListButtonActionPerformed(JTextField nameTextField,JCheckBox isActiveCheckBox,ArrayList tableListData
                                      ,int editCompoundgroup_ID,int insertID,int status_ID
                                      ,JTable jTable1,JButton addListBtn,JButton deletebtn
                                      ,JButton saveallbtn,int editnow_ID,boolean Saveremaining){
            
                
         String name=nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        boolean isActiveCheck=isActiveCheckBox.isSelected();

        for(int i=0;i<tableListData.size();i++)
        {
            Vector tableline =(Vector) tableListData.get(i);

            int Compoundgroup_ID=(int) tableline.get(2);
            if(editCompoundgroup_ID==Compoundgroup_ID) continue;
 
            String nam = (String) tableline.get(0);//1
            if(nam.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            
        }
        if(editCompoundgroup_ID<1){
            Vector line = new Vector();
          
            line.add(name);//1//0
            
            line.add(isActiveCheck);//2//1
          
       
            //11
            line.add(insertID);//3 ======== PLANT_ID previous
            line.add(status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            tableListData.add(line);
           insertID++ ;
        }
        else{
            for(int i=0;i<tableListData.size();i++)
            {
                Vector line =(Vector) tableListData.get(i);
                int Compoundgroup_ID=(int) line.get(2);
              
                if(editCompoundgroup_ID==Compoundgroup_ID){
                    status_ID=2;
                   // line.set(0, symbol);
                    line.set(0,name);//1
                   
                    line.set(1,isActiveCheck);//2
                    
                    line.set(2,editCompoundgroup_ID);//3 ======== Plant_ID previous
                    line.set(3,status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        final    DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        model1.setRowCount(tableListData.size());
        for(int i=0;i<tableListData.size();i++)
        {
                Vector tableline =(Vector) tableListData.get(i);
               

                String nam = (String) tableline.get(0);//1
                model1.setValueAt((Object)nam, i, 0);//1
               
                Boolean isact = (Boolean) tableline.get(1);//2
                model1.setValueAt((Object)isact, i, 1);//2
                
        }

        nameTextField.setText("");
        nameTextField.setEnabled(true);
        
        isActiveCheckBox.setSelected(true);
        addListBtn.setEnabled(false);
        deletebtn.setEnabled(true);
        editCompoundgroup_ID=-1;
        status_ID=-1;
        editnow_ID=0;
        saveallbtn.setEnabled(true);
        Saveremaining = true;
          
        }
        
        
        public void OnSaveAllButtonActionPerformed(ArrayList tableListData,JTable jTable1,boolean Saveremaining){
        
            
                    try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveCompoundgroupData(tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                loadCompoundgroupData(tableListData,jTable1);
               // saveallbtn.setEnabled(false);
             Saveremaining = false;
            }
        } catch (SQLException ex) {
        } catch (UnknownHostException ex) {
        }
        }
        
        
        
        public void OnDeleteButtonActionPerformed(ArrayList tableListData,JTable jTable1,JButton deletebtn,
                        JButton editBtn,JButton addnewbtn){

                
               
            int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
            if(dialogDelete==JOptionPane.YES_OPTION){     
                         DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
                         int row = jTable1.getSelectedRow();
                         Vector tableline =(Vector) tableListData.get(row);

                              int Compoundtype_ID=(int) tableline.get(2);//3

                      boolean isSuccess;
               try {
                   isSuccess = sqlManager.deleteCompoundgroupdata(Compoundtype_ID);
                   if(isSuccess){ 
                        JOptionPane.showMessageDialog(null, "Delete Successfully from h2 Database Server", "Delete Successfully from h2 Database Server", JOptionPane.INFORMATION_MESSAGE);
                     loadCompoundgroupData(tableListData,jTable1);
                                  }
                      } 
               catch (SQLException ex) {
                                                 } 
               catch (UnknownHostException ex) {
                                                 }
                                                       }

            deletebtn.setEnabled(false);
            editBtn.setEnabled(false);
            addnewbtn.setEnabled(true);
    
        }
        
        
         public void TableMouseorKeyChange(int row,JTextField createdOnTextField
                        ,JTextField updatedOnTextField,JTextField CreatedByTextField, JTextField UpdatedByTextField
                        ,ArrayList tableListData,JTable jTable1,JTextField nameTextField
                        ,JCheckBox isActiveCheckBox,int editCompoundgroup_ID,int status_ID
                        ,JButton addListBtn,JButton deletebtn,JButton editBtn,JButton addnewbtn){
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
        
      
       Vector tableline =(Vector) tableListData.get(row);
               
      
        String nam = (String) tableline.get(0);//1
  
        Boolean isact = (Boolean) tableline.get(1);//2
        String createdOn = (String) tableline.get(6);//7
     
        String createdby = (String) tableline.get(4);//5
        createdOnTextField.setText(""+createdOn);//
        updatedOnTextField.setText((String) tableline.get(7));//8
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);//
        String updatedby =(String) tableline.get(5);//6
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);//
        CreatedByTextField.setText(""+createdby);
        UpdatedByTextField.setText(updatedby);
        
        nameTextField.setEditable(false);
       
        isActiveCheckBox.setEnabled(false);
        
        
        nameTextField.setText(""+nam);
     
        isActiveCheckBox.setSelected(isact);
        
   
        editCompoundgroup_ID=(int) tableline.get(2);//3
        status_ID=(int) tableline.get(3);//4
        editBtn.setEnabled(true);
        addnewbtn.setEnabled(true);
        addListBtn.setEnabled(false);
        deletebtn.setEnabled(true);
        
    }
         
         
}
