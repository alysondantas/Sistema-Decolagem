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

/**
 * 
 * @author Alyson Dantas
 *
 */

public interface ConexaoRMI extends Remote {

	/**
	 * Metodo que retorna o grafo
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public Grafo getGrafo() throws java.rmi.RemoteException;

	/**
	 * Metodo que realiza a compra de um trecho
	 * @param origem
	 * @param destino
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws SemVagasException
	 * @throws OperacaoInvalidaException
	 */
	public Trajeto comprarTrecho(String origem, String destino) throws java.rmi.RemoteException, SemVagasException, OperacaoInvalidaException;

	/**
	 * Metodo que realiza a reserva de um trecho
	 * @param origem
	 * @param destino
	 * @param cliente
	 * @param servidor
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public Trajeto reservaTrecho(String origem, String destino, String cliente, String servidor) throws java.rmi.RemoteException;

	/**
	 * Metodo que recebe o pedido de cancelar uma reserva
	 * @param origem
	 * @param destino
	 * @param cliente
	 * @param servidor
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws CampoVazioException
	 * @throws IOException
	 */
	public boolean recebeCancelaReserva(String origem, String destino, String cliente, String servidor) throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, CampoVazioException, IOException;

	/**
	 * Metodo que avisa que um cliente saiu da reserva
	 * @param nome
	 * @param a
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean recebeSaiuReserva(String nome, Aresta a)throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, IOException;

	/**
	 * Metodo que finaliza uma viagem
	 * @param origem
	 * @param destino
	 * @return
	 * @throws java.rmi.RemoteException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws OperacaoInvalidaException
	 * @throws IOException
	 */
	public boolean finalizaViagem(String origem, String destino) throws java.rmi.RemoteException, FileNotFoundException, ClassNotFoundException, OperacaoInvalidaException, IOException;

	/**
	 * Metodo que permite o acesso a uma região critica
	 * @param timeStamp
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public boolean regiaoCriticaAcesso(int timeStamp) throws java.rmi.RemoteException;
}
