package br.uefs.ecomp.SistemaDecolagem.util;

import java.rmi.Remote;

import br.uefs.ecomp.SistemaDecolagem.model.Grafo;

public interface ConexaoRMI extends Remote {
	
	public Grafo getGrafo() throws java.rmi.RemoteException;
	
	public boolean comprarTrecho(String origem, String destino) throws java.rmi.RemoteException;
}
