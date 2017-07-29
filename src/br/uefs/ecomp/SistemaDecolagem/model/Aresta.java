package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;

import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

public class Aresta implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	private Vertice destino;
	private int qtdPoltronas;
	private int poltronasLivres;
	private boolean passou;
	private String nomeServer;
	
	public Aresta(Vertice destino, int qtdPoltronas, String nomeServer){
		this.setDestino(destino);
		this.qtdPoltronas = qtdPoltronas;
		poltronasLivres = qtdPoltronas;
		setPassou(false);
		this.nomeServer = nomeServer;
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

	public String getNomeServer() {
		return nomeServer;
	}

	public void setNomeServer(String nomeServer) {
		this.nomeServer = nomeServer;
	}
	
	@Override
	public Aresta clone() throws CloneNotSupportedException {
		return (Aresta) super.clone();
	}
}
