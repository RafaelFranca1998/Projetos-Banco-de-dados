package br.model;

import java.util.ArrayList;

public class Cursos {

	public static ArrayList<String> getCursos() {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("Administra��o");
		list.add("An�lise de Sistemas");
		list.add("Redes");
		list.add("Fisioterapia");
		list.add("Direito");
		list.add("Assistente Social");
		list.add("Fonoaudiologia");
		list.add("Biblioteconomia");
		return list;
	}
}
