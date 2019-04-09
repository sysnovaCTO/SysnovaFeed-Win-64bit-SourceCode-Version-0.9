package SysnovaFeed;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.glp_prob;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class sqlManager {
    
    static int AD_USER_ID=0;
    static String username="";
    static String password="";
    public static String getUserName(){
        return username;
    }
    public static int getUserID(){
        return AD_USER_ID;
    }

    public static boolean loginUser(String userName, String Pass) throws SQLException, UnknownHostException{
        
        username="";
        password="";
        AD_USER_ID=0;
        boolean isFound=false;
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            //JOptionPane.showMessageDialog(null, "connection", "Login Error", JOptionPane.ERROR_MESSAGE);
        
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " Select AD_USER_ID from AD_USER"
                    + " Where  isActive='Y' AND Name='"+userName
                    + "' and Password='"+Pass
                    + "'";

            
            pstmt = conn.prepareStatement(sql);
            try {
                    rs = pstmt.executeQuery();
                    if(rs.next()){	
                        isFound= true;
                        AD_USER_ID=rs.getInt(1);
                        username=userName;
                        password=Pass;
                        //System.getProperty("user.name");
                       // InetAddress.getLocalHost().getHostName();
                        //System.out.println(InetAddress.getLocalHost().getHostName());
                    }
                    rs.close();
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                    
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not Running. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        
        return isFound;
    }
    
    /*****************Neutrient Window**********************************
     * 
     * @return Unit
     * @throws SQLException
     * @throws UnknownHostException 
     */
    public static ArrayList getNutrientUnitComboodata() 
            throws SQLException, UnknownHostException{
        
        ArrayList unitList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " Select UNIT_ID ,SYMBOL From UNIT Where  ISACTIVE='Y' Order by SYMBOL";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1));
                        line.add(rs.getString(2));
                        unitList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                    
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return unitList;
    }
    public static ArrayList getNutrientPropertyComboodata() 
            throws SQLException, UnknownHostException{
        
        ArrayList unitList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " Select NUTRIENTPROPERTY_ID, NAME  from NUTRIENTPROPERTY Where ISACTIVE ='Y' Order by Name";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1));
                        line.add(rs.getString(2));
                        unitList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return unitList;
    }
    public static ArrayList getNutrientdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList nutrientList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " Select nu.CODE,SHORTNAME ,LONGNAME ,u.SYMBOL,u.UNIT_ID ,"+
                        " nup.name,nup.NUTRIENTPROPERTY_ID ,nu.ISACTIVE ,LAYER ,BROILER ,FISH ,OTHERE,nu.NUTRIENT_ID,user.name,use.name,"
                        + " nu.CREATED,nu.UPDATED from NUTRIENT nu" +
                        " join UNIT u on (u.UNIT_ID=nu.UNIT_ID)" +
                        " join  NUTRIENTPROPERTY nup on (nu.NUTRIENTPROPERTY_ID=nup.NUTRIENTPROPERTY_ID)"+
                        " Left join  AD_USER  user on (nu.CREATEDBY=user.AD_USER_ID)" +
                        " Left join  AD_USER  use on (nu.UPDATEDBY=use.AD_USER_ID)" +
                        " Order By nu.CODE,nup.name";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getString(1)); //0 code
                        line.add(rs.getString(2));//1 SHORTNAME
                        line.add(rs.getString(3));//2 LONGNAME
                        line.add(rs.getString(4));//3 unit SYMBOL
                        line.add(rs.getInt(5));//4 UNIT_ID
                        line.add(rs.getString(6));//5
                        line.add(rs.getInt(7));//6 NUTRIENTPROPERTY_ID
                        line.add(rs.getBoolean(8));//7 ISACTIVE
                        line.add(rs.getBoolean(9));//8 layerCheck
                        line.add(rs.getBoolean(10));//9 broilerCheck
                        line.add(rs.getBoolean(11));//10 fishCheck
                        line.add(rs.getBoolean(12));//11 otherCheck
                        line.add(rs.getInt(13));//12 ========NUTRIENT_ID previous
                        line.add(0);//13========1 new add 2update 0 for exits
                        line.add(rs.getString(14)); //14 createdby
                        line.add(rs.getString(15)); //15 updatedby
                        line.add(rs.getString(16)); //16 created
                        line.add(rs.getString(17)); //17 updated
                        nutrientList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return nutrientList;
    }
    
    public static boolean saveNutrientdata(ArrayList nutrientList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<nutrientList.size();i++)
                {
                    Vector tableline =(Vector) nutrientList.get(i);
                    String cod = (String) tableline.get(0);
                    String nam = (String) tableline.get(1);
                    String lngtxt = (String) tableline.get(2);
                    String unitval = (String) tableline.get(3);
                    int unitid=(int) tableline.get(4);
                    String propertyVal = (String) tableline.get(5);
                    int propertyid=(int) tableline.get(6);
                    Boolean act = (Boolean) tableline.get(7);
                    String isact="N";
                    if(act)isact="Y";
                    Boolean layer = (Boolean) tableline.get(8);
                    String layerCheck="N";
                    if(layer)layerCheck="Y";
                    Boolean broiler = (Boolean) tableline.get(9);
                    String broilerCheck="N";
                    if(broiler)broilerCheck="Y";
                    Boolean fish = (Boolean) tableline.get(10);
                    String fishCheck="N";
                    if(fish)fishCheck="Y";
                    Boolean other = (Boolean) tableline.get(11);
                    String otherCheck="N";
                    if(other)otherCheck="Y";
                    int editNutraint_ID=(int) tableline.get(12);
                    int status_ID=(int) tableline.get(13);
                    if(editNutraint_ID>=10000000){
                       String SQL = " INSERT INTO Nutrient"
                            + " (CREATEDBY ,CODE ,SHORTNAME ,LONGNAME ,ISACTIVE ,UPDATEDBY ,UNIT_ID ,"
                            + "NUTRIENTPROPERTY_ID ,BROILER ,LAYER ,FISH ,OTHERE  )"
                            + " Values( "+AD_USER_ID+",'"+cod+"','"+nam+"','"+lngtxt+"','"+isact+"',"+AD_USER_ID+","+unitid+
                               ","+propertyid+",'"+broilerCheck+"','"+layerCheck+"','"+fishCheck+"','"+otherCheck+"'"
                               + ")";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update Nutrient SET CODE ='"+cod+"',SHORTNAME='"+nam+"' ,LONGNAME='"+lngtxt+"' ,ISACTIVE='"+isact+"' ,"
                                     + " UPDATEDBY="+AD_USER_ID+" ,UNIT_ID="+unitid+" ,NUTRIENTPROPERTY_ID="+propertyid+" ,BROILER ='"+broilerCheck+"',LAYER='"+layerCheck+"' ,"
                                     + " FISH='"+fishCheck+"' ,OTHERE= '"+otherCheck+"',UPDATED='"+timestamp
                                     + "' Where NUTRIENT_ID="+editNutraint_ID;
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(nutrientList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 //System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    
    
    /**************************Ingredient SQL*************************************
    * 
    */

   public static ArrayList getIngredientGroupComboodata() 
               throws SQLException, UnknownHostException{

           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " Select IngredientGroup_ID ,Name From IngredientGroup Where ISACTIVE='Y' Order by Name";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));
                           line.add(rs.getString(2));
                           groupList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return groupList;
       }
   
   public static ArrayList getIngredienttypeComboodata() 
               throws SQLException, UnknownHostException{

           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " Select IngredientType_id ,Name From IngredientType Where ISACTIVE='Y' Order by Name";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));
                           line.add(rs.getString(2));
                           groupList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return groupList;
       }
   
   public static ArrayList getIngrediantdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList ingrediantList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT i.code,i.LONGNAME ,i.SCINTFICNAME ,ig.name,ig.INGREDIENTGROUP_ID,it.name,it.INGREDIENTTYPE_ID,"+
                        " i.isactive,use.name,i.UPDATED,i.INGREDIENT_ID  FROM INGREDIENT i" +
                        " Left JOIN INGREDIENTGROUP ig on (i.INGREDIENTGROUP_ID =ig.INGREDIENTGROUP_ID )\n" +
                        " Left JOIN INGREDIENTTYPE it on (i.INGREDIENTTYPE_ID =it.INGREDIENTTYPE_ID )\n" +
                        " Left join  AD_USER  use on (i.UPDATEDBY=use.AD_USER_ID)\n" +
                        " Order By ig.name, i.code";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getString(1)); //0 code
                        line.add(rs.getString(2));//1 LONGNAME
                        line.add(rs.getString(3));//2 ScientificName
                        line.add(rs.getString(4));//3 group Name
                        line.add(rs.getInt(5));//4 INGREDIENTGROUP_ID
                        line.add(rs.getString(6));//INGREDIENTTYPE Name
                        line.add(rs.getInt(7));//6 it.INGREDIENTTYPE_ID
                        line.add(rs.getBoolean(8));//7 ISACTIVE
                        line.add(rs.getString(9)); //15 updatedby
                        line.add(rs.getString(10)); //17 updated
                        line.add(rs.getInt(11));//INGREDIENT
                        ingrediantList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return ingrediantList;
    }
    public static int saveingredientdata(boolean isNew,String code,String name,String scintiText,
            int group_ID,int type_ID,boolean isActiveCheck,int INGREDIENT_ID) 
            throws SQLException, UnknownHostException{
        
        //int INGREDIENT_ID=0;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        int count=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){	
            String isact="N";
            if(isActiveCheck)isact="Y";
            try 
            {
                if(isNew){
                    
                    String SQL ="";
                    if(type_ID<1){
                       SQL = " INSERT INTO INGREDIENT"
                         + " (CREATEDBY ,CODE ,LONGNAME ,SCINTFICNAME ,ISACTIVE ,UPDATEDBY ,INGREDIENTGROUP_ID "
                         + " )"
                         + " Values( "+AD_USER_ID+",'"+code+"','"+name+"','"+scintiText+"','"+isact+"',"+AD_USER_ID+","+group_ID
                            + ")"; 
                    }
                    else{
                        SQL = " INSERT INTO INGREDIENT"
                         + " (CREATEDBY ,CODE ,LONGNAME ,SCINTFICNAME ,ISACTIVE ,UPDATEDBY ,INGREDIENTGROUP_ID ,"
                         + "INGREDIENTTYPE_ID  )"
                         + " Values( "+AD_USER_ID+",'"+code+"','"+name+"','"+scintiText+"','"+isact+"',"+AD_USER_ID+","+group_ID+
                            ","+type_ID
                            + ")";
                    }
                    
                    count=stmt.executeUpdate(SQL);
                    if(count>0){
                    conn.commit();
                    }
                    ResultSet rs=null;
                    PreparedStatement pstmt=null;		
                    String sql = 
                                " SELECT INGREDIENT_ID from INGREDIENT where CODE='"+code
                            + "' AND LONGNAME='"+name
                            + "'";

                    pstmt = conn.prepareStatement(sql);
                    try 
                    {
                        rs = pstmt.executeQuery();
                        while(rs.next()){	
                             INGREDIENT_ID=rs.getInt(1);
                        }
                        rs.close();
                        pstmt.close();

                    } catch (SQLException e) {
                         e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                    }
                    finally{
                        if(rs!=null) rs.close();
                        if(pstmt!=null) pstmt.close();
                    }
                    
                 }
                else{
                    String SQL1 ="";
                    if(type_ID>0){
                        SQL1 =" ,INGREDIENTTYPE_ID="+type_ID;
                    }
                    String SQL = "Update INGREDIENT SET CODE ='"+code+"',LONGNAME='"+name+"' ,SCINTFICNAME='"+scintiText+"' ,ISACTIVE='"+isact+"' ,"
                                 + " UPDATEDBY="+AD_USER_ID+" ,INGREDIENTGROUP_ID="+group_ID+SQL1
                                 + ",UPDATED='"+timestamp
                                 + "' Where INGREDIENT_ID="+INGREDIENT_ID;
                    int count1=stmt.executeUpdate(SQL);
                    if(count1>0){
                        conn.commit();
                    }
                }
               
                
                
                
            } catch (SQLException e) {
                INGREDIENT_ID=0;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return INGREDIENT_ID;
    }
    public static boolean  deleteIngredient(int INGREDIENT_ID) throws SQLException{
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                String SQL = " Delete from INGREDIENT where INGREDIENT_ID="+INGREDIENT_ID;
                stmt.executeUpdate(SQL); 
                conn.commit();
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Delete Not Possible. This INGREDIENT already used in Ingredient Analysis.", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    
    
    public static ArrayList getplantwisenutraintdata(int Ingredient_id) 
            throws SQLException, UnknownHostException{
        
        ArrayList plantwisenutraintdata=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " Select 1, n.Nutrient_ID,n.code,n.shortName,n.longName ,"
                    + " COALESCE(na.NutrientAnalysis_ID,-1) NutrientAnalysis,COALESCE(specification,-1) specification,"
                    + " COALESCE(overlay,-1) overlay,COALESCE(analysis,-1) analysis,np.name,u.symbol from Nutrient n " +
                        " left join NutrientAnalysis na ON(n.Nutrient_ID=na.Nutrient_ID "+
                        " and na.Ingredient_id="+Ingredient_id
                    + ") " +
                        " left join NUTRIENTPROPERTY  np ON(n.NUTRIENTPROPERTY_ID=np.NUTRIENTPROPERTY_ID ) " +
                        " left join UNIT  u ON(n.UNIT_ID=u.UNIT_ID )"+
                        " where  n.isactive='Y' Order by np.name";

            pstmt = conn.prepareStatement(sql);
            System.out.println(""+sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1)); //0 plant_ID
                        line.add(rs.getInt(2));//1 Nutrient_ID
                        line.add(rs.getString(3));//2 Nutrient code
                        line.add(rs.getString(4));//3 Nutrient shortName
                        line.add(rs.getString(5));//4 Nutrient longName
                        line.add(rs.getInt(6));//NutrientAnalysis_ID
                        line.add(rs.getDouble(7));//6 it.specification
                        line.add(rs.getDouble(8));//7 overlay
                        line.add(rs.getDouble(9)); //15 analysis
                        line.add(rs.getString(10));//3 Nutrient property
                        line.add(rs.getString(11));//4 Nutrient unit
                        plantwisenutraintdata.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return plantwisenutraintdata;
    }
    public static boolean saveplantwisenutraintdata(ArrayList nutrientListData,int Ingredient_ID) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<nutrientListData.size();i++)
                {
                    String SqlValue="";
                    Vector tableline =(Vector) nutrientListData.get(i);

                    int Nutrient_ID = (Integer) tableline.get(1);
                    int NutrientAnalysis_ID=(Integer) tableline.get(5);
                    //Double specification = (Double) tableline.get(6);
                    //Double overlay = (Double) tableline.get(7);
                    //Double analysis = (Double) tableline.get(8);
                    String Updatespecification=null;
                    String Updateoverlay=null;
                    String Updateanalysis=null;
                    if(tableline.get(6)!=null){//specification
                        Double specification = (Double) tableline.get(6);
                        if(specification==-1){
                            SqlValue=",null";
                            Updatespecification="null";
                        }
                        else{
                             SqlValue=","+specification;
                             Updatespecification=""+specification;
                        }
                    }
                    else{
                         SqlValue=",null";
                          Updatespecification="null";
                    }
                    if(tableline.get(7)!=null){//overlay
                        Double overlay = (Double) tableline.get(7);
                        if(overlay==-1){
                            SqlValue=SqlValue+",null";
                            Updateoverlay="null";
                        }
                        else{
                             SqlValue=SqlValue+","+overlay;
                              Updateoverlay=""+overlay;
                        }
                    }
                    else{
                         SqlValue=SqlValue+",null";
                          Updateoverlay="null";
                    }
                    if(tableline.get(8)!=null){//analysis
                        Double analysis = (Double) tableline.get(8);
                        if(analysis==-1){
                            SqlValue=SqlValue+",null";
                            Updateanalysis="null";
                        }
                        else{
                             SqlValue=SqlValue+","+analysis;
                             Updateanalysis=""+analysis;
                        }
                    }
                    else{
                         SqlValue=SqlValue+",null";
                         Updateanalysis="null";
                    }
                    if(NutrientAnalysis_ID<0){
                       String SQL = " INSERT INTO NUTRIENTANALYSIS"
                            + " ( createdby,isactive,updatedby,specification,overlay,analysis,"
                            + " Nutrient_id,Ingredient_id )"
                            + " Values( "+AD_USER_ID+",'Y',"+AD_USER_ID+SqlValue+
                               ","+Nutrient_ID+","+Ingredient_ID
                            + ")";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                        String SQL = "Update NUTRIENTANALYSIS SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,specification="+Updatespecification+" ,overlay="+Updateoverlay+" ,"
                                     + " analysis="+Updateanalysis+" ,UPDATED='"+timestamp
                                     + "' Where NUTRIENTANALYSIS_ID="+NutrientAnalysis_ID;
                        stmt.executeUpdate(SQL); 
                    }
                }
                if(nutrientListData.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    
    /**
     * PRICE WEEK Setup
     */
    public static ArrayList getPRICEWEEK() 
            throws SQLException, UnknownHostException{
        
        ArrayList weekList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT p.CODE ,p.DESCRIPTION ,p.ISACTIVE ,p.VALIDFROM ,p.VALIDTO ,use.name,p.UPDATED,p.PRICEWEEK_ID  FROM PRICEWEEK p"+
                        " Left join  AD_USER  use on (p.UPDATEDBY=use.AD_USER_ID)" +
                        " Order By p.code";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getString(1)); //0 code
                        line.add(rs.getString(2));//1 DESCRIPTION
                        line.add(rs.getBoolean(3));//2 ISACTIVE
                        line.add(rs.getString(4));//3 VALIDFROM
                        line.add(rs.getString(5));//4 VALIDTO
                        line.add(rs.getString(6)); //5 updatedby
                        line.add(rs.getString(7)); //6 updated
                        line.add(rs.getInt(8));//7 PRICEWEEK_ID
                        weekList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return weekList;
    }
    
    public static boolean  deletepriceweek(int PRICEWEEK_ID) throws SQLException{
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                String SQL = " Delete from PRICEWEEK where PRICEWEEK_ID="+PRICEWEEK_ID;
                stmt.executeUpdate(SQL); 
                conn.commit();
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Delete Not Possible. This PRICE WEEK already used in Ingredient Analysis.", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    
    public static int savePRICEWEEK(String code,String desc, String selectedDateleft,String selectedDateright,boolean isActiveCheck,int ID) 
            throws SQLException, UnknownHostException{
        
        //int INGREDIENT_ID=0;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        int count=0;
        int PRICEWEEK_ID=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){	
            String isact="N";
            if(isActiveCheck)isact="Y";
            try 
            {
                    String SQLappend ="";
                    String SQLUpdate =", validFrom=";
                    if(selectedDateleft!=null){
                        SQLappend=",'"+selectedDateleft+"'";
                        SQLUpdate=SQLUpdate+"'"+selectedDateleft+"'";
                    }
                    else{
                        SQLappend=",null";
                        SQLUpdate=SQLUpdate+"null";
                    }
                    SQLUpdate=SQLUpdate+", VALIDTO=";
                    if(selectedDateright!=null){
                        SQLappend=SQLappend+",'"+selectedDateright+"'";
                        SQLUpdate=SQLUpdate+"'"+selectedDateright+"'";
                    }
                    else{
                        SQLappend=SQLappend+",null";
                        SQLUpdate=SQLUpdate+"null";
                    }
                    String SQL ="";
                    
                            
                    
                    if(ID<1){
                      SQL = " INSERT INTO priceweek"
                         + " (CREATEDBY ,CODE ,description ,ISACTIVE ,UPDATEDBY ,validFrom,VALIDTO "
                         + " )"
                         + " Values( "+AD_USER_ID+",'"+code+"','"+desc+"','"+isact+"',"+AD_USER_ID+""+SQLappend
                            + ")";  
                    }
                    else{
                        SQL = "Update priceweek SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,CODE='"+code+"' ,description='"+desc+"' ,isactive='"+isact+"'"
                                     + SQLUpdate
                                     + " Where PRICEWEEK_ID="+ID;
                    }
                    count=stmt.executeUpdate(SQL);
                    if(count>0){
                        conn.commit();
                        PRICEWEEK_ID=ID;
                    }
                    
                    if(ID<1)
                    {
                        ResultSet rs=null;
                        PreparedStatement pstmt=null;		
                        String sql = 
                                    " SELECT PRICEWEEK_ID from PRICEWEEK where CODE='"+code
                                + "' AND description='"+desc
                                + "'";

                        pstmt = conn.prepareStatement(sql);
                        try 
                        {
                            rs = pstmt.executeQuery();
                            while(rs.next()){	
                                 PRICEWEEK_ID=rs.getInt(1);
                            }
                            rs.close();
                            pstmt.close();

                        } catch (SQLException e) {
                             e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                        }
                        finally{
                            if(rs!=null) rs.close();
                            if(pstmt!=null) pstmt.close();
                        }
                    }
                    
            }
            catch (SQLException e) {
                PRICEWEEK_ID=0;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return PRICEWEEK_ID;
    }
    
    /**
     * PriceList Details
     */
    public static ArrayList getPriceListDetails(int PRICEWEEK_ID) 
            throws SQLException, UnknownHostException{
        
        ArrayList weekList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT  i.INGREDIENT_ID,i.code, i.LONGNAME ,COALESCE(Price,-1) as PRICE,COALESCE(W.WEEKLYPRICE_ID,-1),COALESCE(PRICEWEEK_ID,-1) FROM INGREDIENT i " +
                        " LEFT Join WEEKLYPRICE W on (W.INGREDIENT_ID = i.INGREDIENT_ID AND PRICEWEEK_ID="+PRICEWEEK_ID+
                        ") where i.isactive='Y' ORDER BY i.code";
            System.err.println(""+sql);
            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1)); //0 INGREDIENT_ID
                        line.add(rs.getString(2));//1 INGREDIENT Code
                        line.add(rs.getString(3));//2 INGREDIENT LONGNAME
                        //line.add(rs.getInt(4));//3 PLANT_ID
                        //line.add(rs.getString(5));//4 PLANT Name
                        line.add(rs.getDouble(4)); //5 Price
                        line.add(rs.getInt(5)); //6 WEEKLYPRICE_ID
                        line.add(rs.getInt(6));//PRICEWEEK_ID
                        weekList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return weekList;
    }
    
    public static boolean saveplantwisePrice(ArrayList priceListData,int PRICEWEEK_ID) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<priceListData.size();i++)
                {
                    
                    String SqlValue="";
                    Vector tableline =(Vector) priceListData.get(i);

                    int INGREDIENT_ID = (Integer) tableline.get(0);
                    int WEEKLYPRICE_ID=(Integer) tableline.get(4);
                    Double price = (Double) tableline.get(3);
                    
                    if(WEEKLYPRICE_ID<0){
                       String SQL = " INSERT INTO weeklyPrice"
                            + " ( createdby,isactive,updatedby,Ingredient_id,price,priceweek_id )"
                            + " Values( "+AD_USER_ID+",'Y',"+AD_USER_ID+","+INGREDIENT_ID+","+price+
                               ","+PRICEWEEK_ID
                            + ")";
                        //System.out.println(""+SQL);
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                        
                        String SQL = "Update weeklyPrice SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,price="+price+" ,UPDATED='"+timestamp
                                     + "' Where WEEKLYPRICE_ID="+WEEKLYPRICE_ID;
                        stmt.executeUpdate(SQL); 

                    }
                }
                if(priceListData.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 //System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    
    /**
     * Compound Setup
     */
    public static ArrayList getCompoundList() 
            throws SQLException, UnknownHostException{
        
        ArrayList COMPOUNDList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT c.code,c.Name,c.description,c.isactive,c.updatedby,ct.CompoundType_id,ct.name,"
                        + "c.CompoundGroup_id,cg.name,use.name," +
                        " c.UPDATED,c.COMPOUND_ID,c.broiler,c.layer,c.Fish,c.othere,COALESCE(wp.priceweek_id,-1),wp.description,Total_formuated,cost,previouscost,SOLUTION_ID FROM COMPOUND c" +
                        " Left join  AD_USER  use on (c.UPDATEDBY=use.AD_USER_ID)" +
                        " Left join  priceweek  wp on (c.priceweek_id=wp.priceweek_id)" +
                        " join  CompoundGroup cg on (c.CompoundGroup_id=cg.CompoundGroup_ID)" +
                        " join  CompoundType ct on (c.CompoundType_id=ct.CompoundType_id) Order by c.code";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getString(1)); //0 code
                        line.add(rs.getString(2));//1 Name
                        line.add(rs.getString(3));//2 DESCRIPTION
                        line.add(rs.getBoolean(4));//3 isactive
                        line.add(rs.getString(5));//4 
                        line.add(rs.getInt(6)); //5 CompoundType_id
                        line.add(rs.getString(7)); //6 CompoundType name
                        line.add(rs.getInt(8));//7 CompoundGroup_id
                        line.add(rs.getString(9)); //8 CompoundGroup Name
                        //line.add(rs.getInt(10)); //9 Plant_id
                        //line.add(rs.getString(11)); //10 Plan Name
                        line.add(rs.getString(10)); //11 updated name
                        line.add(rs.getString(11)); //12 updated 
                        line.add(rs.getInt(12)); //13 CompoundType_id
                        line.add(rs.getBoolean(13)); //14 broiler
                        line.add(rs.getBoolean(14)); //15 layer
                        line.add(rs.getBoolean(15)); //16 Fish 
                        line.add(rs.getBoolean(16)); //17 othere 
                        line.add(rs.getInt(17));
                        line.add(rs.getString(18));
                        line.add(rs.getDouble(19));
                        line.add(rs.getDouble(20));
                        line.add(rs.getDouble(21));
                        line.add(rs.getInt(22));
                        
                        COMPOUNDList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return COMPOUNDList;
    }
    
    public static ArrayList getCompoundGroupComboodata() 
               throws SQLException, UnknownHostException{

           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = "Select CompoundGroup_id,Name From CompoundGroup Where ISACTIVE='Y' Order by Name";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));
                           line.add(rs.getString(2));
                           groupList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return groupList;
       }
    public static ArrayList getCompoundtypeComboodata() 
               throws SQLException, UnknownHostException{

           ArrayList typeList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = "Select CompoundType_id,Name From CompoundType Where ISACTIVE='Y' Order by Name";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));
                           line.add(rs.getString(2));
                           typeList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return typeList;
       }
    
    public static int saveCompound(int Compound_id,String code,String name,String desc, int CompoundGroup_ID,int CompoundType_ID, boolean isActiveCheck,boolean layerCheck1,boolean fishCheck1,boolean otherCheck1,boolean broilerCheck1) 
            throws SQLException, UnknownHostException{
        
        //int INGREDIENT_ID=0;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        int count=0;
        int Compound_ID=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){	
            String isact="N";
            if(isActiveCheck)isact="Y";
            try 
            {       
                    String layerCheck="N";
                    if(layerCheck1)layerCheck="Y";
                    String broilerCheck="N";
                    if(broilerCheck1)broilerCheck="Y";
                    String fishCheck="N";
                    if(fishCheck1)fishCheck="Y";
                    String otherCheck="N";
                    if(otherCheck1)otherCheck="Y";
                    String SQL="";
                    if(Compound_id<1){
                      SQL = " INSERT INTO Compound"
                         + " (CREATEDBY ,CODE ,name,description ,ISACTIVE ,UPDATEDBY ,CompoundGroup_id,CompoundType_id,"
                         + " BROILER ,LAYER ,FISH ,OTHERE )"
                         + " Values( "+AD_USER_ID+",'"+code+"','"+name+"','"+desc+"','"+isact+"',"+AD_USER_ID+","
                              +CompoundGroup_ID+","+CompoundType_ID+",'"+broilerCheck+"','"+layerCheck+"','"+fishCheck+"','"+otherCheck+"'"
                               + ")"; 
                    }
                    else{
                        
                        SQL = "Update Compound SET "
                                     + " UPDATEDBY="+AD_USER_ID+",CODE='"+code+"',name='"+name+"',description='"+desc+"' ,isactive='"+isact+"',"
                                     + "CompoundGroup_id="+CompoundGroup_ID+",CompoundType_id="+CompoundType_ID+", BROILER ='"+broilerCheck+"',LAYER='"+layerCheck+"' ,"
                                     + " FISH='"+fishCheck+"' ,OTHERE= '"+otherCheck+"'"
                                     + " Where Compound_id="+Compound_id;
                                
                    }
                    count=stmt.executeUpdate(SQL);
                    if(count>0){
                        conn.commit();
                    }
                    
                    if(Compound_id<1)
                    {
                        ResultSet rs=null;
                        PreparedStatement pstmt=null;		
                        String sql = 
                                    " SELECT Compound_ID from Compound where CODE='"+code
                                + "' AND name='"+name
                                + "'";

                        pstmt = conn.prepareStatement(sql);
                        try 
                        {
                            rs = pstmt.executeQuery();
                            while(rs.next()){	
                                 Compound_ID=rs.getInt(1);
                            }
                            rs.close();
                            pstmt.close();

                        } catch (SQLException e) {
                             e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
                        }
                        finally{
                            if(rs!=null) rs.close();
                            if(pstmt!=null) pstmt.close();
                        }
                    }
                    else{
                        Compound_ID=Compound_id;
                    }
                    
            }
            catch (SQLException e) {
                Compound_ID=0;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. ", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return Compound_ID;
    }
    
    public static boolean  deleteCompound(int Compound_id) throws SQLException{
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                String SQL = " Delete from Compound where Compound_id="+Compound_id;
                stmt.executeUpdate(SQL); 
                conn.commit();
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Delete Not Possible. This Compound already used in Formulation.", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    /**
     * Formulation
     * @return
     * @throws SQLException
     * @throws UnknownHostException 
     */
    public static ArrayList getIngrediantdata(ArrayList ingredientData) 
            throws SQLException, UnknownHostException{
        
        ArrayList ingrediantList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        String INGREDIENTList="";
        if(ingredientData!=null)
            if(ingredientData.size()>0){
                for(int i=0;i<ingredientData.size();i++)
                {  
                   Vector tableline =(Vector) ingredientData.get(i);
                   int INGREDIENT_ID=(int) tableline.get(0); 
                   if(INGREDIENTList.length()<1){
                       INGREDIENTList="Where i.Isactive='Y' AND i.INGREDIENT_ID NOT IN ( "+INGREDIENT_ID;
                   }
                   else{
                       INGREDIENTList=INGREDIENTList+", "+INGREDIENT_ID;
                   }
                }
                INGREDIENTList=INGREDIENTList+" )";
            }
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT i.INGREDIENT_ID,i.code,i.LONGNAME  FROM INGREDIENT i" +
                      " ";
            if(INGREDIENTList.length()>0){
                sql = sql+"  "+INGREDIENTList;
            }
            sql = sql+" Order By  i.code";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1)); //0 id
                        line.add(false);// select
                        line.add(rs.getString(2));//2 code
                        line.add(rs.getString(3));//3 LONGNAME
                        ingrediantList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return ingrediantList;
    }
    
    public static ArrayList getNutrientPopupData(ArrayList nutrientData, String whereClause) 
            throws SQLException, UnknownHostException{
        
        ArrayList ingrediantList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        String nutrient_IDList="";
        if(nutrientData!=null)
            if(nutrientData.size()>0){
                for(int i=0;i<nutrientData.size();i++)
                {  
                   Vector tableline =(Vector) nutrientData.get(i);
                   int nutrient_ID=(int) tableline.get(0); 
                   if(nutrient_IDList.length()<1){
                       nutrient_IDList="  i.Isactive='Y' AND i.NUTRIENT_ID NOT IN ( "+nutrient_ID;
                   }
                   else{
                       nutrient_IDList=nutrient_IDList+", "+nutrient_ID;
                   }
                }
                nutrient_IDList=nutrient_IDList+" )";
            }
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " SELECT i.NUTRIENT_ID,i.code,i.ShortNAME,u.symbol FROM NUTRIENT i "
                    + "join unit  u on (u.unit_ID=i.unit_ID) " +
                      " ";
            if(whereClause.length()>0){
                 sql = sql+" Where ( "+whereClause+" )";
                 if(nutrient_IDList.length()>0){
                    sql = sql+" AND "+nutrient_IDList;
            }
            }
            else if(nutrient_IDList.length()>0){
                sql = sql+"  where "+nutrient_IDList;
            }
            
            sql = sql+" Order By i.code";
            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1)); //0 id
                        line.add(false);// select
                        line.add(rs.getString(2));//2 code
                        line.add(rs.getString(3));//3 LONGNAME
                        line.add(rs.getString(4));//3 Unit
                        ingrediantList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return ingrediantList;
    }
    public static ArrayList getCompoundingredientdata(int Compound_id) 
               throws SQLException, UnknownHostException{

           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT i.INGREDIENT_ID,i.code,i.longname,COALESCE(price,-1),COALESCE(minvalue,-1),COALESCE(value,-1),COALESCE(maxvalue,-1),ci.COMPOUND_ID,ci.COMPOUNDINGREDIENT_ID FROM COMPOUNDINGREDIENT  ci " +
                            " join INGREDIENT  i on (ci.INGREDIENT_ID=i.INGREDIENT_ID) " +
                            " Where ci.COMPOUND_ID="+Compound_id;

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//INGREDIENT_ID
                           line.add(rs.getString(2));//INGREDIENT code
                           line.add(rs.getString(3));//INGREDIENT longname
                           line.add(rs.getDouble(4));// price
                           line.add(rs.getDouble(5));//minvalue
                           line.add(rs.getDouble(6));//value
                           line.add(rs.getDouble(7));//maxvalue
                           line.add(rs.getInt(8));//COMPOUND_ID
                           line.add(rs.getInt(9));//COMPOUNDINGREDIENT_ID
                           groupList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return groupList;
       }
    
    public static ArrayList getCompoundnutrientdata(int Compound_id) 
               throws SQLException, UnknownHostException{

           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT i.NUTRIENT_ID,i.code,i.shortname,u.symbol,COALESCE(minvalue,-1),COALESCE(value,-1),COALESCE(maxvalue,-1),ci.COMPOUND_ID,ci.CompoundNutrient_ID FROM CompoundNutrient  ci " +
                            " join NUTRIENT  i on (ci.NUTRIENT_ID=i.NUTRIENT_ID) " +
                            " join unit  u on (u.unit_ID=i.unit_ID)" +
                            " Where ci.COMPOUND_ID="+Compound_id+ ""
                       + " Order by i.code";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//NUTRIENT_ID
                           line.add(rs.getString(2));//code
                           line.add(rs.getString(3));//shortname
                           line.add(rs.getString(4));//symbol
                           line.add(rs.getDouble(5));//minvalue
                           line.add(rs.getDouble(6));//value
                           line.add(rs.getDouble(7));//maxvalue
                           line.add(rs.getInt(8));//COMPOUND_ID
                           line.add(rs.getInt(9));//CompoundNutrient_ID
                           groupList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return groupList;
       }
    
    public static boolean saveCompoundIngredient(ArrayList ingredientData, int Compound_id) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<ingredientData.size();i++)
                {
                          
                    String SqlValue="";
                    Vector tableline =(Vector) ingredientData.get(i);

                    int INGREDIENT_ID = (Integer) tableline.get(0);
                    Double price = null;
                    //if((Double) tableline.get(3)!=-1)
                        price=(Double) tableline.get(3);
                    Double minvalue = null;
                    //((Double) tableline.get(4)!=-1)
                        minvalue=(Double) tableline.get(4);
                    Double value = null;
                    //if((Double) tableline.get(5)!=-1)
                        value=(Double) tableline.get(5);
                    Double maxvalue = null;
                    //if((Double) tableline.get(6)!=-1)
                        maxvalue =(Double) tableline.get(6);
                    int COMPOUND_ID=(Integer) tableline.get(7);
                    int COMPOUNDINGREDIENT_ID=(Integer) tableline.get(8);
                    
                    if(COMPOUNDINGREDIENT_ID<0){
                       String SQL = " INSERT INTO CompoundIngredient"
                            + " ( createdby,isactive,updatedby,Ingredient_id,price,minvalue,value,maxvalue,Compound_id )"
                            + " Values( "+AD_USER_ID+",'Y',"+AD_USER_ID+","+INGREDIENT_ID+","+price+
                               ","+minvalue+","+value+","+maxvalue+","+Compound_id
                            + ")";
                        //System.out.println(""+SQL);
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                        
                        String SQL = "Update CompoundIngredient SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,price="+price+" ,UPDATED='"+timestamp
                                     +"' ,minvalue="+minvalue+" ,value="+value+" ,maxvalue="+maxvalue+" ,Compound_id="+Compound_id+" ,Ingredient_id="+INGREDIENT_ID
                                     + " Where CompoundIngredient_ID="+COMPOUNDINGREDIENT_ID;
                        stmt.executeUpdate(SQL); 
                    
                    }
                }
                if(ingredientData.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    public static boolean saveCompoundNutrient(ArrayList nutrientData, int Compound_id) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<nutrientData.size();i++)
                {
                          
                    String SqlValue="";
                    Vector tableline =(Vector) nutrientData.get(i);

                    int Nutrient_id = (Integer) tableline.get(0);

                    Double minvalue = null;
                    //((Double) tableline.get(4)!=-1)
                        minvalue=(Double) tableline.get(4);
                    Double value = null;
                    //if((Double) tableline.get(5)!=-1)
                        value=(Double) tableline.get(5);
                    Double maxvalue = null;
                    //if((Double) tableline.get(6)!=-1)
                        maxvalue =(Double) tableline.get(6);
                    int COMPOUND_ID=(Integer) tableline.get(7);
                    int CompoundNutrient_ID=(Integer) tableline.get(8);
                    
                    if(CompoundNutrient_ID<0){
                       String SQL = " INSERT INTO CompoundNutrient"
                            + " ( createdby,isactive,updatedby,Nutrient_id,minvalue,value,maxvalue,Compound_id )"
                            + " Values( "+AD_USER_ID+",'Y',"+AD_USER_ID+","+Nutrient_id+
                               ","+minvalue+","+value+","+maxvalue+","+Compound_id
                            + ")";
                        //System.out.println(""+SQL);
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                        
                        String SQL = "Update CompoundNutrient SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,UPDATED='"+timestamp
                                     +"' ,minvalue="+minvalue+" ,value="+value+" ,maxvalue="+maxvalue+" ,Compound_id="+Compound_id+" ,Nutrient_id="+Nutrient_id
                                     + " Where CompoundNutrient_ID="+CompoundNutrient_ID;
                        stmt.executeUpdate(SQL); 
                    
                    }
                }
                if(nutrientData.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    public static boolean  deleteCompoundingredient(ArrayList ingredientDeleteData) throws SQLException{
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<ingredientDeleteData.size();i++)
                {
                    int CompoundIngredient_ID = (Integer) ingredientDeleteData.get(i);
                    String SQL = " Delete from CompoundIngredient where CompoundIngredient_id="+CompoundIngredient_ID;
                    stmt.executeUpdate(SQL);
                    
                }
                conn.commit();
                
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Delete Not Possible. This Compound already used in Formulation.", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        return isSuccess;
    }
    public static boolean  deleteCompoundnutrient(ArrayList nutrientDeleteData) throws SQLException{
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<nutrientDeleteData.size();i++)
                {
                    int CompoundNutrient_ID = (Integer) nutrientDeleteData.get(i);
                    String SQL = " Delete from CompoundNutrient where CompoundNutrient_id="+CompoundNutrient_ID;
                    stmt.executeUpdate(SQL); 
                    
                }
                conn.commit();
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Delete Not Possible. This Compound already used in Formulation.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    public static ArrayList gepriceListPropertyComboodata() 
            throws SQLException, UnknownHostException{
        
        ArrayList priceweekList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " Select priceweek_ID, description  from priceweek Where ISACTIVE ='Y' Order by description";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                Vector line = new Vector();
                line.add(-1);
                line.add("");
                priceweekList.add(line);
                while(rs.next()){	
                        Vector line1 = new Vector();
                        line1.add(rs.getInt(1));
                        line1.add(rs.getString(2));
                        priceweekList.add(line1);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);
        }
        return priceweekList;
    }
    public static boolean updateCompoundpriceweek(int Compound_id,int priceweek_ID) 
            throws SQLException, UnknownHostException{
        
        boolean success=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        int count=0;
        int Compound_ID=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){	

            try 
            {       String SQL ="";
                    if(priceweek_ID<1){
                         SQL = "Update Compound SET "
                                     + " UPDATEDBY="+AD_USER_ID+",priceweek_id=null"
                                     + " Where Compound_id="+Compound_id;
                    }
                    else{
                      SQL = "Update Compound SET "
                                     + " UPDATEDBY="+AD_USER_ID+",priceweek_id="+priceweek_ID
                                     + " Where Compound_id="+Compound_id;  
                    }
                    
                    count=stmt.executeUpdate(SQL);
                    if(count>0){
                        conn.commit();
                    }
                    else{
                         success=false;
                    }
                    
            }
            catch (SQLException e) {
                success=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. ", "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return success;
    }
    public static HashMap getweekpriceata(int priceweek_ID) 
               throws SQLException, UnknownHostException{
            HashMap priceMap=new HashMap();
            priceMap.clear();
           ArrayList groupList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT INGREDIENT_ID,COALESCE(Price,-1)  FROM WEEKLYPRICE where priceweek_ID="+priceweek_ID;

               pstmt = conn.prepareStatement(sql);
               try 
               {
                    rs = pstmt.executeQuery();
                    while(rs.next()){	
                        if (!priceMap.containsKey(rs.getInt(1)))
                            priceMap.put(rs.getInt(1), rs.getDouble(2));
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return priceMap;
       }
    
    /**
     * Formulation
     * @param Compound_id
     * @return
     * @throws SQLException
     * @throws UnknownHostException 
     */
    public static ArrayList getCompoundingredientformulation(int Compound_id) 
               throws SQLException, UnknownHostException{

           ArrayList INGREDIENList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT INGREDIENT_ID ,COALESCE(PRICE,0.00),COALESCE(MINVALUE ,0.00) ,COALESCE(MAXVALUE,0.00),COMPOUNDINGREDIENT_ID  FROM COMPOUNDINGREDIENT " +
                            " where COMPOUND_ID="+Compound_id+
                            " order by INGREDIENT_ID";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//INGREDIENT_ID
                           line.add(rs.getDouble(2));//PRICE
                           line.add(rs.getDouble(3));//MINVALUE
                           line.add(rs.getDouble(4));//MAXVALUE
                           line.add(rs.getInt(5));//INGREDIENT_ID
                           INGREDIENList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return INGREDIENList;
       }
    public static ArrayList getCompoundNutrientformulation(int Compound_id) 
               throws SQLException, UnknownHostException{

           ArrayList NutrientList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT NUTRIENT_ID,COALESCE(MINVALUE ,0.00),COALESCE(MAXVALUE,0.00),COMPOUNDNUTRIENT_ID  FROM COMPOUNDNUTRIENT " +
                            " where COMPOUND_ID="+Compound_id+
                            " order by NUTRIENT_ID";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//INGREDIENT_ID
                           line.add(rs.getDouble(2));//MINVALUE
                           line.add(rs.getDouble(3));//MAXVALUE
                           line.add(rs.getInt(4));//COMPOUNDNUTRIENT_ID
                           NutrientList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return NutrientList;
       }
    
    public static ArrayList getConstrain(int Compound_id,HashMap ingredientMap) 
               throws SQLException, UnknownHostException{

           ArrayList constrainList=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT i.longname,n.shortname,analysis,ci.INGREDIENT_ID,cn.NUTRIENT_ID,COMPOUNDNUTRIENT_ID  FROM COMPOUND c" +
                            " join COMPOUNDINGREDIENT ci on(c.COMPOUND_ID=ci.COMPOUND_ID)" +
                            " join INGREDIENT  i on(i.INGREDIENT_ID=ci.INGREDIENT_ID)" +
                            " join COMPOUNDNUTRIENT  cn on(c.COMPOUND_ID=cn.COMPOUND_ID)" +
                            " join NUTRIENT  n on(n.NUTRIENT_ID=cn.NUTRIENT_ID)" +
                            " join NUTRIENTANALYSIS na on(cn.NUTRIENT_ID =na.NUTRIENT_ID and i.INGREDIENT_ID=na.INGREDIENT_ID )" +
                            " where c.COMPOUND_ID=" +Compound_id+
                            " order by cn.NUTRIENT_ID ,ci.INGREDIENT_ID";

               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector<Object> line = new Vector<Object>();
    				line.add(rs.getString(1)); //"Syabean Meal"
    				line.add(rs.getString(2)); // "VIT_C"
    				line.add(rs.getDouble(3)); //10
    				line.add(rs.getInt(4)); //10
    				line.add(rs.getInt(5)); //10
    				
    				
    				if(ingredientMap.containsKey(rs.getInt(4))){
                                    int serialNo=(Integer) ingredientMap.get(rs.getInt(4));
                                    line.add(serialNo); //10
    				}
    				else{
                                    JOptionPane.showMessageDialog(null, "Formulation Ingredients Serial Error", "Formulation Error", JOptionPane.ERROR_MESSAGE);
                                    break;
    				}
                                line.add(rs.getInt(6)); //10
    				constrainList.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return constrainList;
       }
    public static double getTotalProduceQty(){
		double produce_Total_Compound=100.00;
		return produce_Total_Compound;
    }
    public static boolean write_lp_solution(glp_prob lp,int COMPOUND_ID) throws SQLException, UnknownHostException{
        
        boolean status=true;
        int i;
        int n;
        int c;
        String name;
        double val;
        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        int solution_ID=GLPK.glp_get_prim_stat(lp);
        double cost=val/getTotalProduceQty();
        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        int count=0;
        int Compound_ID=0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        double demand=0.0;
        if(conn!=null){
            try 
            {   
                c = GLPK.glp_get_num_rows(lp);
                n = GLPK.glp_get_num_cols(lp);
                for (i = 1; i <= n; i++) {
                    name = GLPK.glp_get_col_name(lp, i);
                    if(name==null) continue;
                    val = GLPK.glp_get_col_prim(lp, i);
                    int CompoundIngredient_ID=Integer.parseInt(name);
                    BigDecimal value=(BigDecimal.valueOf(val));
                    value=value.setScale(3, RoundingMode.UP);
                    String SQL= "Update CompoundIngredient SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,UPDATED='"+timestamp
                                     +"' ,value="+value
                                     + " Where CompoundIngredient_ID="+CompoundIngredient_ID;
                    count=stmt.executeUpdate(SQL);
                }
                for (i = 1; i <= c; i++) 
                {
                    name = GLPK.glp_get_row_name(lp, i);
                    double lo = GLPK.glp_get_row_lb(lp, i);
                    double prim = GLPK.glp_get_row_prim(lp, i);
                    double ub = GLPK.glp_get_row_ub(lp, i);
                    if(name==null) continue;
                    else if(name.endsWith("0000")){
                        demand=prim;//Total Produce In Kg
                    }
                    else{
                        int CompoundNutrient_ID=Integer.parseInt(name);
                        val=prim/getTotalProduceQty();
                        BigDecimal value=(BigDecimal.valueOf(val));
                    
                        value=value.setScale(3, RoundingMode.UP);
                        
                        String SQL = "Update CompoundNutrient SET "
                                     + " UPDATEDBY="+AD_USER_ID+" ,UPDATED='"+timestamp
                                     +"' ,value="+value
                                     + " Where CompoundNutrient_ID="+CompoundNutrient_ID;
                        stmt.executeUpdate(SQL);
                        }
                }
                
                BigDecimal cost1=(BigDecimal.valueOf(cost));
                cost1=cost1.setScale(3, RoundingMode.UP);
                BigDecimal totDemand=(BigDecimal.valueOf(demand));
                totDemand=totDemand.setScale(3, RoundingMode.UP);
                
                String SQL1 = "Update COMPOUND set SOLUTION_ID="+solution_ID+",Total_formuated="+totDemand
                                + ", PREVIOUSCOST=COST ,cost="+cost1
                                + ", UPDATEDBY="+AD_USER_ID+" ,UPDATED='"+timestamp
                                + "' Where COMPOUND_ID="+COMPOUND_ID;
                stmt.executeUpdate(SQL1);
                conn.commit();   
            }
            catch (SQLException e) {
                status=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. \n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        return status;
    }
    
    public static ArrayList getNutrientAnalysisdata(int Compound_id,int NUTRIENT_ID, double nutrientvalue) 
               throws SQLException, UnknownHostException{

           ArrayList NutrientAnalysis=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = " SELECT n.NUTRIENT_ID,i.longname,COALESCE(ci.value,-1),COALESCE(analysis,-1), COALESCE(Round((ci.value*analysis)/100,3),-1) as val," +
                            " Case when analysis is not null then COALESCE(Round(((ci.value*analysis)/"+nutrientvalue
                       + "),3),-1)  else -1 end,i.code " +
                            " FROM COMPOUND c" +
                            " join COMPOUNDINGREDIENT ci on(c.COMPOUND_ID=ci.COMPOUND_ID)" +
                            " join INGREDIENT  i on(i.INGREDIENT_ID=ci.INGREDIENT_ID)" +
                            " join NUTRIENT  n on(n.NUTRIENT_ID="+NUTRIENT_ID
                       + ")" +
                            " join NUTRIENTANALYSIS na on(n.NUTRIENT_ID =na.NUTRIENT_ID and i.INGREDIENT_ID=na.INGREDIENT_ID )" +
                            " where c.COMPOUND_ID="+Compound_id
                       + " and n.NUTRIENT_ID="+NUTRIENT_ID
                       + " and ci.value!=0 order by ci.INGREDIENT_ID ";
               
               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//NUTRIENT_ID
                           line.add(rs.getString(2));//ingredient Name
                           line.add(rs.getDouble(3));//ingredient %
                           line.add(rs.getDouble(4));//analysis
                           line.add(rs.getDouble(5));//Value
                           line.add(rs.getDouble(6));//NUTRIENT%
                           line.add(rs.getString(7));//ingredient Name
                           NutrientAnalysis.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, ""+e, "SQL Error", JOptionPane.ERROR_MESSAGE);
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return NutrientAnalysis;
       }
    
    public static ArrayList getIngredientAnalysisdata(int Compound_id,int INGREDIENT_ID) 
               throws SQLException, UnknownHostException{

           ArrayList INGREDIENTAnalysis=new ArrayList();
           Connection conn = null;
           conn=h2JDBCConnection.h2JDBCConnection();
           if(conn!=null){
               ResultSet rs=null;
               PreparedStatement pstmt=null;		
               String sql = "  SELECT n.NUTRIENT_ID,n.code,n.SHORTNAME ,u.symbol,COALESCE(analysis,-1), COALESCE(Round((ci.value*analysis)/100,3),-1) as val  FROM COMPOUND c" +
                            " join COMPOUNDINGREDIENT  ci on(ci.INGREDIENT_ID="+INGREDIENT_ID
                       + " and c.COMPOUND_ID=ci.COMPOUND_ID) " +
                            " join COMPOUNDNUTRIENT  cn on(cn.COMPOUND_ID=c.COMPOUND_ID) " +
                            " join NUTRIENT  n on(n.NUTRIENT_ID=cn.NUTRIENT_ID)"
                            + "join unit  u on(n.unit_ID=u.unit_ID)" +
                            " join NUTRIENTANALYSIS na on(n.NUTRIENT_ID =na.NUTRIENT_ID and ci.INGREDIENT_ID=na.INGREDIENT_ID )" +
                            " where c.COMPOUND_ID="+Compound_id
                       + " and ci.INGREDIENT_ID="+INGREDIENT_ID
                       + "  order by n.NUTRIENT_ID ";

               System.out.println(""+sql);
               pstmt = conn.prepareStatement(sql);
               try 
               {
                   rs = pstmt.executeQuery();
                   while(rs.next()){	
                           Vector line = new Vector();
                           line.add(rs.getInt(1));//NUTRIENT_ID
                           line.add(rs.getString(2));//NUTRIENT code
                           line.add(rs.getString(3));//NUTRIENT Name
                           line.add(rs.getString(4));//Symbol Name
                           line.add(rs.getDouble(5));//analysis %
                           line.add(rs.getDouble(6));//Value
                           
                           INGREDIENTAnalysis.add(line);
                   }
                   rs.close();
                   pstmt.close();

               } catch (SQLException e) {
                    e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
               }
               finally{
                   if(rs!=null) rs.close();
                   if(pstmt!=null) pstmt.close();
               }
           }
           else{
               JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

           }

           return INGREDIENTAnalysis;
       }
    
    /*****************User Info Window**********************************
     * 
     * @return UserInfo
     * @throws SQLException
     * @throws UnknownHostException 
     */
    
    public static ArrayList getUser1data() 
            throws SQLException, UnknownHostException{
        
        ArrayList userList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql  = 
                        " Select a.NAME,a.PASSWORD ,a.ISACTIVE ,a.AD_USER_ID,user.NAME,use.NAME,a.CREATED,a.updated "
                        + "from AD_USER a "
                    + " Left join  AD_USER  user on (a.CREATEDBY=user.AD_USER_ID) " +
                      " Left join  AD_USER  use on (a.UPDATEDBY=use.AD_USER_ID)"; 


            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                        line.add(rs.getString(1));//0 ID
                        line.add(rs.getString(2));//1 Password
                        line.add(rs.getBoolean(3));//2 ISACTIVE
                        line.add(rs.getInt(4));//3 ========User_ID previous
                        line.add(0);//========4 new add 2update 0 for exits
                        line.add(rs.getString(5)); //5 created
                        line.add(rs.getString(6)); //6 CREATEDBY
                        line.add(rs.getString(7)); //7 updated
                        line.add(rs.getString(8));//8 updatedby
                        
                        
                        
                        userList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return userList;
    }
    
    public static boolean saveUserdata(ArrayList userList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<userList.size();i++)
                {
                    Vector tableline =(Vector) userList.get(i);
                    String id = (String) tableline.get(0);
                    String password = (String) tableline.get(1);
                    Boolean act = (Boolean) tableline.get(2);
                    String isact="N";
                    if(act)isact="Y";
                    int editUser_ID=(int) tableline.get(3);

                    int status_ID=(int) tableline.get(4);
                    if(editUser_ID>=10000000){
                       String SQL = " INSERT INTO AD_USER"
                            + " (CREATEDBY,NAME,PASSWORD,ISACTIVE,UPDATEDBY  )"
                            
                            + " Values( '"+AD_USER_ID+"','"+id+"','"+password+"','"+isact+"','"+AD_USER_ID
                               + "')";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update AD_USER SET NAME ='"+id+"',PASSWORD='"+password+"',ISACTIVE='"+isact+"',UPDATED='"+timestamp+"',UPDATEDBY='"+AD_USER_ID
                                     + "'Where AD_USER_ID="+editUser_ID+"";
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(userList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    static boolean saveUSerdata(ArrayList tableListData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*****************NUTRIENT PROPERTY Window**********************************
     * 
     * @return NUTRIENT PROPERTY
     * @throws SQLException
     * @throws UnknownHostException 
     */
    
     public static ArrayList getNuPropertydata() 
            throws SQLException, UnknownHostException{
        
        ArrayList propertylist=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql  = 
                        " Select a.NAME,a.ISACTIVE ,a.NUTRIENTPROPERTY_ID,user.NAME,use.NAME,a.CREATED,a.updated "
                        + "from NUTRIENTPROPERTY a "
                        + " Left join  AD_USER  user on (a.CREATEDBY=user.AD_USER_ID)" 
                        +  " Left join  AD_USER  use on (a.UPDATEDBY=use.AD_USER_ID)"; 


            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                        line.add(rs.getString(1));//0 NAME
                        line.add(rs.getBoolean(2));//1 ISACTIVE
                        line.add(rs.getInt(3));//2 ========CREATEDBY
                        line.add(0);//========3 new add 2update 0 for exits
                        line.add(rs.getString(4)); //4 UPDATEDBY
                        line.add(rs.getString(5)); //5 CREATED
                        line.add(rs.getString(6)); //6 updated
                        line.add(rs.getString(7)); //6 updated
                        propertylist.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return propertylist;
    }
    
    public static boolean saveNuPropertydata(ArrayList propertyList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<propertyList.size();i++)
                {
                    Vector tableline =(Vector) propertyList.get(i);
                    String name = (String) tableline.get(0);
                    Boolean act = (Boolean) tableline.get(1);
                    String isact="N";
                    if(act)isact="Y";
                    int editproperty_ID=(int) tableline.get(2);

                    int status_ID=(int) tableline.get(3);
                    if(editproperty_ID>=10000000){
                       String SQL = " INSERT INTO NUTRIENTPROPERTY"
                            + " (CREATEDBY,NAME,ISACTIVE,UPDATEDBY  )"
                            
                            + " Values( '"+AD_USER_ID+"','"+name+"','"+isact+"','"+AD_USER_ID
                               + "')";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update NUTRIENTPROPERTY SET NAME ='"+name+"',ISACTIVE='"+isact+"',UPDATED='"+timestamp+"',UPDATEDBY='"+AD_USER_ID
                                     + "'Where NUTRIENTPROPERTY_ID="+editproperty_ID+"";
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(propertyList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
          public static boolean deleteNuPropertyGroupdata(int editproperty_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
            Connection conn = null;
            PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();               
                 String SQL = " DELETE  FROM NUTRIENTPROPERTY"                           
                                 + " Where NUTRIENTPROPERTY_ID="+ editproperty_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
           
          catch(Exception e) {
                throw new IllegalStateException("Sorry,cannot",e);                
             }                                        
        return isSuccess;            
        }
    /*****************Ingredient PROPERTY Window**********************************
     * 
     * @return Ingredient PROPERTY
     * @throws SQLException
     * @throws UnknownHostException 
     */
    
     public static ArrayList getIngredientGrpdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList propertylist=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql  = 
                        " Select a.NAME,a.ISACTIVE ,a.INGREDIENTGROUP_ID,user.NAME,use.NAME,a.CREATED,a.updated "
                        + " from INGREDIENTGROUP a "
                        + " Left join  AD_USER  user on (a.CREATEDBY=user.AD_USER_ID)" 
                        + " Left join  AD_USER  use on (a.UPDATEDBY=use.AD_USER_ID)"; 


            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                        line.add(rs.getString(1));//0 NAME
                        line.add(rs.getBoolean(2));//1 ISACTIVE
                        line.add(rs.getInt(3));//2 ========CREATEDBY
                        line.add(0);//========3 new add 2update 0 for exits
                        line.add(rs.getString(4)); //4 UPDATEDBY
                        line.add(rs.getString(5)); //5 CREATED
                        line.add(rs.getString(6)); //6 updated
                        line.add(rs.getString(7)); //6 updated
                        propertylist.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return propertylist;
    }
       
    public static boolean saveIngredientGroupdata(ArrayList ingredientList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<ingredientList.size();i++)
                {
                    Vector tableline =(Vector) ingredientList.get(i);
                    String name = (String) tableline.get(0);
                    Boolean act = (Boolean) tableline.get(1);
                    String isact="N";
                    if(act)isact="Y";
                    int editingredient_ID=(int) tableline.get(2);

                    int status_ID=(int) tableline.get(3);
                    if(editingredient_ID>=10000000){
                       String SQL = " INSERT INTO INGREDIENTGROUP"
                            + " (CREATEDBY,NAME,ISACTIVE,UPDATEDBY  )"
                            
                            + " Values( '"+AD_USER_ID+"','"+name+"','"+isact+"','"+AD_USER_ID
                               + "')";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update INGREDIENTGROUP SET NAME ='"+name+"',ISACTIVE='"+isact+"',UPDATED='"+timestamp+"',UPDATEDBY='"+AD_USER_ID
                                     + "'Where INGREDIENTGROUP_ID="+editingredient_ID+"";
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(ingredientList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
     public static boolean deleteGradientGroupdata(int editingredient_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
            Connection conn = null;
            PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();
               
                String SQL = " DELETE  FROM INGREDIENTGROUP"
                            
                                 + " Where INGREDIENTGROUP_ID="+ editingredient_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  //pstmt.setString(1,editUnit_ID );
                
               
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
           
          catch(Exception e) {
                throw new IllegalStateException("Sorry,cannot",e);                
             }                                        
        return isSuccess;            
        }
     
    /*****************Unit Window**********************************
     * 
     * @return Unit
     * @throws SQLException
     * @throws UnknownHostException 
     */

    public static ArrayList getUnitdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList unitList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " Select u.SYMBOL,u.NAME,u.ISACTIVE,u.UNIT_ID,u.CREATED,u.UPDATED,user.name,use.name,u.CREATEDBY,u.UPDATEDBY from UNIT u "+
                       
                        " Left join  AD_USER  user on (u.CREATEDBY=user.AD_USER_ID)" +
                        " Left join  AD_USER  use on (u.UPDATEDBY=use.AD_USER_ID)" +
                        
                        " Order By SYMBOL,NAME";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                        line.add(rs.getString(1));//1 NAME
                       
                        line.add(rs.getString(2));//2 SYMBOL
                        line.add(rs.getBoolean(3));//7 ISACTIVE
                        line.add(rs.getInt(4));//4 ========NUTRIENT_ID previous
                        line.add(0);//0========1 new add 2update 0 for exits
                        line.add(rs.getString(5)); //5 createdby
                        line.add(rs.getString(6)); //6 updatedby
                        line.add(rs.getString(7)); //7 created
                        line.add(rs.getString(8));// 8 updated
                        
                        
                        
                        unitList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
    
        return unitList;
    }
    
    public static boolean saveUnitdata(ArrayList unitList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<unitList.size();i++)
                {
                    Vector tableline =(Vector) unitList.get(i);
                    
                    String symbol = (String) tableline.get(0);
                    String nam = (String) tableline.get(1);
                   
                   
                    Boolean act = (Boolean) tableline.get(2);
                    String isact="N";
                    if(act)isact="Y";
                    int editUnit_ID=(int) tableline.get(3);


                    int status_ID=(int) tableline.get(4);
                    if(editUnit_ID>=10000000){
                       String SQL = " INSERT INTO UNIT"
                            + " (SYMBOL,NAME,ISACTIVE,CREATEDBY,UPDATEDBY  )"
                            
                            + " Values( '"+symbol+"','"+nam+"','"+isact+"',"+AD_USER_ID+","+AD_USER_ID
                               + ")";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update UNIT SET SYMBOL ='"+symbol+"',NAME='"+nam+"'"+",ISACTIVE='"+isact+"'"+",UPDATED='"+timestamp+"'"+",UPDATEDBY="+AD_USER_ID+""
                                    
                                     + " Where UNIT_ID="+editUnit_ID;
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(unitList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    



public static boolean deleteUnitdata(int Unit_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
        


                 
                  Connection conn = null;
                  PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();
               
                String SQL = " DELETE  FROM UNIT"
                            
                                 + " Where UNIT_ID="+ Unit_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  //pstmt.setString(1,editUnit_ID );
                
               
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
            catch(Exception e) {
                throw new IllegalStateException("cannot",e);
                                 }      
                                  
        return isSuccess;
            
    }

/*****************Compoundgroup Window**********************************
     * 
     * @return Unit
     * @throws SQLException
     * @throws UnknownHostException 
     */

    public static ArrayList getCompoundgroupdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList plantList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " Select cg.NAME,cg.ISACTIVE,cg.COMPOUNDGROUP_ID,cg.CREATED,cg.UPDATED,user.name,use.name,cg.CREATEDBY,cg.UPDATEDBY from COMPOUNDGROUP cg "+
                       
                        " Left join  AD_USER  user on (cg.CREATEDBY=user.AD_USER_ID)" +
                        " Left join  AD_USER  use on (cg.UPDATEDBY=use.AD_USER_ID)" +
                        
                        " Order By NAME";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                       // line.add(rs.getString(1));//1 NAME
                       
                        line.add(rs.getString(1));//2 SYMBOL
                        line.add(rs.getBoolean(2));//7 ISACTIVE
                        line.add(rs.getInt(3));//4 ========NUTRIENT_ID previous
                        line.add(0);//0========1 new add 2update 0 for exits
                        line.add(rs.getString(4)); //5 createdby
                        line.add(rs.getString(5)); //6 updatedby
                        line.add(rs.getString(6)); //7 created
                        line.add(rs.getString(7));// 8 updated
                        
                        
                        
                        plantList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
    
        return plantList;
    }
    
    public static boolean saveCompoundgroupData(ArrayList CompoundgroupList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<CompoundgroupList.size();i++)
                {
                    Vector tableline =(Vector) CompoundgroupList.get(i);
                    
                   // String symbol = (String) tableline.get(0);
                    String nam = (String) tableline.get(0);//1
                   
                   
                    Boolean act = (Boolean) tableline.get(1);//2
                    String isact="N";
                    if(act)isact="Y";
                    int editCompoundgroup_ID=(int) tableline.get(2);//3


                    int status_ID=(int) tableline.get(3);//4
                    if(editCompoundgroup_ID>=10000000){
                       String SQL = " INSERT INTO COMPOUNDGROUP "
                            + " (NAME,ISACTIVE,CREATEDBY,UPDATEDBY)"
                            
                            + " Values('"+nam+"','"+isact+"',"+AD_USER_ID+","+AD_USER_ID
                               + ")";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update COMPOUNDGROUP SET name='"+nam+"'"+",ISACTIVE='"+isact+"'"+",UPDATED='"+timestamp+"'"+",UPDATEDBY="+AD_USER_ID+""
                                    
                                     + " Where COMPOUNDGROUP_ID="+editCompoundgroup_ID;
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(CompoundgroupList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    



public static boolean deleteCompoundgroupdata(int Compoundgroup_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
            Connection conn = null;
            PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();
               
                String SQL = " DELETE  FROM COMPOUNDGROUP"
                            
                                 + " Where COMPOUNDGROUP_ID="+ Compoundgroup_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  //pstmt.setString(1,editUnit_ID );
                
               
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
           
          catch(Exception e) {
                throw new IllegalStateException("cannot",e);
            }      
                                  
        return isSuccess;
            
    }

/*****************Unit Window**********************************
     * 
     * @return Compoundtype
     * @throws SQLException
     * @throws UnknownHostException 
     */

    public static ArrayList getCompoundType() 
            throws SQLException, UnknownHostException{
        
        ArrayList plantList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = 
                        " Select ct.NAME,ct.ISACTIVE,ct.COMPOUNDTYPE_ID,ct.CREATED,ct.UPDATED,user.name,use.name,ct.CREATEDBY,ct.UPDATEDBY from COMPOUNDTYPE ct "+
                       
                        " Left join  AD_USER  user on (ct.CREATEDBY=user.AD_USER_ID)" +
                        " Left join  AD_USER  use on (ct.UPDATEDBY=use.AD_USER_ID)" +
                        
                        " Order By NAME";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                       // line.add(rs.getString(1));//1 NAME
                       
                        line.add(rs.getString(1));//2 SYMBOL
                        line.add(rs.getBoolean(2));//7 ISACTIVE
                        line.add(rs.getInt(3));//4 ========NUTRIENT_ID previous
                        line.add(0);//0========1 new add 2update 0 for exits
                        line.add(rs.getString(4)); //5 createdby
                        line.add(rs.getString(5)); //6 updatedby
                        line.add(rs.getString(6)); //7 created
                        line.add(rs.getString(7));// 8 updated
                        
                        
                        
                        plantList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
    
        return plantList;
    }
    
    public static boolean saveCompoundtypeData(ArrayList CompoundtypeList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<CompoundtypeList.size();i++)
                {
                    Vector tableline =(Vector) CompoundtypeList.get(i);
                    
                   // String symbol = (String) tableline.get(0);
                    String nam = (String) tableline.get(0);//1
                   
                   
                    Boolean act = (Boolean) tableline.get(1);//2
                    String isact="N";
                    if(act)isact="Y";
                    int editCompoundtype_ID=(int) tableline.get(2);//3


                    int status_ID=(int) tableline.get(3);//4
                    if(editCompoundtype_ID>=10000000){
                       String SQL = " INSERT INTO COMPOUNDTYPE "
                            + " (NAME,ISACTIVE,CREATEDBY,UPDATEDBY)"
                            
                            + " Values('"+nam+"','"+isact+"',"+AD_USER_ID+","+AD_USER_ID
                               + ")";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update PLANT SET COMPOUNDTYPE='"+nam+"'"+",ISACTIVE='"+isact+"'"+",UPDATED='"+timestamp+"'"+",UPDATEDBY="+AD_USER_ID+""
                                    
                                     + " Where COMPOUNDTYPE_ID="+editCompoundtype_ID;
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(CompoundtypeList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
    



public static boolean deleteCompoundtypedata(int Compoundtype_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
            Connection conn = null;
            PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();
               
                String SQL = " DELETE  FROM COMPOUNDTYPE"
                            
                                 + " Where COMPOUNDTYPE_ID="+ Compoundtype_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  //pstmt.setString(1,editUnit_ID );
                
               
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
           
          catch(Exception e) {
                throw new IllegalStateException("cannot",e);
                                 }      
                                  
        return isSuccess;
            
    }

/*****************Ingredient Type Window**********************************
     * 
     * @return Ingredient Type
     * @throws SQLException
     * @throws UnknownHostException 
     */
    
     public static ArrayList getIngredientTypedata() 
            throws SQLException, UnknownHostException{
        
        ArrayList IngredinetTypelist=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql  = 
                        " Select a.NAME,a.ISACTIVE ,a.INGREDIENTTYPE_ID,user.NAME,use.NAME,a.CREATED,a.updated "
                        + " from INGREDIENTTYPE a "
                        + " Left join  AD_USER  user on (a.CREATEDBY=user.AD_USER_ID)" 
                        + " Left join  AD_USER  use on (a.UPDATEDBY=use.AD_USER_ID)"; 


            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        
                        line.add(rs.getString(1));//0 NAME
                        line.add(rs.getBoolean(2));//1 ISACTIVE
                        line.add(rs.getInt(3));//2 ========CREATEDBY
                        line.add(0);//========3 new add 2update 0 for exits
                        line.add(rs.getString(4)); //4 UPDATEDBY
                        line.add(rs.getString(5)); //5 CREATED
                        line.add(rs.getString(6)); //6 updated
                        line.add(rs.getString(7)); //6 updated
                        IngredinetTypelist.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return IngredinetTypelist;
    }
       
    public static boolean saveIngredientTypedata(ArrayList ingredientTypeList) 
            throws SQLException, UnknownHostException{
        
        boolean isSuccess=true;
        Connection conn = null;
        Statement stmt = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        conn.setAutoCommit(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        stmt = conn.createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE);
        if(conn!=null){		
            try 
            {
                for(int i=0;i<ingredientTypeList.size();i++)
                {
                    Vector tableline =(Vector) ingredientTypeList.get(i);
                    String name = (String) tableline.get(0);
                    Boolean act = (Boolean) tableline.get(1);
                    String isact="N";
                    if(act)isact="Y";
                    int editingredientType_ID=(int) tableline.get(2);

                    int status_ID=(int) tableline.get(3);
                    if(editingredientType_ID>=10000000){
                       String SQL = " INSERT INTO INGREDIENTTYPE"
                            + " (CREATEDBY,NAME,ISACTIVE,UPDATEDBY  )"
                            
                            + " Values( '"+AD_USER_ID+"','"+name+"','"+isact+"','"+AD_USER_ID
                               + "')";
                        stmt.executeUpdate(SQL); 
                    }
                    else{
                         if(status_ID>0){
                             String SQL = "Update INGREDIENTTYPE SET NAME ='"+name+"',ISACTIVE='"+isact+"',UPDATED='"+timestamp+"',UPDATEDBY='"+AD_USER_ID
                                     + "'Where INGREDIENTTYPE_ID="+editingredientType_ID+"";
                            stmt.executeUpdate(SQL); 
                         }
                    }
                }
                if(ingredientTypeList.size()>0){
                    conn.commit();
                }
            } catch (SQLException e) {
                isSuccess=false;
                try{
                    if(conn!=null)
                        conn.rollback();
                    }catch(SQLException se2){
                        se2.printStackTrace();
                    }
                 System.out.println(""+e);
                    JOptionPane.showMessageDialog(null, "Save Error. "+e, "Error", JOptionPane.ERROR_MESSAGE);

            }
            finally
            {
                //finally block used to close resources
                try{
                   if(stmt!=null)
                      stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
            }//end try
            
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return isSuccess;
    }
     public static boolean deleteGradientTypedata(int editingredientType_ID ) 
            throws SQLException, UnknownHostException{        

    
            boolean isSuccess=true;
            Connection conn = null;
            PreparedStatement pstmt=null;
                  

          try {
                 
                 conn=h2JDBCConnection.h2JDBCConnection();
               
                String SQL = " DELETE  FROM INGREDIENTTYPE"
                            
                                 + " Where INGREDIENTTYPE_ID="+ editingredientType_ID  ;
                  pstmt = conn.prepareStatement(SQL);
                  //pstmt.setString(1,editUnit_ID );
                
               
                  pstmt.execute();
                  JOptionPane.showMessageDialog(null,"Deleted" );

                  } 
           
          catch(Exception e) {
                throw new IllegalStateException("Sorry,cannot",e);                
             }                                        
        return isSuccess;            
        }
/**
 * Compound Report
 * @return
 * @throws SQLException
 * @throws UnknownHostException 
 */

public static ArrayList getCompoundReportdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList reportdataList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " SELECT COMPOUND_ID,case when Description is not null then ('('||CODE||') '||NAME||'-'||Description) else ('('||CODE||') '||NAME) end ass"
                    + " FROM COMPOUND WHERE solution_ID=2";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1));
                        line.add(rs.getString(2));
                        reportdataList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return reportdataList;
    }

public static ArrayList getingredientRepdata() 
            throws SQLException, UnknownHostException{
        
        ArrayList reportdataList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " SELECT INGREDIENT_ID,'('||CODE||') '||LongNAME"
                    + " FROM INGREDIENT WHERE Isactive='Y' Order by CODE";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1));
                        line.add(rs.getString(2));
                        reportdataList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return reportdataList;
    }
/**
 * Compound Deshboard
 * @return
 * @throws SQLException
 * @throws UnknownHostException 
 */

public static ArrayList getTotaldata() 
            throws SQLException, UnknownHostException{
        
        ArrayList reportdataList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " SELECT com,week,nu,ing" +
                        " FROM " +
                        " ( " +
                        " SELECT cast (count(*) as int) as com FROM COMPOUND" +
                        " ) " +
                        " JOIN" +
                        " (" +
                        " SELECT cast (count(*) as int) as week FROM PRICEWEEK" +
                        " )ON (1=1)" +
                        " JOIN" +
                        " (" +
                        " SELECT cast (count(*) as int) as ing FROM INGREDIENT" +
                        " )ON (1=1)" +
                        " JOIN" +
                        " (" +
                        " SELECT cast (count(*) as int) as nu FROM NUTRIENT" +
                        " ) ON (1=1)";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getInt(1));
                        line.add(rs.getInt(2));
                        line.add(rs.getInt(3));
                        line.add(rs.getInt(4));
                        reportdataList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        return reportdataList;
    }

public static ArrayList getpaidata() 
            throws SQLException, UnknownHostException{
        
        ArrayList reportdataList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = " SELECT b,l,f,o " +
                        " FROM " +
                        " ( " +
                        " SELECT COALESCE(Avg(cost),0) as b FROM COMPOUND where solution_ID=2 and Broiler='Y' " +
                        " ) " +
                        " JOIN" +
                        " (" +
                        " SELECT COALESCE(Avg(cost),0) as l FROM COMPOUND where solution_ID=2 and Layer='Y' " +
                        " )ON (1=1)" +
                        " JOIN" +
                        " (" +
                        " SELECT COALESCE(Avg(cost),0) as f FROM COMPOUND where solution_ID=2 and Fish='Y' " +
                        " )ON (1=1)" +
                        " JOIN" +
                        " (" +
                        " SELECT COALESCE(Avg(cost),0) as o FROM COMPOUND where solution_ID=2 and Othere='Y' " +
                        " ) ON (1=1)";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getDouble(1));
                        line.add(rs.getDouble(2));
                        line.add(rs.getDouble(3));
                        line.add(rs.getDouble(4));
                        reportdataList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        return reportdataList;
    }

public static ArrayList getbarchardata(String NutrientName) 
            throws SQLException, UnknownHostException{
        
        ArrayList reportdataList=new ArrayList();
        Connection conn = null;
        conn=h2JDBCConnection.h2JDBCConnection();
        if(conn!=null){
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = "  SELECT b,l,f,o " +
                            " FROM " +
                            " ( " +
                            " SELECT COALESCE(Avg(value),0) as b FROM COMPOUNDNUTRIENT c " +
                            " join NUTRIENT n on (c.NUTRIENT_ID=n.NUTRIENT_ID) " +
                            " join COMPOUND  co on (co.COMPOUND_ID=c.COMPOUND_ID) " +
                            "  where solution_ID=2 and co.Broiler='Y' and n.Shortname='"+NutrientName
                    + "' " +
                            " )  " +
                            " JOIN " +
                            " ( " +
                            " SELECT COALESCE(Avg(value),0) as l FROM COMPOUNDNUTRIENT c " +
                            " join NUTRIENT n on (c.NUTRIENT_ID=n.NUTRIENT_ID) " +
                            " join COMPOUND  co on (co.COMPOUND_ID=c.COMPOUND_ID) " +
                            " where solution_ID=2 and co.Layer='Y' and n.Shortname='"+NutrientName
                    + "' " +
                            " )ON (1=1) " +
                            " JOIN " +
                            " ( " +
                            " SELECT COALESCE(Avg(value),0) as f FROM COMPOUNDNUTRIENT c " +
                            " join NUTRIENT n on (c.NUTRIENT_ID=n.NUTRIENT_ID) " +
                            " join COMPOUND  co on (co.COMPOUND_ID=c.COMPOUND_ID) " +
                            " where solution_ID=2 and co.fish='Y' and n.Shortname='"+NutrientName
                    + "' " +
                            " )ON (1=1)" +
                            " JOIN " +
                            " ( " +
                            " SELECT COALESCE(Avg(value),0) as o FROM COMPOUNDNUTRIENT c " +
                            " join NUTRIENT n on (c.NUTRIENT_ID=n.NUTRIENT_ID) " +
                            " join COMPOUND  co on (co.COMPOUND_ID=c.COMPOUND_ID) " +
                            " where solution_ID=2 and co.Othere='Y' and n.Shortname='"+NutrientName
                    + "' " +
                            ") ON (1=1)";

            pstmt = conn.prepareStatement(sql);
            try 
            {
                rs = pstmt.executeQuery();
                while(rs.next()){	
                        Vector line = new Vector();
                        line.add(rs.getDouble(1));
                        line.add(rs.getDouble(2));
                        line.add(rs.getDouble(3));
                        line.add(rs.getDouble(4));
                        reportdataList.add(line);
                }
                rs.close();
                pstmt.close();
		
            } catch (SQLException e) {
                                        e.printStackTrace();JOptionPane.showMessageDialog(null, ""+e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "h2 Database Server Not connected. please Run Database manually", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
        return reportdataList;
    }

}


