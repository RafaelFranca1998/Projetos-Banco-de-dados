package br.main.window;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.DAO.Datasource;
import br.DAO.ProgramDAO;
import br.main.ProgramaAlunoDB;
import br.model.Cursos;
import br.model.ProjetoAluno;

public class EditWindow {
	private JFrame frameEdit = new JFrame("Editar");

	private JButton btnConfirmar = new JButton("Confirmar");
	private JButton btnCancelar = new JButton("Cancelar");

	private JLabel lblNomeEdit = new JLabel("Nome");
	private JLabel lblIdadeEdit = new JLabel("Idade");
	private JLabel lblCursoEdit = new JLabel("Curso");
	private JLabel lblTurnoEdit = new JLabel("Turno");

	private JTextField textFieldNomeEdit = new JTextField();

	private ProgramaAlunoDB gr = new ProgramaAlunoDB();
	private Choice choiceIdadeEdit = new Choice();
	private Choice choiceCursoEdit = new Choice();
	private Choice choiceTurnoEdit = new Choice();

	private ProjetoAluno BD = new ProjetoAluno();
	private Datasource ds;
	private ProgramDAO dao;

	private int identificação;
	private final JLabel lblSobrenome = new JLabel("Sobrenome");
	private final JTextField textFieldSobrenomeEdit = new JTextField();
	private final JLabel lblPreenchaTodosOs = new JLabel("Preencha Todos os Campos");
	static EditWindow instance;

	public EditWindow() {
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void abrir(int id) {
		gr.enableWindow(false);
		identificação = id;
		
		textFieldSobrenomeEdit.setBounds(94, 73, 147, 20);
		textFieldSobrenomeEdit.setColumns(10);
		choiceTurnoEdit.setSize(146, 20);
		choiceTurnoEdit.setLocation(95, 155);
		choiceCursoEdit.setSize(146, 20);
		choiceCursoEdit.setLocation(95, 129);
		choiceIdadeEdit.setBounds(118, 103, 89, 20);
		lblIdadeEdit.setBounds(24, 104, 77, 14);
		lblNomeEdit.setBounds(24, 49, 77, 14);
		btnCancelar.setBounds(192, 221, 89, 23);
		btnConfirmar.setBounds(44, 221, 105, 23);
		textFieldNomeEdit.setBounds(95, 46, 146, 20);
		textFieldNomeEdit.setColumns(10);
		lblCursoEdit.setBounds(24, 129, 46, 14);
		lblTurnoEdit.setBounds(24, 155, 46, 14);
		frameEdit.setBounds(100, 100, 326, 300);

		choiceIdadeEdit.add("");
		for (int i = 0; i < 110; i++) {
			choiceIdadeEdit.add(String.valueOf(i + 1));
		}


		choiceTurnoEdit.add("Matutino");
		choiceTurnoEdit.add("Vespertino");
		choiceTurnoEdit.add("Noturno");
		for (int i = 0; i < Cursos.getCursos().size(); i++) {
			choiceCursoEdit.add(Cursos.getCursos().get(i).toString());
		}

		frameEdit.getContentPane().add(lblCursoEdit);
		frameEdit.getContentPane().add(lblTurnoEdit);
		frameEdit.getContentPane().add(btnConfirmar);
		frameEdit.getContentPane().add(textFieldNomeEdit);
		frameEdit.getContentPane().add(lblNomeEdit);
		frameEdit.getContentPane().add(choiceIdadeEdit);
		frameEdit.getContentPane().add(choiceCursoEdit);
		frameEdit.getContentPane().add(choiceTurnoEdit);
		frameEdit.getContentPane().add(lblIdadeEdit);
		frameEdit.getContentPane().add(btnCancelar);
		frameEdit.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameEdit.setType(Type.POPUP);
		frameEdit.getContentPane().setLayout(null);
		lblSobrenome.setBounds(24, 74, 62, 14);

		frameEdit.getContentPane().add(lblSobrenome);

		frameEdit.getContentPane().add(textFieldSobrenomeEdit);
		lblPreenchaTodosOs.setForeground(Color.RED);
		lblPreenchaTodosOs.setBounds(24, 24, 155, 14);
		lblPreenchaTodosOs.setVisible(false);
		
		frameEdit.getContentPane().add(lblPreenchaTodosOs);
		frameEdit.setResizable(false);
		frameEdit.setVisible(true);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gr.enableWindow(true);
				close();
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ds = new Datasource();
				dao = new ProgramDAO(ds);
				BD.setId(identificação);
				try {
					BD.setNome(textFieldNomeEdit.getText());
					BD.setSobrenome(textFieldSobrenomeEdit.getText());
					BD.setIdade(Integer.parseInt(choiceIdadeEdit.getSelectedItem()));
					BD.setTurno(choiceTurnoEdit.getSelectedItem());
					BD.setCurso(choiceCursoEdit.getSelectedItem());
					dao.Update(BD);
					lblPreenchaTodosOs.setVisible(false);
					gr.enableWindow(true);
					gr.updateTable();
					close();
					
				} catch (Exception e2) {
					lblPreenchaTodosOs.setVisible(true);
					
				}
			}
		});
	}

	private void close() {
		frameEdit.setVisible(false);
	}
}
