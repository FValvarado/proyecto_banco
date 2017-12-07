package modelDao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import model.Cliente;
import model.Ejecutivo;
import model.Juridico;
import model.Natural;

public class ClienteDao {
	private static final String SQL_LIST = "SELECT * FROM vw_listar_clientes;";
	private static final String SQL_EXISTS = "SELECT RUT FROM banco.vw_listar_clientes WHERE RUT LIKE ?";
	private static final String SQL_SEARCH = "SELECT * FROM banco.vw_listar_clientes WHERE RUT LIKE ?";
	private static final String SQL_SEARCH_CLIENT_TYPE = "call banco.sp_buscar_cliente(?);";
	private static final Conexion cnn = Conexion.saberEstado();

	public Cliente search_client_type(Cliente c) {
		PreparedStatement ps;
		ResultSet rs;
		Natural n;
		Juridico j;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_SEARCH_CLIENT_TYPE);
			ps.setString(1, c.getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				String tipo = rs.getString("TIPO");
				switch (tipo) {
				case "NATURAL":
					n = new Natural();
					n.setPerRut(rs.getString("RUT"));
					n.setPerNombre(rs.getString("NOMBRE"));
					n.setPerApePaterno(rs.getString("PATERNO"));
					n.setPerApeMaterno(rs.getString("MATERNO"));
					n.setPerNacionalidad(rs.getString("NACIONALIDAD"));
					n.setPerFecNacimiento(rs.getString("NACIMIENTO"));
					n.setCliCategoria(rs.getString("CATEGORIA"));
					Ejecutivo ej = new Ejecutivo();
					ej.setPerRut(rs.getString("EJECUTIVO"));
					n.setEje(ej);
					n.setNatPatrimonio(rs.getInt("PATRIMONIO"));
					System.out.println(n);
					return n;
				case "JURIDICO":
					j = new Juridico();
					j.setPerRut(rs.getString("RUT"));
					j.setPerNombre(rs.getString("NOMBRE"));
					j.setPerApePaterno(rs.getString("PATERNO"));
					j.setPerApeMaterno(rs.getString("MATERNO"));
					j.setPerNacionalidad(rs.getString("NACIONALIDAD"));
					j.setPerFecNacimiento(rs.getString("NACIMIENTO"));
					j.setCliCategoria(rs.getString("CATEGORIA"));
					Ejecutivo eje = new Ejecutivo();
					eje.setPerRut(rs.getString("EJECUTIVO"));
					j.setEje(eje);
					j.setJurRazSocial(rs.getString("RAZON_SOCIAL"));
					System.out.println(j);
					return j;
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error al buscar Clientes " + ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return null;
	}

	public boolean search(Cliente cli) {
		CallableStatement ps;
		ResultSet rs;
		try {
			ps = cnn.getCnn().prepareCall(SQL_SEARCH);
			ps.setString(1, cli.getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				e.setPerRut(rs.getString("EJECUTIVO"));
				cli.setPerNombre(rs.getString("NOMBRE"));
				cli.setPerApePaterno(rs.getString("PATERNO"));
				cli.setPerApeMaterno(rs.getString("MATERNO"));
				cli.setPerFecNacimiento(rs.getString("NACIMIENTO"));
				cli.setCliCategoria(rs.getString("CATEGORIA"));
				cli.setEje(e);
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean exists(Cliente cli) {
		PreparedStatement ps;
		ResultSet rs;
		Cliente x2 = new Cliente();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_EXISTS);
			ps.setString(1, cli.getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				x2.setPerRut(rs.getString("RUT"));
			}
			return (cli.equals(x2));

		} catch (SQLException ex) {
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}
	
	public ArrayList<Cliente> list() {

		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				Cliente c = new Cliente();
				c.setPerRut(rs.getString("RUT"));
				c.setPerNombre(rs.getString("NOMBRE"));
				c.setPerApePaterno(rs.getString("PATERNO"));
				c.setPerApeMaterno(rs.getString("MATERNO"));
				c.setPerNacionalidad(rs.getString("NACIONALIDAD"));
				c.setPerFecNacimiento(rs.getString("NACIMIENTO"));
				c.setCliCategoria(rs.getString("CATEGORIA"));
				c.setEje(e);
				clientes.add(c);

			}
		} catch (SQLException ex) {
			System.out.println("Error al Listar Cliente ");
		}
		return clientes;

	}
}
