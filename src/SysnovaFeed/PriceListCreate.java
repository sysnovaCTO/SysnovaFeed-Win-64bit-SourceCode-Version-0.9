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
public class PriceListCreate extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    boolean isNew=false;
    int PRICEWEEK_ID=0;
    ArrayList tableListData=new ArrayList();
    UtilDateModel modelleft=null;
    JDatePanelImpl datePanelleft=null;
    JDatePickerImpl datePickerleft=null;
    UtilDateModel modelRight=null;
    JDatePanelImpl datePanelRight=null;
    JDatePickerImpl datePickerRight=null;
    public PriceListCreate(int row,ArrayList dataList) {
        initComponents();
        PRICEWEEK_ID=0;
        userNameLabel.setText("");
        userNameLabel.setText(" "+sqlManager.getUserName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        //this.addWindowListener(exitListener);
        tableListData.clear();
        tableListData=dataList;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        modelleft = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        int year=1900+timestamp.getYear();
        //modelleft.setDate(year, timestamp.getMonth(), timestamp.getDate());
        //modelleft.setSelected(true);
        
        datePanelleft = new JDatePanelImpl(modelleft, p);
        datePickerleft = new JDatePickerImpl(datePanelleft, new DateLabelFormatter());
        datebtnleft.add(datePickerleft);
        
        modelRight = new UtilDateModel();
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        //modelRight.setDate(year, timestamp.getMonth(), timestamp.getDate());
        datePanelRight = new JDatePanelImpl(modelRight, p);
        datePickerRight = new JDatePickerImpl(datePanelRight, new DateLabelFormatter());
        dateBtnRight.add(datePickerRight);
        
        if(row<0){
            
        }else{
            setPRICEWEEKData(row);
        }
    }
//    WindowListener exitListener = new WindowAdapter() {
//        public void windowClosing(WindowEvent e) {            
//            PriceList PriceList = new PriceList();
//            PriceList.setVisible(true);
//        }
//    };
    public void setPRICEWEEKData(int row) {

        Vector tableline =(Vector) tableListData.get(row);
        PRICEWEEK_ID=(int) tableline.get(7);
        String cod = (String) tableline.get(0);
        codeTextField.setText(cod);
        String DESCRIPTION = (String) tableline.get(1);
        descTextField.setText(DESCRIPTION);

        String VALIDFROM = (String) tableline.get(3);
        //datePickerleft.setValue(LocalDate.now());
        if(VALIDFROM!=null){
            try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(VALIDFROM);
            int year=1900+date.getYear();
            modelleft.setDate(year, date.getMonth(), date.getDate());
            modelleft.setSelected(true);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String VALIDTO = (String) tableline.get(4);
        if(VALIDTO!=null){
            try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(VALIDTO);
            int year=1900+date.getYear();
            modelRight.setDate(year, date.getMonth(), date.getDate());
            modelRight.setSelected(true);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Boolean isact = (Boolean) tableline.get(2);
        isActiveCheckBox.setSelected(isact);
    }
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }
        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
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
        jLabel9 = new javax.swing.JLabel();
        descTextField = new javax.swing.JTextField();
        isActiveCheckBox = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        datebtnleft = new javax.swing.JButton();
        dateBtnRight = new javax.swing.JButton();
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userNameLabel)
                .addContainerGap(775, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNameLabel)
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Price List Setup");

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

        jLabel9.setText("Valid From");

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

        jLabel10.setText("Valid To");

        datebtnleft.setBackground(new java.awt.Color(255, 255, 255));
        datebtnleft.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        datebtnleft.setForeground(new java.awt.Color(255, 255, 255));
        datebtnleft.setText("New");
        datebtnleft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        datebtnleft.setEnabled(false);
        datebtnleft.setIconTextGap(5);
        datebtnleft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datebtnleftActionPerformed(evt);
            }
        });

        dateBtnRight.setBackground(new java.awt.Color(255, 255, 255));
        dateBtnRight.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        dateBtnRight.setForeground(new java.awt.Color(255, 255, 255));
        dateBtnRight.setText("New");
        dateBtnRight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dateBtnRight.setEnabled(false);
        dateBtnRight.setIconTextGap(5);
        dateBtnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBtnRightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datebtnleft, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(isActiveCheckBox)
                    .addComponent(descTextField)
                    .addComponent(dateBtnRight, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(dateBtnRight)
                    .addComponent(datebtnleft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(isActiveCheckBox)
                .addContainerGap(39, Short.MAX_VALUE))
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
                    .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
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
        String desc=descTextField.getText();
        if(desc.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Description", "Description Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        if(tableListData!=null)
        if(tableListData.size()>0)
            for(int i=0;i<tableListData.size();i++)
            {
                Vector tableline =(Vector) tableListData.get(i);

                int PRICEWEEK_id=(int) tableline.get(7);
                if(PRICEWEEK_id==PRICEWEEK_ID) continue;

                String cod = (String) tableline.get(0);
                if(code.compareTo(cod)==0){
                    JOptionPane.showMessageDialog(null, 
                     "Code Already Exits", "Code Error", JOptionPane.ERROR_MESSAGE);  
                    return;
                }
                String desc1 = (String) tableline.get(1);
                if(desc1.compareTo(desc)==0){
                    JOptionPane.showMessageDialog(null, 
                     "Description Already Exits", "Description Error", JOptionPane.ERROR_MESSAGE);  
                    return;
                }
            }
        boolean isActiveCheck=isActiveCheckBox.isSelected();
        Date selectedDateleft = (Date) datePickerleft.getModel().getValue();
        Date selectedDateright = (Date) datePickerRight.getModel().getValue();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String Dateleft = null;
        String Dateright = null;
        if(selectedDateleft!=null){
            Dateleft = formatter.format(selectedDateleft);
        }
         if(selectedDateright!=null){
            Dateright = formatter.format(selectedDateright);
        }       
        
        try {
            if(PRICEWEEK_ID<1){
                PRICEWEEK_ID = sqlManager.savePRICEWEEK(code,desc,Dateleft,Dateright,isActiveCheck,PRICEWEEK_ID);
                if(PRICEWEEK_ID>0)
                {
                    JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                }  
            }
            else{
                int id = sqlManager.savePRICEWEEK(code,desc,Dateleft,Dateright,isActiveCheck,PRICEWEEK_ID);
                if(id>0)
                {
                    JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
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
//        PriceList PriceList = new PriceList();
//        PriceList.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void datebtnleftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datebtnleftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datebtnleftActionPerformed

    private void dateBtnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateBtnRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateBtnRightActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JButton dateBtnRight;
    private javax.swing.JButton datebtnleft;
    private javax.swing.JTextField descTextField;
    private javax.swing.JCheckBox isActiveCheckBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton saveallbtn;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}