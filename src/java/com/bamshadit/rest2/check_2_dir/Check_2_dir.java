package com.bamshadit.rest2.check_2_dir;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bamshad
 */
public class Check_2_dir extends HttpServlet {

    @EJB
    Filewalker1 fw;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessRequest</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>context path: " + request.getContextPath() + "</h1>");
            //out.println("<h1>request uri: " + request.getRequestURI() + "</h1>");
            out.println("<h1>dir1: " + request.getParameter("dir1") + "</h1>");
            out.println("<h1>dir2: " + request.getParameter("dir2") + "</h1>");
            out.println("</body>");
            out.println("</html>");
            List<File> receivedList =  fw.walk("c:\\BL Test");            
            out.println("Total number of dir and files: " + receivedList.size());
            for (File item:receivedList) {
                out.println ("<div class='filleOrDir' style='cursor:pointer; border:1px solid darkblue; padding:5px; margin:5px; background-color:#F2F2F2; font-family:Tahoma'>");
                out.println ("name: " + item.getName() + " | path: " + item.getAbsolutePath());
                out.println ("</div>");
            }
            
            //clear the list since apparently by reloading (hot reload?) the list just gets loaded with another set
            //rather than a fresh list
            receivedList.clear();

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
