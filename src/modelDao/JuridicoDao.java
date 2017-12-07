package modelDao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import model.Ejecutivo;
import model.Juridico;

public class JuridicoDao {
	private static final String SQL_INSERT = "call banco.sp_ingresar_juridico(?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "call banco.sp_actualizar_juridico(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_LIST = "SELECT * FROM vw_listar_cliente_juridicos";
	private static final String SQL_EXISTS = "SELECT j.cliente_persona_perRut AS RUT FROM juridico j WHERE cliente_persona_perRut=?";

	private static final Conexion cnn = Conexion.saberEstado();

	public boolean insert(Juridico j) {
		PreparedStatement ps;
		int bandera = 1;
		try {

			ps = cnn.getCnn().prepareStatement(SQL_INSERT);
			ps.setString(1, j.getPerRut());
			ps.setString(2, j.getPerNombre());
			ps.setString(3, j.getPerApePaterno());
			ps.setString(4, j.getPerApeMaterno());
			ps.setString(5, j.getCliCategoria());
			ps.setString(6, j.getJurRazSocial());
			ps.executeUpdate();
			this.exists(j);
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

	public boolean exists(Juridico j) {
		PreparedStatement ps;
		ResultSet rs;
		Juridico j2 = new Juridico();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_EXISTS);
			ps.setString(1, j.getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				j = new Juridico();
				j2.setPerRut(rs.getString("RUT"));
			}
			return (j.equals(j2));

		} catch (SQLException ex) {
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean update(Juridico j) {
		CallableStatement ps;
		ResultSet rs;
		int bandera = 0;
		try {
			ps = cnn.getCnn().prepareCall(SQL_UPDATE);

			ps.setString(1, j.getPerRut());
			ps.setString(2, j.getPerNombre());
			ps.setString(3, j.getPerApePaterno());
			ps.setString(4, j.getPerApeMaterno());
			ps.setString(5, j.getPerNacionalidad());
			ps.setString(6, j.getPerFecNacimiento());
			ps.setString(7, j.getCliCategoria());
			ps.setString(8, j.getEje().getPerRut());
			ps.setString(9, j.getJurRazSocial());
			System.out.println(ps.toString());
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

	public ArrayList<Juridico> list() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Juridico> juridicos = new ArrayList<Juridico>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				e.setPerRut(rs.getString("EJECUTIVO"));
				juridicos.add(new Juridico(rs.getString("RUT"), rs.getString("NOMBRE"), rs.getString("PATERNO"),
						rs.getString("MATERNO"), rs.getString("NACIONALIDAD"), rs.getString("NACIMIENTO"),
						rs.getString("CATEGORIA"), e, rs.getString("RAZON_SOCIAL")));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return juridicos;
	}
}
