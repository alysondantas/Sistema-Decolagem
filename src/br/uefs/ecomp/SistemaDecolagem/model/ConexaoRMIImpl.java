package br.uefs.ecomp.SistemaDecolagem.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SemVagasException;
import br.uefs.ecomp.SistemaDecolagem.util.ConexaoRMI;

public class ConexaoRMIImpl extends UnicastRemoteObject implements ConexaoRMI {

	private static final long serialVersionUID = 1L;
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
	public Trajeto comprarTrecho(String origem, String destino) throws RemoteException, SemVagasException, OperacaoInvalidaException {
		// TODO Auto-generated method stub
		return controller.recebeCompraDeOutro(origem, destino);
	}

	@Override
	public Trajeto reservaTrecho(String origem, String destino, String cliente, String servidor) throws RemoteException {
		// TODO Auto-generated method stub
		return controller.recebeReserva(cliente, origem, destino, servidor);
	}

	@Override
	public boolean recebeSaiReserva(String origem, String destino, String cliente, String servidor) throws RemoteException {
		// TODO Auto-generated method stub
		return controller.saiReserva(origem, destino, cliente, servidor);
	}

}
