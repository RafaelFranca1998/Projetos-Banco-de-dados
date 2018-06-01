package br.curso;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class DisciplinasOptativas {

	private JFrame frame;
	JTable table = new JTable();

	/**
	 * Launch the application.
	 */

	public static void run() {
		try {
			DisciplinasOptativas window = new DisciplinasOptativas();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public DisciplinasOptativas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds((d.width/2)-225, (d.height/2)-150, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 444, 200);
		scrollPane.setViewportView(table);

		frame.getContentPane().add(scrollPane);

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Disciplina Optativa" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();	
			}
		});
		btnFechar.setBounds(170, 227, 89, 23);
		frame.getContentPane().add(btnFechar);

	}

}
