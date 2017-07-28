package br.uefs.ecomp.SistemaDecolagem.model;

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
	
	public Caminho(String origem, String destino) throws CloneNotSupportedException{
		this.origemS = origem;
		this.geral = "";
		this.destinoS = destino;
		grafo = ControllerDadosServer.getInstance().getGrafo().clone();
	}
	
	public void criaCaminho() throws OrigemDestinoIguaisException, VerticeNaoEncontradoException{
		if(origemS.equals(destinoS)){
			throw new OrigemDestinoIguaisException();
		}
		Iterator<Vertice> iteraG = grafo.iterador();
		Vertice aux;
		Vertice origem = null;
		Vertice destino = null;
		while(iteraG.hasNext()){
			aux = iteraG.next();
			if(aux.getNome().equals(origemS)){
				origem = aux;
			}
			if(aux.getNome().equals(destinoS)){
				destino = aux;
			}
		}
		
		if(origem == null || destino == null){
			throw new VerticeNaoEncontradoException();
		}
		String s = "";
		vaiFundo(origem,destino,s);
		System.out.println("Caminho: + " + geral);
	}
	
	public void vaiFundo(Vertice atual, Vertice destino, String s){
		List<Aresta> listArestas = atual.getArestas();
		Iterator<Aresta> iteraArestas = listArestas.iterator();
		Aresta aux;
		s = s + atual.getNome() + " para ";
		while(iteraArestas.hasNext()){
			aux = iteraArestas.next();
			if(aux.isPassou()){
				return;
			}else{
				aux.setPassou(true);
				if(aux.getDestino().equals(destino)){
					geral = geral + s + " fim ";
					return;
				}else{
					vaiFundo(aux.getDestino(),destino,s);
				}
			}
			
		}
	}
}
