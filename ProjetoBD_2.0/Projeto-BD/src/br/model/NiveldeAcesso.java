package br.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.main.ProgramaAlunoDB;

public class NiveldeAcesso {
	static boolean administrator = false;
	static JFrame frmLogin;
	static JLabel lblLoginOuSenha = new JLabel("Login ou senha Incorretos");
	private static JTextField textFieldLogin;
	private static JPasswordField passwordFieldSenha;
	
	public static boolean isAdministrator() {
		return administrator;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void loginAsADM() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds((d.width/2)-175, (d.height/2)-150, 350, 300);
		frmLogin.getContentPane().setLayout(null);
		
		lblLoginOuSenha.setVisible(false);
		final JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(24, 137, 46, 14);
		frmLogin.getContentPane().add(lblSenha);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(24, 94, 46, 14);
		frmLogin.getContentPane().add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(80, 91, 205, 20);
		frmLogin.getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(80, 134, 205, 20);
		frmLogin.getContentPane().add(passwordFieldSenha);
		passwordFieldSenha.setColumns(10);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (checaLogin(textFieldLogin.getText(), passwordFieldSenha. getText())) {
					JOptionPane.showMessageDialog(lblSenha, "Sucesso", "Login", JOptionPane.INFORMATION_MESSAGE);
					ProgramaAlunoDB.checarPrivilegio();
					frmLogin.dispose();
				}else {
					lblLoginOuSenha.setVisible(true);
				}
			}
		});
		btnLogar.setBounds(59, 205, 89, 23);
		frmLogin.getContentPane().add(btnLogar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
			}
		});
		btnCancelar.setBounds(189, 205, 89, 23);
		frmLogin.getContentPane().add(btnCancelar);
		
		
		lblLoginOuSenha.setForeground(Color.RED);
		lblLoginOuSenha.setBounds(112, 165, 162, 14);
		frmLogin.getContentPane().add(lblLoginOuSenha);
		frmLogin.setVisible(true);
	}
	
	private static boolean checaLogin(String login, String senha) {
		boolean confirm = false;
		if (login.equals("admin") && senha.equals("admin")) {
			administrator = true;
			confirm = true;
		}
		if (login.equals("admin") && senha.equals("12345")) {
			administrator = true;
			confirm =  true;
		}
		return confirm;
	}
	public static void admLogout() {
		administrator = false;
		ProgramaAlunoDB.checarPrivilegio();
	}
}
