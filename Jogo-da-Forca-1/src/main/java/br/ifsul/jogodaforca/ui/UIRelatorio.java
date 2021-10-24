package br.ifsul.jogodaforca.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.springframework.data.domain.Sort;

import br.ifsul.jogodaforca.model.Palavra;
import br.ifsul.jogodaforca.model.PalavraRepository;
import javax.swing.JLabel;
import java.awt.TextArea;

public class UIRelatorio extends JFrame {

	private JPanel contentPane;

	public UIRelatorio(PalavraRepository palavraRepository) {
		setTitle("Relatório");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIMain uim = new UIMain(palavraRepository);
				uim.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(173, 227, 89, 23);
		contentPane.add(btnVoltar);

		TextArea textAreaFaceis = new TextArea();
		textAreaFaceis.setBounds(10, 31, 414, 78);
		contentPane.add(textAreaFaceis);

		TextArea textAreaNormais = new TextArea();
		textAreaNormais.setBounds(10, 135, 414, 86);
		contentPane.add(textAreaNormais);

		JLabel lblFaceis = new JLabel("Fáceis:");
		lblFaceis.setBounds(10, 11, 414, 14);
		contentPane.add(lblFaceis);

		JLabel lblNormais = new JLabel("Normais:");
		lblNormais.setBounds(10, 115, 414, 14);
		contentPane.add(lblNormais);

		List<Palavra> listaPalavras = palavraRepository.findAll(Sort.by(Sort.Direction.ASC, "palavra"));

		for (Palavra p : listaPalavras) {
			if (p.getPalavra().length() < 6) {
				textAreaFaceis.append(p.getPalavra() + "\n");
			} else {
				textAreaNormais.append(p.getPalavra() + "\n");
			}
		}
	}
}
