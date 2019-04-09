package SysnovaFeed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class jasperReport {
   public void formulationReport(int compound_ID) throws IOException, JRException{
    // Report Viewer
    Connection connect = null;
    connect=h2JDBCConnection.h2JDBCConnection();
    File jrxmlDir = new File(""+System.getProperty("user.dir")+"/report");
    System.out.println("getCanonicalPath ---   "+jrxmlDir);
    if (!jrxmlDir.exists() || !jrxmlDir.isDirectory()){
        JOptionPane.showMessageDialog(null, "Report Directory Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    Map params = new HashMap();
    
    
    JasperPrint ip=null;
    try {
        String report = jrxmlDir+"/Formulation.jrxml";
        String subreport = jrxmlDir+"/Formulation_subreport1.jasper";
        //System.out.println("getCanonicalPath ---   "+jrxmlDir);
        JasperReport ir = JasperCompileManager.compileReport(report);
        params.put("ID", compound_ID);
        params.put("SUBREPORT_DIR", ""+subreport);
        ip = JasperFillManager.fillReport(ir, params,connect);
    } 
    catch (Exception e) {
        System.out.println(""+e);
        JOptionPane.showMessageDialog(null, "Report .jrxml/.Jasper file Not Found \n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Open Report in JFrame
    JFrame frame = new JFrame();
    frame.setTitle("SysnovaFeed Report");
    frame.setBounds(100, 100, 800,600);
    frame.getContentPane().add(new JRViewer(ip));
    frame.setVisible(true);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
   }
   
   public void blendOrderReport(int compound_ID) throws IOException, JRException{
    // Report Viewer
    Connection connect = null;
    connect=h2JDBCConnection.h2JDBCConnection();
    File jrxmlDir = new File(""+System.getProperty("user.dir")+"/report");
    System.out.println("getCanonicalPath ---   "+jrxmlDir);
    if (!jrxmlDir.exists() || !jrxmlDir.isDirectory()){
        JOptionPane.showMessageDialog(null, "Report Directory Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    Map params = new HashMap();
    
    
    JasperPrint ip=null;
    try {
        String report = jrxmlDir+"/BlendOrder.jrxml";
        //System.out.println("getCanonicalPath ---   "+jrxmlDir);
        JasperReport ir = JasperCompileManager.compileReport(report);
        params.put("ID", compound_ID);
        ip = JasperFillManager.fillReport(ir, params,connect);
    } 
    catch (Exception e) {
        System.out.println(""+e);
        JOptionPane.showMessageDialog(null, "Report .jrxml/.Jasper file Not Found \n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Open Report in JFrame
    JFrame frame = new JFrame();
    frame.setTitle("SysnovaFeed Report");
    frame.setBounds(100, 100, 800,600);
    frame.getContentPane().add(new JRViewer(ip));
    frame.setVisible(true);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
   }
   
   public void igredientDetailsReport(int ingredientID) throws IOException, JRException{
    // Report Viewer
    Connection connect = null;
    connect=h2JDBCConnection.h2JDBCConnection();
    File jrxmlDir = new File(""+System.getProperty("user.dir")+"/report");
    System.out.println("getCanonicalPath ---   "+jrxmlDir);
    if (!jrxmlDir.exists() || !jrxmlDir.isDirectory()){
        JOptionPane.showMessageDialog(null, "Report Directory Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    Map params = new HashMap();
    
    
    JasperPrint ip=null;
    try {
        String report = jrxmlDir+"/IngredientDetails.jrxml";
        //System.out.println("getCanonicalPath ---   "+jrxmlDir);
        JasperReport ir = JasperCompileManager.compileReport(report);
        params.put("ID", ingredientID);
        ip = JasperFillManager.fillReport(ir, params,connect);
    } 
    catch (Exception e) {
        System.out.println(""+e);
        JOptionPane.showMessageDialog(null, "Report .jrxml/.Jasper file Not Found \n"+e, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Open Report in JFrame
    JFrame frame = new JFrame();
    frame.setTitle("SysnovaFeed Report");
    frame.setBounds(100, 100, 800,600);
    frame.getContentPane().add(new JRViewer(ip));
    frame.setVisible(true);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
   }
}
