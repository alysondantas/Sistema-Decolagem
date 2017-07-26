package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

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
		
		textFieldPorta = new JTextField();
		textFieldPorta.setColumns(10);
		textFieldPorta.setBounds(337, 202, 86, 20);
		frame.getContentPane().add(textFieldPorta);
		
		JLabel label = new JLabel("Porta:");
		label.setBounds(302, 206, 46, 14);
		frame.getContentPane().add(label);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(308, 250, 115, 23);
		btnStartServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				iniciaServer();
			}
		});
		frame.getContentPane().add(btnStartServer);
		
		rdbtnServidor = new JRadioButton("Servidor 1");
		rdbtnServidor.setSelected(true);
		tipo.add(rdbtnServidor);
		rdbtnServidor.setBounds(11, 198, 86, 23);
		frame.getContentPane().add(rdbtnServidor);
		
		rdbtnServidor2 = new JRadioButton("Servidor 2");
		tipo.add(rdbtnServidor2);
		rdbtnServidor2.setBounds(11, 224, 86, 23);
		frame.getContentPane().add(rdbtnServidor2);
		
		rdbtnServidor3 = new JRadioButton("Servidor 3");
		tipo.add(rdbtnServidor3);
		rdbtnServidor3.setBounds(11, 250, 86, 23);
		frame.getContentPane().add(rdbtnServidor3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 11, 412, 180);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setColumns(10);
		
		JButton btnSync = new JButton("sync");
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerDadosServer controllerD = ControllerDadosServer.getInstance();
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
				}
			}
		});
		btnSync.setBounds(209, 250, 89, 23);
		frame.getContentPane().add(btnSync);
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
}
