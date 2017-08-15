package br.uefs.ecomp.SistemaDecolagem.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OrigemDestinoIguaisException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.VerticeNaoEncontradoException;

/**
 * 
 * @author Alyson Dantas
 *
 */

public class Caminho {
	private Grafo grafo;
	private String origemS;
	private String destinoS;
	private String geral;
	private List<Vertice> superiores;
	private List<Trajeto> trajeto;

	/**
	 * Construtor
	 * @param origem
	 * @param destino
	 * @throws CloneNotSupportedException
	 */
	public Caminho(String origem, String destino) throws CloneNotSupportedException{
		this.origemS = origem;
		this.geral = "";
		this.destinoS = destino;
		superiores = new ArrayList<Vertice>();
		trajeto = new ArrayList<Trajeto>();
		grafo = ControllerDadosServer.getInstance().getGrafoServer().clonado();
	}

	/**
	 * Metodo que cria um caminho
	 * @return
	 * @throws OrigemDestinoIguaisException
	 * @throws VerticeNaoEncontradoException
	 */
	public String criaCaminho() throws OrigemDestinoIguaisException, VerticeNaoEncontradoException{
		if(origemS.equals(destinoS)){
			throw new OrigemDestinoIguaisException();
		}


		Vertice origem = grafo.getVertice(origemS);
		Vertice destino = grafo.getVertice(destinoS);

		if(origem == null || destino == null){
			throw new VerticeNaoEncontradoException();
		}
		String s = "";
		String reserva = "";
		Trajeto t = null;
		Trajeto t2 = null;
		vaiFundo(origem,destino,s,reserva,t, t2);
		System.out.println(geral);
		return geral;
	}

	/**
	 * Metodo que percorre um caminho até o fundo
	 * @param atual
	 * @param destino
	 * @param s
	 * @param reserva
	 * @param ultimo
	 * @param anterior
	 */
	public void vaiFundo(Vertice atual, Vertice destino, String s, String reserva, Trajeto ultimo, Trajeto anterior){
		List<Aresta> listArestas = atual.getArestas();
		Iterator<Aresta> iteraArestas = listArestas.iterator();
		Aresta aux;
		s = s +"$" + atual.getNome() + "$";
		System.out.println("Entrou em " + atual.getNome() + " qtd de arestas " + listArestas.size());
		while(iteraArestas.hasNext()){
			aux = iteraArestas.next();
			System.out.println(atual.getNome() + " para " + aux.getDestino().getNome());
			Iterator<Vertice> iteraSuperiores = superiores.iterator();
			boolean verifica = false;
			Vertice vAux;
			while(iteraSuperiores.hasNext()){
				vAux = iteraSuperiores.next();
				if(vAux.getNome().equals(aux.getDestino().getNome())){
					System.out.println("Essa ja esta entre as superiores!");
					verifica = true;
				}
			}

			if(aux.isPassou() || verifica){
				System.out.println("Boolean aux " + aux.isPassou() + " boolean verifica " + verifica);
				System.out.println("Já passou por essa!");
				//return;
			}else{
				aux.setPassou(true);
				System.out.println("Nao passou por essa!");
				s =s +"@"+ aux.getNomeServer()+"@";
				if(aux.getDestino().equals(destino)){
					//geral = geral + s + "$" + destino.getNome() + "%";
					System.out.println("Chegou no fim");
					Iterator<Trajeto> iteraT = trajeto.iterator();
					Trajeto auxT;
					while(iteraT.hasNext()){
						auxT = iteraT.next();
						geral = geral + auxT.getOrigem().getNome() + "!" + auxT.getDestino().getDestino().getNome() + "!" + auxT.getDestino().getNomeServer() + "!";
					}
					geral = geral + atual.getNome() + "!" + aux.getDestino().getNome() +"!" + aux.getNomeServer() + "$";
					//superiores.add(atual);//previne a volta de uma aresta de um ponto para ele mesmo
					//trajeto.remove(ultimo);
					vaiFundo(atual,destino,reserva,reserva,anterior,anterior);
					//superiores.remove(atual);
					System.out.println("Removeu dos superiores " + atual.getNome());
					if(anterior!=null && anterior.getDestino()!=null){
						anterior.getDestino().setPassou(false);
					}
					aux.setPassou(false);
					return;
				}else{
					superiores.add(atual);
					Trajeto t = new Trajeto(atual,aux);
					anterior = ultimo;
					trajeto.add(t);
					ultimo = t;
					System.out.println("Add aos superiores " + atual.getNome());
					reserva = s + atual.getNome() + "$";
					vaiFundo(aux.getDestino(),destino,s,reserva,t,anterior);
					trajeto.remove(t);
					superiores.remove(atual);
					if(anterior!=null && anterior.getDestino()!=null){
						anterior.getDestino().setPassou(false);
						ultimo.getDestino().setPassou(false);
					}
					System.out.println("Removeu dos superiores " + atual.getNome());
					System.out.println("Voltou para " + atual.getNome());
				}
			}
		}
		//superiores.remove(atual);
		System.out.println("Removeu dos superiores " + atual.getNome());
		return;
	}
}
