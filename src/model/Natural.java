/**
 * 
 */
package model;

/**
 * @author Francisco Valdés
 *
 */
public class Natural extends Cliente {
	private String natPatrimonio;

	/**
	 * Constructor Vacio
	 */
	public Natural() {	}

	/**
	 * Se obtienen los valores de la clase persona
	 */
	public Natural(String perRut, 
			String perNombre, 
			String perApePaterno, 
			String perApeMaterno, 
			String perNacionalidad,
			String perFecNacimiento, 
			String cliCategoria, 
			String natPatrimonio) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, perNacionalidad, perFecNacimiento, cliCategoria);
		this.natPatrimonio = natPatrimonio;
	}

	public String getNatPatrimonio() {
		return natPatrimonio;
	}

	public void setNatPatrimonio(String natPatrimonio) {
		this.natPatrimonio = natPatrimonio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Natural [natPatrimonio=" + natPatrimonio + ", cliCategoria=" + cliCategoria + ", perRut=" + perRut
				+ ", perNombre=" + perNombre + ", perApePaterno=" + perApePaterno + ", perApeMaterno=" + perApeMaterno
				+ ", perNacionalidad=" + perNacionalidad + ", perFecNacimiento=" + perFecNacimiento + "]";
	}
	

}
