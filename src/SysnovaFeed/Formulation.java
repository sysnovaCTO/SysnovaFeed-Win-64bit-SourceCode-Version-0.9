package SysnovaFeed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.*;  
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class Formulation {

   
    glp_prob lp;
    glp_smcp parm;
    SWIGTYPE_p_int ind;
    SWIGTYPE_p_double val;
    int ret;
    ArrayList ingrediantValueList = new ArrayList();
    ArrayList nutrientValueList = new ArrayList();
    ArrayList constrainList = new ArrayList();
    //ArrayList ingrediantPriceList = new ArrayList();
    public boolean calculation(int Compound_id){
        HashMap ingredientMap=new HashMap();
        ingredientMap.clear();
        ingrediantValueList.clear();
        nutrientValueList.clear();
        constrainList.clear();
        boolean status=false;
        try {
            ingrediantValueList=sqlManager.getCompoundingredientformulation(Compound_id);
            nutrientValueList=sqlManager.getCompoundNutrientformulation(Compound_id);
            for(int i=0;i<ingrediantValueList.size();i++)
            {                    
                Vector tableline1 =(Vector) ingrediantValueList.get(i);
                int INGREDIENT_ID=(Integer) tableline1.get(0);
                if(!ingredientMap.containsKey(INGREDIENT_ID)){
                    ingredientMap.put(INGREDIENT_ID, (i+1));
                }
            }
            constrainList=sqlManager.getConstrain(Compound_id,ingredientMap);
            
        } catch (SQLException ex) {
            Logger.getLogger(Formulation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Formulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ingrediantValueList.size()<1 ||nutrientValueList.size()<1 ||constrainList.size()<1){
            JOptionPane.showMessageDialog(null, "Data Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            lp = GLPK.glp_create_prob();
            GLPK.glp_set_prob_name(lp, "LibreFeed");

            // column setup
            ind = GLPK.new_intArray(ingrediantValueList.size()+10);
            val = GLPK.new_doubleArray(ingrediantValueList.size()+10);
            GLPK.glp_add_cols(lp, (ingrediantValueList.size()+1));
            for(int i=0;i<ingrediantValueList.size();i++)
            {
                //INGREDIENT_ID ,COALESCE(PRICE,-1),COALESCE(MINVALUE ,-1) ,COALESCE(MAXVALUE,-1),COMPOUNDINGREDIENT_ID
                Vector ingrediantLine=(Vector)ingrediantValueList.get(i);
                int COMPOUNDINGREDIENT_ID=(Integer) ingrediantLine.get(4);
                Double minVal=(Double)ingrediantLine.get(2);
                Double maxVal=(Double)ingrediantLine.get(3);
                GLPK.glp_set_col_name(lp, i+1, ""+COMPOUNDINGREDIENT_ID);
                if(minVal==0.00 && maxVal==0.00){

                    GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                    GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_DB, minVal, (maxVal+getTotalProduceQty()));//double boundary
                }
                else if(minVal>0.00 && maxVal==0.00){
                    GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                    GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_LO, minVal, (maxVal+getTotalProduceQty()));//double boundary
                }
                else if(maxVal-minVal<= 0.00000001){
                     GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                     GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_FX, minVal, maxVal);//double boundary
                }
                else if(minVal>0.00 && maxVal>0.00){
                     GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                     GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_DB, minVal, maxVal);//double boundary
                }
                else if(minVal==0.00 && maxVal>0.00){
                     GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                     GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_DB, minVal, maxVal);//double boundary
                }
                else{
                    GLPK.glp_set_col_kind(lp, i+1, GLPKConstants.GLP_CV);//GLP_CV continuous variable;
                    GLPK.glp_set_col_bnds(lp, i+1, GLPKConstants.GLP_DB, minVal, maxVal);//double boundary
                }
            }

            // Row setup 
            GLPK.glp_add_rows(lp, constrainList.size()+10);

            int coumnNo=1;
            int rowNo=1;
            int previousneutaint_ID=-1;
            for(int i=0;i<constrainList.size();i++)
            {
                Vector constrainLine=(Vector)constrainList.get(i);
                // i.longname,n.shortname,analysis,ci.INGREDIENT_ID,cn.NUTRIENT_ID,COMPOUNDNUTRIENT_ID
                Double Value=(Double)constrainLine.get(2);
                int ingediant_ID=(Integer)constrainLine.get(3);
                int neutaint_ID=(Integer)constrainLine.get(4);
                int serialNo=(Integer)constrainLine.get(5);
                int COMPOUNDNUTRIENT_ID=(Integer)constrainLine.get(6);
                if(previousneutaint_ID!=neutaint_ID)
                {
                    if(previousneutaint_ID!=-1)
                    {
                            GLPK.glp_set_mat_row(lp, coumnNo-1, rowNo-1, ind, val);
                    }
                    previousneutaint_ID=neutaint_ID;
                    GLPK.glp_set_row_name(lp, coumnNo, ""+COMPOUNDNUTRIENT_ID);
                    boolean isenter=false;
                    for(int j=0;j<nutrientValueList.size() && isenter==false;j++)
                    {
                        //NUTRIENT_ID,COALESCE(MINVALUE ,-1),COALESCE(MAXVALUE,-1),COMPOUNDNUTRIENT_ID
                        Vector nutrientLine=(Vector)nutrientValueList.get(j);
                        int nutrient_ID=(Integer)nutrientLine.get(0);
                        Double minVal=(Double)nutrientLine.get(1)*getTotalProduceQty();
                        Double maxVal=(Double)nutrientLine.get(2)*getTotalProduceQty();
                        if(nutrient_ID==neutaint_ID)
                        {
                            if(minVal==0.00 && maxVal==0.00){
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_DB, minVal, maxVal+100000000);//double boundary
                            }
                            else if(minVal>0.00 && maxVal==0.00){
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_LO, minVal, 0);//double boundary
                            }
                            else if(maxVal-minVal<= 0.00000001){
                                    //System.out.println(" Name: "+neutaintName+" -- Min: "+minVal+"   ....Max:  "+maxVal);
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_FX, minVal, maxVal);//double boundary
                            }
                            else if(minVal>0.00 && maxVal>0.00){
                                            //  GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_FX, 16.147, 16.147);
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_DB, minVal, maxVal);//double boundary
                            }
                            else if(minVal==0.00 && maxVal>0.00){
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_DB, 0, maxVal);//double boundary
                            }
                            else{
                                GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_DB, minVal, maxVal);//double boundary
                            }
                            isenter=true;
                        }
                    }
                    if(!isenter){
                        GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_DB, 0, 100000000);//double boundary
                    }
                    coumnNo++;
                    rowNo=1;
                }
                GLPK.intArray_setitem(ind, rowNo, serialNo);
                GLPK.doubleArray_setitem(val, rowNo, Value);
                rowNo++;
            }
            GLPK.glp_set_mat_row(lp, (coumnNo-1), rowNo-1, ind, val);            

            //-------------------Produce component-------------------
            GLPK.glp_set_row_name(lp, coumnNo, "0000");//Demand
            GLPK.glp_set_row_bnds(lp, coumnNo, GLPKConstants.GLP_FX, getTotalProduceQty(), getTotalProduceQty());
            for(int j=0;j<ingrediantValueList.size();j++){
                GLPK.intArray_setitem(ind, j+1, j+1);
                GLPK.doubleArray_setitem(val, j+1, 1);
            }
            GLPK.glp_set_mat_row(lp, coumnNo, ingrediantValueList.size(), ind, val);


            // Free memory
            GLPK.delete_intArray(ind);
            GLPK.delete_doubleArray(val);

            //	Minimize z = -.5 * x1 + .5 * x2 - x3 + 1
            // Define objective
            GLPK.glp_set_obj_name(lp, "cost");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MIN);

            for(int j=0;j<ingrediantValueList.size();j++){
                Vector ingrediantPriceLine=(Vector)ingrediantValueList.get(j);
                Double value=(Double)ingrediantPriceLine.get(1);
                GLPK.glp_set_obj_coef(lp, j+1, value);
            }

            // Solve model
            parm = new glp_smcp();
            GLPK.glp_init_smcp(parm);
            ret = GLPK.glp_simplex(lp, parm);

            // Retrieve solution
            
            if (ret == 0) {
                status=sqlManager.write_lp_solution(lp,Compound_id);
            } else {
                JOptionPane.showMessageDialog(null, "The problem could not be solved", "Formulation Error", JOptionPane.ERROR_MESSAGE);
            }
            // Free memory
            GLPK.glp_delete_prob(lp);
            
            
        }
        catch (GlpkException ex) {
	    ret = 1;
            JOptionPane.showMessageDialog(null, "Solution Error. \n"+ex.getMessage(), "GlpkException Error", JOptionPane.ERROR_MESSAGE);
            GLPK.glp_delete_prob(lp);
        } catch (SQLException ex) {
            Logger.getLogger(Formulation.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Solution Error. \n"+ex.getMessage(), "SQLException Error", JOptionPane.ERROR_MESSAGE);
             GLPK.glp_delete_prob(lp);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Formulation.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Solution Error. \n"+ex.getMessage(), "UnknownHostException Error", JOptionPane.ERROR_MESSAGE);
             GLPK.glp_delete_prob(lp);
        }
        return status;
    }
    
    /**
     * write simplex solution
     * @param lp problem
     * 
     */
    
    static void write_lp_solution(glp_prob lp) {
        int i;
        int n;
        int c;
        String name;
        double val;

        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        
        System.out.print(name);
        System.out.print(" For Per Kg = ");
        System.out.println(val/getTotalProduceQty());
        c = GLPK.glp_get_num_rows(lp);
        n = GLPK.glp_get_num_cols(lp);
        for (i = 1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            if(name==null) continue;
            val = GLPK.glp_get_col_prim(lp, i);
            System.out.print(name);
            System.out.print(" = ");
            System.out.println(val);
        }
        System.out.println("");
        System.out.println(" ==========================");
        System.out.println("  ");
        for (i = 1; i <= c; i++) {
            name = GLPK.glp_get_row_name(lp, i);
            double lo = GLPK.glp_get_row_lb(lp, i);
            double prim = GLPK.glp_get_row_prim(lp, i);
            double ub = GLPK.glp_get_row_ub(lp, i);
            if(name==null) continue;
            //System.out.print(name);
            //System.out.print(" = ");
            //System.out.print("low: "+lo+" ");
            if(name.endsWith("Demand")){
            	System.out.println(" ==========================");
            	System.out.println(" ==========================");
            	System.out.print("Total Produce In Kg");
                System.out.print(" = ");
            	System.out.println(" value: "+prim);
            }
            else{
            	System.out.print(name);
                System.out.print(" = ");
            	System.out.println(" value: "+prim/getTotalProduceQty());
            	}
            //System.out.println(" High: "+ub);
        }
        
        
        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        
        System.out.print(name);
        System.out.print(" For Per Kg = ");
        System.out.println(val/getTotalProduceQty());
    }
    
    public static double getTotalProduceQty(){
		double produce_Total_Compound=100.00;
		return produce_Total_Compound;
	}
    
    
}
