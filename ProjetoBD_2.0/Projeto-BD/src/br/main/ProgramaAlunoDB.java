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
import java.util.ArrayList;
import java.util.Calendar;

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
import br.DAO.ProgramDAOAluno;
import br.DAO.ProgramDAOCursos;
import br.curso.AlunosCurso;
import br.curso.DisciplinasObrigatórias;
import br.curso.DisciplinasOptativas;
import br.curso.SobreWindow;
import br.main.window.EditWindow;
import br.model.Cursos;
import br.model.Idade;
import br.model.NiveldeAcesso;
import br.model.ProjetoAluno;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ProgramaAlunoDB {
	private static JFrame frmGerenciaDB = new JFrame();

	private TextField textFieldName = new TextField();
	private TextField textFieldPesquisa = new TextField();
	private TextField textFieldSobrenome = new TextField();

	private static JTable tableResultado = new JTable();

	public JScrollPane scrollPaneAlunos = new JScrollPane();
	public JScrollPane scrollPaneCursos = new JScrollPane();

	private Label lblPesquisar = new Label("Pesquisar");
	private JLabel lblName = new JLabel("Nome");
	private JLabel lblTurno = new JLabel("Turno");
	private JLabel lblFiltroCurso = new JLabel("Curso");
	private JLabel lblSelectCurso = new JLabel("Curso");
	private Label lblCursos = new Label("Cursos");
	private JLabel lblNewLabel = new JLabel("Pesquisar Nome");
	private JLabel lblDia = new JLabel("Dia");
	private JLabel lblIdadeWarnning = new JLabel("Escolha uma Idade");
	private JLabel lblFiltroTurno = new JLabel("Turno");
	private JLabel lblFiltrosDePesquisa = new JLabel("Filtros de Pesquisa");
	private JLabel lblSobrenome = new JLabel("Sobrenome");
	private Label lblLabelInicio = new Label("Inicio");

	private static JButton btnRemover = new JButton("Remover");
	private static JButton btnAdicionar = new JButton("Inserir");
	private static JButton btnEditar = new JButton("Editar");

	private JPanel panelPesquisar = new JPanel();
	private JPanel panelInserir = new JPanel();
	private JPanel panelCursos = new JPanel();
	private JPanel panelMenu = new JPanel();
	private JPanel panelInicio = new JPanel();

	private Choice choiceDia = new Choice();
	Choice choiceMes = new Choice();
	Choice choiceAno = new Choice();
	private Choice choiceTurno = new Choice();
	private Choice choiceCurso = new Choice();
	private Choice choiceCurso2 = new Choice();

	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnInicio = new JMenu("Inicio");
	private JMenu mnAjuda = new JMenu("Ajuda");

	private JMenuItem mntmSair = new JMenuItem("Sair");
	private JMenuItem mntmSobre = new JMenuItem("Sobre...");

	private JCheckBox chckbxMatutino = new JCheckBox("Matutino");
	private JCheckBox chckbxVespertino = new JCheckBox("Vespertino");
	private JCheckBox chckbxNoturno = new JCheckBox("Noturno");

	private static Datasource ds;
	private static ProgramDAOAluno daoAluno;
	private ProjetoAluno BDAlunos;
	private int id = -1;
	private int idCurso = -1;
	private boolean updated, clicked1, clicked2, clicked3, clicked4;

	private JPopupMenu popupMenuCursos = new JPopupMenu();
	private Label lblAdicionar;
	private JTable tableCursos;
	private final JLabel lblSistemaAluno = new JLabel("Sistema Aluno");
	private final JPopupMenu popupMenu = new JPopupMenu();
	private final JMenuItem menuItem_3 = new JMenuItem("Editar");
	private final JMenuItem menuItem_4 = new JMenuItem("Remover");
	private final static JLabel lblLogout_login = new JLabel("Logar Como Administrador");
	private static JButton btnLogar = new JButton("Login");
	private static JButton btnLogout = new JButton("Logout");

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
		initialize();
		updateTableAlunos();
	}
	/**
	 * Preenche a tabela com dados e atualiza.
	 */
	public static void updateTableAlunos() {
		ds = new Datasource();
		daoAluno = new ProgramDAOAluno(ds);
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
		for (ProjetoAluno BD2 : daoAluno.listAlunos()) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

	public void updateTableCursos() {
		ds = new Datasource();
		new ProgramDAOCursos(ds);
		DefaultTableModel modelo = (DefaultTableModel) tableCursos.getModel();
		modelo.setNumRows(0);
		for (Cursos cs2 : ProgramDAOCursos.listarCursos()) {
			modelo.addRow(new Object[] { cs2.getId(), cs2.getCurso() });
		}
	}

	public void updateByCurso(String curso) {
		ds = new Datasource();
		daoAluno = new ProgramDAOAluno(ds);
		BDAlunos = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
		for (ProjetoAluno BD2 : daoAluno.shortByCurso(curso)) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

	public static void checarPrivilegio() {
		if (NiveldeAcesso.isAdministrator()) {
			btnRemover.setVisible(true);
			btnEditar.setVisible(true);
			btnAdicionar.setVisible(true);
			lblLogout_login.setText("Sair de Administrador");
			btnLogar.setVisible(false);
			btnLogout.setVisible(true);
			
			
		}else {
			btnAdicionar.setVisible(false);
			btnEditar.setVisible(false);
			btnRemover.setVisible(false);			
			btnLogar.setVisible(true);
			btnLogout.setVisible(false );
			lblLogout_login.setText("Logar Como Adiministrador");
		}
	}
	
	private void updateByName(String name) {
		ds = new Datasource();
		daoAluno = new ProgramDAOAluno(ds);
		BDAlunos = new ProjetoAluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);

		for (ProjetoAluno BD2 : daoAluno.shortByName(name)) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getSobrenome(), BD2.getIdade(), BD2.getCurso(),
					BD2.getTurno() });
		}
	}

	private void updateChoiceIdade() {
		choiceDia.removeAll();
		choiceMes.removeAll();
		choiceAno.removeAll();
		for (int i = 0; i < 30; i++) {
			if (i < 9) {
				choiceDia.add(String.valueOf("0" + (i + 1)));
			} else {
				choiceDia.add(String.valueOf(i + 1));
			}
		}
		for (int i = 0; i < 12; i++) {
			if (i < 9) {
				choiceMes.add(String.valueOf("0" + (i + 1)));
			} else {
				choiceMes.add(String.valueOf(i + 1));
			}
		}
		Calendar cal = Calendar.getInstance();
		int anoatual = cal.get(Calendar.YEAR);
		for (int i = anoatual; i > 1900; i--) {
			choiceAno.add(String.valueOf(i));
		}
	}

	public static void enableWindow(boolean b) {
		frmGerenciaDB.setEnabled(b);
		updateTableAlunos();
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
		checarPrivilegio();
		frmGerenciaDB.setBounds(100, 100, 1060, 600);
		frmGerenciaDB.getContentPane().setBackground(Color.WHITE);
		frmGerenciaDB.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmGerenciaDB.setBackground(Color.BLACK);
		frmGerenciaDB.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rafael_Cruz\\Desktop\\Programa\\download.png"));
		BDAlunos = new ProjetoAluno();
		updateChoiceIdade();

		panelInserir.setVisible(false);
		
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

		panelCursos.setBounds(671, 60, 355, 422);
		frmGerenciaDB.getContentPane().add(panelCursos);
		panelCursos.setLayout(null);

		scrollPaneCursos.setBounds(0, 0, 355, 422);
		panelCursos.add(scrollPaneCursos);

		tableCursos = new JTable();
		scrollPaneCursos.setColumnHeaderView(tableCursos);

		tableCursos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableCursos
				.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID", "Curso" }) {
					private static final long serialVersionUID = 1L;
					boolean[] canEdit = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});

		tableCursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					popupMenuCursos.show(tableCursos, e.getX(), e.getY());
					int col = tableCursos.columnAtPoint(e.getPoint());
					int row = tableCursos.rowAtPoint(e.getPoint());
					if (col != -1 && row != -1) {
						tableCursos.setColumnSelectionInterval(col, col);
						tableCursos.setRowSelectionInterval(row, row);
					}
				}
				Object coluna = tableCursos.getValueAt(tableCursos.getSelectedRow(), 0);
				id = Integer.parseInt(coluna.toString());
				System.out.println(idCurso);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (updated) {
					updateTableAlunos();
					updated = false;
				}
			}
		});
		scrollPaneCursos.setViewportView(tableCursos);

		popupMenuCursos.setBounds(0, 0, 200, 50);

		JMenuItem menuItem = new JMenuItem("Ver Alunos");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunosCurso.run();
			}
		});
		popupMenuCursos.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Ver Disciplinas Obrigatórias");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisciplinasObrigatórias.run();
			}
		});
		popupMenuCursos.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Ver Disciplinas Opcionais");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisciplinasOptativas.run();
			}
		});
		popupMenuCursos.add(menuItem_2);

		addPopup(scrollPaneCursos, popupMenuCursos);
		if (NiveldeAcesso.isAdministrator()) {
			scrollPaneCursos.add(popupMenuCursos);			
		}
		panelInserir.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInserir.setBackground(Color.WHITE);
		panelInserir.setBounds(671, 60, 355, 422);

		frmGerenciaDB.getContentPane().add(panelInserir);
		panelInserir.setLayout(null);
		lblIdadeWarnning.setBounds(20, 269, 111, 22);
		panelInserir.add(lblIdadeWarnning);

		lblIdadeWarnning.setVisible(false);
		lblIdadeWarnning.setBackground(Color.WHITE);
		lblIdadeWarnning.setForeground(Color.RED);

		lblSelectCurso.setBounds(20, 302, 46, 14);
		panelInserir.add(lblSelectCurso);
		choiceCurso.setBounds(20, 322, 326, 20);
		panelInserir.add(choiceCurso);
		choiceDia.setBounds(20, 176, 60, 20);
		panelInserir.add(choiceDia);
		choiceDia.setEnabled(false);
		choiceMes.setEnabled(false);
		choiceAno.setEnabled(false);
		lblDia.setBounds(20, 156, 46, 14);
		panelInserir.add(lblDia);
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ds = new Datasource();
					daoAluno = new ProgramDAOAluno(ds);
					if (choiceDia.getSelectedItem().toString() != "" && textFieldName.getText() != ""
							&& choiceCurso.getSelectedItem().toString() != "") {
						try {
							BDAlunos.setNome(textFieldName.getText());
							BDAlunos.setIdade(Integer.parseInt(choiceDia.getSelectedItem().toString()));
							BDAlunos.setCurso(choiceCurso.getSelectedItem().toString());
							BDAlunos.setSobrenome(textFieldSobrenome.getText());
							System.out.println(choiceCurso.getSelectedItem().toString());
							BDAlunos.setTurno(choiceTurno.getSelectedItem().toString());
							daoAluno.create(BDAlunos);
							JOptionPane.showMessageDialog(frmGerenciaDB, "Adicionado!", "Menssagem", 1);
							textFieldName.setText("");
							lblIdadeWarnning.setVisible(false);
						} catch (Exception a) {
							JOptionPane.showMessageDialog(frmGerenciaDB, "Preencha todos os campos", "Erro!", 2);
						}
					} else {
						lblIdadeWarnning.setVisible(true);
					}
					updateTableAlunos();
				}
			}
		});
		textFieldName.setBounds(20, 78, 326, 23);
		panelInserir.add(textFieldName);
		lblName.setBounds(20, 59, 111, 14);
		panelInserir.add(lblName);
		choiceTurno.setBounds(219, 253, 113, 20);
		panelInserir.add(choiceTurno);
		textFieldSobrenome.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if (textFieldName.getText().length() != 0 && textFieldSobrenome.getText().length() != 0) {
					choiceDia.setEnabled(true);
					choiceMes.setEnabled(true);
					choiceAno.setEnabled(true);
				} else {
					choiceDia.setEnabled(false);
					choiceMes.setEnabled(false);
					choiceAno.setEnabled(false);
				}
			}
		});

		textFieldSobrenome.setBounds(20, 125, 326, 20);
		textFieldSobrenome.setColumns(10);
		lblTurno.setBounds(219, 233, 46, 14);
		panelInserir.add(lblTurno);
		lblSobrenome.setBounds(20, 107, 111, 14);

		panelInserir.add(lblSobrenome);
		panelInserir.add(textFieldSobrenome);

		JLabel lblMes = new JLabel("M\u00EAs");
		lblMes.setBounds(119, 156, 46, 14);
		panelInserir.add(lblMes);

		choiceMes.setBounds(119, 176, 60, 20);
		panelInserir.add(choiceMes);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(219, 156, 46, 14);
		panelInserir.add(lblAno);

		choiceAno.setBounds(219, 176, 60, 20);
		panelInserir.add(choiceAno);
		choiceTurno.add("Matutino");
		choiceTurno.add("Vespertino");
		choiceTurno.add("Noturno");
		textFieldName.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if (textFieldName.getText().length() != 0 && textFieldSobrenome.getText().length() != 0) {
					choiceDia.setEnabled(true);
					choiceMes.setEnabled(true);
					choiceAno.setEnabled(true);
				} else {
					choiceDia.setEnabled(false);
					choiceMes.setEnabled(false);
					choiceAno.setEnabled(false);
				}
			}
		});
		frmGerenciaDB.getContentPane().add(btnRemover);
		frmGerenciaDB.getContentPane().add(btnAdicionar);
		frmGerenciaDB.getContentPane().add(scrollPaneAlunos);
		popupMenu.setBounds(0, 0, 97, 50);
		if (NiveldeAcesso.isAdministrator()) {
			scrollPaneAlunos.add(popupMenu);			
		}
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					EditWindow edit = new EditWindow();
					edit.abrir(id);
					System.out.println("[Log] ID: " + id);
				} else {
					JOptionPane.showMessageDialog(frmGerenciaDB, "Selecione uma pessoa", "Falha",
							JOptionPane.INFORMATION_MESSAGE);
				}
				updated = true;
			}
		});
		
		popupMenu.add(menuItem_3);
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ds = new Datasource();
					daoAluno = new ProgramDAOAluno(ds);
					Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0);
					if (JOptionPane.showConfirmDialog(tableResultado,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Livro da Lista?", "Atenção!",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						int id = Integer.parseInt(coluna.toString());

						System.out.println("[Log] ID= " + id + JOptionPane.YES_OPTION);
						BDAlunos.setId(id);
						daoAluno.delete(BDAlunos);
						updateTableAlunos();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(tableResultado, "Selecione uma pessoa:");
				}
			}
		});
		
		popupMenu.add(menuItem_4);
		frmGerenciaDB.getContentPane().add(btnEditar);
		frmGerenciaDB.getContentPane().setLayout(null);
		frmGerenciaDB.setResizable(false);
		frmGerenciaDB.setTitle("Programa Aluno");
		frmGerenciaDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (tableResultado.getCellSelectionEnabled()) {
			btnRemover.setEnabled(true);
		}
		scrollPaneAlunos.setBounds(209, 27, 441, 455);
		btnEditar.setBounds(268, 497, 89, 23);
		btnRemover.setBounds(523, 496, 89, 24);
		btnAdicionar.setBounds(822, 496, 89, 24);
		btnAdicionar.setVisible(false);

		btnAdicionar.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnRemover.setBackground(UIManager.getColor("Button.disabledShadow"));

		btnRemover.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					ds = new Datasource();
					daoAluno = new ProgramDAOAluno(ds);
					Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0);
					JOptionPane pane = new JOptionPane();
					if (pane.showConfirmDialog(tableResultado,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Livro da Lista?", "Atenção!",
							pane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						int id = Integer.parseInt(coluna.toString());

						System.out.println("[Log] ID= " + id + pane.YES_OPTION);
						BDAlunos.setId(id);
						daoAluno.delete(BDAlunos);
						updateTableAlunos();
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
				daoAluno = new ProgramDAOAluno(ds);
				if (choiceDia.getSelectedItem().toString().equals("")) {
					lblIdadeWarnning.setVisible(true);

				} else {
					try {
						BDAlunos.setNome(textFieldName.getText());
						BDAlunos.setSobrenome(textFieldSobrenome.getText());
						BDAlunos.setIdade(Idade.calcularIdade(Integer.parseInt(choiceDia.getSelectedItem()),
								Integer.parseInt(choiceMes.getSelectedItem()),
								Integer.parseInt(choiceAno.getSelectedItem())));
						BDAlunos.setIdadedia(Integer.parseInt(choiceDia.getSelectedItem().toString()));
						BDAlunos.setIdademes(Integer.parseInt(choiceMes.getSelectedItem().toString()));
						BDAlunos.setIdadeano(Integer.parseInt(choiceAno.getSelectedItem().toString()));
						BDAlunos.setCurso(choiceCurso.getSelectedItem().toString());
						System.out.println(choiceCurso.getSelectedItem().toString());
						BDAlunos.setTurno(choiceTurno.getSelectedItem().toString());
						daoAluno.create(BDAlunos);
						JOptionPane.showMessageDialog(frmGerenciaDB, "Adicionado!", "Menssagem", 1);
						textFieldName.setText("");
						textFieldSobrenome.setText("");
						updateChoiceIdade();
						lblIdadeWarnning.setVisible(false);
					} catch (Exception a) {
						JOptionPane.showMessageDialog(frmGerenciaDB, "Preencha todos os campos", "Erro!", 2);
					}
				}
				updateTableAlunos();
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
					updateTableAlunos();
					updated = false;
				}
			}
		});

		tableResultado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableResultado.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Sobrenome", "Idade", "Curso", "Turno" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPaneAlunos.setViewportView(tableResultado);

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
					updateTableAlunos();
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
				updateTableAlunos();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					String name = textFieldPesquisa.getText();
					if (name.equals("")) {
						updateTableAlunos();
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

		panelMenu.setBorder(new LineBorder(new Color(1, 1, 1)));
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(21, 86, 164, 268);
		frmGerenciaDB.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);

		lblLabelInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLabelInicio.setAlignment(Label.CENTER);
		lblLabelInicio.setBackground(SystemColor.gray);
		lblLabelInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (clicked1) {

				} else {
					lblLabelInicio.setBackground(SystemColor.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (clicked1) {

				} else {
					lblLabelInicio.setBackground(Color.WHITE);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelInicio.setVisible(true);
				panelPesquisar.setVisible(false);
				panelInserir.setVisible(false);
				btnAdicionar.setVisible(false);
				panelCursos.setVisible(false);
				clicked1 = true;
				clicked2 = false;
				clicked3 = false;
				clicked4 = false;
				lblLabelInicio.setBackground(SystemColor.gray);
				lblCursos.setBackground(SystemColor.window);
				lblAdicionar.setBackground(SystemColor.window);
				lblPesquisar.setBackground(SystemColor.window);

			}
		});
		lblLabelInicio.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		lblLabelInicio.setBounds(1, 38, 162, 41);
		panelMenu.add(lblLabelInicio);

		lblPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPesquisar.setBackground(SystemColor.text);
		lblPesquisar.setAlignment(Label.CENTER);
		lblPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked1 = false;
				clicked2 = true;
				clicked3 = false;
				clicked4 = false;
				panelInicio.setVisible(false);
				panelPesquisar.setVisible(true);
				panelInserir.setVisible(false);
				btnAdicionar.setVisible(false);
				panelCursos.setVisible(false);
				lblPesquisar.setBackground(SystemColor.gray);
				lblCursos.setBackground(SystemColor.window);
				lblLabelInicio.setBackground(SystemColor.window);
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
		lblAdicionar.setBackground(SystemColor.window);
		lblAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked1 = false;
				clicked2 = false;
				clicked3 = true;
				clicked4 = false;
				panelInicio.setVisible(false);
				panelInserir.setVisible(true);
				if (NiveldeAcesso.isAdministrator()) {
					btnAdicionar.setVisible(true);					
				}else {
					btnAdicionar.setVisible(false);
				}
				panelPesquisar.setVisible(false);
				panelCursos.setVisible(false);
				lblAdicionar.setBackground(SystemColor.gray);
				lblCursos.setBackground(SystemColor.window);
				lblLabelInicio.setBackground(SystemColor.window);
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
		lblAdicionar.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		lblAdicionar.setBounds(1, 140, 162, 37);
		panelMenu.add(lblAdicionar);

		lblCursos.setFont(new Font("Yu Gothic Light", Font.PLAIN, 20));
		lblCursos.setAlignment(Label.CENTER);
		lblCursos.setBounds(1, 182, 162, 41);
		lblCursos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCursos.setBackground(SystemColor.window);
		lblCursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked1 = false;
				clicked2 = false;
				clicked3 = false;
				clicked4 = true;

				panelInicio.setVisible(false);
				panelInserir.setVisible(false);
				btnAdicionar.setVisible(false);
				panelPesquisar.setVisible(false);
				panelCursos.setVisible(true);
				lblCursos.setBackground(SystemColor.gray);
				lblAdicionar.setBackground(SystemColor.window);
				lblLabelInicio.setBackground(SystemColor.window);
				lblPesquisar.setBackground(SystemColor.window);

				updateTableCursos();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (clicked4) {

				} else {
					lblCursos.setBackground(SystemColor.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (clicked4) {

				} else {
					lblCursos.setBackground(SystemColor.window);
				}
			}
		});
		panelMenu.add(lblCursos);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("D:\\WorkspaceEclipse\\GerenciaBD\\Projeto-BD\\src\\aluno.png"));
		lblImage.setBounds(56, 376, 89, 144);
		frmGerenciaDB.getContentPane().add(lblImage);
		lblSistemaAluno.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblSistemaAluno.setBounds(35, 376, 133, 29);
		
		frmGerenciaDB.getContentPane().add(lblSistemaAluno);
		lblLogout_login.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogout_login.setBounds(887, 11, 157, 14);
		
		frmGerenciaDB.getContentPane().add(lblLogout_login);
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					NiveldeAcesso.loginAsADM();					
			}
		});
		btnLogar.setBounds(955, 26, 89, 23);
		
		frmGerenciaDB.getContentPane().add(btnLogar);
		
	
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NiveldeAcesso.admLogout();
			}
		});
		btnLogout.setBounds(955, 27, 89, 23);
		frmGerenciaDB.getContentPane().add(btnLogout);

		frmGerenciaDB.setJMenuBar(menuBar);

		menuBar.add(mnInicio);
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGerenciaDB.dispose();
			}
		});

		mnInicio.add(mntmSair);

		menuBar.add(mnAjuda);
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SobreWindow.run();
			}
		});

		mnAjuda.add(mntmSobre);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					EditWindow edit = new EditWindow();
					edit.abrir(id);
					System.out.println("[Log] ID: " + id);
				} else {
					JOptionPane.showMessageDialog(frmGerenciaDB, "Selecione uma pessoa", "Falha",
							JOptionPane.INFORMATION_MESSAGE);
				}
				updated = true;
			}
		});
		preencherCursos();
		frmGerenciaDB.setVisible(true);
	}

	private void preencherCursos() {
		ds = new Datasource();
		new ProgramDAOCursos(ds);
		ArrayList<Cursos> curso = new ArrayList<Cursos>();
		curso = ProgramDAOCursos.listarCursos();
		for (int i = 0; i < curso.size(); i++) {
			choiceCurso.add(curso.get(i).getCurso());
		}
		choiceCurso2.add("Nenhum");
		for (int i = 0; i < curso.size(); i++) {
			choiceCurso2.add(curso.get(i).getCurso());
		}
	}
}
