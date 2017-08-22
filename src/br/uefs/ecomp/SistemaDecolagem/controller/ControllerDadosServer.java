package br.uefs.ecomp.SistemaDecolagem.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CadastroJaExistenteException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OrigemDestinoIguaisException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SemVagasException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SenhaIncorretaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.VerticeNaoEncontradoException;
import br.uefs.ecomp.SistemaDecolagem.model.*;
import br.uefs.ecomp.SistemaDecolagem.util.ConexaoRMI;
/**
 * 
 * @author Alyson Dantas
 *
 */
public class ControllerDadosServer {
	private static ControllerDadosServer unicaInstancia;
	private Grafo grafo;
	private Grafo grafoServers;
	private String seuNomeServer;
	private int portServer1 = 1099;
	private String ipServer1 = "192.168.15.5";
	private int portServer2 = 1100;
	private String ipServer2 = "192.168.15.5";
	private int portServer3 = 1101;
	private String ipServer3 = "192.168.15.5";
	private ConexaoRMI conexaoRMI2;
	private ConexaoRMI conexaoRMI1;
	private String nomeServidor1 = "servidor1";
	private String nomeServidor2 = "servidor2";
	private String lipServer1 = ipServer1;
	private String lipServer2 = ipServer2;
	private int lportServer2 = portServer2 + 1000;
	private int lportServer1 = portServer1 + 1000;
	private boolean regiaoCritica = false;
	private String clienteNaRegiao = "";
	private int timeStamp = 0;


	/**
	 * Construtor
	 */
	private ControllerDadosServer(){
		grafo = new Grafo();
		grafoServers = new Grafo();
		seuNomeServer = "";
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
		String caminho = "clientes";
		criaCaminho(caminho);//cria o caminho
		File arquivos = new File("/sistemaDecolagem/" + seuNomeServer + "/clientes/");
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
		String caminho = "clientes";
		criaCaminho(caminho);//cria o caminho caso ele não exista
		ObjectOutputStream objectOutC = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("/sistemaDecolagem/" + seuNomeServer + "/clientes/"+ cliente.getNome() + ".dat")));	//grava o objeto no caminho informado		
		objectOutC.writeObject(cliente);//escreve o arquivo
		objectOutC.flush();
		objectOutC.close();
	}

	/**
	 * Metedo que cria as pastas de acesso caso não ja existam
	 */
	private void criaCaminho(String caminho) {
		File caminhoCliente = new File("\\sistemaDecolagem\\" + seuNomeServer + "\\" + caminho); // verifica se a pasta existe
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
		String caminho = "clientes";
		criaCaminho(caminho);//cria o caminho
		if (nome == null || nome.equals("") || senha == null || senha.equals("")) {
			throw new CampoVazioException();//lança exceção caso uma dos campos estejam vazios
		}
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/" + seuNomeServer +"/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();

		String a = cliente.getSenha();

		if (a.equals(senha)) {//se a agencia for a mesma retorna a conta
			return cliente;
		} else {//caso não retorna uma exceção
			throw new SenhaIncorretaException();
		}
	}

	/**
	 * Metodo que faz a leitura do arquivo de grafo e estrutura as rotas
	 * @param arquivo
	 * @throws OperacaoInvalidaException
	 * @throws IOException
	 */
	public void lerGrafo(String arquivo) throws OperacaoInvalidaException, IOException{
		String caminho = "grafo";
		criaCaminho(caminho);
		String linha = null;
		FileReader arq = new FileReader("/sistemaDecolagem/grafo/" + arquivo);
		BufferedReader lerArq = new BufferedReader(arq);
		linha = lerArq.readLine(); // lê a primeira linha
		arq.close();

		if(linha != null){
			System.out.println("Arquivo lido " + linha);
			String informacoes[] = linha.split(Pattern.quote("!"));
			String cidades[] = informacoes[0].split(Pattern.quote("%"));
			int i = cidades.length;
			//adiciona as cidades ao grafo
			for(i = 0 ; i < cidades.length ; i++){
				String cidade = cidades[i];
				System.out.println("Nova cidade add " + cidade);
				Vertice novaCidade = new Vertice(cidade);
				grafo.inserirPonto(novaCidade);
			}

			String rotasNovas[] = informacoes[1].split(Pattern.quote("$"));
			i = rotasNovas.length;
			//adiciona as rotas
			for(i = 0 ; i < rotasNovas.length ; i++){
				String rota[] = rotasNovas[i].split(Pattern.quote("#"));
				Vertice destino = grafo.getVertice(rota[1]);
				Vertice origem = grafo.getVertice(rota[0]);
				if(destino != null && origem !=null){
					Aresta nova = new Aresta(destino, Integer.parseInt(rota[2]),seuNomeServer);
					System.out.println("Novo trecho de " + origem.getNome() + " para " + destino.getNome() + " com " + nova.getPoltronasLivres() + " proltronas para o server " + seuNomeServer);
					origem.addAresta(nova);
				}else{
					extracted();
				}
			}
		}	
	}

	private void extracted() throws OperacaoInvalidaException {
		throw new OperacaoInvalidaException();
	}

	/**
	 * Metodo que sincroniza os Grafos dos 3 servidores
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws VerticeNaoEncontradoException 
	 */
	public void syncGrafos() throws MalformedURLException, RemoteException, NotBoundException, VerticeNaoEncontradoException{
		if(seuNomeServer.equals("servidor1")){
			nomeServidor1 = "servidor2";
			nomeServidor2 = "servidor3";
			lportServer1 = portServer2 + 1000;
			lportServer2 = portServer3 + 1000;
			lipServer1 = ipServer2;
			lipServer2 = ipServer3;
		}else if(seuNomeServer.equals("servidor2")){
			nomeServidor1 = "servidor1";
			nomeServidor2 = "servidor3";
			lportServer1 = portServer1 + 1000;
			lportServer2 = portServer3 + 1000;
			lipServer1 = ipServer1;
			lipServer2 = ipServer3;
		}else if(seuNomeServer.equals("servidor3")){
			nomeServidor1 = "servidor1";
			nomeServidor2 = "servidor2";
			lportServer1 = portServer1 + 1000;
			lportServer2 = portServer2 + 1000;
			lipServer1 = ipServer1;
			lipServer2 = ipServer2;
		}

		conexaoRMI1 = (ConexaoRMI) Naming.lookup( "rmi://"+lipServer1+":"+lportServer1+"/" + nomeServidor1);
		Grafo grafo1 = conexaoRMI1.getGrafo();
		conexaoRMI2 = (ConexaoRMI) Naming.lookup( "rmi://"+lipServer2+":"+lportServer2+"/" + nomeServidor2);
		Grafo grafo2 = conexaoRMI2.getGrafo();

		grafoServers = new Grafo();

		mesclaGrafo(grafoServers,grafo);
		mesclaGrafo(grafoServers,grafo1);
		mesclaGrafo(grafoServers,grafo2);

		organiza();

		//Printa o novo grafo
		Iterator<Vertice> iteraGrafoS = grafoServers.iterador();
		Vertice aux = null;
		Iterator<Aresta> iteraAresta;
		Aresta arestinha = null;
		while(iteraGrafoS.hasNext()){
			aux = iteraGrafoS.next();
			System.out.println("Vertice: " + aux.getNome());
			iteraAresta = aux.getArestas().iterator();
			while(iteraAresta.hasNext()){
				arestinha = iteraAresta.next();
				System.out.println("Aresta para " + arestinha.getDestino().getNome() + " do server " + arestinha.getNomeServer() + " de " + aux.getNome());
			}
		}
	}

	/**
	 * Metodo que mescla dois grafos em um
	 * @param principal
	 * @param pequeno
	 */
	public void mesclaGrafo(Grafo principal, Grafo pequeno){
		Iterator<Vertice> iteraGrafoS = principal.iterador();
		Iterator<Vertice> iteraGrafoM = pequeno.iterador();
		List<Aresta> arestas;
		List<Aresta> arestas2;
		Iterator<Aresta> iteraAresta;
		Aresta arestinha;

		Vertice aux = null;
		Vertice aux2 = null;
		boolean verifica = false;

		while(iteraGrafoM.hasNext()){
			aux = iteraGrafoM.next();
			while(iteraGrafoS.hasNext()){
				aux2 = iteraGrafoS.next();
				if(aux.getNome().equals(aux2.getNome())){
					verifica = true;
					System.out.println("Vertice identico encontrado" + aux.getNome());
					arestas = aux.getArestas();
					arestas2 = aux2.getArestas();
					iteraAresta = arestas.iterator();
					while(iteraAresta.hasNext()){
						arestinha = iteraAresta.next();
						if(!verificaArestaIgual(grafoServers, arestinha, aux)){
							arestas2.add(arestinha);
							System.out.println("Nova rota de"+aux2.getNome() + " para "+arestinha.getDestino().getNome());
						}else{
							System.out.println("Nao add rota de"+aux2.getNome() + " para "+arestinha.getDestino().getNome());
						}
					}
					break;
				}
			}
			if(verifica == false){
				System.out.println("Novo vertice add " + aux.getNome());
				grafoServers.inserirPonto(aux);
			}
			verifica = false;
			iteraGrafoS = grafoServers.iterador();
		}
	}

	/**
	 * Metodo que verifica se ja existe essa aresta no grafo
	 * @param grafo
	 * @param aresta
	 * @return
	 */
	public boolean verificaArestaIgual(Grafo g, Aresta a, Vertice vertice){
		Iterator<Vertice> iteraV = g.iterador();
		Vertice v;
		List<Aresta> listA;
		Iterator<Aresta> iteraA;
		Aresta aux;
		while(iteraV.hasNext()){
			v = iteraV.next();
			listA = v.getArestas();
			iteraA = listA.iterator();
			while(iteraA.hasNext()){
				aux = iteraA.next();
				if(v.getNome().equals(vertice.getNome()) && aux.getNomeServer().equals(a.getNomeServer()) && aux.getDestino().getNome().equals(a.getDestino().getNome())){
					System.out.println("origem " + v.getNome() + " | " + vertice.getNome());
					System.out.println("nome server" + aux.getNomeServer() +" | " +  a.getNomeServer());
					System.out.println("destino " + aux.getDestino().getNome() + " | " + a.getDestino().getNome());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo que organiza os vertices
	 * @throws VerticeNaoEncontradoException
	 */
	public void organiza() throws VerticeNaoEncontradoException{
		Iterator<Vertice> iteraVertice = grafoServers.iterador();
		Iterator<Aresta> iteraAresta;
		Vertice v;
		List<Aresta> arestas;
		Aresta a;
		Vertice aux;
		while(iteraVertice.hasNext()){
			v = iteraVertice.next();
			arestas = v.getArestas();
			iteraAresta = arestas.iterator();
			while(iteraAresta.hasNext()){
				a = iteraAresta.next();
				aux = grafoServers.getVertice(a.getDestino().getNome());
				if(aux != null){
					a.setDestino(aux);
				}else{
					throw new VerticeNaoEncontradoException();
				}
			}
		}
	}

	/**
	 * Metodo que solicita todos os vertices
	 * @return
	 */
	public String solicitaTodosVertices(){
		Iterator<Vertice> iteraV = grafoServers.iterador();
		Vertice aux;
		String todos = "";
		while(iteraV.hasNext()){
			aux = iteraV.next();
			//todos = todos + aux.getNome() + "$";
			System.out.println("esta no" + aux.getNome());
			if(!iteraV.hasNext()){
				todos = todos + aux.getNome();
			}else{
				todos = todos + aux.getNome() + "$";
			}
		}
		return todos;
	}

	/**
	 * Metodo que retorna o trajeto entre dois pontos
	 * @param origem
	 * @param destino
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws OrigemDestinoIguaisException
	 * @throws VerticeNaoEncontradoException
	 */
	public String getTrajeto(String origem, String destino) throws CloneNotSupportedException, OrigemDestinoIguaisException, VerticeNaoEncontradoException{
		Caminho caminho = new Caminho(origem,destino);
		String s = caminho.criaCaminho();
		String informacoes[] = s.split(Pattern.quote("$"));
		int i;
		List<String> correcao = new ArrayList<String>();
		Iterator<String> iteraC;
		String aux;
		boolean verifica = false;
		for(i = 0; i < informacoes.length ; i++){
			if(!informacoes[i].equals("")){
				iteraC = correcao.iterator();
				while(iteraC.hasNext()){
					aux = iteraC.next();
					if(aux.equals(informacoes[i])){
						verifica = true;
					}
				}
				if(verifica == false){
					correcao.add(informacoes[i]);
				}else{
					verifica = false;
				}
			}
		}
		String corrigida = "";
		iteraC = correcao.iterator();
		while(iteraC.hasNext()){
			aux = iteraC.next();
			corrigida = corrigida + aux;
			if(iteraC.hasNext()){
				corrigida = corrigida + "$";
			}
		}
		System.out.println("Correção " + corrigida);
		return corrigida;
	}

	/**
	 * Metodo que direciona a compra de um trecho
	 * @param cliente
	 * @param origem
	 * @param destino
	 * @param servidor
	 * @return
	 * @throws CampoVazioException
	 * @throws SemVagasException
	 * @throws OperacaoInvalidaException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public boolean compraCaminho(String cliente, String origem,String destino, String servidor, String destinoFinal) throws CampoVazioException, SemVagasException, OperacaoInvalidaException, FileNotFoundException, ClassNotFoundException, IOException, InterruptedException{
		if(origem == null || origem.equals("") || destino == null || destino.equals("") || servidor == null || servidor.equals("") || cliente == null || cliente.equals("") || destinoFinal == null || destinoFinal.equals("")){
			throw new CampoVazioException();
		}

		//aqui onde verifica se pode comprar algo
		if(regiaoCritica){
			if(cliente.equals(clienteNaRegiao)){
				return direcionaCompra(cliente, origem, destino, servidor,destinoFinal);
			}
		}else{
			solicitaAcessoCritico(cliente);
		}
		Thread.sleep(500);
		timeStamp++;
		return compraCaminho(cliente, origem, destino, servidor, destinoFinal);
	}

	/**
	 * Metodo que solicita acesso a região critica
	 * @param cliente
	 * @throws RemoteException
	 */
	public void solicitaAcessoCritico(String cliente) throws RemoteException{
		boolean resposta = conexaoRMI1.regiaoCriticaAcesso(timeStamp);
		if(!resposta){
			resposta = conexaoRMI2.regiaoCriticaAcesso(timeStamp);
			if(!resposta){
				regiaoCritica = true;
				clienteNaRegiao = cliente;
			}
		}
	}

	/**
	 * Metodo que direciona a compra de um cliente para o servidor correto
	 * @param cliente
	 * @param origem
	 * @param destino
	 * @param servidor
	 * @param destinoFinal
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SemVagasException
	 * @throws OperacaoInvalidaException
	 * @throws IOException
	 */
	private boolean direcionaCompra(String cliente,String origem, String destino, String servidor, String destinoFinal) throws FileNotFoundException, ClassNotFoundException, SemVagasException, OperacaoInvalidaException, IOException{
		if(servidor.equals(seuNomeServer)){
			return realizaCompra(cliente,origem,destino,destinoFinal);
		}else if(servidor.equals(nomeServidor1)){
			//conexaoRMI1
			Trajeto comprado = conexaoRMI1.comprarTrecho(origem, destino);
			if(comprado != null){
				atualizaCliente(cliente,comprado);
				if(destino.equals(destinoFinal)){
					regiaoCritica = false;
					clienteNaRegiao = "";
					timeStamp = 0;
				}
				return true;
			}else{
				regiaoCritica = false;
				clienteNaRegiao = "";
				return false;
			}
			//envia para o servidor que possui a trajetoria
		}else if(servidor.equals(nomeServidor2)){
			//conexaoRMI2
			Trajeto comprado = conexaoRMI2.comprarTrecho(origem, destino);
			if(comprado != null){
				atualizaCliente(cliente,comprado);
				if(destino.equals(destinoFinal)){
					regiaoCritica = false;
					clienteNaRegiao = "";
				}
				timeStamp = 0;
				return true;
			}else{
				regiaoCritica = false;
				clienteNaRegiao = "";
				return false;
			}
		}
		regiaoCritica = false;
		clienteNaRegiao = "";
		return false;
	}

	/**
	 * Metodo que recebe a solicitação de compra de outro servidor
	 * @param origem
	 * @param destino
	 * @return
	 * @throws SemVagasException
	 * @throws OperacaoInvalidaException
	 */
	public Trajeto recebeCompraDeOutro(String origem,String destino) throws SemVagasException, OperacaoInvalidaException{
		Aresta aresta = grafo.getAresta(origem, destino);
		if(aresta.getPoltronasLivres()<1){
			throw new SemVagasException();
		}else{
			aresta.decrementaPoltronasLivres();
			Vertice v = grafo.getVertice(origem);
			Trajeto t = new Trajeto(v,aresta);
			return t;
		}
	}

	/**
	 * Metodo que raliza a compra de um trecho
	 * @param cliente
	 * @param origem
	 * @param destino
	 * @return
	 * @throws SemVagasException
	 * @throws OperacaoInvalidaException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean realizaCompra(String cliente, String origem, String destino, String destinoFinal) throws SemVagasException, OperacaoInvalidaException, FileNotFoundException, ClassNotFoundException, IOException{
		Aresta aresta = grafo.getAresta(origem, destino);
		if(aresta.getPoltronasLivres()<1){
			regiaoCritica = false;
			clienteNaRegiao = "";
			timeStamp = 0;
			throw new SemVagasException();
		}else{
			aresta.decrementaPoltronasLivres();
			Vertice v = grafo.getVertice(origem);
			Trajeto t = new Trajeto(v,aresta);
			atualizaCliente(cliente,t);
			if(destino.equals(destinoFinal)){
				regiaoCritica = false;
				clienteNaRegiao = "";
			}
			return true;
		}
	}

	/**
	 * Metodo que atualiza os clientes e suas compras
	 * @param nome
	 * @param t
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void atualizaCliente(String nome, Trajeto t) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/" + seuNomeServer +"/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();
		cliente.addTrajeto(t);
		escreveCliente(cliente);
	}

	/**
	 * Metodo que atualiza os clientes e suas compras
	 * @param nome
	 * @param t
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void atualizaClienteReserva(String nome, Trajeto t) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/" + seuNomeServer +"/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();
		cliente.addTrajetoReserva(t);
		System.out.println("cliente atualizado " + nome);
		escreveCliente(cliente);
	}

	/**
	 * Metodo que coloca um cliente em reserva
	 * @param origem
	 * @param destino
	 * @param cliente
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean entrarReserva(String origem, String destino,String cliente) throws FileNotFoundException, ClassNotFoundException, IOException{
		System.out.println("reserva de " + origem + " para " + destino);
		Aresta aresta = grafo.getAresta(origem, destino);
		System.out.println("Vagas: " + aresta.getPoltronasLivres());
		if(aresta.getPoltronasLivres()>1){
			System.out.println("aresta encontrada com vagas!");
			ClienteServer cli = new ClienteServer(cliente, seuNomeServer);
			Vertice v = grafo.getVertice(origem);
			Trajeto t = new Trajeto(v,aresta);
			atualizaClienteReserva(cliente,t);

			return aresta.addReserva(cli);
		}else{

			return false;
		}

	}

	/**
	 * Metodo que recebe as reservas de trechos
	 * @param origem
	 * @param destino
	 * @param cliente
	 * @param servidor
	 * @return
	 * @throws CampoVazioException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean reservarTrecho(String origem, String destino, String cliente, String servidor) throws CampoVazioException, FileNotFoundException, ClassNotFoundException, IOException{
		if(origem == null || origem.equals("") || destino == null || destino.equals("") || servidor == null || servidor.equals("") || cliente == null || cliente.equals("")){
			throw new CampoVazioException();
		}

		System.out.println("Cliente: " + cliente);
		if(servidor.equals(seuNomeServer)){
			System.out.println("Solicitação para o servidor atual: ");
			return entrarReserva(origem, destino, cliente);
		}else if(servidor.equals(nomeServidor1)){
			//conexaoRMI1
			System.out.println("Solicitação para outro servidor " + servidor);
			Trajeto comprado = conexaoRMI1.reservaTrecho(origem, destino, cliente, seuNomeServer);
			if(comprado != null){
				atualizaClienteReserva(cliente,comprado);
				return true;
			}else{
				return false;
			}
			//envia para o servidor que possui a trajetoria
		}else if(servidor.equals(nomeServidor2)){
			//conexaoRMI1
			System.out.println("Solicitação para outro servidor " + servidor);
			Trajeto comprado = conexaoRMI2.reservaTrecho(origem, destino, cliente, seuNomeServer);
			if(comprado != null){
				atualizaClienteReserva(cliente,comprado);
				return true;
			}else{
				return false;
			}
			//envia para o servidor que possui a trajetoria
		}
		System.out.println("Servidor invalido");
		return false;
	}

	/**
	 * Metodo que recebe reserva de outro servidor
	 * @param cliente
	 * @param origem
	 * @param destino
	 * @param server
	 * @return
	 */
	public Trajeto recebeReserva(String cliente, String origem, String destino, String server){
		Aresta aresta = grafo.getAresta(origem, destino);
		if(aresta.getPoltronasLivres()<1){
			ClienteServer cli = new ClienteServer(cliente, server);
			Vertice v = grafo.getVertice(origem);
			Trajeto t = new Trajeto(v,aresta);
			if(aresta.addReserva(cli)){
				return t;
			}
		}
		return null;
	}

	/**
	 * Metodo que recebe solicitação de cancelar reserva
	 * @param cliente
	 * @param origem
	 * @param destino
	 * @param servidor
	 * @return
	 * @throws CampoVazioException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean cancelaReserva(String cliente, String origem, String destino, String servidor) throws CampoVazioException, FileNotFoundException, ClassNotFoundException, IOException{
		if(origem == null || origem.equals("") || destino == null || destino.equals("") || servidor == null || servidor.equals("") || cliente == null || cliente.equals("")){
			throw new CampoVazioException();
		}
		if(servidor.equals(seuNomeServer)){
			boolean t = removeReserva(origem, destino, cliente,seuNomeServer);
			if(t){
				atualizaClienteRemoveReserva(cliente,origem,destino);
				return true;
			}else{
				return false;
			}
		}else if(servidor.equals(nomeServidor2)){
			boolean b = conexaoRMI2.recebeCancelaReserva(origem, destino, cliente, seuNomeServer);
			if(b){
				atualizaClienteRemoveReserva(cliente, origem, destino);
				return true;
			}else{
				return false;
			}
		}else if(servidor.equals(nomeServidor1)){
			boolean b = conexaoRMI1.recebeCancelaReserva(origem, destino, cliente, seuNomeServer);
			if(b){
				atualizaClienteRemoveReserva(cliente, origem, destino);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	/**
	 * Metodo que atualiza os clientes e suas compras
	 * @param nome
	 * @param t
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void atualizaClienteRemoveReserva(String nome, String origem, String destino) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/" + seuNomeServer +"/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();
		Iterator<Trajeto> itera = cliente.getTrajetosReservados().iterator();
		Trajeto aux = null;
		Aresta aresta = grafo.getAresta(origem, destino);
		while(itera.hasNext()){
			aux = itera.next();
			if(aux.getDestino().equals(aresta)){
				break;
			}
		}
		cliente.getTrajetosReservados().remove(aux);
		escreveCliente(cliente);
	}

	/**
	 * Metodo que remove reserva
	 * @param origem
	 * @param destino
	 * @param cliente
	 * @param server
	 * @return
	 */
	public boolean removeReserva(String origem, String destino, String cliente,String server){
		Aresta aresta = grafo.getAresta(origem, destino);
		return aresta.removeReserva(cliente,server);
	}

	/**
	 * Metodo que recebe solicitação de finalizar uma viagem
	 * @param viagem
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws OperacaoInvalidaException
	 * @throws IOException
	 */
	public boolean finalizaViagem(String viagem) throws FileNotFoundException, ClassNotFoundException, OperacaoInvalidaException, IOException{
		String informacoes[] = viagem.split(Pattern.quote("$"));
		int i;
		for(i = 0 ; i < informacoes.length ; i++){
			String aux[] = informacoes[i].split(Pattern.quote("!"));
			if(aux[2].equals(seuNomeServer)){
				recebeFinalizaViagem(aux[0], aux[1]);
			}else if(aux[2].equals(nomeServidor1)){
				conexaoRMI1.finalizaViagem(aux[0], aux[1]);
			}else if(aux[2].equals(nomeServidor2)){
				conexaoRMI2.finalizaViagem(aux[0], aux[1]);
			}
		}

		return false;
	}

	/**
	 * Metodo que finaliza uma viagem e incrementa as poltronas livres
	 * @param origem
	 * @param destino
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws OperacaoInvalidaException
	 * @throws IOException
	 */
	public boolean recebeFinalizaViagem(String origem,String destino) throws FileNotFoundException, ClassNotFoundException, OperacaoInvalidaException, IOException{
		Aresta a = grafo.getAresta(origem, destino);
		if(a != null){
			return a.incrementaPoltronasLivres();
		}else return false;
	}

	/**
	 * Metodo que valida quando um cliente saiu da espera por uma vaga
	 * @param cliente
	 * @param aresta
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean saiuReserva(ClienteServer cliente, Aresta aresta) throws FileNotFoundException, ClassNotFoundException, IOException{
		if(cliente.getServer().equals(seuNomeServer)){
			return atualizaClienteSaiuReserva(cliente.getNome(),aresta);
		}else if(cliente.getServer().equals(nomeServidor1)){
			return conexaoRMI1.recebeSaiuReserva(cliente.getNome(), aresta);
		}else if(cliente.getServer().equals(nomeServidor2)){
			return conexaoRMI2.recebeSaiuReserva(cliente.getNome(), aresta);
		}
		return false;
	}

	/**
	 * Metodo que atualiza a saida de um cliente da espera
	 * @param nome
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean atualizaClienteSaiuReserva(String nome, Aresta a) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/sistemaDecolagem/" + seuNomeServer +"/clientes/" + nome + ".dat")));//recupera a conta
		Cliente cliente = (Cliente) objectIn.readObject();
		objectIn.close();
		Iterator<Trajeto> itera = cliente.getTrajetosReservados().iterator();
		Trajeto aux = null;
		while(itera.hasNext()){
			aux = itera.next();
			if(aux.getDestino().getDestino().getNome().equals(a.getDestino().getNome()) && aux.getDestino().getNomeServer().equals(a.getNomeServer())){
				break;
			}
		}
		if(aux != null){
			boolean b = cliente.removeTrajetoReserva(aux);
			cliente.addTrajetoReserva(aux);
			escreveCliente(cliente);
			return b;
		}
		return false;
	}

	public String getSeuServer() {
		return seuNomeServer;
	}

	public void setSeuServer(String seuServer) {
		this.seuNomeServer = seuServer;
	}

	public Grafo getGrafo(){
		return grafo;
	}

	public String getIpServer1() {
		return ipServer1;
	}

	public void setIpServer1(String ipServer3) {
		this.ipServer1 = ipServer3;
	}

	public String getIpServer2() {
		return ipServer2;
	}

	public void setIpServer2(String ipServer3) {
		this.ipServer2 = ipServer3;
	}

	public String getIpServer3() {
		return ipServer3;
	}

	public void setIpServer3(String ipServer3) {
		this.ipServer3 = ipServer3;
	}

	public int getPortaServer1(){
		return portServer1;
	}

	public int getPortaServer2(){
		return portServer2;
	}

	public int getPortaServer3(){
		return portServer3;
	}

	public void setPortaServer1(int porta){
		this.portServer1 = porta;
	}

	public void setPortaServer2(int porta){
		this.portServer2 = porta;
	}

	public void setPortaServer3(int porta){
		this.portServer3 = porta;
	}

	public Grafo getGrafoServer(){
		return grafoServers;
	}

	public boolean getRegiaoCritica(){
		return regiaoCritica;
	}

	public int getTimeStamp(){
		return timeStamp;
	}
}
