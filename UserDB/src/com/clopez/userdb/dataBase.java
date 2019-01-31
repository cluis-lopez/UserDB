package com.clopez.userdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class dataBase {
	private ArrayList<User> db;

	public dataBase() {
		super();
		db = new ArrayList<>();
	}

	public void addUser(User u) {
		db.add(u);
	}

	public int load(String filename) {
		RandomAccessFile fd;
		int result = 0;
		try {
			fd = new RandomAccessFile(filename, "r");
			result = (int) fd.length();
			if (result == 0) {
				fd.close();
				return result;
			}
			byte b[] = new byte[ (int) fd.length() ];
			fd.readFully(b);
			fd.close();
			Gson gson = new Gson();
			db = gson.fromJson(new String(b), new TypeToken<ArrayList<User>>() {}.getType());
		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero de usuarios");
			return result;
		} catch (IOException e) {
			System.err.println("Error de entrada salida");
			e.printStackTrace();
		} 
		return result;
	}

	public void dump(String file) {
		Gson gson = new Gson();
		String json = gson.toJson(db);
		try {
			RandomAccessFile fd = new RandomAccessFile(file, "rw");
			fd.writeBytes(json);
			fd.close();
		} catch (IOException e) {
			System.err.println("No se pueden volcar los datos en el fichero");
			e.printStackTrace();
		}
	}

	public void list() {
		for (User u: db) {
			System.out.println(u.toString());
		}
	}

	public int size() {
		return db.size();
	}
	
	/**
	 * @param user
	 * @return -1 if the user does not exists in the database, its index otherwise
	 */
	public int userCheck(String user) {
		for (int i=0; i< db.size(); i++) {
			if (db.get(i).getName().equals(user))
				return i;
		}
		return -1;
	}
	
	public User getUser(int i) {
		if (i<0 || i >= db.size())
			return null;
		return db.get(i);
	}
}
