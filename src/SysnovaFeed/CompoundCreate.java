package SysnovaFeed;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class CompoundCreate extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    boolean isNew=false;
    int Compound_id=0;
    ArrayList tableListData=new ArrayList();
    UtilDateModel modelleft=null;
    JDatePanelImpl datePanelleft=null;
    JDatePickerImpl datePickerleft=null;
    UtilDateModel modelRight=null;
    JDatePanelImpl datePanelRight=null;
    JDatePickerImpl datePickerRight=null;
    public CompoundCreate(int row,ArrayList dataList) {
        initComponents();
        Compound_id=0;
        userNameLabel.setText("");
        userNameLabel.setText(" "+sqlManager.getUserName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
       // this.addWindowListener(exitListener);
        tableListData.clear();
        tableListData=dataList;
        CompoundWork();
        if(row<0){
            
        }else{
            setcompoundData(row);
        }
    }
    ArrayList groupList=new ArrayList();
    ArrayList typeList=new ArrayList();
    public void CompoundWork() {

        groupList.clear();
        typeList.clear();
        try {

            groupList=sqlManager.getCompoundGroupComboodata();
            groupComboPanel(groupList);
            typeList=sqlManager.getCompoundtypeComboodata();
            typeComboPanel(typeList);
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void typeComboPanel(ArrayList typeList) {
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) typeComboBox.getModel();
        // removing old data
        model.removeAllElements();

        for(int i=0;i<typeList.size();i++)
        {
                Vector line =(Vector) typeList.get(i);
                int type_ID = (Integer) line.get(0);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        typeComboBox.setModel(model); 
    }
    public void groupComboPanel(ArrayList groupList) {
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) groupComboBox.getModel();
        // removing old data
        model.removeAllElements();

        for(int i=0;i<groupList.size();i++)
        {
                Vector line =(Vector) groupList.get(i);
                int group_ID = (Integer) line.get(0);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        groupComboBox.setModel(model); 
    }
    
//    WindowListener exitListener = new WindowAdapter() {
//        public void windowClosing(WindowEvent e) {            
//            Compound Compound = new Compound();
//            Compound.setVisible(true);
//        }
//    };
    public void setcompoundData(int row) {
          
        Vector tableline =(Vector) tableListData.get(row);
        Compound_id=(int) tableline.get(11);
        String cod = (String) tableline.get(0);
        codeTextField.setText(cod);
        String name = (String) tableline.get(1);
        NameTextField.setText(name);
        String DESCRIPTION = (String) tableline.get(2);
        descTextField.setText(DESCRIPTION);
        int group_ID=(int) tableline.get(7);
        int type_ID=(int) tableline.get(5);

        for(int i=0;i<groupList.size();i++)
        {
            Vector line =(Vector) groupList.get(i);
            int groupID = (Integer) line.get(0);
            if(groupID==group_ID){
                groupComboBox.setSelectedIndex(i);
            }
        }
        for(int i=0;i<typeList.size();i++)
        {
            Vector line =(Vector) typeList.get(i);
            int typeID = (Integer) line.get(0);
            if(typeID==type_ID){
                typeComboBox.setSelectedIndex(i);
            }
        } 
        Boolean broilerCheck = (Boolean) tableline.get(12);
        Boolean layerCheck = (Boolean) tableline.get(13);
        Boolean fishCheck = (Boolean) tableline.get(14);
        Boolean otherCheck = (Boolean) tableline.get(15);
        
        broilerCheckBox.setSelected(broilerCheck);
        layerCheckBox.setSelected(layerCheck);
        fishCheckBox.setSelected(fishCheck);
        otherCheckBox.setSelected(otherCheck);
        Boolean isact = (Boolean) tableline.get(3);
        isActiveCheckBox.setSelected(isact);
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        codeTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        descTextField = new javax.swing.JTextField();
        isActiveCheckBox = new javax.swing.JCheckBox();
        NameTextField = new javax.swing.JTextField();
        groupComboBox = new javax.swing.JComboBox();
        typeComboBox = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        broilerCheckBox = new javax.swing.JCheckBox();
        fishCheckBox = new javax.swing.JCheckBox();
        layerCheckBox = new javax.swing.JCheckBox();
        otherCheckBox = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        saveallbtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SysnovaFEED");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(254, 254, 254));
        jPanel1.setNextFocusableComponent(codeTextField);
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 470));

        jPanel2.setBackground(new java.awt.Color(57, 63, 63));
        jPanel2.setNextFocusableComponent(codeTextField);

        userNameLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(232, 245, 245));
        userNameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/profile.png"))); // NOI18N
        userNameLabel.setText("   ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userNameLabel)
                .addContainerGap(756, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNameLabel)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Compound Entry");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setNextFocusableComponent(codeTextField);
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 350));

        codeTextField.setNextFocusableComponent(descTextField);
        codeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("Code");

        jLabel7.setText("Name");

        jLabel9.setText("Description");

        descTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTextFieldActionPerformed(evt);
            }
        });

        isActiveCheckBox.setSelected(true);
        isActiveCheckBox.setText("IsActive");
        isActiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isActiveCheckBoxActionPerformed(evt);
            }
        });

        NameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTextFieldActionPerformed(evt);
            }
        });

        groupComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Group");

        jLabel12.setText("Type");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        broilerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        broilerCheckBox.setText("Broiler");
        broilerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                broilerCheckBoxActionPerformed(evt);
            }
        });

        fishCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        fishCheckBox.setText("Fish");

        layerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        layerCheckBox.setText("Layer");
        layerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerCheckBoxActionPerformed(evt);
            }
        });

        otherCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        otherCheckBox.setText("Other");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(broilerCheckBox)
                    .addComponent(layerCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otherCheckBox)
                    .addComponent(fishCheckBox))
                .addGap(23, 23, 23))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(broilerCheckBox)
                    .addComponent(fishCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(layerCheckBox)
                    .addComponent(otherCheckBox))
                .addContainerGap())
        );

        jLabel13.setText("Animal Type");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codeTextField)
                    .addComponent(descTextField)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NameTextField)
                    .addComponent(isActiveCheckBox)
                    .addComponent(groupComboBox, 0, 220, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(descTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(isActiveCheckBox))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel13))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        saveallbtn.setToolTipText("Save All");
        saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveallbtn.setIconTextGap(5);
        saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveallbtnActionPerformed(evt);
            }
        });

        backbtn.setBackground(new java.awt.Color(255, 255, 255));
        backbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        backbtn.setForeground(new java.awt.Color(255, 255, 255));
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/back.png"))); // NOI18N
        backbtn.setToolTipText("Save All");
        backbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backbtn.setIconTextGap(5);
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 457, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTextFieldActionPerformed

    private void descTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descTextFieldActionPerformed

    private void saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveallbtnActionPerformed
        
        String code=codeTextField.getText();
        if(code.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Code", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String name=NameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        
        if(tableListData!=null)
        if(tableListData.size()>0)
            for(int i=0;i<tableListData.size();i++)
            {
                Vector tableline =(Vector) tableListData.get(i);

                int Compound_ID=(int) tableline.get(11);
                if(Compound_ID==Compound_id) continue;

                String cod = (String) tableline.get(0);
                if(code.compareTo(cod)==0){
                    JOptionPane.showMessageDialog(null, 
                     "Code Already Exits", "Code Error", JOptionPane.ERROR_MESSAGE);  
                    return;
                }
                String name1 = (String) tableline.get(1);
                if(name1.compareTo(name)==0){
                    JOptionPane.showMessageDialog(null, 
                     "Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                    return;
                }
            }
        
        String desc=descTextField.getText();
        int CompoundGroup_Index=groupComboBox.getSelectedIndex();
        Vector CompoundGrou =(Vector) groupList.get(CompoundGroup_Index);
        int CompoundGroup_ID = (Integer) CompoundGrou.get(0);
        
        int CompoundType_Index=typeComboBox.getSelectedIndex();
        Vector CompoundType =(Vector) typeList.get(CompoundType_Index);
        int CompoundType_ID = (Integer) CompoundType.get(0);
        
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        boolean layerCheck=layerCheckBox.isSelected();
        boolean fishCheck=fishCheckBox.isSelected();
        boolean otherCheck=otherCheckBox.isSelected();
        boolean broilerCheck=broilerCheckBox.isSelected();

        try {
            if(Compound_id<1){
                Compound_id = sqlManager.saveCompound(Compound_id,code,name,desc,CompoundGroup_ID,CompoundType_ID,isActiveCheck,layerCheck,fishCheck,otherCheck,broilerCheck );
                if(Compound_id>0)
                {
                    JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                }  
            }
            else{
                int id = sqlManager.saveCompound(Compound_id,code,name,desc,CompoundGroup_ID,CompoundType_ID,isActiveCheck,layerCheck,fishCheck,otherCheck,broilerCheck );
                if(id>0)
                {
                    JOptionPane.showMessageDialog(null, "Updated Successfully", "Updated ", JOptionPane.INFORMATION_MESSAGE);
                }
                 
            }
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_saveallbtnActionPerformed

    
    private void isActiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isActiveCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isActiveCheckBoxActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
//        Compound Compound = new Compound();
//        Compound.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void NameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTextFieldActionPerformed

    private void broilerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_broilerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_broilerCheckBoxActionPerformed

    private void layerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_layerCheckBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameTextField;
    private javax.swing.JButton backbtn;
    private javax.swing.JCheckBox broilerCheckBox;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JTextField descTextField;
    private javax.swing.JCheckBox fishCheckBox;
    private javax.swing.JComboBox groupComboBox;
    private javax.swing.JCheckBox isActiveCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JCheckBox layerCheckBox;
    private javax.swing.JCheckBox otherCheckBox;
    private javax.swing.JButton saveallbtn;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}