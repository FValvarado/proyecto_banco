/**
 * 
 */
package model;

/**
 * @author Francisco Valdés
 *version 1.0
 *Class Abstract Clase base para la persona, la cual se utilizará para dar atributos bases a los derivantes de esta.
 */
public abstract class Persona {
	protected String perRut;
	protected String perNombre;
	protected String perApePaterno;
	protected String perApeMaterno;
	protected String perNacionalidad;
	protected String perFecNacimiento;
	/**
	 * Constructor vacio
	 */
	public Persona() {
	}
	/**
	 * @param perRut Representa el Rut de la Persona
	 * @param perNombre	Representa el nombre de la persona
	 * @param perApePaterno representa el apellido paterno de la persona
	 * @param perApeMaterno representa el apellido materno de la persona 
	 * @param nacionalidad nacionalidad de la persona
	 * @param perFecNacimiento fecha de nacimiento de la persona
	 */
	public Persona(String perRut, String perNombre, String perApePaterno, String perApeMaterno, String nacionalidad,
			String perFecNacimiento) {
		this.perRut = perRut;
		this.perNombre = perNombre;
		this.perApePaterno = perApePaterno;
		this.perApeMaterno = perApeMaterno;
		this.perNacionalidad = nacionalidad;
		this.perFecNacimiento = perFecNacimiento;
	}
	
	public String getPerRut() {
		return perRut;
	}
	public void setPerRut(String perRut) {
		this.perRut = perRut;
	}
	public String getPerNombre() {
		return perNombre;
	}
	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}
	public String getPerApePaterno() {
		return perApePaterno;
	}
	public void setPerApePaterno(String perApePaterno) {
		this.perApePaterno = perApePaterno;
	}
	public String getPerApeMaterno() {
		return perApeMaterno;
	}
	public void setPerApeMaterno(String perApeMaterno) {
		this.perApeMaterno = perApeMaterno;
	}
	public String getPerNacionalidad() {
		return perNacionalidad;
	}
	public void setPerNacionalidad(String nacionalidad) {
		perNacionalidad = nacionalidad;
	}
	public String getPerFecNacimiento() {
		return perFecNacimiento;
	}
	public void setPerFecNacimiento(String perFecNacimiento) {
		this.perFecNacimiento = perFecNacimiento;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [perRut=" + perRut + ", perNombre=" + perNombre + ", perApePaterno=" + perApePaterno
				+ ", perApeMaterno=" + perApeMaterno + ", perNacionalidad=" + perNacionalidad + ", perFecNacimiento="
				+ perFecNacimiento + "]";
	}
	
	
	

}
