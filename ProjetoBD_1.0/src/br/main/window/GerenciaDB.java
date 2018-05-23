package br.main.window;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.DAO.Datasource;
import br.DAO.ProgramDAO;
import br.model.BancodeDados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GerenciaDB {
	private JFrame frmInserirRemoverLivros = new JFrame();
	private TextField textFieldName = new TextField();
	private JTable tableResultado = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JLabel lblName = new JLabel("Nome");
	private JButton btnRemover = new JButton("Remover");
	private JButton btnAdicionar = new JButton("Inserir");
	private Choice choiceIdade = new Choice();
	private Datasource ds;
	private ProgramDAO dao;
	private BancodeDados BD;
	private Label lblTipoWarnning = new Label();
	private Label lblIdadeWarnning;
	private JLabel lblIdade = new JLabel("Idade");
	private JButton btnEditar = new JButton("Editar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciaDB window = new GerenciaDB();
					window.frmInserirRemoverLivros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GerenciaDB() {
		initialize();
		readJtable();
	}

	/**
	 * Preenche a tabela com dados.
	 */
	public void readJtable() {
		ds = new Datasource();
		dao = new ProgramDAO(ds);
		BD = new BancodeDados();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		for (BancodeDados BD2 : dao.bookList()) {
			modelo.addRow(new Object[] { BD2.getId(), BD2.getNome(), BD2.getIdade() });
		}
	}

	public void clearJtable() {
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		textFieldName.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if (textFieldName.getText().length() != 0) {
					choiceIdade.setEnabled(true);
				} else {
					choiceIdade.setEnabled(false);
				}
			}
		});

		if (tableResultado.getCellSelectionEnabled()) {
			btnRemover.setEnabled(true);
		}

		textFieldName.setBounds(509, 180, 115, 23);

		lblName.setBounds(513, 160, 111, 14);

		lblIdade.setBounds(513, 209, 46, 14);

		scrollPane.setBounds(26, 39, 459, 266);

		btnRemover.setBounds(312, 327, 89, 24);

		btnAdicionar.setBackground(Color.LIGHT_GRAY);
		btnAdicionar.setBounds(518, 327, 89, 24);

		btnRemover.setBackground(Color.LIGHT_GRAY);

		frmInserirRemoverLivros.getContentPane().add(btnRemover);
		frmInserirRemoverLivros.getContentPane().add(btnAdicionar);
		frmInserirRemoverLivros.getContentPane().add(scrollPane);
		frmInserirRemoverLivros.getContentPane().add(textFieldName);
		frmInserirRemoverLivros.getContentPane().add(lblName);
		frmInserirRemoverLivros.getContentPane().add(lblIdade);
		frmInserirRemoverLivros.getContentPane().setLayout(null);
		frmInserirRemoverLivros.setResizable(false);
		frmInserirRemoverLivros.setBounds(100, 100, 650, 400);
		frmInserirRemoverLivros.setTitle("Banco de Dados");
		frmInserirRemoverLivros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

						System.out.println("Aqui o id" + id + pane.YES_OPTION);
						BD.setId(id);
						dao.delete(BD);
						clearJtable();
						readJtable();
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
				btnRemover.setBackground(Color.LIGHT_GRAY);
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds = new Datasource();
				dao = new ProgramDAO(ds);
				if (choiceIdade.getSelectedItem() != "" && textFieldName.getText() != null) {
					try {
						BD.setNome(textFieldName.getText());
						BD.setIdade(Integer.parseInt(choiceIdade.getSelectedItem().toString()));
						dao.create(BD);
						JOptionPane.showMessageDialog(frmInserirRemoverLivros, "Adicionado!", "Menssagem", 1);
						textFieldName.setText("");
						lblTipoWarnning.setVisible(false);
					} catch (Exception a) {
						JOptionPane.showMessageDialog(frmInserirRemoverLivros, "Preencha todos os campos", "Erro!", 2);
					}
				} else {
					lblTipoWarnning.setVisible(true);
				}
				clearJtable();
				readJtable();
			}
		});

		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdicionar.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdicionar.setBackground(Color.LIGHT_GRAY);
			}
		});

		tableResultado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableResultado.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Idade" }) {
					private static final long serialVersionUID = 1L;
					boolean[] canEdit = new boolean[] { false, false, false };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		scrollPane.setViewportView(tableResultado);

		choiceIdade.setBounds(509, 233, 115, 20);
		frmInserirRemoverLivros.getContentPane().add(choiceIdade);

		lblIdadeWarnning = new Label("Escolha uma Idade");
		lblIdadeWarnning.setVisible(false);
		lblIdadeWarnning.setBackground(UIManager.getColor("CheckBox.background"));
		lblIdadeWarnning.setForeground(Color.RED);
		lblIdadeWarnning.setBounds(528, 259, 96, 22);
		frmInserirRemoverLivros.getContentPane().add(lblIdadeWarnning);
		choiceIdade.setEnabled(false);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnEditar.setBackground(Color.LIGHT_GRAY);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BD.setId((int)tableResultado.getValueAt(tableResultado.getSelectedRow(), 0));
					BD.setNome(
							JOptionPane.showInputDialog(scrollPane, "Nome ", "Editar", JOptionPane.OK_CANCEL_OPTION));
					BD.setIdade(Integer.parseInt(
							JOptionPane.showInputDialog(scrollPane, "Idade ", "Editar", JOptionPane.OK_CANCEL_OPTION)));
					dao.Update(BD);
					clearJtable();
					readJtable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(tableResultado, "Selecione uma pessoa:");
				}
			}
		});
		btnEditar.setBounds(76, 328, 89, 23);
		frmInserirRemoverLivros.getContentPane().add(btnEditar);
		choiceIdade.add("");
		for (int i = 0; i < 110; i++) {
			choiceIdade.add(String.valueOf(i+1));
		}

	}
}
