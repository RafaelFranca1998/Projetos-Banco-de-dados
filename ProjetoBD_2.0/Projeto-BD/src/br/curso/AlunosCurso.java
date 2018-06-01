
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

public class AlunosCurso {
	JTable table;
	private JFrame frame;

	/**
	 * Launch the application.
	 */

	public static void run() {
		try {
			AlunosCurso window = new AlunosCurso();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public AlunosCurso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		table = new JTable();
		frame = new JFrame();
		frame.setBounds((d.width/2)-225, (d.height/2)-150, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 200);
		frame.getContentPane().add(scrollPane);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnFechar.setBounds(167, 227, 89, 23);
		frame.getContentPane().add(btnFechar);

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID", "Aluno" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

	}

}
