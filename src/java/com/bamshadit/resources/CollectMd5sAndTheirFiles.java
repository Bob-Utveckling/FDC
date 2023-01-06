package com.bamshadit.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import com.bamshadit.resources.MD5Checksum;
import javax.ejb.Stateless;
/**
 *
 * @author Bamshad
 */

@Stateless
public class CollectMd5sAndTheirFiles {
    
    @EJB
    MD5Checksum md5Checksum;
    
    public HashMap<String,List<String>> collectAndReturn(List<File> AllFiles) {
        //Go through the list of files and for their MD5s either
        //add the similar ones to hashmap or a create a new item in the hashmap
        List<String> MyList = new ArrayList<>();
        String thisMd5 = "";
        HashMap<String,List<String>> Md5sAndTheirFiles1 = new HashMap<String,List<String>>();
        for (File f:AllFiles) {
            //System.out.println("-- new file: " + f.getAbsolutePath());
            MyList = new ArrayList<>();
            MyList.add(f.getAbsolutePath());            
            thisMd5 = md5Checksum.getMD5(f.getAbsolutePath());

            if (Md5sAndTheirFiles1.containsKey(thisMd5)) {
                Md5sAndTheirFiles1.get(thisMd5).add(f.getAbsolutePath());                
                /*
                MyList = Md5sAndTheirFiles1.get(thisMd5);
                MyList.add(f.getAbsolutePath());
                Md5sAndTheirFiles1.put(thisMd5, MyList);
                */

            } else {
                Md5sAndTheirFiles1.put(thisMd5, MyList);
                //wrong: Md5sAndTheirFiles1.put(thisMd5, Md5sAndTheirFiles1.get(thisMd5).add(f.getAbsolutePath()));                                
            }
            System.out.println(Md5sAndTheirFiles1.toString());
        }
        return Md5sAndTheirFiles1;
    }
}
