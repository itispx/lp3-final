package br.ifsul.jogodaforca.ui;

import java.awt.TextArea;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ifsul.jogodaforca.model.Palavra;
import br.ifsul.jogodaforca.model.PalavraRepository;

public class UIJogo extends JFrame {

	private JPanel contentPane;
	private TextArea textArea;

	public UIJogo(PalavraRepository palavraRepository) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new TextArea();
		textArea.setBounds(10, 10, 380, 160);
		contentPane.add(textArea);

		List<Palavra> listaPalavras = palavraRepository.findByDificuldade(false);

		for (Palavra p : listaPalavras) {
			textArea.append(p.getPalavra() + "\n");
		}

	}
}
