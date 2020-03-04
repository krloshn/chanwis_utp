/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import coneccion.conexionDB;
import dtos.Categoria;
import interfaces.OperacionesBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDao implements OperacionesBD<Categoria>{
    private static conexionDB con = conexionDB.newConnection();
    // REALIZAR LAS CONSULTAS A LA BASE DE DATOS CON PREPARESTATEMENT
    private static PreparedStatement pstm = null;
    // PARA ALMACENAR EL RESULTADO DE LA CONSULTA REALIZADA
    private static ResultSet res = null;
    // DEFINIR LOS QUERYS
    private static final String INSERT = "insert into usuario(nombre,apellido,correo,telefono,contrasena)values(?,?,?,?,?)";
    
    private static final String CAT_LIST = "select * from categoria";
    
    private static final String CLAVE = "select correo,nombre,contrasena from usuario where correo=?";
    
    ArrayList<Categoria> cat_list = new ArrayList<Categoria>();

    public ArrayList<Categoria> categoria_list(){
        Categoria cat = null;
         try{
            pstm = con.getConnection().prepareStatement(CAT_LIST);
 

            res = pstm.executeQuery();
            while(res.next()){
              cat = new Categoria();
              cat.setId(res.getInt(1));
              cat.setDescripcion(res.getString(2));
              cat.setIdestado(res.getInt(3));
              cat_list.add(cat);

            }
        } catch (Exception e) {
              System.out.println("Error login :"+e.getMessage());
              e.printStackTrace();
        } finally {
            closeConnection();
        }
         
         return cat_list;
    }
    
    @Override
    public Boolean insert(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(Categoria t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Categoria> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria selectById(Object idd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Categoria> selectByName(String name) {
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
