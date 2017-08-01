package br.uefs.ecomp.SistemaDecolagem.model;

public class ClienteServer {
	private String nome;
	private String server;
	
	public ClienteServer(String nome, String server){
		this.setNome(nome);
		this.setServer(server);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
}
