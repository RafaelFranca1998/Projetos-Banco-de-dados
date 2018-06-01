package br.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.model.Cursos;

public class ProgramDAOCursos extends DAO {
	private static Datasource dataSource;
	public Connection connection;
	
	public ProgramDAOCursos(Datasource ds) {
		ProgramDAOCursos.dataSource = ds;
	}
	
	public static ArrayList<Cursos> listarCursos() {
		try {
			
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM cursos");
			ResultSet rs = ps.executeQuery();
			int i = 0;
			ArrayList<Cursos> Lista = new ArrayList<Cursos>();
			while (rs.next()) {
				Cursos cs = new Cursos();
				cs.setId(rs.getInt("id"));
				cs.setCurso(rs.getString("curso"));
				Lista.add(cs);
				System.out.println(Lista.get(i));
				i++;
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		}finally {
			dataSource.closeConnection();
		}
	return null;
	}
}