package br.uefs.ecomp.SistemaDecolagem.controller;

import br.uefs.ecomp.bancoCooperativo.controller.ControllerServer;

public class ControllerServer {
	
	private static ControllerServer unicaInstancia;
	
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
}
