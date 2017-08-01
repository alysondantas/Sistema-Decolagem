package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class ControllerGui {
	private static ControllerGui unicaInstancia;
	private String ip;
	private int porta;
	
	private ControllerGui(){
		ip = "10.0.0.107";
		porta = 1099;
		
	}
	
	/**
	 * Metódo que reseta o objeto Controller ja instanciado.
	 */
	public static void zerarSingleton(){
		unicaInstancia = null;
	}
	
	/**
	 * Controla o instanciamento do objeto controller.
	 * @return unicaInstancia
	 */
	public static synchronized ControllerGui getInstance(){
		if(unicaInstancia==null){
			unicaInstancia = new ControllerGui();
		}
		return unicaInstancia;
	}
	
	/**
	 * Metódo que envia a origem e o destino, retornando o grafo.
	 * @param origem
	 * @param destino
	 * @return grafo
	 */
	public String buscarGrafo(String origem, String destino){
		String trajeto= null;
		
		return trajeto;
	}
	
	
	public String[] carregarOrigemDestino() throws UnknownHostException, IOException, ClassNotFoundException{
		String pack = "2|";
		
		Socket rec = new Socket(ip,porta);

		//Enviando o código do arquivo a ser baixado do servidor
		ObjectOutputStream saida = new ObjectOutputStream(rec.getOutputStream());
		saida.writeObject(pack);
		saida.flush();

		ObjectInputStream entrada = new ObjectInputStream(rec.getInputStream());//recebo o pacote do cliente
		String recebido = (String) entrada.readObject(); 
		saida.close();//fecha a comunicação com o servidor
		entrada.close();
		rec.close();
		
		String origemDestino[] = recebido.split(Pattern.quote("$"));//coloca no array cada vertice contendo origem e destino, visto que origem e destino são iguais.
		
		
		return origemDestino;
	}

}
