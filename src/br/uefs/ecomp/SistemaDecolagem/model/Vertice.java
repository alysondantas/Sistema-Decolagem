package br.uefs.ecomp.SistemaDecolagem.model;

import java.util.ArrayList;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

public class Vertice {
	private String nome;
	private List<Aresta> arestas;
	private int qtdPoltronas;
	private int poltronasLivres;
	
	public Vertice(String nome, int qtdPoltronas){
		this.nome = nome;
		this.qtdPoltronas = qtdPoltronas;
		poltronasLivres = qtdPoltronas;
		setArestas(new ArrayList<>());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
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
