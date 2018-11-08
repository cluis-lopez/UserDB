package com.clopez.dbutilities;

import java.util.Scanner;

import com.clopez.userdb.User;
import com.clopez.userdb.dataBase;

public class CheckUser {

	public static void main(String[] args) {
		dataBase db = new dataBase();
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		if (db.load("data.json") == 0) {
			System.out.println("No hay usuarios en la base de datos");
			sc.close();
			return;
		}
		
		System.out.println("Username :");
		username = sc.next();
		
		int index = db.userCheck(username);
		
		if (index == -1) {
			System.out.println("El usuario no existe en la base de datos");
			sc.close();
			return;
		}
		
		User u = db.getUser(index);
		
		System.out.println("Password: ");
		password = sc.next();
		
		if (u.checkPassword(password))
			System.out.println("Password OK");
		else
			System.out.println("La password no es correcta");
		
		sc.close();

	}

}
