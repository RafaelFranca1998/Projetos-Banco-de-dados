package br.model;

import java.util.ArrayList;

public class Cursos {
	int id;
	String curso;
	
	public Cursos() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public static ArrayList<String> getCursos() {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("Administração");
		list.add("Análise de Sistemas");
		list.add("Redes");
		list.add("Fisioterapia");
		list.add("Direito");
		list.add("Assistente Social");
		list.add("Fonoaudiologia");
		list.add("Biblioteconomia");
		return list;
	}
	
	
}
