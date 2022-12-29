
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bamshad
 */
@Stateless
public class Filewalker_old {
    List<String> myFinalList = new ArrayList<>();
    int i = -1;            
    public List<String> walk (String path, HttpServletResponse response) throws ServletException, IOException {
        try {         
            PrintWriter out = response.getWriter();
            out.println("Hello from Filewalk EJB?");
            File root = new File (path);
            File[] list = root.listFiles();
            if (list == null) return myFinalList;
            for (File f : list) {
                i++;
                if (f.isDirectory()) {
                    walk(f.getAbsolutePath(), response);
                    //out.println("<br>Dir: " + f.getAbsolutePath());
                    myFinalList.add(f.getAbsolutePath());
                } else {
                    //out.println("<br>File: " + f.getAbsolutePath());
                    myFinalList.add(f.getAbsolutePath());
                }
            }
            return myFinalList;
        } catch(Exception e) {  }
        return myFinalList;
    }

}
