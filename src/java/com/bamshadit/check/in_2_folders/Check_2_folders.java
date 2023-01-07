package com.bamshadit.check.in_2_folders;

import com.bamshadit.resources.CollectMd5sAndTheirFiles;
import com.bamshadit.resources.MD5Checksum;
import com.bamshadit.resources.Filewalker;
import com.bamshadit.resources.MD5s;
import com.bamshadit.rest.check_1_dir.*;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

/**
 * REST Web Service
 *
 * @author Bamshad
 */
//@Path("/hello/{name}")
//@Path("/Check_1_dir")
//@Path("/check/in_2_folders/")
@Path("/folders")
public class Check_2_folders {

    //@EJB
    //DupPathFiles dupPathFiles;
    @EJB
    CollectMd5sAndTheirFiles collectMd5sAndTheirFiles;    
    @EJB
    MD5Checksum md5Checksum;
    @EJB
    MD5s Md5s;   
    //Filewalker not annotated as EJB. One instance caused repeat values even with a different argument for fw.walk(dir1) & dir2
    //@EJB
    //Filewalker fw;    
    @Context
    private UriInfo context;


    public Check_2_folders() {
    }

    @POST
    @Path("/get_all_duplicates_on_two_paths/")    
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@FormParam("dir1") String dir1, @FormParam("dir2") String dir2) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        //return ("{\"name\":\"value\", \"dir1\":\"" + dir1 + "\"" + ",\"dir2\":\"" + dir2 + "\" }");
        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");                
        Filewalker fw1 = new Filewalker();
        List<File> receivedFiles1 = fw1.walk(dir1);
        Filewalker fw2 = new Filewalker();
        List<File> receivedFiles2 = fw2.walk(dir2);
        
        /*System.out.println("\n <><><><><><><><><><><><><><><><><><>");
        System.out.println(receivedFiles1.toString());
        System.out.println("\n <><><><><><><><><><><><><><><><><><>");
        System.out.println(receivedFiles2.toString());
        System.out.println("\n <><><><><><><><><><><><><><><><><><>");*/

        HashMap<String,List<String>> Md5sAndTheirFiles1 = collectMd5sAndTheirFiles.collectAndReturn(receivedFiles1);
        HashMap<String,List<String>> Md5sAndTheirFiles2 = collectMd5sAndTheirFiles.collectAndReturn(receivedFiles2);

        //Set<String> Files1MD5s = Md5s.getMD5s(receivedFiles1);        
                
        /*System.out.println("\n <><><><><><><><><><><><><><><><><><>");
        System.out.println("\n <><><><><><><><><><><><><><><><><><>");
        System.out.println(receivedFiles1.toString());
        System.out.println("\n <><><><><><><><><><><><><><><><><><>");
        System.out.println(receivedFiles2.toString());
        System.out.println("\n <><><><><><><><><><><><><><><><><><>");*/


        List keys1 = new ArrayList(Md5sAndTheirFiles1.keySet());
        //List keys2 = new ArrayList(Md5sAndTheirFiles2.keySet());
        //List<String> duplicateMd5s = new ArrayList<String>();        

        List<DupPathFiles> myDupPathFilesObjs = new ArrayList<DupPathFiles>();        
        /*DupPathFiles myDupObj = new DupPathFiles();        
        Collections.addAll(myDupObj.onpath1, "file1","file2","file3","file4");      
        Collections.addAll(myDupObj.onpath2, "file3", "file23","file1");
        myDupPathFilesObjs.add(myDupObj);

        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "file3", "file23","file1");
        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "file23", "file223","file2221");
        
        myDupPathFilesObjs.add(new DupPathFiles(list1, list2));        
        */
        for (int i=0; i<Md5sAndTheirFiles1.size(); i++) {
            if (Md5sAndTheirFiles2.containsKey(keys1.get(i))) {
                //has found 
                myDupPathFilesObjs.add(new DupPathFiles(Md5sAndTheirFiles1.get(keys1.get(i)),
                                                        Md5sAndTheirFiles2.get(keys1.get(i)))
                );
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(myDupPathFilesObjs);
        System.out.println("FINAL: " + json.toString());
        //receivedFiles1.clear();
        //receivedFiles2.clear();
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("memory before run: " + beforeUsedMem/1000000 + "MB , after run: " + afterUsedMem/1000000 + "MB -- usage: " + actualMemUsed/1000000);
        return (json);        
        
    }
    
}
