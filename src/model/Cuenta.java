/**
 * 
 */
package model;

/**
 * @author Francisco Valdés
 *version 1.0
 */
public class Cuenta extends Cliente{
	private int cueId;
	private int cueSaldo;
	private String cueFecApertura;
	private String cueEstado;
	private int cueSobregiro;
	private String cliente;
	/**
	 * @param cliente Instanciemos al cliente.
	 */
	public Cuenta() {
		super();
	}
	
	/**
	 * @param perRut
	 */
	public Cuenta(String perRut, String perNombre, String perApePaterno, String perApeMaterno, String nacionalidad,String perFecNacimiento, String cliCategoria, int cueId, int cueSaldo, String cueFecApertura,
			String cueEstado, int cueSobregiro, String cliente) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, nacionalidad, perFecNacimiento, cliCategoria);
		this.cueId = cueId;
		this.cueSaldo = cueSaldo;
		this.cueFecApertura = cueFecApertura;
		this.cueEstado = cueEstado;
		this.cueSobregiro = cueSobregiro;
		this.cliente = cliente;
	}

	public int getCueId() {
		return cueId;
	}

	public void setCueId(int cueId) {
		this.cueId = cueId;
	}

	public int getCueSaldo() {
		return cueSaldo;
	}

	public void setCueSaldo(int cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	public String getCueFecApertura() {
		return cueFecApertura;
	}

	public void setCueFecApertura(String cueFecApertura) {
		this.cueFecApertura = cueFecApertura;
	}

	public String getCueEstado() {
		return cueEstado;
	}

	public void setCueEstado(String cueEstado) {
		this.cueEstado = cueEstado;
	}

	public int getCueSobregiro() {
		return cueSobregiro;
	}

	public void setCueSobregiro(int cueSobregiro) {
		this.cueSobregiro = cueSobregiro;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuenta [cueId=" + cueId + ", cueSaldo=" + cueSaldo + ", cueFecApertura=" + cueFecApertura
				+ ", cueEstado=" + cueEstado + ", cueSobregiro=" + cueSobregiro + ", cliente=" + cliente + "]";
	}
	
	
	
	
}
