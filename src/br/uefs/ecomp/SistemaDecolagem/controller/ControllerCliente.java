package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ControllerCliente {
	
	private static ControllerCliente unicaInstancia;
	private String ip;
	private int porta;
	private String nomeLogin;
	private String senhaLogin;

	private ControllerCliente(){
		nomeLogin = "";
		senhaLogin = "";
		ip = "192.168.15.4";
		porta = 1099;
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
	
	/**
	 * Metodo que se conecta ao servidor para cadastrar.
	 * @param senha
	 * @param nome
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String cadastrar(String senha, String nome) throws UnknownHostException, IOException, ClassNotFoundException{
		String pack = "0|" + nome + "|" + senha; //envia 0 para cadastrar o usuario e senha no protocolo
		//Cria o Socket para buscar o arquivo no servidor 
		Socket rec = new Socket(ip,porta);

		//Enviando o nome do arquivo a ser baixado do servidor
		ObjectOutputStream saida = new ObjectOutputStream(rec.getOutputStream());
		saida.writeObject(pack);
		saida.flush();

		ObjectInputStream entrada = new ObjectInputStream(rec.getInputStream());//recebo o pacote do cliente
		String recebido = (String) entrada.readObject(); 
		saida.close();//fecha a comunicação com o servidor
		entrada.close();
		rec.close();

		return recebido;
	}
	
	
}
