package br.uefs.ecomp.SistemaDecolagem.model;

import java.util.ArrayList;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

public class Vertice {
	private String nome;
	private List<Aresta> arestas;
	
	
	public Vertice(String nome){
		this.nome = nome;
		arestas = new ArrayList<>();
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
	
	public void addAresta(Aresta aresta){
		this.arestas.add(aresta);
	}

}
