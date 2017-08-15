package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.uefs.ecomp.SistemaDecolagem.util.IGrafo;

/**
 * 
 * @author Alyson Dantas
 *
 */

public class Grafo implements IGrafo, Serializable,Cloneable  {

	private static final long serialVersionUID = 1L;
	private List<Vertice> vertices;

	/**
	 * Construtor
	 */
	public Grafo(){
		vertices = new ArrayList<>();
	}

	@Override
	public int obterTamanho() {
		return vertices.size();
	}

	@Override
	public boolean removePonto(String nomeVertice) {
		Iterator<Vertice> itera = vertices.iterator();
		Vertice aux = null;
		while(itera.hasNext()){
			aux = itera.next();
			if(aux.getNome().equals(nomeVertice)){
				vertices.remove(aux);
				return true;
			}
		}
		return false;
	}

	@Override
	public void inserirPonto(Vertice vertice) {
		vertices.add(vertice);
	}

	@Override
	public Iterator<Vertice> iterador() {
		return vertices.iterator();
	}

	@Override
	public Vertice getVertice(String nomeVertice) {
		Iterator<Vertice> itera = vertices.iterator();
		Vertice aux = null;
		while(itera.hasNext()){
			aux = itera.next();
			if(aux.getNome().equals(nomeVertice)){
				return aux;
			}
		}
		return null;
	}

	@Override
	public Grafo clone() throws CloneNotSupportedException {
		return (Grafo) super.clone();
	}
	
	/**
	 * Metodo que retorna a aresta solicitada pela origem e destino
	 * @param o
	 * @param d
	 * @return
	 */
	public Aresta getAresta(String o, String d){
		Iterator<Vertice> iteraV = vertices.iterator();
		Iterator<Aresta> iteraA;
		Vertice auxV;
		Aresta auxA;
		while(iteraV.hasNext()){
			auxV = iteraV.next();
			if(auxV.getNome().equals(o)){
				iteraA = auxV.getArestas().iterator();
				while(iteraA.hasNext()){
					auxA = iteraA.next();
					if(auxA.getDestino().getNome().equals(d)){
						return auxA;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Metodo que realiza um clone do grafo
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Grafo clonado() throws CloneNotSupportedException{
		Grafo g = new Grafo();
		Iterator<Vertice> iteraG1 = vertices.iterator();
		Iterator<Aresta> iteraA1;
		Vertice v;
		while(iteraG1.hasNext()){
			v = iteraG1.next();
			Vertice cloneV = new Vertice(v.getNome());
			g.inserirPonto(cloneV);
		}
		
		iteraG1 = g.iterador();
		Vertice auxV;
		Vertice destino;
		Aresta auxA;
		while(iteraG1.hasNext()){
			v = iteraG1.next();
			auxV = getVertice(v.getNome());
			iteraA1 = auxV.getArestas().iterator();
			while(iteraA1.hasNext()){
				auxA = iteraA1.next();
				destino = g.getVertice(auxA.getDestino().getNome());
				Aresta a = new Aresta(destino, auxA.getQtdPoltronas(), auxA.getNomeServer());
				v.addAresta(a);
			}
		}
		return g;
	}
}
