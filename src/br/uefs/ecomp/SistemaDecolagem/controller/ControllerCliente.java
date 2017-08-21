package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

/**
 * @author Nilo
 *
 */
/**
 * @author Nilo
 *
 */
/**
 * @author Nilo
 *
 */
public class ControllerCliente {
	
	private static ControllerCliente unicaInstancia;
	private String ip1;
	private int porta1;
	private String ip2;
	private int porta2;
	private String ip3;
	private int porta3;
	private String nomeLogin;
	private String senhaLogin;
	private static String servidorAtual;
	private static String ipAtual;
	private static int portaAtual;

	private ControllerCliente(){
		nomeLogin = "";
		senhaLogin = "";
		ip1 = "10.0.0.111";
		porta2 = 1100;
		ip2 = "10.0.0.107";
		porta2 = 1100;
		ip3 = "10.0.0.107";
		porta1 = 1101;
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
	 * Armazena o servidor atual que o cliente foi logado.
	 */
	public void setServidorAtual(String servidor){
		
		ControllerCliente.servidorAtual = servidor;
	}
	/**
	 *Armazena o ip atual do servidor utilizado. 
	 */
	public void setIpAtual(String ipAtual){
		ControllerCliente.ipAtual = ipAtual;
	}
	/**
	 *Armazena a porta atual utilizada pelo cliente. 
	 */
	public void setPortaAtual(int portaAtual){
		ControllerCliente.portaAtual = portaAtual;
		
	}
	
	/**
	 * Metodo que se conecta ao servidor para cadastrar.
	 * @param senha
	 * @param nome
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws OperacaoInvalidaException 
	 */
	public String cadastrar(String senha, String nome, String servidor) throws UnknownHostException, IOException, ClassNotFoundException, OperacaoInvalidaException{
		String pack = "0|" + nome + "|" + senha; //envia 0 para cadastrar o usuario e senha no protocolo
		//Cria o Socket para buscar o arquivo no servidor 
		Socket rec = null;
		if(servidor.equals("servidor1")){
			rec = new Socket(ip1,porta1);
		}else if(servidor.equals("servidor2")){
			rec = new Socket(ip2,porta2);
		}else if(servidor.equals("servidor3")){
			rec = new Socket(ip3,porta3);
		}else{
			throw new OperacaoInvalidaException();
		}

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
	

	/**
	 * Metódo que envia ao servidor informação de login e verifica se está logado ou não.
	 * @param senha
	 * @param nome
	 * @return boolean
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws OperacaoInvalidaException 
	 */
	public boolean estaLogado(String senha, String nome,String servidor)throws UnknownHostException, IOException, ClassNotFoundException, OperacaoInvalidaException{
		String pack = "1|" + nome + "|" + senha;//Envia 1 para logar no servidor.
		
		Socket rec = null;
		if(servidor.equals("servidor1")){
			rec = new Socket(ip1,porta1);
		}else if(servidor.equals("servidor2")){
			rec = new Socket(ip2,porta2);
		}else if(servidor.equals("servidor3")){
			rec = new Socket(ip3,porta3);
		}else{
			throw new OperacaoInvalidaException();
		}
		ObjectOutputStream saida = new ObjectOutputStream(rec.getOutputStream());//objeto de saida.
		saida.writeObject(pack);//envia o objeto.
		saida.flush();
		
		ObjectInputStream entrada = new ObjectInputStream(rec.getInputStream());//objeto de entrada.
		String recebido = (String) entrada.readObject();
		saida.close();
		entrada.close();
		rec.close();
		
		if(recebido.equals("concluido")){//retorna verdadeiro ou falso caso haja uma conta no servidor.
			return true;
		}	
		else {return false;}
	}
	
	/**
	 * Metodo que atualiza os ips e portas
	 * @param ip1
	 * @param porta1
	 * @param ip2
	 * @param porta2
	 * @param ip3
	 * @param porta3
	 * @throws CampoVazioException
	 */
	public void atualizaIpPorta(String ip1, int porta1, String ip2, int porta2, String ip3, int porta3) throws CampoVazioException{
		if(ip1 == null || ip1.equals("") || ip2 == null || ip2.equals("") || ip3 == null || ip3.equals("")){
			throw new CampoVazioException();
		}
		this.ip1 = ip1;
		this.ip2 = ip2;
		this.ip3 = ip3;
		this.porta1 = porta1;
		this.porta2 = porta2;
		this.porta3 = porta3;
	}
	/**
	 * Metodo que solicita e recebe os vertices do servidor.
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String[] carregarOrigemDestino() throws UnknownHostException, IOException, ClassNotFoundException{
		String pack = "2|";
		
		//Socket rec = new Socket(ControllerCliente.ipAtual,ControllerCliente.portaAtual);
		Socket rec = new Socket(ipAtual,portaAtual);

		//Enviando o código do arquivo a ser baixado do servidor
		ObjectOutputStream saida = new ObjectOutputStream(rec.getOutputStream());
		saida.writeObject(pack);
		saida.flush();

		ObjectInputStream entrada = new ObjectInputStream(rec.getInputStream());//recebo o pacote do cliente
		String recebido = (String) entrada.readObject(); 
		saida.close();//fecha a comunicação com o servidor
		entrada.close();
		rec.close();
		
		String [] origemDestino = new String[5];
		 origemDestino = recebido.split(Pattern.quote("$"));//coloca no array cada vertice contendo origem e destino, visto que origem e destino são iguais.
		
		
		return origemDestino;
	}
	
}
