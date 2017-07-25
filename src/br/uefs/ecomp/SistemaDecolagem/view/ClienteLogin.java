package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ClienteLogin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField;
	private JRadioButton rdbtnServidor;
	private JRadioButton rdbtnServidor2;
	private JRadioButton rdbtnServidor3;
	private final ButtonGroup tipo = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteLogin window = new ClienteLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClienteLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String nome = System.getProperty("os.name");//recupera o nome do SO
		if(nome.substring(0, 7).equals("Windows")){//se ele for WINDOWS é colocado um LookAndFeel do windows para rodar melhorar a aparencia
			try { 
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			} catch (InstantiationException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 277);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 339, 216);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(null);
		
		textField_6 = new JTextField();
		textField_6.setBounds(59, 11, 86, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNome = new JLabel("Usuario:");
		lblNome.setBounds(10, 14, 50, 14);
		panel.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(193, 14, 50, 14);
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(232, 11, 86, 20);
		panel.add(passwordField);
		
		rdbtnServidor = new JRadioButton("Servidor 1");
		rdbtnServidor.setBounds(40, 62, 86, 23);
		tipo.add(rdbtnServidor);
		panel.add(rdbtnServidor);
		
		rdbtnServidor2 = new JRadioButton("Servidor 2");
		rdbtnServidor2.setBounds(128, 62, 86, 23);
		tipo.add(rdbtnServidor2);
		panel.add(rdbtnServidor2);
		
		rdbtnServidor3 = new JRadioButton("Servidor 3");
		rdbtnServidor3.setBounds(216, 62, 86, 23);
		tipo.add(rdbtnServidor3);
		panel.add(rdbtnServidor3);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 130, 89, 23);
		panel.add(btnLogin);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(229, 130, 89, 23);
		panel.add(btnCadastrar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Configurações", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblServidorIp = new JLabel("Servidor 1 IP:");
		lblServidorIp.setBounds(10, 11, 73, 14);
		panel_1.add(lblServidorIp);
		
		textField = new JTextField();
		textField.setText("192.168.15.100");
		textField.setBounds(10, 34, 104, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("192.168.15.100");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 89, 104, 20);
		panel_1.add(textField_1);
		
		JLabel lblServidorIp_1 = new JLabel("Servidor 2 IP:");
		lblServidorIp_1.setBounds(10, 66, 73, 14);
		panel_1.add(lblServidorIp_1);
		
		textField_2 = new JTextField();
		textField_2.setText("192.168.15.100");
		textField_2.setColumns(10);
		textField_2.setBounds(10, 143, 104, 20);
		panel_1.add(textField_2);
		
		JLabel lblServidorIp_2 = new JLabel("Servidor 3 IP:");
		lblServidorIp_2.setBounds(10, 120, 73, 14);
		panel_1.add(lblServidorIp_2);
		
		textField_3 = new JTextField();
		textField_3.setText("1099");
		textField_3.setColumns(10);
		textField_3.setBounds(178, 34, 42, 20);
		panel_1.add(textField_3);
		
		JLabel lblServidorPorta = new JLabel("Servidor 1 PORTA:");
		lblServidorPorta.setBounds(178, 11, 98, 14);
		panel_1.add(lblServidorPorta);
		
		textField_4 = new JTextField();
		textField_4.setText("1099");
		textField_4.setColumns(10);
		textField_4.setBounds(178, 89, 42, 20);
		panel_1.add(textField_4);
		
		JLabel lblServidorPorta_1 = new JLabel("Servidor 2 PORTA:");
		lblServidorPorta_1.setBounds(178, 66, 98, 14);
		panel_1.add(lblServidorPorta_1);
		
		textField_5 = new JTextField();
		textField_5.setText("1099");
		textField_5.setColumns(10);
		textField_5.setBounds(178, 143, 42, 20);
		panel_1.add(textField_5);
		
		JLabel lblServidorPorta_2 = new JLabel("Servidor 3 PORTA:");
		lblServidorPorta_2.setBounds(178, 120, 98, 14);
		panel_1.add(lblServidorPorta_2);
	}
}
