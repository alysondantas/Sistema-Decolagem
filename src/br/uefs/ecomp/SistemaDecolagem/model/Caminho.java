package br.uefs.ecomp.SistemaDecolagem.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OrigemDestinoIguaisException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.VerticeNaoEncontradoException;

public class Caminho {
	private Grafo grafo;
	private Grafo arvore;
	private String origemS;
	private String destinoS;
	private String geral;
	private List<Vertice> superiores;

	public Caminho(String origem, String destino) throws CloneNotSupportedException{
		this.origemS = origem;
		this.geral = "";
		this.destinoS = destino;
		superiores = new ArrayList<Vertice>();
		grafo = ControllerDadosServer.getInstance().getGrafoServer().clone();
	}

	public void criaCaminho() throws OrigemDestinoIguaisException, VerticeNaoEncontradoException{
		if(origemS.equals(destinoS)){
			throw new OrigemDestinoIguaisException();
		}


		Vertice origem = grafo.getVertice(origemS);
		Vertice destino = grafo.getVertice(destinoS);

		if(origem == null || destino == null){
			throw new VerticeNaoEncontradoException();
		}
		String s = "";
		vaiFundo(origem,destino,s);
		System.out.println(geral);
	}

	public void vaiFundo(Vertice atual, Vertice destino, String s){
		List<Aresta> listArestas = atual.getArestas();
		Iterator<Aresta> iteraArestas = listArestas.iterator();
		Aresta aux;
		s = s + atual.getNome() + "$";
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
				if(aux.getDestino().equals(destino)){
					geral = geral + s + destino.getNome() + "%";
					System.out.println("Chegou no fim");
					//superiores.add(atual);//previne a volta de uma aresta de um ponto para ele mesmo
					vaiFundo(atual,destino,s);
					aux.setPassou(false);
					return;
				}else{
					superiores.add(atual);
					System.out.println("Add aos superiores " + atual.getNome());
					vaiFundo(aux.getDestino(),destino,s);
					superiores.remove(atual);
					System.out.println("Removeu dos superiores " + atual.getNome());
					System.out.println("Voltou para " + atual.getNome());
				}
			}
		}
		return;
	}
}
