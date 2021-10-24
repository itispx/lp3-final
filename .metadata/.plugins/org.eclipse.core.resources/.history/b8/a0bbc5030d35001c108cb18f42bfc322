package br.ifsul.jogodaforca.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import br.ifsul.jogodaforca.model.PalavraRepository;

public class UIDificuldade extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnFacil;
	private JRadioButton rdbtnNormal;
	private JLabel lblAviso;

	private Boolean dificuldade = null;

	public UIDificuldade(PalavraRepository palavraRepository) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dificuldade == null) {
					lblAviso.setText("Escolha uma dificuldade");
				} else {
					UIJogo uij = new UIJogo(palavraRepository, dificuldade);
					uij.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnJogar.setBounds(225, 227, 199, 23);
		contentPane.add(btnJogar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIMain uim = new UIMain(palavraRepository);
				uim.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 227, 205, 23);
		contentPane.add(btnVoltar);

		JLabel lblTitulo = new JLabel("Selecione uma dificuldade:");
		lblTitulo.setBounds(10, 11, 414, 14);
		contentPane.add(lblTitulo);

		rdbtnFacil = new JRadioButton("Fácil");
		rdbtnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNormal.isSelected()) {
					rdbtnNormal.setSelected(false);
				}
				dificuldade = false;
			}
		});
		rdbtnFacil.setBounds(20, 32, 109, 23);
		contentPane.add(rdbtnFacil);

		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFacil.isSelected()) {
					rdbtnFacil.setSelected(false);
				}
				dificuldade = true;
			}
		});
		rdbtnNormal.setBounds(20, 58, 109, 23);
		contentPane.add(rdbtnNormal);

		lblAviso = new JLabel("");
		lblAviso.setBounds(10, 88, 229, 36);
		contentPane.add(lblAviso);
	}
}
