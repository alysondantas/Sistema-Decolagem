package br.uefs.ecomp.SistemaDecolagem.controller;

public class ControllerCliente {
	
	private static ControllerCliente unicaInstancia;

	private ControllerCliente(){

	}

	/**
	 * controla o instanciamento de objetos Controller
	 * @return unicaInstancia
	 */
	public static synchronized ControllerCliente getInstance(){
		if(unicaInstancia==null){
			unicaInstancia = new ControllerCliente();
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
