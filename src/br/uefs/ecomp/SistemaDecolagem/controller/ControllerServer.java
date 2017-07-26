package br.uefs.ecomp.SistemaDecolagem.controller;

import java.net.ServerSocket;

import javax.swing.JTextArea;

import br.uefs.ecomp.SistemaDecolagem.threads.ThreadRecebeCliente;
import br.uefs.ecomp.SistemaDecolagem.threads.ThreadServeCliente;


public class ControllerServer {
	
	private static ControllerServer unicaInstancia;
	private ServerSocket server;
	private ThreadRecebeCliente thread;
	
	private ControllerServer(){
		
	}

	/**
	 * controla o instanciamento de objetos Controller
	 * @return unicaInstancia
	 */
	public static synchronized ControllerServer getInstance(){
		if(unicaInstancia==null){
			unicaInstancia = new ControllerServer();
		}
		return unicaInstancia;
	}

	/**
	 * reseta o objeto Controller ja instanciado
	 */
	public static void zerarSingleton (){
		unicaInstancia = null;
	}
	
	/**
	 * Metodo que inicia o servidor
	 * @param port Porta para o servidor
	 * @param textField TextField do log
	 * @return
	 */
	public String iniciaServer(int port, JTextArea textField){
		try {     
			System.out.println("Incializando o servidor...");
			textField.setText(textField.getText() + "Incializando o servidor... \n");

			server = new ServerSocket(port);//instancia um socket server na porta desejada
			System.out.println("Servidor iniciado, ouvindo a porta " + port);
			textField.setText(textField.getText() + "Servidor iniciado, ouvindo a porta " + port);//indica que o servidor foi ligado em determinada porta

			ThreadServeCliente threadGUI = new ThreadServeCliente(thread, textField, server);//thread que permite a atualização periodica da GUI
			threadGUI.start();

		}catch(Exception e){
			e.printStackTrace();//exibe a exceção que foi lançada
			textField.setText(textField.getText() + "Excecao ocorrida ao criar thread: " + e);//caso alguma exceção desconheciada seja lançada ela encerra a thread e é exibida
		}

		return null;
	}
}
