package modelDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import conexion.Conexion;
import model.Ejecutivo;


public class EjecutivoDao {
	private static final String SQL_INSERT=
            "call banco.sp_ingresar_ejecutivo(?,?,?,?,?)";

	public static final String SQL_UPDATE=
			"call banco.sp_actualizar_ejecutivo(?,?,?,?,?,?,?)";
	public static final String SQL_EXISTS=
			"SELECT e.persona_perRut AS RUT FROM persona p, ejecutivo e WHERE persona_perRut=?";
	public static final String SQL_LIST=
			"SELECT * FROM vw_listar_ejecutivos";
	public static final Conexion cnn=Conexion.saberEstado();
	
	
	public boolean insert(Ejecutivo e){
		PreparedStatement ps;
        int bandera= 1;
        try {

            ps=cnn.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1, e.getPerRut());
            ps.setString(2, e.getPerNombre());
            ps.setString(3, e.getPerApePaterno());
            ps.setString(4, e.getPerApeMaterno());
            ps.setString(5, e.getEjeFecContrato());
            ps.executeUpdate();
            this.exists(e);
            if(bandera > 0){
                return true;
            }
        } catch (SQLException ex) {
        	System.out.println(ex.toString());
        	}finally{
            cnn.cerrarConexion();
        }
        return false;
        
		}
	 public boolean exists(Ejecutivo e) {
	        PreparedStatement ps;
	        ResultSet rs;
	       Ejecutivo e2=new Ejecutivo();
	            try {
	            ps=cnn.getCnn().prepareStatement(SQL_EXISTS);
	            ps.setString(1,e.getPerRut());
	            rs=ps.executeQuery();
	            while(rs.next()){
	                e=new Ejecutivo();
	                e2.setPerRut(rs.getString("RUT"));
	                }
	            return (e.equals(e2));

	            } catch (SQLException ex) {
	            }finally{
	                cnn.cerrarConexion();
	                }
	            return false;
	 }
	public boolean update(Ejecutivo e) {
    	PreparedStatement ps;
    	int bandera;
    	try {
    		ps=cnn.getCnn().prepareStatement(SQL_UPDATE);
    		ps.setString(1, e.getPerRut());
    		ps.setString(2, e.getPerNombre());
    		ps.setString(3, e.getPerApePaterno());
    		ps.setString(4, e.getPerApeMaterno());
    		ps.setString(5, e.getPerNacionalidad());
    		ps.setString(6, e.getPerFecNacimiento());
    		ps.setString(7,e.getEjeFecContrato());
    		System.out.println(e.toString());
    		bandera=ps.executeUpdate();
    		if (bandera>0) {
				return true;
			}
    	}catch (SQLException ex) {
    		System.out.println("Error al Actualizar Ejecutivo");
		}finally {
			cnn.cerrarConexion();
		}
    	return false;
	}

	public ArrayList<Ejecutivo> list() {
	    
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Ejecutivo> ejecutivos= new ArrayList<Ejecutivo>();
        try {
            ps=cnn.getCnn().prepareStatement(SQL_LIST);
            rs=ps.executeQuery();
            while(rs.next()){
                ejecutivos.add(
                		 new Ejecutivo(rs.getString("RUT"),
                                 rs.getString("NOMBRE"),
                                 rs.getString("PATERNO"),
                                 rs.getString("MATERNO"),
                                 rs.getString("NACIONALIDAD"),
                                 rs.getString("NACIMIENTO"),
                                 rs.getString("FECHA_CONTRATO")));
                              
             }
         } catch (SQLException ex) {
             System.out.println("Error al Listar Empleados");
         }finally {
 			cnn.cerrarConexion();
 		}
         return ejecutivos;
     }

    }