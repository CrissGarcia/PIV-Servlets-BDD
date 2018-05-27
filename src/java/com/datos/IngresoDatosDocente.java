/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IngresoDatosDocente", urlPatterns = {"/IngresoDatosDocente"})
public class IngresoDatosDocente extends HttpServlet {

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
        
    Connection conn;
    Statement stmt;
    
    int id;
    String nom;
    int edad;
    
    id = Integer.parseInt(request.getParameter("id"));
    edad = Integer.parseInt(request.getParameter("edad"));
    nom = request.getParameter("nombre");


    response.setContentType ("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"+
                                               "databaseName=FICA;user=sa;password=chugoransutipechisala129");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO DOCENTES VALUES ("+id+",'"+nom+"',"+edad+")");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println("Error : "+e.getMessage());
        }
        
            /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet IngresoDatosDocente</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Ingreso BDD</h1>");                                  
        out.println("<form action=\"index.html\" method=\"POST\"/><br>");
        out.println("<table border=\"3\" cellpadding=\"5\">");          
        out.println("<td rowspan=\"3\"> Insertado: "+"("+id+",'"+  nom  +"',"+  edad  +")</td>");          
        out.println("<tr><ID><input type=\"submit\" value=\"REGRESAR\"/></td><br>");
        out.println("</table>");
        out.println("</form>"); 
        out.println("</body>");
        out.println("</html>");
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
