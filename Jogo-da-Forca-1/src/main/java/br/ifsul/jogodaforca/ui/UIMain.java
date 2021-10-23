package br.ifsul.jogodaforca.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UIMain extends JFrame {

	private JPanel contentPane;

	public UIMain() {
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
			}
		});
		btnAddPalavra.setBounds(10, 162, 414, 23);
		contentPane.add(btnAddPalavra);

		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorio.setBounds(10, 108, 414, 23);
		contentPane.add(btnRelatorio);

		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnJogar.setBounds(10, 54, 414, 23);
		contentPane.add(btnJogar);
	}
}