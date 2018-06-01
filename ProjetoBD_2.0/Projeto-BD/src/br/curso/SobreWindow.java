package br.curso;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SobreWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	public static void run() {
		try {
			SobreWindow window = new SobreWindow();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public SobreWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		frame = new JFrame("Programa Aluno 2.0");
		frame.setBounds((d.width/2)-250,(d.height/2)-170, 500, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSobre = new JLabel("");
		lblSobre.setIcon(new ImageIcon("D:\\\\WorkspaceEclipse\\\\GerenciaBD\\\\Projeto-BD\\\\src\\\\sobre.png"));
		lblSobre.setBounds(388, 33, 56, 70);
		frame.getContentPane().add(lblSobre);
		
		JLabel lblSobre2 = new JLabel("");
		lblSobre2.setIcon(new ImageIcon("D:\\WorkspaceEclipse\\GerenciaBD\\Projeto-BD\\src\\aluno.png"));
		lblSobre2.setBounds(347, 33, 67, 100);
		frame.getContentPane().add(lblSobre2);
		
		JLabel lblVersion = new JLabel("Version 2.0.0");
		lblVersion.setBounds(100, 49, 112, 47);
		frame.getContentPane().add(lblVersion);
		
		JLabel lblNome = new JLabel("Programa Aluno");
		lblNome.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		lblNome.setBounds(90, 33, 134, 35);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCreatedBy = new JLabel("Created By:");
		lblCreatedBy.setBounds(77, 119, 75, 14);
		frame.getContentPane().add(lblCreatedBy);
		
		JLabel lblcreator = new JLabel("-Creator1");
		lblcreator.setBounds(149, 119, 75, 14);
		frame.getContentPane().add(lblcreator);
		
		JLabel lblcreator_1 = new JLabel("-Creator2");
		lblcreator_1.setBounds(149, 138, 75, 14);
		frame.getContentPane().add(lblcreator_1);
		
		JLabel lblCreator = new JLabel("-Creator3");
		lblCreator.setBounds(149, 159, 56, 14);
		frame.getContentPane().add(lblCreator);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnFechar.setBounds(182, 250, 89, 23);
		frame.getContentPane().add(btnFechar);
	}
}
