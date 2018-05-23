package br.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.model.BancodeDados;





public class ProgramDAO {
	private Datasource dataSource;
	public Connection connection;
	public PreparedStatement stmt;

	// -------------------------------------------------------------------
	/**
	 * Cria uma nova instância da Classe BibliotecaDao.
	 * @param datasource os dados da conexão.
	 */
	public ProgramDAO(Datasource ds) {
		this.dataSource = ds;
	}
	// -------------------------------------------------------------------
	/**
	 * Metodo que retorna um Arraylist contendo os dados da tabela no Banco de Dados.
	 * @return Lista com ID, Nome, Tipo, endereco, arquivo e nome do arquivo.
	 */
	public ArrayList<BancodeDados> bookList() {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM pessoas");
			ResultSet rs = ps.executeQuery();
			ArrayList<BancodeDados> Lista = new ArrayList<BancodeDados>();
			while (rs.next()) {
				BancodeDados BD = new BancodeDados();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setIdade(rs.getInt("idade"));
				Lista.add(BD);
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("ERRO GERAL: " + e.getMessage());
		}
		return null;
	}
	// -------------------------------------------------------------------
	/**
	 * Insere dados no banco de dados.
	 * @param U os dados do usuário.
	 */
	public void create(BancodeDados U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("INSERT INTO pessoas(id,nome,idade) VALUES (?,?,?)");
			stmp.setInt(1, U.getId());
			stmp.setString(2, U.getNome());
			stmp.setLong(3, U.getIdade());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Update(BancodeDados U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("	UPDATE pessoas SET nome = ? , idade = ? WHERE id = ?");
			stmp.setString(1, U.getNome());
			stmp.setLong(2, U.getIdade());
			stmp.setInt(3, U.getId());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	// -------------------------------------------------------------------
	/**
	 * Deleta dados baseado no ID passado como parâmetro.
	 * @param U ID a ser Deletado. 
	 */
	public void delete(BancodeDados U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("DELETE FROM pessoas WHERE id = ?");
			stmp.setInt(1, U.getId());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BancodeDados> selectTypeofBook(String type) {
		connection = dataSource.getConnection();
		stmt = null;
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM pessoas WHERE tipo = ?");
			ps.setString(1, "tipo1");
			ResultSet rs = ps.executeQuery();
			ArrayList<BancodeDados> Lista = new ArrayList<BancodeDados>();
			while (rs.next()) {
				BancodeDados BD = new BancodeDados();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setIdade(rs.getInt("idade"));
				Lista.add(BD);
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("ERRO GERAL: " + e.getMessage());
		}
		return null;
	}
	
	public Boolean checkLogin(int login, int senha) {
		Boolean check = false;
		try {
			PreparedStatement ps = dataSource.getConnection()
					.prepareStatement("SELECT * FROM pessoas WHERE login = ? AND senha = ?");
			ps.setInt(1, login);
			ps.setInt(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro Geral " + e.getMessage());
		}
		return check;
	}
}
