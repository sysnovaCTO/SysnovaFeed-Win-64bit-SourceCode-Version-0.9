/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysnovaFeed;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author root
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form TabTest
     */
    public Main() {
        initComponents();
        jTabbedPane1.remove(jPanel_Nutrient);
        jTabbedPane1.remove(jPanel_Ingredient);
        jTabbedPane1.remove(jPanel_Pricelist);
        jTabbedPane1.remove(jPanel_Compound);
        jTabbedPane1.remove(jPanel_UserInfo);
        jTabbedPane1.remove(jPanel_IngredientGroup);
        jTabbedPane1.remove(jPanel_IngredientType);
        jTabbedPane1.remove(jPanel_UnitEntry);
        jTabbedPane1.remove(jPanel_NutrientProperty);
        jTabbedPane1.remove(jPanel_CompoundGroup);
        jTabbedPane1.remove(jPanel_CompoundType);
        
        jTabbedPane1.add(jPanel_Dashboard);
        jTabbedPane1.setTitleAt(0, "DashBoard");
        

                

        
        
        userNameLabel2.setText("");
        userNameLabel2.setText("  "+sqlManager.getUserName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        this.addWindowListener(exitListener);
        getTotalVal();
        pieChart();
        barChart() ;
    }

    Nutrient a;
    Ingredient i;
    PriceList p;
    Compound c;
    UserInfo u;
    IngredientGroup ig;
    IngredientType it;
    Unit unit;
    NutrientProperty np;
    Compoundgroup cg;
    Compoundtype ct;
    
    
    
      WindowListener exitListener = new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(
             null, "Are You Sure to Close Application?", 
             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
           System.exit(0);
           
        }
        else if(confirm==1)
        {
             //System.exit(-1);
        }

    }
};
      
      
       public void getTotalVal() {
        totcomlbl.setText("");
        totprilbl.setText("");
        totnutlbl.setText("");
        totinglbl.setText("");
        try {
            ArrayList totalList=sqlManager.getTotaldata();
            Vector totalLine=(Vector)totalList.get(0);
            int comVal=(Integer)totalLine.get(0);
            int priceVal=(Integer)totalLine.get(1);
            int nutVal=(Integer)totalLine.get(2);
            int ingVal=(Integer)totalLine.get(3);
            
            totcomlbl.setText(""+comVal);
            totprilbl.setText(""+priceVal);
            totnutlbl.setText(""+nutVal);
            totinglbl.setText(""+ingVal);
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
            
            
    public void barChart() 
    {
        JFreeChart barChart = ChartFactory.createBarChart(
         "Analysis",           
         "Animal Type",            
         "Nutrient Value",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );  
      jButton2.add(chartPanel);
    }
                
                
   private CategoryDataset createDataset( )
   {
      final String Broiler = "Broiler";        
      final String Layer = "Layer";        
      final String Fish = "Fish";    
      final String Other = "Other";
      final String NutrientA_EE = "A_EE";        
      final String NutrientDM = "DM";
      final String NutrientCP = "CP";
      final String NutrientEE = "EE";
      final String NutrientCF = "CF";  
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
      try {
            ArrayList DEList=sqlManager.getbarchardata("A_EE");
            if(DEList.size()>0){
                Vector line=(Vector)DEList.get(0);
                dataset.addValue( (Double)line.get(0), Broiler , NutrientA_EE );
                dataset.addValue( (Double)line.get(1) , Layer , NutrientA_EE );
                dataset.addValue( (Double)line.get(2) , Fish , NutrientA_EE );
                dataset.addValue( (Double)line.get(3) , Other , NutrientA_EE );
            }
            
            ArrayList DMList=sqlManager.getbarchardata("DM");
            if(DMList.size()>0){
                Vector line=(Vector)DMList.get(0);
                dataset.addValue( (Double)line.get(0), Broiler , NutrientDM );
                dataset.addValue( (Double)line.get(1) , Layer , NutrientDM );
                dataset.addValue( (Double)line.get(2) , Fish , NutrientDM );
                dataset.addValue( (Double)line.get(3) , Other , NutrientDM );
            }
            
            ArrayList CPList=sqlManager.getbarchardata("CP");
            if(CPList.size()>0){
                Vector line=(Vector)CPList.get(0);
                dataset.addValue( (Double)line.get(0), Broiler , NutrientCP );
                dataset.addValue( (Double)line.get(1) , Layer , NutrientCP );
                dataset.addValue( (Double)line.get(2) , Fish , NutrientCP );
                dataset.addValue( (Double)line.get(3) , Other , NutrientCP );
            }
            
            ArrayList EEList=sqlManager.getbarchardata("EE");
            if(EEList.size()>0){
                Vector line=(Vector)EEList.get(0);
                dataset.addValue( (Double)line.get(0), Broiler , NutrientEE );
                dataset.addValue( (Double)line.get(1) , Layer , NutrientEE );
                dataset.addValue( (Double)line.get(2) , Fish , NutrientEE );
                dataset.addValue( (Double)line.get(3) , Other , NutrientEE );
            } 
            
            ArrayList CFList=sqlManager.getbarchardata("CF");
            if(CPList.size()>0){
                Vector line=(Vector)CPList.get(0);
                dataset.addValue( (Double)line.get(0), Broiler , NutrientCF );
                dataset.addValue( (Double)line.get(1) , Layer , NutrientCF );
                dataset.addValue( (Double)line.get(2) , Fish , NutrientCF );
                dataset.addValue( (Double)line.get(3) , Other , NutrientCF );
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*
      dataset.addValue( 20 , Broiler , NutrientDE );
      dataset.addValue( 36.33 , Broiler , NutrientDM );
      dataset.addValue( 20 , Broiler , NutrientCP );
      dataset.addValue( 36.33 , Broiler , NutrientEE );
      dataset.addValue( 36.33 , Broiler , NutrientCF );

      dataset.addValue( 25 , Layer , NutrientDE );
      dataset.addValue( 26.99 , Layer , NutrientDM ); 
      dataset.addValue( 20 , Layer , NutrientCP );
      dataset.addValue( 36.33 , Layer , NutrientEE );
      dataset.addValue( 36.33 , Layer , NutrientCF );
      
      dataset.addValue( 29 , Fish , NutrientDE );
      dataset.addValue( 32 , Fish , NutrientDM ); 
      dataset.addValue( 20 , Fish , NutrientCP );
      dataset.addValue( 36.33 , Fish , NutrientEE );
      dataset.addValue( 36.33 , Fish , NutrientCF );

      dataset.addValue( 39 , Other , NutrientDE );
      dataset.addValue( 24 , Other , NutrientDM );
      dataset.addValue( 20 , Other , NutrientCP );
      dataset.addValue( 36.33 , Other , NutrientEE );
      dataset.addValue( 36.33 , Other , NutrientCF );
              */       

      return dataset; 
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void pieChart() 
    {
        JFreeChart chart = createChart(createDataset1());
        final ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270)); 
        jButton1.add(chartPanel);
    }
    public static PieDataset createDataset1( ) 
    {
        double Broiler=0.0;
        double Layer=0.0;
        double Fish=0.0;
        double Other=0.0;
        try {
            ArrayList pieList=sqlManager.getpaidata();
            Vector pielLine=(Vector)pieList.get(0);
            Broiler=(Double)pielLine.get(0);
            Layer=(Double)pielLine.get(1);
            Fish=(Double)pielLine.get(2);
            Other=(Double)pielLine.get(3);
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "Broiler" , Broiler );  
      dataset.setValue( "Layer" , Layer );   
      dataset.setValue( "Fish" , Fish );    
      dataset.setValue( "Other" , Other );  
      return dataset;         
    }
   private static JFreeChart createChart( PieDataset dataset )
   {
        JFreeChart chart = ChartFactory.createPieChart(      
         "Formulation Cost Analysis",  // chart title 
         dataset,        // data    
         true,           // include legend   
         true, 
         false);

      return chart;
   }
   public static JPanel createDemoPanel( )
   {
      JFreeChart chart = createChart(createDataset1( ) );  
      return new ChartPanel( chart ); 
   }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        userNameLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        newbtn9 = new javax.swing.JButton();
        nutrientbtn = new javax.swing.JButton();
        ingredientbtn = new javax.swing.JButton();
        newbtn7 = new javax.swing.JButton();
        newbtn14 = new javax.swing.JButton();
        newbtn8 = new javax.swing.JButton();
        newbtn11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        newbtn19 = new javax.swing.JButton();
        newbtn4 = new javax.swing.JButton();
        newbtn22 = new javax.swing.JButton();
        newbtn24 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        newbtn5 = new javax.swing.JButton();
        newbtn27 = new javax.swing.JButton();
        newbtn28 = new javax.swing.JButton();
        newbtn12 = new javax.swing.JButton();
        newbtn13 = new javax.swing.JButton();
        newbtn15 = new javax.swing.JButton();
        newbtn16 = new javax.swing.JButton();
        newbtn17 = new javax.swing.JButton();
        newbtn18 = new javax.swing.JButton();
        newbtn20 = new javax.swing.JButton();
        newbtn21 = new javax.swing.JButton();
        newbtn23 = new javax.swing.JButton();
        newbtn26 = new javax.swing.JButton();
        newbtn10 = new javax.swing.JButton();
        newbtn29 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_Nutrient = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        nutrient_codeTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nutrient_longTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        nutrient_editBtn = new javax.swing.JButton();
        nutrient_addnewbtn = new javax.swing.JButton();
        nutrient_addListBtn = new javax.swing.JButton();
        nutrient_unitComboBox = new javax.swing.JComboBox();
        nutrient_propertyComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        nutrient_nameTextField = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        nutrient_broilerCheckBox = new javax.swing.JCheckBox();
        nutrient_fishCheckBox = new javax.swing.JCheckBox();
        nutrient_layerCheckBox = new javax.swing.JCheckBox();
        nutrient_otherCheckBox = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        nutrient_isActiveCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        nutrient_jTable = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        nutrient_saveallbtn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        nutrient_updatedOnTextField = new javax.swing.JTextField();
        nutrient_UpdatedByTextField = new javax.swing.JTextField();
        nutrient_createdOnTextField = new javax.swing.JTextField();
        nutrient_CreatedByTextField = new javax.swing.JTextField();
        jPanel_Ingredient = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ingredient_jTable = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ingredientRefreshBtn = new javax.swing.JButton();
        ingredient_deleteBtn = new javax.swing.JButton();
        ingredient_newbtn = new javax.swing.JButton();
        jPanel_Compound = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        compound_jTable = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        compoundRefreshBtn = new javax.swing.JButton();
        deleteBtn2 = new javax.swing.JButton();
        newbtn30 = new javax.swing.JButton();
        editbtn1 = new javax.swing.JButton();
        detailsbtn = new javax.swing.JButton();
        jPanel_UserInfo = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        userinfo_UserIDTextField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        userinfo_editBtn = new javax.swing.JButton();
        userinfo_addnewbtn = new javax.swing.JButton();
        userinfo_addListBtn = new javax.swing.JButton();
        userinfo_isActiveCheckBox = new javax.swing.JCheckBox();
        userinfo_PasswordTextField = new javax.swing.JPasswordField();
        jScrollPane6 = new javax.swing.JScrollPane();
        userinfo_jTable = new javax.swing.JTable();
        jPanel62 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        userinfo_saveallbtn = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        userinfo_updatedOnTextField = new javax.swing.JTextField();
        userinfo_UpdatedByTextField = new javax.swing.JTextField();
        userinfo_createdOnTextField = new javax.swing.JTextField();
        userinfo_CreatedByTextField = new javax.swing.JTextField();
        jPanel_IngredientGroup = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        ingredientgroup_editBtn = new javax.swing.JButton();
        ingredientgroup_addnewbtn = new javax.swing.JButton();
        ingredientgroup_addListBtn = new javax.swing.JButton();
        ingredientgroup_IngredientNameTextField = new javax.swing.JTextField();
        ingredientgroup_isActiveCheckBox = new javax.swing.JCheckBox();
        ingredientgroup_Deletebtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        ingredientgroup_jTable = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        ingredientgroup_saveallbtn = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        ingredientgroup_updatedOnTextField = new javax.swing.JTextField();
        ingredientgroup_UpdatedByTextField = new javax.swing.JTextField();
        ingredientgroup_createdOnTextField = new javax.swing.JTextField();
        ingredientgroup_CreatedByTextField = new javax.swing.JTextField();
        jPanel_IngredientType = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        ingredienttype_editBtn = new javax.swing.JButton();
        ingredienttype_addnewbtn = new javax.swing.JButton();
        ingredienttype_addListBtn = new javax.swing.JButton();
        ingredienttype_IngredientTypeTextField = new javax.swing.JTextField();
        ingredienttype_isActiveCheckBox = new javax.swing.JCheckBox();
        ingredienttype_Deletebtn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        ingredienttype_jTable = new javax.swing.JTable();
        jPanel39 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        ingredienttype_saveallbtn = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        ingredienttype_updatedOnTextField = new javax.swing.JTextField();
        ingredienttype_UpdatedByTextField = new javax.swing.JTextField();
        ingredienttype_createdOnTextField = new javax.swing.JTextField();
        ingredienttype_CreatedByTextField = new javax.swing.JTextField();
        jPanel_UnitEntry = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        unit_codeTextField = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        unit_editBtn = new javax.swing.JButton();
        unit_addnewbtn = new javax.swing.JButton();
        unit_addListBtn = new javax.swing.JButton();
        unit_nameTextField = new javax.swing.JTextField();
        unit_isActiveCheckBox = new javax.swing.JCheckBox();
        unit_deletebtn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        unit_jTable = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        unit_saveallbtn = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        unit_updatedOnTextField = new javax.swing.JTextField();
        unit_UpdatedByTextField = new javax.swing.JTextField();
        unit_createdOnTextField = new javax.swing.JTextField();
        unit_CreatedByTextField = new javax.swing.JTextField();
        jPanel_NutrientProperty = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        nutrientproperty_editBtn = new javax.swing.JButton();
        nutrientproperty_addnewbtn = new javax.swing.JButton();
        nutrientproperty_addListBtn = new javax.swing.JButton();
        nutrientproperty_NuPropertyNameTextField = new javax.swing.JTextField();
        nutrientproperty_isActiveCheckBox = new javax.swing.JCheckBox();
        nutrientproperty_Deletebtn = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        nutrientproperty_jTable = new javax.swing.JTable();
        jPanel49 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        nutrientproperty_saveallbtn = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        nutrientproperty_updatedOnTextField = new javax.swing.JTextField();
        nutrientproperty_UpdatedByTextField = new javax.swing.JTextField();
        nutrientproperty_createdOnTextField = new javax.swing.JTextField();
        nutrientproperty_CreatedByTextField = new javax.swing.JTextField();
        jPanel_CompoundGroup = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        compoundgroup_editBtn = new javax.swing.JButton();
        compoundgroup_addnewbtn = new javax.swing.JButton();
        compoundgroup_addListBtn = new javax.swing.JButton();
        compoundgroup_nameTextField = new javax.swing.JTextField();
        compoundgroup_isActiveCheckBox = new javax.swing.JCheckBox();
        compoundgroup_deletebtn = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        compoundgroup_jTable = new javax.swing.JTable();
        jPanel54 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        compoundgroup_saveallbtn = new javax.swing.JButton();
        jPanel56 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        compoundgroup_updatedOnTextField = new javax.swing.JTextField();
        compoundgroup_UpdatedByTextField = new javax.swing.JTextField();
        compoundgroup_createdOnTextField = new javax.swing.JTextField();
        compoundgroup_CreatedByTextField = new javax.swing.JTextField();
        jPanel_CompoundType = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        compoundtype_editBtn = new javax.swing.JButton();
        compoundtype_addnewbtn = new javax.swing.JButton();
        compoundtype_addListBtn = new javax.swing.JButton();
        compoundtype_nameTextField = new javax.swing.JTextField();
        compoundtype_isActiveCheckBox = new javax.swing.JCheckBox();
        compoundtype_deletebtn = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        compoundtype_jTable = new javax.swing.JTable();
        jPanel59 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        compoundtype_saveallbtn = new javax.swing.JButton();
        jPanel61 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        compoundtype_updatedOnTextField = new javax.swing.JTextField();
        compoundtype_UpdatedByTextField = new javax.swing.JTextField();
        compoundtype_createdOnTextField = new javax.swing.JTextField();
        compoundtype_CreatedByTextField = new javax.swing.JTextField();
        jPanel_Dashboard = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totcomlbl = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totprilbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        totnutlbl = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totinglbl = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jPanel_Pricelist = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PriceList_jTable = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        pricelistRefreshBtn = new javax.swing.JButton();
        deleteBtn1 = new javax.swing.JButton();
        newbtn1 = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        detailsBtn = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SysnovaFEED");
        setSize(new java.awt.Dimension(1600, 900));

        jPanel2.setPreferredSize(new java.awt.Dimension(1600, 900));

        jSplitPane1.setDividerSize(20);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 10));

        userNameLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(232, 245, 245));
        userNameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/user1.png"))); // NOI18N
        userNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userNameLabelMouseClicked(evt);
            }
        });

        userNameLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        userNameLabel2.setForeground(new java.awt.Color(0, 0, 0));
        userNameLabel2.setText(" UserName");
        userNameLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel3.setBackground(new java.awt.Color(107, 12, 234));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(240, 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(103, 62, 122));
        jLabel2.setText("Data");

        newbtn9.setBackground(new java.awt.Color(255, 255, 255));
        newbtn9.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn9.setForeground(new java.awt.Color(0, 0, 0));
        newbtn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn9.setBorder(null);
        newbtn9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn9.setEnabled(false);
        newbtn9.setIconTextGap(5);
        newbtn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn9ActionPerformed(evt);
            }
        });

        nutrientbtn.setBackground(new java.awt.Color(255, 255, 255));
        nutrientbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientbtn.setForeground(new java.awt.Color(0, 0, 0));
        nutrientbtn.setText(" Nutrient");
        nutrientbtn.setBorder(null);
        nutrientbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nutrientbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        nutrientbtn.setIconTextGap(5);
        nutrientbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientbtnActionPerformed(evt);
            }
        });

        ingredientbtn.setBackground(new java.awt.Color(255, 255, 255));
        ingredientbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientbtn.setForeground(new java.awt.Color(0, 0, 0));
        ingredientbtn.setText(" Ingredient");
        ingredientbtn.setBorder(null);
        ingredientbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ingredientbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ingredientbtn.setIconTextGap(5);
        ingredientbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientbtnActionPerformed(evt);
            }
        });

        newbtn7.setBackground(new java.awt.Color(255, 255, 255));
        newbtn7.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn7.setForeground(new java.awt.Color(0, 0, 0));
        newbtn7.setText(" PriceList");
        newbtn7.setBorder(null);
        newbtn7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn7.setIconTextGap(5);
        newbtn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn7ActionPerformed(evt);
            }
        });

        newbtn14.setBackground(new java.awt.Color(255, 255, 255));
        newbtn14.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn14.setForeground(new java.awt.Color(0, 0, 0));
        newbtn14.setText(" Compound");
        newbtn14.setBorder(null);
        newbtn14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn14.setIconTextGap(5);
        newbtn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn14ActionPerformed(evt);
            }
        });

        newbtn8.setBackground(new java.awt.Color(255, 255, 255));
        newbtn8.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn8.setForeground(new java.awt.Color(0, 0, 0));
        newbtn8.setText(" Ingredient Group");
        newbtn8.setBorder(null);
        newbtn8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn8.setIconTextGap(5);
        newbtn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn8ActionPerformed(evt);
            }
        });

        newbtn11.setBackground(new java.awt.Color(255, 255, 255));
        newbtn11.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn11.setForeground(new java.awt.Color(0, 0, 0));
        newbtn11.setText(" Ingredient Type");
        newbtn11.setBorder(null);
        newbtn11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn11.setIconTextGap(5);
        newbtn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn11ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(103, 62, 122));
        jLabel1.setText("Setup");

        newbtn19.setBackground(new java.awt.Color(255, 255, 255));
        newbtn19.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn19.setForeground(new java.awt.Color(0, 0, 0));
        newbtn19.setText(" Unit");
        newbtn19.setBorder(null);
        newbtn19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn19.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn19.setIconTextGap(5);
        newbtn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn19ActionPerformed(evt);
            }
        });

        newbtn4.setBackground(new java.awt.Color(255, 255, 255));
        newbtn4.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn4.setForeground(new java.awt.Color(0, 0, 0));
        newbtn4.setText(" Nutrient Property");
        newbtn4.setBorder(null);
        newbtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn4.setIconTextGap(5);
        newbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn4ActionPerformed(evt);
            }
        });

        newbtn22.setBackground(new java.awt.Color(255, 255, 255));
        newbtn22.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn22.setForeground(new java.awt.Color(0, 0, 0));
        newbtn22.setText(" Compound Gruop");
        newbtn22.setBorder(null);
        newbtn22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn22.setIconTextGap(5);
        newbtn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn22ActionPerformed(evt);
            }
        });

        newbtn24.setBackground(new java.awt.Color(255, 255, 255));
        newbtn24.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn24.setForeground(new java.awt.Color(0, 0, 0));
        newbtn24.setText(" Compound Type");
        newbtn24.setBorder(null);
        newbtn24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn24.setIconTextGap(5);
        newbtn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn24ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(103, 62, 122));
        jLabel3.setText("Report");

        newbtn5.setBackground(new java.awt.Color(255, 255, 255));
        newbtn5.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn5.setForeground(new java.awt.Color(0, 0, 0));
        newbtn5.setText(" Formulation Cost");
        newbtn5.setBorder(null);
        newbtn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn5.setIconTextGap(5);
        newbtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn5ActionPerformed(evt);
            }
        });

        newbtn27.setBackground(new java.awt.Color(255, 255, 255));
        newbtn27.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn27.setForeground(new java.awt.Color(0, 0, 0));
        newbtn27.setText(" Blend Order");
        newbtn27.setBorder(null);
        newbtn27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn27.setIconTextGap(5);
        newbtn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn27ActionPerformed(evt);
            }
        });

        newbtn28.setBackground(new java.awt.Color(255, 255, 255));
        newbtn28.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn28.setForeground(new java.awt.Color(0, 0, 0));
        newbtn28.setText(" Ingredient Details");
        newbtn28.setBorder(null);
        newbtn28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newbtn28.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        newbtn28.setIconTextGap(5);
        newbtn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn28ActionPerformed(evt);
            }
        });

        newbtn12.setBackground(new java.awt.Color(255, 255, 255));
        newbtn12.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn12.setForeground(new java.awt.Color(0, 0, 0));
        newbtn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn12.setBorder(null);
        newbtn12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn12.setEnabled(false);
        newbtn12.setIconTextGap(5);
        newbtn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn12ActionPerformed(evt);
            }
        });

        newbtn13.setBackground(new java.awt.Color(255, 255, 255));
        newbtn13.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn13.setForeground(new java.awt.Color(0, 0, 0));
        newbtn13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn13.setBorder(null);
        newbtn13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn13.setEnabled(false);
        newbtn13.setIconTextGap(5);
        newbtn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn13ActionPerformed(evt);
            }
        });

        newbtn15.setBackground(new java.awt.Color(255, 255, 255));
        newbtn15.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn15.setForeground(new java.awt.Color(0, 0, 0));
        newbtn15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn15.setBorder(null);
        newbtn15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn15.setEnabled(false);
        newbtn15.setIconTextGap(5);
        newbtn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn15ActionPerformed(evt);
            }
        });

        newbtn16.setBackground(new java.awt.Color(255, 255, 255));
        newbtn16.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn16.setForeground(new java.awt.Color(0, 0, 0));
        newbtn16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn16.setBorder(null);
        newbtn16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn16.setEnabled(false);
        newbtn16.setIconTextGap(5);
        newbtn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn16ActionPerformed(evt);
            }
        });

        newbtn17.setBackground(new java.awt.Color(255, 255, 255));
        newbtn17.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn17.setForeground(new java.awt.Color(0, 0, 0));
        newbtn17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn17.setBorder(null);
        newbtn17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn17.setEnabled(false);
        newbtn17.setIconTextGap(5);
        newbtn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn17ActionPerformed(evt);
            }
        });

        newbtn18.setBackground(new java.awt.Color(255, 255, 255));
        newbtn18.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn18.setForeground(new java.awt.Color(0, 0, 0));
        newbtn18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn18.setBorder(null);
        newbtn18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn18.setEnabled(false);
        newbtn18.setIconTextGap(5);
        newbtn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn18ActionPerformed(evt);
            }
        });

        newbtn20.setBackground(new java.awt.Color(255, 255, 255));
        newbtn20.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn20.setForeground(new java.awt.Color(0, 0, 0));
        newbtn20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn20.setBorder(null);
        newbtn20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn20.setEnabled(false);
        newbtn20.setIconTextGap(5);
        newbtn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn20ActionPerformed(evt);
            }
        });

        newbtn21.setBackground(new java.awt.Color(255, 255, 255));
        newbtn21.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn21.setForeground(new java.awt.Color(0, 0, 0));
        newbtn21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn21.setBorder(null);
        newbtn21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn21.setEnabled(false);
        newbtn21.setIconTextGap(5);
        newbtn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn21ActionPerformed(evt);
            }
        });

        newbtn23.setBackground(new java.awt.Color(255, 255, 255));
        newbtn23.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn23.setForeground(new java.awt.Color(0, 0, 0));
        newbtn23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn23.setBorder(null);
        newbtn23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn23.setEnabled(false);
        newbtn23.setIconTextGap(5);
        newbtn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn23ActionPerformed(evt);
            }
        });

        newbtn26.setBackground(new java.awt.Color(255, 255, 255));
        newbtn26.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn26.setForeground(new java.awt.Color(0, 0, 0));
        newbtn26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn26.setBorder(null);
        newbtn26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn26.setEnabled(false);
        newbtn26.setIconTextGap(5);
        newbtn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn26ActionPerformed(evt);
            }
        });

        newbtn10.setBackground(new java.awt.Color(255, 255, 255));
        newbtn10.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn10.setForeground(new java.awt.Color(0, 0, 0));
        newbtn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn10.setBorder(null);
        newbtn10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn10.setEnabled(false);
        newbtn10.setIconTextGap(5);
        newbtn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn10ActionPerformed(evt);
            }
        });

        newbtn29.setBackground(new java.awt.Color(255, 255, 255));
        newbtn29.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn29.setForeground(new java.awt.Color(0, 0, 0));
        newbtn29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.png"))); // NOI18N
        newbtn29.setBorder(null);
        newbtn29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn29.setEnabled(false);
        newbtn29.setIconTextGap(5);
        newbtn29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(userNameLabel)
                        .addGap(6, 6, 6)
                        .addComponent(userNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(nutrientbtn))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(ingredientbtn))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn29, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn14))
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn18, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn24))
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn27))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(newbtn15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(newbtn28))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(newbtn23, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(newbtn19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(newbtn21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(newbtn8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(userNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn9, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(nutrientbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(ingredientbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn29, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(newbtn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn26, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(newbtn14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newbtn15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn28, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel4);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(891, 699));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(254, 254, 254));
        jPanel7.setPreferredSize(new java.awt.Dimension(740, 868));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Nutrient Information");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nutrient_codeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_codeTextFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("Code");

        jLabel16.setText("Short Name");

        jLabel17.setText("Long Name");

        nutrient_longTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_longTextFieldActionPerformed(evt);
            }
        });

        jLabel18.setText("Unit");

        nutrient_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrient_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrient_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrient_editBtn.setText("Edit");
        nutrient_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrient_editBtn.setIconTextGap(5);
        nutrient_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_editBtnActionPerformed(evt);
            }
        });

        nutrient_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrient_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrient_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrient_addnewbtn.setText("Add New");
        nutrient_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrient_addnewbtn.setIconTextGap(5);
        nutrient_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_addnewbtnActionPerformed(evt);
            }
        });

        nutrient_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrient_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrient_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrient_addListBtn.setText("Add List");
        nutrient_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrient_addListBtn.setIconTextGap(5);
        nutrient_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_addListBtnActionPerformed(evt);
            }
        });

        nutrient_unitComboBox.setBackground(new java.awt.Color(255, 255, 255));

        nutrient_propertyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_propertyComboBox.setForeground(new java.awt.Color(12, 22, 22));
        nutrient_propertyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_propertyComboBoxActionPerformed(evt);
            }
        });

        jLabel19.setText("Group");

        nutrient_nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_nameTextFieldActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        nutrient_broilerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_broilerCheckBox.setText("Broiler");
        nutrient_broilerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_broilerCheckBoxActionPerformed(evt);
            }
        });

        nutrient_fishCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_fishCheckBox.setText("Fish");

        nutrient_layerCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_layerCheckBox.setText("Layer");
        nutrient_layerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_layerCheckBoxActionPerformed(evt);
            }
        });

        nutrient_otherCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_otherCheckBox.setText("Other");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nutrient_broilerCheckBox)
                    .addComponent(nutrient_layerCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nutrient_otherCheckBox)
                    .addComponent(nutrient_fishCheckBox))
                .addGap(23, 23, 23))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_broilerCheckBox)
                    .addComponent(nutrient_fishCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_layerCheckBox)
                    .addComponent(nutrient_otherCheckBox))
                .addContainerGap())
        );

        jLabel20.setText("Used In");

        nutrient_isActiveCheckBox.setSelected(true);
        nutrient_isActiveCheckBox.setText("IsActive");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nutrient_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nutrient_nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(nutrient_codeTextField))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(nutrient_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nutrient_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nutrient_longTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nutrient_isActiveCheckBox)
                            .addComponent(nutrient_propertyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nutrient_unitComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_longTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrient_unitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(nutrient_propertyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel20))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nutrient_isActiveCheckBox)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nutrient_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nutrient_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nutrient_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        nutrient_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Short Name", "Long Name", "Unit", "Group", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nutrient_jTable.setRowHeight(20);
        nutrient_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nutrient_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nutrient_jTableMouseClicked(evt);
            }
        });
        nutrient_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nutrient_jTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(nutrient_jTable);

        jPanel14.setBackground(new java.awt.Color(70, 73, 85));

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("All Nutrient List");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(70, 73, 85));

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Nutrient Entry");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        nutrient_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        nutrient_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrient_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrient_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        nutrient_saveallbtn.setToolTipText("Save All");
        nutrient_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrient_saveallbtn.setIconTextGap(5);
        nutrient_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrient_saveallbtnActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel23.setText("Created");

        jLabel24.setText("On:");

        jLabel25.setText("By:");

        jLabel26.setText("Updated");

        jLabel27.setText("On:");

        jLabel28.setText("By:");

        nutrient_updatedOnTextField.setEditable(false);

        nutrient_UpdatedByTextField.setEditable(false);

        nutrient_createdOnTextField.setEditable(false);

        nutrient_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(1, 1, 1)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nutrient_createdOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(nutrient_CreatedByTextField))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nutrient_UpdatedByTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(nutrient_updatedOnTextField))
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addGap(40, 40, 40))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel23))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(nutrient_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(nutrient_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(nutrient_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nutrient_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(nutrient_saveallbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nutrient_saveallbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(230, 230, 230))
        );

        javax.swing.GroupLayout jPanel_NutrientLayout = new javax.swing.GroupLayout(jPanel_Nutrient);
        jPanel_Nutrient.setLayout(jPanel_NutrientLayout);
        jPanel_NutrientLayout.setHorizontalGroup(
            jPanel_NutrientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        jPanel_NutrientLayout.setVerticalGroup(
            jPanel_NutrientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NutrientLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 671, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nutrient", jPanel_Nutrient);

        jPanel_Ingredient.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Ingredient.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_Ingredient.setPreferredSize(new java.awt.Dimension(900, 470));
        jPanel_Ingredient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_IngredientMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel29.setText("Ingredient Information");

        ingredient_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Scientific Name", "Group", "Type", "Changed By", "Changed On", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingredient_jTable.setRowHeight(20);
        ingredient_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ingredient_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingredient_jTableMouseClicked(evt);
            }
        });
        ingredient_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingredient_jTableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(ingredient_jTable);

        jPanel18.setBackground(new java.awt.Color(70, 73, 85));

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("All Ingredient List");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        ingredientRefreshBtn.setBackground(new java.awt.Color(255, 255, 255));
        ingredientRefreshBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientRefreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientRefreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/refresh.png"))); // NOI18N
        ingredientRefreshBtn.setToolTipText("Home");
        ingredientRefreshBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientRefreshBtn.setIconTextGap(5);
        ingredientRefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientRefreshBtnActionPerformed(evt);
            }
        });

        ingredient_deleteBtn.setBackground(new java.awt.Color(182, 142, 144));
        ingredient_deleteBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredient_deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredient_deleteBtn.setText("Delete");
        ingredient_deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredient_deleteBtn.setIconTextGap(5);
        ingredient_deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredient_deleteBtnActionPerformed(evt);
            }
        });

        ingredient_newbtn.setBackground(new java.awt.Color(182, 142, 144));
        ingredient_newbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredient_newbtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredient_newbtn.setText("New");
        ingredient_newbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredient_newbtn.setIconTextGap(5);
        ingredient_newbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredient_newbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_IngredientLayout = new javax.swing.GroupLayout(jPanel_Ingredient);
        jPanel_Ingredient.setLayout(jPanel_IngredientLayout);
        jPanel_IngredientLayout.setHorizontalGroup(
            jPanel_IngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_IngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_IngredientLayout.createSequentialGroup()
                        .addComponent(ingredientRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29))
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_IngredientLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ingredient_newbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ingredient_deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_IngredientLayout.setVerticalGroup(
            jPanel_IngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_IngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ingredientRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_IngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingredient_newbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ingredient_deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingredient", jPanel_Ingredient);

        jPanel_Compound.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Compound.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_Compound.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel33.setText("Compound Information");

        compound_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Group", "PriceWeek", "Changed By", "Changed On", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        compound_jTable.setRowHeight(20);
        compound_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        compound_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compound_jTableMouseClicked(evt);
            }
        });
        compound_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compound_jTableKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(compound_jTable);

        jPanel22.setBackground(new java.awt.Color(70, 73, 85));

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("All Compound List");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        compoundRefreshBtn.setBackground(new java.awt.Color(255, 255, 255));
        compoundRefreshBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundRefreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundRefreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/refresh.png"))); // NOI18N
        compoundRefreshBtn.setToolTipText("Home");
        compoundRefreshBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundRefreshBtn.setIconTextGap(5);
        compoundRefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundRefreshBtnActionPerformed(evt);
            }
        });

        deleteBtn2.setBackground(new java.awt.Color(182, 142, 144));
        deleteBtn2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        deleteBtn2.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn2.setText("Delete");
        deleteBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn2.setIconTextGap(5);
        deleteBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn2ActionPerformed(evt);
            }
        });

        newbtn30.setBackground(new java.awt.Color(182, 142, 144));
        newbtn30.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn30.setForeground(new java.awt.Color(255, 255, 255));
        newbtn30.setText("New");
        newbtn30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn30.setIconTextGap(5);
        newbtn30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn30ActionPerformed(evt);
            }
        });

        editbtn1.setBackground(new java.awt.Color(182, 142, 144));
        editbtn1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        editbtn1.setForeground(new java.awt.Color(255, 255, 255));
        editbtn1.setText("Edit");
        editbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbtn1.setIconTextGap(5);
        editbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtn1ActionPerformed(evt);
            }
        });

        detailsbtn.setBackground(new java.awt.Color(182, 142, 144));
        detailsbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        detailsbtn.setForeground(new java.awt.Color(255, 255, 255));
        detailsbtn.setText("Details");
        detailsbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detailsbtn.setIconTextGap(5);
        detailsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CompoundLayout = new javax.swing.GroupLayout(jPanel_Compound);
        jPanel_Compound.setLayout(jPanel_CompoundLayout);
        jPanel_CompoundLayout.setHorizontalGroup(
            jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                        .addGroup(jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                                .addGap(0, 316, Short.MAX_VALUE)
                                .addComponent(detailsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newbtn30, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CompoundLayout.createSequentialGroup()
                                .addComponent(compoundRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel33)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                        .addGroup(jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel_CompoundLayout.setVerticalGroup(
            jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_CompoundLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(compoundRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Compound", jPanel_Compound);

        jPanel_UserInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_UserInfo.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_UserInfo.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel46.setText("User Information ");

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        userinfo_UserIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userinfo_UserIDTextFieldActionPerformed(evt);
            }
        });

        jLabel47.setText("User ID");

        jLabel48.setText("Password");

        userinfo_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        userinfo_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userinfo_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        userinfo_editBtn.setText("Edit");
        userinfo_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userinfo_editBtn.setIconTextGap(5);
        userinfo_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userinfo_editBtnActionPerformed(evt);
            }
        });

        userinfo_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        userinfo_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userinfo_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        userinfo_addnewbtn.setText("Add New");
        userinfo_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userinfo_addnewbtn.setIconTextGap(5);
        userinfo_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userinfo_addnewbtnActionPerformed(evt);
            }
        });

        userinfo_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        userinfo_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userinfo_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        userinfo_addListBtn.setText("Add List");
        userinfo_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userinfo_addListBtn.setIconTextGap(5);
        userinfo_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userinfo_addListBtnActionPerformed(evt);
            }
        });

        userinfo_isActiveCheckBox.setSelected(true);
        userinfo_isActiveCheckBox.setText("IsActive");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userinfo_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(userinfo_UserIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userinfo_isActiveCheckBox)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(userinfo_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userinfo_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userinfo_PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userinfo_UserIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userinfo_PasswordTextField)
                    .addComponent(jLabel48))
                .addGap(119, 119, 119)
                .addComponent(userinfo_isActiveCheckBox)
                .addGap(73, 73, 73)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userinfo_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userinfo_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userinfo_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        userinfo_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "User ID", "Password", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userinfo_jTable.setRowHeight(20);
        userinfo_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userinfo_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userinfo_jTableMouseClicked(evt);
            }
        });
        userinfo_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userinfo_jTableKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(userinfo_jTable);

        jPanel62.setBackground(new java.awt.Color(70, 73, 85));

        jLabel49.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("All User Information List");

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(70, 73, 85));

        jLabel50.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("User Information Entry");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        userinfo_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        userinfo_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        userinfo_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        userinfo_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        userinfo_saveallbtn.setToolTipText("Save All");
        userinfo_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userinfo_saveallbtn.setIconTextGap(5);
        userinfo_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userinfo_saveallbtnActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel51.setText("Created");

        jLabel52.setText("On:");

        jLabel53.setText("By:");

        jLabel54.setText("Updated");

        jLabel55.setText("On:");

        jLabel56.setText("By:");

        userinfo_updatedOnTextField.setEditable(false);

        userinfo_UpdatedByTextField.setEditable(false);

        userinfo_createdOnTextField.setEditable(false);

        userinfo_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userinfo_CreatedByTextField)
                    .addComponent(userinfo_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addGap(4, 4, 4)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userinfo_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(userinfo_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addGap(58, 58, 58))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jLabel51))
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55)
                                    .addComponent(userinfo_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(userinfo_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 10, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(userinfo_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userinfo_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_UserInfoLayout = new javax.swing.GroupLayout(jPanel_UserInfo);
        jPanel_UserInfo.setLayout(jPanel_UserInfoLayout);
        jPanel_UserInfoLayout.setHorizontalGroup(
            jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UserInfoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_UserInfoLayout.createSequentialGroup()
                        .addComponent(userinfo_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46))
                    .addGroup(jPanel_UserInfoLayout.createSequentialGroup()
                        .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel_UserInfoLayout.setVerticalGroup(
            jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UserInfoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userinfo_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_UserInfoLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User Info Entry", jPanel_UserInfo);

        jPanel_IngredientGroup.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_IngredientGroup.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_IngredientGroup.setPreferredSize(new java.awt.Dimension(1600, 900));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel35.setText("Ingredient Group");

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setText("Ingredient Name");

        ingredientgroup_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredientgroup_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientgroup_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientgroup_editBtn.setText("Edit");
        ingredientgroup_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientgroup_editBtn.setIconTextGap(5);
        ingredientgroup_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_editBtnActionPerformed(evt);
            }
        });

        ingredientgroup_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredientgroup_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientgroup_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientgroup_addnewbtn.setText("New");
        ingredientgroup_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientgroup_addnewbtn.setIconTextGap(5);
        ingredientgroup_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_addnewbtnActionPerformed(evt);
            }
        });

        ingredientgroup_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredientgroup_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientgroup_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientgroup_addListBtn.setText("Add List");
        ingredientgroup_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientgroup_addListBtn.setIconTextGap(5);
        ingredientgroup_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_addListBtnActionPerformed(evt);
            }
        });

        ingredientgroup_IngredientNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_IngredientNameTextFieldActionPerformed(evt);
            }
        });

        ingredientgroup_isActiveCheckBox.setSelected(true);
        ingredientgroup_isActiveCheckBox.setText("IsActive");

        ingredientgroup_Deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredientgroup_Deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientgroup_Deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientgroup_Deletebtn.setText("Delete");
        ingredientgroup_Deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientgroup_Deletebtn.setIconTextGap(5);
        ingredientgroup_Deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_DeletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(ingredientgroup_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredientgroup_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredientgroup_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredientgroup_addListBtn))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ingredientgroup_isActiveCheckBox)
                            .addComponent(ingredientgroup_IngredientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingredientgroup_IngredientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(33, 33, 33)
                .addComponent(ingredientgroup_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingredientgroup_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredientgroup_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredientgroup_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredientgroup_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        ingredientgroup_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Ingredient Name", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingredientgroup_jTable.setRowHeight(20);
        ingredientgroup_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ingredientgroup_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingredientgroup_jTableMouseClicked(evt);
            }
        });
        ingredientgroup_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingredientgroup_jTableKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(ingredientgroup_jTable);

        jPanel34.setBackground(new java.awt.Color(70, 73, 85));

        jLabel37.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("All Ingredient List");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(70, 73, 85));

        jLabel38.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Ingredient Entry");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ingredientgroup_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        ingredientgroup_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredientgroup_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredientgroup_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        ingredientgroup_saveallbtn.setToolTipText("Save All");
        ingredientgroup_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredientgroup_saveallbtn.setIconTextGap(5);
        ingredientgroup_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_saveallbtnActionPerformed(evt);
            }
        });

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel107.setText("Created");

        jLabel108.setText("On:");

        jLabel109.setText("By:");

        jLabel110.setText("Updated");

        jLabel111.setText("On:");

        jLabel112.setText("By:");

        ingredientgroup_updatedOnTextField.setEditable(false);
        ingredientgroup_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredientgroup_updatedOnTextFieldActionPerformed(evt);
            }
        });

        ingredientgroup_UpdatedByTextField.setEditable(false);

        ingredientgroup_createdOnTextField.setEditable(false);

        ingredientgroup_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel108)
                    .addComponent(jLabel109))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredientgroup_CreatedByTextField)
                    .addComponent(ingredientgroup_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112))
                .addGap(4, 4, 4)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredientgroup_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(ingredientgroup_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jLabel110)
                .addGap(58, 58, 58))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(jLabel107))
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel111)
                                    .addComponent(ingredientgroup_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel112)
                                    .addComponent(ingredientgroup_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ingredientgroup_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingredientgroup_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel109)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_IngredientGroupLayout = new javax.swing.GroupLayout(jPanel_IngredientGroup);
        jPanel_IngredientGroup.setLayout(jPanel_IngredientGroupLayout);
        jPanel_IngredientGroupLayout.setHorizontalGroup(
            jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                        .addComponent(ingredientgroup_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35))
                    .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                        .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel_IngredientGroupLayout.setVerticalGroup(
            jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ingredientgroup_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_IngredientGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_IngredientGroupLayout.createSequentialGroup()
                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("Ingredient Group", jPanel_IngredientGroup);

        jPanel_IngredientType.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_IngredientType.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_IngredientType.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel45.setText("Ingredient Type");

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel57.setText("Ingredient Type");

        ingredienttype_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredienttype_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredienttype_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredienttype_editBtn.setText("Edit");
        ingredienttype_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredienttype_editBtn.setIconTextGap(5);
        ingredienttype_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_editBtnActionPerformed(evt);
            }
        });

        ingredienttype_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredienttype_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredienttype_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredienttype_addnewbtn.setText("New");
        ingredienttype_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredienttype_addnewbtn.setIconTextGap(5);
        ingredienttype_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_addnewbtnActionPerformed(evt);
            }
        });

        ingredienttype_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredienttype_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredienttype_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredienttype_addListBtn.setText("Add List");
        ingredienttype_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredienttype_addListBtn.setIconTextGap(5);
        ingredienttype_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_addListBtnActionPerformed(evt);
            }
        });

        ingredienttype_IngredientTypeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_IngredientTypeTextFieldActionPerformed(evt);
            }
        });

        ingredienttype_isActiveCheckBox.setSelected(true);
        ingredienttype_isActiveCheckBox.setText("IsActive");

        ingredienttype_Deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        ingredienttype_Deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredienttype_Deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredienttype_Deletebtn.setText("Delete");
        ingredienttype_Deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredienttype_Deletebtn.setIconTextGap(5);
        ingredienttype_Deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_DeletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(ingredienttype_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredienttype_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredienttype_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ingredienttype_addListBtn))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ingredienttype_isActiveCheckBox)
                            .addComponent(ingredienttype_IngredientTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingredienttype_IngredientTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(33, 33, 33)
                .addComponent(ingredienttype_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingredienttype_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredienttype_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredienttype_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingredienttype_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        ingredienttype_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Ingredient Type", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingredienttype_jTable.setRowHeight(20);
        ingredienttype_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ingredienttype_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingredienttype_jTableMouseClicked(evt);
            }
        });
        ingredienttype_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingredienttype_jTableKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(ingredienttype_jTable);

        jPanel39.setBackground(new java.awt.Color(70, 73, 85));

        jLabel58.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("All Ingredient Type List");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel40.setBackground(new java.awt.Color(70, 73, 85));

        jLabel59.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Ingredient Type Entry");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ingredienttype_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        ingredienttype_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        ingredienttype_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        ingredienttype_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        ingredienttype_saveallbtn.setToolTipText("Save All");
        ingredienttype_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingredienttype_saveallbtn.setIconTextGap(5);
        ingredienttype_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_saveallbtnActionPerformed(evt);
            }
        });

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel60.setText("Created");

        jLabel61.setText("On:");

        jLabel62.setText("By:");

        jLabel63.setText("Updated");

        jLabel64.setText("On:");

        jLabel65.setText("By:");

        ingredienttype_updatedOnTextField.setEditable(false);
        ingredienttype_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingredienttype_updatedOnTextFieldActionPerformed(evt);
            }
        });

        ingredienttype_UpdatedByTextField.setEditable(false);

        ingredienttype_createdOnTextField.setEditable(false);

        ingredienttype_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredienttype_CreatedByTextField)
                    .addComponent(ingredienttype_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65))
                .addGap(4, 4, 4)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredienttype_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(ingredienttype_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63)
                .addGap(58, 58, 58))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(jLabel60))
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel64)
                                    .addComponent(ingredienttype_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel65)
                                    .addComponent(ingredienttype_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ingredienttype_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingredienttype_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_IngredientTypeLayout = new javax.swing.GroupLayout(jPanel_IngredientType);
        jPanel_IngredientType.setLayout(jPanel_IngredientTypeLayout);
        jPanel_IngredientTypeLayout.setHorizontalGroup(
            jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientTypeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_IngredientTypeLayout.createSequentialGroup()
                        .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel_IngredientTypeLayout.createSequentialGroup()
                        .addComponent(ingredienttype_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45)
                        .addGap(23, 23, 23))))
        );
        jPanel_IngredientTypeLayout.setVerticalGroup(
            jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IngredientTypeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ingredienttype_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_IngredientTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_IngredientTypeLayout.createSequentialGroup()
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7))
                .addGap(99, 99, 99))
        );

        jTabbedPane1.addTab("Ingredient Type", jPanel_IngredientType);

        jPanel_UnitEntry.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_UnitEntry.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_UnitEntry.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel66.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel66.setText("Unit Information");

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        unit_codeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_codeTextFieldActionPerformed(evt);
            }
        });

        jLabel67.setText("Symbol");

        jLabel68.setText("Name");

        unit_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        unit_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unit_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        unit_editBtn.setText("Edit");
        unit_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        unit_editBtn.setIconTextGap(5);
        unit_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_editBtnActionPerformed(evt);
            }
        });

        unit_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        unit_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unit_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        unit_addnewbtn.setText("New");
        unit_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        unit_addnewbtn.setIconTextGap(5);
        unit_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_addnewbtnActionPerformed(evt);
            }
        });

        unit_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        unit_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unit_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        unit_addListBtn.setText("Add List");
        unit_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        unit_addListBtn.setIconTextGap(5);
        unit_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_addListBtnActionPerformed(evt);
            }
        });

        unit_nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_nameTextFieldActionPerformed(evt);
            }
        });

        unit_isActiveCheckBox.setSelected(true);
        unit_isActiveCheckBox.setText("IsActive");

        unit_deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        unit_deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unit_deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        unit_deletebtn.setText("Delete");
        unit_deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        unit_deletebtn.setIconTextGap(5);
        unit_deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_deletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(unit_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unit_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unit_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unit_addListBtn))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unit_isActiveCheckBox)
                            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(unit_nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(unit_codeTextField)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unit_codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unit_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addGap(18, 18, 18)
                .addComponent(unit_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unit_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unit_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unit_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unit_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        unit_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Symbol", "Name", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        unit_jTable.setRowHeight(20);
        unit_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        unit_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unit_jTableMouseClicked(evt);
            }
        });
        unit_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unit_jTableKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(unit_jTable);

        jPanel44.setBackground(new java.awt.Color(70, 73, 85));

        jLabel69.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("All Unit List");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel45.setBackground(new java.awt.Color(70, 73, 85));

        jLabel70.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Unit Entry");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        unit_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        unit_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unit_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        unit_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        unit_saveallbtn.setToolTipText("Save All");
        unit_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        unit_saveallbtn.setIconTextGap(5);
        unit_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_saveallbtnActionPerformed(evt);
            }
        });

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel71.setText("Created");

        jLabel72.setText("On:");

        jLabel73.setText("By:");

        jLabel74.setText("Updated");

        jLabel75.setText("On:");

        jLabel76.setText("By:");

        unit_updatedOnTextField.setEditable(false);
        unit_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_updatedOnTextFieldActionPerformed(evt);
            }
        });

        unit_UpdatedByTextField.setEditable(false);

        unit_createdOnTextField.setEditable(false);

        unit_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unit_CreatedByTextField)
                    .addComponent(unit_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76))
                .addGap(4, 4, 4)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unit_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(unit_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel74)
                .addGap(58, 58, 58))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jLabel71))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(unit_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(unit_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(unit_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unit_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel73)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_UnitEntryLayout = new javax.swing.GroupLayout(jPanel_UnitEntry);
        jPanel_UnitEntry.setLayout(jPanel_UnitEntryLayout);
        jPanel_UnitEntryLayout.setHorizontalGroup(
            jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UnitEntryLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_UnitEntryLayout.createSequentialGroup()
                        .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                    .addGroup(jPanel_UnitEntryLayout.createSequentialGroup()
                        .addComponent(unit_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addContainerGap())))
        );
        jPanel_UnitEntryLayout.setVerticalGroup(
            jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UnitEntryLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(unit_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_UnitEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_UnitEntryLayout.createSequentialGroup()
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8))
                .addGap(0, 75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Unit Entry", jPanel_UnitEntry);

        jPanel_NutrientProperty.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_NutrientProperty.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_NutrientProperty.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel77.setText("Nutrient Property");

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel78.setText("Property Name");

        nutrientproperty_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrientproperty_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientproperty_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrientproperty_editBtn.setText("Edit");
        nutrientproperty_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientproperty_editBtn.setIconTextGap(5);
        nutrientproperty_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_editBtnActionPerformed(evt);
            }
        });

        nutrientproperty_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrientproperty_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientproperty_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrientproperty_addnewbtn.setText("New");
        nutrientproperty_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientproperty_addnewbtn.setIconTextGap(5);
        nutrientproperty_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_addnewbtnActionPerformed(evt);
            }
        });

        nutrientproperty_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrientproperty_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientproperty_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrientproperty_addListBtn.setText("Add List");
        nutrientproperty_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientproperty_addListBtn.setIconTextGap(5);
        nutrientproperty_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_addListBtnActionPerformed(evt);
            }
        });

        nutrientproperty_NuPropertyNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_NuPropertyNameTextFieldActionPerformed(evt);
            }
        });

        nutrientproperty_isActiveCheckBox.setSelected(true);
        nutrientproperty_isActiveCheckBox.setText("IsActive");

        nutrientproperty_Deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        nutrientproperty_Deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientproperty_Deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrientproperty_Deletebtn.setText("Delete");
        nutrientproperty_Deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientproperty_Deletebtn.setIconTextGap(5);
        nutrientproperty_Deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_DeletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(nutrientproperty_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nutrientproperty_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nutrientproperty_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nutrientproperty_addListBtn))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nutrientproperty_isActiveCheckBox)
                            .addComponent(nutrientproperty_NuPropertyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrientproperty_NuPropertyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addGap(33, 33, 33)
                .addComponent(nutrientproperty_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nutrientproperty_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nutrientproperty_Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nutrientproperty_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nutrientproperty_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        nutrientproperty_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Property Name", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nutrientproperty_jTable.setRowHeight(20);
        nutrientproperty_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nutrientproperty_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nutrientproperty_jTableMouseClicked(evt);
            }
        });
        nutrientproperty_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nutrientproperty_jTableKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(nutrientproperty_jTable);

        jPanel49.setBackground(new java.awt.Color(70, 73, 85));

        jLabel79.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("All Nutrient Property List");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel50.setBackground(new java.awt.Color(70, 73, 85));

        jLabel80.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Nutrient Property Entry");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        nutrientproperty_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        nutrientproperty_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        nutrientproperty_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        nutrientproperty_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        nutrientproperty_saveallbtn.setToolTipText("Save All");
        nutrientproperty_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nutrientproperty_saveallbtn.setIconTextGap(5);
        nutrientproperty_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_saveallbtnActionPerformed(evt);
            }
        });

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel81.setText("Created");

        jLabel82.setText("On:");

        jLabel83.setText("By:");

        jLabel84.setText("Updated");

        jLabel85.setText("On:");

        jLabel86.setText("By:");

        nutrientproperty_updatedOnTextField.setEditable(false);
        nutrientproperty_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutrientproperty_updatedOnTextFieldActionPerformed(evt);
            }
        });

        nutrientproperty_UpdatedByTextField.setEditable(false);

        nutrientproperty_createdOnTextField.setEditable(false);

        nutrientproperty_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82)
                    .addComponent(jLabel83))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nutrientproperty_CreatedByTextField)
                    .addComponent(nutrientproperty_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addGap(4, 4, 4)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nutrientproperty_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(nutrientproperty_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel84)
                .addGap(58, 58, 58))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(jLabel81))
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel51Layout.createSequentialGroup()
                                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel85)
                                    .addComponent(nutrientproperty_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel86)
                                    .addComponent(nutrientproperty_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(nutrientproperty_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nutrientproperty_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel83)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_NutrientPropertyLayout = new javax.swing.GroupLayout(jPanel_NutrientProperty);
        jPanel_NutrientProperty.setLayout(jPanel_NutrientPropertyLayout);
        jPanel_NutrientPropertyLayout.setHorizontalGroup(
            jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NutrientPropertyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_NutrientPropertyLayout.createSequentialGroup()
                        .addComponent(nutrientproperty_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel77))
                    .addGroup(jPanel_NutrientPropertyLayout.createSequentialGroup()
                        .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel_NutrientPropertyLayout.setVerticalGroup(
            jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NutrientPropertyLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nutrientproperty_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_NutrientPropertyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_NutrientPropertyLayout.createSequentialGroup()
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nutrient Property", jPanel_NutrientProperty);

        jPanel_CompoundGroup.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_CompoundGroup.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_CompoundGroup.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel87.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel87.setText("CompoundGroup Information");

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel88.setText("Name");

        compoundgroup_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundgroup_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundgroup_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundgroup_editBtn.setText("Edit");
        compoundgroup_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundgroup_editBtn.setIconTextGap(5);
        compoundgroup_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_editBtnActionPerformed(evt);
            }
        });

        compoundgroup_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundgroup_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundgroup_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundgroup_addnewbtn.setText("New");
        compoundgroup_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundgroup_addnewbtn.setIconTextGap(5);
        compoundgroup_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_addnewbtnActionPerformed(evt);
            }
        });

        compoundgroup_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundgroup_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundgroup_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundgroup_addListBtn.setText("Add List");
        compoundgroup_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundgroup_addListBtn.setIconTextGap(5);
        compoundgroup_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_addListBtnActionPerformed(evt);
            }
        });

        compoundgroup_nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_nameTextFieldActionPerformed(evt);
            }
        });

        compoundgroup_isActiveCheckBox.setSelected(true);
        compoundgroup_isActiveCheckBox.setText("IsActive");

        compoundgroup_deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundgroup_deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundgroup_deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundgroup_deletebtn.setText("Delete");
        compoundgroup_deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundgroup_deletebtn.setIconTextGap(5);
        compoundgroup_deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_deletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(compoundgroup_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundgroup_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundgroup_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundgroup_addListBtn))
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(compoundgroup_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createSequentialGroup()
                                .addComponent(compoundgroup_isActiveCheckBox)
                                .addGap(90, 90, 90)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compoundgroup_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88))
                .addGap(115, 115, 115)
                .addComponent(compoundgroup_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compoundgroup_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundgroup_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundgroup_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundgroup_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        compoundgroup_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Name", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        compoundgroup_jTable.setRowHeight(20);
        compoundgroup_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        compoundgroup_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compoundgroup_jTableMouseClicked(evt);
            }
        });
        compoundgroup_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compoundgroup_jTableKeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(compoundgroup_jTable);

        jPanel54.setBackground(new java.awt.Color(70, 73, 85));

        jLabel89.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("All CompoundGroup List");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel55.setBackground(new java.awt.Color(70, 73, 85));

        jLabel90.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("CompoundGroup Entry");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        compoundgroup_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        compoundgroup_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundgroup_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundgroup_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        compoundgroup_saveallbtn.setToolTipText("Save All");
        compoundgroup_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundgroup_saveallbtn.setIconTextGap(5);
        compoundgroup_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_saveallbtnActionPerformed(evt);
            }
        });

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel91.setText("Created");

        jLabel92.setText("On:");

        jLabel93.setText("By:");

        jLabel94.setText("Updated");

        jLabel95.setText("On:");

        jLabel96.setText("By:");

        compoundgroup_updatedOnTextField.setEditable(false);
        compoundgroup_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundgroup_updatedOnTextFieldActionPerformed(evt);
            }
        });

        compoundgroup_UpdatedByTextField.setEditable(false);

        compoundgroup_createdOnTextField.setEditable(false);

        compoundgroup_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92)
                    .addComponent(jLabel93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compoundgroup_CreatedByTextField)
                    .addComponent(compoundgroup_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95)
                    .addComponent(jLabel96))
                .addGap(4, 4, 4)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compoundgroup_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(compoundgroup_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(jLabel94)
                .addGap(58, 58, 58))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(jLabel91))
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel56Layout.createSequentialGroup()
                                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel95)
                                    .addComponent(compoundgroup_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel96)
                                    .addComponent(compoundgroup_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(compoundgroup_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compoundgroup_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel93)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_CompoundGroupLayout = new javax.swing.GroupLayout(jPanel_CompoundGroup);
        jPanel_CompoundGroup.setLayout(jPanel_CompoundGroupLayout);
        jPanel_CompoundGroupLayout.setHorizontalGroup(
            jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                        .addComponent(compoundgroup_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                        .addComponent(jLabel87))
                    .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                        .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        jPanel_CompoundGroupLayout.setVerticalGroup(
            jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compoundgroup_saveallbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_CompoundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_CompoundGroupLayout.createSequentialGroup()
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Compound Group", jPanel_CompoundGroup);

        jPanel_CompoundType.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_CompoundType.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_CompoundType.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel97.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel97.setText("Compound Type");

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel98.setText("Name");

        compoundtype_editBtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundtype_editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundtype_editBtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundtype_editBtn.setText("Edit");
        compoundtype_editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundtype_editBtn.setIconTextGap(5);
        compoundtype_editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_editBtnActionPerformed(evt);
            }
        });

        compoundtype_addnewbtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundtype_addnewbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundtype_addnewbtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundtype_addnewbtn.setText("New");
        compoundtype_addnewbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundtype_addnewbtn.setIconTextGap(5);
        compoundtype_addnewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_addnewbtnActionPerformed(evt);
            }
        });

        compoundtype_addListBtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundtype_addListBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundtype_addListBtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundtype_addListBtn.setText("Add List");
        compoundtype_addListBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundtype_addListBtn.setIconTextGap(5);
        compoundtype_addListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_addListBtnActionPerformed(evt);
            }
        });

        compoundtype_nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_nameTextFieldActionPerformed(evt);
            }
        });

        compoundtype_isActiveCheckBox.setSelected(true);
        compoundtype_isActiveCheckBox.setText("IsActive");

        compoundtype_deletebtn.setBackground(new java.awt.Color(255, 0, 95));
        compoundtype_deletebtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundtype_deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundtype_deletebtn.setText("Delete");
        compoundtype_deletebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundtype_deletebtn.setIconTextGap(5);
        compoundtype_deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_deletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(compoundtype_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundtype_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundtype_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundtype_addListBtn))
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(compoundtype_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                                .addComponent(compoundtype_isActiveCheckBox)
                                .addGap(90, 90, 90)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compoundtype_nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98))
                .addGap(115, 115, 115)
                .addComponent(compoundtype_isActiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compoundtype_addnewbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundtype_deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundtype_editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundtype_addListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        compoundtype_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Name", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        compoundtype_jTable.setRowHeight(20);
        compoundtype_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        compoundtype_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compoundtype_jTableMouseClicked(evt);
            }
        });
        compoundtype_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compoundtype_jTableKeyPressed(evt);
            }
        });
        jScrollPane11.setViewportView(compoundtype_jTable);

        jPanel59.setBackground(new java.awt.Color(70, 73, 85));

        jLabel99.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("All CompoundType List");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel60.setBackground(new java.awt.Color(70, 73, 85));

        jLabel100.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("CompoundType Entry");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel100)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        compoundtype_saveallbtn.setBackground(new java.awt.Color(255, 255, 255));
        compoundtype_saveallbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        compoundtype_saveallbtn.setForeground(new java.awt.Color(255, 255, 255));
        compoundtype_saveallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/savecolor.png"))); // NOI18N
        compoundtype_saveallbtn.setToolTipText("Save All");
        compoundtype_saveallbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        compoundtype_saveallbtn.setIconTextGap(5);
        compoundtype_saveallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_saveallbtnActionPerformed(evt);
            }
        });

        jPanel61.setBackground(new java.awt.Color(255, 255, 255));
        jPanel61.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel101.setText("Created");

        jLabel102.setText("On:");

        jLabel103.setText("By:");

        jLabel104.setText("Updated");

        jLabel105.setText("On:");

        jLabel106.setText("By:");

        compoundtype_updatedOnTextField.setEditable(false);
        compoundtype_updatedOnTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundtype_updatedOnTextFieldActionPerformed(evt);
            }
        });

        compoundtype_UpdatedByTextField.setEditable(false);

        compoundtype_createdOnTextField.setEditable(false);

        compoundtype_CreatedByTextField.setEditable(false);

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compoundtype_CreatedByTextField)
                    .addComponent(compoundtype_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel105)
                    .addComponent(jLabel106))
                .addGap(4, 4, 4)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compoundtype_updatedOnTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(compoundtype_UpdatedByTextField))
                .addContainerGap())
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel104)
                .addGap(58, 58, 58))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel104)
                            .addComponent(jLabel101))
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel61Layout.createSequentialGroup()
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel105)
                                    .addComponent(compoundtype_updatedOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel106)
                                    .addComponent(compoundtype_UpdatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(compoundtype_createdOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compoundtype_CreatedByTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel103)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_CompoundTypeLayout = new javax.swing.GroupLayout(jPanel_CompoundType);
        jPanel_CompoundType.setLayout(jPanel_CompoundTypeLayout);
        jPanel_CompoundTypeLayout.setHorizontalGroup(
            jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundTypeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CompoundTypeLayout.createSequentialGroup()
                        .addGroup(jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CompoundTypeLayout.createSequentialGroup()
                        .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_CompoundTypeLayout.createSequentialGroup()
                        .addComponent(compoundtype_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel97)))
                .addGap(26, 26, 26))
        );
        jPanel_CompoundTypeLayout.setVerticalGroup(
            jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CompoundTypeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(compoundtype_saveallbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_CompoundTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CompoundTypeLayout.createSequentialGroup()
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11))
                .addGap(100, 100, 100))
        );

        jTabbedPane1.addTab("Compound Type", jPanel_CompoundType);

        jPanel_Dashboard.setBackground(new java.awt.Color(241, 241, 241));
        jPanel_Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel27.setBackground(new java.awt.Color(255, 152, 0));

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Compound");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/opt.png"))); // NOI18N

        totcomlbl.setFont(new java.awt.Font("DejaVu Sans", 1, 22)); // NOI18N
        totcomlbl.setForeground(new java.awt.Color(255, 255, 255));
        totcomlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totcomlbl.setText("66999");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(totcomlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totcomlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 150, 136));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/mail.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Total PriceList");

        totprilbl.setFont(new java.awt.Font("DejaVu Sans", 1, 22)); // NOI18N
        totprilbl.setForeground(new java.awt.Color(255, 255, 255));
        totprilbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totprilbl.setText("55398");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(totprilbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totprilbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(211, 47, 43));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/sysnovafeed.jpg"))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(244, 67, 54));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/mail1.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Nutrient");

        totnutlbl.setFont(new java.awt.Font("DejaVu Sans", 1, 22)); // NOI18N
        totnutlbl.setForeground(new java.awt.Color(255, 255, 255));
        totnutlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totnutlbl.setText("55232");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(totnutlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totnutlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(33, 150, 243));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/poultry.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total Ingredient");

        totinglbl.setFont(new java.awt.Font("DejaVu Sans", 1, 22)); // NOI18N
        totinglbl.setForeground(new java.awt.Color(255, 255, 255));
        totinglbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totinglbl.setText("55329");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totinglbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(totinglbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(159, 119, 79));
        jPanel10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(240, 4));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/das1.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setBorder(null);
        jButton2.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setBorder(null);
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel39.setText("Developed By : www.sysnova.com");

        javax.swing.GroupLayout jPanel_DashboardLayout = new javax.swing.GroupLayout(jPanel_Dashboard);
        jPanel_Dashboard.setLayout(jPanel_DashboardLayout);
        jPanel_DashboardLayout.setHorizontalGroup(
            jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DashboardLayout.createSequentialGroup()
                        .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_DashboardLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel39)))
                .addGap(69, 69, 69))
        );
        jPanel_DashboardLayout.setVerticalGroup(
            jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                    .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel_DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel_DashboardLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel39)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Dashboard", jPanel_Dashboard);

        jPanel_Pricelist.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Pricelist.setForeground(new java.awt.Color(254, 254, 254));
        jPanel_Pricelist.setPreferredSize(new java.awt.Dimension(900, 470));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel31.setText("Weekly Price Information");

        PriceList_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Description", "Valid From", "Valid To", "Changed By", "Changed On", "Active"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PriceList_jTable.setRowHeight(20);
        PriceList_jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PriceList_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PriceList_jTableMouseClicked(evt);
            }
        });
        PriceList_jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PriceList_jTableKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(PriceList_jTable);

        jPanel20.setBackground(new java.awt.Color(70, 73, 85));

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("All Price List");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pricelistRefreshBtn.setBackground(new java.awt.Color(255, 255, 255));
        pricelistRefreshBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        pricelistRefreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        pricelistRefreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/refresh.png"))); // NOI18N
        pricelistRefreshBtn.setToolTipText("Home");
        pricelistRefreshBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pricelistRefreshBtn.setIconTextGap(5);
        pricelistRefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricelistRefreshBtnActionPerformed(evt);
            }
        });

        deleteBtn1.setBackground(new java.awt.Color(182, 142, 144));
        deleteBtn1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        deleteBtn1.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn1.setText("Delete");
        deleteBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn1.setIconTextGap(5);
        deleteBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtn1ActionPerformed(evt);
            }
        });

        newbtn1.setBackground(new java.awt.Color(182, 142, 144));
        newbtn1.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        newbtn1.setForeground(new java.awt.Color(255, 255, 255));
        newbtn1.setText("New");
        newbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newbtn1.setIconTextGap(5);
        newbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbtn1ActionPerformed(evt);
            }
        });

        editbtn.setBackground(new java.awt.Color(182, 142, 144));
        editbtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        editbtn.setForeground(new java.awt.Color(255, 255, 255));
        editbtn.setText("Edit");
        editbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbtn.setIconTextGap(5);
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });

        detailsBtn.setBackground(new java.awt.Color(182, 142, 144));
        detailsBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        detailsBtn.setForeground(new java.awt.Color(255, 255, 255));
        detailsBtn.setText("Details");
        detailsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detailsBtn.setIconTextGap(5);
        detailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_PricelistLayout = new javax.swing.GroupLayout(jPanel_Pricelist);
        jPanel_Pricelist.setLayout(jPanel_PricelistLayout);
        jPanel_PricelistLayout.setHorizontalGroup(
            jPanel_PricelistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PricelistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_PricelistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_PricelistLayout.createSequentialGroup()
                        .addGap(0, 307, Short.MAX_VALUE)
                        .addComponent(detailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_PricelistLayout.createSequentialGroup()
                        .addComponent(pricelistRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel_PricelistLayout.setVerticalGroup(
            jPanel_PricelistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PricelistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_PricelistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pricelistRefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_PricelistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(deleteBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newbtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(101, 101, 101))
        );

        jTabbedPane1.addTab("Price List", jPanel_Pricelist);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel5);

        jPanel26.setBackground(new java.awt.Color(70, 73, 85));
        jPanel26.setPreferredSize(new java.awt.Dimension(109, 28));

        logoutBtn.setBackground(new java.awt.Color(57, 63, 63));
        logoutBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("About Us");
        logoutBtn.setToolTipText("Pree Here For logout");
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setIconTextGap(5);
        logoutBtn.setRequestFocusEnabled(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userNameLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userNameLabelMouseClicked
        // TODO add your handling code here:

        jTabbedPane1.remove(jPanel_Nutrient);
        jTabbedPane1.remove(jPanel_Ingredient);
        jTabbedPane1.remove(jPanel_Pricelist);
        jTabbedPane1.remove(jPanel_Compound);
        jTabbedPane1.remove(jPanel_UserInfo);
        jTabbedPane1.remove(jPanel_IngredientGroup);
        jTabbedPane1.remove(jPanel_IngredientType);
        jTabbedPane1.remove(jPanel_UnitEntry);
        jTabbedPane1.remove(jPanel_NutrientProperty);
        jTabbedPane1.remove(jPanel_CompoundGroup);
        jTabbedPane1.remove(jPanel_CompoundType);
        jTabbedPane1.add(jPanel_Dashboard);
        jTabbedPane1.setTitleAt(0, "DashBoard");
    }//GEN-LAST:event_userNameLabelMouseClicked

    private void newbtn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn9ActionPerformed

        
    private void nutrientbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientbtnActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_Nutrient);
        //Nutrient a =new Nutrient();
        a=new Nutrient(nutrient_codeTextField, nutrient_nameTextField, nutrient_longTextField, nutrient_isActiveCheckBox,
            nutrient_layerCheckBox, nutrient_fishCheckBox, nutrient_otherCheckBox, nutrient_broilerCheckBox, nutrient_editBtn,
            nutrient_addnewbtn, nutrient_addListBtn, nutrient_createdOnTextField, nutrient_updatedOnTextField, nutrient_CreatedByTextField, nutrient_UpdatedByTextField);

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_Nutrient, index);
            jTabbedPane1.setTitleAt(index, "Nutrient");
            jTabbedPane1.setSelectedIndex(index);

            boolean Saveremaining = false;
            nutrient_saveallbtn.setEnabled(false);
         
     

            a.NutrientWork(nutrient_unitComboBox,nutrient_propertyComboBox,nutrient_unitList,nutrient_propertyList);
            a.loadNutrientData(nutrient_jTable,nutrient_tableListData);
            
            try {
                nutrient_tableListData=sqlManager.getNutrientdata();
                nutrient_unitList=sqlManager.getNutrientUnitComboodata();
                nutrient_propertyList=sqlManager.getNutrientPropertyComboodata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{

            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_Nutrient, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Nutrient");
            jTabbedPane1.setSelectedIndex(tabcount);
            boolean Saveremaining = false;
            nutrient_saveallbtn.setEnabled(false);

            a.NutrientWork(nutrient_unitComboBox,nutrient_propertyComboBox,nutrient_unitList,nutrient_propertyList);
            a.loadNutrientData(nutrient_jTable,nutrient_tableListData);
            
            try {
                nutrient_tableListData=sqlManager.getNutrientdata();
                nutrient_unitList=sqlManager.getNutrientUnitComboodata();
                nutrient_propertyList=sqlManager.getNutrientPropertyComboodata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_nutrientbtnActionPerformed

    ArrayList ingredient_tableListData=new ArrayList();
    private void ingredientbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientbtnActionPerformed
        // TODO add your handling code here:

         int index = jTabbedPane1.indexOfComponent(jPanel_Ingredient);
        i=new Ingredient();

        if (index > 0) {
      // The tab could not be found
            jTabbedPane1.add(jPanel_Ingredient, index);
            jTabbedPane1.setTitleAt(index, "Ingredient");
            jTabbedPane1.setSelectedIndex(index);

            //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
            i.loadIngredientData(ingredient_tableListData,ingredient_jTable);
            try {
                ingredient_tableListData=sqlManager.getIngrediantdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
//            userNameLabel3.setText("");
//            userNameLabel3.setText("Hello, "+sqlManager.getUserName());
     
        }
        else{

            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_Ingredient, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Ingredient");
            jTabbedPane1.setSelectedIndex(tabcount);

           // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
            i.loadIngredientData(ingredient_tableListData,ingredient_jTable);
            try {
                ingredient_tableListData=sqlManager.getIngrediantdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
//            userNameLabel3.setText("");
//            userNameLabel3.setText("Hello, "+sqlManager.getUserName());
         }
    }//GEN-LAST:event_ingredientbtnActionPerformed

    ArrayList pricelist_tableListData=new ArrayList();
    private void newbtn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn7ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_Pricelist);
        p=new PriceList();

        if (index > 0) {
          // The tab could not be found
            jTabbedPane1.add(jPanel_Pricelist, index);
            jTabbedPane1.setTitleAt(index, "Price List");
            jTabbedPane1.setSelectedIndex(index);
            
            
            
            p.loadpricelistData(pricelist_tableListData, PriceList_jTable);
            try {
                pricelist_tableListData=sqlManager.getPRICEWEEK();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        else{
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_Pricelist, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Price List");
            jTabbedPane1.setSelectedIndex(tabcount);
            
            
            p.loadpricelistData(pricelist_tableListData, PriceList_jTable);
            try {
                pricelist_tableListData=sqlManager.getPRICEWEEK();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_newbtn7ActionPerformed

     ArrayList compound_tableListData=new ArrayList();
    private void newbtn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn14ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_Compound);

        c=new Compound();

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_Compound, index);
            jTabbedPane1.setTitleAt(index, "Compound");
            jTabbedPane1.setSelectedIndex(index);

            c.loadCompoundData(compound_tableListData, compound_jTable);
            try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_Compound, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Compound");
            jTabbedPane1.setSelectedIndex(tabcount);

            c.loadCompoundData(compound_tableListData, compound_jTable);
            try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_newbtn14ActionPerformed

    ArrayList userinfo_tableListData=new ArrayList();
     ArrayList ingredientgroup_tableListData=new ArrayList();
    private void newbtn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn8ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_IngredientGroup);

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_IngredientGroup, index);
            jTabbedPane1.setTitleAt(index, "Ingredient Group");
            jTabbedPane1.setSelectedIndex(index);

            ig=new IngredientGroup(ingredientgroup_Saveremaining,ingredientgroup_saveallbtn,ingredientgroup_tableListData
                ,ingredientgroup_IngredientNameTextField,ingredientgroup_isActiveCheckBox,ingredientgroup_editBtn,ingredientgroup_addnewbtn
                ,ingredientgroup_addListBtn,ingredientgroup_createdOnTextField,ingredientgroup_updatedOnTextField
                ,ingredientgroup_CreatedByTextField,ingredientgroup_UpdatedByTextField,ingredientgroup_jTable);

            try {
                ingredientgroup_tableListData=sqlManager.getIngredientGrpdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_IngredientGroup, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Ingredient Group");
            jTabbedPane1.setSelectedIndex(tabcount);

            ig=new IngredientGroup(ingredientgroup_Saveremaining,ingredientgroup_saveallbtn,ingredientgroup_tableListData
                ,ingredientgroup_IngredientNameTextField,ingredientgroup_isActiveCheckBox,ingredientgroup_editBtn,ingredientgroup_addnewbtn
                ,ingredientgroup_addListBtn,ingredientgroup_createdOnTextField,ingredientgroup_updatedOnTextField
                ,ingredientgroup_CreatedByTextField,ingredientgroup_UpdatedByTextField,ingredientgroup_jTable);

            try {
                ingredientgroup_tableListData=sqlManager.getIngredientGrpdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_newbtn8ActionPerformed

}
    ArrayList ingredienttype_tableListData=new ArrayList();
    private void newbtn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn11ActionPerformed
        // TODO add your handling

        int index = jTabbedPane1.indexOfComponent(jPanel_IngredientType);

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_IngredientType, index);
            jTabbedPane1.setTitleAt(index, "Ingredient Type");
            jTabbedPane1.setSelectedIndex(index);

            it=new IngredientType(ingredienttype_Saveremaining, ingredienttype_insertID,
                ingredienttype_saveallbtn, ingredienttype_tableListData, ingredienttype_jTable,
                ingredienttype_CreatedByTextField, ingredienttype_isActiveCheckBox, ingredienttype_editBtn,
                ingredienttype_addnewbtn, ingredienttype_addListBtn,ingredienttype_Deletebtn,
                ingredienttype_createdOnTextField, ingredienttype_updatedOnTextField, ingredienttype_CreatedByTextField,
                ingredienttype_UpdatedByTextField);

            try {
                ingredienttype_tableListData=sqlManager.getIngredientTypedata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
        }
        else{
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_IngredientType, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Ingredient Type");
            jTabbedPane1.setSelectedIndex(tabcount);

            it=new IngredientType(ingredienttype_Saveremaining, ingredienttype_insertID,
                ingredienttype_saveallbtn, ingredienttype_tableListData, ingredienttype_jTable,
                ingredienttype_CreatedByTextField, ingredienttype_isActiveCheckBox, ingredienttype_editBtn,
                ingredienttype_addnewbtn, ingredienttype_addListBtn,ingredienttype_Deletebtn,
                ingredienttype_createdOnTextField, ingredienttype_updatedOnTextField, ingredienttype_CreatedByTextField,
                ingredienttype_UpdatedByTextField);

            try {
                ingredienttype_tableListData=sqlManager.getIngredientTypedata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_newbtn11ActionPerformed
    
    ArrayList  unit_tableListData=new ArrayList();
    private void newbtn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn19ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_UnitEntry);

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_UnitEntry, index);
            jTabbedPane1.setTitleAt(index, "Unit Entry");
            jTabbedPane1.setSelectedIndex(index);

            unit=new Unit(unit_Saveremaining,unit_tableListData,unit_codeTextField,unit_nameTextField
                ,unit_isActiveCheckBox,unit_addListBtn,unit_createdOnTextField,unit_updatedOnTextField
                ,unit_CreatedByTextField,unit_UpdatedByTextField,unit_jTable);

            try {
                unit_tableListData=sqlManager.getUnitdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_UnitEntry, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Unit Entry");
            jTabbedPane1.setSelectedIndex(tabcount);

            unit=new Unit(unit_Saveremaining,unit_tableListData,unit_codeTextField,unit_nameTextField
                ,unit_isActiveCheckBox,unit_addListBtn,unit_createdOnTextField,unit_updatedOnTextField
                ,unit_CreatedByTextField,unit_UpdatedByTextField,unit_jTable);

            try {
                unit_tableListData=sqlManager.getUnitdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_newbtn19ActionPerformed

    ArrayList nutrientproperty_tableListData=new ArrayList();
    private void newbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn4ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_NutrientProperty);

        if (index > 0)
        {
            // The tab could not be found
            jTabbedPane1.add(jPanel_NutrientProperty, index);
            jTabbedPane1.setTitleAt(index, "Nutrient Property");
            jTabbedPane1.setSelectedIndex(index);

            np=new NutrientProperty(nutrientproperty_tableListData,nutrientproperty_jTable,nutrientproperty_Saveremaining, nutrientproperty_saveallbtn
                , nutrientproperty_editBtn,  nutrientproperty_addnewbtn,  nutrientproperty_addListBtn, nutrientproperty_Deletebtn, nutrientproperty_NuPropertyNameTextField
                , nutrientproperty_isActiveCheckBox, nutrientproperty_createdOnTextField, nutrientproperty_updatedOnTextField
                , nutrientproperty_CreatedByTextField, nutrientproperty_UpdatedByTextField);

            try {
                nutrientproperty_tableListData=sqlManager.getNuPropertydata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_NutrientProperty, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Nutrient Property");
            jTabbedPane1.setSelectedIndex(tabcount);

            np=new NutrientProperty(nutrientproperty_tableListData,nutrientproperty_jTable,nutrientproperty_Saveremaining, nutrientproperty_saveallbtn
                , nutrientproperty_editBtn,  nutrientproperty_addnewbtn,  nutrientproperty_addListBtn, nutrientproperty_Deletebtn, nutrientproperty_NuPropertyNameTextField
                , nutrientproperty_isActiveCheckBox, nutrientproperty_createdOnTextField, nutrientproperty_updatedOnTextField
                , nutrientproperty_CreatedByTextField, nutrientproperty_UpdatedByTextField);

            try {
                nutrientproperty_tableListData=sqlManager.getNuPropertydata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_newbtn4ActionPerformed

      ArrayList compoundgroup_tableListData=new ArrayList();
    private void newbtn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn22ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_CompoundGroup);

        if (index > 0)
        {
            // The tab could not be found
            jTabbedPane1.add(jPanel_CompoundGroup, index);
            jTabbedPane1.setTitleAt(index, "Compound Group");
            jTabbedPane1.setSelectedIndex(index);

            cg=new Compoundgroup(compoundgroup_Saveremaining, compoundgroup_tableListData,compoundgroup_jTable,
                compoundgroup_nameTextField, compoundgroup_isActiveCheckBox, compoundgroup_addListBtn,
                compoundgroup_createdOnTextField, compoundgroup_updatedOnTextField, compoundgroup_CreatedByTextField,
                compoundgroup_UpdatedByTextField);

            try {
                compoundgroup_tableListData=sqlManager.getCompoundgroupdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_CompoundGroup, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Compound Group");
            jTabbedPane1.setSelectedIndex(tabcount);

            cg=new Compoundgroup(compoundgroup_Saveremaining, compoundgroup_tableListData,compoundgroup_jTable,
                compoundgroup_nameTextField, compoundgroup_isActiveCheckBox, compoundgroup_addListBtn,
                compoundgroup_createdOnTextField, compoundgroup_updatedOnTextField, compoundgroup_CreatedByTextField,
                compoundgroup_UpdatedByTextField);

            try {
                compoundgroup_tableListData=sqlManager.getCompoundgroupdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_newbtn22ActionPerformed

      ArrayList compoundtype_tableListData=new ArrayList();
    private void newbtn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn24ActionPerformed
        // TODO add your handling code here:

        int index = jTabbedPane1.indexOfComponent(jPanel_CompoundType);

        if (index > 0)
        {
            // The tab could not be found
            jTabbedPane1.add(jPanel_CompoundType, index);
            jTabbedPane1.setTitleAt(index, "Compound Type");
            jTabbedPane1.setSelectedIndex(index);

            ct=new Compoundtype( compoundtype_tableListData  , compoundtype_jTable, compoundtype_Saveremaining, compoundtype_nameTextField,
                compoundtype_isActiveCheckBox, compoundtype_addListBtn, compoundtype_createdOnTextField
                , compoundtype_updatedOnTextField, compoundtype_CreatedByTextField, compoundtype_UpdatedByTextField);

            try {
                compoundtype_tableListData=sqlManager.getCompoundType();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            int tabcount=jTabbedPane1.getTabCount();
            jTabbedPane1.add(jPanel_CompoundType, tabcount);
            jTabbedPane1.setTitleAt(tabcount, "Compound Type");
            jTabbedPane1.setSelectedIndex(tabcount);

            ct=new Compoundtype( compoundtype_tableListData  , compoundtype_jTable, compoundtype_Saveremaining, compoundtype_nameTextField,
                compoundtype_isActiveCheckBox, compoundtype_addListBtn, compoundtype_createdOnTextField
                , compoundtype_updatedOnTextField, compoundtype_CreatedByTextField, compoundtype_UpdatedByTextField);

            try {
                compoundtype_tableListData=sqlManager.getCompoundType();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_newbtn24ActionPerformed

    private void newbtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn5ActionPerformed
        // TODO add your handling code here:
        FormulationCostReport FormulationCostReport = new FormulationCostReport();
        FormulationCostReport.setLocation(400, 250);
        FormulationCostReport.setVisible(true);
    }//GEN-LAST:event_newbtn5ActionPerformed

    private void newbtn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn27ActionPerformed
        // TODO add your handling code here:
        blendOrderReport blendOrderReport = new blendOrderReport();
        blendOrderReport.setLocation(400, 250);
        blendOrderReport.setVisible(true);
    }//GEN-LAST:event_newbtn27ActionPerformed

    private void newbtn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn28ActionPerformed
        // TODO add your handling code here:
        IngredientDetailsReport IngredientDetailsReport = new IngredientDetailsReport();
        IngredientDetailsReport.setLocation(400, 250);
        IngredientDetailsReport.setVisible(true);
    }//GEN-LAST:event_newbtn28ActionPerformed

    private void nutrient_codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_codeTextFieldActionPerformed

    private void nutrient_longTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_longTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_longTextFieldActionPerformed

    private void nutrient_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_editBtnActionPerformed

        nutrient_codeTextField.setEditable(true);
        nutrient_nameTextField.setEditable(true);
        nutrient_longTextField.setEditable(true);

        nutrient_isActiveCheckBox.setEnabled(true);
        nutrient_broilerCheckBox.setEnabled(true);
        nutrient_layerCheckBox.setEnabled(true);
        nutrient_fishCheckBox.setEnabled(true);
        nutrient_otherCheckBox.setEnabled(true);
        nutrient_editBtn.setEnabled(false);
        nutrient_addnewbtn.setEnabled(true);
        nutrient_unitComboBox.setEnabled(true);
        nutrient_propertyComboBox.setEnabled(true);
        nutrient_addListBtn.setEnabled(true);
        nutrient_editnow_ID=1;
    }//GEN-LAST:event_nutrient_editBtnActionPerformed

    private void nutrient_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_addnewbtnActionPerformed
        // TODO add your handling code here:
        nutrient_codeTextField.setText("");
        nutrient_nameTextField.setText("");
        nutrient_longTextField.setText("");
        nutrient_codeTextField.setEditable(true);
        nutrient_nameTextField.setEditable(true);
        nutrient_longTextField.setEditable(true);
        nutrient_unitComboBox.setSelectedIndex(0);
        nutrient_propertyComboBox.setSelectedIndex(0);
        nutrient_isActiveCheckBox.setSelected(true);
        nutrient_broilerCheckBox.setSelected(false);
        nutrient_layerCheckBox.setSelected(false);
        nutrient_fishCheckBox.setSelected(false);
        nutrient_otherCheckBox.setSelected(false);
        nutrient_isActiveCheckBox.setEnabled(true);
        nutrient_broilerCheckBox.setEnabled(true);
        nutrient_layerCheckBox.setEnabled(true);
        nutrient_fishCheckBox.setEnabled(true);
        nutrient_otherCheckBox.setEnabled(true);
        nutrient_editBtn.setEnabled(false);
        nutrient_addnewbtn.setEnabled(false);
        nutrient_unitComboBox.setEnabled(true);
        nutrient_propertyComboBox.setEnabled(true);
        nutrient_addListBtn.setEnabled(true);
        nutrient_status_ID=-1;
        nutrient_editNutraint_ID=-1;
        nutrient_editnow_ID=0;
        nutrient_createdOnTextField.setText("");
        nutrient_updatedOnTextField.setText("");
        nutrient_CreatedByTextField.setText("");
        nutrient_UpdatedByTextField.setText("");
    }//GEN-LAST:event_nutrient_addnewbtnActionPerformed

    ArrayList nutrient_unitList=new ArrayList();
    ArrayList nutrient_propertyList=new ArrayList();
    private void nutrient_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_addListBtnActionPerformed
        // TODO add your handling code here:

        //a.AddListButtonActionPerformed(nutrient_codeTextField, nutrient_nameTextField, nutrient_longTextField, nutrient_unitComboBox, nutrient_propertyComboBox, nutrient_isActiveCheckBox, nutrient_layerCheckBox, nutrient_fishCheckBox, nutrient_otherCheckBox, nutrient_broilerCheckBox, nutrient_editBtn, nutrient_addnewbtn, nutrient_addListBtn, nutrient_saveallbtn, nutrient_jTable,nutrient_tableListData);

        
        String code=nutrient_codeTextField.getText();
        if(code.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Code", "Code Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String name=nutrient_nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Short Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String longText=nutrient_longTextField.getText();
        if(longText.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Long Name", "Long Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        
        String unitValue = nutrient_unitComboBox.getSelectedItem().toString();
        int unit_Index=nutrient_unitComboBox.getSelectedIndex();
        Vector unit =(Vector) nutrient_unitList.get(unit_Index);
        int Unit_ID = (Integer) unit.get(0);
        
        String propertyValue = nutrient_propertyComboBox.getSelectedItem().toString();
        int property_Index=nutrient_propertyComboBox.getSelectedIndex();
        Vector property =(Vector) nutrient_propertyList.get(property_Index);
        int property_ID = (Integer) property.get(0);
        
        boolean isActiveCheck=nutrient_isActiveCheckBox.isSelected();
        boolean layerCheck=nutrient_layerCheckBox.isSelected();
        boolean fishCheck=nutrient_fishCheckBox.isSelected();
        boolean otherCheck=nutrient_otherCheckBox.isSelected();
        boolean broilerCheck=nutrient_broilerCheckBox.isSelected();
        
        for(int i=0;i<nutrient_tableListData.size();i++)
        {
            Vector tableline =(Vector) nutrient_tableListData.get(i);

            int Nutraint_ID=(int) tableline.get(12);
            if(nutrient_editNutraint_ID==Nutraint_ID) continue;
            
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
        if(nutrient_editNutraint_ID<1){
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
            line.add(nutrient_insertID);//12 ======== NUTRIENT_ID previous
            line.add(nutrient_status_ID);//14========1 newadd 2update 0 for exits 
            line.add(""); //15
            line.add(""); //16
            line.add(""); //17
            line.add(""); //18
            nutrient_tableListData.add(line);
            nutrient_insertID++;
        }
        else{
            for(int i=0;i<nutrient_tableListData.size();i++)
            {
                Vector line =(Vector) nutrient_tableListData.get(i);
                int Nutraint_ID=(int) line.get(12);
                if(nutrient_editNutraint_ID==Nutraint_ID){
                    nutrient_status_ID=2;
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
                    line.set(12,nutrient_editNutraint_ID);//12 ======== NUTRIENT_ID previous
                    line.set(13,nutrient_status_ID);//======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        //editNutraint_ID=(int) tableline.get(12);
        //status_ID=(int) tableline.get(13);

        DefaultTableModel model1 = (DefaultTableModel)nutrient_jTable.getModel();
        model1.setRowCount(nutrient_tableListData.size());
        for(int i=0;i<nutrient_tableListData.size();i++)
        {
                Vector tableline =(Vector) nutrient_tableListData.get(i);
               
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
        nutrient_codeTextField.setText("");
        nutrient_codeTextField.setEditable(true);
        nutrient_nameTextField.setText("");
        nutrient_nameTextField.setEnabled(true);
        nutrient_longTextField.setText("");
        nutrient_isActiveCheckBox.setSelected(true);
        nutrient_broilerCheckBox.setSelected(false);
        nutrient_layerCheckBox.setSelected(false);
        nutrient_fishCheckBox.setSelected(false);
        nutrient_otherCheckBox.setSelected(false);
        
        nutrient_editBtn.setEnabled(false);
        nutrient_addnewbtn.setEnabled(false);
        nutrient_addListBtn.setEnabled(true);
        nutrient_editNutraint_ID=-1;
        nutrient_status_ID=-1;
        nutrient_editnow_ID=0;
        nutrient_saveallbtn.setEnabled(true);
        nutrient_Saveremaining=true;
        
        
    }//GEN-LAST:event_nutrient_addListBtnActionPerformed

    private void nutrient_propertyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_propertyComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_propertyComboBoxActionPerformed

    private void nutrient_nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_nameTextFieldActionPerformed

    private void nutrient_broilerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_broilerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_broilerCheckBoxActionPerformed

    private void nutrient_layerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_layerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrient_layerCheckBoxActionPerformed

    int nutrient_editNutraint_ID=-1;
    int nutrient_status_ID=-1;
    int nutrient_editnow_ID=0;
    private void nutrient_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nutrient_jTableMouseClicked
        // TODO add your handling code here:
        nutrient_editNutraint_ID=-1;
        nutrient_editnow_ID=0;
        nutrient_status_ID=-1;
        nutrient_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        nutrient_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_nutrient_jTableMouseClicked

      ArrayList nutrient_tableListData=new ArrayList();
    private void nutrient_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nutrient_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed Sys"+nutrient_jTable.getSelectedRow());
            if(nutrient_jTable.getSelectedRow()!=0)
            nutrient_TableMouseorKeyChange(nutrient_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+nutrient_jTable.getSelectedRow());
            if(nutrient_jTable.getSelectedRow()!=nutrient_tableListData.size()-1)
            nutrient_TableMouseorKeyChange(nutrient_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+nutrient_jTable.getSelectedRow());
            if(nutrient_jTable.getSelectedRow()!=nutrient_tableListData.size()-1)
            nutrient_TableMouseorKeyChange(nutrient_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_nutrient_jTableKeyPressed

    private void nutrient_TableMouseorKeyChange(int row){
        
        nutrient_createdOnTextField.setText("");
        nutrient_updatedOnTextField.setText("");
        nutrient_CreatedByTextField.setText("");
        nutrient_UpdatedByTextField.setText("");
        
        //CreatedByTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        //UpdatedByTextField.setHorizontalAlignment(SwingConstants.LEFT);
        
        
        
        Vector tableline =(Vector) nutrient_tableListData.get(row);
               
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
        nutrient_createdOnTextField.setText(""+createdOn);
        nutrient_updatedOnTextField.setText((String) tableline.get(15));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(17);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        nutrient_CreatedByTextField.setText(""+createdby);
        nutrient_UpdatedByTextField.setText(updatedby);
        nutrient_codeTextField.setEditable(false);
        nutrient_nameTextField.setEditable(false);
        nutrient_longTextField.setEditable(false);
        nutrient_isActiveCheckBox.setEnabled(false);
        nutrient_broilerCheckBox.setEnabled(false);
        nutrient_layerCheckBox.setEnabled(false);
        nutrient_fishCheckBox.setEnabled(false);
        nutrient_otherCheckBox.setEnabled(false);
        nutrient_unitComboBox.setEnabled(false);
        nutrient_propertyComboBox.setEnabled(false);
        
        for(int i=0;i<nutrient_unitList.size();i++)
        {
                Vector line =(Vector) nutrient_unitList.get(i);
                int Unit_ID = (Integer) line.get(0);
                if(unitid==Unit_ID){
                    nutrient_unitComboBox.setSelectedIndex(i);
                     break;
                }
        }
        for(int i=0;i<nutrient_propertyList.size();i++)
        {
                Vector line =(Vector) nutrient_propertyList.get(i);
                int propertyID = (Integer) line.get(0);
                if(propertyid==propertyID){
                    nutrient_propertyComboBox.setSelectedIndex(i);
                }
               
        }

        nutrient_codeTextField.setText(""+cod);
        nutrient_nameTextField.setText(""+nam);
        nutrient_longTextField.setText(""+lngtxt);
        nutrient_isActiveCheckBox.setSelected(isact);
        
        Boolean layerCheck = (Boolean) tableline.get(8);
        Boolean broilerCheck = (Boolean) tableline.get(9);
        Boolean fishCheck = (Boolean) tableline.get(10);
        Boolean otherCheck = (Boolean) tableline.get(11);
        
        nutrient_broilerCheckBox.setSelected(broilerCheck);
        nutrient_layerCheckBox.setSelected(layerCheck);
        nutrient_fishCheckBox.setSelected(fishCheck);
        nutrient_otherCheckBox.setSelected(otherCheck);
        nutrient_editNutraint_ID=(int) tableline.get(12);
        nutrient_status_ID=(int) tableline.get(13);
        nutrient_editBtn.setEnabled(true);
        nutrient_addnewbtn.setEnabled(true);
        nutrient_addListBtn.setEnabled(false);
        
    }
    
    
    
    
    boolean nutrient_Saveremaining=false;
    int nutrient_insertID=10000000;
    private void nutrient_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrient_saveallbtnActionPerformed
        try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveNutrientdata(nutrient_tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                a.loadNutrientData(nutrient_jTable,nutrient_tableListData);
            try {
                    nutrient_tableListData=sqlManager.getNutrientdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
                nutrient_saveallbtn.setEnabled(false);
                nutrient_Saveremaining=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nutrient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nutrient_saveallbtnActionPerformed

    int selectedRow=-1;
    private void ingredient_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredient_jTableMouseClicked
        // TODO add your handling code here:
        selectedRow= ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
            Vector tableline =(Vector) ingredient_tableListData.get(row);
            int INGREDIENT_ID = (Integer) tableline.get(10);
            //this.dispose();
            IngredientDetails ingredientDetails = new IngredientDetails(row,ingredient_tableListData);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ingredientDetails.setBounds(0,0,screenSize.width, screenSize.height);
            ingredientDetails.setVisible(true);
            //ingredientDetails.setLocation(200, 200);
        }
    }//GEN-LAST:event_ingredient_jTableMouseClicked

    private void ingredient_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingredient_jTableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredient_jTableKeyPressed

    private void ingredientRefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientRefreshBtnActionPerformed
     int index = jTabbedPane1.indexOfComponent(jPanel_Ingredient);
        i=new Ingredient();

        if (index > 0) {
      // The tab could not be found
            jTabbedPane1.add(jPanel_Ingredient, index);
            jTabbedPane1.setTitleAt(index, "Ingredient");
            jTabbedPane1.setSelectedIndex(index);

            //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
            i.loadIngredientData(ingredient_tableListData,ingredient_jTable);
            try {
                ingredient_tableListData=sqlManager.getIngrediantdata();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
//            userNameLabel3.setText("");
//            userNameLabel3.setText("Hello, "+sqlManager.getUserName());
     
        }
    }//GEN-LAST:event_ingredientRefreshBtnActionPerformed

    private void ingredient_deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredient_deleteBtnActionPerformed
        // TODO add your handling code here:
        if(selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        Vector tableline =(Vector) ingredient_tableListData.get(selectedRow);
        int INGREDIENT_ID = (Integer) tableline.get(10);
        try {
            boolean isSuccess=sqlManager.deleteIngredient(INGREDIENT_ID);
            if(isSuccess)
            {
                i.loadIngredientData(ingredient_tableListData,ingredient_jTable);
                JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
            
                try {
                    ingredient_tableListData=sqlManager.getIngrediantdata();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingredient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ingredient_deleteBtnActionPerformed

    private void ingredient_newbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredient_newbtnActionPerformed
        // TODO add your handling code here:
        //this.dispose();
        IngredientDetails ingredientDetails = new IngredientDetails(-1,ingredient_tableListData);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ingredientDetails.setBounds(0,0,screenSize.width, screenSize.height);
        ingredientDetails.setVisible(true);
       // ingredientDetails.setLocation(200, 200);
    }//GEN-LAST:event_ingredient_newbtnActionPerformed

    private void jPanel_IngredientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_IngredientMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel_IngredientMouseClicked

      int pricelist_selectedRow=-1;
    private void PriceList_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PriceList_jTableMouseClicked
        // TODO add your handling code here:

        pricelist_selectedRow= ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
            Vector tableline =(Vector) pricelist_tableListData.get(row);
            Boolean isact = (Boolean) tableline.get(2);
            if(!isact){
                JOptionPane.showMessageDialog(null, "Record is InActive! Please Active First.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //this.dispose();

            PriceListDetails priceListDetails = new PriceListDetails(row,pricelist_tableListData);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            priceListDetails.setBounds(0,0,screenSize.width, screenSize.height);
            priceListDetails.setVisible(true);
            //priceListDetails.setLocation(200, 200);
            try {
                pricelist_tableListData=sqlManager.getPRICEWEEK();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_PriceList_jTableMouseClicked

    private void PriceList_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceList_jTableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceList_jTableKeyPressed

    private void pricelistRefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricelistRefreshBtnActionPerformed

        int index = jTabbedPane1.indexOfComponent(jPanel_Pricelist);
        p=new PriceList();

        if (index > 0) {
          // The tab could not be found
            jTabbedPane1.add(jPanel_Pricelist, index);
            jTabbedPane1.setTitleAt(index, "Price List");
            jTabbedPane1.setSelectedIndex(index);
            
            
            
            p.loadpricelistData(pricelist_tableListData, PriceList_jTable);
            try {
                pricelist_tableListData=sqlManager.getPRICEWEEK();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_pricelistRefreshBtnActionPerformed

    private void deleteBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn1ActionPerformed
        // TODO add your handling code here:
        if(pricelist_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        int confirm = JOptionPane.showOptionDialog(
            null, "Are You Sure to Delete?",
            "Exit Confirmation", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm != 0) {
            return;
        }
        Vector tableline =(Vector) pricelist_tableListData.get(pricelist_selectedRow);
        int PRICEWEEK_ID = (Integer) tableline.get(7);
        try {
            boolean isSuccess=sqlManager.deletepriceweek(PRICEWEEK_ID);
            if(isSuccess)
            {
                p=new PriceList();
                p.loadpricelistData(pricelist_tableListData,PriceList_jTable);
                JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                try {
                    pricelist_tableListData=sqlManager.getPRICEWEEK();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingredient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtn1ActionPerformed

    private void newbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn1ActionPerformed
        // TODO add your handling code here:
        //        this.dispose();
        PriceListCreate PriceListCreate = new PriceListCreate(-1,null);
        PriceListCreate.setLocation(50, 100);
        PriceListCreate.setVisible(true);
    }//GEN-LAST:event_newbtn1ActionPerformed

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        // TODO add your handling code here:
        if(pricelist_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        Vector tableline =(Vector) pricelist_tableListData.get(pricelist_selectedRow);
        int PRICEWEEK_ID = (Integer) tableline.get(7);
        // this.dispose();
        PriceListCreate PriceListCreate = new PriceListCreate(pricelist_selectedRow,pricelist_tableListData);
        PriceListCreate.setLocation(50, 100);
        PriceListCreate.setVisible(true);
    }//GEN-LAST:event_editbtnActionPerformed

    private void detailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtnActionPerformed
        // TODO add your handling code here:
        if(pricelist_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        Vector tableline =(Vector) pricelist_tableListData.get(pricelist_selectedRow);
        Boolean isact = (Boolean) tableline.get(2);
        if(!isact){
            JOptionPane.showMessageDialog(null, "Record is InActive! Please Active First.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //this.dispose();
        PriceListDetails priceListDetails = new PriceListDetails(pricelist_selectedRow,pricelist_tableListData);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        priceListDetails.setBounds(0,0,screenSize.width, screenSize.height);
        priceListDetails.setVisible(true);
        //priceListDetails.setLocation(200, 200);
                try {
                pricelist_tableListData=sqlManager.getPRICEWEEK();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_detailsBtnActionPerformed

    int compound_selectedRow=-1;
    private void compound_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compound_jTableMouseClicked
        // TODO add your handling code here:
        compound_selectedRow= ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            int row = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
            Vector tableline =(Vector) compound_tableListData.get(row);
            Boolean isact = (Boolean) tableline.get(3);
            if(!isact){
                JOptionPane.showMessageDialog(null, "Record is InActive! Please Active First.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //this.dispose();
            int priceweek=(int) tableline.get(16);
            CompoundFormulation compoundFormulation = new CompoundFormulation(row,compound_tableListData,null,null,null,null,priceweek);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            compoundFormulation.setBounds(0,0,screenSize.width, screenSize.height);
            compoundFormulation.setVisible(true);
            //compoundFormulation.setLocation(200, 200);
            try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_compound_jTableMouseClicked

    private void compound_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compound_jTableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_compound_jTableKeyPressed

    private void compoundRefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundRefreshBtnActionPerformed
 int index = jTabbedPane1.indexOfComponent(jPanel_Compound);

        c=new Compound();

        if (index > 0) {
            // The tab could not be found
            jTabbedPane1.add(jPanel_Compound, index);
            jTabbedPane1.setTitleAt(index, "Compound");
            jTabbedPane1.setSelectedIndex(index);

            c.loadCompoundData(compound_tableListData, compound_jTable);
            try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_compoundRefreshBtnActionPerformed

    private void deleteBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtn2ActionPerformed
        // TODO add your handling code here:
        if(compound_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        int confirm = JOptionPane.showOptionDialog(
            null, "Are You Sure to Delete This Compound ?",
            "Exit Confirmation", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm != 0) {
            return;
        }
        Vector tableline =(Vector) compound_tableListData.get(compound_selectedRow);
        int Compound_id = (Integer) tableline.get(11);
        try {
            boolean isSuccess=sqlManager.deleteCompound(Compound_id);
            if(isSuccess)
            {
                c.loadCompoundData(compound_tableListData,compound_jTable);
                JOptionPane.showMessageDialog(null, "Delete Successfully",
                    "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                try {
                    compound_tableListData=sqlManager.getCompoundList();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingredient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtn2ActionPerformed

    private void newbtn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn30ActionPerformed
        // TODO add your handling code here:
        CompoundCreate CompoundCreate = new CompoundCreate(-1,null);
        CompoundCreate.setLocation(50, 100);
        CompoundCreate.setVisible(true);
    }//GEN-LAST:event_newbtn30ActionPerformed

    private void editbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtn1ActionPerformed
        // TODO add your handling code here:
        if(compound_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Vector tableline =(Vector) compound_tableListData.get(compound_selectedRow);
        int compound_ID = (Integer) tableline.get(11);
        //this.dispose();
        CompoundCreate CompoundCreate = new CompoundCreate(compound_selectedRow,compound_tableListData);
        CompoundCreate.setLocation(50, 100);
        CompoundCreate.setVisible(true);
                    try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_editbtn1ActionPerformed

    private void detailsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsbtnActionPerformed
        // TODO add your handling code here:

        if(compound_selectedRow<0){
            JOptionPane.showMessageDialog(null, "No Row Selected! Please Active First.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Vector tableline =(Vector) compound_tableListData.get(compound_selectedRow);
        Boolean isact = (Boolean) tableline.get(3);
        if(!isact){
            JOptionPane.showMessageDialog(null, "Record is InActive! Please Active First.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //this.dispose();
        int priceweek=(int) tableline.get(16);
        CompoundFormulation compoundFormulation = new CompoundFormulation(compound_selectedRow,compound_tableListData,null,null,null,null,priceweek);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        compoundFormulation.setBounds(0,0,screenSize.width, screenSize.height);
        compoundFormulation.setVisible(true);
        //compoundFormulation.setLocation(200, 200);
                    try {
                compound_tableListData=sqlManager.getCompoundList();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_detailsbtnActionPerformed

    private void userinfo_UserIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userinfo_UserIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userinfo_UserIDTextFieldActionPerformed

    int userinfo_editUser_ID=-1;
    int userinfo_status_ID=-1;
    int userinfo_editnow_ID=0;
    private void userinfo_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userinfo_editBtnActionPerformed

        userinfo_UserIDTextField.setEditable(true);
        userinfo_PasswordTextField.setEditable(true);
        userinfo_isActiveCheckBox.setEnabled(true);
        //editBtn.setEnabled(false);
        userinfo_addnewbtn.setEnabled(true);
        userinfo_addListBtn.setEnabled(true);
        userinfo_editnow_ID=1;
    }//GEN-LAST:event_userinfo_editBtnActionPerformed

    private void userinfo_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userinfo_addnewbtnActionPerformed
        // TODO add your handling code here:
        userinfo_UserIDTextField.setText("");
        userinfo_PasswordTextField.setText("");
        userinfo_UserIDTextField.setEditable(true);
        userinfo_PasswordTextField.setEditable(true);
        userinfo_isActiveCheckBox.setSelected(true);
        userinfo_isActiveCheckBox.setEnabled(true);
        userinfo_editBtn.setEnabled(true);
        userinfo_addnewbtn.setEnabled(true);
        userinfo_addListBtn.setEnabled(true);
        userinfo_status_ID=-1;
        userinfo_editUser_ID=-1;
        userinfo_editnow_ID=0;
        userinfo_createdOnTextField.setText("");
        userinfo_updatedOnTextField.setText("");
        userinfo_CreatedByTextField.setText("");
        userinfo_UpdatedByTextField.setText("");
    }//GEN-LAST:event_userinfo_addnewbtnActionPerformed

    boolean userinfo_Saveremaining=false;
    int userinfo_insertID=10000000; 
    private void userinfo_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userinfo_addListBtnActionPerformed
        // TODO add your handling code here:

      //  u.addListBtnActionPerformed(userinfo_UserIDTextField,userinfo_PasswordTextField,userinfo_isActiveCheckBox
//            ,userinfo_tableListData,userinfo_editUser_ID,userinfo_insertID,userinfo_status_ID,userinfo_jTable,userinfo_editBtn
//            ,userinfo_addnewbtn,userinfo_addListBtn,userinfo_saveallbtn,userinfo_editnow_ID,userinfo_Saveremaining);

         String user=userinfo_UserIDTextField.getText();
        if(user.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide User ID", "User ID Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        String pass=userinfo_PasswordTextField.getText();
        if(pass.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Password", "Password Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }  
        
        boolean isActiveCheck=userinfo_isActiveCheckBox.isSelected();
        for(int i=0;i<userinfo_tableListData.size();i++)
        {
            Vector tableline =(Vector) userinfo_tableListData.get(i);
            int AD_USER_ID=(int) tableline.get(3);
            if(userinfo_editUser_ID==AD_USER_ID) continue;
            
            String name = (String) tableline.get(0);
            if(user.compareTo(name)==0){
//                JOptionPane.showMessageDialog(null, 
//                 "User ID Already Exists", "ID Error", JOptionPane.ERROR_MESSAGE);  
//                return;
            } 
            String password = (String) tableline.get(1);

        }
        if(userinfo_editUser_ID<1){
            Vector line = new Vector();
            line.add(user); //0
            line.add(pass);//1            
            line.add(isActiveCheck);//2
            line.add(userinfo_insertID);//3 ======== User_ID previous
            line.add(userinfo_status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            userinfo_tableListData.add(line);
            userinfo_insertID++;
        }
        else{
            for(int i=0;i<userinfo_tableListData.size();i++)
            {
                Vector line =(Vector) userinfo_tableListData.get(i);
                int User_ID=(int) line.get(3);
                if(userinfo_editUser_ID==User_ID){
                    userinfo_status_ID=2;
                    line.set(0, user);
                    line.set(1,pass);//1
                    line.set(2,isActiveCheck);//2
                    line.set(3,userinfo_editUser_ID);//3 ======== User_ID previous
                    line.set(4,userinfo_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        //editNutraint_ID=(int) tableline.get(12);
        //status_ID=(int) tableline.get(13);

        DefaultTableModel model1 = (DefaultTableModel)userinfo_jTable.getModel();
        model1.setRowCount(userinfo_tableListData.size());
        for(int i=0;i<userinfo_tableListData.size();i++)
        {
                Vector tableline =(Vector) userinfo_tableListData.get(i);               
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
        userinfo_UserIDTextField.setText("");
        userinfo_UserIDTextField.setEditable(true);
        userinfo_PasswordTextField.setText("");
        userinfo_PasswordTextField.setEnabled(true);
        userinfo_isActiveCheckBox.setSelected(true);       
        userinfo_editBtn.setEnabled(false);
        userinfo_addnewbtn.setEnabled(false);
        userinfo_addListBtn.setEnabled(true);
        userinfo_editUser_ID=-1;
        userinfo_status_ID=-1;
        userinfo_editnow_ID=0;
        userinfo_saveallbtn.setEnabled(true);
        userinfo_Saveremaining=true;

    }//GEN-LAST:event_userinfo_addListBtnActionPerformed

    private void userinfo_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userinfo_jTableMouseClicked
        // TODO add your handling code here:
        userinfo_editUser_ID=-1;
        userinfo_editnow_ID=0;
        userinfo_status_ID=-1;
        userinfo_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        userinfo_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_userinfo_jTableMouseClicked

    private void userinfo_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userinfo_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed Sys"+userinfo_jTable.getSelectedRow());
            if(userinfo_jTable.getSelectedRow()!=0)
            userinfo_TableMouseorKeyChange(userinfo_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+userinfo_jTable.getSelectedRow());
            if(userinfo_jTable.getSelectedRow()!=userinfo_tableListData.size()-1)
            userinfo_TableMouseorKeyChange(userinfo_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+userinfo_jTable.getSelectedRow());
            if(userinfo_jTable.getSelectedRow()!=userinfo_tableListData.size()-1)
            userinfo_TableMouseorKeyChange(userinfo_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_userinfo_jTableKeyPressed

    
            
    public void userinfo_TableMouseorKeyChange(int row) {
        
        userinfo_createdOnTextField.setText("");
        userinfo_updatedOnTextField.setText("");
        userinfo_CreatedByTextField.setText("");
        userinfo_UpdatedByTextField.setText("");
        
        //CreatedByTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        //UpdatedByTextField.setHorizontalAlignment(SwingConstants.LEFT);
        
        
        
        Vector tableline =(Vector) userinfo_tableListData.get(row);
               
        String user = (String) tableline.get(0);
        String pass = (String) tableline.get(1);
        Boolean isact = (Boolean) tableline.get(2);
        String createdOn = (String) tableline.get(5);
     
        String createdby = (String) tableline.get(7);
        userinfo_createdOnTextField.setText(""+createdOn);
        userinfo_updatedOnTextField.setText((String) tableline.get(6));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(8);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        userinfo_CreatedByTextField.setText(""+createdby);
        userinfo_UpdatedByTextField.setText(""+updatedby);
        userinfo_UserIDTextField.setEditable(false);
        userinfo_PasswordTextField.setEditable(false);
        userinfo_isActiveCheckBox.setEnabled(false);

        userinfo_UserIDTextField.setText(""+user);
        userinfo_PasswordTextField.setText(""+pass);
        userinfo_isActiveCheckBox.setSelected(isact);
        
        userinfo_editUser_ID=(int) tableline.get(3);
        userinfo_status_ID=(int) tableline.get(4);
        userinfo_editBtn.setEnabled(true);
        userinfo_addnewbtn.setEnabled(true);
        userinfo_addListBtn.setEnabled(true);

        
    }
    
    
    
    
    private void userinfo_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userinfo_saveallbtnActionPerformed

       // u.saveallbuttonActionPerformed(userinfo_tableListData,userinfo_saveallbtn,userinfo_Saveremaining,userinfo_jTable);
        
                try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveUserdata(userinfo_tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                u.loadUserData(userinfo_tableListData,userinfo_jTable);
                userinfo_saveallbtn.setEnabled(false);
                userinfo_Saveremaining=false;
                userinfo_tableListData=sqlManager.getUser1data();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_userinfo_saveallbtnActionPerformed

    private void ingredientgroup_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_editBtnActionPerformed
        //
        ingredientgroup_IngredientNameTextField.setEditable(true);
        ingredientgroup_isActiveCheckBox.setEnabled(true);
        ingredientgroup_editBtn.setEnabled(false);
        ingredientgroup_addnewbtn.setEnabled(true);
        ingredientgroup_addListBtn.setEnabled(true);
        ingredientgroup_Deletebtn.setEnabled(false);
        ingredientgroup_editnow_ID=1;
    }//GEN-LAST:event_ingredientgroup_editBtnActionPerformed

    private void ingredientgroup_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_addnewbtnActionPerformed
        // TODO add your handling code here:
        //ingredientgroup_codeTextField.setText("");
        ingredientgroup_IngredientNameTextField.setText("");
        ingredientgroup_IngredientNameTextField.setEditable(true);
        ingredientgroup_isActiveCheckBox.setSelected(true);
        ingredientgroup_isActiveCheckBox.setEnabled(true);
        ingredientgroup_editBtn.setEnabled(true);
        ingredientgroup_addnewbtn.setEnabled(true);
        ingredientgroup_addListBtn.setEnabled(true);
        ingredientgroup_Deletebtn.setEnabled(false);
        ingredientgroup_status_ID=-1;
        ingredientgroup_editIngredient_ID=-1;
        ingredientgroup_editnow_ID=0;
        ingredientgroup_createdOnTextField.setText("");
        ingredientgroup_updatedOnTextField.setText("");
        ingredientgroup_CreatedByTextField.setText("");
        ingredientgroup_UpdatedByTextField.setText("");
    }//GEN-LAST:event_ingredientgroup_addnewbtnActionPerformed

    private void ingredientgroup_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_addListBtnActionPerformed

//        ig.OnaddtolistButtonActionPerformed(ingredientgroup_Saveremaining, ingredientgroup_saveallbtn,
//            ingredientgroup_tableListData, ingredientgroup_IngredientNameTextField,
//            ingredientgroup_isActiveCheckBox, ingredientgroup_editBtn, ingredientgroup_addnewbtn,
//            ingredientgroup_addListBtn,ingredientgroup_jTable, ingredientgroup_Deletebtn, ingredientgroup_editIngredient_ID,
//            ingredientgroup_status_ID, ingredientgroup_insertID, ingredientgroup_editnow_ID);

        
          String ingredient=ingredientgroup_IngredientNameTextField.getText();
        if(ingredient.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Ingredient Name", "Ingredient Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }      
        boolean isActiveCheck=ingredientgroup_isActiveCheckBox.isSelected();
        for(int i=0;i<ingredientgroup_tableListData.size();i++)
        {
            Vector tableline =(Vector) ingredientgroup_tableListData.get(i);
            int ingredient_ID=(int) tableline.get(2);
            if(ingredientgroup_editIngredient_ID==ingredient_ID) continue;
            
            String name = (String) tableline.get(0);
            if(ingredient.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Ingredient Name Already Exists", "Ingredient Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            } 

        }
        if(ingredientgroup_editIngredient_ID<1){
            Vector line = new Vector();
            line.add(ingredient); //0         
            line.add(isActiveCheck);//1
            line.add(ingredientgroup_insertID);//2 ======== NutrientProperty_ID previous
            line.add(ingredientgroup_status_ID);//3========1 newadd 2update 0 for exits 
            line.add(""); //4
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            ingredientgroup_tableListData.add(line);
            ingredientgroup_insertID++;
        }
        else{
            for(int i=0;i<ingredientgroup_tableListData.size();i++)
            {
                Vector line =(Vector) ingredientgroup_tableListData.get(i);
                int Ingredientgrp_ID=(int) line.get(2);
                if(ingredientgroup_editIngredient_ID==Ingredientgrp_ID){
                    ingredientgroup_status_ID=2;
                    line.set(0, ingredient);
                    line.set(1,isActiveCheck);//2
                    line.set(2,ingredientgroup_editIngredient_ID);//3 ======== NutrientProperty_ID previous
                    line.set(3,ingredientgroup_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        DefaultTableModel model1 = (DefaultTableModel)ingredientgroup_jTable.getModel();
        model1.setRowCount(ingredientgroup_tableListData.size());
        for(int i=0;i<ingredientgroup_tableListData.size();i++)
        {
                Vector tableline =(Vector) ingredientgroup_tableListData.get(i);               
                String name = (String) tableline.get(0);
                model1.setValueAt((Object)name, i, 0);
                Boolean isact = (Boolean) tableline.get(1);
                model1.setValueAt((Object)isact, i, 1);
                
        }
        ingredientgroup_IngredientNameTextField.setText("");
        ingredientgroup_IngredientNameTextField.setEditable(true);
        ingredientgroup_isActiveCheckBox.setSelected(true);       
        ingredientgroup_editBtn.setEnabled(true);
        ingredientgroup_addnewbtn.setEnabled(true);
        ingredientgroup_addListBtn.setEnabled(true);
        ingredientgroup_Deletebtn.setEnabled(false);
        ingredientgroup_editIngredient_ID=-1;
        ingredientgroup_status_ID=-1;
        ingredientgroup_editnow_ID=0;
        ingredientgroup_saveallbtn.setEnabled(true);
        ingredientgroup_Saveremaining=true;

    }//GEN-LAST:event_ingredientgroup_addListBtnActionPerformed

    private void ingredientgroup_IngredientNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_IngredientNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredientgroup_IngredientNameTextFieldActionPerformed

    private void ingredientgroup_DeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_DeletebtnActionPerformed
        // TODO add your handling code here:

       // ig.OnDeleteButtonActionPerformed(ingredientgroup_jTable, ingredientgroup_tableListData);
       
          int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
               if(dialogDelete==JOptionPane.YES_OPTION){     
                
                DefaultTableModel model1 = (DefaultTableModel)ingredientgroup_jTable.getModel();
                int row = ingredientgroup_jTable.getSelectedRow();
                Vector tableline =(Vector) ingredientgroup_tableListData.get(row);
                int editingredient_ID=(int) tableline.get(2);//3
                     
             boolean isSuccess;
      try {
          isSuccess = sqlManager.deleteGradientGroupdata(editingredient_ID);
          if(isSuccess){ 

                JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                ig.loadIngrdientGrpData(ingredientgroup_tableListData,ingredientgroup_jTable);
                ingredientgroup_tableListData=sqlManager.getIngredientGrpdata();
             //Saveremaining = false;
                         }
             } catch (SQLException ex) {
          Logger.getLogger(IngredientGroup.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
      catch (UnknownHostException ex) {
          Logger.getLogger(IngredientGroup.class.getName()).log(Level.SEVERE, null, ex);
                       }
        }                                      
          
        
    }//GEN-LAST:event_ingredientgroup_DeletebtnActionPerformed

        int ingredientgroup_editIngredient_ID=-1;
    int ingredientgroup_status_ID=-1;
    int ingredientgroup_editnow_ID=0;
    private void ingredientgroup_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredientgroup_jTableMouseClicked
        // TODO add your handling code here:
        ingredientgroup_editIngredient_ID=-1;
        ingredientgroup_editnow_ID=0;
        ingredientgroup_status_ID=-1;
        ingredientgroup_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        ingredientgroup_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_ingredientgroup_jTableMouseClicked

    private void ingredientgroup_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingredientgroup_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+ingredientgroup_jTable.getSelectedRow());
            if(ingredientgroup_jTable.getSelectedRow()!=0)

            ingredientgroup_TableMouseorKeyChange(ingredientgroup_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+ingredientgroup_jTable.getSelectedRow());
            if(ingredientgroup_jTable.getSelectedRow()!=ingredientgroup_tableListData.size()-1)
            ingredientgroup_TableMouseorKeyChange(ingredientgroup_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+ingredientgroup_jTable.getSelectedRow());
            if(ingredientgroup_jTable.getSelectedRow()!=ingredientgroup_tableListData.size()-1)
            ingredientgroup_TableMouseorKeyChange(ingredientgroup_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_ingredientgroup_jTableKeyPressed

    
        private void ingredientgroup_TableMouseorKeyChange(int row){
        
        ingredientgroup_createdOnTextField.setText("");
        ingredientgroup_updatedOnTextField.setText("");
        ingredientgroup_CreatedByTextField.setText("");
        ingredientgroup_UpdatedByTextField.setText("");
               
        Vector tableline =(Vector) ingredientgroup_tableListData.get(row);               
        String ingredient_name = (String) tableline.get(0);
        Boolean isact = (Boolean) tableline.get(1);
        String createdOn = (String) tableline.get(4);
     
        String createdby = (String) tableline.get(6);
        ingredientgroup_createdOnTextField.setText(""+createdOn);
        ingredientgroup_updatedOnTextField.setText((String) tableline.get(5));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(7);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        ingredientgroup_CreatedByTextField.setText(""+createdby);
        ingredientgroup_UpdatedByTextField.setText(""+updatedby);
        ingredientgroup_IngredientNameTextField.setEditable(false);
        ingredientgroup_isActiveCheckBox.setEnabled(false);

        ingredientgroup_IngredientNameTextField.setText(""+ingredient_name);
        ingredientgroup_isActiveCheckBox.setSelected(isact);
        
        ingredientgroup_editIngredient_ID=(int) tableline.get(2);
        ingredientgroup_status_ID=(int) tableline.get(3);
        ingredientgroup_editBtn.setEnabled(true);
        ingredientgroup_addnewbtn.setEnabled(true);
        ingredientgroup_addListBtn.setEnabled(true);
        ingredientgroup_Deletebtn.setEnabled(true);
        
    }
    private void ingredientgroup_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_saveallbtnActionPerformed

//        ig.OnSaveAllButtonActionPerformed(ingredientgroup_tableListData, ingredientgroup_jTable,
//            ingredientgroup_saveallbtn, ingredientgroup_Saveremaining);
        
        
                boolean isSuccess;
        try {
            isSuccess = sqlManager.saveIngredientGroupdata(ingredientgroup_tableListData);
            if(isSuccess)
        {
            JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
            ig.loadIngrdientGrpData(ingredientgroup_tableListData,ingredientgroup_jTable);
            ingredientgroup_saveallbtn.setEnabled(false);
            ingredientgroup_Saveremaining=false;
            ingredientgroup_tableListData=sqlManager.getIngredientGrpdata();
        }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientGroup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(IngredientGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ingredientgroup_saveallbtnActionPerformed

      int ingredienttype_editType_ID=-1;
    int ingredienttype_status_ID=-1;
    int ingredienttype_editnow_ID=0;
    private void ingredienttype_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_editBtnActionPerformed

        ingredienttype_IngredientTypeTextField.setEditable(true);
        ingredienttype_isActiveCheckBox.setEnabled(true);
        ingredienttype_editBtn.setEnabled(false);
        ingredienttype_addnewbtn.setEnabled(true);
        ingredienttype_addListBtn.setEnabled(true);
        ingredienttype_Deletebtn.setEnabled(false);
        ingredienttype_editnow_ID=1;
    }//GEN-LAST:event_ingredienttype_editBtnActionPerformed

    private void ingredienttype_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_addnewbtnActionPerformed
        // TODO add your handling code here:
        //        //  codeTextField.setText("");
        ingredienttype_IngredientTypeTextField.setText("");
        ingredienttype_IngredientTypeTextField.setEditable(true);
        ingredienttype_isActiveCheckBox.setSelected(true);
        ingredienttype_isActiveCheckBox.setEnabled(true);
        ingredienttype_editBtn.setEnabled(true);
        ingredienttype_addnewbtn.setEnabled(true);
        ingredienttype_addListBtn.setEnabled(true);
        ingredienttype_Deletebtn.setEnabled(false);
        ingredienttype_status_ID=-1;
        ingredienttype_editType_ID=-1;
        ingredienttype_editnow_ID=0;
        ingredienttype_createdOnTextField.setText("");
        ingredienttype_updatedOnTextField.setText("");
        ingredienttype_CreatedByTextField.setText("");
        ingredienttype_UpdatedByTextField.setText("");
        ingredienttype_saveallbtn.setEnabled(true);
    }//GEN-LAST:event_ingredienttype_addnewbtnActionPerformed

    private void ingredienttype_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_addListBtnActionPerformed

//        it.addListBtnActionPerformed(ingredienttype_IngredientTypeTextField,ingredienttype_isActiveCheckBox,
//            ingredienttype_tableListData,ingredienttype_editType_ID,ingredienttype_insertID,ingredienttype_status_ID
//            ,ingredienttype_editnow_ID,ingredienttype_Saveremaining,ingredienttype_jTable,ingredienttype_editBtn
//            ,ingredienttype_addnewbtn,ingredienttype_addListBtn,ingredienttype_Deletebtn,ingredienttype_saveallbtn);


        String IngredientTypename=ingredienttype_IngredientTypeTextField.getText();
        if(IngredientTypename.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Ingredient Type", "Ingredient Type Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }      
        boolean isActiveCheck=ingredienttype_isActiveCheckBox.isSelected();
        for(int i=0;i<ingredienttype_tableListData.size();i++)
        {
            Vector tableline =(Vector) ingredienttype_tableListData.get(i);
            int IngredientType_ID=(int) tableline.get(2);
            if(ingredienttype_editType_ID==IngredientType_ID) continue;
            
            String name = (String) tableline.get(0);
            if(IngredientTypename.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Ingredient Type Already Exists", "Ingredient Type Error", JOptionPane.ERROR_MESSAGE);  
                return;
            } 

        }
        if(ingredienttype_editType_ID<1){
            Vector line = new Vector();
            line.add(IngredientTypename); //0         
            line.add(isActiveCheck);//1
            line.add(ingredienttype_insertID);//2 ======== IngredientType_ID previous
            line.add(ingredienttype_status_ID);//3========1 newadd 2update 0 for exits 
            line.add(""); //4
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            ingredienttype_tableListData.add(line);
            ingredienttype_insertID++;
        }
        else{
            for(int i=0;i<ingredienttype_tableListData.size();i++)
            {
                Vector line =(Vector) ingredienttype_tableListData.get(i);
                int IngType_ID=(int) line.get(2);
                if(ingredienttype_editType_ID==IngType_ID){
                    ingredienttype_status_ID=2;
                    line.set(0, IngredientTypename);
                    line.set(1,isActiveCheck);//2
                    line.set(2,ingredienttype_editType_ID);//3 ======== IngredientType_ID previous
                    line.set(3,ingredienttype_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        DefaultTableModel model1 = (DefaultTableModel)ingredienttype_jTable.getModel();
        model1.setRowCount(ingredienttype_tableListData.size());
        for(int i=0;i<ingredienttype_tableListData.size();i++)
        {
                Vector tableline =(Vector) ingredienttype_tableListData.get(i);               
                String name = (String) tableline.get(0);
                model1.setValueAt((Object)name, i, 0);
                Boolean isact = (Boolean) tableline.get(1);
                model1.setValueAt((Object)isact, i, 1);
                
        }

        ingredienttype_IngredientTypeTextField.setText("");
        ingredienttype_IngredientTypeTextField.setEditable(true);
        ingredienttype_isActiveCheckBox.setSelected(true);       
        ingredienttype_editBtn.setEnabled(true);
        ingredienttype_addnewbtn.setEnabled(true);
        ingredienttype_addListBtn.setEnabled(true);
        ingredienttype_Deletebtn.setEnabled(false);
        ingredienttype_editType_ID=-1;
        ingredienttype_status_ID=-1;
        ingredienttype_editnow_ID=0;
        ingredienttype_saveallbtn.setEnabled(true);
        ingredienttype_Saveremaining=true;
        
        
        

    
    }//GEN-LAST:event_ingredienttype_addListBtnActionPerformed

    private void ingredienttype_IngredientTypeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_IngredientTypeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredienttype_IngredientTypeTextFieldActionPerformed

    private void ingredienttype_DeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_DeletebtnActionPerformed
        // TODO add your handling code here:

        it.OnDeleteButtonActionPerformed(ingredienttype_jTable, ingredienttype_tableListData);
        try {
            ingredienttype_tableListData=sqlManager.getIngredientTypedata();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ingredienttype_DeletebtnActionPerformed

    private void ingredienttype_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredienttype_jTableMouseClicked
        // TODO add your handling code here:
        ingredienttype_editType_ID=-1;
        ingredienttype_editnow_ID=0;
        ingredienttype_status_ID=-1;
        ingredienttype_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        ingredienttype_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_ingredienttype_jTableMouseClicked

    private void ingredienttype_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingredienttype_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+ingredienttype_jTable.getSelectedRow());
            if(ingredienttype_jTable.getSelectedRow()!=0)
            ingredienttype_TableMouseorKeyChange(ingredienttype_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+ingredienttype_jTable.getSelectedRow());
            if(ingredienttype_jTable.getSelectedRow()!=ingredienttype_tableListData.size()-1)
            ingredienttype_TableMouseorKeyChange(ingredienttype_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+ingredienttype_jTable.getSelectedRow());
            if(ingredienttype_jTable.getSelectedRow()!=ingredienttype_tableListData.size()-1)
            ingredienttype_TableMouseorKeyChange(ingredienttype_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_ingredienttype_jTableKeyPressed

    
    public void ingredienttype_TableMouseorKeyChange(int row){
        
        ingredienttype_createdOnTextField.setText("");
        ingredienttype_updatedOnTextField.setText("");
        ingredienttype_CreatedByTextField.setText("");
        ingredienttype_UpdatedByTextField.setText("");
        
      
       Vector tableline =(Vector) ingredienttype_tableListData.get(row);
               
        String ingrdientname = (String) tableline.get(0);
        Boolean isact = (Boolean) tableline.get(1);
        String createdOn = (String) tableline.get(4);
     
        String createdby = (String) tableline.get(6);
        ingredienttype_createdOnTextField.setText(""+createdOn);
        ingredienttype_updatedOnTextField.setText((String) tableline.get(5));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(7);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        ingredienttype_CreatedByTextField.setText(""+createdby);
        ingredienttype_UpdatedByTextField.setText(""+updatedby);
        ingredienttype_IngredientTypeTextField.setEditable(false);
        ingredienttype_isActiveCheckBox.setEnabled(false);

        ingredienttype_IngredientTypeTextField.setText(""+ingrdientname);
        ingredienttype_isActiveCheckBox.setSelected(isact);
        
        ingredienttype_editType_ID=(int) tableline.get(2);
        ingredienttype_status_ID=(int) tableline.get(3);
        ingredienttype_editBtn.setEnabled(true);
        ingredienttype_addnewbtn.setEnabled(true);
        ingredienttype_addListBtn.setEnabled(true);
        ingredienttype_Deletebtn.setEnabled(true);
        
    }
    
    
    
    
    
    
    
     boolean ingredienttype_Saveremaining=true;
    int ingredienttype_insertID=10000000; 
    private void ingredienttype_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_saveallbtnActionPerformed

            
        // it.OnSaveAllButtonActionPerformed(ingredienttype_tableListData, ingredienttype_jTable, ingredienttype_saveallbtn, ingredienttype_Saveremaining);
        
                boolean isSuccess;
        try {
            isSuccess = sqlManager.saveIngredientTypedata(ingredienttype_tableListData);
            if(isSuccess)
        {
            JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
            it.loadIngredientTypeData(ingredienttype_tableListData,ingredienttype_jTable);
            ingredienttype_saveallbtn.setEnabled(false);
            ingredienttype_Saveremaining=false;
            
            ingredienttype_tableListData=sqlManager.getIngredientTypedata();

        }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientType.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(IngredientType.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_ingredienttype_saveallbtnActionPerformed

    private void ingredienttype_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredienttype_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredienttype_updatedOnTextFieldActionPerformed

    private void unit_codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_codeTextFieldActionPerformed

    private void unit_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_editBtnActionPerformed

        unit_codeTextField.setEditable(true);
        unit_nameTextField.setEditable(true);
        unit_isActiveCheckBox.setEnabled(true);
        unit_editBtn.setEnabled(false);
        unit_deletebtn.setEnabled(false);
        unit_addnewbtn.setEnabled(false);

        unit_addListBtn.setEnabled(true);
        unit_editnow_ID=1;
    }//GEN-LAST:event_unit_editBtnActionPerformed

    private void unit_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_addnewbtnActionPerformed
        // TODO add your handling code here:
        unit_codeTextField.setText("");
        unit_nameTextField.setText("");
        unit_codeTextField.setEditable(true);
        unit_nameTextField.setEditable(true);
        unit_isActiveCheckBox.setSelected(true);
        unit_isActiveCheckBox.setEnabled(true);
        unit_deletebtn.setEnabled(false);
        unit_editBtn.setEnabled(false);
        unit_addListBtn.setEnabled(true);
        unit_status_ID=-1;
        unit_editUnit_ID=-1;
        unit_editnow_ID=0;
        unit_createdOnTextField.setText("");
        unit_updatedOnTextField.setText("");
        unit_CreatedByTextField.setText("");
        unit_UpdatedByTextField.setText("");
    }//GEN-LAST:event_unit_addnewbtnActionPerformed

    private void unit_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_addListBtnActionPerformed

//        unit.OnAddToListButtonActionPerformed(unit_tableListData,unit_codeTextField,unit_nameTextField
//            ,unit_isActiveCheckBox, unit_editUnit_ID, unit_insertID, unit_status_ID, unit_addListBtn, unit_deletebtn
//            , unit_editnow_ID, unit_saveallbtn, unit_Saveremaining, unit_jTable);
        
        
        String symbol=unit_codeTextField.getText();
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
        String name=unit_nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        boolean isActiveCheck=unit_isActiveCheckBox.isSelected();

        for(int i=0;i<unit_tableListData.size();i++)
        {
            Vector tableline =(Vector) unit_tableListData.get(i);
            int Unit_ID=(int) tableline.get(3);
            if(unit_editUnit_ID==Unit_ID) continue;
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
        if(unit_editUnit_ID<1){
            Vector line = new Vector();
            line.add(symbol); //0
            line.add(name);//1
            line.add(isActiveCheck);//2
            line.add(unit_insertID);//3 ======== NUTRIENT_ID previous
            line.add(unit_status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            unit_tableListData.add(line);
            unit_insertID++ ;
        }
        else{
            for(int i=0;i<unit_tableListData.size();i++)
            {
                Vector line =(Vector) unit_tableListData.get(i);
                int Unit_ID=(int) line.get(3);
              
                if(unit_editUnit_ID==Unit_ID){
                    unit_status_ID=2;
                    line.set(0, symbol);
                    line.set(1,name);//1
                   
                    line.set(2,isActiveCheck);//2
                    
                    line.set(3,unit_editUnit_ID);//3 ======== NUTRIENT_ID previous
                    line.set(4,unit_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        final    DefaultTableModel model1 = (DefaultTableModel)unit_jTable.getModel();
        model1.setRowCount(unit_tableListData.size());
        for(int i=0;i<unit_tableListData.size();i++)
        {
            Vector tableline =(Vector) unit_tableListData.get(i);

            String cod = (String) tableline.get(0);
            model1.setValueAt((Object)cod, i, 0);
            String nam = (String) tableline.get(1);
            model1.setValueAt((Object)nam, i, 1);

            Boolean isact = (Boolean) tableline.get(2);
            model1.setValueAt((Object)isact, i, 2);
        }
        unit_codeTextField.setText("");
        unit_codeTextField.setEditable(true);
        unit_nameTextField.setText("");
        unit_nameTextField.setEnabled(true);
        
        unit_isActiveCheckBox.setSelected(true);
        unit_addListBtn.setEnabled(false);
        unit_deletebtn.setEnabled(true);
        unit_editUnit_ID=-1;
        unit_status_ID=-1;
        unit_editnow_ID=0;
        unit_saveallbtn.setEnabled(true);
        unit_Saveremaining = true;
    }//GEN-LAST:event_unit_addListBtnActionPerformed

    private void unit_nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_nameTextFieldActionPerformed

    private void unit_deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_deletebtnActionPerformed

        //unit.OnDeleteButtonActionPerformed(unit_jTable, unit_tableListData, unit_deletebtn, unit_editBtn, unit_addnewbtn);
        
        
         int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
        if(dialogDelete==JOptionPane.YES_OPTION){     
            DefaultTableModel model1 = (DefaultTableModel)unit_jTable.getModel();
            int row = unit_jTable.getSelectedRow();
            Vector tableline =(Vector) unit_tableListData.get(row);
            int Unit_ID=(int) tableline.get(3);    
            boolean isSuccess;
            try {
                isSuccess = sqlManager.deleteUnitdata(Unit_ID);
                if(isSuccess){ 
                    JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                    unit.loadUnitData(unit_tableListData,unit_jTable);
                    unit_tableListData=sqlManager.getUnitdata();
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
        unit_deletebtn.setEnabled(false);
        unit_editBtn.setEnabled(false);
        unit_addnewbtn.setEnabled(true);  
    }//GEN-LAST:event_unit_deletebtnActionPerformed

    private void unit_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unit_jTableMouseClicked
        // TODO add your handling code here:
        unit_editUnit_ID=-1;
        unit_editnow_ID=0;
        unit_status_ID=-1;
        unit_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        unit_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_unit_jTableMouseClicked

    private void unit_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unit_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+unit_jTable.getSelectedRow());
            if(unit_jTable.getSelectedRow()!=0)
            unit_TableMouseorKeyChange(unit_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+unit_jTable.getSelectedRow());
            if(unit_jTable.getSelectedRow()!=unit_tableListData.size()-1)
            unit_TableMouseorKeyChange(unit_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+unit_jTable.getSelectedRow());
            if(unit_jTable.getSelectedRow()!=unit_tableListData.size()-1)
            unit_TableMouseorKeyChange(unit_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_unit_jTableKeyPressed

     private void unit_TableMouseorKeyChange(int row){
        
        unit_createdOnTextField.setText("");
        unit_updatedOnTextField.setText("");
        unit_CreatedByTextField.setText("");
        unit_UpdatedByTextField.setText("");
        Vector tableline =(Vector) unit_tableListData.get(row);  
        String cod = (String) tableline.get(0);
        String nam = (String) tableline.get(1);
        Boolean isact = (Boolean) tableline.get(2);
        String createdOn = (String) tableline.get(7);
     
        String createdby = (String) tableline.get(5);
        unit_createdOnTextField.setText(""+createdOn);
        unit_updatedOnTextField.setText((String) tableline.get(8));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(6);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        unit_CreatedByTextField.setText(""+createdby);
        unit_UpdatedByTextField.setText(updatedby);
        unit_codeTextField.setEditable(false);
        unit_nameTextField.setEditable(false);
        unit_isActiveCheckBox.setEnabled(false);
        unit_codeTextField.setText(""+cod);
        unit_nameTextField.setText(""+nam);
        unit_isActiveCheckBox.setSelected(isact);
        unit_editUnit_ID=(int) tableline.get(3);
        unit_status_ID=(int) tableline.get(4);
        unit_editBtn.setEnabled(true);
        unit_addnewbtn.setEnabled(true);
        unit_addListBtn.setEnabled(false);
        unit_deletebtn.setEnabled(true);
        
    }
    
    
    
    
    boolean unit_Saveremaining=true;
    int unit_insertID=10000000; 
    int unit_editUnit_ID=-1;
    int unit_status_ID=-1;
    int unit_editnow_ID=0;
    private void unit_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_saveallbtnActionPerformed

        //unit.OnSaveAllButtonActionPerformed(unit_jTable, unit_tableListData, unit_Saveremaining);
        
            try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveUnitdata(unit_tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                unit.loadUnitData(unit_tableListData,unit_jTable);
               // saveallbtn.setEnabled(false);
                unit_Saveremaining = false;
                unit_tableListData=sqlManager.getUnitdata();
            }
        } catch (SQLException ex) {
        } catch (UnknownHostException ex) {
        }
    }//GEN-LAST:event_unit_saveallbtnActionPerformed

     int nutrientproperty_editProperty_ID=-1;
    int nutrientproperty_status_ID=-1;
    int nutrientproperty_editnow_ID=0;
    private void nutrientproperty_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_editBtnActionPerformed

        nutrientproperty_NuPropertyNameTextField.setEditable(true);
        nutrientproperty_isActiveCheckBox.setEnabled(true);
        nutrientproperty_editBtn.setEnabled(false);
        nutrientproperty_addnewbtn.setEnabled(true);
        nutrientproperty_addListBtn.setEnabled(true);
        nutrientproperty_Deletebtn.setEnabled(false);
        nutrientproperty_editnow_ID=1;
    }//GEN-LAST:event_nutrientproperty_editBtnActionPerformed

    private void nutrientproperty_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_addnewbtnActionPerformed
        // TODO add your handling code here:
        //  codeTextField.setText("");
        nutrientproperty_NuPropertyNameTextField.setText("");
        nutrientproperty_NuPropertyNameTextField.setEditable(true);
        nutrientproperty_isActiveCheckBox.setSelected(true);
        nutrientproperty_isActiveCheckBox.setEnabled(true);
        nutrientproperty_editBtn.setEnabled(true);
        nutrientproperty_addnewbtn.setEnabled(true);
        nutrientproperty_addListBtn.setEnabled(true);
        nutrientproperty_Deletebtn.setEnabled(false);
        nutrientproperty_status_ID=-1;
        nutrientproperty_editProperty_ID=-1;
        nutrientproperty_editnow_ID=0;
        nutrientproperty_createdOnTextField.setText("");
        nutrientproperty_updatedOnTextField.setText("");
        nutrientproperty_CreatedByTextField.setText("");
        nutrientproperty_UpdatedByTextField.setText("");
    }//GEN-LAST:event_nutrientproperty_addnewbtnActionPerformed

    private void nutrientproperty_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_addListBtnActionPerformed

//        np.OnAddButtonActionPerformed(nutrientproperty_NuPropertyNameTextField, nutrientproperty_isActiveCheckBox
//            , nutrientproperty_tableListData, nutrientproperty_editProperty_ID, nutrientproperty_insertID
//            ,nutrientproperty_status_ID, nutrientproperty_jTable, nutrientproperty_Saveremaining, nutrientproperty_saveallbtn
//            , nutrientproperty_editBtn,  nutrientproperty_addnewbtn,  nutrientproperty_addListBtn
//            ,nutrientproperty_Deletebtn, nutrientproperty_editnow_ID);


        
    
        String nu_propertyname=nutrientproperty_NuPropertyNameTextField.getText();
        if(nu_propertyname.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Nutrient Name", "Nutrient Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }      
        boolean isActiveCheck=nutrientproperty_isActiveCheckBox.isSelected();
        for(int i=0;i<nutrientproperty_tableListData.size();i++)
        {
            Vector tableline =(Vector) nutrientproperty_tableListData.get(i);
            int NutrientProperty_ID=(int) tableline.get(2);
            if(nutrientproperty_editProperty_ID==NutrientProperty_ID) continue;
            
            String name = (String) tableline.get(0);
            if(nu_propertyname.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Nutrient Name Already Exists", "Nutrient Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            } 

        }
        if(nutrientproperty_editProperty_ID<1){
            Vector line = new Vector();
            line.add(nu_propertyname); //0         
            line.add(isActiveCheck);//1
            line.add(nutrientproperty_insertID);//2 ======== NutrientProperty_ID previous
            line.add(nutrientproperty_status_ID);//3========1 newadd 2update 0 for exits 
            line.add(""); //4
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            nutrientproperty_tableListData.add(line);
            nutrientproperty_insertID++;
        }
        else{
            for(int i=0;i<nutrientproperty_tableListData.size();i++)
            {
                Vector line =(Vector) nutrientproperty_tableListData.get(i);
                int NuProperty_ID=(int) line.get(2);
                if(nutrientproperty_editProperty_ID==NuProperty_ID){
                    nutrientproperty_status_ID=2;
                    line.set(0, nu_propertyname);
                    line.set(1,isActiveCheck);//2
                    line.set(2,nutrientproperty_editProperty_ID);//3 ======== NutrientProperty_ID previous
                    line.set(3,nutrientproperty_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        DefaultTableModel model1 = (DefaultTableModel)nutrientproperty_jTable.getModel();
        model1.setRowCount(nutrientproperty_tableListData.size());
        for(int i=0;i<nutrientproperty_tableListData.size();i++)
        {
                Vector tableline =(Vector) nutrientproperty_tableListData.get(i);               
                String name = (String) tableline.get(0);
                model1.setValueAt((Object)name, i, 0);
                Boolean isact = (Boolean) tableline.get(1);
                model1.setValueAt((Object)isact, i, 1);
                
        }

        nutrientproperty_NuPropertyNameTextField.setText("");
        nutrientproperty_NuPropertyNameTextField.setEditable(true);
        nutrientproperty_isActiveCheckBox.setSelected(true);       
        nutrientproperty_editBtn.setEnabled(true);
        nutrientproperty_addnewbtn.setEnabled(true);
        nutrientproperty_addListBtn.setEnabled(true);
        nutrientproperty_Deletebtn.setEnabled(false);
        nutrientproperty_editProperty_ID=-1;
        nutrientproperty_status_ID=-1;
        nutrientproperty_editnow_ID=0;
        nutrientproperty_saveallbtn.setEnabled(true);
        nutrientproperty_Saveremaining=true;
        
        




    }//GEN-LAST:event_nutrientproperty_addListBtnActionPerformed

    private void nutrientproperty_NuPropertyNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_NuPropertyNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrientproperty_NuPropertyNameTextFieldActionPerformed

    private void nutrientproperty_DeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_DeletebtnActionPerformed
        // TODO add your handling code here:

        //np.OnDeleteButtonActionPerformed(nutrientproperty_tableListData, nutrientproperty_jTable);
        
        int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
        if(dialogDelete==JOptionPane.YES_OPTION){     
                
                DefaultTableModel model1 = (DefaultTableModel)nutrientproperty_jTable.getModel();
                int row = nutrientproperty_jTable.getSelectedRow();
                Vector tableline =(Vector) nutrientproperty_tableListData.get(row);
                int editproperty_ID=(int) tableline.get(2);//3

                boolean isSuccess;
                try {
                isSuccess = sqlManager.deleteNuPropertyGroupdata(editproperty_ID);
                if(isSuccess){ 

                       JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Successfully", JOptionPane.INFORMATION_MESSAGE);
                       np.loadNuPropertyData(nutrientproperty_tableListData,nutrientproperty_jTable);
                       nutrientproperty_tableListData=sqlManager.getNuPropertydata();
                    //Saveremaining = false;
                                }
                    } catch (SQLException ex) {
                    Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
                                               } 
                    catch (UnknownHostException ex) {
                    Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
                              }
        }                                      
          
    }//GEN-LAST:event_nutrientproperty_DeletebtnActionPerformed

    private void nutrientproperty_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nutrientproperty_jTableMouseClicked
        // TODO add your handling code here:
        nutrientproperty_editProperty_ID=-1;
        nutrientproperty_editnow_ID=0;
        nutrientproperty_status_ID=-1;
        nutrientproperty_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        nutrientproperty_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_nutrientproperty_jTableMouseClicked

    private void nutrientproperty_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nutrientproperty_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+nutrientproperty_jTable.getSelectedRow());
            if(nutrientproperty_jTable.getSelectedRow()!=0)
            nutrientproperty_TableMouseorKeyChange(nutrientproperty_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+nutrientproperty_jTable.getSelectedRow());
            if(nutrientproperty_jTable.getSelectedRow()!=nutrientproperty_tableListData.size()-1)
            nutrientproperty_TableMouseorKeyChange(nutrientproperty_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+nutrientproperty_jTable.getSelectedRow());
            if(nutrientproperty_jTable.getSelectedRow()!=nutrientproperty_tableListData.size()-1)
            nutrientproperty_TableMouseorKeyChange(nutrientproperty_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_nutrientproperty_jTableKeyPressed

    
    private void nutrientproperty_TableMouseorKeyChange(int row){
        
        nutrientproperty_createdOnTextField.setText("");
        nutrientproperty_updatedOnTextField.setText("");
        nutrientproperty_CreatedByTextField.setText("");
        nutrientproperty_UpdatedByTextField.setText("");
        
      
       Vector tableline =(Vector) nutrientproperty_tableListData.get(row);
               
        String propertyname = (String) tableline.get(0);
        Boolean isact = (Boolean) tableline.get(1);
        String createdOn = (String) tableline.get(4);
     
        String createdby = (String) tableline.get(6);
        nutrientproperty_createdOnTextField.setText(""+createdOn);
        nutrientproperty_updatedOnTextField.setText((String) tableline.get(5));
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);
        String updatedby =(String) tableline.get(7);
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);
        nutrientproperty_CreatedByTextField.setText(""+createdby);
        nutrientproperty_UpdatedByTextField.setText(""+updatedby);
        nutrientproperty_NuPropertyNameTextField.setEditable(false);
        nutrientproperty_isActiveCheckBox.setEnabled(false);

        nutrientproperty_NuPropertyNameTextField.setText(""+propertyname);
        nutrientproperty_isActiveCheckBox.setSelected(isact);
        
        nutrientproperty_editProperty_ID=(int) tableline.get(2);
        nutrientproperty_status_ID=(int) tableline.get(3);
        nutrientproperty_editBtn.setEnabled(true);
        nutrientproperty_addnewbtn.setEnabled(true);
        nutrientproperty_addListBtn.setEnabled(true);
        nutrientproperty_Deletebtn.setEnabled(true);
        
    }
    
    
    
    
    
    
    
     boolean nutrientproperty_Saveremaining=true;
    int nutrientproperty_insertID=10000000;
    private void nutrientproperty_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_saveallbtnActionPerformed

        //np.OnSaveAllButtonActionPerformed(nutrientproperty_tableListData, nutrientproperty_jTable, nutrientproperty_saveallbtn, nutrientproperty_Saveremaining);
        
        
        boolean isSuccess;
        try {
            isSuccess = sqlManager.saveNuPropertydata(nutrientproperty_tableListData);
            if(isSuccess)
        {
            JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
            np.loadNuPropertyData(nutrientproperty_tableListData,nutrientproperty_jTable);
            nutrientproperty_saveallbtn.setEnabled(false);
            nutrientproperty_Saveremaining=false;
            nutrientproperty_tableListData=sqlManager.getNuPropertydata();
        }
        } catch (SQLException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NutrientProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_nutrientproperty_saveallbtnActionPerformed

    private void nutrientproperty_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutrientproperty_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nutrientproperty_updatedOnTextFieldActionPerformed

    int compoundgroup_editCompoundgroup_ID=-1;
    int compoundgroup_status_ID=-1;
    int compoundgroup_editnow_ID=0;
    private void compoundgroup_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_editBtnActionPerformed

        compoundgroup_nameTextField.setEditable(true);

        compoundgroup_isActiveCheckBox.setEnabled(true);

        compoundgroup_editBtn.setEnabled(false);
        compoundgroup_deletebtn.setEnabled(false);
        compoundgroup_addnewbtn.setEnabled(false);

        compoundgroup_addListBtn.setEnabled(true);
        compoundgroup_editnow_ID=1;
    }//GEN-LAST:event_compoundgroup_editBtnActionPerformed

    private void compoundgroup_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_addnewbtnActionPerformed
        // TODO add your handling code here:
        //        //  codeTextField.setText("");
        compoundgroup_nameTextField.setText("");

        compoundgroup_nameTextField.setEditable(true);

        compoundgroup_isActiveCheckBox.setSelected(true);
        compoundgroup_isActiveCheckBox.setEnabled(true);
        compoundgroup_deletebtn.setEnabled(false);
        compoundgroup_editBtn.setEnabled(false);

        compoundgroup_addListBtn.setEnabled(true);
        compoundgroup_status_ID=-1;
        compoundgroup_editCompoundgroup_ID=-1;
        compoundgroup_editnow_ID=0;
        compoundgroup_createdOnTextField.setText("");
        compoundgroup_updatedOnTextField.setText("");
        compoundgroup_CreatedByTextField.setText("");
        compoundgroup_UpdatedByTextField.setText("");
    }//GEN-LAST:event_compoundgroup_addnewbtnActionPerformed

    private void compoundgroup_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_addListBtnActionPerformed

//        cg.OnAddLListButtonActionPerformed(compoundgroup_nameTextField, compoundgroup_isActiveCheckBox, compoundgroup_tableListData
//            , compoundgroup_editCompoundgroup_ID, compoundgroup_insertID, compoundgroup_status_ID
//            , compoundgroup_jTable, compoundgroup_addListBtn, compoundgroup_deletebtn
//            , compoundgroup_saveallbtn, compoundgroup_editnow_ID,compoundgroup_Saveremaining);


        String name=compoundgroup_nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        boolean isActiveCheck=compoundgroup_isActiveCheckBox.isSelected();

        for(int i=0;i<compoundgroup_tableListData.size();i++)
        {
            Vector tableline =(Vector) compoundgroup_tableListData.get(i);

            int Compoundgroup_ID=(int) tableline.get(2);
            if(compoundgroup_editCompoundgroup_ID==Compoundgroup_ID) continue;
 
            String nam = (String) tableline.get(0);//1
            if(nam.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            
        }
        if(compoundgroup_editCompoundgroup_ID<1){
            Vector line = new Vector();
          
            line.add(name);//1//0
            
            line.add(isActiveCheck);//2//1
          
       
            //11
            line.add(compoundgroup_insertID);//3 ======== PLANT_ID previous
            line.add(compoundgroup_status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            compoundgroup_tableListData.add(line);
            compoundgroup_insertID++ ;
        }
        else{
            for(int i=0;i<compoundgroup_tableListData.size();i++)
            {
                Vector line =(Vector) compoundgroup_tableListData.get(i);
                int Compoundgroup_ID=(int) line.get(2);
              
                if(compoundgroup_editCompoundgroup_ID==Compoundgroup_ID){
                    compoundgroup_status_ID=2;
                   // line.set(0, symbol);
                    line.set(0,name);//1
                   
                    line.set(1,isActiveCheck);//2
                    
                    line.set(2,compoundgroup_editCompoundgroup_ID);//3 ======== Plant_ID previous
                    line.set(3,compoundgroup_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        final    DefaultTableModel model1 = (DefaultTableModel)compoundgroup_jTable.getModel();
        model1.setRowCount(compoundgroup_tableListData.size());
        for(int i=0;i<compoundgroup_tableListData.size();i++)
        {
                Vector tableline =(Vector) compoundgroup_tableListData.get(i);
               

                String nam = (String) tableline.get(0);//1
                model1.setValueAt((Object)nam, i, 0);//1
               
                Boolean isact = (Boolean) tableline.get(1);//2
                model1.setValueAt((Object)isact, i, 1);//2
                
        }

        compoundgroup_nameTextField.setText("");
        compoundgroup_nameTextField.setEnabled(true);
        
        compoundgroup_isActiveCheckBox.setSelected(true);
        compoundgroup_addListBtn.setEnabled(false);
        compoundgroup_deletebtn.setEnabled(true);
        compoundgroup_editCompoundgroup_ID=-1;
        compoundgroup_status_ID=-1;
        compoundgroup_editnow_ID=0;
        compoundgroup_saveallbtn.setEnabled(true);
        compoundgroup_Saveremaining = true;
            
    
    
    }//GEN-LAST:event_compoundgroup_addListBtnActionPerformed

    private void compoundgroup_nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compoundgroup_nameTextFieldActionPerformed

    private void compoundgroup_deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_deletebtnActionPerformed
        // TODO add your handling code here:

        //cg.OnDeleteButtonActionPerformed(compoundgroup_tableListData, compoundgroupjTable, compoundgroup_deletebtn,
          //  compoundgroup_editBtn, compoundgroup_addnewbtn);
        
          
        int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
        if(dialogDelete==JOptionPane.YES_OPTION){     
                DefaultTableModel model1 = (DefaultTableModel)compoundgroup_jTable.getModel();
                int row = compoundgroup_jTable.getSelectedRow();
                Vector tableline =(Vector) compoundgroup_tableListData.get(row);
              
                     int Compoundtype_ID=(int) tableline.get(2);//3
                     
            boolean isSuccess;
            try {
                isSuccess = sqlManager.deleteCompoundgroupdata(Compoundtype_ID);
                if(isSuccess){ 
                     JOptionPane.showMessageDialog(null, "Delete Successfully from h2 Database Server", "Delete Successfully from h2 Database Server", JOptionPane.INFORMATION_MESSAGE);
                     cg.loadCompoundgroupData(compoundgroup_tableListData,compoundgroup_jTable);
                      compoundgroup_tableListData=sqlManager.getCompoundgroupdata();
                               }
                   } 
            catch (SQLException ex) {
                                              } 
            catch (UnknownHostException ex) {
                                              }
        }

        compoundgroup_deletebtn.setEnabled(false);
        compoundgroup_editBtn.setEnabled(false);
        compoundgroup_addnewbtn.setEnabled(true);  
        
        

    }//GEN-LAST:event_compoundgroup_deletebtnActionPerformed

    private void compoundgroup_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compoundgroup_jTableMouseClicked
        // TODO add your handling code here:
        compoundgroup_editCompoundgroup_ID=-1;
        compoundgroup_editnow_ID=0;
        compoundgroup_status_ID=-1;
        compoundgroup_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        compoundgroup_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_compoundgroup_jTableMouseClicked

    private void compoundgroup_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compoundgroup_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+compoundgroup_jTable.getSelectedRow());
            if(compoundgroup_jTable.getSelectedRow()!=0)
            compoundgroup_TableMouseorKeyChange(compoundgroup_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+compoundgroup_jTable.getSelectedRow());
            if(compoundgroup_jTable.getSelectedRow()!=compoundgroup_tableListData.size()-1)
            compoundgroup_TableMouseorKeyChange(compoundgroup_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+compoundgroup_jTable.getSelectedRow());
            if(compoundgroup_jTable.getSelectedRow()!=compoundgroup_tableListData.size()-1)
            compoundgroup_TableMouseorKeyChange(compoundgroup_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_compoundgroup_jTableKeyPressed

    
    private void compoundgroup_TableMouseorKeyChange(int row){
        
        compoundgroup_createdOnTextField.setText("");
        compoundgroup_updatedOnTextField.setText("");
        compoundgroup_CreatedByTextField.setText("");
        compoundgroup_UpdatedByTextField.setText("");
        
      
       Vector tableline =(Vector) compoundgroup_tableListData.get(row);
               
      
        String nam = (String) tableline.get(0);//1
  
        Boolean isact = (Boolean) tableline.get(1);//2
        String createdOn = (String) tableline.get(6);//7
     
        String createdby = (String) tableline.get(4);//5
        compoundgroup_createdOnTextField.setText(""+createdOn);//
        compoundgroup_updatedOnTextField.setText((String) tableline.get(7));//8
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);//
        String updatedby =(String) tableline.get(5);//6
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);//
        compoundgroup_CreatedByTextField.setText(""+createdby);
        compoundgroup_UpdatedByTextField.setText(updatedby);
        compoundgroup_nameTextField.setEditable(false);
        compoundgroup_isActiveCheckBox.setEnabled(false);
        compoundgroup_nameTextField.setText(""+nam);
        compoundgroup_isActiveCheckBox.setSelected(isact);
        
   
        compoundgroup_editCompoundgroup_ID=(int) tableline.get(2);//3
        compoundgroup_status_ID=(int) tableline.get(3);//4
        compoundgroup_editBtn.setEnabled(true);
        compoundgroup_addnewbtn.setEnabled(true);
        compoundgroup_addListBtn.setEnabled(false);
        compoundgroup_deletebtn.setEnabled(true);
        
    }
    
    
    
    
    
    
      boolean compoundgroup_Saveremaining=true;
    int compoundgroup_insertID=10000000; 
    private void compoundgroup_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_saveallbtnActionPerformed

        //cg.OnSaveAllButtonActionPerformed(compoundgroup_tableListData, compoundgroup_jTable, compoundgroup_Saveremaining);
        
        try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveCompoundgroupData(compoundgroup_tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                cg.loadCompoundgroupData(compoundgroup_tableListData,compoundgroup_jTable);
               // saveallbtn.setEnabled(false);
                compoundgroup_Saveremaining = false;
                compoundgroup_tableListData=sqlManager.getCompoundgroupdata();
            }
        } catch (SQLException ex) {
        } catch (UnknownHostException ex) {
        }
    }//GEN-LAST:event_compoundgroup_saveallbtnActionPerformed

    private void compoundgroup_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundgroup_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compoundgroup_updatedOnTextFieldActionPerformed

     int compoundtype_editCompoundtype_ID=-1;
    int compoundtype_status_ID=-1;
    int compoundtype_editnow_ID=0;
    private void compoundtype_editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_editBtnActionPerformed

        compoundtype_nameTextField.setEditable(true);

        compoundtype_isActiveCheckBox.setEnabled(true);

        compoundtype_editBtn.setEnabled(false);
        compoundtype_deletebtn.setEnabled(false);
        compoundtype_addnewbtn.setEnabled(false);

        compoundtype_addListBtn.setEnabled(true);
        compoundtype_editnow_ID=1;
    }//GEN-LAST:event_compoundtype_editBtnActionPerformed

    private void compoundtype_addnewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_addnewbtnActionPerformed
        // TODO add your handling code here:
        //  codeTextField.setText("");
        compoundtype_nameTextField.setText("");

        compoundtype_nameTextField.setEditable(true);

        compoundtype_isActiveCheckBox.setSelected(true);
        compoundtype_isActiveCheckBox.setEnabled(true);
        compoundtype_deletebtn.setEnabled(false);
        compoundtype_editBtn.setEnabled(false);

        compoundtype_addListBtn.setEnabled(true);
        compoundtype_status_ID=-1;
        compoundtype_editCompoundtype_ID=-1;
        compoundtype_editnow_ID=0;
        compoundtype_createdOnTextField.setText("");
        compoundtype_updatedOnTextField.setText("");
        compoundtype_CreatedByTextField.setText("");
        compoundtype_UpdatedByTextField.setText("");
    }//GEN-LAST:event_compoundtype_addnewbtnActionPerformed

    private void compoundtype_addListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_addListBtnActionPerformed

//        ct.OnAddToListButtonActionPerformed(compoundtype_nameTextField, compoundtype_isActiveCheckBox, compoundtype_tableListData
//            , compoundtype_editCompoundtype_ID, compoundtype_insertID, compoundtype_status_ID, compoundtype_jTable, compoundtype_deletebtn
//            , compoundtype_addListBtn, compoundtype_saveallbtn, compoundtype_editnow_ID,compoundtype_Saveremaining);

        
        
         String name=compoundtype_nameTextField.getText();
        if(name.length()<1){
          JOptionPane.showMessageDialog(null, 
                "Provide Name", "Short Name Error", JOptionPane.ERROR_MESSAGE);  
          return;
        }
        boolean isActiveCheck=compoundtype_isActiveCheckBox.isSelected();

        for(int i=0;i<compoundtype_tableListData.size();i++)
        {
            Vector tableline =(Vector) compoundtype_tableListData.get(i);

            int Compoundtype_ID=(int) tableline.get(2);
            if(compoundtype_editCompoundtype_ID==Compoundtype_ID) continue;
 
            String nam = (String) tableline.get(0);//1
            if(nam.compareTo(name)==0){
                JOptionPane.showMessageDialog(null, 
                 "Name Already Exits", "Name Error", JOptionPane.ERROR_MESSAGE);  
                return;
            }
            
        }
        if(compoundtype_editCompoundtype_ID<1){
            Vector line = new Vector();
          
            line.add(name);//1//0
            
            line.add(isActiveCheck);//2//1
          
       
            //11
            line.add(compoundtype_insertID);//3 ======== PLANT_ID previous
            line.add(compoundtype_status_ID);//4========1 newadd 2update 0 for exits 
            line.add(""); //5
            line.add(""); //6
            line.add(""); //7
            line.add(""); //8
            compoundtype_tableListData.add(line);
            compoundtype_insertID++ ;
        }
        else{
            for(int i=0;i<compoundtype_tableListData.size();i++)
            {
                Vector line =(Vector) compoundtype_tableListData.get(i);
                int Compoundtype_ID=(int) line.get(2);
              
                if(compoundtype_editCompoundtype_ID==Compoundtype_ID){
                    compoundtype_status_ID=2;
                   // line.set(0, symbol);
                    line.set(0,name);//1
                   
                    line.set(1,isActiveCheck);//2
                    
                    line.set(2,compoundtype_editCompoundtype_ID);//3 ======== Plant_ID previous
                    line.set(3,compoundtype_status_ID);//4======== -1 newadd 2 update 0 for exits 
                }
            }
        }
        
        final    DefaultTableModel model1 = (DefaultTableModel)compoundtype_jTable.getModel();
        model1.setRowCount(compoundtype_tableListData.size());
        for(int i=0;i<compoundtype_tableListData.size();i++)
        {
                Vector tableline =(Vector) compoundtype_tableListData.get(i);
               

                String nam = (String) tableline.get(0);//1
                model1.setValueAt((Object)nam, i, 0);//1
               
                Boolean isact = (Boolean) tableline.get(1);//2
                model1.setValueAt((Object)isact, i, 1);//2
                
        }

        compoundtype_nameTextField.setText("");
        compoundtype_nameTextField.setEnabled(true);
        
        compoundtype_isActiveCheckBox.setSelected(true);
        compoundtype_addListBtn.setEnabled(false);
        compoundtype_deletebtn.setEnabled(true);
        compoundtype_editCompoundtype_ID=-1;
        compoundtype_status_ID=-1;
        compoundtype_editnow_ID=0;
        compoundtype_saveallbtn.setEnabled(true);
        compoundtype_Saveremaining = true;
        
        



        
    }//GEN-LAST:event_compoundtype_addListBtnActionPerformed

    private void compoundtype_nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compoundtype_nameTextFieldActionPerformed

    private void compoundtype_deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_deletebtnActionPerformed
        // TODO add your handling code here:

//        ct.OnDeleteButtonActionPerformed(compoundtype_jTable, compoundtype_tableListData, compoundtype_editBtn, compoundtype_addnewbtn
//            , compoundtype_deletebtn);
        
        
        int dialogDelete=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Row?", "DELETE Row",JOptionPane.YES_NO_OPTION);
        if(dialogDelete==JOptionPane.YES_OPTION){     
                DefaultTableModel model1 = (DefaultTableModel)compoundtype_jTable.getModel();
                int row = compoundtype_jTable.getSelectedRow();
                Vector tableline =(Vector) compoundtype_tableListData.get(row);
              
                     int Compoundtype_ID=(int) tableline.get(2);//3
                     
            boolean isSuccess;
            try {
            isSuccess = sqlManager.deleteCompoundtypedata(Compoundtype_ID);
            if(isSuccess){ 
      
                JOptionPane.showMessageDialog(null, "Delete Successfully from h2 Database Server", "Delete Successfully from h2 Database Server", JOptionPane.INFORMATION_MESSAGE);
                ct.loadCompoundtypeData(compoundtype_tableListData,compoundtype_jTable);
                 compoundtype_tableListData=sqlManager.getCompoundType();
                //Saveremaining = false;
                         }
             } catch (SQLException ex) {
            //Logger.getLogger(Unit_1.class.getName()).log(Level.SEVERE, null, ex);
                                        } 
            catch (UnknownHostException ex) {
            //Logger.getLogger(Unit_1.class.getName()).log(Level.SEVERE, null, ex);
                       }
        }
        compoundtype_deletebtn.setEnabled(false);
        compoundtype_editBtn.setEnabled(false);
        compoundtype_addnewbtn.setEnabled(true);
        
        
    }//GEN-LAST:event_compoundtype_deletebtnActionPerformed

    private void compoundtype_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compoundtype_jTableMouseClicked
        // TODO add your handling code here:
        compoundtype_editCompoundtype_ID=-1;
        compoundtype_editnow_ID=0;
        compoundtype_status_ID=-1;
        compoundtype_jTable.getSelectedRow();
        int row = ( (JTable) evt.getSource() ).rowAtPoint(evt.getPoint());
        int column = ( (JTable) evt.getSource() ).columnAtPoint(evt.getPoint());

        compoundtype_TableMouseorKeyChange(row);

        //JOptionPane.showMessageDialog(null, row+"Code Already Exits "+jTable1.getSelectedRow(), "Code Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_compoundtype_jTableMouseClicked

    private void compoundtype_jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compoundtype_jTableKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP arrow key pressed "+compoundtype_jTable.getSelectedRow());
            if(compoundtype_jTable.getSelectedRow()!=0)
            compoundtype_TableMouseorKeyChange(compoundtype_jTable.getSelectedRow()-1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow key pressed "+compoundtype_jTable.getSelectedRow());
            if(compoundtype_jTable.getSelectedRow()!=compoundtype_tableListData.size()-1)
            compoundtype_TableMouseorKeyChange(compoundtype_jTable.getSelectedRow()+1);
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("down arrow key pressed "+compoundtype_jTable.getSelectedRow());
            if(compoundtype_jTable.getSelectedRow()!=compoundtype_tableListData.size()-1)
            compoundtype_TableMouseorKeyChange(compoundtype_jTable.getSelectedRow()+1);

        }
    }//GEN-LAST:event_compoundtype_jTableKeyPressed

    
    private void compoundtype_TableMouseorKeyChange(int row){
        
        compoundtype_createdOnTextField.setText("");
        compoundtype_updatedOnTextField.setText("");
        compoundtype_CreatedByTextField.setText("");
        compoundtype_UpdatedByTextField.setText("");
        
      
        Vector tableline =(Vector) compoundtype_tableListData.get(row);
               
      
        String nam = (String) tableline.get(0);//1
  
        Boolean isact = (Boolean) tableline.get(1);//2
        String createdOn = (String) tableline.get(6);//7
     
        String createdby = (String) tableline.get(4);//5
        compoundtype_createdOnTextField.setText(""+createdOn);//
        compoundtype_updatedOnTextField.setText((String) tableline.get(7));//8
        if(createdby.length()>20)
            createdby = createdby.substring(0,19);//
        String updatedby =(String) tableline.get(5);//6
        if(updatedby.length()>20)
            updatedby = updatedby.substring(0,19);//
        compoundtype_CreatedByTextField.setText(""+createdby);
        compoundtype_UpdatedByTextField.setText(updatedby);
        //codeTextField.setEditable(false);
        compoundtype_nameTextField.setEditable(false);
       
        compoundtype_isActiveCheckBox.setEnabled(false);
        
        
        compoundtype_nameTextField.setText(""+nam);
     
        compoundtype_isActiveCheckBox.setSelected(isact);
        
   
        compoundtype_editCompoundtype_ID=(int) tableline.get(2);//3
        compoundtype_status_ID=(int) tableline.get(3);//4
         //int unitid=(int) tableline.get(9);
        compoundtype_editBtn.setEnabled(true);
        compoundtype_addnewbtn.setEnabled(true);
        compoundtype_addListBtn.setEnabled(false);
        compoundtype_deletebtn.setEnabled(true);
        
    }
    
    
    
    
    boolean ingredientgroup_Saveremaining=true;
    int ingredientgroup_insertID=10000000; 
    boolean compoundtype_Saveremaining=true;
    int compoundtype_insertID=10000000; 
    private void compoundtype_saveallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_saveallbtnActionPerformed

        //ct.OnSaveAllButtonActionPerformed(compoundtype_jTable, compoundtype_tableListData, compoundtype_Saveremaining);
        
        
            try {
            // TODO add your handling code here:
            boolean isSuccess=sqlManager.saveCompoundtypeData(compoundtype_tableListData);
            if(isSuccess)
            {
                JOptionPane.showMessageDialog(null, "Save Successfully", "Save Successfully", JOptionPane.INFORMATION_MESSAGE);
                ct.loadCompoundtypeData(compoundtype_tableListData,compoundtype_jTable);
               // saveallbtn.setEnabled(false);
                compoundtype_Saveremaining = false;
                compoundtype_tableListData=sqlManager.getCompoundType();
            }
            } catch (SQLException ex) {
            } catch (UnknownHostException ex) {
            }
    }//GEN-LAST:event_compoundtype_saveallbtnActionPerformed

    private void compoundtype_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundtype_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compoundtype_updatedOnTextFieldActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        final int index = jTabbedPane1.getSelectedIndex();

        if(index>0){

            if(SwingUtilities.isRightMouseButton(evt)){
                jTabbedPane1.remove(index);

            }

        }

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void newbtn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn12ActionPerformed

    private void newbtn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn13ActionPerformed

    private void newbtn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn15ActionPerformed

    private void newbtn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn16ActionPerformed

    private void newbtn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn17ActionPerformed

    private void newbtn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn18ActionPerformed

    private void newbtn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn20ActionPerformed

    private void newbtn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn21ActionPerformed

    private void newbtn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn23ActionPerformed

    private void newbtn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn26ActionPerformed

    private void newbtn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn10ActionPerformed

    private void newbtn29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbtn29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbtn29ActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
//        this.dispose();
//        System.exit(0)
        about_us ab=new about_us();
        ab.setLocation(200, 120);
        ab.setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void ingredientgroup_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientgroup_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredientgroup_updatedOnTextFieldActionPerformed

    private void unit_updatedOnTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_updatedOnTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_updatedOnTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PriceList_jTable;
    private javax.swing.JButton compoundRefreshBtn;
    private javax.swing.JTable compound_jTable;
    private javax.swing.JTextField compoundgroup_CreatedByTextField;
    private javax.swing.JTextField compoundgroup_UpdatedByTextField;
    private javax.swing.JButton compoundgroup_addListBtn;
    private javax.swing.JButton compoundgroup_addnewbtn;
    private javax.swing.JTextField compoundgroup_createdOnTextField;
    private javax.swing.JButton compoundgroup_deletebtn;
    private javax.swing.JButton compoundgroup_editBtn;
    private javax.swing.JCheckBox compoundgroup_isActiveCheckBox;
    private javax.swing.JTable compoundgroup_jTable;
    private javax.swing.JTextField compoundgroup_nameTextField;
    private javax.swing.JButton compoundgroup_saveallbtn;
    private javax.swing.JTextField compoundgroup_updatedOnTextField;
    private javax.swing.JTextField compoundtype_CreatedByTextField;
    private javax.swing.JTextField compoundtype_UpdatedByTextField;
    private javax.swing.JButton compoundtype_addListBtn;
    private javax.swing.JButton compoundtype_addnewbtn;
    private javax.swing.JTextField compoundtype_createdOnTextField;
    private javax.swing.JButton compoundtype_deletebtn;
    private javax.swing.JButton compoundtype_editBtn;
    private javax.swing.JCheckBox compoundtype_isActiveCheckBox;
    private javax.swing.JTable compoundtype_jTable;
    private javax.swing.JTextField compoundtype_nameTextField;
    private javax.swing.JButton compoundtype_saveallbtn;
    private javax.swing.JTextField compoundtype_updatedOnTextField;
    private javax.swing.JButton deleteBtn1;
    private javax.swing.JButton deleteBtn2;
    private javax.swing.JButton detailsBtn;
    private javax.swing.JButton detailsbtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JButton editbtn1;
    private javax.swing.JButton ingredientRefreshBtn;
    private javax.swing.JButton ingredient_deleteBtn;
    private javax.swing.JTable ingredient_jTable;
    private javax.swing.JButton ingredient_newbtn;
    private javax.swing.JButton ingredientbtn;
    private javax.swing.JTextField ingredientgroup_CreatedByTextField;
    private javax.swing.JButton ingredientgroup_Deletebtn;
    private javax.swing.JTextField ingredientgroup_IngredientNameTextField;
    private javax.swing.JTextField ingredientgroup_UpdatedByTextField;
    private javax.swing.JButton ingredientgroup_addListBtn;
    private javax.swing.JButton ingredientgroup_addnewbtn;
    private javax.swing.JTextField ingredientgroup_createdOnTextField;
    private javax.swing.JButton ingredientgroup_editBtn;
    private javax.swing.JCheckBox ingredientgroup_isActiveCheckBox;
    private javax.swing.JTable ingredientgroup_jTable;
    private javax.swing.JButton ingredientgroup_saveallbtn;
    private javax.swing.JTextField ingredientgroup_updatedOnTextField;
    private javax.swing.JTextField ingredienttype_CreatedByTextField;
    private javax.swing.JButton ingredienttype_Deletebtn;
    private javax.swing.JTextField ingredienttype_IngredientTypeTextField;
    private javax.swing.JTextField ingredienttype_UpdatedByTextField;
    private javax.swing.JButton ingredienttype_addListBtn;
    private javax.swing.JButton ingredienttype_addnewbtn;
    private javax.swing.JTextField ingredienttype_createdOnTextField;
    private javax.swing.JButton ingredienttype_editBtn;
    private javax.swing.JCheckBox ingredienttype_isActiveCheckBox;
    private javax.swing.JTable ingredienttype_jTable;
    private javax.swing.JButton ingredienttype_saveallbtn;
    private javax.swing.JTextField ingredienttype_updatedOnTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel_Compound;
    private javax.swing.JPanel jPanel_CompoundGroup;
    private javax.swing.JPanel jPanel_CompoundType;
    private javax.swing.JPanel jPanel_Dashboard;
    private javax.swing.JPanel jPanel_Ingredient;
    private javax.swing.JPanel jPanel_IngredientGroup;
    private javax.swing.JPanel jPanel_IngredientType;
    private javax.swing.JPanel jPanel_Nutrient;
    private javax.swing.JPanel jPanel_NutrientProperty;
    private javax.swing.JPanel jPanel_Pricelist;
    private javax.swing.JPanel jPanel_UnitEntry;
    private javax.swing.JPanel jPanel_UserInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton newbtn1;
    private javax.swing.JButton newbtn10;
    private javax.swing.JButton newbtn11;
    private javax.swing.JButton newbtn12;
    private javax.swing.JButton newbtn13;
    private javax.swing.JButton newbtn14;
    private javax.swing.JButton newbtn15;
    private javax.swing.JButton newbtn16;
    private javax.swing.JButton newbtn17;
    private javax.swing.JButton newbtn18;
    private javax.swing.JButton newbtn19;
    private javax.swing.JButton newbtn20;
    private javax.swing.JButton newbtn21;
    private javax.swing.JButton newbtn22;
    private javax.swing.JButton newbtn23;
    private javax.swing.JButton newbtn24;
    private javax.swing.JButton newbtn26;
    private javax.swing.JButton newbtn27;
    private javax.swing.JButton newbtn28;
    private javax.swing.JButton newbtn29;
    private javax.swing.JButton newbtn30;
    private javax.swing.JButton newbtn4;
    private javax.swing.JButton newbtn5;
    private javax.swing.JButton newbtn7;
    private javax.swing.JButton newbtn8;
    private javax.swing.JButton newbtn9;
    private javax.swing.JTextField nutrient_CreatedByTextField;
    private javax.swing.JTextField nutrient_UpdatedByTextField;
    private javax.swing.JButton nutrient_addListBtn;
    private javax.swing.JButton nutrient_addnewbtn;
    private javax.swing.JCheckBox nutrient_broilerCheckBox;
    private javax.swing.JTextField nutrient_codeTextField;
    private javax.swing.JTextField nutrient_createdOnTextField;
    private javax.swing.JButton nutrient_editBtn;
    private javax.swing.JCheckBox nutrient_fishCheckBox;
    private javax.swing.JCheckBox nutrient_isActiveCheckBox;
    private javax.swing.JTable nutrient_jTable;
    private javax.swing.JCheckBox nutrient_layerCheckBox;
    private javax.swing.JTextField nutrient_longTextField;
    private javax.swing.JTextField nutrient_nameTextField;
    private javax.swing.JCheckBox nutrient_otherCheckBox;
    private javax.swing.JComboBox nutrient_propertyComboBox;
    private javax.swing.JButton nutrient_saveallbtn;
    private javax.swing.JComboBox nutrient_unitComboBox;
    private javax.swing.JTextField nutrient_updatedOnTextField;
    private javax.swing.JButton nutrientbtn;
    private javax.swing.JTextField nutrientproperty_CreatedByTextField;
    private javax.swing.JButton nutrientproperty_Deletebtn;
    private javax.swing.JTextField nutrientproperty_NuPropertyNameTextField;
    private javax.swing.JTextField nutrientproperty_UpdatedByTextField;
    private javax.swing.JButton nutrientproperty_addListBtn;
    private javax.swing.JButton nutrientproperty_addnewbtn;
    private javax.swing.JTextField nutrientproperty_createdOnTextField;
    private javax.swing.JButton nutrientproperty_editBtn;
    private javax.swing.JCheckBox nutrientproperty_isActiveCheckBox;
    private javax.swing.JTable nutrientproperty_jTable;
    private javax.swing.JButton nutrientproperty_saveallbtn;
    private javax.swing.JTextField nutrientproperty_updatedOnTextField;
    private javax.swing.JButton pricelistRefreshBtn;
    private javax.swing.JLabel totcomlbl;
    private javax.swing.JLabel totinglbl;
    private javax.swing.JLabel totnutlbl;
    private javax.swing.JLabel totprilbl;
    private javax.swing.JTextField unit_CreatedByTextField;
    private javax.swing.JTextField unit_UpdatedByTextField;
    private javax.swing.JButton unit_addListBtn;
    private javax.swing.JButton unit_addnewbtn;
    private javax.swing.JTextField unit_codeTextField;
    private javax.swing.JTextField unit_createdOnTextField;
    private javax.swing.JButton unit_deletebtn;
    private javax.swing.JButton unit_editBtn;
    private javax.swing.JCheckBox unit_isActiveCheckBox;
    private javax.swing.JTable unit_jTable;
    private javax.swing.JTextField unit_nameTextField;
    private javax.swing.JButton unit_saveallbtn;
    private javax.swing.JTextField unit_updatedOnTextField;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel userNameLabel2;
    private javax.swing.JTextField userinfo_CreatedByTextField;
    private javax.swing.JPasswordField userinfo_PasswordTextField;
    private javax.swing.JTextField userinfo_UpdatedByTextField;
    private javax.swing.JTextField userinfo_UserIDTextField;
    private javax.swing.JButton userinfo_addListBtn;
    private javax.swing.JButton userinfo_addnewbtn;
    private javax.swing.JTextField userinfo_createdOnTextField;
    private javax.swing.JButton userinfo_editBtn;
    private javax.swing.JCheckBox userinfo_isActiveCheckBox;
    private javax.swing.JTable userinfo_jTable;
    private javax.swing.JButton userinfo_saveallbtn;
    private javax.swing.JTextField userinfo_updatedOnTextField;
    // End of variables declaration//GEN-END:variables

    private ClosableTabbedPane jTabbedPane2;
}
