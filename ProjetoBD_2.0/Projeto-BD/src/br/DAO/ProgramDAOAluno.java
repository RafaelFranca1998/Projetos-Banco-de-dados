package br.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.model.ProjetoAluno;

public class ProgramDAOAluno extends DAO {
	private Datasource dataSource;
	public Connection connection;
	public PreparedStatement stmt;

	// -------------------------------------------------------------------
	/**
	 * Cria uma nova instância da Classe BibliotecaDao.
	 * 
	 * @param datasource
	 *            os dados da conexão.
	 */
	public ProgramDAOAluno(Datasource ds) {
		this.dataSource = ds;
	}

	// -------------------------------------------------------------------
	/**
	 * Metodo que retorna um Arraylist contendo os dados da tabela no Banco de
	 * Dados.
	 * 
	 * @return Lista com ID, Nome, Tipo, endereco, arquivo e nome do arquivo.
	 */
	public ArrayList<ProjetoAluno> listAlunos() {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM alunos");
			ResultSet rs = ps.executeQuery();
			ArrayList<ProjetoAluno> Lista = new ArrayList<ProjetoAluno>();
			while (rs.next()) {
				ProjetoAluno BD = new ProjetoAluno();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setSobrenome(rs.getString("sobrenome"));
				BD.setIdade(rs.getInt("idade"));
				BD.setCurso(rs.getString("curso"));
				BD.setTurno(rs.getString("turno"));
				Lista.add(BD);
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} finally {
			dataSource.closeConnection();
		}
		return null;
	}
	// -------------------------------------------------------------------

	public ArrayList<ProjetoAluno> shortByCurso(String curso) {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM alunos WHERE curso = ?");
			ps.setString(1, curso);
			ResultSet rs = ps.executeQuery();
			ArrayList<ProjetoAluno> list = new ArrayList<ProjetoAluno>();
			while (rs.next()) {
				ProjetoAluno BD = new ProjetoAluno();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setSobrenome(rs.getString("sobrenome"));
				BD.setIdade(rs.getInt("idade"));
				BD.setCurso(rs.getString("curso"));
				BD.setTurno(rs.getString("turno"));
				list.add(BD);
			}
			return list;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} finally {
			dataSource.closeConnection();
		}
		return null;
	}

	/*
	 * public ArrayList<ProjetoAluno> shortById(int id) { try { PreparedStatement ps
	 * = dataSource.getConnection().
	 * prepareStatement("SELECT * FROM pessoas WHERE id = ?"); ps.setInt(1, id);
	 * ResultSet rs = ps.executeQuery(); ArrayList<ProjetoAluno> list = new
	 * ArrayList<ProjetoAluno>(); while (rs.next()) { ProjetoAluno BD = new
	 * ProjetoAluno(); BD.setId(rs.getInt("id")); BD.setNome(rs.getString("nome"));
	 * BD.setSobrenome(rs.getString("sobrenome")); BD.setIdade(rs.getInt("idade"));
	 * BD.setCurso(rs.getString("curso")); BD.setTurno(rs.getString("turno"));
	 * list.add(BD); } return list; } catch (SQLException e) {
	 * System.err.println("[ERRO!] Erro na Listagem " + e.getMessage()); } catch
	 * (Exception e) { System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
	 * }finally { dataSource.closeConnection(); } return null; }
	 */
	public ArrayList<ProjetoAluno> shortByName(String name) {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM alunos WHERE nome = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			ArrayList<ProjetoAluno> list = new ArrayList<ProjetoAluno>();
			while (rs.next()) {
				ProjetoAluno BD = new ProjetoAluno();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setSobrenome(rs.getString("sobrenome"));
				BD.setIdade(rs.getInt("idade"));
				BD.setCurso(rs.getString("curso"));
				BD.setTurno(rs.getString("turno"));
				list.add(BD);
			}
			return list;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} finally {
			dataSource.closeConnection();
		}
		return null;
	}

	public ArrayList<ProjetoAluno> shortByTurno(String turno) {
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM alunos WHERE turno = ?");
			ps.setString(1, turno);
			ResultSet rs = ps.executeQuery();
			ArrayList<ProjetoAluno> list = new ArrayList<ProjetoAluno>();
			while (rs.next()) {
				ProjetoAluno BD = new ProjetoAluno();
				BD.setId(rs.getInt("id"));
				BD.setNome(rs.getString("nome"));
				BD.setSobrenome(rs.getString("sobrenome"));
				BD.setIdade(rs.getInt("idade"));
				BD.setCurso(rs.getString("curso"));
				BD.setTurno(rs.getString("turno"));
				list.add(BD);
			}
			return list;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} finally {
			dataSource.closeConnection();
		}
		return null;
	}

	/**
	 * Insere dados no banco de dados.
	 * 
	 * @param U
	 *            os dados do usuário.
	 */
	public void create(ProjetoAluno U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("INSERT INTO alunos(id,nome,sobrenome,idade,curso,turno) VALUES (?,?,?,?,?,?)");
			stmp.setInt(1, U.getId());
			stmp.setString(2, U.getNome());
			stmp.setString(3, U.getSobrenome());
			stmp.setLong(4, U.getIdade());
			stmp.setString(5, U.getCurso());
			stmp.setString(6, U.getTurno());
			stmp.executeUpdate();
			System.out.println("[Log] Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				dataSource.closeConnection();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
			}
		}
	}

	public void Update(ProjetoAluno U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement(
					"UPDATE alunos SET nome = ?, sobrenome = ?, idade = ?, idadedia = ?, idademes = ?, idadeano = ?, curso = ?, turno = ? WHERE id = ?");
			stmp.setString(1, U.getNome());
			stmp.setString(2, U.getSobrenome());
			stmp.setInt(3, U.getIdade());
			stmp.setInt(4, U.getIdadedia());
			stmp.setInt(5, U.getIdademes());
			stmp.setInt(6, U.getIdadeano());
			stmp.setString(7, U.getCurso());
			stmp.setString(8, U.getTurno());
			stmp.setInt(9, U.getId());
			stmp.executeUpdate();
			System.out.println("[Log] Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				dataSource.closeConnection();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
			}
		}

	}

	// -------------------------------------------------------------------
	/**
	 * Deleta dados baseado no ID passado como parâmetro.
	 * 
	 * @param U
	 *            ID a ser Deletado.
	 */
	public void delete(ProjetoAluno U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("DELETE FROM alunos WHERE id = ?");
			stmp.setInt(1, U.getId());
			stmp.executeUpdate();
			System.out.println("[Log] Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				dataSource.closeConnection();
				con.close();
			} catch (SQLException e) {
				System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
