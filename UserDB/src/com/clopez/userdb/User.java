package com.clopez.userdb;

import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private String hashedPassword;
	private String salt;
	
	public User(String username, String clearPasswd) {
		this.username = username;
		String temp[] = new String[2];
		try {
			temp = Encrypt.hashPasswd(clearPasswd);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error en la encriptacion");
			e.printStackTrace();
		}
		this.hashedPassword = temp[0];
		this.salt = temp[1];
	}
	
	public String toString() {
		return ("Username: "+username+"\nPassword: "+hashedPassword+"\nSalt: "+salt);
	}
	
	public String getName() {
		return username;
	}

	public boolean checkPassword(String clearPassword) {
		return Encrypt.checkPasswd(clearPassword, hashedPassword, salt);
	}
}
