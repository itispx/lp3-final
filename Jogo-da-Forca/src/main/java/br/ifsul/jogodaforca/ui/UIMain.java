package br.ifsul.jogodaforca.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ifsul.jogodaforca.model.PalavraRepository;

public class UIMain extends JFrame {

	private JPanel contentPane;

	public UIMain(PalavraRepository palavraRepository) {
		setTitle("Jogo da Forca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddPalavra = new JButton("Adicionar palavra");
		btnAddPalavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAdicionar uia = new UIAdicionar(palavraRepository);
				uia.setVisible(true);
				setVisible(false);
			}
		});
		btnAddPalavra.setBounds(10, 162, 414, 23);
		contentPane.add(btnAddPalavra);

		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIRelatorio uir = new UIRelatorio(palavraRepository);
				uir.setVisible(true);
				setVisible(false);
			}
		});
		btnRelatorio.setBounds(10, 108, 414, 23);
		contentPane.add(btnRelatorio);

		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIDificuldade uid = new UIDificuldade(palavraRepository);
				uid.setVisible(true);
				setVisible(false);
			}
		});
		btnJogar.setBounds(10, 54, 414, 23);
		contentPane.add(btnJogar);
	}
}
