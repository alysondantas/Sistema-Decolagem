package br.uefs.ecomp.SistemaDecolagem.model;

public class Aresta {
	private Vertice destino;
	private boolean passou;
	
	public Aresta(Vertice destino){
		this.setDestino(destino);
		setPassou(false);
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public boolean isPassou() {
		return passou;
	}

	public void setPassou(boolean passou) {
		this.passou = passou;
	}

}
