package modelDao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import model.Ejecutivo;
import model.Natural;

public class NaturalDao {
	private static final String SQL_INSERT = "call banco.sp_ingresar_natural(?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "call banco.sp_actualizar_natural(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_EXISTS = "SELECT n.cliente_persona_perRut AS RUT FROM banco.natural n WHERE cliente_persona_perRut =?";
	private static final String SQL_SEARCH = "SELECT * FROM banco.natural n,cliente c, persona p "
			+ "WHERE p.perRut = c.persona_perRut" + "AND c.persona_perRUt= n.cliente_persona_perRut"
			+ "AND clinete_persona_perRut LIKE=?";
	private static final String SQL_LIST = "SELECT * FROM vw_listar_cliente_naturales";

	private static final Conexion cnn = Conexion.saberEstado();

	public boolean insert(Natural n) {
		PreparedStatement ps;
		int bandera = 1;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_INSERT);
			ps.setString(1, n.getPerRut());
			ps.setString(2, n.getPerNombre());
			ps.setString(3, n.getPerApePaterno());
			ps.setString(4, n.getPerApeMaterno());
			ps.setString(5, n.getCliCategoria());
			ps.setInt(6, n.getNatPatrimonio());
			ps.executeUpdate();
			this.exists(n);
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean update(Natural x) {
		CallableStatement ps;
		ResultSet rs;
		int bandera = 0;
		try {
			ps = cnn.getCnn().prepareCall(SQL_UPDATE);
			ps.setString(1, x.getPerRut());
			ps.setString(2, x.getPerNombre());
			ps.setString(3, x.getPerApePaterno());
			ps.setString(4, x.getPerApeMaterno());
			ps.setString(5, x.getPerNacionalidad());
			ps.setString(6, x.getPerFecNacimiento());
			ps.setString(7, x.getCliCategoria());
			ps.setString(8, x.getEje().getPerRut());
			ps.setInt(9, x.getNatPatrimonio());
			rs = ps.executeQuery();
			while (rs.next()) {
				bandera = rs.getInt("_RESULTADO");
				System.out.println(bandera);
			}
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean exists(Natural n) {
		PreparedStatement ps;
		ResultSet rs;
		Natural n2 = new Natural();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_EXISTS);
			ps.setString(1, n.getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				n = new Natural();
				n2.setPerRut(rs.getString("RUT"));
			}
			return (n.equals(n2));

		} catch (SQLException ex) {
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public Natural search(Natural n) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_SEARCH);
			ps.setString(1, n.getPerRut() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				n.setPerRut(rs.getString("RUT"));
				n.setPerApePaterno(rs.getString("PATERNO"));
				n.setPerApeMaterno(rs.getString("MATERNO"));

			}
		} catch (SQLException ex) {
		} finally {
			cnn.cerrarConexion();
		}
		return n;
	}

	public ArrayList<Natural> list() {

		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Natural> naturales = new ArrayList<Natural>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				e.setPerRut(rs.getString("EJECUTIVO"));
				naturales.add(new Natural(rs.getString("RUT"), rs.getString("NOMBRE"), rs.getString("PATERNO"),
						rs.getString("MATERNO"), rs.getString("NACIONALIDAD"), rs.getString("NACIMIENTO"),
						rs.getString("CATEGORIA"), e, rs.getInt("PATRIMONIO")));
			}
		} catch (SQLException ex) {
			System.out.println("Error al Listar Cliente Natural");
		} finally {
			cnn.cerrarConexion();
		}
		return naturales;
	}

}
