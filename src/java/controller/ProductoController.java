/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.CategoriaDao;
import daos.ProductoDao;
import dtos.Categoria;
import dtos.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

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
            response.setContentType("text/plain");
            String proceso=request.getParameter("txtProceso");
            switch (proceso) {
                  case "jx_productos":
                      jx_productos(request, response);
                      break;
              }
    }
    
    private void jx_productos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categorias = request.getParameter("categoria");
       ArrayList<Categoria> cat_list = new ArrayList<Categoria>();
        Producto produc = new Producto();
        StringBuffer returnData = null;
        
        CategoriaDao catDAO = new CategoriaDao();
        ProductoDao prodDAO = new ProductoDao();
        
        returnData = new StringBuffer("{\"data\":[");
        String coma = "";
        for (Categoria catego : catDAO.categoria_list()) {
            
            returnData.append(""+coma+"{\"id\": \""+catego.getId()+"\",\"descrip\": \""+catego.getDescripcion()+"\"}");
        
            coma = ",";
        }
        
         returnData.append("]}");
        
        response.getWriter().write(returnData.toString());
        
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
