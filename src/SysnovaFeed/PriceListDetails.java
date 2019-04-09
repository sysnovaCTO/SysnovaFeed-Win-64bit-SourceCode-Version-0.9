package SysnovaFeed;


import java.awt.Color;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
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
public class PriceListDetails extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    boolean isNew=false;
    int PRICEWEEK_ID=0;
    ArrayList tableListData=new ArrayList();
    public PriceListDetails(int row,ArrayList dataList) {
        tableListData.clear();
        tableListData=dataList;
        initComponents();
        PRICEWEEK_ID=0;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
//        this.addWindowListener(exitListener);
        PriceListDetailsDataLoad(row);
        tableDetailsDataLoad();
        userNameLabel.setText("");
        userNameLabel.setText(" "+sqlManager.getUserName());
    }
    
//    WindowListener exitListener = new WindowAdapter() {
//        public void windowClosing(WindowEvent e) {
//            PriceList PriceList = new PriceList();
//            PriceList.setVisible(true);
//        }
//    };
    
    
     public void PriceListDetailsDataLoad(int row) {
        
        codeTextField.setText("");
        codeTextField.setEditable(false);
        descTextField.setText("");
        descTextField.setEditable(false);
        dateleft.setText("");
        dateleft.setEditable(false);
        dateright.setText("");
        dateright.setEditable(false);
        
        Vector tableline =(Vector) tableListData.get(row);
        PRICEWEEK_ID=(int) tableline.get(7);
        String cod = (String) tableline.get(0);
        codeTextField.setText(cod);
        String DESCRIPTION = (String) tableline.get(1);
        descTextField.setText(DESCRIPTION);
        
        String VALIDFROM = (String) tableline.get(3);
        dateleft.setText(VALIDFROM);
        String VALIDTO = (String) tableline.get(4);
        dateright.setText(VALIDTO);
     }
     
     
      public void tableDetailsDataLoad() {
       
        try {
            tableListData.clear();

            tableListData=sqlManager.getPriceListDetails(PRICEWEEK_ID);
            if(tableListData==null){
                JOptionPane.showMessageDialog(null, 
                "No Data Found", "Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }

            jTable1.removeAll();

            DefaultTableModel model1  = (DefaultTableModel)jTable1.getModel();

            model1.setRowCount(0);
            model1.setRowCount((tableListData.size()));
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer() );
            //jTable1.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer() );
            //jTable1.getColumnModel().getColumn(1).setCellRenderer(new CustomCellRenderer());
            
            //DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();


            for(int i=0;i<tableListData.size();i++)
            {        
                Vector tableline =(Vector) tableListData.get(i);
                String cod = (String) tableline.get(1);
                model1.setValueAt((Object)cod, i, 0);
                String name = (String) tableline.get(2);
                model1.setValueAt((Object)name, i, 1);
                double Price = (double) tableline.get(3);
                if(Price<0){
                    model1.setValueAt((Object)null, i, 2);
                }
                else{
                    model1.setValueAt((double)Price, i, 2);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PriceListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PriceListDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

     private class CustomCellRenderer extends DefaultTableCellRenderer {
         private  final DecimalFormat formatter = new DecimalFormat( "#0.000" );
        /* (non-Javadoc)
         * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
         
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
        DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );

        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
           row, column);
         return rendererComp ;
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
        descTextField = new javax.swing.JTextField();
        dateleft = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dateright = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                if(column < 2 ) return false;
                return true;
            }
        };
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
                .addContainerGap(932, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNameLabel)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Price Information");

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

        jLabel7.setText("Description");

        jLabel8.setText("Valid From");

        descTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTextFieldActionPerformed(evt);
            }
        });

        dateleft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateleftActionPerformed(evt);
            }
        });

        jLabel9.setText("Valid To");

        dateright.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daterightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateleft, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateright, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(descTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(dateleft, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(dateright, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Long Name", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(300);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(400);
        }

        jPanel5.setBackground(new java.awt.Color(70, 73, 85));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingredient Price");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
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
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTextFieldActionPerformed

    private void dateleftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateleftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateleftActionPerformed

    private void descTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descTextFieldActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_homeBtnActionPerformed

    private void saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveallbtnActionPerformed
       
        if(tableListData==null){
                JOptionPane.showMessageDialog(null, 
                "No Data Found", "Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }

        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        TableCellEditor editor = jTable1.getCellEditor();
        if (editor != null)
        {
            editor.stopCellEditing();
            editor.cancelCellEditing();
        }
        for(int i=0;i<tableListData.size();i++)
        {
            try {
                    Vector tableline =(Vector) tableListData.get(i);
                    try {
                        double pricedouble = (double)model1.getValueAt(i, 2);
                        //System.out.println(""+pricedouble);
                        tableline.set(3, pricedouble);
                    } 
                    catch (Exception e) {
                        String price=(String)model1.getValueAt(i, 2);
                        //System.out.println(""+price);
                            if(price==null){
                                tableline.set(3, null);
                                continue;
                        }
                        try {
                            double pricedouble = Double.parseDouble(price);
                            tableline.set(3, pricedouble);
                        } catch (Exception e1) {
                            tableline.set(3, null);
                        }
                    }
                } catch (Exception e) {
                }
            
        }
        
        try {
                boolean isSuccess=sqlManager.saveplantwisePrice(tableListData,PRICEWEEK_ID);
                if(isSuccess)
                {
                    tableDetailsDataLoad();
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

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
//        PriceList PriceList = new PriceList();
//        PriceList.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void daterightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daterightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daterightActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JTextField dateleft;
    private javax.swing.JTextField dateright;
    private javax.swing.JTextField descTextField;
    private javax.swing.JButton homeBtn;
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
    private javax.swing.JButton saveallbtn;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}

