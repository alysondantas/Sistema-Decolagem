package br.uefs.ecomp.SistemaDecolagem.threads;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;

public class ThreadCliente extends Thread {
	private Socket cliente;//socket do cliente
	private ServerSocket server;//socket do servidor
	private JTextArea textField;//para atualizar a interface
	private ControllerDadosServer controller = ControllerDadosServer.getInstance();//instancia do controller

	/**
	 * Construtor
	 * @param server Servidor que aceita os clientes
	 * @param textField Area de log na interface
	 * @param cliente Cliente que ja foi aceito
	 */
	public ThreadCliente(ServerSocket server, JTextArea textField, Socket cliente) {//recebe o socket server e o textArea
		this.server = server; 
		this.cliente = cliente;
		this.textField = textField;
	}
}
