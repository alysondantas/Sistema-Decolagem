package br.uefs.ecomp.SistemaDecolagem.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.util.IGrafo;

public class Grafo implements IGrafo{
	private List<Vertice> vertices;

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
}
