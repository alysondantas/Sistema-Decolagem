package br.uefs.ecomp.SistemaDecolagem.model;

import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

public class Aresta {
	private Vertice destino;
	private int qtdPoltronas;
	private int poltronasLivres;
	private boolean passou;
	
	public Aresta(Vertice destino, int qtdPoltronas){
		this.setDestino(destino);
		this.qtdPoltronas = qtdPoltronas;
		poltronasLivres = qtdPoltronas;
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

	public int getQtdPoltronas() {
		return qtdPoltronas;
	}

	public int getPoltronasLivres() {
		return poltronasLivres;
	}

	public void incrementaPoltronasLivres() throws OperacaoInvalidaException {
		if(poltronasLivres < qtdPoltronas){
			this.poltronasLivres++;
		}else{
			throw new OperacaoInvalidaException();
		}
	}
	
	public void decrementaPoltronasLivres() throws OperacaoInvalidaException {
		if(poltronasLivres < 1){
			throw new OperacaoInvalidaException();
		}else{
			poltronasLivres--;
		}
	}
}
