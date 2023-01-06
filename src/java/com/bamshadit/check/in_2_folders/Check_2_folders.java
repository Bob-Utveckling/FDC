package com.bamshadit.check.in_2_folders;

import com.bamshadit.resources.CollectMd5sAndTheirFiles;
import com.bamshadit.resources.MD5Checksum;
import com.bamshadit.resources.Filewalker;
import com.bamshadit.rest.check_1_dir.*;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
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

    @EJB
    CollectMd5sAndTheirFiles collectMd5sAndTheirFiles;
    
    @EJB
    MD5Checksum md5Checksum;
    
    @EJB
    Filewalker fw;
    
    @Context
    private UriInfo context;


    public Check_2_folders() {
    }

    @POST
    @Path("/get_all_duplicates_on_two_paths/")    
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@FormParam("dir1") String dir1, @FormParam("dir2") String dir2) {
        //return ("{\"name\":\"value\", \"dir1\":\"" + dir1 + "\"" + ",\"dir2\":\"" + dir2 + "\" }");
        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");                
                
        List<File> receivedFiles = fw.walk(dir1);
        HashMap<String,List<String>> Md5sAndTheirFiles1 = collectMd5sAndTheirFiles.collectAndReturn(receivedFiles);        
        
        Gson gson = new Gson();
        String json = gson.toJson(Md5sAndTheirFiles1);
        System.out.println("FINAL: " + json.toString());
        receivedFiles.clear();
        return (json);
        
    }
    
}
