/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.resources;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.Stateless;

/**
 *
 * @author Bamshad
 */
@Stateless
public class MD5Checksum {
    public String getMD5(String filePath) {
        System.out.println("- called the getMD5 method of class GetMD5Checksum");
        String md5 = "";
        //try (InputStream is = Files.newInputStream(Paths.get(f.getAbsolutePath()))) {
        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {            
            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
            System.out.println("TheMd5Hex of inputstream is: " + md5);
        } catch (Exception e) {
            System.out.println("BL Exception: " + e);                
            md5 = "";
        }
        return md5;
    }
    
}
