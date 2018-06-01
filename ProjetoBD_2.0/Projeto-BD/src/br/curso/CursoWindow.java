package br.curso;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class CursoWindow {
	JButton btnExibirAlunosDo = new JButton("Exibir Alunos do Curso");
	

	private static JFrame frameCurso;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnDisciplinasObrigatrias;
	private JButton btnDisciplinasOptativas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CursoWindow window = new CursoWindow();
					window.frameCurso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CursoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCurso = new JFrame();
		frameCurso.setBounds(100, 100, 600, 400);
		frameCurso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCurso.setResizable(false);
		frameCurso.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 594, 289);
		frameCurso.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBounds(0, 0, 439, 87);
		scrollPane.setViewportView(table);
		
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID","Curso","Disciplinas Obrigatórias", "Disciplinas Optativas" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false,false};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		
		
		btnExibirAlunosDo.setBounds(399, 327, 175, 23);
		frameCurso.getContentPane().add(btnExibirAlunosDo);
		
		btnDisciplinasObrigatrias = new JButton("Disciplinas Obrigat\u00F3rias");
		btnDisciplinasObrigatrias.setBounds(201, 327, 188, 23);
		frameCurso.getContentPane().add(btnDisciplinasObrigatrias);
		
		btnDisciplinasOptativas = new JButton("Disciplinas Optativas");
		btnDisciplinasOptativas.setBounds(22, 327, 169, 23);
		frameCurso.getContentPane().add(btnDisciplinasOptativas);
		
	}
}
