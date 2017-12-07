package model;

/**
 * @author Francisco Valdés
 */
public class Juridico extends Cliente {
	String	jurRazSocial;

	public Juridico() {
	}

	public Juridico(String perRut, String perNombre, String perApePaterno, String perApeMaterno, String perNacionalidad,
			String perFecNacimiento, String cliCategoria, Ejecutivo eje, String jurRazSocial) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, perNacionalidad, perFecNacimiento, cliCategoria, eje);
		this.jurRazSocial = jurRazSocial;
	}

	public String getJurRazSocial() {
		return jurRazSocial;
	}

	public void setJurRazSocial(String jurRazSocial) {
		this.jurRazSocial = jurRazSocial;
	}

	
	@Override
	public String toString() {
		return "Juridico [jurRazSocial=" + jurRazSocial + ", cliCategoria=" + cliCategoria + ", eje=" + eje
				+ ", perRut=" + perRut + ", perNombre=" + perNombre + ", perApePaterno=" + perApePaterno
				+ ", perApeMaterno=" + perApeMaterno + ", perNacionalidad=" + perNacionalidad + ", perFecNacimiento="
				+ perFecNacimiento + "]";
	}

	

}
