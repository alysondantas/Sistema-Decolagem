package br.uefs.ecomp.SistemaDecolagem.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SemVagasException;
import br.uefs.ecomp.SistemaDecolagem.util.ConexaoRMI;

/**
 * 
 * @author Alyson Dantas
 *
 */

public class ConexaoRMIImpl extends UnicastRemoteObject implements ConexaoRMI {

	private static final long serialVersionUID = 1L;
	private ControllerDadosServer controller;

	/**
	 * Construtor
	 * @throws RemoteException
	 */
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
	public boolean recebeCancelaReserva(String origem, String destino, String cliente, String servidor) throws FileNotFoundException, ClassNotFoundException, CampoVazioException, IOException {
		// TODO Auto-generated method stub
		return controller.cancelaReserva(origem, destino, cliente, servidor);
	}

	@Override
	public boolean recebeSaiuReserva(String nome, Aresta a) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return controller.atualizaClienteSaiuReserva(nome, a);
	}

	@Override
	public boolean finalizaViagem(String origem, String destino) throws FileNotFoundException, ClassNotFoundException, OperacaoInvalidaException, IOException {
		// TODO Auto-generated method stub
		return controller.recebeFinalizaViagem(origem, destino);
	}

	@Override
	public boolean regiaoCriticaAcesso(int timeStamp) throws RemoteException {
		// TODO Auto-generated method stub
		int meuTime = controller.getTimeStamp();
		if(meuTime<=timeStamp){
			return controller.getRegiaoCritica();
		}else{
			return true;
		}
	}

}
