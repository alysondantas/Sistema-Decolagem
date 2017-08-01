package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
    private String senha;
    private List<Trajeto> trajetos;
    private List<Trajeto> trajetosReservados;
    
    public Cliente(String nome, String senha){
    	this.setNome(nome);
    	this.setSenha(senha);
    	trajetosReservados = new ArrayList<Trajeto>();
    	setTrajetos(new ArrayList<Trajeto>());
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Trajeto> getTrajetos() {
		return trajetos;
	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}
	
	public void addTrajeto(Trajeto t){
		trajetos.add(t);
	}
	
	public void addTrajetoReserva(Trajeto t){
		trajetosReservados.add(t);
	}

	public List<Trajeto> getTrajetosReservados() {
		return trajetosReservados;
	}
	
	public boolean removeTrajetoReserva(Trajeto t){
		return trajetosReservados.remove(t);
	}

	public void setTrajetosReservados(List<Trajeto> trajetosReservados) {
		this.trajetosReservados = trajetosReservados;
	}
}
