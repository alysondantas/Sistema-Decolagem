package br.uefs.ecomp.SistemaDecolagem.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.util.ConexaoRMI;

public class ConexaoRMIImpl extends UnicastRemoteObject implements ConexaoRMI {
	
	private ControllerDadosServer controller;
	
	public ConexaoRMIImpl() throws RemoteException {
		super();
		controller = ControllerDadosServer.getInstance();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Grafo getGrafo() throws RemoteException {
		// TODO Auto-generated method stub
		return controller.getGrafo();
	}

	@Override
	public boolean comprarTrecho(String origem, String destino) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
