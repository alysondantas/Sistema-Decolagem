package br.uefs.ecomp.SistemaDecolagem.view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;

public class ClienteGui extends JFrame {

	private JPanel contentPanePrincipal;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 376);
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPanePrincipal);
		
		JTabbedPane tabbedPanePrincipal = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblUser = new JLabel("User");
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
		
		JButton btnBuscar = new JButton("buscar");
		
		JList listOrigem = new JList();
		
		JLabel lblSelecioneAOrigem = new JLabel("Selecione a origem");
		
		JLabel lblSelecioneODestino = new JLabel("Selecione o Destino");
		
		JList listDestino = new JList();
		
		JLabel lblSelecioneOsTrechos = new JLabel("Selecione os trechos");
		
		JList listTrechos = new JList();
		
		JButton btnComprar = new JButton("Comprar");
		GroupLayout gl_panelComprarPassagem = new GroupLayout(panelComprarPassagem);
		gl_panelComprarPassagem.setHorizontalGroup(
			gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelComprarPassagem.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBuscar, Alignment.LEADING)
						.addGroup(gl_panelComprarPassagem.createSequentialGroup()
							.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
								.addComponent(listDestino, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(lblSelecioneAOrigem)
								.addComponent(lblSelecioneODestino)
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addComponent(listOrigem, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelComprarPassagem.createSequentialGroup()
									.addGap(398)
									.addComponent(lblSelecioneOsTrechos)
									.addGap(271))
								.addGroup(Alignment.TRAILING, gl_panelComprarPassagem.createSequentialGroup()
									.addGap(325)
									.addGroup(gl_panelComprarPassagem.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnComprar)
										.addComponent(listTrechos, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE))
									.addGap(39)))))
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
					.addGap(55)
					.addComponent(btnComprar)
					.addGap(53))
		);
		panelComprarPassagem.setLayout(gl_panelComprarPassagem);
		
		JPanel panelPassagemAdquirida = new JPanel();
		tabbedPanePrincipal.addTab("Passagens Adquiridas", null, panelPassagemAdquirida, null);
		
		JLabel lblPassagemAdquirida = new JLabel("Passagem Adquirida");
		
		JList listTrechosAdquiridos = new JList();
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
}
