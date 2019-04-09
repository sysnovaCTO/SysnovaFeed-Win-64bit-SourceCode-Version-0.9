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
public class UserInfo {
    
    
    
        public void loadUserData(ArrayList tableListData,JTable jTable1) {
        
        try {
            tableListData=sqlManager.getUser1data();
            
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(tableListData.size());
            for(int i=0;i<tableListData.size();i++)
            {
                    Vector tableline =(Vector) tableListData.get(i);
                    String name = (String) tableline.get(0);
                    model1.setValueAt((Object)name, i, 0);
                    String pass = (String) tableline.get(1);
                    //model1.setValueAt((Object)pass, i, 1);
                    StringBuilder sb = new StringBuilder(pass.length());
                    int length = pass.length();
                    for(int j = 0;j<length;j++){
                    sb.append('*');
                    }
                    model1.setValueAt((Object)sb.toString(), i, 1);
                    Boolean isact = (Boolean) tableline.get(2);
                    model1.setValueAt((Object)isact, i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
//        
//    public void TableMouseorKeyChange(int row,JTextField createdOnTextField,JTextField updatedOnTextField
//            ,JTextField CreatedByTextField,JTextField UpdatedByTextField,ArrayList tableListData
//            ,JTextField UserIDTextField,JTextField PasswordTextField,JCheckBox isActiveCheckBox 
//            ,JButton editBtn, JButton addnewbtn, JButton addListBtn,int editUser_ID,int status_ID) {
//        
//        createdOnTextField.setText("");
//        updatedOnTextField.setText("");
//        CreatedByTextField.setText("");
//        UpdatedByTextField.setText("");
//        
//        //CreatedByTextField.setHorizontalAlignment(SwingConstants.RIGHT);
//        //UpdatedByTextField.setHorizontalAlignment(SwingConstants.LEFT);
//        
//        
//        
//        Vector tableline =(Vector) tableListData.get(row);
//               
//        String user = (String) tableline.get(0);
//        String pass = (String) tableline.get(1);
//        Boolean isact = (Boolean) tableline.get(2);
//        String createdOn = (String) tableline.get(5);
//     
//        String createdby = (String) tableline.get(7);
//        createdOnTextField.setText(""+createdOn);
//        updatedOnTextField.setText((String) tableline.get(6));
//        if(createdby.length()>20)
//            createdby = createdby.substring(0,19);
//        String updatedby =(String) tableline.get(8);
//        if(updatedby.length()>20)
//            updatedby = updatedby.substring(0,19);
//        CreatedByTextField.setText(""+createdby);
//        UpdatedByTextField.setText(""+updatedby);
//        UserIDTextField.setEditable(false);
//        PasswordTextField.setEditable(false);
//        isActiveCheckBox.setEnabled(false);
//
//        UserIDTextField.setText(""+user);
//        PasswordTextField.setText(""+pass);
//        isActiveCheckBox.setSelected(isact);
//        
//        editUser_ID=(int) tableline.get(3);
//        status_ID=(int) tableline.get(4);
//        editBtn.setEnabled(true);
//        addnewbtn.setEnabled(true);
//        addListBtn.setEnabled(true);
//
//        
//    }
    


    
    public void addListBtnActionPerformed(JTextField UserIDTextField,JTextField PasswordTextField,JCheckBox isActiveCheckBox
                ,ArrayList tableListData,int editUser_ID,int insertID,int status_ID,JTable jTable1,JButton editBtn
                ,JButton addnewbtn,JButton addListBtn,JButton saveallbtn,int editnow_ID,Boolean Saveremaining){
    
        
        
                String user=UserIDTextField.getText();
        if(user.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide User ID", "User ID Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String pass=PasswordTextField.getText();
        if(pass.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Password", "Password Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }  
        
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        for(int i=0;i<tableListData.size();i++)
        {
            Vector tableline =(Vector) tableListData.get(i);
            int AD_USER_ID=(int) tableline.get(3);
            if(editUser_ID==AD_USER_ID) continue;
            
            String name = (String) tableline.get(0);
            if(user.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "User ID Already Exists", "ID Error", JOptionPane.ERROR_MESSAGE);  
                return;
            } 
            String password = (String) tableline.get(1);

        }
        if(editUser_ID<1){
            Vector line = new Vector();
            line.add(user); //0
            line.add(pass);//1            
            line.add(isActiveCheck);//2
            line.add(insertID);//3 ======== User_ID previous
            line.add(status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            tableListData.add(line);
            insertID++;
        }
        else{
            for(int i=0;i<tableListData.size();i++)
            {
                Vector line =(Vector) tableListData.get(i);
                int User_ID=(int) line.get(3);
                if(editUser_ID==User_ID){
                    status_ID=2;
                    line.set(0, user);
                    line.set(1,pass);//1
                    line.set(2,isActiveCheck);//2
                    line.set(3,editUser_ID);//3 ======== User_ID previous
                    line.set(4,status_ID);//4======== -1 newadd 2 update 0 for exits 
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
                String name = (String) tableline.get(0);
                model1.setValueAt((Object)name, i, 0);
                String password = (String) tableline.get(1);
               // model1.setValueAt((Object)password, i, 1);
                StringBuilder sb = new StringBuilder(password.length());
                int length = password.length();
                for(int j = 0;j<length;j++){
                    sb.append('*');
                }
            model1.setValueAt((Object)sb.toString(), i, 1);//1
            Boolean isact = (Boolean) tableline.get(2);
            model1.setValueAt((Object)isact, i, 2);
                
        }
        UserIDTextField.setText("");
        UserIDTextField.setEditable(true);
        PasswordTextField.setText("");
        PasswordTextField.setEnabled(true);
        isActiveCheckBox.setSelected(true);       
        editBtn.setEnabled(false);
        addnewbtn.setEnabled(false);
        addListBtn.setEnabled(true);
        editUser_ID=-1;
        status_ID=-1;
        editnow_ID=0;
        saveallbtn.setEnabled(true);
        Saveremaining=true;
        
        
        
    }
    
    
    public void saveallbuttonActionPerformed(ArrayList tableListData,JButton saveallbtn,Boolean Saveremaining,JTable jTable1){
    
        
                try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveUserdata(tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                loadUserData(tableListData,jTable1);
                saveallbtn.setEnabled(false);
                Saveremaining=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    

    
}
