package br.uefs.ecomp.SistemaDecolagem.util;

import java.util.Iterator;

import br.uefs.ecomp.SistemaDecolagem.model.Vertice;

public interface IGrafo {
	
	public int obterTamanho();
	
	public boolean removePonto(String nomeVertice);
	
	public void inserirPonto(Vertice vertice);
	
	public Iterator<Vertice> iterador();
	
	public Vertice getVertice(String nomeVertice);
}
