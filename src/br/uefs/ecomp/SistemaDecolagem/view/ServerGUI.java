package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.controller.ControllerServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OrigemDestinoIguaisException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SemVagasException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.VerticeNaoEncontradoException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class ServerGUI {

	private JFrame frame;
	private JTextField textFieldPorta;
	private JTextArea textArea;
	private final ButtonGroup tipo = new ButtonGroup();
	private JRadioButton rdbtnServidor;
	private JRadioButton rdbtnServidor2;
	private JRadioButton rdbtnServidor3;
	private JButton btnStartServer;
	private ControllerServer controller = ControllerServer.getInstance();
	private JTextField textFieldS1Ip;
	private JTextField textFieldS2Ip;
	private JTextField textFieldS3Ip;
	private JTextField textFieldS1Porta;
	private JTextField textFieldS2Porta;
	private JTextField textFieldS3Porta;
	private JButton btnAtualizar;
	private ControllerDadosServer controllerD = ControllerDadosServer.getInstance();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
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
	public ServerGUI() {
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
		frame.setBounds(100, 100, 449, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 433, 281);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Server", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 408, 154);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setColumns(10);
		
		JButton btnSync = new JButton("sync");
		btnSync.setBounds(204, 219, 89, 23);
		panel.add(btnSync);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(303, 219, 115, 23);
		panel.add(btnStartServer);
		
		textFieldPorta = new JTextField();
		textFieldPorta.setBounds(332, 188, 86, 20);
		panel.add(textFieldPorta);
		textFieldPorta.setColumns(10);
		
		JLabel label = new JLabel("Porta:");
		label.setBounds(297, 192, 46, 14);
		panel.add(label);
		
		rdbtnServidor2 = new JRadioButton("Servidor 2");
		rdbtnServidor2.setBounds(6, 197, 86, 23);
		panel.add(rdbtnServidor2);
		tipo.add(rdbtnServidor2);
		
		rdbtnServidor3 = new JRadioButton("Servidor 3");
		rdbtnServidor3.setBounds(6, 223, 86, 23);
		panel.add(rdbtnServidor3);
		tipo.add(rdbtnServidor3);
		
		rdbtnServidor = new JRadioButton("Servidor 1");
		rdbtnServidor.setBounds(6, 171, 86, 23);
		panel.add(rdbtnServidor);
		rdbtnServidor.setSelected(true);
		tipo.add(rdbtnServidor);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//controllerD.getTrajeto("Y", "C");
					//boolean b = controllerD.reservarTrecho("A", "E", "alyson", "servidor1");
					boolean b = controllerD.compraCaminho("alyson", "A", "E", "servidor1", "E");
					System.out.println("Trecho aviso: " + b);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CampoVazioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SemVagasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OperacaoInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(127, 187, 89, 23);
		panel.add(btnNewButton);
		btnStartServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				iniciaServer();
			}
		});
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controllerD.syncGrafos();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (VerticeNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Configurações", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblServidorIp = new JLabel("Servidor 1 IP:");
		lblServidorIp.setBounds(10, 11, 77, 14);
		panel_1.add(lblServidorIp);
		
		textFieldS1Ip = new JTextField();
		textFieldS1Ip.setText("192.168.15.4");
		textFieldS1Ip.setBounds(10, 33, 108, 20);
		panel_1.add(textFieldS1Ip);
		textFieldS1Ip.setColumns(10);
		
		textFieldS2Ip = new JTextField();
		textFieldS2Ip.setText("192.168.15.4");
		textFieldS2Ip.setColumns(10);
		textFieldS2Ip.setBounds(10, 86, 108, 20);
		panel_1.add(textFieldS2Ip);
		
		JLabel lblServidorIp_1 = new JLabel("Servidor 2 IP:");
		lblServidorIp_1.setBounds(10, 64, 77, 14);
		panel_1.add(lblServidorIp_1);
		
		textFieldS3Ip = new JTextField();
		textFieldS3Ip.setText("192.168.15.4");
		textFieldS3Ip.setColumns(10);
		textFieldS3Ip.setBounds(10, 139, 108, 20);
		panel_1.add(textFieldS3Ip);
		
		JLabel lblServidorIp_2 = new JLabel("Servidor 3 IP:");
		lblServidorIp_2.setBounds(10, 117, 77, 14);
		panel_1.add(lblServidorIp_2);
		
		JLabel lblServidorPorta = new JLabel("Servidor 1 Porta:");
		lblServidorPorta.setBounds(257, 11, 91, 14);
		panel_1.add(lblServidorPorta);
		
		textFieldS1Porta = new JTextField();
		textFieldS1Porta.setText("1099");
		textFieldS1Porta.setBounds(257, 33, 86, 20);
		panel_1.add(textFieldS1Porta);
		textFieldS1Porta.setColumns(10);
		
		textFieldS2Porta = new JTextField();
		textFieldS2Porta.setText("1100");
		textFieldS2Porta.setColumns(10);
		textFieldS2Porta.setBounds(257, 86, 86, 20);
		panel_1.add(textFieldS2Porta);
		
		JLabel lblServidorPorta_1 = new JLabel("Servidor 2 Porta:");
		lblServidorPorta_1.setBounds(257, 64, 91, 14);
		panel_1.add(lblServidorPorta_1);
		
		textFieldS3Porta = new JTextField();
		textFieldS3Porta.setText("1101");
		textFieldS3Porta.setColumns(10);
		textFieldS3Porta.setBounds(257, 139, 86, 20);
		panel_1.add(textFieldS3Porta);
		
		JLabel lblServidorPorta_2 = new JLabel("Servidor 3 Porta:");
		lblServidorPorta_2.setBounds(257, 117, 91, 14);
		panel_1.add(lblServidorPorta_2);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaIps();
			}
		});
		btnAtualizar.setBounds(10, 206, 89, 23);
		panel_1.add(btnAtualizar);
	}
	
	/**
	 * Metodo que incia o servidor
	 */
	private void iniciaServer(){
		if(controller != null){//se o controller existir
			btnStartServer.setEnabled(false);//desabilita o botão de inciar
			rdbtnServidor.setEnabled(false);
			rdbtnServidor2.setEnabled(false);
			rdbtnServidor3.setEnabled(false);
			textFieldPorta.setEnabled(false);
			textFieldS1Ip.setEnabled(false);
			textFieldS2Ip.setEnabled(false);
			textFieldS3Ip.setEnabled(false);
			textFieldS1Porta.setEnabled(false);
			textFieldS2Porta.setEnabled(false);
			textFieldS3Porta.setEnabled(false);
			btnAtualizar.setEnabled(false);
			int i = 1099;//porta padrão
			String grafo = "grafo";
			String servidor = "servidor";
			if(rdbtnServidor.isSelected()){
				i = 1099;
				grafo = "grafo1.dat";
				servidor = "servidor1";
			}else if(rdbtnServidor2.isSelected()){
				i = 1100;
				grafo = "grafo2.dat";
				servidor = "servidor2";
			}else if(rdbtnServidor3.isSelected()){
				i = 1101;
				grafo = "grafo3.dat";
				servidor = "servidor3";
			}
			String portaS = textFieldPorta.getText();//recupera a porta do usuario
			try{
				i = Integer.parseInt(portaS);//converte a string em int
			}catch(NumberFormatException e){
				textArea.setText("Erro ao digitar porta, porta escolhida padrao:"+ i + "\n");//caso não seja valida a porta avisa ao usuario e usa a porta padrão
			}
			textFieldPorta.setText("" + i);
			controller.iniciaServer(i, textArea, grafo, servidor);//inicia o servidor
		}
	}
	
	public void atualizaIps(){
		String ip1 = textFieldS1Ip.getText();
		String ip2 = textFieldS2Ip.getText();
		String ip3 = textFieldS3Ip.getText();
		String porta1S = textFieldS1Porta.getText();
		String porta2S = textFieldS2Porta.getText();
		String porta3S = textFieldS3Porta.getText();
		try{
			int porta1 = Integer.parseInt(porta1S);
			int porta2 = Integer.parseInt(porta2S);
			int porta3 = Integer.parseInt(porta3S);
			controllerD.setIpServer1(ip1);
			controllerD.setIpServer2(ip2);
			controllerD.setIpServer3(ip3);
			controllerD.setPortaServer1(porta1);
			controllerD.setPortaServer2(porta2);
			controllerD.setPortaServer3(porta3);
			JOptionPane.showMessageDialog(null,"Pronto!","Atualizado!",2);//exibe recebido
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Digite um ip ou porta valido!","Erro",2);//exibe recebido
		}
		
		
	}
}
