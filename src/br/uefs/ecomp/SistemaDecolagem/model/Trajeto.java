package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;

/**
 * 
 * @author Alyson Dantas
 *
 */
public class Trajeto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Vertice origem;
	private Aresta destino;
	
	/**
	 * Construtor
	 * @param o
	 * @param d
	 */
	public Trajeto(Vertice o, Aresta d){
		this.setOrigem(o);
		this.setDestino(d);
	}

	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Aresta getDestino() {
		return destino;
	}

	public void setDestino(Aresta destino) {
		this.destino = destino;
	}
}
