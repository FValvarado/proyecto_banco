/**
 * 
 */
package model;

/**
 * @author Francisco Valdés
 *version 1.0
 */
public abstract class Cliente extends Persona {
	protected String cliCategoria;

	/**
	 * Constructor vacio
	 */
	public Cliente() {
		super();
	}

	/**
	 * @param perRut 
	 * @param perNombre
	 * @param perApePaterno
	 * @param perApeMaterno
	 * @param nacionalidad
	 * @param perFecNacimiento
	 * @param cliCategoria
	 */
	public Cliente(String perRut, String perNombre, String perApePaterno, String perApeMaterno, String nacionalidad,String perFecNacimiento, String cliCategoria) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, nacionalidad, perFecNacimiento);
		this.cliCategoria = cliCategoria;
	}

	public String getCliCategoria() {
		return cliCategoria;
	}

	public void setCliCategoria(String cliCategoria) {
		this.cliCategoria = cliCategoria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [cliCategoria=" + cliCategoria + ", perRut=" + perRut + ", perNombre=" + perNombre
				+ ", perApePaterno=" + perApePaterno + ", perApeMaterno=" + perApeMaterno + ", Nacionalidad="
				+ Nacionalidad + ", perFecNacimiento=" + perFecNacimiento + "]";
	}
	
	
	

}
