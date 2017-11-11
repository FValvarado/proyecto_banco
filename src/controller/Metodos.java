package controller;

public class Metodos {

	public static int x = 1;
	public static int x_new = 1;

	/**
	 * @param x
	 * @param x_new
	 */
	public Metodos(int x, int x_new) {
		Metodos.x = x;
		Metodos.x_new = x_new;
	}
	
	public static void increaser() { //Función para numeros incrementarios, la cual utilizaremos para nombrar a nuestras instancias con numeros incrementarios.
		while (true) { 
			x = x + 1;
			x_new = x;
			break;
		}			
	}
}

