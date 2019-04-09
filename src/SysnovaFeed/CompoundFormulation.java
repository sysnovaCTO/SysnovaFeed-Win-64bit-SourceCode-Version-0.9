package SysnovaFeed;



import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class CompoundFormulation extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */

    int Compound_id=0;
    ArrayList tableListData=new ArrayList();
    ArrayList ingredientData=new ArrayList();
    ArrayList ingredientDeleteData=new ArrayList();
    ArrayList nutrientData=new ArrayList();
    ArrayList nutrientDeleteData=new ArrayList();
    int lineno=0;
    int ingredientjTablerow = -1;
    int ingredientjTablecolumn = -1;
    int nutrientsTablerow=0;
    ArrayList priceList=new ArrayList();
    int priceweek_ID=-1;
    int week_ID=-1;
    public CompoundFormulation(int row,ArrayList tableList,ArrayList ingredientList,ArrayList nutrientsList
    ,ArrayList ingredientDeleteList,ArrayList nutrientsDeleteList,int price_ID) {
        //int priceweek_ID=-1;
        priceweek_ID=price_ID;
        week_ID=price_ID;
        Compound_id=0;
        nutrientsTablerow=-1;
        ingredientjTablerow = -1;
        ingredientDeleteData.clear();
        nutrientDeleteData.clear();
        ingredientData.clear();
        nutrientData.clear();
        if(ingredientList!=null)
            ingredientData=ingredientList;
        if(ingredientDeleteList!=null)
            ingredientDeleteData=ingredientDeleteList;
        if(nutrientsDeleteList!=null)
            nutrientDeleteData=nutrientsDeleteList;
        if(nutrientsList!=null)
            nutrientData=nutrientsList;
        tableListData.clear();
        tableListData=tableList;
        lineno=row;
        initComponents();
        saveallbtn.setEnabled(true);
        userNameLabel.setText("");
        userNameLabel.setText(" "+sqlManager.getUserName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
//        this.addWindowListener(exitListener);
        CompoundWork( row);
        priceList.clear();
        try {
            priceList=sqlManager.gepriceListPropertyComboodata();
            priceListComboPanel(priceList);
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<priceList.size();i++)
        {
                Vector line =(Vector) priceList.get(i);
                int price_ID1 = (Integer) line.get(0);
                if(priceweek_ID==price_ID1){
                    priceListComboBox.setSelectedIndex(i);
                }
               
        }
    JMenuItem item = new JMenuItem("Add");
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        ingredientsPopup ingredientsAll = new ingredientsPopup( lineno,tableListData,ingredientData,nutrientData,ingredientDeleteData,nutrientDeleteData,priceweek_ID);
        ingredientsAll.setLocation(100, 100);
        ingredientsAll.setVisible(true);
      }
    });
    menu.add(item);
    item = new JMenuItem("Delete ");
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Menu Delete");
        if(ingredientData.size()<1){
            return;
        }
        if(ingredientjTablerow==-1){
            JOptionPane.showMessageDialog(null, "No Ingredient Row Delected", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
           Vector tableline =(Vector) ingredientData.get(ingredientjTablerow);
           int COMPOUNDINGREDIENT_ID=(Integer) tableline.get(8);
           if(COMPOUNDINGREDIENT_ID>0){
               ingredientDeleteData.add(COMPOUNDINGREDIENT_ID);
               saveallbtn.setEnabled(true);
           }
           getIngredientDatafromTable();
           ingredientData.remove(ingredientjTablerow);
           CompoundIngredientsData();
        }
        
      }
    });
    menu.add(item);
    ingredientjTable.setComponentPopupMenu(menu);
    ingredientjTable.addMouseListener( new MouseAdapter()
    {
        public void mouseReleased(MouseEvent e)
        {
            JTable source = (JTable)e.getSource();
            ingredientjTablerow = source.rowAtPoint( e.getPoint() );
            ingredientjTablecolumn = source.columnAtPoint( e.getPoint() );
            saveallbtn.setEnabled(true);
            isAnalysybtn=true;
            if (! source.isRowSelected(ingredientjTablerow))
                source.changeSelection(ingredientjTablerow, ingredientjTablecolumn, false, false);
            if(analysisbtn.isEnabled() && isAnalysybtn){
                Vector tableline =(Vector) tableListData.get(lineno);
                int SOLUTION_ID=(int) tableline.get(21);
                //if(SOLUTION_ID==2)
                {
                    setAnalysisIngredientTableData(ingredientjTablerow);
                }
            }
        }
    });
    
    
    JMenuItem item1 = new JMenuItem("Add");
    item1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        nutrientPopup nutrientPopup = new nutrientPopup( lineno,tableListData,ingredientData,nutrientData,ingredientDeleteData,nutrientDeleteData,priceweek_ID );
        nutrientPopup.setLocation(100, 100);
        nutrientPopup.setVisible(true);
      }
    });
    menu1.add(item1);
    item1 = new JMenuItem("Delete ");
    item1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if(nutrientData.size()<1){
            return;
        }
        if(nutrientsTablerow==-1){
            JOptionPane.showMessageDialog(null, "No Nutrient Row Delected", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
           Vector tableline =(Vector) nutrientData.get(nutrientsTablerow);
           int COMPOUNDnutrient_ID=(Integer) tableline.get(8);
           if(COMPOUNDnutrient_ID>0){
               nutrientDeleteData.add(COMPOUNDnutrient_ID);
               saveallbtn.setEnabled(true);
           }
           getNutrientDatafromTable();
            nutrientData.remove(nutrientsTablerow);
            Compoundnutrientsdata();
        }
      }
    });
    menu1.add(item1);
    nutrientsTable.setComponentPopupMenu(menu1);
    nutrientsTable.addMouseListener( new MouseAdapter()
    {
        public void mouseReleased(MouseEvent e)
        {
            JTable source = (JTable)e.getSource();
            nutrientsTablerow = source.rowAtPoint( e.getPoint() );
            int column = source.columnAtPoint( e.getPoint() );
            saveallbtn.setEnabled(true);
            isAnalysybtn=true;
            if (! source.isRowSelected(nutrientsTablerow))
                source.changeSelection(nutrientsTablerow, column, false, false);
            if(analysisbtn.isEnabled() && isAnalysybtn){
                Vector tableline =(Vector) tableListData.get(lineno);
                int SOLUTION_ID=(int) tableline.get(21);
               // if(SOLUTION_ID==2)
                {
                    setAnalysisTableData(nutrientsTablerow);
                }
            }
            
            
        }
    });
    priceListComboBox.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            int price_Index=priceListComboBox.getSelectedIndex();
            Vector price =(Vector) priceList.get(price_Index);
            priceweek_ID = (Integer) price.get(0); 
            saveallbtn.setEnabled(true);
            HashMap priceMap=new HashMap();
            priceMap.clear();
            if(priceweek_ID>0){
                try {
                    priceMap=sqlManager.getweekpriceata(priceweek_ID);
                } catch (SQLException ex) {
                    Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            for(int i=0;i<ingredientData.size();i++)
            {                    
                Vector tableline1 =(Vector) ingredientData.get(i);
                int INGREDIENT_ID=(Integer) tableline1.get(0);
                if(priceMap.containsKey(INGREDIENT_ID)){
                    try {
                        double price1 =(double)priceMap.get(INGREDIENT_ID);
                        tableline1.set(3, price1);
                    } catch (Exception e1) {
                        tableline1.set(3, -1.0);
                    }
                }
                else{
                    tableline1.set(3, -1.0);
                }
            }
            CompoundIngredientsData();
        }
        });
        
    }
    
  JPopupMenu menu = new JPopupMenu("Popup");
  JPopupMenu menu1 = new JPopupMenu("Popup");

  class MyLabel extends JLabel {
    public MyLabel(String text) {
      super(text);
      addMouseListener(new PopupTriggerListener());
    }

    class PopupTriggerListener extends MouseAdapter {
      public void mousePressed(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          menu.show(ev.getComponent(), ev.getX(), ev.getY());
          saveallbtn.setEnabled(true);
        }
      }

      public void mouseReleased(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
          menu.show(ev.getComponent(), ev.getX(), ev.getY());
          saveallbtn.setEnabled(true);
        }
      }

      public void mouseClicked(MouseEvent ev) {
      }
    }
  }
  public void  setAnalysisTableData(int nutrientsTablerow){
      ArrayList analysisData=new ArrayList();
        if(nutrientData.size()<1){
            JOptionPane.showMessageDialog(null, "Please Provide Compound Nutrient And Run Formulation", "Error", JOptionPane.ERROR_MESSAGE);

        }
      Vector tableline =(Vector) nutrientData.get(nutrientsTablerow);
        int NUTRIENT_ID=(int) tableline.get(0);
        double nutrientvalue=0;
        try {
            if(tableline.get(5)!=null){
                    nutrientvalue = (double) tableline.get(5);
            }
            analysisData=sqlManager.getNutrientAnalysisdata(Compound_id,NUTRIENT_ID,nutrientvalue);
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      if(analysisData!=null){
          
          String cod = (String) tableline.get(1);
          String name = (String) tableline.get(2);
          String sym = (String) tableline.get(3);
          
          nutrientAnalysisLbl.setText("Analysis: "+cod+" "+name+" "+nutrientvalue+" "+sym);
          analysisjTable.removeAll();
            DefaultTableModel model1 = (DefaultTableModel)analysisjTable.getModel();
            model1.setRowCount(0);   
            model1.setColumnCount(0);
            if(analysisData.size()<1){
                return;
                //model1.setRowCount(10);   
            }
            else{
                model1.setRowCount(analysisData.size());
            }
            //model1.add
            model1.addColumn("Code");
            model1.addColumn("Ingredient");
            model1.addColumn("%");
            model1.addColumn("Analysis");
            model1.addColumn("Value");
            model1.addColumn("Nutrient%");
            
            TableColumnModel columnModel = analysisjTable.getColumnModel();
            
            columnModel.getColumn(0).setMinWidth(50);
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(0).setMaxWidth(180);
            columnModel.getColumn(1).setMinWidth(180);
            columnModel.getColumn(1).setPreferredWidth(180);
            columnModel.getColumn(1).setMaxWidth(350);

            analysisjTable.getColumnModel().getColumn(2).setCellRenderer(new DecimalFormatRenderer() );
            analysisjTable.getColumnModel().getColumn(3).setCellRenderer(new DecimalFormatRenderer() );
            analysisjTable.getColumnModel().getColumn(4).setCellRenderer(new DecimalFormatRenderer() );
            analysisjTable.getColumnModel().getColumn(5).setCellRenderer(new DecimalFormatRenderer() );

            for(int i=0;i<analysisData.size();i++)
            {                    
                Vector tableline1 =(Vector) analysisData.get(i);
                String code = (String) tableline1.get(6);
                model1.setValueAt((Object)code, i, 0);
                
                String name1 = (String) tableline1.get(1);
                model1.setValueAt((Object)name1, i, 1);
                
                if(tableline1.get(2)!=null){
                    double value = (double) tableline1.get(2);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 2);
                  else 
                        model1.setValueAt((Object)null, i, 2);
                }else{
                    model1.setValueAt((Object)null, i, 2);  
                }
                if(tableline1.get(3)!=null){
                    double value = (double) tableline1.get(3);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 3);
                  else 
                        model1.setValueAt((Object)null, i, 3);
                }else{
                    model1.setValueAt((Object)null, i, 3);  
                }
                if(tableline1.get(4)!=null){
                    double value = (double) tableline1.get(4);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 4);
                  else 
                        model1.setValueAt((Object)null, i, 4);
                }else{
                    model1.setValueAt((Object)null, i, 4);  
                }
                if(tableline1.get(5)!=null){
                    double value = (double) tableline1.get(5);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 5);
                  else 
                        model1.setValueAt((Object)null, i, 5);
                }else{
                    model1.setValueAt((Object)null, i, 5);  
                }
            } 
        }
      
  }
  public void  setAnalysisIngredientTableData(int ingredientjTablerow){
      ArrayList analysisData=new ArrayList();
        if(ingredientData.size()<1){
            JOptionPane.showMessageDialog(null, "Please Provide Compound Ingredient And Run Formulation", "Error", JOptionPane.ERROR_MESSAGE);

        }
      Vector tableline =(Vector) ingredientData.get(ingredientjTablerow);
        int Ingredient_ID=(int) tableline.get(0);
        try {
            analysisData=sqlManager.getIngredientAnalysisdata(Compound_id,Ingredient_ID);
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      if(analysisData!=null){
          
          String cod = (String) tableline.get(1);
          String name = (String) tableline.get(2);
          
          nutrientAnalysisLbl.setText("Analysis: "+cod+" "+name);
          analysisjTable.removeAll();
            DefaultTableModel model1 = (DefaultTableModel)analysisjTable.getModel();
            model1.setRowCount(0);   
            model1.setColumnCount(0);
            if(analysisData.size()<1){
                return;
                //model1.setRowCount(10);   
            }
            else{
                model1.setRowCount(analysisData.size());
            }
            //model1.add
            model1.addColumn("Code");
            model1.addColumn("Nutrient");
            model1.addColumn("Unit");
            model1.addColumn("Analysis");
            model1.addColumn("Value");
            
            TableColumnModel columnModel = analysisjTable.getColumnModel();
            
            columnModel.getColumn(0).setMinWidth(80);
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(0).setMaxWidth(180);
            columnModel.getColumn(1).setMinWidth(180);
            columnModel.getColumn(1).setPreferredWidth(180);
            columnModel.getColumn(1).setMaxWidth(350);

            //analysisjTable.getColumnModel().getColumn(2).setCellRenderer(new DecimalFormatRenderer() );
            analysisjTable.getColumnModel().getColumn(3).setCellRenderer(new DecimalFormatRenderer() );
            analysisjTable.getColumnModel().getColumn(4).setCellRenderer(new DecimalFormatRenderer() );
            //analysisjTable.getColumnModel().getColumn(5).setCellRenderer(new DecimalFormatRenderer() );

            for(int i=0;i<analysisData.size();i++)
            {                    
                Vector tableline1 =(Vector) analysisData.get(i);
                String code = (String) tableline1.get(1);
                model1.setValueAt((Object)code, i, 0);
                
                String name1 = (String) tableline1.get(2);
                model1.setValueAt((Object)name1, i, 1);
                
                String sym = (String) tableline1.get(3);
                model1.setValueAt((Object)sym, i, 2);
                
                if(tableline1.get(4)!=null){
                    double value = (double) tableline1.get(4);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 3);
                  else 
                        model1.setValueAt((Object)null, i, 3);
                }else{
                    model1.setValueAt((Object)null, i, 3);  
                }
                if(tableline1.get(5)!=null){
                    double value = (double) tableline1.get(5);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 4);
                  else 
                        model1.setValueAt((Object)null, i, 4);
                }else{
                    model1.setValueAt((Object)null, i, 4);  
                }
                
            } 
        }
      
  }
  
  public void priceListComboPanel(ArrayList price_List) {
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) priceListComboBox.getModel();
        // removing old data
        model.removeAllElements();

        for(int i=0;i<price_List.size();i++)
        {
                Vector line =(Vector) price_List.get(i);
                int price_List_ID = (Integer) line.get(0);
                String name = (String) line.get(1);
                model.addElement(name);
        }
        priceListComboBox.setModel(model); 
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
//            Compound Compound = new Compound();
//            Compound.setVisible(true);
//        }
//    };
    
    
    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
      private static final DecimalFormat formatter = new DecimalFormat( "#0.000" );
 
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
        table.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );

        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
           row, column);
         return rendererComp ;
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
         //if(row==1)
            //rendererComp .setBackground(Color.decode("#f1fbf1"));
            DecimalFormat formatter = new DecimalFormat( "#0.000" );
            rendererComp.setBackground(Color.white);
            try {
                    if(value!=null){
                        try{
                           value = formatter.format((Number)value);
                        } 
                        catch(Exception e){
                        } 
                    }
                Vector tableline1 =(Vector) ingredientData.get(row);
                
                if(tableline1.get(4)!=null && tableline1.get(5)!=null)
                    if(tableline1.get(4)!=null && tableline1.get(5)!=null)
                {
                    double minval = (double) tableline1.get(4);
                    double val = (double) tableline1.get(5);
                    if (minval>val && minval!=-1&& val!=-1) {
                        rendererComp.setBackground(Color.green);
                   }
                }
                if(tableline1.get(6)!=null && tableline1.get(5)!=null){
                    double maxvalue = (double) tableline1.get(6);
                    double val = (double) tableline1.get(5);
                    if (maxvalue<val && maxvalue!=-1&& val!=-1) {
                        rendererComp.setBackground(Color.green);
                   }
                } 
            } catch (Exception e) {
            }
            DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
            table.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
            
            Component rendererComp1 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
            row, column);
         return rendererComp1 ;
        }

    }
    private class CustomCellRendererNutrient extends DefaultTableCellRenderer {

        /* (non-Javadoc)
         * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table, Object value,
          boolean isSelected, boolean hasFocus, int row, int column) {

         Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
         row, column);
         //if(row==1)
            //rendererComp .setBackground(Color.decode("#f1fbf1"));
            DecimalFormat formatter = new DecimalFormat( "#0.000" );
            rendererComp.setBackground(Color.white);
            try {
                if(value!=null){
                        try{
                           value = formatter.format((Number)value);
                        } 
                        catch(Exception e){
                        } 
                    }
                Vector tableline1 =(Vector) nutrientData.get(row);
                
                if(tableline1.get(4)!=null && tableline1.get(5)!=null)
                    if(tableline1.get(4)!=null && tableline1.get(5)!=null)
                {
                    double minval = (double) tableline1.get(4);
                    double val = (double) tableline1.get(5);
                    if (minval>val && minval!=-1&& val!=-1) {
                        rendererComp.setBackground(Color.green);
                   }
                }
                if(tableline1.get(6)!=null && tableline1.get(5)!=null){
                    double maxvalue = (double) tableline1.get(6);
                    double val = (double) tableline1.get(5);
                    if (maxvalue<val && maxvalue!=-1&& val!=-1) {
                        rendererComp.setBackground(Color.green);
                   }
                } 
            } catch (Exception e) {
            }
        DefaultTableCellRenderer rightRenderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        table.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
         Component rendererComp1 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
            row, column);
         return rendererComp1 ;
        }

    }
    public void CompoundIngredientsData()
    {
        if(ingredientData!=null){
            DefaultTableModel model1 = (DefaultTableModel)ingredientjTable.getModel();
            model1.setRowCount(0);   
            if(ingredientData.size()<1){
                 model1.setRowCount(15);   
            }
            else
                model1.setRowCount(ingredientData.size());

            
            
            ingredientjTable.getColumnModel().getColumn(2).setCellRenderer(new DecimalFormatRenderer() );
            ingredientjTable.getColumnModel().getColumn(3).setCellRenderer(new DecimalFormatRenderer() );
            ingredientjTable.getColumnModel().getColumn(4).setCellRenderer(new DecimalFormatRenderer() );
            ingredientjTable.getColumnModel().getColumn(5).setCellRenderer(new DecimalFormatRenderer() );
            ingredientjTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRenderer() );
            
            
            
            for(int i=0;i<ingredientData.size();i++)
            {                    
                Vector tableline1 =(Vector) ingredientData.get(i);
                String cod1 = (String) tableline1.get(1);
                //System.out.println(""+cod1);
                model1.setValueAt((Object)cod1, i, 0);
                String name1 = (String) tableline1.get(2);
                model1.setValueAt((Object)name1, i, 1);
                if(tableline1.get(3)!=null){
                  double price = (double) tableline1.get(3);
                  if(price!=-1)
                    model1.setValueAt((Object)price, i, 2);
                  else model1.setValueAt((Object)null, i, 2);
                      
                }
                else{
                    model1.setValueAt((Object)null, i, 2);  
                }
                if(tableline1.get(4)!=null){
                    double minval = (double) tableline1.get(4);
                    if(minval!=-1)
                    model1.setValueAt((Object)minval, i, 3);
                  else 
                        model1.setValueAt((Object)null, i, 3);
                }else{
                    model1.setValueAt((Object)null, i, 3);  
                }
                if(tableline1.get(5)!=null){
                    double value = (double) tableline1.get(5);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 4);
                  else 
                        model1.setValueAt((Object)null, i, 4);
                }else{
                    model1.setValueAt((Object)null, i, 4);  
                }
                if(tableline1.get(6)!=null){
                    double maxval = (double) tableline1.get(6);
                    if(maxval!=-1)
                    model1.setValueAt((Object)maxval, i, 5);
                  else 
                        model1.setValueAt((Object)null, i, 5); 
                }else{
                    model1.setValueAt((Object)null, i, 5);  
                }
            } 
        }
    }
    
    public void Compoundnutrientsdata(){
        if(nutrientData!=null){
            DefaultTableModel model1 = (DefaultTableModel)nutrientsTable.getModel();
            model1.setRowCount(0);   
            if(nutrientData.size()<1){
                 model1.setRowCount(15);   
            }
            else
                model1.setRowCount(nutrientData.size());
            nutrientsTable.getColumnModel().getColumn(3).setCellRenderer(new DecimalFormatRenderer() );
            nutrientsTable.getColumnModel().getColumn(4).setCellRenderer(new DecimalFormatRenderer() );
            nutrientsTable.getColumnModel().getColumn(5).setCellRenderer(new DecimalFormatRenderer() );
            nutrientsTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCellRendererNutrient() );
            for(int i=0;i<nutrientData.size();i++)
            {                    
                Vector tableline1 =(Vector) nutrientData.get(i);
                String cod1 = (String) tableline1.get(1);
                //System.out.println(""+cod1);
                model1.setValueAt((Object)cod1, i, 0);
                String name1 = (String) tableline1.get(2);
                model1.setValueAt((Object)name1, i, 1);
                
                String unit = (String) tableline1.get(3);
                model1.setValueAt((Object)unit, i, 2);
                
                if(tableline1.get(4)!=null){
                  double minval = (double) tableline1.get(4);
                  if(minval!=-1)
                    model1.setValueAt((Object)minval, i, 3);
                  else model1.setValueAt((Object)null, i, 3);
                      
                }
                else{
                    model1.setValueAt((Object)null, i, 3);  
                }
                if(tableline1.get(5)!=null){
                    double value = (double) tableline1.get(5);
                    if(value!=-1)
                    model1.setValueAt((Object)value, i, 4);
                  else 
                        model1.setValueAt((Object)null, i, 4);
                }else{
                    model1.setValueAt((Object)null, i, 4);  
                }
                if(tableline1.get(6)!=null){
                    double maxValue = (double) tableline1.get(6);
                    if(maxValue!=-1)
                    model1.setValueAt((Object)maxValue, i, 5);
                  else 
                        model1.setValueAt((Object)null, i, 5);
                }else{
                    model1.setValueAt((Object)null, i, 5);  
                }
                
            } 
        }
    }
    public void CompoundWork(int row) {
        
        Vector tableline =(Vector) tableListData.get(row);
        Compound_id=(int) tableline.get(11);
        try {
            if(ingredientData.size()<1){
                ingredientData=sqlManager.getCompoundingredientdata(Compound_id);
            }
            if(nutrientData.size()<1){
                nutrientData=sqlManager.getCompoundnutrientdata(Compound_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cod = (String) tableline.get(0);
        //codeLabel.setText(cod);
        String name = (String) tableline.get(1);
        //nameLabel.setText(name);
        jLabel4.setText("("+cod+") "+name+" Ingredients");//Ingredients
        double Total_formuated=(double) tableline.get(18);
        BigDecimal Total_formuatedvalue=(BigDecimal.valueOf(Total_formuated));
        Total_formuatedvalue=Total_formuatedvalue.multiply(new BigDecimal("10"));
        Total_formuatedvalue=Total_formuatedvalue.setScale(3, RoundingMode.UP);
        Solutionlbl.setForeground(Color.black);
        if(Total_formuatedvalue.compareTo(new BigDecimal("1000"))!=0){
            Solutionlbl.setForeground(Color.red);
        }
        Solutionlbl.setText("Formulated for: "+Total_formuatedvalue+" kg");
        double Optimized_Price=(double) tableline.get(19);
        double Previous_Price=(double) tableline.get(20);
        
        if(Optimized_Price<Previous_Price){
            double res=Optimized_Price-Previous_Price;
            BigDecimal value=(BigDecimal.valueOf(res));
            value=value.setScale(3, RoundingMode.UP);
            Previous_PriceLBL.setText ("Previous  Price : "+Previous_Price+" /kg ("+value+")");
            Optimized_Pricelbl.setText("Optimized Price: "+Optimized_Price+" /kg (+0.00)");
        }
        else{
            double res=Optimized_Price-Previous_Price;
            BigDecimal value=(BigDecimal.valueOf(res));
            value=value.setScale(3, RoundingMode.UP);
            Previous_PriceLBL.setText("Previous  Price : "+Previous_Price+"/kg (-0.00)");
            Optimized_Pricelbl.setText("Optimized Price: "+Optimized_Price+"/kg (+"+value+")");
        }

        int SOLUTION_ID=(int) tableline.get(21);
        analysisjTable.setVisible(false);
        analysisjTable.removeAll();
        DefaultTableModel model1 = (DefaultTableModel)analysisjTable.getModel();
        model1.setRowCount(0);   
        model1.setColumnCount(0);
        nutrientAnalysisLbl.setVisible(false);
        analysisbtn.setEnabled(true);
        isAnalysybtn=false;
        reportbtn.setEnabled(false);
        if(SOLUTION_ID==-1){
            formulatedlbl.setText("Solution Not Prepared");
            formulatedlbl.setForeground(Color.pink);
            analysisbtn.setEnabled(true);
        }
        else if(SOLUTION_ID==2){
            analysisbtn.setEnabled(true);
            reportbtn.setEnabled(true);
            formulatedlbl.setText("SOLUTION OPTIMAL");
            formulatedlbl.setForeground(Color.GREEN);
        }
        else{
            formulatedlbl.setText("SOLUTION NOT OPTIMAL");
            formulatedlbl.setForeground(Color.red);
        }
                        
        CompoundIngredientsData();
        Compoundnutrientsdata();
         
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
        jScrollPane1 = new javax.swing.JScrollPane();
        nutrientsTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        saveallbtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        analysisjTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        ingredientjTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        nutrientAnalysisLbl = new javax.swing.JLabel();
        priceListComboBox = new javax.swing.JComboBox();
        formulaBtn = new javax.swing.JButton();
        Previous_PriceLBL = new javax.swing.JLabel();
        Optimized_Pricelbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Solutionlbl = new javax.swing.JLabel();
        formulatedlbl = new javax.swing.JLabel();
        analysisbtn = new javax.swing.JButton();
        reportbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SysnovaFEED");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(254, 254, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 470));

        jPanel2.setBackground(new java.awt.Color(57, 63, 63));

        userNameLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(232, 245, 245));
        userNameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/profile.png"))); // NOI18N
        userNameLabel.setText("  ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userNameLabel)
                .addContainerGap(937, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userNameLabel)
        );

        nutrientsTable.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        nutrientsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Nutrient", "Unit", "Minimum", "Value", "Maximum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nutrientsTable.setGridColor(new java.awt.Color(241, 227, 227));
        nutrientsTable.setRowHeight(20);
        nutrientsTable.setSelectionBackground(new java.awt.Color(213, 237, 239));
        nutrientsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nutrientsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nutrientsTableMouseClicked(evt);
            }
        });
        nutrientsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nutrientsTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(nutrientsTable);
        if (nutrientsTable.getColumnModel().getColumnCount() > 0) {
            nutrientsTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            nutrientsTable.getColumnModel().getColumn(0).setMaxWidth(80);
            nutrientsTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            nutrientsTable.getColumnModel().getColumn(2).setMaxWidth(60);
            nutrientsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            nutrientsTable.getColumnModel().getColumn(3).setMaxWidth(80);
            nutrientsTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            nutrientsTable.getColumnModel().getColumn(4).setMaxWidth(80);
            nutrientsTable.getColumnModel().getColumn(5).setPreferredWidth(60);
            nutrientsTable.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jPanel5.setBackground(new java.awt.Color(70, 73, 85));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Compound Ingredients");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        homeBtn.setToolTipText("Copy Data From Another Compound");
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

        jLabel12.setText("Price List");

        jPanel6.setBackground(new java.awt.Color(70, 73, 85));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Compound Nutrients");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        analysisjTable.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        analysisjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        analysisjTable.setGridColor(new java.awt.Color(241, 227, 227));
        jScrollPane3.setViewportView(analysisjTable);

        ingredientjTable.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        ingredientjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Ingredient", "Price", "Minimum", "Value", "Maximum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingredientjTable.setGridColor(new java.awt.Color(241, 227, 227));
        ingredientjTable.setRowHeight(20);
        ingredientjTable.setSelectionBackground(new java.awt.Color(213, 237, 239));
        ingredientjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ingredientjTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ingredientjTableAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane4.setViewportView(ingredientjTable);
        if (ingredientjTable.getColumnModel().getColumnCount() > 0) {
            ingredientjTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            ingredientjTable.getColumnModel().getColumn(0).setMaxWidth(80);
            ingredientjTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            ingredientjTable.getColumnModel().getColumn(2).setMaxWidth(60);
            ingredientjTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            ingredientjTable.getColumnModel().getColumn(3).setMaxWidth(60);
            ingredientjTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            ingredientjTable.getColumnModel().getColumn(4).setMaxWidth(60);
            ingredientjTable.getColumnModel().getColumn(5).setPreferredWidth(60);
            ingredientjTable.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        jPanel8.setBackground(new java.awt.Color(70, 73, 85));

        nutrientAnalysisLbl.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        nutrientAnalysisLbl.setForeground(new java.awt.Color(255, 255, 255));
        nutrientAnalysisLbl.setText("Nutrient Analysis");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nutrientAnalysisLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nutrientAnalysisLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        priceListComboBox.setBackground(new java.awt.Color(255, 255, 255));
        priceListComboBox.setAutoscrolls(true);
        priceListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceListComboBoxActionPerformed(evt);
            }
        });

        formulaBtn.setBackground(new java.awt.Color(255, 255, 255));
        formulaBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        formulaBtn.setForeground(new java.awt.Color(255, 255, 255));
        formulaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/formula2.png"))); // NOI18N
        formulaBtn.setToolTipText("Formulaton");
        formulaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        formulaBtn.setIconTextGap(5);
        formulaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formulaBtnActionPerformed(evt);
            }
        });

        Previous_PriceLBL.setText("Previous Price");

        Optimized_Pricelbl.setText("Optimized Price");

        jLabel13.setText("Batch Size: 1000 kg");

        Solutionlbl.setText("Formulated for:");

        formulatedlbl.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        formulatedlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        formulatedlbl.setText("Solution");
        formulatedlbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        formulatedlbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        analysisbtn.setBackground(new java.awt.Color(255, 255, 255));
        analysisbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        analysisbtn.setForeground(new java.awt.Color(255, 255, 255));
        analysisbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/tree.png"))); // NOI18N
        analysisbtn.setToolTipText("Compound Analysis");
        analysisbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analysisbtn.setIconTextGap(5);
        analysisbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analysisbtnActionPerformed(evt);
            }
        });

        reportbtn.setBackground(new java.awt.Color(255, 255, 255));
        reportbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        reportbtn.setForeground(new java.awt.Color(255, 255, 255));
        reportbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Report.png"))); // NOI18N
        reportbtn.setToolTipText("Report");
        reportbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportbtn.setIconTextGap(5);
        reportbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportbtnActionPerformed(evt);
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(Solutionlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(formulaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(analysisbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reportbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Previous_PriceLBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Optimized_Pricelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(priceListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(formulatedlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(formulaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(formulatedlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Previous_PriceLBL)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Optimized_Pricelbl))
                                    .addComponent(reportbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(analysisbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(Solutionlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(31, 31, 31))
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

    private void formulaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formulaBtnActionPerformed
        // TODO add your handling code here:
        try {
            boolean isSuccess1 =false;
            boolean isSuccess2 =false;
            if(priceweek_ID!=week_ID){
                isSuccess1=sqlManager.updateCompoundpriceweek(Compound_id,priceweek_ID);
                if(!isSuccess1)
                {
                    JOptionPane.showMessageDialog(null, "PriseList Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if(ingredientData.size()>0)
            {
                getIngredientDatafromTable();
                isSuccess1=sqlManager.saveCompoundIngredient(ingredientData,Compound_id);
                if(!isSuccess1)
                {
                    JOptionPane.showMessageDialog(null, "ingredient Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "ingredient Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(nutrientData.size()>0){
                getNutrientDatafromTable();
                isSuccess2=sqlManager.saveCompoundNutrient(nutrientData,Compound_id);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Nutrient Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nutrient Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(ingredientDeleteData.size()>0){
                isSuccess2=sqlManager.deleteCompoundingredient(ingredientDeleteData);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Ingredient Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(nutrientDeleteData.size()>0){
                isSuccess2=sqlManager.deleteCompoundnutrient(nutrientDeleteData);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Nutrient Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            //ingredientData.clear();
            //nutrientData.clear();
            ingredientDeleteData.clear();
            nutrientDeleteData.clear();
            //CompoundWork(lineno);
            saveallbtn.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            for(int i=0;i<ingredientData.size();i++)
            {
                Vector tableline1 =(Vector) ingredientData.get(i);
                if(tableline1.get(3)==null)
                {
                    String code = (String) tableline1.get(1);
                    String name = (String) tableline1.get(2);
                    JOptionPane.showMessageDialog(null, "Ingredient Price Not Found.\nRow no: "+(i+1)+" "
                        + "\nIngredient Code: "+code+"\nIngredient Name: "+name, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    double price = (double) tableline1.get(3);
                    if(price<1){
                        String code = (String) tableline1.get(1);
                        String name = (String) tableline1.get(2);
                        JOptionPane.showMessageDialog(null, "Negetive/Zreo Price Not Allow.\nRow no: "+(i+1)+" "
                            + "\nIngredient Code: "+code+"\nIngredient Name: "+name, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if(tableline1.get(4)!=null && tableline1.get(6)!=null)
                if(tableline1.get(4)!=null && tableline1.get(6)!=null)
                {
                    double minval = (double) tableline1.get(4);
                    double maxval = (double) tableline1.get(6);
                    if (minval>maxval) {
                        String code = (String) tableline1.get(1);
                        String name = (String) tableline1.get(2);
                        JOptionPane.showMessageDialog(null, "Ingredient Max Value Error.\nRow no: "+(i+1)+" "
                            + "\nIngredient Code: "+code+"\nIngredient Name: "+name, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            for(int i=0;i<nutrientData.size();i++)
            {
                Vector tableline1 =(Vector) nutrientData.get(i);
                if(tableline1.get(4)!=null && tableline1.get(6)!=null)
                if(tableline1.get(4)!=null && tableline1.get(6)!=null)
                {
                    double minval = (double) tableline1.get(4);
                    double maxval = (double) tableline1.get(6);
                    if (minval>maxval) {
                        String code = (String) tableline1.get(1);
                        String name = (String) tableline1.get(2);
                        JOptionPane.showMessageDialog(null, "Nutrient Max Value Error.\nRow no: "+(i+1)+" "
                            + "\nNutrient Code: "+code+"\nNutrient Name: "+name, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        }

        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Formulation formulation=new Formulation();
        boolean status=formulation.calculation(Compound_id);
        if(status){
            //this.dispose();
            //CompoundFormulation compoundFormulation = new CompoundFormulation(lineno,tableListData,null,null,null,null,priceweek_ID);
            //compoundFormulation.setVisible(true);
            tableListData.clear();
            try {
                tableListData=sqlManager.getCompoundList();
                ingredientData=sqlManager.getCompoundingredientdata(Compound_id);
                nutrientData=sqlManager.getCompoundnutrientdata(Compound_id);

            } catch (SQLException ex) {
                Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
            }
            CompoundWork(lineno);
        }
    }//GEN-LAST:event_formulaBtnActionPerformed

    private void priceListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceListComboBoxActionPerformed
        // TODO add your handling code here:

        //int price_Index=priceListComboBox.getSelectedIndex();
        //Vector price =(Vector) priceList.get(price_Index);
        //priceweek_ID = (Integer) price.get(0);
    }//GEN-LAST:event_priceListComboBoxActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
//        Compound Compound = new Compound();
//        Compound.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveallbtnActionPerformed

        try {
            boolean isSuccess1 =false;
            boolean isSuccess2 =false;
            if(priceweek_ID!=week_ID){
                isSuccess1=sqlManager.updateCompoundpriceweek(Compound_id,priceweek_ID);
                if(!isSuccess1)
                {
                    JOptionPane.showMessageDialog(null, "PriseList Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if(ingredientData.size()>0)
            {
                getIngredientDatafromTable();
                isSuccess1=sqlManager.saveCompoundIngredient(ingredientData,Compound_id);
                if(!isSuccess1)
                {
                    JOptionPane.showMessageDialog(null, "ingredient Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(nutrientData.size()>0){
                getNutrientDatafromTable();
                isSuccess2=sqlManager.saveCompoundNutrient(nutrientData,Compound_id);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Nutrient Saved Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if(ingredientDeleteData.size()>0){
                isSuccess2=sqlManager.deleteCompoundingredient(ingredientDeleteData);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Ingredient Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(nutrientDeleteData.size()>0){
                isSuccess2=sqlManager.deleteCompoundnutrient(nutrientDeleteData);
                if(!isSuccess2)
                {
                    JOptionPane.showMessageDialog(null, "Nutrient Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            ingredientData.clear();
            nutrientData.clear();
            ingredientDeleteData.clear();
            nutrientDeleteData.clear();
            CompoundWork(lineno);
            saveallbtn.setEnabled(false);
            week_ID=priceweek_ID;

        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveallbtnActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homeBtnActionPerformed

    private void nutrientsTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nutrientsTableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrientsTableKeyPressed

    private void nutrientsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nutrientsTableMouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_nutrientsTableMouseClicked
    boolean isAnalysybtn=true;
    private void analysisbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analysisbtnActionPerformed
        // TODO add your handling code here:
        Vector tableline =(Vector) tableListData.get(lineno);
        int SOLUTION_ID=(int) tableline.get(21);
        //if(SOLUTION_ID==2)
        {
            isAnalysybtn=true;
            analysisjTable.setVisible(true);
            nutrientAnalysisLbl.setVisible(true);
        }
        
        
    }//GEN-LAST:event_analysisbtnActionPerformed

    private void reportbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportbtnActionPerformed
        // TODO add your handling code here:
        jasperReport report =new jasperReport();
        try {
            report.formulationReport(Compound_id);
        } catch (IOException ex) {
            Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(CompoundFormulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reportbtnActionPerformed

    private void ingredientjTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ingredientjTableAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredientjTableAncestorAdded
    private void getIngredientDatafromTable(){
        DefaultTableModel model1 = (DefaultTableModel)ingredientjTable.getModel();
        
        TableCellEditor editor = ingredientjTable.getCellEditor();
        if (editor != null)
        {
            editor.stopCellEditing();
            editor.cancelCellEditing();
        }
        for(int i=0;i<ingredientData.size();i++)
        {
            try {
                Vector tableline =(Vector) ingredientData.get(i);
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
                    }
                    else{
                        try {
                            double pricedouble = Double.parseDouble(price);
                            tableline.set(3, pricedouble);
                        } catch (Exception e1) {
                            tableline.set(3, null);
                        }
                    }
                }
                try {
                    double minvalue = (double)model1.getValueAt(i, 3);
                    //System.out.println(""+pricedouble);
                    tableline.set(4, minvalue);
                } 
                catch (Exception e) {
                    String minvalue=(String)model1.getValueAt(i, 3);
                    //System.out.println(""+price);
                    if(minvalue==null){
                            tableline.set(4, null);
                    }
                    else{
                        try {
                        double minvalue1 = Double.parseDouble(minvalue);
                        tableline.set(4, minvalue1);
                        } catch (Exception e1) {
                            tableline.set(4, null);
                        }
                    }
                }
                try {
                    double value = (double)model1.getValueAt(i, 4);
                    //System.out.println(""+pricedouble);
                    tableline.set(5, value);
                } 
                catch (Exception e) {
                    String value=(String)model1.getValueAt(i, 4);
                    //System.out.println(""+price);
                    if(value==null){
                            tableline.set(5, null);
                    }
                    else{
                        try {
                        double value1 = Double.parseDouble(value);
                        tableline.set(5, value1);
                        } catch (Exception e1) {
                            tableline.set(5, null);
                        }
                    }
                    
                }
                try {
                    double maxvalue = (double)model1.getValueAt(i, 5);
                    tableline.set(6, maxvalue);
                } 
                catch (Exception e) {
                    String maxvalue=(String)model1.getValueAt(i, 5);
                    if(maxvalue==null){
                            tableline.set(6, null);
                    }
                    else{
                        try {
                        double maxvalue1 = Double.parseDouble(maxvalue);
                        tableline.set(6, maxvalue1);
                        } catch (Exception e1) {
                            tableline.set(6, null);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    private void getNutrientDatafromTable(){
        DefaultTableModel model1 = (DefaultTableModel)nutrientsTable.getModel();
        
        TableCellEditor editor = nutrientsTable.getCellEditor();
        if (editor != null)
        {
            editor.stopCellEditing();
            editor.cancelCellEditing();
        }
        for(int i=0;i<nutrientData.size();i++)
        {
            try {
                Vector tableline =(Vector) nutrientData.get(i);
                
                try {
                    double minvalue = (double)model1.getValueAt(i, 3);
                    //System.out.println(""+pricedouble);
                    tableline.set(4, minvalue);
                } 
                catch (Exception e) {
                    String minvalue=(String)model1.getValueAt(i, 3);
                    //System.out.println(""+price);
                    if(minvalue==null){
                            tableline.set(4, null);
                    }
                    else{
                        try {
                        double minvalue1 = Double.parseDouble(minvalue);
                        tableline.set(4, minvalue1);
                        } catch (Exception e1) {
                            tableline.set(4, null);
                        }
                    }
                }
                try {
                    double value = (double)model1.getValueAt(i, 4);
                    //System.out.println(""+pricedouble);
                    tableline.set(5, value);
                } 
                catch (Exception e) {
                    String value=(String)model1.getValueAt(i, 4);
                    //System.out.println(""+price);
                    if(value==null){
                            tableline.set(5, null);
                    }
                    else{
                        try {
                        double value1 = Double.parseDouble(value);
                        tableline.set(5, value1);
                        } catch (Exception e1) {
                            tableline.set(5, null);
                        }
                    }
                    
                }
                try {
                    double maxvalue = (double)model1.getValueAt(i, 5);
                    tableline.set(6, maxvalue);
                } 
                catch (Exception e) {
                    String maxvalue=(String)model1.getValueAt(i, 5);
                    if(maxvalue==null){
                            tableline.set(6, null);
                    }
                    else{
                        try {
                        double maxvalue1 = Double.parseDouble(maxvalue);
                        tableline.set(6, maxvalue1);
                        } catch (Exception e1) {
                            tableline.set(6, null);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Optimized_Pricelbl;
    private javax.swing.JLabel Previous_PriceLBL;
    private javax.swing.JLabel Solutionlbl;
    private javax.swing.JButton analysisbtn;
    private javax.swing.JTable analysisjTable;
    private javax.swing.JButton backbtn;
    private javax.swing.JButton formulaBtn;
    private javax.swing.JLabel formulatedlbl;
    private javax.swing.JButton homeBtn;
    private javax.swing.JTable ingredientjTable;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel nutrientAnalysisLbl;
    private javax.swing.JTable nutrientsTable;
    private javax.swing.JComboBox priceListComboBox;
    private javax.swing.JButton reportbtn;
    private javax.swing.JButton saveallbtn;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}