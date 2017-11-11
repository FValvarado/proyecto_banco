/**
 * 
 */
package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Cuenta;
import model.Ejecutivo;
import model.Juridico;
import model.Natural;
import controller.Metodos;

/**
 * @author Francisco Valdés
 *
 */
public class Interfaz {

	/**
	 * @param args
	 */
	  	
	public static void init(String[] args) {
		
		Ejecutivo inst_ejecutivo = new Ejecutivo();
		Natural inst_cuenta_natural = new Natural();
		Juridico inst_cuenta_juridica = new Juridico();
		Cuenta 	inst_cuenta = new Cuenta();
		String fecha_nacimiento = "";
		LocalDate today = LocalDate.now();	//Conseguir la fecha de hoy
		String inst_rut="",inst_nombre="",inst_apellido_p="",inst_apellido_m="",inst_nacionalidad="",inst_categoria="";
		
		Object [] opciones=new Object[] {"1.- Utilizar Persona de Prueba", "2.- Crear Persona","3.- Salir"};
		
		System.out.println(Metodos.x);

		
		Object seleccion = JOptionPane.showInputDialog(null, 	"No existen personas para utilizar en el sistema! Que desea hacer?", 
																"SISTEMA DE CONTROL DE CUENTAS BANCARIO", JOptionPane.WARNING_MESSAGE,null,opciones, 
																"1.-Utilizar Persona de Prueba");

		switch (String.valueOf(seleccion).charAt(0)) {
		case '1': //Inserta datos ya existentes para su uso de prueba.
			inst_cuenta.setPerRut("19.735.818-1");
			inst_cuenta.setPerNombre("Francisco");
			inst_cuenta.setPerApePaterno("Valdés");
			inst_cuenta.setPerApeMaterno("Alvarado");
			inst_cuenta.setPerNacionalidad("Chileno");
			inst_cuenta.setCliCategoria("Normal");
			
			System.out.println(inst_cuenta);
			break;
		case'2':
			while(inst_rut.equals("")) {inst_rut=JOptionPane.showInputDialog("Ingrese el RUT de la Persona.");}
			inst_cuenta.setPerRut(inst_rut);
			while(inst_nombre.equals("")) {inst_nombre=JOptionPane.showInputDialog("Ingrese el NOMBRE de la Persona.");}
			inst_cuenta.setPerNombre(inst_nombre);
			while(inst_apellido_m.equals("")) {inst_apellido_m=JOptionPane.showInputDialog("Ingrese el APELLIDO MATERNO de la Persona.");}
			inst_cuenta.setPerApeMaterno(inst_apellido_m);
			while(inst_apellido_p.equals("")) {inst_apellido_p=JOptionPane.showInputDialog("Ingrese el APELLIDO PATERNO de la Persona.");}
			inst_cuenta.setPerApePaterno(inst_apellido_p);
			while(inst_nacionalidad.equals("")) {inst_nacionalidad=JOptionPane.showInputDialog("Ingrese la NACIONALIDAD de la Persona.");}
			inst_cuenta.setPerNacionalidad(inst_nacionalidad);
			System.out.println(inst_cuenta);
			break;
		case '3':
			System.exit(0);
			break;

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

	
