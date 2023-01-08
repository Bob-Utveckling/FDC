package com.bamshadit.file_similarity;

import com.bamshadit.resources.Filewalker;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
@Path("/")
public class Similarity {

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Similarity
     */
    public Similarity() {
    }

    @POST
    @Path("/")      
    @Produces(MediaType.APPLICATION_JSON)
    public String method1(
            @FormParam("fileToCheck") String fileToCheck,
            @FormParam("whereToLook") String whereToLook) {
        File temp = new File(fileToCheck);        
        FileObj myFile = new FileObj(
            temp.getName(),
            temp.getParent(),
            0,0,0, temp.length());
        
        Filewalker fw = new Filewalker();
        List<File> receivedFiles = fw.walk(whereToLook);
        List<FileObj> fobjects = new ArrayList<>();
        for (File f:receivedFiles) {
            fobjects.add(new FileObj(
                f.getName(),
                    f.getParent(),
                    rank_name_with_chars(myFile.getFileName(), f.getName()),
                    rank_name_with_ordered_chars(myFile.getFileName(), f.getName()),
                    rank_path(myFile.getFilePath(), f.getParent()),
                    rank_size(myFile.getSize_ranking(), f.length())
            ));
        }
        
        updateFinalSocres(fobjects);
        Collections.sort(fobjects);
        
        Gson gson = new Gson();
        String json = gson.toJson(fobjects);        
        return (json);
        //return ("{\"name\":\"value\", \"dir\":\"" + folderName + "\"}");
    }
    
    public void updateFinalSocres(List<FileObj> fobjs) {
        for (int i=0; i<fobjs.size(); i++) {
            fobjs.get(i).setTotalscore(fobjs.get(i).getName_ranking_for_chars()+
                    fobjs.get(i).getName_ranking_for_ordered_chars()+
                    fobjs.get(i).getPath_ranking() +
                    fobjs.get(i).getSize_ranking());
        }
    }
    
    public int rank_size(long myLength, long fLength) {
        //within a threshold
        int score = 0;
        if (myLength==fLength) {
            score = 4;
        } else
        if (myLength/fLength < 2 || myLength/fLength > 0.5) {
            score = 1;
        } else {
            score = 0;
        }
        System.out.println("rank_size: " + score);
        return score;
    }
    
    
    public int rank_path(String myPath, String fPath) {
        int score = 0;
        
        return score;
    }
    
    public int rank_name_with_ordered_chars(String myFileName, String fFileName) {
        int score = 0;
        for (int i=0; i<fFileName.length(); i++) {
            if (i>=myFileName.length()) { break; }
            if (fFileName.contains(myFileName.substring(0, i))) {
                score += 1;
            }
        }
        System.out.println("rank_name_with_ordered_chars: " + score);
        return score;
    }
    
    public int rank_name_with_chars(String myFileName, String fFileName) {
        int score = 0;
        char[] myFileNameChars = myFileName.toCharArray();
        //char[] fFileNameChars = fFileName.toCharArray();       
        for (char myChar:myFileNameChars) {            
            if (fFileName.indexOf(myChar) != -1 ) {
                //this char of myFileName in fFileName
                score += 1;
            }
        }
        System.out.println("rank_name_with_chars: " + score);
        return score;
    }

}
