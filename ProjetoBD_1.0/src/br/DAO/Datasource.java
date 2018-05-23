package br.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
	private String hostName;
	private int port;
	private String dataBase;
	private String userName;
	private String password;
	private Connection connection;

	public Datasource() {
		hostName = "localhost";
		port = 3306;
		dataBase = "bd_programabd";
		userName = "root";
		password = "32612421";

		try {
			String url = "jdbc:mysql://" + hostName + ":" + port + "/" + dataBase + "?useSSL=false";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Conexão Efetuada");
		} catch (SQLException e) {
			System.err.println("Não Foi Possivel Conectar ao Banco de dados: " + e);
		} catch (Exception e) {
			System.err.println("Não foi possivel conectar! ERRO GERAL: " + e);
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexão Encerrada!");
		} catch (SQLException e1) {
			System.err.println("Não fechou " + e1.getMessage());
		} catch (Exception e2) {
			System.err.println("ERRO GERAL:  " + e2.getMessage());
		}

	}
}
