package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.FileNotFoundException;
import java.net.ServerSocket;

import javax.swing.JTextArea;

import br.uefs.ecomp.SistemaDecolagem.threads.ThreadConexaoRMI;
import br.uefs.ecomp.SistemaDecolagem.threads.ThreadRecebeCliente;
import br.uefs.ecomp.SistemaDecolagem.threads.ThreadServeCliente;

/**
 * 
 * @author Alyson Dantas
 *
 */

public class ControllerServer {

	private static ControllerServer unicaInstancia;
	private ServerSocket server;
	private ThreadRecebeCliente thread;

	/**
	 * Construtor
	 */
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
	public String iniciaServer(int port, JTextArea textField, String grafo, String servidor){
		try {
			System.out.println("Incializando o servidor...");
			textField.setText(textField.getText() + "Incializando o servidor... \n");

			ControllerDadosServer controllerDados = ControllerDadosServer.getInstance();
			controllerDados.setSeuServer(servidor);
			controllerDados.lerGrafo(grafo);
			textField.setText(textField.getText() + "Destinos e trechos atualizados... \n");//atualiza as cidades e trechos pelo arquivo

			ThreadConexaoRMI tConexaoRMI = new ThreadConexaoRMI(servidor,port+1000, textField);
			tConexaoRMI.start();

			server = new ServerSocket(port);//instancia um socket server na porta desejada
			System.out.println("Servidor iniciado, ouvindo a porta " + port);
			textField.setText(textField.getText() + "Servidor iniciado, ouvindo a porta " + port);//indica que o servidor foi ligado em determinada porta

			ThreadServeCliente threadGUI = new ThreadServeCliente(thread, textField, server);//thread que permite a atualiza��o periodica da GUI
			threadGUI.start();

		}catch(FileNotFoundException e){
			textField.setText(textField.getText() + "Erro arquivo de grafo do servidor n�o foi encontrado!");//caso alguma exce��o desconheciada seja lan�ada ela encerra a thread e � exibida
		}catch(Exception e){
			e.printStackTrace();//exibe a exce��o que foi lan�ada
			textField.setText(textField.getText() + "Excecao ocorrida ao criar conex�o: " + e);//caso alguma exce��o desconheciada seja lan�ada ela encerra a thread e � exibida
		}

		return null;
	}
}
