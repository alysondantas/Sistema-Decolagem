package br.uefs.ecomp.SistemaDecolagem.view;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.SistemaDecolagem.controller.ControllerCliente;


import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClienteGui extends JFrame {
	private ControllerCliente controller = ControllerCliente.getInstance();
	JComboBox<String> listOrigem;
	String[] temp;
	JButton btnAtualizarRotas;
	JButton btnComprar;
	JComboBox <String> listTrechos;
	JComboBox <String>listDestino;
	JButton btnBuscar;
	private String [] auxTrechos= {"sem trechos"};
	private JPanel contentPanePrincipal;
	private JLabel lblUser;
	private String serv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGui frame = new ClienteGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteGui() {
		temp = new String[5];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 376);
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPanePrincipal);
		
		JTabbedPane tabbedPanePrincipal = new JTabbedPane(JTabbedPane.TOP);
		
		 lblUser = new JLabel("User");
		GroupLayout gl_contentPanePrincipal = new GroupLayout(contentPanePrincipal);
		gl_contentPanePrincipal.setHorizontalGroup(
			gl_contentPanePrincipal.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPanePrincipal, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
				.addGroup(gl_contentPanePrincipal.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUser)
					.addContainerGap(844, Short.MAX_VALUE))
		);
		gl_contentPanePrincipal.setVerticalGroup(
			gl_contentPanePrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanePrincipal.createSequentialGroup()
					.addComponent(lblUser)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(tabbedPanePrincipal, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panelComprarPassagem = new JPanel();
		tabbedPanePrincipal.addTab("Comprar Passagem", null, panelComprarPassagem, null);
		
		 btnBuscar = new JButton("buscar");
		 btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
						String[] aux;// Vetor auxiliar para quebra de trechos.
				try {
					auxTrechos = controller.carregarTrechos(listOrigem.getSelectedItem(), listDestino.getSelectedItem());//recebe os trechos. Apesar de ser object, posteriormente � convertido em String.
					
					for(int i = 0; i<auxTrechos.length; i+=3) {
						aux = auxTrechos[i].split(Pattern.quote("!"));
						//String[] aux2 = aux[i].split(Pattern.quote("!"));
						listTrechos.addItem(auxTrechos[i]);
						//listTrechos.addItem(aux[i]);
						//listTrechos.addItem(aux2[i]);
					}
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			 
		 });
		
		//DefaultListModel modelOrigem = new DefaultListModel();
		listOrigem = new JComboBox<String>();
		
		
		
		JLabel lblSelecioneAOrigem = new JLabel("Selecione a origem");
		
		JLabel lblSelecioneODestino = new JLabel("Selecione o Destino");
		
		 listDestino = new JComboBox<String>();
		
		JLabel lblSelecioneOsTrechos = new JLabel("Selecione os trechos");
		
		 listTrechos = new JComboBox<String>();
		 
		
		 btnComprar = new JButton("Comprar");
		 btnComprar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.comprar(lblUser.getText(),(String) listOrigem.getSelectedItem(),(String) listDestino.getSelectedItem(),serv, (String)listTrechos.getSelectedItem());
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			 
		 });
		
		 btnAtualizarRotas = new JButton("Atualizar");
		
		btnAtualizarRotas.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				try { temp = controller.carregarOrigemDestino().clone(); //ControllerCliente.getInstance().carregarOrigemDestino().clone();
				listOrigem.addItem(temp[0]);
				listOrigem.addItem(temp[1]);
				listOrigem.addItem(temp[2]);
				listOrigem.addItem(temp[3]);
				listOrigem.addItem(temp[4]);
				listDestino.addItem(temp[0]);
				listDestino.addItem(temp[1]);
				listDestino.addItem(temp[2]);
				listDestino.addItem(temp[3]);
				listDestino.addItem(temp[4]);

				} catch (ClassNotFoundException | IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		
		JLabel lblAtualizarRotas = new JLabel("Atualizar Rotas");
		GroupLayout gl_panelComprarPassagem = new GroupLayout(panelComprarPassagem);
		gl_panelComprarPassagem.setHorizontalGroup(
			gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelComprarPassagem.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelComprarPassagem.createSequentialGroup()
							.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
										.addComponent(listDestino, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
										.addComponent(lblSelecioneAOrigem)
										.addComponent(lblSelecioneODestino)
										.addGroup(gl_panelComprarPassagem.createSequentialGroup()
											.addComponent(listOrigem, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(btnBuscar, Alignment.TRAILING))
									.addGap(325))
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addComponent(btnAtualizarRotas)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addComponent(lblSelecioneOsTrechos)
									.addGap(271))
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnComprar)
										.addComponent(listTrechos, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE))
									.addGap(39))))
						.addComponent(lblAtualizarRotas))
					.addContainerGap())
		);
		gl_panelComprarPassagem.setVerticalGroup(
			gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelComprarPassagem.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelecioneAOrigem)
						.addComponent(lblSelecioneOsTrechos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.BASELINE)
						.addComponent(listOrigem, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(listTrechos, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblSelecioneODestino)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(listDestino, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBuscar)
					.addGap(30)
					.addComponent(lblAtualizarRotas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnComprar)
						.addComponent(btnAtualizarRotas))
					.addGap(53))
		);
		panelComprarPassagem.setLayout(gl_panelComprarPassagem);
		
		JPanel panelPassagemAdquirida = new JPanel();
		tabbedPanePrincipal.addTab("Passagens Adquiridas", null, panelPassagemAdquirida, null);
		
		JLabel lblPassagemAdquirida = new JLabel("Passagem Adquirida");
		
		JComboBox listTrechosAdquiridos = new JComboBox();
		GroupLayout gl_panelPassagemAdquirida = new GroupLayout(panelPassagemAdquirida);
		gl_panelPassagemAdquirida.setHorizontalGroup(
			gl_panelPassagemAdquirida.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPassagemAdquirida.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPassagemAdquirida.createParallelGroup(Alignment.LEADING)
						.addComponent(listTrechosAdquiridos, GroupLayout.PREFERRED_SIZE, 799, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassagemAdquirida))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		gl_panelPassagemAdquirida.setVerticalGroup(
			gl_panelPassagemAdquirida.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPassagemAdquirida.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPassagemAdquirida)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listTrechosAdquiridos, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(254, Short.MAX_VALUE))
		);
		panelPassagemAdquirida.setLayout(gl_panelPassagemAdquirida);
		contentPanePrincipal.setLayout(gl_contentPanePrincipal);
	}
	public void setLabelUser(String nomeUser) {
		lblUser.setText(nomeUser);	
	}
	public void setServ(String servidor) {
		this.serv = servidor;
	}

}
