package br.main;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.DAO.Datasource;
import br.DAO.ProgramDAO;
import br.main.window.EditWindow;
import br.model.Cursos;
import br.model.ProjetoAluno;

public class ProgramaAlunoDB {
	private static JFrame frmGerenciaDB = new JFrame();

	private TextField textFieldName = new TextField();
	private TextField textFieldPesquisa = new TextField();
	private TextField textFieldSobrenome = new TextField();

	private JTable tableResultado = new JTable();

	public JScrollPane scrollPane = new JScrollPane();

	private Label lblPesquisar = new Label("Pesquisar");
	private JLabel lblName = new JLabel("Nome");
	private JLabel lblTurno = new JLabel("Turno");
	private JLabel lblFiltroCurso = new JLabel("Curso");
	private JLabel lblCurso = new JLabel("Curso");
	private JLabel lblNewLabel = new JLabel("Pesquisar Nome");
	private JLabel lblIdade = new JLabel("Idade");
	private JLabel lblIdadeWarnning = new JLabel("Escolha uma Idade");
	private JLabel lblFiltroTurno = new JLabel("Turno");
	private JLabel lblFiltrosDePesquisa = new JLabel("Filtros de Pesquisa");
	private JLabel lblSobrenome = new JLabel("Sobrenome");

	private JButton btnRemover = new JButton("Remover");
	private JButton btnAdicionar = new JButton("Inserir");
	private JButton btnEditar = new JButton("Editar");

	private JPanel panelPesquisar = new JPanel();
	private JPanel panelInserir = new JPanel();

	private Choice choiceIdade = new Choice();
	private Choice choiceTurno = new Choice();
	private Choice choiceCurso = new Choice();
	private Choice choiceCurso2 = new Choice();

	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnInicio = new JMenu("Inicio");
	private JMenu mnAjuda = new JMenu("Ajuda");

	private JMenuItem mntmSair = new JMenuItem("Sair");
	private JMenuItem mntmRemover = new JMenuItem("Remover");
	private JMenuItem mntmSobre = new JMenuItem("Sobre...");
	private JMenuItem mntmEditar = new JMenuItem("Editar");

	private JCheckBox chckbxMatutino = new JCheckBox("Matutino");
	private JCheckBox chckbxVespertino = new JCheckBox("Vespertino");
	private JCheckBox chckbxNoturno = new JCheckBox("Noturno");

	private Datasource ds;
	private ProgramDAO dao;
	private ProjetoAluno BD;
	private int id = -1;
	private boolean updated, clicked1, clicked2, clicked3;

	private JPopupMenu popupMenu = new JPopupMenu();
	private Label lblAdicionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ProgramaAlunoDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProgramaAlunoDB() {
		textFieldSobrenome.setBounds(20, 125, 326, 20);
		textFieldSobrenome.setColumns(10);
		initialize();
		updateTable();
	}

	/**
	 * Preenche a tabela com dados e atualiza.
	 */
	public void updateTable() {
		ds = new Datasource();
		dao = new ProgramDAO(ds);
		BD = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
		for (ProjetoAluno BD2 : dao.listAlunos()) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

	public void updateByCurso(String curso) {
		ds = new Datasource();
		dao = new ProgramDAO(ds);
		BD = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
		for (ProjetoAluno BD2 : dao.shortByCurso(curso)) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

/*	private void updateByTurno(int turno) {
		ds = new Datasource();
		dao = new ProgramDAO(ds);
		BD = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);

		for (ProjetoAluno BD2 : dao.shortByTurno("Matutino")) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}
*/
	private void updateByName(String name) {
		ds = new Datasource();
		dao = new ProgramDAO(ds);
		BD = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);

		for (ProjetoAluno BD2 : dao.shortByName(name)) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

	private void updateChoiceIdade() {
		choiceIdade.removeAll();
		choiceIdade.add("");
		for (int i = 0; i < 110; i++) {
			choiceIdade.add(String.valueOf(i + 1));
		}
	}

	public void enableWindow(boolean b) {
		frmGerenciaDB.setEnabled(b);
		updateTable();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		updateChoiceIdade();
		frmGerenciaDB.getContentPane().setBackground(Color.WHITE);

		frmGerenciaDB.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmGerenciaDB.setBackground(Color.BLACK);

		frmGerenciaDB.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rafael_Cruz\\Desktop\\Programa\\download.png"));
		frmGerenciaDB.setBounds(100, 100, 1060, 600);

		final JPanel panelInicio = new JPanel();
		panelInicio.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInicio.setBackground(Color.WHITE);
		panelInicio.setBounds(671, 60, 355, 422);
		frmGerenciaDB.getContentPane().add(panelInicio);
		panelInicio.setLayout(null);

		JLabel lblInformaesDoAluno = new JLabel("Informa\u00E7\u00F5es do Aluno");
		lblInformaesDoAluno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInformaesDoAluno.setBounds(22, 11, 176, 31);
		panelInicio.add(lblInformaesDoAluno);

		TextArea textArea = new TextArea();
		textArea.setBounds(10, 74, 335, 315);
		panelInicio.add(textArea);
		frmGerenciaDB.getContentPane().add(btnRemover);
		frmGerenciaDB.getContentPane().add(btnAdicionar);
		frmGerenciaDB.getContentPane().add(scrollPane);
		frmGerenciaDB.getContentPane().add(btnEditar);
		frmGerenciaDB.getContentPane().setLayout(null);
		frmGerenciaDB.setResizable(false);
		frmGerenciaDB.setTitle("Programa Aluno");
		frmGerenciaDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (tableResultado.getCellSelectionEnabled()) {
			btnRemover.setEnabled(true);
		}
		scrollPane.setBounds(209, 27, 441, 455);
		btnEditar.setBounds(268, 497, 89, 23);
		btnRemover.setBounds(523, 496, 89, 24);
		btnAdicionar.setBounds(822, 496, 89, 24);
		btnAdicionar.setVisible(false);
		;

		btnAdicionar.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnRemover.setBackground(UIManager.getColor("Button.disabledShadow"));

		btnRemover.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					ds = new Datasource();
					dao = new ProgramDAO(ds);
					Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0);
					JOptionPane pane = new JOptionPane();
					if (pane.showConfirmDialog(tableResultado,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Livro da Lista?", "Atenção!",
							pane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						int id = Integer.parseInt(coluna.toString());

						System.out.println("[Log] ID= " + id + pane.YES_OPTION);
						BD.setId(id);
						dao.delete(BD);
						updateTable();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(tableResultado, "Selecione uma pessoa:");
				}
			}
		});

		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRemover.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRemover.setBackground(UIManager.getColor("Button.disabledShadow"));
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds = new Datasource();
				dao = new ProgramDAO(ds);
				if (choiceIdade.getSelectedItem().toString().equals("")) {
					lblIdadeWarnning.setVisible(true);

				} else {
					try {
						BD.setNome(textFieldName.getText());
						BD.setSobrenome(textFieldSobrenome.getText());
						BD.setIdade(Integer.parseInt(choiceIdade.getSelectedItem().toString()));
						BD.setCurso(choiceCurso.getSelectedItem().toString());
						System.out.println(choiceCurso.getSelectedItem().toString());
						BD.setTurno(choiceTurno.getSelectedItem().toString());
						dao.create(BD);
						JOptionPane.showMessageDialog(frmGerenciaDB, "Adicionado!", "Menssagem", 1);
						textFieldName.setText("");
						textFieldSobrenome.setText("");
						updateChoiceIdade();
						lblIdadeWarnning.setVisible(false);
					} catch (Exception a) {
						JOptionPane.showMessageDialog(frmGerenciaDB, "Preencha todos os campos", "Erro!", 2);
					}
				}
				updateTable();
			}
		});

		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdicionar.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdicionar.setBackground(UIManager.getColor("Button.disabledShadow"));
			}
		});
		tableResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					popupMenu.show(tableResultado, e.getX(), e.getY());
					int col = tableResultado.columnAtPoint(e.getPoint());
					int row = tableResultado.rowAtPoint(e.getPoint());
					if (col != -1 && row != -1) {
						tableResultado.setColumnSelectionInterval(col, col);
						tableResultado.setRowSelectionInterval(row, row);
					}
				}
				Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0);
				id = Integer.parseInt(coluna.toString());
				System.out.println(id);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (updated) {
					updateTable();
					updated = false;
				}
			}
		});

		addPopup(scrollPane, popupMenu);

		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditWindow edit = new EditWindow();
				if (id != -1) {
					System.out.println(id);
					edit.abrir(id);
				} else {
					JOptionPane.showMessageDialog(frmGerenciaDB, "Selecione uma pessoa", "Falha",
							JOptionPane.INFORMATION_MESSAGE);
				}
				updated = true;
			}
		});
		popupMenu.add(mntmEditar);

		mntmRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds = new Datasource();
				dao = new ProgramDAO(ds);
				Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0);
				int id = Integer.parseInt(coluna.toString());
				try {
					if (JOptionPane.showConfirmDialog(tableResultado,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Livro da Lista?", "Atenção!",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						BD.setId(id);
						dao.delete(BD);
						System.out.println("[Log] ID= " + id + JOptionPane.YES_OPTION);
						updateTable();
					}
				} catch (Exception e2) {
					System.err.println("[ERRO!] Não foi possivel Remover o Aluno de ID= " + id + e2.getStackTrace());
				}
			}
		});
		popupMenu.add(mntmRemover);

		tableResultado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableResultado.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Sobrenome", "Idade", "Curso", "Turno" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPane.setViewportView(tableResultado);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(UIManager.getColor("Button.disabledShadow"));
			}
		});
		btnEditar.setBackground(UIManager.getColor("Button.disabledShadow"));
		panelPesquisar.setVisible(false);

		panelPesquisar.setBorder(UIManager.getBorder("Tree.editorBorder"));
		panelPesquisar.setBackground(Color.WHITE);
		panelPesquisar.setBounds(671, 60, 355, 422);
		frmGerenciaDB.getContentPane().add(panelPesquisar);
		panelPesquisar.setLayout(null);

		lblFiltroCurso.setBounds(20, 111, 123, 14);
		panelPesquisar.add(lblFiltroCurso);

		choiceCurso2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (choiceCurso2.getSelectedItem().equals("Nenhum")) {
					updateTable();
				} else {
					String curso = choiceCurso2.getSelectedItem().toString();
					updateByCurso(curso);
				}

			}
		});
		choiceCurso2.setBounds(20, 131, 123, 20);
		panelPesquisar.add(choiceCurso2);
		lblFiltroTurno.setBounds(21, 169, 46, 14);

		panelPesquisar.add(lblFiltroTurno);

		chckbxMatutino.setBackground(Color.WHITE);
		chckbxMatutino.setBounds(20, 190, 97, 23);
		panelPesquisar.add(chckbxMatutino);

		chckbxVespertino.setBackground(Color.WHITE);
		chckbxVespertino.setBounds(20, 227, 97, 23);
		panelPesquisar.add(chckbxVespertino);

		chckbxNoturno.setBackground(Color.WHITE);
		chckbxNoturno.setForeground(Color.BLACK);
		chckbxNoturno.setBounds(20, 264, 97, 23);
		panelPesquisar.add(chckbxNoturno);
		textFieldPesquisa.setBounds(20, 38, 123, 20);
		panelPesquisar.add(textFieldPesquisa);

		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				updateTable();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					String name = textFieldPesquisa.getText();
					if (name.equals("")) {
						updateTable();
					} else {
						updateByName(name);
					}
				}
			}
		});
		textFieldPesquisa.setColumns(10);
		lblNewLabel.setBounds(20, 18, 115, 14);
		panelPesquisar.add(lblNewLabel);
		lblFiltrosDePesquisa.setBounds(20, 86, 115, 14);
		panelPesquisar.add(lblFiltrosDePesquisa);
		panelInserir.setVisible(false);
		panelInserir.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInserir.setBackground(Color.WHITE);
		panelInserir.setBounds(671, 60, 355, 422);

		frmGerenciaDB.getContentPane().add(panelInserir);
		panelInserir.setLayout(null);
		lblIdadeWarnning.setBounds(20, 202, 111, 22);
		panelInserir.add(lblIdadeWarnning);

		lblIdadeWarnning.setVisible(false);
		lblIdadeWarnning.setBackground(Color.WHITE);
		lblIdadeWarnning.setForeground(Color.RED);

		lblCurso.setBounds(20, 224, 46, 14);
		panelInserir.add(lblCurso);
		choiceCurso.setBounds(20, 244, 326, 20);
		panelInserir.add(choiceCurso);
		choiceIdade.setBounds(20, 176, 60, 20);
		panelInserir.add(choiceIdade);
		choiceIdade.setEnabled(false);
		lblIdade.setBounds(20, 156, 46, 14);
		panelInserir.add(lblIdade);
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ds = new Datasource();
					dao = new ProgramDAO(ds);
					if (choiceIdade.getSelectedItem().toString() != "" && textFieldName.getText() != ""
							&& choiceCurso.getSelectedItem().toString() != "") {
						try {
							BD.setNome(textFieldName.getText());
							BD.setIdade(Integer.parseInt(choiceIdade.getSelectedItem().toString()));
							BD.setCurso(choiceCurso.getSelectedItem().toString());
							BD.setSobrenome(textFieldSobrenome.getText());
							System.out.println(choiceCurso.getSelectedItem().toString());
							BD.setTurno(choiceTurno.getSelectedItem().toString());
							dao.create(BD);
							JOptionPane.showMessageDialog(frmGerenciaDB, "Adicionado!", "Menssagem", 1);
							textFieldName.setText("");
							lblIdadeWarnning.setVisible(false);
						} catch (Exception a) {
							JOptionPane.showMessageDialog(frmGerenciaDB, "Preencha todos os campos", "Erro!", 2);
						}
					} else {
						lblIdadeWarnning.setVisible(true);
					}
					updateTable();
				}
			}
		});
		textFieldName.setBounds(20, 78, 326, 23);
		panelInserir.add(textFieldName);
		lblName.setBounds(20, 59, 111, 14);
		panelInserir.add(lblName);
		choiceTurno.setBounds(219, 176, 113, 20);
		panelInserir.add(choiceTurno);

		lblTurno.setBounds(219, 156, 46, 14);
		panelInserir.add(lblTurno);
		lblSobrenome.setBounds(20, 107, 111, 14);

		panelInserir.add(lblSobrenome);
		panelInserir.add(textFieldSobrenome);

		final JPanel panelMenu = new JPanel();
		panelMenu.setBorder(new LineBorder(new Color(1, 1, 1)));
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(21, 86, 164, 268);
		frmGerenciaDB.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);

		final Label lblNewLabelInicio = new Label("Inicio");
		lblNewLabelInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabelInicio.setAlignment(Label.CENTER);
		lblNewLabelInicio.setBackground(SystemColor.gray);
		lblNewLabelInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (clicked1) {

				} else {
					lblNewLabelInicio.setBackground(SystemColor.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (clicked1) {

				} else {
					lblNewLabelInicio.setBackground(Color.WHITE);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelInicio.setVisible(true);
				panelPesquisar.setVisible(false);
				panelInserir.setVisible(false);
				btnAdicionar.setVisible(false);
				clicked1 = true;
				clicked2 = false;
				clicked3 = false;
				lblNewLabelInicio.setBackground(SystemColor.gray);
				lblAdicionar.setBackground(SystemColor.window);
				lblPesquisar.setBackground(SystemColor.window);

			}
		});
		lblNewLabelInicio.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		lblNewLabelInicio.setBounds(1, 38, 162, 41);
		panelMenu.add(lblNewLabelInicio);

		lblPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPesquisar.setBackground(SystemColor.text);
		lblPesquisar.setAlignment(Label.CENTER);
		lblPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked1 = false;
				clicked2 = true;
				clicked3 = false;
				panelInicio.setVisible(false);
				panelPesquisar.setVisible(true);
				panelInserir.setVisible(false);
				btnAdicionar.setVisible(false);
				lblPesquisar.setBackground(SystemColor.gray);
				lblNewLabelInicio.setBackground(SystemColor.window);
				lblAdicionar.setBackground(SystemColor.window);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (clicked2) {

				} else {
					lblPesquisar.setBackground(SystemColor.text);
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (clicked2) {

				} else {
					lblPesquisar.setBackground(SystemColor.gray);
				}

			}
		});
		lblPesquisar.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		lblPesquisar.setBounds(1, 91, 162, 41);
		panelMenu.add(lblPesquisar);

		lblAdicionar = new Label("Adicionar");
		lblAdicionar.setAlignment(Label.CENTER);
		lblAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdicionar.setForeground(Color.DARK_GRAY);
		lblAdicionar.setBackground(SystemColor.window);
		lblAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked1 = false;
				clicked2 = false;
				clicked3 = true;
				panelInicio.setVisible(false);
				panelInserir.setVisible(true);
				btnAdicionar.setVisible(true);
				panelPesquisar.setVisible(false);
				lblAdicionar.setBackground(SystemColor.gray);
				lblNewLabelInicio.setBackground(SystemColor.window);
				lblPesquisar.setBackground(SystemColor.window);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (clicked3) {

				} else {
					lblAdicionar.setBackground(SystemColor.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (clicked3) {

				} else {
					lblAdicionar.setBackground(SystemColor.window);
				}
			}
		});
		lblAdicionar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAdicionar.setBounds(1, 140, 162, 37);
		panelMenu.add(lblAdicionar);

		frmGerenciaDB.setJMenuBar(menuBar);

		menuBar.add(mnInicio);
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGerenciaDB.dispose();
			}
		});

		mnInicio.add(mntmSair);

		menuBar.add(mnAjuda);

		mnAjuda.add(mntmSobre);
		choiceTurno.add("Matutino");
		choiceTurno.add("Vespertino");
		choiceTurno.add("Noturno");
		textFieldName.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if (textFieldName.getText().length() != 0) {
					choiceIdade.setEnabled(true);
				} else {
					choiceIdade.setEnabled(false);
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditWindow edit =  new EditWindow();
				if (id != -1) {
					System.out.println("[Log] ID: " + id);
					edit.abrir(id);
				} else {
					JOptionPane.showMessageDialog(frmGerenciaDB, "Selecione uma pessoa", "Falha",
							JOptionPane.INFORMATION_MESSAGE);
				}
				updated = true;
			}
		});
		for (int i = 0; i < Cursos.getCursos().size(); i++) {
			choiceCurso.add(Cursos.getCursos().get(i).toString());
		}
		choiceCurso2.add("Nenhum");
		for (int i = 0; i < Cursos.getCursos().size(); i++) {
			choiceCurso2.add(Cursos.getCursos().get(i).toString());
		}
		frmGerenciaDB.setVisible(true);
	}
}
