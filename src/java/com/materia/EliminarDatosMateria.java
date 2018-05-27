/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.materia;

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

@WebServlet(name = "EliminarDatosMateria", urlPatterns = {"/EliminarDatosMateria"})
public class EliminarDatosMateria extends HttpServlet {

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

        String cod;
        cod = request.getParameter("codMateriaE");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EliminarDatosMateria</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Eliminar Materias</h1>");
            
            out.println("<form action=\"EliminarDatosMateria\"/>");
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

                conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FICA","sa","chugoransutipechisala129");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM MATERIAS");

                out.println("<select name=\"codMateriaE\">");
                out.println("<option selected value=\"null\"> Seleccionar materia </option>");

                while (rs.next()) {
                    out.println("<option>" + rs.getInt("codMateria") + " : " + rs.getString("nomMateria")+ "</option>");
                }
                out.println("</select>");                
                out.println("<input type=\"submit\" value=\"Eliminar\" />");
                out.println("</form>");
                
                System.out.println("codigo: " + cod);
                
                if (cod != null) {
                    stmt.executeUpdate("DELETE MATERIAS WHERE codMateria =" + cod);
                }
                stmt.close();

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            out.println("<form action=\"index.html\" method=\"POST\"/><br>");
            out.println("<table border=\"3\" cellpadding=\"5\">"); 
            out.println("<td rowspan=\"3\"> Eliminado: "+"("+cod+")</td><br>");          
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
