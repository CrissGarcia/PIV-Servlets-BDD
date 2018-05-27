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

@WebServlet(name = "ReporteDocentes", urlPatterns = {"/ReporteDocentes"})
public class ReporteDocentes extends HttpServlet {

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
        ResultSet rs;

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReporteDocentes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Reporte de Docentes</h1>");
            out.println("<form action=\"index.html\" method=\"POST\"/><br>");
            out.println("<table border=\"3\" cellpadding=\"5\">");          
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
//                        + "databaseName=Fica;user=sa;password=sasql");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FICA",
                                                   "sa","chugoransutipechisala129");
                stmt = conn.createStatement();

                rs = stmt.executeQuery("SELECT * FROM DOCENTES");

                while (rs.next()) {
                    out.println("_____________________________________________________"+"<br>");
                    out.println();
                    out.println("Id docente:"+ rs.getInt("idDocente")+"<br>");    
                    out.println("Nombre docente : "+rs.getString("nomDocente")+"<br>");
                    out.println("Edad docente : " + rs.getInt("edadDocente")+"<br>");                  
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            out.println("<br>");
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
