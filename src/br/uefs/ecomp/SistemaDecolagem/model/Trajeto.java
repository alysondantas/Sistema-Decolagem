package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;

public class Trajeto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Vertice origem;
	private Aresta destino;
	
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
