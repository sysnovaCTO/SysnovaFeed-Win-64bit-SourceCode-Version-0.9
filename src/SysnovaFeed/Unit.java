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
import sun.security.krb5.KrbAsReqBuilder;

/**
 *
 * @author root
 */
public class Unit {
    
    
    public Unit(boolean Saveremaining,ArrayList tableListData,JTextField codeTextField,JTextField nameTextField
            ,JCheckBox isActiveCheckBox,JButton addListBtn,JTextField createdOnTextField,JTextField updatedOnTextField
            ,JTextField CreatedByTextField,JTextField UpdatedByTextField,JTable jTable1){
        
        Saveremaining = false;
        tableListData.clear();
//        userNameLabel.setText("");
//        userNameLabel.setText("Hello, "+sqlManager.getUserName());
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        loadUnitData(tableListData,jTable1);
        codeTextField.setText("");
        nameTextField.setText("");
        isActiveCheckBox.setSelected(true);
        addListBtn.setEnabled(true);
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
    }
    
    
        public void loadUnitData(ArrayList tableListData,JTable jTable1) {
        
        try {
            tableListData=sqlManager.getUnitdata();
            
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                Vector tableline =(Vector) tableListData.get(i);
                String symbol = (String) tableline.get(0);
                model1.setValueAt((Object)symbol, i, 0);
                String nam = (String) tableline.get(1);
                model1.setValueAt((Object)nam, i, 1);

                Boolean isact = (Boolean) tableline.get(2);
                model1.setValueAt((Object)isact, i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    public void TableMouseorKeyChange(int row,JTextField createdOnTextField,JTextField updatedOnTextField
            ,JTextField CreatedByTextField,JTextField UpdatedByTextField,ArrayList tableListData,JTextField codeTextField,JTextField nameTextField
            ,JCheckBox isActiveCheckBox,int editUnit_ID,int status_ID,JButton editBtn,JButton  addnewbtn
            ,JButton addListBtn,JButton  deletebtn){
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
        Vector tableline =(Vector) tableListData.get(row);  
        String cod = (String) tableline.get(0);
        String nam = (String) tableline.get(1);
        Boolean isact = (Boolean) tableline.get(2);
        String createdOn = (String) tableline.get(7);
     
        String createdby = (String) tableline.get(5);
        createdOnTextField.setText(""+createdOn);
        updatedOnTextField.setText((String) tableline.get(8));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(6);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        CreatedByTextField.setText(""+createdby);
        UpdatedByTextField.setText(updatedby);
        codeTextField.setEditable(false);
        nameTextField.setEditable(false);
        isActiveCheckBox.setEnabled(false);
        codeTextField.setText(""+cod);
        nameTextField.setText(""+nam);
        isActiveCheckBox.setSelected(isact);
        editUnit_ID=(int) tableline.get(3);
        status_ID=(int) tableline.get(4);
        editBtn.setEnabled(true);
        addnewbtn.setEnabled(true);
        addListBtn.setEnabled(false);
        deletebtn.setEnabled(true);
        
    }
    
    
    public void OnAddToListButtonActionPerformed(ArrayList tableListData,JTextField codeTextField,JTextField nameTextField
            ,JCheckBox isActiveCheckBox,int editUnit_ID,int insertID,int status_ID,JButton addListBtn,JButton deletebtn
            ,int editnow_ID,JButton saveallbtn,boolean Saveremaining,JTable jTable1){
    
        String symbol=codeTextField.getText();
        if(symbol.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Symbol", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        if(symbol.length()>5){
          JOptionPane.showMessageDialog(null, 
                "Symbol Should be less then 6 digits", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
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
            int Unit_ID=(int) tableline.get(3);
            if(editUnit_ID==Unit_ID) continue;
            String sym = (String) tableline.get(0);
            if(symbol.compareTo(sym)==0){
                JOptionPane.showMessageDialog(null, 
                 "Symbol Already Exits", "Symbol Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            String nam = (String) tableline.get(1);
            if(nam.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
        }
        if(editUnit_ID<1){
            Vector line = new Vector();
            line.add(symbol); //0
            line.add(name);//1
            line.add(isActiveCheck);//2
            line.add(insertID);//3 ======== NUTRIENT_ID previous
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
                int Unit_ID=(int) line.get(3);
              
                if(editUnit_ID==Unit_ID){
                    status_ID=2;
                    line.set(0, symbol);
                    line.set(1,name);//1
                   
                    line.set(2,isActiveCheck);//2
                    
                    line.set(3,editUnit_ID);//3 ======== NUTRIENT_ID previous
                    line.set(4,status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        final    DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        model1.setRowCount(tableListData.size());
        for(int i=0;i<tableListData.size();i++)
        {
            Vector tableline =(Vector) tableListData.get(i);

            String cod = (String) tableline.get(0);
            model1.setValueAt((Object)cod, i, 0);
            String nam = (String) tableline.get(1);
            model1.setValueAt((Object)nam, i, 1);

            Boolean isact = (Boolean) tableline.get(2);
            model1.setValueAt((Object)isact, i, 2);
        }
        codeTextField.setText("");
        codeTextField.setEditable(true);
        nameTextField.setText("");
        nameTextField.setEnabled(true);
        
        isActiveCheckBox.setSelected(true);
        addListBtn.setEnabled(false);
        deletebtn.setEnabled(true);
        editUnit_ID=-1;
        status_ID=-1;
        editnow_ID=0;
        saveallbtn.setEnabled(true);
        Saveremaining = true;
        
        
        
        
    }


    
    public void OnDeleteButtonActionPerformed(JTable jTable1 ,ArrayList tableListData,JButton deletebtn 
            ,JButton editBtn,JButton  addnewbtn){
        
        
        
        int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
        if(dialogDelete==JOptionPane.YES_OPTION){     
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            int row = jTable1.getSelectedRow();
            Vector tableline =(Vector) tableListData.get(row);
            int Unit_ID=(int) tableline.get(3);    
            boolean isSuccess;
            try {
                isSuccess = sqlManager.deleteUnitdata(Unit_ID);
                if(isSuccess){ 
                    JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                    loadUnitData(tableListData,jTable1);
                 //Saveremaining = false;
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (UnknownHostException ex) {
                Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        deletebtn.setEnabled(false);
        editBtn.setEnabled(false);
        addnewbtn.setEnabled(true); 
    }
    
    
    public void OnSaveAllButtonActionPerformed(JTable jTable1 ,ArrayList tableListData,boolean Saveremaining){
    
                try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveUnitdata(tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                loadUnitData(tableListData,jTable1);
               // saveallbtn.setEnabled(false);
             Saveremaining = false;
            }
        } catch (SQLException ex) {
        } catch (UnknownHostException ex) {
        }
        
        
    }
}
