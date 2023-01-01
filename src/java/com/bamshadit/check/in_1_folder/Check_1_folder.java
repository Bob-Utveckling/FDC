package com.bamshadit.check.in_1_folder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.bamshadit.check.in_1_folder.Filewalker;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
//import javax.json.JsonObject;
//import org.json.JSONObject;
import com.google.gson.*;

/**
 * REST Web Service
 *
 * @author Bamshad
 */
@Path("/")
public class Check_1_folder {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloworldResource
     */
    public Check_1_folder() {
    }

    @EJB
    Filewalker fw;

    @EJB
    DuplicateCheckerInOneFolder dc1f;

    @POST
    @Path("/folder/check_folder_for_duplicates")
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    //@Produces(MediaType.TEXT_PLAIN)
    public String CheckOneFolder(
            @FormParam("folderName") String folderName,
            @FormParam("fileName") String fileName) {

        List<File> receivedFiles = dc1f.getDuplicateFiles(folderName, fileName);
        
        /*Remove the original file from list since we keep that.
        * simple but if we get it complex, then the folderName
        * with double slashes or backslashes etc. should also be checked
        */
        File origFileToRemove = new File("");
        for (File f : receivedFiles) {
            if (f.getParent().equals(folderName)) {
                origFileToRemove = f;
            }
        } receivedFiles.remove(origFileToRemove);

        //return ("{\"name\":\"value\", \"dir\":\"" + dir + "\"}");
        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");
        String filePath = "";
        HashMap<String, String> pathAndNameList = new HashMap<String, String>();
        int i = 0;
        for (File file : receivedFiles) {
            i++;
            //System.out.println ("file is: " + file.getName());
            if (file.isDirectory()) {
            } else if (file.isFile()) {
                filePath = file.getAbsolutePath();
            } else {
                filePath = "unknown path";
            }
            filePath += "_" + i;
            pathAndNameList.put(filePath, file.getName());
        }

        /*System.out.println(typeAndNameList.size());
        System.out.println(typeAndNameList.get(f2));*/
        //JSONObject obj = new JSONObject(typeAndNameList);
        //JsonObject obj = new JsonObject(typeAndNameList);
        Gson gson = new Gson();
        String json = gson.toJson(pathAndNameList);

        //return (typeAndNameList.toString());
        receivedFiles.clear();
        return (json);
    }

    @POST
    @Path("/folder/show_all_files_and_folders")
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    //@Produces(MediaType.TEXT_PLAIN)
    public String getFolder(@FormParam("folderName") String folderName) {

        List<File> receivedFiles = fw.walk(folderName);
        //return ("{\"name\":\"value\", \"dir\":\"" + dir + "\"}");
        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");
        String type = "";
        HashMap<String, String> typeAndNameList = new HashMap<String, String>();
        int i = 0;
        for (File file : receivedFiles) {
            i++;
            System.out.println("file is: " + file.getName());
            if (file.isDirectory()) {
                type = "Directory";
            } else if (file.isFile()) {
                type = "File";
            } else {
                type = "unknown";
            }
            type += i;
            typeAndNameList.put(type, file.getName());
        }

        /*System.out.println(typeAndNameList.size());
        System.out.println(typeAndNameList.get(f2));*/
        //JSONObject obj = new JSONObject(typeAndNameList);
        //JsonObject obj = new JsonObject(typeAndNameList);        
        Gson gson = new Gson();
        String json = gson.toJson(typeAndNameList);

        //return (typeAndNameList.toString());
        receivedFiles.clear();
        return (json);
    }

    @GET
    @Path("/folder/{folderName}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    //@Produces(MediaType.TEXT_PLAIN)
    //public String getFolderName(@PathParam("folderName") String folderName) {
    //public HashMap<String,String> getFolderName(@PathParam("folderName") String folderName) {
    public String getFolderName(@PathParam("folderName") String folderName) {

        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");
        //List<File> receivedList =  fw.walk("c:\\BL Test");  
        //List<File> receivedFiles = fw.walk("c:\\BL Test");          
        List<File> receivedFiles = fw.walk(folderName);

        /*File f1 = new File("C:/BLfile.txt");
        File f2 = new File("C:/BLFile2.txt");
        List<File> receivedFiles = new ArrayList<File>();
        receivedFiles.add(f1);
        receivedFiles.add(f2);
         */
        String type = "";
        HashMap<String, String> typeAndNameList = new HashMap<String, String>();
        List<String> fileInReceivedList = new ArrayList<>();
        int i = 0;
        for (File file : receivedFiles) {
            i++;
            System.out.println("file is: " + file.getName());
            if (file.isDirectory()) {
                type = "Directory";
            } else if (file.isFile()) {
                type = "File";
            } else {
                type = "unknown";
            }
            type += i;
            typeAndNameList.put(type, file.getName());
        }

        /*System.out.println(typeAndNameList.size());
        System.out.println(typeAndNameList.get(f2));*/
        //JSONObject obj = new JSONObject(typeAndNameList);
        //JsonObject obj = new JsonObject(typeAndNameList);
        Gson gson = new Gson();
        String json = gson.toJson(typeAndNameList);

        //return (typeAndNameList.toString());
        receivedFiles.clear();
        return (json);
        //return ("{\"name\":\"value\", \"dir\":\"" + folderName + "\"}");
    }

    @GET
    @Path("/folder")
    @Produces(MediaType.APPLICATION_JSON)
    public String giveMessage1() {
        return ("{\"message\":\"Please use the format: /folder/folderName to start checking it\"}");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String giveMessage2() {
        return ("{\"message\":\"Please use the format: /folder/folderName to start checking it\"}");
    }

    /**
     * PUT method for updating or creating an instance of HelloworldResource
     *
     * @param content representation for the resource
     */
    /*wrong @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }*/
}
