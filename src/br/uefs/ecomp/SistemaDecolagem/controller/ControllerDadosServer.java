package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.uefs.SistemaDecolagem.exceptions.*;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CadastroJaExistenteException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SenhaIncorretaException;
import br.uefs.ecomp.SistemaDecolagem.model.*;

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

	/**
	 * Metodo de cadastrar Uma nova Conta
	 * @param nome
	 * @param senha
	 * @throws CampoVazioException
	 * @throws IOException 
	 * @throws CadastroJaExistenteException 
	 */
	public void cadastrarConta(String nome, String senha) throws CampoVazioException, IOException, CadastroJaExistenteException{
		if (nome == null || nome.equals("") || senha == null || senha.equals("")) {
			throw new CampoVazioException();
		}

		criaCaminho();//cria o caminho
		File arquivos = new File("/sistemaDecolagem/clientes/");
		File todosarquivos[] = arquivos.listFiles();//verifica todos os arquivos que ja estão na pasta
		int cont = 0;
		for (int i = todosarquivos.length; cont < i; cont++) {//procura na pasta por algum cliente ja cadastrado para não conflitar
			File arquivo = todosarquivos[cont];
			if (arquivo.getName().equals(nome + ".dat")) {
				throw new CadastroJaExistenteException();
			}
			System.out.println(arquivo.getName());
		}

		Cliente cliente = new Cliente(nome, senha);
		escreveCliente(cliente);//escreve o cliente

	}

	/**
	 * Metodo que escreve o arquivo cliente
	 * @param cliente Cliente
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void escreveCliente(Cliente cliente) throws IOException {
		criaCaminho();//cria o caminho caso ele não exista
		ObjectOutputStream objectOutC = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("/sistemaDecolagem/clientes/" + cliente.getNome() + ".dat")));	//grava o objeto no caminho informado		
		objectOutC.writeObject(cliente);//escreve o arquivo
		objectOutC.flush();
		objectOutC.close();
	}

	/**
	 * Metedo que cria as pastas de acesso caso não ja existam
	 */
	private void criaCaminho() {
		File caminhoCliente = new File("\\sistemaDecolagem\\clientes"); // verifica se a pasta existe
		if (!caminhoCliente.exists()) {
			caminhoCliente.mkdirs(); //caso não exista cria a pasta
		}
	}

	/**
	 * Metodo de realizar login do cliente
	 * @param nome
	 * @param senha
	 * @return
	 * @throws CampoVazioException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SenhaIncorretaException
	 */
	public Cliente loginCliente(String nome, String senha) throws CampoVazioException, FileNotFoundException, IOException, ClassNotFoundException, SenhaIncorretaException {
		criaCaminho();//cria o caminho
		if (nome == null || nome.equals("") || senha == null || senha.equals("")) {
			throw new CampoVazioException();//lança exceção caso uma dos campos estejam vazios
		}
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();

		String a = cliente.getSenha();

		if (a.equals(senha)) {//se a agencia for a mesma retorna a conta
			return cliente;
		} else {//caso não retorna uma exceção
			throw new SenhaIncorretaException();
		}
	}
}
