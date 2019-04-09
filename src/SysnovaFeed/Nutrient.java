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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class Nutrient {
    

//        
    public Nutrient(JTextField codeTextField,JTextField nameTextField,JTextField longTextField
        ,JCheckBox isActiveCheckBox,JCheckBox layerCheckBox,JCheckBox fishCheckBox
        ,JCheckBox otherCheckBox,JCheckBox broilerCheckBox,JButton editBtn,JButton addnewbtn,JButton addListBtn
        ,JTextField createdOnTextField,JTextField updatedOnTextField,JTextField CreatedByTextField,JTextField UpdatedByTextField)
    {
        
        codeTextField.setText("");
        nameTextField.setText("");
        longTextField.setText("");
        isActiveCheckBox.setSelected(true);
        broilerCheckBox.setSelected(false);
        layerCheckBox.setSelected(false);
        fishCheckBox.setSelected(false);
        otherCheckBox.setSelected(false);
        editBtn.setEnabled(false);
        addnewbtn.setEnabled(false);
        addListBtn.setEnabled(true);
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
    }
//        
//    ArrayList unitList=new ArrayList();
//    ArrayList propertyList=new ArrayList();
    public void NutrientWork(JComboBox unitComboBox,JComboBox propertyComboBox,ArrayList unitList,ArrayList propertyList) {

        unitList.clear();
        propertyList.clear();
        try {
            unitList=sqlManager.getNutrientUnitComboodata();
            unitComboPanel(unitList, unitComboBox);
            propertyList=sqlManager.getNutrientPropertyComboodata();
            propertyComboPanel(propertyList,propertyComboBox);
        } catch (SQLException ex) {
            //Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            //Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
//    
    public void unitComboPanel(ArrayList unitList,JComboBox unitComboBox) {
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) unitComboBox.getModel();
        // removing old data
        model.removeAllElements();

        for(int i=0;i<unitList.size();i++)
        {
                Vector line =(Vector) unitList.get(i);
                int Unit_ID = (Integer) line.get(0);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        unitComboBox.setModel(model); 
    }
        
    public void propertyComboPanel(ArrayList propertyList,JComboBox propertyComboBox) {
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) propertyComboBox.getModel();
        // removing old data
        model.removeAllElements();

        for(int i=0;i<propertyList.size();i++)
        {
                Vector line =(Vector) propertyList.get(i);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        propertyComboBox.setModel(model); 
    }
             //ArrayList tableListData=new ArrayList(); 
             
    
    public void loadNutrientData(JTable jTable1,ArrayList tableListData) {
        
        try {
            tableListData=sqlManager.getNutrientdata();
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                    Vector tableline =(Vector) tableListData.get(i);

                    String cod = (String) tableline.get(0);
                    model1.setValueAt((Object)cod, i, 0);
                    String nam = (String) tableline.get(1);
                    model1.setValueAt((Object)nam, i, 1);
                    String lngtxt = (String) tableline.get(2);
                    model1.setValueAt((Object)lngtxt, i, 2);
                    String unitval = (String) tableline.get(3);
                    model1.setValueAt((Object)unitval, i, 3);
                    String propertyVal = (String) tableline.get(5);
                    model1.setValueAt((Object)propertyVal, i, 4);
                    Boolean isact = (Boolean) tableline.get(7);
                    model1.setValueAt((Object)isact, i, 5);

            }
        } catch (SQLException ex) {
           // Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            //Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    //ArrayList tableListData=new ArrayList();
      int editNutraint_ID=-1;
    int status_ID=-1;
    int editnow_ID=0;
      boolean Saveremaining=false;
     int insertID=10000000;
    public void AddListButtonActionPerformed(JTextField codeTextField,JTextField nameTextField,JTextField longTextField
        ,JComboBox unitComboBox,JComboBox propertyComboBox,JCheckBox isActiveCheckBox,JCheckBox layerCheckBox,JCheckBox fishCheckBox
        ,JCheckBox otherCheckBox,JCheckBox broilerCheckBox,JButton editBtn,JButton addnewbtn,JButton addListBtn,JButton saveallbtn
        ,JTable jTable1,ArrayList tableListData,ArrayList unitList,ArrayList propertyList){
    
        String code=codeTextField.getText();
        if(code.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Code", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String name=nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Short Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String longText=longTextField.getText();
        if(longText.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Long Name", "Long Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        
        String unitValue = unitComboBox.getSelectedItem().toString();
        int unit_Index=unitComboBox.getSelectedIndex();
        Vector unit =(Vector) unitList.get(unit_Index);
        int Unit_ID = (Integer) unit.get(0);
        
        String propertyValue = propertyComboBox.getSelectedItem().toString();
        int property_Index=propertyComboBox.getSelectedIndex();
        Vector property =(Vector) propertyList.get(property_Index);
        int property_ID = (Integer) property.get(0);
        
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        boolean layerCheck=layerCheckBox.isSelected();
        boolean fishCheck=fishCheckBox.isSelected();
        boolean otherCheck=otherCheckBox.isSelected();
        boolean broilerCheck=broilerCheckBox.isSelected();
        
        for(int i=0;i<tableListData.size();i++)
        {
            Vector tableline =(Vector) tableListData.get(i);

            int Nutraint_ID=(int) tableline.get(12);
            if(editNutraint_ID==Nutraint_ID) continue;
            
            String cod = (String) tableline.get(0);
            if(code.compareTo(cod)==0){
                JOptionPane.showMessageDialog(null, 
                 "Code Already Exits", "Code Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            String nam = (String) tableline.get(1);
            if(nam.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Short Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            String lngtxt = (String) tableline.get(2);
            if(lngtxt.compareTo(longText)==0){
                JOptionPane.showMessageDialog(null, 
                 "Long Name Already Exits", "Long Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
        }
        if(editNutraint_ID<1){
            Vector line = new Vector();
            line.add(code); //0
            line.add(name);//1
            line.add(longText);//2
            line.add(unitValue);//3
            line.add(Unit_ID);//4
            line.add(propertyValue);//5
            line.add(property_ID);//6
            line.add(isActiveCheck);//7
            line.add(layerCheck);//8
            line.add(broilerCheck);//9
            line.add(fishCheck);//10
            line.add(otherCheck);//11
            line.add(insertID);//12 ======== NUTRIENT_ID previous
            line.add(status_ID);//14========1 newadd 2update 0 for exits 
            line.add(""); //15
            line.add(""); //16
            line.add(""); //17
            line.add(""); //18
            tableListData.add(line);
            insertID++;
        }
        else{
            for(int i=0;i<tableListData.size();i++)
            {
                Vector line =(Vector) tableListData.get(i);
                int Nutraint_ID=(int) line.get(12);
                if(editNutraint_ID==Nutraint_ID){
                    status_ID=2;
                    line.set(0, code);
                    line.set(1,name);//1
                    line.set(2,longText);//2
                    line.set(3,unitValue);//3
                    line.set(4,Unit_ID);//4
                    line.set(5,propertyValue);//5
                    line.set(6,property_ID);//6
                    line.set(7,isActiveCheck);//7
                    line.set(8,layerCheck);//8
                    line.set(9,broilerCheck);//9
                    line.set(10,fishCheck);//10
                    line.set(11,otherCheck);//11
                    line.set(12,editNutraint_ID);//12 ======== NUTRIENT_ID previous
                    line.set(13,status_ID);//======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        //editNutraint_ID=(int) tableline.get(12);
        //status_ID=(int) tableline.get(13);

        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        model1.setRowCount(tableListData.size());
        for(int i=0;i<tableListData.size();i++)
        {
                Vector tableline =(Vector) tableListData.get(i);
               
                String cod = (String) tableline.get(0);
                model1.setValueAt((Object)cod, i, 0);
                String nam = (String) tableline.get(1);
                model1.setValueAt((Object)nam, i, 1);
                String lngtxt = (String) tableline.get(2);
                model1.setValueAt((Object)lngtxt, i, 2);
                String unitval = (String) tableline.get(3);
                model1.setValueAt((Object)unitval, i, 3);
                String propertyVal = (String) tableline.get(5);
                model1.setValueAt((Object)propertyVal, i, 4);
                Boolean isact = (Boolean) tableline.get(7);
                model1.setValueAt((Object)isact, i, 5);
                
        }
        codeTextField.setText("");
        codeTextField.setEditable(true);
        nameTextField.setText("");
        nameTextField.setEnabled(true);
        longTextField.setText("");
        isActiveCheckBox.setSelected(true);
        broilerCheckBox.setSelected(false);
        layerCheckBox.setSelected(false);
        fishCheckBox.setSelected(false);
        otherCheckBox.setSelected(false);
        
        editBtn.setEnabled(false);
        addnewbtn.setEnabled(false);
        addListBtn.setEnabled(true);
        editNutraint_ID=-1;
        status_ID=-1;
        editnow_ID=0;
        saveallbtn.setEnabled(true);
        Saveremaining=true;
    
    }
  
   
    
    public void TableMouseorKeyChange(int row,JTextField createdOnTextField,JTextField updatedOnTextField
            ,JTextField CreatedByTextField,JTextField UpdatedByTextField,JTextField codeTextField,JTextField nameTextField,JTextField longTextField
            ,JCheckBox isActiveCheckBox,JCheckBox layerCheckBox,JCheckBox fishCheckBox,JCheckBox broilerCheckBox
            ,JCheckBox otherCheckBox,JComboBox unitComboBox,JComboBox propertyComboBox,JButton editBtn,JButton addnewbtn
            ,JButton addListBtn,ArrayList tableListData,ArrayList unitList,ArrayList propertyList){
        
        createdOnTextField.setText("");
        updatedOnTextField.setText("");
        CreatedByTextField.setText("");
        UpdatedByTextField.setText("");
        
        //CreatedByTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        //UpdatedByTextField.setHorizontalAlignment(SwingConstants.LEFT);
        
        
        
        Vector tableline =(Vector) tableListData.get(row);
               
        String cod = (String) tableline.get(0);
        String nam = (String) tableline.get(1);
        String lngtxt = (String) tableline.get(2);
        String unitval = (String) tableline.get(3);
        int unitid=(int) tableline.get(4);
        String propertyVal = (String) tableline.get(5);
        int propertyid=(int) tableline.get(6);
        Boolean isact = (Boolean) tableline.get(7);
        String createdOn = (String) tableline.get(14);
     
        String createdby = (String) tableline.get(16);
        createdOnTextField.setText(""+createdOn);
        updatedOnTextField.setText((String) tableline.get(15));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(17);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        CreatedByTextField.setText(""+createdby);
        UpdatedByTextField.setText(updatedby);
        codeTextField.setEditable(false);
        nameTextField.setEditable(false);
        longTextField.setEditable(false);
        isActiveCheckBox.setEnabled(false);
        broilerCheckBox.setEnabled(false);
        layerCheckBox.setEnabled(false);
        fishCheckBox.setEnabled(false);
        otherCheckBox.setEnabled(false);
        unitComboBox.setEnabled(false);
        propertyComboBox.setEnabled(false);
        
        for(int i=0;i<unitList.size();i++)
        {
                Vector line =(Vector) unitList.get(i);
                int Unit_ID = (Integer) line.get(0);
                if(unitid==Unit_ID){
                    unitComboBox.setSelectedIndex(i);
                     break;
                }
        }
        for(int i=0;i<propertyList.size();i++)
        {
                Vector line =(Vector) propertyList.get(i);
                int propertyID = (Integer) line.get(0);
                if(propertyid==propertyID){
                    propertyComboBox.setSelectedIndex(i);
                }
               
        }

        codeTextField.setText(""+cod);
        nameTextField.setText(""+nam);
        longTextField.setText(""+lngtxt);
        isActiveCheckBox.setSelected(isact);
        
        Boolean layerCheck = (Boolean) tableline.get(8);
        Boolean broilerCheck = (Boolean) tableline.get(9);
        Boolean fishCheck = (Boolean) tableline.get(10);
        Boolean otherCheck = (Boolean) tableline.get(11);
        
        broilerCheckBox.setSelected(broilerCheck);
        layerCheckBox.setSelected(layerCheck);
        fishCheckBox.setSelected(fishCheck);
        otherCheckBox.setSelected(otherCheck);
        editNutraint_ID=(int) tableline.get(12);
        status_ID=(int) tableline.get(13);
        editBtn.setEnabled(true);
        addnewbtn.setEnabled(true);
        addListBtn.setEnabled(false);
        
    }
    
}
