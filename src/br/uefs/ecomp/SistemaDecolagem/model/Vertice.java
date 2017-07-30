package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vertice implements Serializable {
	private static final long serialVersionUID = 1L;
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
