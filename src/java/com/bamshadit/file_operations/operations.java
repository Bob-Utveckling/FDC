/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamshadit.file_operations;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bamshad
 */
@Path("/")
public class operations {

    @Context
    private UriInfo context;

    public operations() {
    }


    @POST
    @Path("/move/")
    @Produces(MediaType.APPLICATION_JSON)
    public String moveFile(
            @FormParam("currentPath") String currentPath,
            @FormParam("newPath") String newPath) throws IOException {
            
        File givenNewFilePath = new File(newPath);
        if (givenNewFilePath.isDirectory()) { 
            File tempFileName = new File(currentPath);
            String FileName = tempFileName.getName();
            newPath = newPath + "/" + FileName;
        }
        
        String result = "";
        java.nio.file.Path temp = Files.move(
                Paths.get(currentPath),
                Paths.get(newPath));
        
        if (temp != null) {
            result = "Done: File renamed and moved successfully";
        } else {
            result = "Error: Failed to move the file";
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(result);
        System.out.println("FINAL: " + json.toString());
        return (json);
        //return (result);
    }
    
    
    @GET
    @Path("/delete/")
    @Produces(MediaType.APPLICATION_JSON)
    //public String deleteFile(@PathParam("filePath") String filePath) {
    public String deleteFile1(@QueryParam("filePath") String filePath) {
        File f = new File(filePath);
        String result = "";
        
        if (f.delete()) {
            //result="{\"completed\":\"deleted\",\"fileName\":\"" + "filePath" + "\"}";
            result = "deleted " + filePath;
        } else {
            result="failed to delete " + filePath;
        }
        Gson gson = new Gson();
        String json = gson.toJson(result);
        System.out.println("FINAL: " + json.toString());
        return (json);
        //return (result);
    }



    /**
     * PUT method for updating or creating an instance of operations
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
