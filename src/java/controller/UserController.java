/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UsuarioDao;
import dtos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class UserController extends HttpServlet {

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
        
    }
     private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtcorreo");
        String clave = request.getParameter("txtClave");
      
        String apellido = request.getParameter("txtApellido");
        String telefono = request.getParameter("txtTelefono");
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setClave(clave);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        
        UsuarioDao usuarioDAO = new UsuarioDao();
        
        if (usuarioDAO.insert(usuario)) {
           request.getSession().setAttribute("usuario", nombre);
            request.getSession().setAttribute("procesos","logeado");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
        }
     }
     
     
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo=request.getParameter("txtcorreo");
        String clave= request.getParameter("txtClave");
        Usuario usuario = new Usuario(); //invocas clase usuario
        
        UsuarioDao usuarioDAO = new UsuarioDao(); //invocar clase de dato login
        
        usuario = usuarioDAO.login(correo, clave );
        
        if(usuario == null){
          request.getRequestDispatcher("error.jsp").forward(request, response);
        }else{
            request.getSession().setAttribute("usuario",usuario.getNombre());
            request.getSession().setAttribute("procesos","logeado");
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
        
        
    }
    
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("usuario","");
        request.getSession().setAttribute("procesos","registrarse");
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }

     private void recuperarClave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("txtcorreo");

        Usuario usuario = new Usuario();
        
        UsuarioDao usuarioDAO = new UsuarioDao();
        
        usuario = usuarioDAO.recuperar(correo);
         if(usuario == null){
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
         }else{
             
            String to = correo;
            String subject = "recuperacion de clave";
            String message =  "tu clave es : " + usuario.getClave();
            String user = "chuamanh.canvia@gmail.com";
            String pass = "Proyecto1#";
            SendMail.send(to,subject, message, user, pass);
           request.getRequestDispatcher("sendmail.jsp").forward(request, response);

        
         }
     }
     
    private void serv_recuperarclave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
            
    String correo = request.getParameter("correo");   
        Usuario usuario = new Usuario();
        String resulto;
        StringBuffer returnData = null;
        UsuarioDao usuarioDAO = new UsuarioDao();
        
        usuario = usuarioDAO.recuperar(correo);
        returnData = new StringBuffer("{\"data\":{");
        if( usuario == null ){        
            returnData.append("\"ind\": \"2\",");
            returnData.append("\"msj\": \"El correo no existe\",");
        }else{
            String to = correo;
            String subject = "recuperacion de clave";
            String message =  "tu clave es : " + usuario.getClave();
            String user = "chuamanh.canvia@gmail.com";
            String pass = "Proyecto1#";
            SendMail.send(to,subject, message, user, pass);
            
            returnData.append("\"ind\": \"1\",");
            returnData.append("\"msj\": \"exito\",");
        }
    
        returnData.append("}}");
        
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
        
      response.setContentType("text/plain");
      String proceso=request.getParameter("txtProceso");
      switch (proceso) {
            case "registro":
                registerUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "logout":
                logoutUser(request, response);
                break;
            case "recuperar_contrasena":
                recuperarClave(request, response);
                break;
            case "json_recuperar_contrasena":
                serv_recuperarclave(request, response);
                break;
        }
                  
        
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
