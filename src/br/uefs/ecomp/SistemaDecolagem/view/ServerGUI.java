package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerServer;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

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
		frame.setBounds(100, 100, 449, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(10);
		textArea.setBounds(11, 11, 412, 180);
		frame.getContentPane().add(textArea);
		
		textFieldPorta = new JTextField();
		textFieldPorta.setColumns(10);
		textFieldPorta.setBounds(46, 200, 86, 20);
		frame.getContentPane().add(textFieldPorta);
		
		JLabel label = new JLabel("Porta:");
		label.setBounds(11, 204, 46, 14);
		frame.getContentPane().add(label);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(308, 270, 115, 23);
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
		rdbtnServidor.setBounds(11, 227, 86, 23);
		frame.getContentPane().add(rdbtnServidor);
		
		rdbtnServidor2 = new JRadioButton("Servidor 2");
		tipo.add(rdbtnServidor2);
		rdbtnServidor2.setBounds(11, 253, 86, 23);
		frame.getContentPane().add(rdbtnServidor2);
		
		rdbtnServidor3 = new JRadioButton("Servidor 3");
		tipo.add(rdbtnServidor3);
		rdbtnServidor3.setBounds(11, 279, 86, 23);
		frame.getContentPane().add(rdbtnServidor3);
	}
	
	/**
	 * Metodo que incia o servidor
	 */
	private void iniciaServer(){
		if(controller != null){//se o controller existir
			btnStartServer.setEnabled(false);//desabilita o botão de inciar
			int i = 1099;//porta padrão
			String portaS = textFieldPorta.getText();//recupera a porta do usuario
			try{
				i = Integer.parseInt(portaS);//converte a string em int
			}catch(NumberFormatException e){
				textArea.setText("Erro ao digitar porta, porta escolhida padrao: 1099\n");//caso não seja valida a porta avisa ao usuario e usa a porta padrão
			}
			controller.iniciaServer(i, textArea);//inicia o servidor
		}
	}
}
