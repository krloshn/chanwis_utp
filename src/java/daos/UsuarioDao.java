/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import coneccion.conexionDB;
import dtos.Usuario;
import interfaces.OperacionesBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDao implements OperacionesBD<Usuario>{
    // CONEXION A LA BASE DE DATOS GESTIONADA POR LA CLASE CONECTIONBD
    private static conexionDB con = conexionDB.newConnection();
    // REALIZAR LAS CONSULTAS A LA BASE DE DATOS CON PREPARESTATEMENT
    private static PreparedStatement pstm = null;
    // PARA ALMACENAR EL RESULTADO DE LA CONSULTA REALIZADA
    private static ResultSet res = null;
    // DEFINIR LOS QUERYS
    private static final String INSERT = "insert into usuario(nombre,apellido,correo,telefono,contrasena)values(?,?,?,?,?)";
    
    private static final String LOGEO = "select nombre,apellido from usuario where correo=? and contrasena=?";
    
    private static final String CLAVE = "select correo,nombre,contrasena from usuario where correo=?";
    
    public Usuario login(String correo, String clave){
        Usuario usuario = null;
        try{
            pstm = con.getConnection().prepareStatement(LOGEO);
 
            pstm.setString(1, correo);
            pstm.setString(2, clave);

            res = pstm.executeQuery();
            while(res.next()){
              usuario = new Usuario();
              usuario.setNombre(res.getString(1));
              usuario.setApellido(res.getString(2));
                          
            }
        } catch (Exception e) {
              System.out.println("Error login :"+e.getMessage());
              e.printStackTrace();
        } finally {
            closeConnection();
        }
        
      return usuario;
    }
    
    public Usuario recuperar(String correo){
        Usuario usuario= null;
        try{
            pstm = con.getConnection().prepareStatement(CLAVE);
 
            pstm.setString(1, correo);
            res = pstm.executeQuery();
            while(res.next()){
              usuario = new Usuario();
                usuario.setCorreo(res.getString(1));      
                usuario.setNombre(res.getString(2));      
                usuario.setClave(res.getString(3));      
            }
        } catch (Exception e) {
              System.out.println("Error recuperacion :"+e.getMessage());
              e.printStackTrace();
        } finally {
            closeConnection();
        }
        
      return usuario;
    }

    
    public Integer insert1(Usuario t) {
        Integer result = 0;
        try {
            pstm = con.getConnection().prepareStatement(INSERT);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellido());
            pstm.setString(3, t.getCorreo());
            pstm.setString(4, t.getTelefono());
            pstm.setString(5, t.getClave());    

            if (pstm.executeUpdate() > 0) {
                result=pstm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
        }
      return  result;
    }
    
    @Override
    public Boolean insert(Usuario t) {
        boolean result = false;
        try {
            pstm = con.getConnection().prepareStatement(INSERT);
            pstm.setString(1, t.getNombre());
            pstm.setString(2, t.getApellido());
            pstm.setString(3, t.getCorreo());
            pstm.setString(4, t.getTelefono());
            pstm.setString(5, t.getClave());    

            if (pstm.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Errror al insertar :"+e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
      return result;
    }
    @Override
    public Boolean update(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario selectById(Object idd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void closeConnection() {
        try {
            if(res!=null)res.close();
            if(pstm!=null)pstm.close();
            if(con!=null)con.closeConnection();
        } catch (Exception e) {
            System.out.println("Error al cerrar :"+e.getMessage());
        }
        
    }
    
}
