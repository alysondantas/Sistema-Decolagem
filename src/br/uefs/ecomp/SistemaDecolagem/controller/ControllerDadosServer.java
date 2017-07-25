package br.uefs.ecomp.SistemaDecolagem.controller;

import java.net.ServerSocket;

import br.uefs.ecomp.SistemaDecolagem.threads.ThreadCliente;

public class ControllerDadosServer {

	private static ControllerDadosServer unicaInstancia;
	
	
	private ControllerDadosServer(){
		
	}

	/**
	 * controla o instanciamento de objetos Controller
	 * @return unicaInstancia
	 */
	public static synchronized ControllerDadosServer getInstance(){
		if(unicaInstancia==null){
			unicaInstancia = new ControllerDadosServer();
		}
		return unicaInstancia;
	}

	/**
	 * reseta o objeto Controller ja instanciado
	 */
	public static void zerarSingleton (){
		unicaInstancia = null;
	}
}
