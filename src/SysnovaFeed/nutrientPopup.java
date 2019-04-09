package SysnovaFeed;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class nutrientPopup extends javax.swing.JFrame {


    /**
     * Creates new form ingredientsAll
     */
    int Compound_id=0;
    ArrayList tableListData=new ArrayList();
    ArrayList ingredientData=new ArrayList();
    ArrayList nutraintselectList=new ArrayList();
    int lineno=0;
    ArrayList ingredientDeleteData=new ArrayList();
    ArrayList nutrientData=new ArrayList();
    ArrayList nutrientDeleteData=new ArrayList();
     int priceweek_ID=-1;
    public  nutrientPopup(int row,ArrayList tableList,ArrayList ingredientList,ArrayList nutrientsList,ArrayList ingredientDeleteList,ArrayList nutrientsDeleteList,int price_ID) {
        priceweek_ID=price_ID;
        Compound_id=0;
        ingredientData.clear();
        if(ingredientList!=null)
            ingredientData=ingredientList;
        if(nutrientsList!=null)
            nutrientData=nutrientsList;
        if(ingredientDeleteList!=null)
            ingredientDeleteData=ingredientDeleteList;
        if(nutrientsDeleteList!=null)
            nutrientDeleteData=nutrientsDeleteList;
        tableListData.clear();
        tableListData=tableList;
        lineno=row;
        initComponents();
        getnutrientList();
    }
    
    public void getnutrientList(){
        Vector tableline =(Vector) tableListData.get(lineno);
        Compound_id=(int) tableline.get(11);
        selectCheckBox.setEnabled(false);
        
        Boolean broilerCheck = (Boolean) tableline.get(12);
        Boolean layerCheck = (Boolean) tableline.get(13);
        Boolean fishCheck = (Boolean) tableline.get(14);
        Boolean otherCheck = (Boolean) tableline.get(15);
        
        broilerCheckBox.setSelected(broilerCheck);
        layerCheckBox.setSelected(layerCheck);
        fishCheckBox.setSelected(fishCheck);
        otherCheckBox.setSelected(otherCheck);
        /*
        boolean layerCheck=layerCheckBox.isSelected();
        boolean fishCheck=fishCheckBox.isSelected();
        boolean otherCheck=otherCheckBox.isSelected();
        boolean broilerCheck=broilerCheckBox.isSelected();
        */
        String whereClause="";
        if(broilerCheck)
            whereClause=" BROILER='Y'";
       
        if(layerCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  LAYER='Y'";
        else
                whereClause=" LAYER='Y'";
        if(fishCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  FISH='Y'";
        else
                whereClause=" FISH='Y'";
        
        if(otherCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  OTHERE='Y'";
        else
                whereClause=" OTHERE='Y'";
        
        if(whereClause.length()<1){
            whereClause=" BROILER='N' AND LAYER='N' AND FISH='N' AND OTHERE='N' ";
        }
        
        try {
            if(Compound_id>0){
                nutraintselectList.clear();
                nutraintselectList = sqlManager.getNutrientPopupData(nutrientData,whereClause );
                if(nutraintselectList.size()<0)
                {
                    JOptionPane.showMessageDialog(null, "No Data Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                loadIngredientData();
            }
            else{
                JOptionPane.showMessageDialog(null, "No Compound Found", "Error ", JOptionPane.ERROR_MESSAGE);
                 
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public void loadIngredientData() {
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        model1.setRowCount(nutraintselectList.size());
        selectCheckBox.setEnabled(true);
        selectCheckBox.setSelected(false);
        for(int i=0;i<nutraintselectList.size();i++)
        {
            
            boolean isact=selectCheckBox.isSelected();
            Vector tableline =(Vector) nutraintselectList.get(i);
            model1.setValueAt((Object)isact, i, 0);
            String cod = (String) tableline.get(2);
            model1.setValueAt((Object)cod, i, 1);
            String name = (String) tableline.get(3);
            model1.setValueAt((Object)name, i, 2);
            
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cancelbtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        selectCheckBox = new javax.swing.JCheckBox();
        broilerCheckBox = new javax.swing.JCheckBox();
        fishCheckBox = new javax.swing.JCheckBox();
        layerCheckBox = new javax.swing.JCheckBox();
        otherCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("All Nutrients");
        setBackground(new java.awt.Color(255, 255, 255));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Code", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(241, 227, 227));
        jTable1.setRowHeight(20);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        }

        cancelbtn.setBackground(new java.awt.Color(207, 63, 70));
        cancelbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        cancelbtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelbtn.setText("Cancel");
        cancelbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelbtn.setIconTextGap(5);
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(119, 150, 213));
        addBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add");
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setIconTextGap(5);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(70, 73, 85));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nutrients List");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        selectCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        selectCheckBox.setText("Select All");
        selectCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCheckBoxActionPerformed(evt);
            }
        });

        broilerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        broilerCheckBox.setText("Broiler");
        broilerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                broilerCheckBoxActionPerformed(evt);
            }
        });

        fishCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        fishCheckBox.setText("Fish");
        fishCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fishCheckBoxActionPerformed(evt);
            }
        });

        layerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        layerCheckBox.setText("Layer");
        layerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerCheckBoxActionPerformed(evt);
            }
        });

        otherCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        otherCheckBox.setText("Other");
        otherCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(broilerCheckBox)
                        .addGap(81, 81, 81)
                        .addComponent(fishCheckBox)
                        .addGap(100, 100, 100)
                        .addComponent(layerCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(otherCheckBox)
                        .addGap(47, 47, 47))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(broilerCheckBox)
                        .addComponent(fishCheckBox))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(layerCheckBox)
                        .addComponent(otherCheckBox)))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(cancelbtn)
                    .addComponent(selectCheckBox))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        boolean ISFOUND=false;
        for(int i=0;i<nutraintselectList.size();i++)
        {
            boolean isSelected=(boolean) model1.getValueAt(i, 0);
            if(isSelected){
                ISFOUND=true;
                Vector tableline =(Vector) nutraintselectList.get(i);
                Vector line = new Vector();
                line.add((int) tableline.get(0)); //0
                line.add((String) tableline.get(2)); //1
                line.add((String) tableline.get(3));//2
                line.add((String) tableline.get(4));//3 price
                line.add(-1.0); //4 minval
                line.add(-1.0); //5 val
                line.add(-1.0); //6 maxval
                line.add(-1); //7 COMPOUND_ID
                line.add(-1); //8 COMPOUNDnutrients_ID
                nutrientData.add(line);
            }
        }
        if(!ISFOUND){
            JOptionPane.showMessageDialog(null, "No Selection Found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.dispose();
        java.awt.Window win[] = java.awt.Window.getWindows();
            for(int i=0;i<win.length;i++){
            String a=win[i].getClass().toString();
            if(a.equals("class SysnovaFeed.Main")){
            }
            else
            win[i].dispose();
        }
        CompoundFormulation compoundFormulation = new CompoundFormulation(lineno,tableListData,ingredientData,nutrientData,ingredientDeleteData,nutrientDeleteData,priceweek_ID);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        compoundFormulation.setBounds(0,0,screenSize.width, screenSize.height);
        compoundFormulation.setVisible(true);
        //compoundFormulation.setLocation(200, 200);
        
    }//GEN-LAST:event_addBtnActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void selectCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCheckBoxActionPerformed
        // TODO add your handling code here:
        //if(selectCheckBox.isSelected())
        {
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(0);
            model1.setRowCount(nutraintselectList.size());
            for(int i=0;i<nutraintselectList.size();i++)
            {
                boolean isact=selectCheckBox.isSelected();
                Vector tableline =(Vector) nutraintselectList.get(i);
                model1.setValueAt((Object)isact, i, 0);
                String cod = (String) tableline.get(2);
                model1.setValueAt((Object)cod, i, 1);
                String name = (String) tableline.get(3);
                model1.setValueAt((Object)name, i, 2);

            }
        }
    }//GEN-LAST:event_selectCheckBoxActionPerformed
    private void selectCheckBox()
    {
        
        boolean layerCheck=layerCheckBox.isSelected();
        boolean fishCheck=fishCheckBox.isSelected();
        boolean otherCheck=otherCheckBox.isSelected();
        boolean broilerCheck=broilerCheckBox.isSelected();

        String whereClause="";
        if(broilerCheck)
            whereClause=" BROILER='Y'";
       
        if(layerCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  LAYER='Y'";
        else
                whereClause=" LAYER='Y'";
        if(fishCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  FISH='Y'";
        else
                whereClause=" FISH='Y'";
        
        if(otherCheck)
            if(whereClause.length()>1)
                whereClause=whereClause+" OR  OTHERE='Y'";
        else
                whereClause=" OTHERE='Y'";
        
        if(whereClause.length()<1){
            whereClause=" BROILER='N' AND LAYER='N' AND FISH='N' AND OTHERE='N' ";
        }
        
        try {
            if(Compound_id>0){
                nutraintselectList.clear();
                nutraintselectList = sqlManager.getNutrientPopupData(nutrientData,whereClause );
                if(nutraintselectList.size()<0)
                {
                    JOptionPane.showMessageDialog(null, "No Data Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                loadIngredientData();
            }
            else{
                JOptionPane.showMessageDialog(null, "No Compound Found", "Error ", JOptionPane.ERROR_MESSAGE);
                 
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void broilerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_broilerCheckBoxActionPerformed
        // TODO add your handling code here:
        selectCheckBox();
    }//GEN-LAST:event_broilerCheckBoxActionPerformed

    private void layerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerCheckBoxActionPerformed
        // TODO add your handling code here:
        selectCheckBox();
    }//GEN-LAST:event_layerCheckBoxActionPerformed

    private void fishCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fishCheckBoxActionPerformed
        // TODO add your handling code here:
        selectCheckBox();
    }//GEN-LAST:event_fishCheckBoxActionPerformed

    private void otherCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherCheckBoxActionPerformed
        // TODO add your handling code here:
         selectCheckBox();
    }//GEN-LAST:event_otherCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JCheckBox broilerCheckBox;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JCheckBox fishCheckBox;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox layerCheckBox;
    private javax.swing.JCheckBox otherCheckBox;
    private javax.swing.JCheckBox selectCheckBox;
    // End of variables declaration//GEN-END:variables
}
