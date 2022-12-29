package com.bamshadit.check.in_2_folders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.bamshadit.rest.check_1_dir.*;
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
@Path("/folders/")
public class Check_2_dirs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloworldResource
     */
    public Check_2_dirs() {
    }

    /**
     * Retrieves representation of an instance of controlller.HelloworldResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    //@Produces(MediaType.TEXT_PLAIN)
    //public String getJson(@PathParam("dir") String dir) {
    public String getJson(@FormParam("dir1") String dir1, @FormParam("dir2") String dir2) {
    //public String getJson() {
        //TODO return proper representation object
        return ("{\"name\":\"value\", \"dir1\":\"" + dir1 + "\"" + ",\"dir2\":\"" + dir2 + "\" }");
        //return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");
        //throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    //@Produces(MediaType.TEXT_PLAIN)
    //public String getJson(@PathParam("dir") String dir) {
    public String getJson() {
    //public String getJson(@FormParam("dir") String dir) {
    //public String getJson() {
        //TODO return proper representation object
        //return ("{\"name\":\"value\", \"dir\":\"" + dir + "\"}");
        return ("{\"name\":\"value\", \"name2\":\"" + "name" + "\"}");
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of HelloworldResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
