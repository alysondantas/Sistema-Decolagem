package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;

public class Aresta implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	private Vertice destino;
	private int qtdPoltronas;
	private int poltronasLivres;
	private boolean passou;
	private String nomeServer;
	private List<ClienteServer> reservas;
	
	public Aresta(Vertice destino, int qtdPoltronas, String nomeServer){
		this.setDestino(destino);
		this.qtdPoltronas = qtdPoltronas;
		poltronasLivres = qtdPoltronas;
		setPassou(false);
		reservas = new ArrayList<>();
		this.nomeServer = nomeServer;
	}
	
	public boolean addReserva(ClienteServer cliente){
		if(poltronasLivres<1){
			return false;
		}else{
			reservas.add(cliente);
			return true;
		}
	}
	
	public boolean removeReserva(String cliente, String server){
		Iterator<ClienteServer> itera = reservas.iterator();
		ClienteServer aux = null;
		while(itera.hasNext()){
			aux = itera.next();
			if(aux.getNome().equals(cliente) && aux.getServer().equals(server)){
				break;
			}
		}
		
		return reservas.remove(aux);
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

	public synchronized boolean incrementaPoltronasLivres() throws OperacaoInvalidaException, FileNotFoundException, ClassNotFoundException, IOException {
		if(poltronasLivres < qtdPoltronas){
			if(reservas.size() < 1){
				this.poltronasLivres++;
				return true;
			}else{
				ClienteServer cliente = reservas.remove(0);
				ControllerDadosServer control = ControllerDadosServer.getInstance();
				return control.saiuReserva(cliente, this);
			}
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
