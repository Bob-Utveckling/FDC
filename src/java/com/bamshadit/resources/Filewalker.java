package com.bamshadit.resources;


import com.bamshadit.check.in_1_folder.*;
import com.bamshadit.rest2.check_2_dir.*;
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
public class Filewalker {
    //List<String> myFinalList = new ArrayList<>();
    List<File> myFinalList = new ArrayList<>();
    
    //public List<String> walk (String path) {
    public List<File> walk (String path) {
        
        File root = new File (path);
        File[] list = root.listFiles();
        if (list == null) return myFinalList;
        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
                //myFinalList.add(f.getAbsolutePath());
                myFinalList.add(f);
            } else {
                //myFinalList.add(f.getAbsolutePath());
                myFinalList.add(f);
                
            }
        }
        return myFinalList;
    }

}
