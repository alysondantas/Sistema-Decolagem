package br.uefs.ecomp.SistemaDecolagem.threads;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JTextArea;

import br.uefs.ecomp.SistemaDecolagem.model.ConexaoRMIImpl;
import br.uefs.ecomp.SistemaDecolagem.util.ConexaoRMI;


public class ThreadConexaoRMI extends Thread {
	private String nomeServer;
	private int port;
	private JTextArea textField;//para atualizar a interface
	
	public ThreadConexaoRMI(String nome, int port, JTextArea text){
		this.nomeServer = nome;
		this.port = port;
		this.textField = text;
	}
	
	/**
	 * Metodo Run da thread
	 */
	public void run(){
		try {
			System.out.println("RMI na porta " + port + " nome " + nomeServer);
			Registry reg = LocateRegistry.createRegistry(port); 
			//LocateRegistry.createRegistry(port);
			ConexaoRMI conexao = new ConexaoRMIImpl();
			reg.rebind(nomeServer, conexao);
			//Naming.bind(nomeServer, conexao);
			System.out.println("Conexão estabeleciada");
			textField.setText(textField.getText() + "\nServidor RMI iniciado, ouvindo a porta " + port +"\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao estabelecer conexão RMI "+ e.getMessage());
		}
		
		/*while(true){
			
		}*/
	}
}
