/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.check.in_1_folder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Bamshad
 */
@Stateless
public class DuplicateCheckerInOneFolder {

    //List<String> myFinalList = new ArrayList<>();
    List<File> myFinalList = new ArrayList<>();

    //public List<String> walk (String path) {
    public List<File> getDuplicateFiles(String rootFolderToCheck, String fileNameToCheck) {
        System.out.println("full path of file: " + fileNameToCheck);
        int indexOfSlash = fileNameToCheck.lastIndexOf("/") == -1 ? fileNameToCheck.lastIndexOf("\\") : fileNameToCheck.lastIndexOf("/");
        System.out.println ("index of last slash: " + indexOfSlash);
        fileNameToCheck = fileNameToCheck.substring(indexOfSlash+1);
        System.out.println ("original file to check: " + fileNameToCheck);

        File root = new File(rootFolderToCheck);
        File[] listOfFilesInFolder = root.listFiles();
        if (listOfFilesInFolder == null) {
            return myFinalList;
        }
        for (File f : listOfFilesInFolder) {
            if (f.isDirectory()) {
                getDuplicateFiles(f.getAbsolutePath(), fileNameToCheck);
                //myFinalList.add(f.getAbsolutePath());
                //myFinalList.add(f);
                //can check directories here also
            } else if (f.isFile()) {
                //myFinalList.add(f.getAbsolutePath());
                /*
                Here we check duplicacy
                */
                System.out.println("file to check: " + f.getName());
                if (fileNameToCheck.equals(f.getName())) {
                //if (f.getName() == fileNameToCheck) {                    
                    System.out.println("IS DUPLICATE");
                    myFinalList.add(f);
                }
            }
        }
        return myFinalList;
    }

}
