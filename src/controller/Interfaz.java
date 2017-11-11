/**
 * 
 */
package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.Cuenta;
import model.Ejecutivo;
import model.Juridico;
import model.Natural;

/**
 * @author Francisco Valdés
 *
 */
public class Interfaz {

	/**
	 * @param args
	 */
	public static void init(String[] args) {
		Cuenta inst_cuenta = new Cuenta();
		Ejecutivo inst_ejecutivo = new Ejecutivo();
		Natural inst_cuenta_natural = new Natural();
		Juridico inst_cuenta_juridica = new Juridico();
		
		String fecha_nacimiento = "";
		LocalDate today = LocalDate.now();	//Conseguir la fecha de hoy
		String inst_rut="",inst_nombre="",inst_apellido_p="",inst_apellido_m="",inst_nacionalidad="",inst_categoria="";
		
		String test_rut="123",test_nombre="PERSONA_PRUEBA",test_apellido_p="AP.PATER",test_apellido_m="AP.MATER",test_nacionalidad="NO_EXISTE",test_categoria="NORMAL";
		Object [] opciones=new Object[] {"1.- Utilizar Persona de Prueba", "2.- Crear Persona","3.- Salir"};
		
		Object seleccion = JOptionPane.showInputDialog(null, 	"No existen personas para utilizar en el sistema! Que desea hacer?", 
																"SISTEMA DE CONTROL DE CUENTAS BANCARIO", JOptionPane.WARNING_MESSAGE,null,opciones, 
																"1.-Persona de Prueba");

		switch (String.valueOf(seleccion).charAt(0)) {
		case '1':
			System.out.println(test_nombre);
			

		/*	if(perDao.ingresar(personita1)) {
				JOptionPane.showMessageDialog(null, "Persona ingresada con Exito");
			}else {
				JOptionPane.showMessageDialog(null, "Persona NO ingresada");
			}
			break;
*/
		}
	}
}
	
