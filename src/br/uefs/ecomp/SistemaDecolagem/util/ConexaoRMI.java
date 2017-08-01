package br.uefs.ecomp.SistemaDecolagem.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;

import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.OperacaoInvalidaException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SemVagasException;
import br.uefs.ecomp.SistemaDecolagem.model.Aresta;
import br.uefs.ecomp.SistemaDecolagem.model.Grafo;
import br.uefs.ecomp.SistemaDecolagem.model.Trajeto;

public interface ConexaoRMI extends Remote {
	
	public Grafo getGrafo() throws java.rmi.RemoteException;
	
	public Trajeto comprarTrecho(String origem, String destino) throws java.rmi.RemoteException, SemVagasException, OperacaoInvalidaException;
	
	public Trajeto reservaTrecho(String origem, String destino, String cliente, String servidor) throws java.rmi.RemoteException;
	
	public boolean recebeCancelaReserva(String origem, String destino, String cliente, String servidor) throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, CampoVazioException, IOException;
	
	public boolean recebeSaiuReserva(String nome, Aresta a)throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, IOException;
	
	public boolean finalizaViagem(String origem, String destino) throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, OperacaoInvalidaException, IOException;
}
