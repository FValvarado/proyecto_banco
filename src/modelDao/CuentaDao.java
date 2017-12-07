package modelDao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import model.Cliente;
import model.Cuenta;
import model.Ejecutivo;
import model.Juridico;
import model.Natural;

public class CuentaDao {
	private static final String SQL_INSERT = "call banco.sp_ingresar_cuenta(?, ?, ?);";
	private static final String SQL_UPDATE = "call banco.sp_actualizar_cuenta(?,?,?,?);";
	private static final String SQL_UPDATE_TRANSACTION = "UPDATE cuenta SET cueSaldo = ? WHERE cueId = ?;";
	private static final String SQL_DELETE = "call banco.sp_eliminar_cuenta(?);";
	private static final String SQL_BLOCK = "call banco.sp_bloquear_cuenta(?);";
	private static final String SQL_SEARCH = "call banco.sp_buscar_cuenta(?);";
	private static final String SQL_SEARCH_ID = "SELECT * FROM cuenta WHERE cueId=?;";
	private static final String SQL_LIST_CUENTA = "SELECT * FROM banco.vw_listar_cuentas;";
	private static final String SQL_LIST_JURIDICO = "SELECT * FROM banco.vw_listar_juridico_cuentas";
	private static final String SQL_LIST_NATURAL = "SELECT * FROM banco.vw_listar_natural_cuentas;";
	private static final Conexion cnn = Conexion.saberEstado();

	public boolean insert(Cuenta c) {
		CallableStatement cs;
		ResultSet rs;
		int bandera = 0;
		try {
			cs = cnn.getCnn().prepareCall(SQL_INSERT);
			cs.setString(1, c.getCliente().getPerRut());
			cs.setInt(2, c.getCueSaldo());
			cs.setInt(3, c.getCueSobregiro());
			rs = cs.executeQuery();
			while (rs.next()) {
				bandera = rs.getInt("_resultado");
			}
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Error al Ingresar Cuenta " + ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean update(Cuenta c) {
		PreparedStatement ps;
		int bandera;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_UPDATE);
			ps.setInt(1, c.getCueId());
			ps.setInt(2, c.getCueSaldo());
			ps.setString(3, c.getCueEstado());
			ps.setInt(4, c.getCueSobregiro());
			System.out.println(c.toString());

			bandera = ps.executeUpdate();
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Error al Actualizar Ejecutivo");
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean updateTransaction(Cuenta c) {
		PreparedStatement ps;
		int bandera;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_UPDATE_TRANSACTION);
			ps.setInt(1, c.getCueSaldo());
			ps.setInt(2, c.getCueId());
			bandera = ps.executeUpdate();
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

	public boolean delete(Cuenta c) {
		PreparedStatement ps;
		int bandera;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_DELETE);
			ps.setInt(1, c.getCueId());
			System.out.println(c.toString());
			bandera = ps.executeUpdate();
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Error Al Eliminar Cuenta");
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public boolean block(Cuenta c) {
		PreparedStatement ps;
		int bandera;
		try {
			ps = cnn.getCnn().prepareStatement(SQL_BLOCK);
			ps.setInt(1, c.getCueId());
			System.out.println(c.toString());
			bandera = ps.executeUpdate();
			if (bandera > 0) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Error al Bloquear Cuenta");
		} finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public Cuenta search(Cuenta cu) {//para la opcion 6 
		CallableStatement ps;
		ResultSet rs;
		ArrayList<Cuenta> bcuentas = new ArrayList<Cuenta>();
		try {
			ps = cnn.getCnn().prepareCall(SQL_SEARCH);
			ps.setString(1, cu.getCliente().getPerRut());
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				Cliente c = new Cliente();
				e.setPerRut(rs.getString("EJECUTIVO"));
				c.setPerRut(rs.getString("RUT"));
				c.setPerNombre(rs.getString("NOMBRE"));
				c.setPerApePaterno(rs.getString("PATERNO"));
				c.setPerApeMaterno(rs.getString("MATERNO"));
				c.setEje(e);
				System.out.println(c);
				bcuentas.add(new Cuenta(rs.getInt("NUMERO_CUENTA"), rs.getInt("SALDO"), rs.getString("ESTADO"), null,
						rs.getInt("SOBREGIRO"), c));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return cu;
	}
	//para realizar un deposito
	public boolean search_id(Cuenta x) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps= cnn.getCnn().prepareStatement(SQL_SEARCH_ID);
			ps.setInt(1, x.getCueId());
			rs = ps.executeQuery();
            while(rs.next()) {
            	x.setCueSaldo(rs.getInt("cueSaldo"));
            	x.setCueSobregiro(rs.getInt("cueSobregiro"));
            	x.setCueEstado(rs.getString("cueEstado"));
            }
		}catch (SQLException ex) {
            System.out.println(ex.toString());
        }finally {
			cnn.cerrarConexion();
		}
		return false;
	}

	public ArrayList<Cuenta> list_Cuenta() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST_CUENTA);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				Cliente c = new Cliente();
				e.setPerRut(rs.getString("EJECUTIVO"));
				c.setPerRut(rs.getString("RUT"));
				c.setPerNombre(rs.getString("NOMBRE"));
				c.setPerApePaterno(rs.getString("PATERNO"));
				c.setPerApeMaterno(rs.getString("MATERNO"));
				c.setPerNacionalidad(rs.getString("NACIONALIDAD"));
				c.setPerFecNacimiento(rs.getString("NACIMIENTO"));
				c.setCliCategoria(rs.getString("CATEGORIA"));
				c.setEje(e);
				cuentas.add(new Cuenta(rs.getInt("NUMERO_CUENTA"), rs.getInt("SALDO"), null, rs.getString("ESTADO"),
						rs.getInt("SOBREGIRO"), c));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return cuentas;

	}

	public ArrayList<Cuenta> list_Natural() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Cuenta> cuentasn = new ArrayList<Cuenta>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST_NATURAL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				Natural n = new Natural();
				e.setPerRut(rs.getString("EJECUTIVO"));
				n.setPerRut(rs.getString("RUT"));
				n.setPerNombre(rs.getString("NOMBRE"));
				n.setPerApePaterno(rs.getString("PATERNO"));
				n.setPerApeMaterno(rs.getString("MATERNO"));
				n.setPerNacionalidad(rs.getString("NACIONALIDAD"));
				n.setPerFecNacimiento(rs.getString("NACIMIENTO"));
				n.setCliCategoria(rs.getString("CATEGORIA"));
				n.setNatPatrimonio(rs.getInt("PATRIMONIO"));
				n.setEje(e);
				cuentasn.add(new Cuenta(rs.getInt("NUMERO_CUENTA"), rs.getInt("SALDO_TOTAL"),
						rs.getString("FECHA_APERTURA"), rs.getString("ESTADO_CUENTA"), rs.getInt("SOBREGIRO"), n));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return cuentasn;
	}

	public ArrayList<Cuenta> list_Juridico() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Cuenta> cuentasj = new ArrayList<Cuenta>();
		try {
			ps = cnn.getCnn().prepareStatement(SQL_LIST_JURIDICO);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ejecutivo e = new Ejecutivo();
				Juridico j = new Juridico();
				e.setPerRut(rs.getString("EJECUTIVO"));
				j.setPerRut(rs.getString("RUT"));
				j.setPerNombre(rs.getString("NOMBRE"));
				j.setPerApePaterno(rs.getString("PATERNO"));
				j.setPerApeMaterno(rs.getString("MATERNO"));
				j.setPerNacionalidad(rs.getString("NACIONALIDAD"));
				j.setPerFecNacimiento(rs.getString("NACIMIENTO"));
				j.setCliCategoria(rs.getString("CATEGORIA"));
				j.setJurRazSocial(rs.getString("RAZON_SOCIAL"));
				j.setEje(e);
				cuentasj.add(new Cuenta(rs.getInt("NUMERO_CUENTA"), rs.getInt("SALDO_TOTAL"),
						rs.getString("FECHA_APERTURA"), rs.getString("ESTADO_CUENTA"), rs.getInt("SOBREGIRO"), j));

			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			cnn.cerrarConexion();
		}
		return cuentasj;
	}
}