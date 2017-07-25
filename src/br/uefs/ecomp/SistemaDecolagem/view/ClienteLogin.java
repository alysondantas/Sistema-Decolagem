package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClienteLogin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 239);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(null);
		
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
		textField_3.setBounds(172, 34, 104, 20);
		panel_1.add(textField_3);
		
		JLabel lblServidorPorta = new JLabel("Servidor 1 PORTA:");
		lblServidorPorta.setBounds(178, 11, 98, 14);
		panel_1.add(lblServidorPorta);
	}
}
