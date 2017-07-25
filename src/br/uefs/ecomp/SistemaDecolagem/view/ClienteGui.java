package br.uefs.ecomp.SistemaDecolagem.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ClienteGui extends JFrame {

	private JPanel contentPane;
	private JTextField origem;
	private JTextField destino;

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
		setBounds(100, 100, 557, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel nomeCliente = new JLabel("User");
		
		JLabel lblSelecioneOsTrechos = new JLabel("Selecione os trechos desejados");
		
		JLabel lblInsiraAOrigem = new JLabel("Insira a origem");
		
		origem = new JTextField();
		origem.setColumns(10);
		
		JLabel lblInsiraODestino = new JLabel("Insira o destino");
		
		destino = new JTextField();
		destino.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		
		JComboBox rotasP1 = new JComboBox();
		
		JButton btnOKt1 = new JButton("OK");
		
		JLabel lblPrimeiroTrecho = new JLabel("Primeiro Trecho");
		
		JLabel lblSegundoTrecho = new JLabel("Segundo Trecho");
		
		JComboBox rotasP2 = new JComboBox();
		
		JButton btnOKt2 = new JButton("OK");
		
		JLabel lblTerceiroTrecho = new JLabel("Terceiro Trecho");
		
		JComboBox rotasP3 = new JComboBox();
		
		JButton btnOkt3 = new JButton("OK");
		
		JButton btnFinalizar = new JButton("Finalizar");
		
		JLabel lblPosioNaFila = new JLabel("Posi\u00E7\u00E3o na fila de espera");
		
		JLabel label = new JLabel("0");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelecioneOsTrechos, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(81))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrimeiroTrecho)
					.addContainerGap(455, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSegundoTrecho)
					.addContainerGap(451, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTerceiroTrecho)
					.addContainerGap(454, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(rotasP3, Alignment.LEADING, 0, 384, Short.MAX_VALUE)
						.addComponent(rotasP2, Alignment.LEADING, 0, 384, Short.MAX_VALUE)
						.addComponent(rotasP1, Alignment.LEADING, 0, 384, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOKt1)
						.addComponent(btnOKt2)
						.addComponent(btnOkt3))
					.addGap(90))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(466, Short.MAX_VALUE)
					.addComponent(btnFinalizar))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(nomeCliente, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInsiraAOrigem)
					.addGap(4)
					.addComponent(origem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblInsiraODestino)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(destino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBuscar)
					.addGap(34))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPosioNaFila)
					.addContainerGap(483, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(label)
					.addContainerGap(450, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nomeCliente, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInsiraAOrigem)
						.addComponent(origem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInsiraODestino)
						.addComponent(destino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSelecioneOsTrechos)
					.addGap(2)
					.addComponent(lblPrimeiroTrecho)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rotasP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOKt1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSegundoTrecho)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rotasP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOKt2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTerceiroTrecho)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rotasP3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOkt3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFinalizar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPosioNaFila)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
