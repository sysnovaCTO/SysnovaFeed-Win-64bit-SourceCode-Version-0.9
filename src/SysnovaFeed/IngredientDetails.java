package SysnovaFeed;


import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Component;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class IngredientDetails extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    boolean isNew=false;
    int Ingredient_ID=0;
    ArrayList tableListData=new ArrayList();
    public IngredientDetails(int row,ArrayList tableList) {
        tableListData.clear();
        if(row<0 || tableList.size()<1){
            try {
                tableListData.clear();
                tableListData=sqlManager.getIngrediantdata();
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            tableListData=tableList;
        }
        
        isNew=false;
        Ingredient_ID=0;
        initComponents();
        userNameLabel.setText("");
        userNameLabel.setText(" "+sqlManager.getUserName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
//        this.addWindowListener(exitListener);
        IngredientDetailsWork();
        if(row>-1)
        {
             Vector tableline =(Vector) tableList.get(row);
             IngredientDataLoad(tableline);
             setNeutraintdata();
             savebtn.setText("Update");
        }else{
            isNew=true;
            newBtn.setEnabled(false);
            savebtn.setText("Save");
        }
        
    }
    
//    WindowListener exitListener = new WindowAdapter() {
//        public void windowClosing(WindowEvent e) {
//            /*
//            int confirm = JOptionPane.showOptionDialog(
//                 null, "Are You Sure to Close Application?", 
//                 "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
//                 JOptionPane.QUESTION_MESSAGE, null, null, null);
//            if (confirm == 0) {
//               System.exit(0);
//            }
//            */
//            
//            Ingredient IngredientForm = new Ingredient();
//            IngredientForm.setVisible(true);
//        }
//    };
    
    
    public void IngredientDataLoad(Vector tableline) {
        
        String cod = (String) tableline.get(0);
        String nam = (String) tableline.get(1);
        String scienttxt = (String) tableline.get(2);
        String groupval = (String) tableline.get(3);
        int groupid=(int) tableline.get(4);
        String typeVal = (String) tableline.get(5);
        int typeid=(int) tableline.get(6);
        Boolean isact = (Boolean) tableline.get(7);
        Ingredient_ID= (Integer) tableline.get(10);;
        for(int i=0;i<groupList.size();i++)
        {
                Vector line =(Vector) groupList.get(i);
                int Group_ID = (Integer) line.get(0);
                if(groupid==Group_ID){
                    groupComboBox.setSelectedIndex(i);
                     break;
                }
        }
        for(int i=0;i<typeList.size();i++)
        {
                Vector line =(Vector) typeList.get(i);
                int typeID = (Integer) line.get(0);
                if(typeid==typeID){
                    typeComboBox.setSelectedIndex(i+1);
                }
               
        }
        codeTextField.setText(""+cod);
        nameTextField.setText(""+nam);
        scintiTextField.setText(""+scienttxt);
        isActiveCheckBox.setSelected(isact);
       
    }
    ArrayList groupList=new ArrayList();
    ArrayList typeList=new ArrayList();
    public void IngredientDetailsWork() {

        groupList.clear();
        typeList.clear();
        try {
            groupList=sqlManager.getIngredientGroupComboodata();
            groupComboPanel(groupList);
            typeList=sqlManager.getIngredienttypeComboodata();
            typeComboPanel(typeList);
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
      private static final DecimalFormat formatter = new DecimalFormat( "#.000" );
 
      public Component getTableCellRendererComponent(
         JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column) {
 
         // First format the cell value as required
          if(value!=null){
             try{
                value = formatter.format((Number)value);
            } catch(Exception e){
            } 
          }
        
            // And pass it on to parent class
 
         return super.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, column );
          
         
      }
   }
    private class CustomCellRenderer extends DefaultTableCellRenderer {

        /* (non-Javadoc)
         * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table, Object value,
          boolean isSelected, boolean hasFocus, int row, int column) {

         Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
           row, column);

         //Set foreground color
         //rendererComp.setForeground(Color.red);

         //Set background color
         rendererComp .setBackground(Color.decode("#f1fbf1"));

         return rendererComp ;
        }

    }
    
    
    ArrayList nutrientListData=new ArrayList();
    public void setNeutraintdata() {
        
        try {
            nutrientListData=sqlManager.getplantwisenutraintdata(Ingredient_ID);
            DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
            model1.setRowCount(nutrientListData.size());

            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            jTable1.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
//            jTable1.getColumnModel().getColumn(4).setCellRenderer(new CompoundFormulation.DecimalFormatRenderer() );
//            jTable1.getColumnModel().getColumn(5).setCellRenderer(new CompoundFormulation.DecimalFormatRenderer() );
//            jTable1.getColumnModel().getColumn(6).setCellRenderer(new CompoundFormulation.DecimalFormatRenderer() );
            jTable1.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer() );
            jTable1.getColumnModel().getColumn(1).setCellRenderer(new CustomCellRenderer() );
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer() );
            jTable1.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer() );
            jTable1.getColumnModel().getColumn(7).setCellRenderer(new CustomCellRenderer() );
            

            for(int i=0;i<nutrientListData.size();i++)
            {        
                Vector tableline =(Vector) nutrientListData.get(i);
                String cod = (String) tableline.get(2);
                model1.setValueAt((Object)cod, i, 0);
                String nam = (String) tableline.get(3);
                model1.setValueAt((Object)nam, i, 1);
                String longnametxt = (String) tableline.get(4);
                model1.setValueAt((Object)longnametxt, i, 2);
                String groupval = (String) tableline.get(9);
                model1.setValueAt((Object)groupval, i, 3);
                Double specification = (Double) tableline.get(6);
                if(specification!=-1){
                    model1.setValueAt((Object)specification, i, 4);
                }
                else{
                    model1.setValueAt((Object)null, i, 4);
                }
                Double overlay = (Double) tableline.get(7);
                if(overlay!=-1)
                    model1.setValueAt((Object)overlay, i, 5);
                else{
                    model1.setValueAt((Object)null, i, 5);
                }
                Double analysis = (Double) tableline.get(8);
                if(analysis!=-1)
                model1.setValueAt((Object)analysis, i, 6);
                else{
                    model1.setValueAt((Object)null, i, 6);
                }
                
                String unit = (String) tableline.get(10);
                model1.setValueAt((Object)unit, i, 7);
            }
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
        model.addElement(null);
        for(int i=0;i<typeList.size();i++)
        {
                Vector line =(Vector) typeList.get(i);
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
                int Group_ID = (Integer) line.get(0);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        groupComboBox.setModel(model); 
    }
    public void saveIngredientData(){

        String code=codeTextField.getText();
        if(code.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Code", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String name=nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Long Name", "Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        if(tableListData.size()>0)
            for(int i=0;i<tableListData.size();i++)
            {
                Vector tableline =(Vector) tableListData.get(i);

                int ingredient_ID=(int) tableline.get(10);
                if(ingredient_ID==Ingredient_ID) continue;

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
            }
        
        String scintiText=scintiTextField.getText();
        String groupValue = groupComboBox.getSelectedItem().toString();
        int group_Index=groupComboBox.getSelectedIndex();
        Vector group =(Vector) groupList.get(group_Index);
        int group_ID = (Integer) group.get(0);
        int type_ID=0;
        int type_Index=typeComboBox.getSelectedIndex();
        if(type_Index>0){
        Vector type =(Vector) typeList.get(type_Index-1);
        type_ID = (Integer) type.get(0);   
        }
        
        
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        if(isNew){
            try {
                Ingredient_ID = sqlManager.saveingredientdata(isNew,code,name,scintiText,group_ID,type_ID,isActiveCheck,0);
                if(Ingredient_ID>0)
                {
                    newBtn.setEnabled(true);
                    setNeutraintdata();
                    savebtn.setText("Update");
                    JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else{
            try {
                Ingredient_ID = sqlManager.saveingredientdata(isNew,code,name,scintiText,group_ID,type_ID,isActiveCheck,Ingredient_ID);
                if(Ingredient_ID>0)
                {
                    newBtn.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Updated Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jPanel2 = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        codeTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        newBtn = new javax.swing.JButton();
        groupComboBox = new javax.swing.JComboBox();
        typeComboBox = new javax.swing.JComboBox();
        nameTextField = new javax.swing.JTextField();
        isActiveCheckBox = new javax.swing.JCheckBox();
        scintiTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNameLabel)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Ingredient Information");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setNextFocusableComponent(codeTextField);
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 350));

        codeTextField.setNextFocusableComponent(nameTextField);
        codeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("Code");

        jLabel7.setText("Long Name");

        jLabel8.setText("Scientific Name");

        jLabel9.setText("Group");

        newBtn.setBackground(new java.awt.Color(182, 141, 142));
        newBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newBtn.setForeground(new java.awt.Color(255, 255, 255));
        newBtn.setText("New");
        newBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newBtn.setIconTextGap(5);
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        groupComboBox.setBackground(new java.awt.Color(255, 255, 255));

        typeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        typeComboBox.setForeground(new java.awt.Color(12, 22, 22));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });

        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        isActiveCheckBox.setSelected(true);
        isActiveCheckBox.setText("IsActive");
        isActiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isActiveCheckBoxActionPerformed(evt);
            }
        });

        scintiTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scintiTextFieldActionPerformed(evt);
            }
        });

        jLabel10.setText("Type");

        savebtn.setBackground(new java.awt.Color(182, 141, 142));
        savebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        savebtn.setForeground(new java.awt.Color(255, 255, 255));
        savebtn.setText("Save");
        savebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebtn.setIconTextGap(5);
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(groupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isActiveCheckBox)
                            .addComponent(scintiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(newBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(scintiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(groupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(isActiveCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newBtn)
                    .addComponent(savebtn))
                .addGap(63, 63, 63))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Nutrient", "Long Name", "Group", "Specification", "Overlay", "Analysis", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setGridColor(new java.awt.Color(241, 227, 227));
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(213, 237, 239));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(300);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(300);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(300);
            jTable1.getColumnModel().getColumn(7).setMinWidth(60);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(60);
        }

        jPanel5.setBackground(new java.awt.Color(70, 73, 85));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nutrient Data (Analysis Per Kg)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        homeBtn.setBackground(new java.awt.Color(255, 255, 255));
        homeBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(255, 255, 255));
        homeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/copy_2-32.png"))); // NOI18N
        homeBtn.setToolTipText("Copy Nutraint Data From Another Ingredients");
        homeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeBtn.setIconTextGap(5);
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        IngredientDetails ingredientDetails = new IngredientDetails(-1,tableListData);
        ingredientDetails.setVisible(true);
        savebtn.setText("Save");
       
    }//GEN-LAST:event_newBtnActionPerformed

    private void codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTextFieldActionPerformed

    private void scintiTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scintiTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scintiTextFieldActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeComboBoxActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_homeBtnActionPerformed

    private void saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveallbtnActionPerformed
        
        if(Ingredient_ID<1)
        {
            JOptionPane.showMessageDialog(null, "Nutrient not saved.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        
        TableCellEditor editor = jTable1.getCellEditor();
        if (editor != null)
        {
            editor.stopCellEditing();
            editor.cancelCellEditing();
        }
        else{
            //JOptionPane.showMessageDialog(null, "No change Found", "Error", JOptionPane.ERROR_MESSAGE);
            //return;
        }
        for(int i=0;i<nutrientListData.size();i++)
        {
            Vector tableline =(Vector) nutrientListData.get(i);
            
            Double specification=(Double)model1.getValueAt(i, 4); 
            tableline.set(6, specification);
            //if(specification!=null){
            //    tableline.set(6, specification);
            //}
            Double overlay=(Double)model1.getValueAt(i, 5); 
            tableline.set(7, overlay);
            //if(overlay!=null){
            //    tableline.set(7, overlay);
            //}
            if(overlay==null){
                if(specification!=null){
                    tableline.set(8, specification);
                }
                else{
                    tableline.set(8, -1.0);
                }
            }
            else {
                    tableline.set(8, overlay);
                }
            /*
            if(specification==null){
                if(overlay!=null){
                    tableline.set(8, overlay);
                }
                else{
                    tableline.set(8, -1.0);
                }
            }
            else if(overlay==null){
                if(specification!=null){
                    tableline.set(8, specification);
                }
                else{
                    tableline.set(8, -1.0);
                }
            }
            else{
                if(specification<overlay){
                    tableline.set(8, overlay);
                }
                else{
                    tableline.set(8, specification);
                }
            }  
            if(specification==null && overlay==null){
                tableline.set(8, -1.0);
            }
            */
        }
        try {
                boolean isSuccess=sqlManager.saveplantwisenutraintdata(nutrientListData,Ingredient_ID);
                if(isSuccess)
                {
                    setNeutraintdata();
                    JOptionPane.showMessageDialog(null, "Saved Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }//GEN-LAST:event_saveallbtnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE); 
    }//GEN-LAST:event_jTable1MouseClicked

    
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        
        
        
        
    }//GEN-LAST:event_jTable1KeyPressed

    private void isActiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isActiveCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isActiveCheckBoxActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
        saveIngredientData();
    }//GEN-LAST:event_savebtnActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
//        Ingredient IngredientForm = new Ingredient();
//        IngredientForm.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JComboBox groupComboBox;
    private javax.swing.JButton homeBtn;
    private javax.swing.JCheckBox isActiveCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newBtn;
    private javax.swing.JButton saveallbtn;
    private javax.swing.JButton savebtn;
    private javax.swing.JTextField scintiTextField;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}