package br.model;

public class Aluno {
	private Integer id;
	private Integer idade;
	private Integer idadedia;
	private Integer idademes;
	private Integer idadeano;
	
	private String nome,sobrenome, turno, curso;

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getIdadedia() {
		return idadedia;
	}

	public void setIdadedia(int idadedia) {
		this.idadedia = idadedia;
	}

	public int getIdademes() {
		return idademes;
	}

	public void setIdademes(int idademes) {
		this.idademes = idademes;
	}

	public int getIdadeano() {
		return idadeano;
	}

	public void setIdadeano(int idadeano) {
		this.idadeano = idadeano;
	}
	
}
