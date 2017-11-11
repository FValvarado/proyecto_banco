/**
 * 
 */
package model;

/**
 * @author Francisco Valdés
 *
 */
public class Juridico extends Cliente {
	String	jurRazSocial;

	public Juridico() {
	}

	/**
	 * Obtenemos los valores de la clase Persona
	 */
	public Juridico(String perRut, 
			String perNombre,
			String perApePaterno, 
			String perApeMaterno, 
			String nacionalidad,
			String perFecNacimiento, 
			String cliCategoria,
			String jurRazSocial) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, nacionalidad, perFecNacimiento, cliCategoria);
		this.jurRazSocial = jurRazSocial;
	}
	
	

}
