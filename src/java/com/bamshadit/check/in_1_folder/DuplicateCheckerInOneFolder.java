/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.check.in_1_folder;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import org.apache.commons.codec.digest.DigestUtils;
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
        System.out.println ("original file name to check: " + fileNameToCheck);

        File root = new File(rootFolderToCheck);
        File[] listOfFilesInFolder = root.listFiles();
        if (listOfFilesInFolder == null) {
            return myFinalList;
        }
        for (File f : listOfFilesInFolder) {
            
            
            /*try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                try (InputStream is = Files.newInputStream(Paths.get(f.getAbsolutePath()));
                        DigestInputStream dis = new DigestInputStream(is, md))
                {
                    /* Read decorated stream dis to EOF as normal...* /
                    System.out.println("** the dis for file " + f.getAbsolutePath() + " -- is: " + dis);
                } catch (Exception e) { }
                        
            } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 not a valid message digest algorithm");
            }
            */ //didn't understand what dis will be used for. Tries its various methods. Not sure. Just want the MD5 Checksum
            //Trying this instead:
            try (InputStream is = Files.newInputStream(Paths.get(f.getAbsolutePath()))) {
                String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
                System.out.println("TheMd5Hex of inputstream is: " + md5);
            } catch (Exception e) {
                System.out.println (e);
            }
            
            
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
                    System.out.println("IS DUPLICATE");
                    myFinalList.add(f);
                }
            }
        }
        return myFinalList;
    }

}
