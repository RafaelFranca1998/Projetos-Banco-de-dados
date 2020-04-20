package br.main.window;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.DAO.Datasource;
import br.DAO.ProgramDAOAluno;
import br.main.ProgramaAlunoDB;
import br.model.Cursos;
import br.model.Aluno;

public class EditWindow {

	static JFrame frameEdit = new JFrame("Editar");
	Aluno BD = new Aluno();
	Datasource ds;
	ProgramDAOAluno dao;
	int identificacao;
	Choice choiceIdadeDia = new Choice();
	Choice choiceCursoEdit = new Choice();
	Choice choiceTurnoEdit = new Choice();
	Choice choiceIdadeMes = new Choice();
	Choice choiceIdadeAno = new Choice();
	JTextField textFieldNomeEdit = new JTextField();

	public EditWindow() {

	}

	public static void initalize(int id) {
		EditWindow e = new EditWindow();
		e.abrir(id);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void abrir(int id) {
		identificacao = id;

		JButton btnConfirmar = new JButton("Confirmar");
		JButton btnCancelar = new JButton("Cancelar");

		JLabel lblNomeEdit = new JLabel("Nome");
		JLabel lblIdadeEdit = new JLabel("Idade");
		JLabel lblCursoEdit = new JLabel("Curso");
		JLabel lblTurnoEdit = new JLabel("Turno");
		JLabel lblIdadeAno = new JLabel("Ano");
		JLabel lblIdadeDia = new JLabel("Dia");

		final JLabel lblSobrenome = new JLabel("Sobrenome");
		final JTextField textFieldSobrenomeEdit = new JTextField();
		final JLabel lblPreenchaTodosOs = new JLabel("Preencha Todos os Campos");

		ProgramaAlunoDB.enableWindow(false);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		frameEdit.setBounds((d.width/2)-315,(d.height/2)-150, 330, 300);

		updateChoiceIdade();
		textFieldSobrenomeEdit.setBounds(95, 74, 190, 20);
		textFieldSobrenomeEdit.setColumns(10);
		choiceTurnoEdit.setSize(190, 20);
		choiceTurnoEdit.setLocation(95, 165);
		choiceCursoEdit.setSize(190, 20);
		choiceCursoEdit.setLocation(95, 140);
		choiceIdadeDia.setBounds(95, 115, 60, 20);
		lblIdadeEdit.setBounds(24, 115, 77, 14);
		lblNomeEdit.setBounds(24, 46, 77, 14);
		btnCancelar.setBounds(192, 221, 89, 23);
		btnConfirmar.setBounds(44, 221, 105, 23);
		textFieldNomeEdit.setBounds(95, 46, 190, 20);
		textFieldNomeEdit.setColumns(10);
		lblCursoEdit.setBounds(24, 140, 46, 14);
		lblTurnoEdit.setBounds(24, 165, 46, 14);

		choiceIdadeDia.add("");
		for (int i = 0; i < 110; i++) {
			choiceIdadeDia.add(String.valueOf(i + 1));
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
		frameEdit.getContentPane().add(choiceIdadeDia);
		frameEdit.getContentPane().add(choiceCursoEdit);
		frameEdit.getContentPane().add(choiceTurnoEdit);
		frameEdit.getContentPane().add(lblIdadeEdit);
		frameEdit.getContentPane().add(btnCancelar);
		frameEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameEdit.getContentPane().setLayout(null);
		frameEdit.getContentPane().add(lblSobrenome);
		frameEdit.getContentPane().add(textFieldSobrenomeEdit);
		frameEdit.getContentPane().add(lblPreenchaTodosOs);
		frameEdit.setResizable(false);
		frameEdit.setVisible(true);

		lblSobrenome.setBounds(24, 74, 62, 14);
		lblPreenchaTodosOs.setForeground(Color.RED);
		lblPreenchaTodosOs.setBounds(24, 24, 194, 14);

		choiceIdadeMes.setBounds(161, 115, 60, 20);
		frameEdit.getContentPane().add(choiceIdadeMes);

		choiceIdadeAno.setBounds(225, 115, 60, 20);
		frameEdit.getContentPane().add(choiceIdadeAno);

		lblIdadeAno.setBounds(225, 100, 46, 14);
		frameEdit.getContentPane().add(lblIdadeAno);

		lblIdadeDia.setBounds(95, 100, 46, 14);
		frameEdit.getContentPane().add(lblIdadeDia);

		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(161, 100, 46, 14);
		frameEdit.getContentPane().add(lblMs);
		lblPreenchaTodosOs.setVisible(false);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramaAlunoDB.enableWindow(true);
				close();
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ds = new Datasource();
				dao = new ProgramDAOAluno(ds);
				BD.setId(identificacao);
				try {
					BD.setNome(textFieldNomeEdit.getText());
					BD.setSobrenome(textFieldSobrenomeEdit.getText());
					BD.setIdade(Integer.parseInt(choiceIdadeDia.getSelectedItem()));
					BD.setTurno(choiceTurnoEdit.getSelectedItem());
					BD.setCurso(choiceCursoEdit.getSelectedItem());
					dao.Update(BD);
					lblPreenchaTodosOs.setVisible(false);

				} catch (Exception e2) {
					lblPreenchaTodosOs.setVisible(true);

				} finally {
					textFieldNomeEdit.setText("");
					textFieldSobrenomeEdit.setText("");
					ProgramaAlunoDB.enableWindow(true);
					close();
				}
			}
		});
	}

	void close() {
		frameEdit.setVisible(false);
	}

	void updateChoiceIdade() {
		choiceIdadeDia.removeAll();
		choiceIdadeMes.removeAll();
		choiceIdadeAno.removeAll();
		for (int i = 0; i < 30; i++) {
			choiceIdadeDia.add(String.valueOf(i + 1));
		}
		for (int i = 0; i < 12; i++) {
			choiceIdadeMes.add(String.valueOf(i + 1));
		}
		Calendar cal = Calendar.getInstance();
		int anoatual = cal.get(Calendar.YEAR);
		for (int i = anoatual; i > 1900; i--) {
			choiceIdadeAno.add(String.valueOf(i));
		}
	}
}
