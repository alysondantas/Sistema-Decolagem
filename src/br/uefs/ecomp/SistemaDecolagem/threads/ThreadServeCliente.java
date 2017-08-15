package br.uefs.ecomp.SistemaDecolagem.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * 
 * @author Alyson Dantas
 *
 */

public class ThreadServeCliente extends Thread {

	private JTextArea textField;//para atualizar a interface
	private ThreadRecebeCliente thread;
	private ServerSocket server;


	/**
	 * Construtor
	 * @param thread Thread a ser iniciada
	 * @param textField TextField do log
	 * @param server Servidor para os clientes
	 */
	public ThreadServeCliente(ThreadRecebeCliente thread, JTextArea textField,ServerSocket server){
		this.textField = textField;
		this.thread = thread;
		this.server = server;
	}

	/**
	 * Metodo Run da thread
	 */
	public void run(){
		while(true){
			Socket cliente = null;
			try {
				cliente = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(cliente!=null){//caso o cliente n�o seja nulo
				thread = new ThreadRecebeCliente(server, textField, cliente);//passa parametros para thread como o socket server e a textArea
				thread.start();//inicia a thread
			}else{
				System.out.println("erro cliente nulo");
			}
		}
	}
	
}
