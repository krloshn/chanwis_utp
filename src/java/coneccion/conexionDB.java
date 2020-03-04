/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Daniel
 */
public class conexionDB {
    
        private static conexionDB instance = null;
    /*HACIENDO USO DE LA API JDBC */
    private static Connection con;
    /*DEFINIENDO LOS PARAMETROS DE CONEXION A LA BD*/
    private static final String URL = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=chanwis";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String USER = "sa";
    private static final String PASS = "Proyecto1#";
             /* CONSTRUCTOR PRIVADO */
    private conexionDB() {
        try {
            //REGISTRAR EL DRIVER DE CONEXION A LA BD
            Class.forName(DRIVER).newInstance();
            //REALIZAR LA CONEXION
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion establecida correctamente");
            System.out.println(""+con.toString());
        } catch (Exception ex) {
            System.out.println("Error de conexion" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public synchronized static conexionDB newConnection() {
        if (instance == null) {
            instance = new conexionDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        instance = null;
    }


}
