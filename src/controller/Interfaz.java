package controller;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import modelDao.ClienteDao;
import modelDao.CuentaDao;
import modelDao.EjecutivoDao;
import modelDao.JuridicoDao;
import model.Cuenta;
import model.Cliente;
import model.Ejecutivo;
import model.Juridico;
import model.Natural;
import modelDao.NaturalDao;

/**
 * @author Francisco Valdés
 */
public class Interfaz {

	public static void init(String[] args) {
		Scanner input = new Scanner(System.in);
		int option;

		do {

			System.out
					.println("SISTEMA DE MANEJO DE CUENTAS BANCARIAS" + "\n_____________" + "\n1.INGRESO CLIENTE/CUENTA"
							+ "\n2.MANEJO CUENTA" + "\n3.MANTENIMIENTO" + "\n4.SALIR" + "\n_____________");

			System.out.println("INGRESE UNA OPCIÓN");
			option = input.nextInt();

			// INGRESO DE CLIENTE/CUENTA
			if (option == 1) {
				System.out.println("INGRESO CLIENTE/CUENTA" + "\n_____________" + "\n1.NUEVO CLIENTE"
						+ "\n2.NUEVA CUENTA" + "\n_____________");
				System.out.println("INGRESE UNA OPCIÓN");
				option = input.nextInt();

				if (option == 1) {
					insert_Cliente();
				}

				else if (option == 2) {
					insert_Cuenta();
				}

				else {
					System.out.println("Opción Erronea! Volviendo al menu principal...");
				}

			}

			// MANEJO DE CUENTA
			else if (option == 2) {

				System.out.println("MANEJO CUENTA" + "\n_____________" + "\n1.REALIZAR GIRO" + "\n2.REALIZAR DEPOSITO"
						+ "\n_____________");

				System.out.println("INGRESE UNA OPCIÓN");
				option = input.nextInt();

				if (option == 1) {
					manage_out();
				} else if (option == 2) {
					manage_in();
				} else {
					System.out.println("Opción Erronea! Volviendo al menu principal...");
				}

			}

			// MANTENIMIENTO
			else if (option == 3) {

				System.out.println("MANTENIMIENTO" + "\n_____________" + "\n1.CLIENTE" + "\n2.CUENTA" + "\n3.EJECUTIVO"
						+ "\n_____________");
				System.out.println("INGRESE UNA OPCIÓN");
				option = input.nextInt();

				if (option == 1) {
					System.out.println("MANTENIMIENTO DE CLIENTES" + "\n_______________________"
							+ "\n1.ACTUALIZAR CLIENTE NATURAL" + "\n2.ACTUALIZAR CLIENTE JURÍDICO"
							+ "\n3.LISTAR CLIENTES" + "\n_______________________");
					System.out.println("INGRESE UNA OPCIÓN");
					option = input.nextInt();

					if (option == 1) {
						int natjur = 1;
						update_Cliente(natjur);
					}

					else if (option == 2) {
						int natjur = 2;
						update_Cliente(natjur);
					} else if (option == 3) {
						list_Cliente();
					}
				}

				else if (option == 2) {	//MANTENCION
					System.out.println("MANTENCION DE CUENTAS");
					System.out.println("1. ACTUALIZAR CUENTA");
					System.out.println("2. ELIMINAR CUENTA");
					System.out.println("3. BLOQUEAR CUENTA");
					System.out.println("4. BUSCAR CUENTA");
					System.out.println("5. LISTAR CUENTAS");

					option = input.nextInt();

					if (option == 1) {
						update_Cuenta();
					} else if (option == 2) {
						erase_Cuenta();
					} else if (option == 3) {
						block_Cuenta();
					} else if (option == 4) {
						search_Cuenta();
					} else if (option == 5) {
						list_Cuenta();
					} else {
						option = -1;
					}

				}

				else if (option == 3) {	//MANTENCION EJECUTIVOS
					System.out.println("MANTENCION DE EJECUTIVOS");
					System.out.println("1. ACTUALIZAR EJECUTIVO");
					System.out.println("2. ELIMINAR EJECUTIVO");
					System.out.println("3. BLOQUEAR EJECUTIVO");
					System.out.println("4. BUSCAR EJECUTIVO");
					System.out.println("5. LISTAR EJECUTIVO");

					option = input.nextInt();

					if (option == 1) {
						insert_Ejecutivo();
					} else if (option == 2) {
						update_Ejecutivo();
					} else if (option == 3) {
						list_Ejecutivo();
					} else {
						option = -1;
					}

				}

				else {
					System.out.println("Opción Erronea! Volviendo al menu principal...");
				}

			} else {
				option = -1; //TRIGGER PARA SALIR DEL LOOP
			}

		}

		while (option != -1);
		{
			input.close();
		}

	}

	// METODOS

	public static void insert_Cliente() {
		String rut = "", nom = "", ape = "", cat = "";
		int type = -1;
		Scanner input = new Scanner(System.in);

		while (rut.equals("")) {
			System.out.println("Ingrese el RUT del Cliente");
			rut = input.next();
		}

		while (nom.equals("")) {
			System.out.println("Ingrese el Nombre del Cliente");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.next();
			}
			nom = input.next();
		}

		while (ape.equals("")) {
			System.out.println("Ingrese los Apellidos del Cliente separado por espacios...");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.nextLine();
			}
			ape = input.nextLine();
		}

		while (cat.equals("")) {
			System.out.println("Cual es la categoria del cliente?" + "\n1.VIP" + "2.NORMAL" + "3.RIESGO");
			System.out.println("Elija una opción");

			int a = input.nextInt();

			if (a == 1) {
				cat = "VIP";
			} else if (a == 2) {
				cat = "NORMAL";
			} else if (a == 3) {
				cat = "RIESGO";
			} else {
				cat = "";
			}
		}

		if (type == -1) {
			int a;
			System.out.println("Que tipo de cliente es?" + "\n1.Natural" + "2.Juridico");
			System.out.println("Elija una opción");

			a = input.nextInt();

			if (a == 1) {
				Natural nat = new Natural();
				NaturalDao natdao = new NaturalDao();

				int pat = -1;
				while (pat == -1) {
					System.out.println("Ingrese el Patrimonio del Cliente.");
					pat = input.nextInt();
				}

				nat.setPerRut(rut);
				nat.setPerNombre(nom);
				nat.setPerApellido(ape);
				nat.setCliCategoria(cat);
				nat.setNatPatrimonio(pat);
				nat.toString();

				System.out.println(nat.toString());

				if (natdao.insert(nat)) {
					System.out.println("Cliente juridico ingresado satisfactoriamente!");
				} else {

					System.out.println("Error al ingresar");
				}

			} else if (a == 2) {
				Juridico jur = new Juridico();
				JuridicoDao jurdao = new JuridicoDao();

				String raz = "";
				while (raz.equals("")) {
					System.out.println("Ingrese la Razón Social del Cliente.");
					raz = input.nextLine();
				}

				jur.setPerRut(rut);
				jur.setPerNombre(nom);
				jur.setPerApellido(ape);
				jur.setJurRazSocial(raz);
				jur.setCliCategoria(cat);
				jur.toString();

				System.out.println(jur.toString());

				if (jurdao.insert(jur)) {
					System.out.println("Cliente natural ingresado satisfactoriamente!");
				} else {

					System.out.println("Error al ingresar");
				}

			}

		}
		input.close();
	}

	public static void insert_Cuenta() {
		String rut = "";
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		ClienteDao clidao = new ClienteDao();
		System.out.print("Por favor, ingrese el RUT del Cliente el cual será asociado a esta Cuenta.");
		rut = input.next();
		cli.setPerRut(rut);
		clidao.exists(cli);
		if (clidao.exists(cli)) {
			System.out.println("El Cliente existe, se asociará...");
			clidao.search(cli);
			Cuenta cue = new Cuenta(cli);
			CuentaDao cuedao = new CuentaDao();

			System.out.println("Indique el saldo correspondiente a la cuenta.");
			cue.setCueSaldo(input.nextInt());

			if (cli.getCliCategoria().equals("RIESGO")) {
				System.out.println("El cliente se encuentra en categoría de riesgo, no se permitirá sobregiro...");
				cue.setCueSobregiro(0);
			}

			else {
				System.out.println("Desea activar el sobregiro para esta cuenta? 1=Si,2=No");
				int op = input.nextInt();
				if (op == 1) {
					cue.setCueSobregiro(1);
					System.out.println("Sobregiro activado...");
				} else {
					cue.setCueSobregiro(0);
					System.out.println("Sobregiro desactivado...");
				}
			}
			cuedao.insert(cue);
		} else if (!clidao.exists(cli)) {
			System.out.println("El cliente no existe. Volviendo al menu principal.");
		}
		input.close();
	}

	public static void manage_out() {
		int id = -1;
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		Cuenta cue = new Cuenta(cli);
		CuentaDao cuedao = new CuentaDao();
		System.out.println("Ingrese la ID de la Cuenta de la cual realizar un giro...");
		id = input.nextInt();
		cue.setCueId(id);
		cuedao = new CuentaDao();
		cuedao.search_id(cue);
		if (cue.getCueSobregiro() == 1) {
			System.out.println("Digite el dinero a girar.");
			System.out.println("SALDO: " + cue.getCueSaldo());
			int minus = input.nextInt();
			cue.setCueSaldo(cue.getCueSaldo() - minus);
			System.out.println("SU NUEVO SALDO ES = " + cue.getCueSaldo());
			if (cuedao.updateTransaction(cue)) {
				System.out.println("Operación realizada con exito.");
			}
		} else {
			System.out.println("Digite el dinero a girar. Este no puede ser mayor a su saldo.");
			System.out.println("SALDO: " + cue.getCueSaldo());
			int minus = input.nextInt();
			cue.setCueSaldo(cue.getCueSaldo() - minus);
			if (cue.getCueSaldo() - minus > 0) {
				System.out.println("SU NUEVO SALDO ES = " + cue.getCueSaldo());
				if (cuedao.updateTransaction(cue)) {
					System.out.println("Operación realizada con exito.");
				}
			} else {
				System.out.println("No tiene saldo suficiente para realizar esta transacción.");
			}
		}
		input.close();
	}

	public static void manage_in() {
		int id = -1;
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		CuentaDao cuedao = new CuentaDao();
		System.out.println("Ingrese la ID de la Cuenta de la cual realizar un giro...");
		id = input.nextInt();
		Cuenta cue = new Cuenta(cli);
		cue.setCueId(id);
		cuedao = new CuentaDao();
		cuedao.search_id(cue);
		if (cue.getCueEstado().equals("vigente")) {
			System.out.println("Digite el dinero a depositar.");
			System.out.println("SALDO: " + cue.getCueSaldo());
			int plus = input.nextInt();
			cue.setCueSaldo(cue.getCueSaldo() + plus);
			System.out.println("SU NUEVO SALDO ES = " + cue.getCueSaldo());
			if (cuedao.updateTransaction(cue)) {
				System.out.println("Operación realizada con exito.");
			}
		} else {
			System.out.println(
					"La transacción no puede ser realizada. Su cuenta se encuentra en estado: " + cue.getCueEstado());
			System.out.println("Por favor contactarse con su ejecutivo.");
		}
		input.close();
	}

	public static void update_Cliente(int natjur) {

		if (natjur == 1) { // Actualizar Natural!
			String rut = "", nom = "", ape = "", cat = "", nac = "", fec = "";
			Ejecutivo eje = new Ejecutivo();
			Natural nat = new Natural();
			NaturalDao natdao = new NaturalDao();
			Scanner input = new Scanner(System.in);

			while (rut.equals("")) {
				System.out.println("Ingrese el RUT del Cliente");
				rut = input.next();
			}

			while (nom.equals("")) {
				System.out.println("Ingrese el Nombre del Cliente");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.next();
				}
				nom = input.next();
			}

			while (ape.equals("")) {
				System.out.println("Ingrese los Apellidos del Cliente separado por espacios...");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.nextLine();
				}
				ape = input.nextLine();
			}

			while (cat.equals("")) {
				System.out.println("Cual es la categoria del cliente?" + "\n1.VIP" + "2.NORMAL" + "3.RIESGO");
				System.out.println("Elija una opción");
				int a = input.nextInt();

				if (a == 1) {
					cat = "VIP";
				} else if (a == 2) {
					cat = "NORMAL";
				} else if (a == 3) {
					cat = "RIESGO";
				} else {
					cat = "";
				}
			}

			while (nac.equals("")) {
				System.out.println("Ingrese la Nacionalidad del Cliente");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.next();
				}
				nac = input.next();
			}

			while (fec.equals("")) {
				System.out.println("Ingrese la Fecha de Nacimiento del Cliente en formato XXXX-XX-XX [AÑO-MES-DIA]");
				fec = input.next();
			}

			int pat = -1;
			while (pat == -1) {
				System.out.println("Ingrese el Patrimonio del Cliente.");
				pat = input.nextInt();
			}

			System.out.println("Ingrese el RUT del ejecutivo a cargo del Cliente");
			String ejei = input.next();
			eje.setPerRut(ejei);

			nat.setPerRut(rut);
			nat.setPerNombre(nom);
			nat.setPerApellido(ape);
			nat.setPerNacionalidad(nac);
			nat.setPerFecNacimiento(fec);
			nat.setCliCategoria(cat);
			nat.setEje(eje);
			nat.setNatPatrimonio(pat);
			if (natdao.update(nat)) {
				System.out.println("Se realizó Exitosamente la Actualización");
			} else {
				System.out.println("No fue posible actualizar al cliente.");
			}
			input.close();
		}

		if (natjur == 2) { // Actualizar Juridico!
			String rut = "", nom = "", ape = "", cat = "", nac = "", fec = "", raz = "";
			Ejecutivo eje = new Ejecutivo();
			Juridico jur = new Juridico();
			JuridicoDao jurdao = new JuridicoDao();
			Scanner input = new Scanner(System.in);

			while (rut.equals("")) {
				System.out.println("Ingrese el RUT del Cliente");
				rut = input.next();
			}

			while (nom.equals("")) {
				System.out.println("Ingrese el Nombre del Cliente");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.next();
				}
				nom = input.next();
			}

			while (ape.equals("")) {
				System.out.println("Ingrese los Apellidos del Cliente separado por espacios...");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.nextLine();
				}
				ape = input.nextLine();
			}

			while (cat.equals("")) {
				System.out.println("Cual es la categoria del cliente?" + "\n1.VIP" + "2.NORMAL" + "3.RIESGO");
				System.out.println("Elija una opción");
				int a = input.nextInt();

				if (a == 1) {
					cat = "VIP";
				} else if (a == 2) {
					cat = "NORMAL";
				} else if (a == 3) {
					cat = "RIESGO";
				} else {
					cat = "";
				}
			}

			while (nac.equals("")) {
				System.out.println("Ingrese la Nacionalidad del Cliente");
				while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
					System.out.println("Por favor, ingrese algo valido (solo letras)");
					input.next();
				}
				nac = input.next();
			}

			while (fec.equals("")) {
				System.out.println("Ingrese la Fecha de Nacimiento del Cliente en formato XXXX-XX-XX [AÑO-MES-DIA]");
				fec = input.next();
			}

			while (raz.equals("")) {
				System.out.println("Ingrese la Razon Social del Cliente.");
				raz = input.next();
			}

			System.out.println("Ingrese el RUT del ejecutivo a cargo del Cliente");
			String ejei = input.next();
			eje.setPerRut(ejei);

			jur.setPerRut(rut);
			jur.setPerNombre(nom);
			jur.setPerApellido(ape);
			jur.setPerNacionalidad(nac);
			jur.setPerFecNacimiento(fec);
			jur.setCliCategoria(cat);
			jur.setEje(eje);
			jur.setJurRazSocial(raz);
			if (jurdao.update(jur)) {
				System.out.println("Se realizó Exitosamente la Actualización");
			} else {
				System.out.println("No fue posible actualizar al cliente.");
			}
			input.close();
		}

	}

	public static void list_Cliente() {
		Scanner input = new Scanner(System.in);
		int option = -1;

		System.out.println("LISTAR");
		System.out.println("\n1.Clientes Naturales");
		System.out.println("\n2.Clientes Juridicos");
		System.out.println("\n3.Todos");

		option = input.nextInt();

		if (option == 1) {
			NaturalDao natdao = new NaturalDao();
			ArrayList<Natural> natulares = new ArrayList<>();
			natulares = natdao.list();
			for (Natural n1 : natulares) {
				System.out.println(n1.toString());
			}
		} else if (option == 2) {
			JuridicoDao jurdao = new JuridicoDao();
			ArrayList<Juridico> juridicos = new ArrayList<>();
			juridicos = jurdao.list();
			for (Juridico j1 : juridicos) {
				System.out.println(j1.toString());
			}
		}

		else {
			ClienteDao clidao = new ClienteDao();
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clidao.list();
			for (Cliente c1 : clientes) {
				System.out.println(c1.toString());
			}
		}
		input.close();
	}

	public static void update_Cuenta() {

		int id = -1, saldo = -1, sobregiro = 0;
		String es = "";
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		Cuenta cue = new Cuenta(cli);
		CuentaDao cuedao = new CuentaDao();
		ClienteDao clidao = new ClienteDao();
		System.out.print("Por favor, ingrese el RUT del Cliente el cual será asociado a esta Cuenta.");
		clidao.exists(cli);
		if (clidao.exists(cli)) {
			System.out.println("Cliente encontrado...");
			cue = new Cuenta(id, saldo, null, es, sobregiro, cli);
			System.out.println("Ingrese la ID de la Cuenta de la cual realizar un giro...");
			id = input.nextInt();
			cue.setCueId(id);
			System.out.println("Indique el saldo correspondiente a la cuenta.");
			cue.setCueSaldo(input.nextInt());
			System.out.println("Desea activar el sobregiro para esta cuenta? 1=Si,2=No");
			int op = input.nextInt();
			if (op == 1) {
				cue.setCueSobregiro(1);
				System.out.println("Sobregiro activado...");
			} else {
				cue.setCueSobregiro(0);
				System.out.println("Sobregiro desactivado...");
			}
			cuedao = new CuentaDao();
			if (cuedao.update(cue)) {
				System.out.println("Cuenta actualizada satisfactoriamente...");
			}
		} else if (!clidao.exists(cli)) {
			System.out.println("El cliente no existe. Volviendo al menu principal.");
		}
		input.close();
	}

	public static void erase_Cuenta() {
		int id = -1;
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		Cuenta cue = new Cuenta(cli);
		CuentaDao cuedao = new CuentaDao();
		System.out.println("Indique el Id de Cuenta a Eliminar");
		id = input.nextInt();
		cue.setCueId(id);
		cli = new Cliente();
		cue = new Cuenta(cli);
		cue.setCueId(id);
		cuedao = new CuentaDao();
		if (cuedao.delete(cue)) {
			System.out.println("Cuenta eliminada satisfactoriamente...");
		}
		input.close();
	}

	public static void block_Cuenta() {
		int id = -1;
		Scanner input = new Scanner(System.in);
		Cliente cli = new Cliente();
		Cuenta cue = new Cuenta(cli);
		CuentaDao cuedao = new CuentaDao();
		System.out.println("Indique el Id de Cuenta a Eliminar");
		id = input.nextInt();
		cue.setCueId(id);
		cli = new Cliente();
		cue = new Cuenta(cli);
		cue.setCueId(id);
		cuedao = new CuentaDao();
		if (cuedao.block(cue)) {
			System.out.println("Cuenta bloqueada satisfactoriamente...");
		}
		input.close();
	}

	public static void search_Cuenta() {
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el RUT del Cliente a buscar...");
		String rut = input.next();

		Cliente cli = new Cliente();
		cli.setPerRut(rut);
		ArrayList<Cuenta> bcuentas = new ArrayList<>();
		CuentaDao cuedao = new CuentaDao();
		Cuenta cue = new Cuenta(cli);
		cuedao.search(cue);
		for (Cuenta cue1 : bcuentas) {
			System.out.println(cue1.toString());
		}
		input.close();
	}

	public static void list_Cuenta() {
		Scanner input = new Scanner(System.in);
		int option = -1;
		CuentaDao cuedao = new CuentaDao();

		System.out.println("LISTAR");
		System.out.println("\n1.Cuentas Naturales");
		System.out.println("\n2.Cuentas Juridicas");
		System.out.println("\n3.Todas");

		option = input.nextInt();

		if (option == 1) {
			ArrayList<Cuenta> cuentasN = new ArrayList<>();
			cuentasN = cuedao.list_Natural();
			for (Cuenta cue : cuentasN) {
				System.out.println(cue.toString());
			}
			input.close();
		} else if (option == 2) {
			ArrayList<Cuenta> cuentasJ = new ArrayList<>();
			cuentasJ = cuedao.list_Juridico();
			for (Cuenta cue : cuentasJ) {
				System.out.println(cue.toString());
			}
			input.close();
		}

		else {
			ArrayList<Cuenta> cuentas = new ArrayList<>();
			cuentas = cuedao.list_Cuenta();
			for (Cuenta cue : cuentas) {
				System.out.println(cue.toString());
			}
		}
		input.close();
	}

	public static void insert_Ejecutivo() {
		String rut = "", nom = "", ape = "", fec = "", fecn = "";
		Scanner input = new Scanner(System.in);
		Ejecutivo eje = new Ejecutivo();
		EjecutivoDao ejedao = new EjecutivoDao();

		while (rut.equals("")) {
			System.out.println("Ingrese el RUT del Ejecutivo");
			rut = input.next();
		}

		while (nom.equals("")) {
			System.out.println("Ingrese el Nombre del Ejecutivo");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.next();
			}
			nom = input.next();
		}

		while (ape.equals("")) {
			System.out.println("Ingrese los Apellidos del Ejecutivo separado por espacios...");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.nextLine();
			}
			ape = input.nextLine();
		}
		while (fecn.equals("")) {
			System.out.println("Ingrese la Fecha de Nacimiento del Ejecutivo en formato XXXX-XX-XX [AÑO, MES, FECHA]");
			fecn = input.next();
		}
		while (fec.equals("")) {
			System.out.println("Ingrese la Fecha de Contrato del Ejecutivo en formato XXXX-XX-XX [AÑO, MES, FECHA]");
			fec = input.next();
		}

		eje.setPerRut(rut);
		eje.setPerNombre(nom);
		eje.setPerApellido(ape);
		eje.setEjeFecContrato(fec);
		eje.setPerFecNacimiento(fecn);
		if (ejedao.insert(eje)) {
			JOptionPane.showMessageDialog(null, "Ejecutivo ingresado con exito");
		} else {

			System.out.println("Error al ingresar Ejecutivo");
		}
		input.close();
	}

	public static void update_Ejecutivo() {
		String rut = "", nom = "", ape = "", fec = "", fecn = "";
		Scanner input = new Scanner(System.in);
		Ejecutivo eje = new Ejecutivo();
		EjecutivoDao ejedao = new EjecutivoDao();

		while (rut.equals("")) {
			System.out.println("Ingrese el RUT del Ejecutivo");
			rut = input.next();
		}

		while (nom.equals("")) {
			System.out.println("Ingrese el Nombre del Ejecutivo");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.next();
			}
			nom = input.next();
		}

		while (ape.equals("")) {
			System.out.println("Ingrese los Apellidos del Ejecutivo separado por espacios...");
			while (!input.hasNext("[A-Za-z]+") || input.hasNext("")) { // Comprobar solo letras o vacio..
				System.out.println("Por favor, ingrese algo valido (solo letras)");
				input.nextLine();
			}
			ape = input.nextLine();
		}
		while (fecn.equals("")) {
			System.out.println("Ingrese la Fecha de Nacimiento del Ejecutivo en formato XXXX-XX-XX [AÑO, MES, FECHA]");
			fecn = input.next();
		}
		while (fec.equals("")) {
			System.out.println("Ingrese la Fecha de Contrato del Ejecutivo en formato XXXX-XX-XX [AÑO, MES, FECHA]");
			fec = input.next();
		}

		eje.setPerRut(rut);
		eje.setPerNombre(nom);
		eje.setPerApellido(ape);
		eje.setEjeFecContrato(fec);
		eje.setPerFecNacimiento(fecn);
		if (ejedao.update(eje)) {
			System.out.println("Ejecutivo actualizado con exito");
		} else {

			System.out.println("Error al actualizar Ejecutivo");
		}
		input.close();
	}

	public static void list_Ejecutivo() {
		EjecutivoDao ejedao1 = new EjecutivoDao();
		ArrayList<Ejecutivo> ejecutivos = new ArrayList<>();
		ejecutivos = ejedao1.list();
		for (Ejecutivo e1 : ejecutivos) {
			System.out.println(e1.toString());
		}
	}

}
