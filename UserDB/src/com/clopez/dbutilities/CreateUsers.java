package com.clopez.dbutilities;


import java.util.Scanner;


import com.clopez.userdb.User;
import com.clopez.userdb.dataBase;

public class CreateUsers {

	public static void main(String[] args) {
		dataBase db = new dataBase();
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		if (db.load("data.json") == 0) {
			System.out.println("No hay usuarios en la base de datos");
		} else {
			db.list();
		}
		
		while (true) {
			System.out.println("Username (Q para salir del programa) :");
			username = sc.next();
			if (username.equals("Q")) {
				System.out.println("Saliendo del programa");
				break;
			} else if (username.length()<3 || username.length()>25 || db.userCheck(username) != -1) {
				System.out.println("Nombre de usuario invalido");
				continue;
			}
			System.out.println("\nPassword: ");
			password = sc.next();
			if (password.length()<3 || password.length()>20) {
				System.out.println("La longitud de la contraseña es invalida");
				continue;
			}
			
			User u = new User(username, password);
			db.addUser(u);
		}
		
		sc.close();
		
		if (db.size()>0) {
			System.out.println("Escribiendo la BBDD");
			db.dump("data.json");
		} else {
			System.out.println("No hay usuarios que guardar en la base de datos");
		}
		
	}

}
