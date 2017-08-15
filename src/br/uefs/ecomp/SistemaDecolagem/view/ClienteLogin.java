package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerCliente;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClienteLogin {
	
	private JFrame frame;
	private JTextField textFieldip1;
	private JTextField textFieldip2;
	private JTextField textFieldip3;
	private JTextField textFieldporta1;
	private JTextField textFieldporta2;
	private JTextField textFieldporta3;
	private JTextField textFieldUser;
	private JPasswordField passwordFieldSenha;
	private JRadioButton rdbtnServidor;
	private JRadioButton rdbtnServidor2;
	private JRadioButton rdbtnServidor3;
	private ControllerCliente controller = ControllerCliente.getInstance();
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
		frame.setBounds(100, 100, 403, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 367, 241);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(null);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(59, 11, 86, 20);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		JLabel lblNome = new JLabel("Usuario:");
		lblNome.setBounds(10, 14, 50, 14);
		panel.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(193, 14, 50, 14);
		panel.add(lblSenha);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(232, 11, 86, 20);
		panel.add(passwordFieldSenha);
		
		rdbtnServidor = new JRadioButton("Servidor 1");
		rdbtnServidor.setSelected(true);
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
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1){
				String servidor = null;
				if(rdbtnServidor.isSelected()){
					servidor = "servidor1";
				}else if(rdbtnServidor2.isSelected()){
					servidor = "servidor2";
				}else if(rdbtnServidor3.isSelected()){
					servidor = "servidor3";
				}
				try {
					try {
						if(controller.estaLogado(passwordFieldSenha.getText(),textFieldUser.getText(),servidor)==true){
							ClienteGui clienteGui = new ClienteGui();
							clienteGui.setVisible(true);
							
						}
					} catch (OperacaoInvalidaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(229, 130, 89, 23);
		panel.add(btnCadastrar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Configurações", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblServidorIp = new JLabel("Servidor 1 IP:");
		lblServidorIp.setBounds(10, 11, 73, 14);
		panel_1.add(lblServidorIp);
		
		textFieldip1 = new JTextField();
		textFieldip1.setText("10.0.0.107");
		textFieldip1.setBounds(10, 34, 104, 20);
		panel_1.add(textFieldip1);
		textFieldip1.setColumns(10);
		
		textFieldip2 = new JTextField();
		textFieldip2.setText("192.168.15.100");
		textFieldip2.setColumns(10);
		textFieldip2.setBounds(10, 89, 104, 20);
		panel_1.add(textFieldip2);
		
		JLabel lblServidorIp_1 = new JLabel("Servidor 2 IP:");
		lblServidorIp_1.setBounds(10, 66, 73, 14);
		panel_1.add(lblServidorIp_1);
		
		textFieldip3 = new JTextField();
		textFieldip3.setText("192.168.15.100");
		textFieldip3.setColumns(10);
		textFieldip3.setBounds(10, 143, 104, 20);
		panel_1.add(textFieldip3);
		
		JLabel lblServidorIp_2 = new JLabel("Servidor 3 IP:");
		lblServidorIp_2.setBounds(10, 120, 73, 14);
		panel_1.add(lblServidorIp_2);
		
		textFieldporta1 = new JTextField();
		textFieldporta1.setText("1099");
		textFieldporta1.setColumns(10);
		textFieldporta1.setBounds(178, 34, 42, 20);
		panel_1.add(textFieldporta1);
		
		JLabel lblServidorPorta = new JLabel("Servidor 1 PORTA:");
		lblServidorPorta.setBounds(178, 11, 98, 14);
		panel_1.add(lblServidorPorta);
		
		textFieldporta2 = new JTextField();
		textFieldporta2.setText("1100");
		textFieldporta2.setColumns(10);
		textFieldporta2.setBounds(178, 89, 42, 20);
		panel_1.add(textFieldporta2);
		
		JLabel lblServidorPorta_1 = new JLabel("Servidor 2 PORTA:");
		lblServidorPorta_1.setBounds(178, 66, 98, 14);
		panel_1.add(lblServidorPorta_1);
		
		textFieldporta3 = new JTextField();
		textFieldporta3.setText("1101");
		textFieldporta3.setColumns(10);
		textFieldporta3.setBounds(178, 143, 42, 20);
		panel_1.add(textFieldporta3);
		
		JLabel lblServidorPorta_2 = new JLabel("Servidor 3 PORTA:");
		lblServidorPorta_2.setBounds(178, 120, 98, 14);
		panel_1.add(lblServidorPorta_2);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarIp();
			}
		});
		btnAtualizar.setBounds(10, 179, 89, 23);
		panel_1.add(btnAtualizar);
	}
	
	public void cadastrar(){
		String nome = textFieldUser.getText();
		String senha = passwordFieldSenha.getText();
		String servidor = null;
		if(rdbtnServidor.isSelected()){
			servidor = "servidor1";
		}else if(rdbtnServidor2.isSelected()){
			servidor = "servidor2";
		}else if(rdbtnServidor3.isSelected()){
			servidor = "servidor3";
		}
		JOptionPane.showMessageDialog(null,"Sua senha:" + senha,"Senha",2);//exibe recebido
		try {
			String recebido = controller.cadastrar(senha, nome, servidor);
			if(recebido.equals("concluido")){
				JOptionPane.showMessageDialog(null,"Cadastro concluido:" + recebido,"Efetuado",2);//exibe recebido
			}else if (recebido.equals("camponaopreenchido")){
				JOptionPane.showMessageDialog(null,"Campo não preenchido: " + recebido,"Erro",2);//exibe recebido
			}else if (recebido.equals("cadastrojaexistente")){
				JOptionPane.showMessageDialog(null,"Cadastro ja existente: " + recebido,"Erro",2);//exibe recebido
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperacaoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarIp(){
		String ip1 = textFieldip1.getText();
		String ip2 = textFieldip2.getText();
		String ip3 = textFieldip3.getText();
		String portaS1 = textFieldporta1.getText();
		int porta1 = Integer.parseInt(portaS1);
		String portaS2 = textFieldporta2.getText();
		int porta2 = Integer.parseInt(portaS2);
		String portaS3 = textFieldporta3.getText();
		int porta3 = Integer.parseInt(portaS3);
		try {
			controller.atualizaIpPorta(ip1, porta1, ip2, porta2, ip3, porta3);
		} catch (CampoVazioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
