package model;

import model.Cliente;

/**
 * @author Francisco Valdés
 */
public class Cuenta {
	private int cueId;
	private int cueSaldo;
	private String cueFecApertura;
	private String cueEstado;
	private int cueSobregiro;
	private Cliente cliente;
	/**
	 * @param cliente
	 */
	public Cuenta(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @param cueId
	 * @param cueSaldo
	 * @param cueFecApertura
	 * @param cueEstado
	 * @param cueSobregiro
	 * @param cliente
	 */
	public Cuenta(int cueId, int cueSaldo, String cueFecApertura, String cueEstado, int cueSobregiro, Cliente cliente) {
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
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