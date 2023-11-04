package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Cconexion {

    Connection Conectar = null;
    
    String basebd = "bdescuela";
    String user = "root";
    String Pass = "root";
    
    String url =  "jdbc:mysql://localhost:3306/"+basebd;

    public Connection estableConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conectar = DriverManager.getConnection(url, user, Pass);
           JOptionPane.showMessageDialog(null, "Conexion Exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos" + e.toString());
        }
        return Conectar;
    }

}
