package model;

/**
 * @author Francisco Valdés
 */
public class Natural extends Cliente {
	private int natPatrimonio;

	/**
	 * Constructor Vacio
	 */
	public Natural() { super();	}

	public Natural(String perRut, String perNombre, String perApePaterno, String perApeMaterno, String perNacionalidad,
			String perFecNacimiento, String cliCategoria, Ejecutivo eje, int natPatrimonio) {
		super(perRut, perNombre, perApePaterno, perApeMaterno, perNacionalidad, perFecNacimiento, cliCategoria, eje);
		this.natPatrimonio = natPatrimonio;
	}

	public int getNatPatrimonio() {
		return natPatrimonio;
	}

	public void setNatPatrimonio(int natPatrimonio) {
		this.natPatrimonio = natPatrimonio;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + natPatrimonio;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Natural other = (Natural) obj;
		if (perRut == null) {
			if (other.perRut != null)
				return false;
		} else if (!perRut.equals(other.perRut))
			return false;
		return true;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCLIENTE NATURAL ID°" + perRut + 
				"\nNOMBRE = " + perNombre + 
				"\nAPELLIDOS = " + perApePaterno + perApeMaterno +
				"\nNACIONALIDAD = " + perNacionalidad +
				"\nCATEGORIA = " +cliCategoria + 
				"\nFECHA DE NACIMIENTO = " + perFecNacimiento +
				"\nPATRIMONIO = " +natPatrimonio +
				"\nEJECUTIVO A CARGO = " + eje.getPerRut() +
				"\n____________________________________" ;
	}



	

	

}
