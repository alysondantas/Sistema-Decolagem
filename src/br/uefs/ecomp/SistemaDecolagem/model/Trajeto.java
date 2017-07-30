package br.uefs.ecomp.SistemaDecolagem.model;

public class Trajeto {
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
