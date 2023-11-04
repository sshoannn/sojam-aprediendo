package modelo;

import modelo.Cconexion;
import com.mysql.cj.protocol.Resultset;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Calumnos {

    int codigoAlumno;
    String nombreAlumno;
    String apellidoAlumno;

    public int getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(int codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public void insertarAlumno(JTextField paramentrosNombres, JTextField parametrosApellidos) {

        setNombreAlumno(paramentrosNombres.getText());
        setApellidoAlumno(parametrosApellidos.getText());

        Cconexion objCconexion = new Cconexion();

        String consulta = "insert into Alumnos (nombres,apellidos) values (?,?);";

        try {
            CallableStatement cs = objCconexion.estableConnection().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.execute();
            JOptionPane.showInternalMessageDialog(null, "Alumno agregado");
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, "Error Alumno no agregado" + e.toString());

        }

    }

    public void mostrarAlumnos(JTable parametroTablaTotalAlumnos) {
        Cconexion objCconexion = new Cconexion();
        DefaultTableModel modeloTabla = new DefaultTableModel();


        String consulta = "";
        

        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        
        
        
       

        parametroTablaTotalAlumnos.setModel(modeloTabla);

        String sql = "select *from Alumnos;";
        String[] datos = new String[3];
        Statement st;

        try {
            st = objCconexion.estableConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            //para recorrer la tabla
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modeloTabla.addRow(datos);

            }
            parametroTablaTotalAlumnos.setModel(modeloTabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostar los registros" + e.toString());
        }

    }

    public void SelecionarAlumnos(JTable parametroTablaTotalAlumnos, JTextField paramId, JTextField paramNombre, JTextField paramApellidos) {

        try {
            int fila = parametroTablaTotalAlumnos.getSelectedRow();
            if (fila >= 0) {
                paramId.setText(parametroTablaTotalAlumnos.getValueAt(fila, 0).toString());
                paramNombre.setText(parametroTablaTotalAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(parametroTablaTotalAlumnos.getValueAt(fila, 2).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no selecionada");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion" + e.toString());
        }

    }

    public void modificarAlumnos(JTextField parameCodigo, JTextField paramNombre, JTextField paramApellido) {
       
        setNombreAlumno(paramNombre.getText());
        setApellidoAlumno(paramApellido.getText());
        setCodigoAlumno(Integer.parseInt(parameCodigo.getText()));

        Cconexion objCconexion = new Cconexion();
        String consulta = "update Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id = ?;";

        try {

            CallableStatement cs = objCconexion.estableConnection().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setInt(3, getCodigoAlumno());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Moficacion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al modificar" + e.toString());

        }

    }

    public void EliminarAlumnos(JTextField paramId) {
        setCodigoAlumno(Integer.parseInt(paramId.getText()));

        Cconexion objConexion = new Cconexion();
        String consulta = "DELETE FROM Alumnos where alumnos.id = ?;";

        try {
            CallableStatement cs = objConexion.estableConnection().prepareCall(consulta);
            cs.setInt(1, getCodigoAlumno());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Estuduante eliminado Correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo completar la eliminacion" + e.toString());
        }

    }
    /*
    
    public void buscarPorId(JTextField IdBusaqueda,JTextField IdResultado,  JTextField nombreBusqueda,JTextField nombreResultado, JTextField aoellidoBusqueda, JTextField apellidoResultado){
        
        String consulta = "select id, nombres, apellidos from Alumnos where alumnos.id = (?);";
        
            Cconexion objCconexion = new Cconexion();
            try {
                CallableStatement cs = objCconexion.estableConnection().prepareCall(consulta);
                cs.setInt(1,Integer.parseInt(aoellidoBusqueda.getText()));
                cs.execute();
                
                ResultSet rs  = cs.executeQuery();
                if(rs.next()){
                    JOptionPane.showConfirmDialog(null, "Registro encontrado");
                    IdResultado.setText(rs.getString("id"));
                    nombreResultado.setText(rs.getString("nombres"));
                    apellidoResultado.setText(rs.getString("apellidos"));
                    
                }else{
                    JOptionPane.showConfirmDialog(null, "Registro no encontrado");
                    IdResultado.setText("");
                    nombreResultado.setText("");
                    apellidoResultado.setText("");
                    
                    
                }
                
                
        } catch (SQLException e) {
           JOptionPane.showConfirmDialog(null, "ERROR :"+ e.toString());

        }
    }
     */
}
