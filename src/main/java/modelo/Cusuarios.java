
package modelo;
import modelo.Cconexion;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import javax.swing.JOptionPane;


import javax.swing.JTextField;


public class Cusuarios {
    
    int idUsuarios;
    String usuario;
    String passUser;
    String nombre;
    String correo;

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void registrarUsuarios(JTextField paramUser, JTextField paramPass, JTextField paramNombre, JTextField paramCOrreo) {
    
        setUsuario(paramUser.getText());
        setPassUser(paramPass.getText());
        setNombre(paramNombre.getText());
        setCorreo(paramCOrreo.getText());
        
        Cconexion objCconexion = new Cconexion();
        
        String consulta = "INSERT INTO usuarios (usuario, pass, nombre, correo) VALUES (?,?,?,?);";
        
            try {
            CallableStatement cs = objCconexion.estableConnection().prepareCall(consulta);
            cs.setString(1, getUsuario());
            cs.setString(2, getPassUser());
            cs.setString(3, getNombre());
            cs.setString(4, getCorreo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Registro completado exitosamente");
        } catch (SQLException e) {
          JOptionPane.showInternalMessageDialog(null, "Error al registrar" + e.toString());

        }
    }
  public void LoginUsuarios(JTextField paramUsuario, JTextField paramPassword) {
    setUsuario(paramUsuario.getText());
    setPassUser(paramPassword.getText());

    try {
        Cconexion objCconexion = new Cconexion();
        String consulta = "SELECT idusuarios, usuario, pass, nombre, correo FROM usuarios WHERE usuario = ?;";
        CallableStatement cs = objCconexion.estableConnection().prepareCall(consulta);
        cs.setString(1, getUsuario());
        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            String storedPassword = rs.getString(3);

            if (getPassUser().equals(storedPassword)) {
                JOptionPane.showMessageDialog(null, "Autenticación exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "Error al Autenticar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
    } 
}
}
