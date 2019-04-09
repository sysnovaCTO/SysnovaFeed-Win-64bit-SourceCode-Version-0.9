/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysnovaFeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class FeedFormula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//      
				
		ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "GLPSOL");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            if (true) {
                line = r.readLine();
                //if (line == null) { break; }
                //System.out.println(line);
            }
            if(line.equals("GLPSOL: GLPK LP/MIP Solver, v4.63")){
                logIn login = new logIn();
                //login.setLocation(50, 100);
                //login.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Please install GLPK first!!!");
            }

		
        
        //logIn login = new logIn();
        //login.setLocation(50, 100);
        //login.setVisible(true);
        
    }
    
}
