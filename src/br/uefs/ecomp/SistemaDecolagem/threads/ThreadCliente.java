package br.uefs.ecomp.SistemaDecolagem.threads;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import br.uefs.ecomp.SistemaDecolagem.controller.ControllerDadosServer;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CadastroJaExistenteException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.CampoVazioException;
import br.uefs.ecomp.SistemaDecolagem.exceptions.SenhaIncorretaException;


public class ThreadCliente extends Thread {
    private Socket cliente;//socket do cliente
    private ServerSocket server;//socket do servidor
    private JTextArea textField;//para atualizar a interface
    private ControllerDadosServer controller = ControllerDadosServer.getInstance();//instancia do controller

	/**
	 * Construtor
	 * @param server Servidor que aceita os clientes
	 * @param textField Area de log na interface
	 * @param cliente Cliente que ja foi aceito
	 */
	public ThreadCliente(ServerSocket server, JTextArea textField, Socket cliente) {//recebe o socket server e o textArea
		this.server = server; 
		this.cliente = cliente;
		this.textField = textField;
	}
	
	
	/**
	 * Metodo Run da Thread
	 */
	public void run() {
		try {
            //Inicia thread do cliente aceitando clientes

            //ObjectInputStream para receber o nome do arquivo
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());//cria um objeto de entrada
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());//cria um objeto de saida
            String pack = (String) entrada.readObject();//obtem o pacote de entrada
            String informacoes[] = pack.split(Pattern.quote("|"));
            int opcao = Integer.parseInt(informacoes[0]);//recebe a opcao que o cliente mandou
            String s = "erro";//string de log com erro
            switch (opcao) {
                case 0://Cadastro de novo cliente
                    String nome = informacoes[1];//recebe as informações para cadastro
                    String senha = informacoes[2];
                    try {
                        controller.cadastrarConta(nome, senha);//controller cadastra cliente
                        s = "Criar cadastro " + nome;//log
                        saida.writeObject("concluido");//envia resposta concluido
                    } catch (CampoVazioException e) {
                        saida.writeObject("camponaopreenchido");//erro de campo nao preenchido
                    } catch (CadastroJaExistenteException e) {
                        saida.writeObject("cadastrojaexistente");//erro de campo nao preenchido
                    }
                    saida.flush();
                    break;
                case 1://acessa uma conta
                    String nomeAcesso = informacoes[1];
                    String senhaAcesso = informacoes[2];
                    try {
                        controller.loginCliente(nomeAcesso, senhaAcesso);//realiza o login do cliente
                        s = "Acessar conta " + nomeAcesso;//log
                        saida.writeObject("concluido");//envia resposta concluido
                    } catch (CampoVazioException e) {
                        saida.writeObject("camponaopreenchido");//erro de campo nao preenchido
                    } catch (FileNotFoundException e) {
                        saida.writeObject("clientenaoencontrado");//erro de campo nao preenchido
                    } catch (SenhaIncorretaException e) {
                        saida.writeObject("senhaincorreta");//erro de campo nao preenchido
                    }
                    saida.flush();
                    break;
                case 2: 
                    
                    break;
                case 3: 
                    

                    break;
            }
            System.out.println("\nCliente atendido com sucesso: " + s + cliente.getRemoteSocketAddress().toString());
            textField.setText(textField.getText() + "\nCliente atendido com sucesso: " + s + cliente.getRemoteSocketAddress().toString());//coloca o log no textArea

            entrada.close();//finaliza a entrada
            saida.close();//finaliza a saida
            cliente.close();//fecha o cliente
        } catch (SocketException e) {
            System.out.println("Filanizou o atendimento.");
            textField.setText(textField.getText() + "\nAtendimento foi finalizado.");//caso alguma exceção desconheciada seja lançada ela encerra a thread e é exibida
            try {
                cliente.close();   //finaliza o cliente
            } catch (Exception ec) {
                textField.setText(textField.getText() + "\nErro fatal cliente não finalizado: " + ec.getMessage());//cliente não foi finalizado
            }
        } catch (Exception e) {//caso alguma exceção seja lançada
            e.printStackTrace();
            System.out.println("Excecao ocorrida na thread: " + e);
            textField.setText(textField.getText() + "\nExcecao ocorrida na thread: " + e.getMessage());//caso alguma exceção desconheciada seja lançada ela encerra a thread e é exibida
            try {
                cliente.close();   //finaliza o cliente
            } catch (Exception ec) {
                textField.setText(textField.getText() + "\nErro fatal cliente não finalizado: " + ec.getMessage());//cliente não foi finalizado
            }
        }

		
	}
}
