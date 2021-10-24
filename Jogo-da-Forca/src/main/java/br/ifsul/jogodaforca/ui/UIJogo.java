package br.ifsul.jogodaforca.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ifsul.jogodaforca.model.Palavra;
import br.ifsul.jogodaforca.model.PalavraRepository;

public class UIJogo extends JFrame {

	private JPanel contentPane;
	private JTextArea textAreaPalavra = new JTextArea();
	private JTextArea textAreaTentativas = new JTextArea();
	private JTextField textFielEntrada;
	private JLabel lblAviso;;

	private ArrayList<Character> acertos = new ArrayList<Character>();
	private ArrayList<Character> erros = new ArrayList<Character>();
	private List<Character> exibir = new ArrayList<Character>();

	private Integer vidas = 6;

	public UIJogo(PalavraRepository palavraRepository, Boolean dificuldade) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBoneco = new JLabel("");
		try {
			ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/vidas_6.png"));
			Image img = imgIcon.getImage();
			Image resizedImg = getScaledImage(img, 113, 183);
			imgIcon = new ImageIcon(resizedImg);

			lblBoneco.setIcon(imgIcon);

		} catch (Exception e) {
			e.printStackTrace();
		}
		lblBoneco.setBounds(311, 11, 113, 183);
		contentPane.add(lblBoneco);

		Palavra palavra = palavraRepository.findRandomByDificuldade(dificuldade);

		textAreaPalavra.setEnabled(false);
		textAreaPalavra.setDisabledTextColor(Color.BLACK);
		for (int i = 0; i < palavra.getPalavra().length(); i++) {
			exibir.add('_');
			textAreaPalavra.append(" " + exibir.get(i));
		}

		textAreaTentativas.setEnabled(false);
		textAreaTentativas.setDisabledTextColor(Color.BLACK);
		textAreaTentativas.setBounds(123, 140, 178, 54);
		contentPane.add(textAreaTentativas);

		textFielEntrada = new JTextField();
		textFielEntrada.setBounds(10, 140, 103, 20);
		contentPane.add(textFielEntrada);
		textFielEntrada.setColumns(10);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String entrada = textFielEntrada.getText().toUpperCase();
				lblAviso.setText("");
				textFielEntrada.setText("");

				if (entrada.isEmpty()) {
					lblAviso.setText("Insira uma letra");
				} else if (entrada.length() > 1) {
					lblAviso.setText("Insira apenas uma letra");
				} else {
					Character inputValue = entrada.charAt(0);

					for (Character c : acertos) {
						if (c == inputValue) {
							lblAviso.setText("Tentativa já feita");
						}
					}

					for (Character c : erros) {
						if (c == inputValue) {
							lblAviso.setText("Tentativa já feita");
						}
					}

					String tempAviso = lblAviso.getText();
					if (tempAviso == "") {

						String strPalavra = palavra.getPalavra();

						List<Integer> indexes = new ArrayList<Integer>();
						for (int i = 0; i < strPalavra.length(); i++) {
							if (inputValue == strPalavra.charAt(i)) {
								indexes.add(i);
							}
						}

						if (indexes.isEmpty()) {
							System.out.println("Wrong");
							erros.add(inputValue);
							textAreaTentativas.append("| " + inputValue + " ");
							vidas--;
							try {
								ImageIcon imgIcon = new ImageIcon(
										this.getClass().getResource("/vidas_" + vidas + ".png"));
								Image img = imgIcon.getImage();
								Image resizedImg = getScaledImage(img, 113, 183);
								imgIcon = new ImageIcon(resizedImg);

								lblBoneco.setIcon(imgIcon);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							if (vidas == 0) {
								endGame("perdeu", palavraRepository);
							}

						} else {
							System.out.println("Right");
							acertos.add(inputValue);
							for (Integer i : indexes) {
								exibir.set(i, inputValue);
							}

							textAreaPalavra.setText("");
							for (int i = 0; i < strPalavra.length(); i++) {
								textAreaPalavra.append(" " + exibir.get(i));
							}

							if (!exibir.contains('_')) {
								endGame("ganhou", palavraRepository);
							}

						}
					}
				}
			}
		});

		btnConfirmar.setBounds(10, 171, 103, 23);
		contentPane.add(btnConfirmar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIDificuldade uid = new UIDificuldade(palavraRepository);
				uid.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 227, 414, 23);
		contentPane.add(btnVoltar);

		JLabel lblTituloInserir = new JLabel("Insira uma letra:");
		lblTituloInserir.setBounds(10, 115, 103, 14);
		contentPane.add(lblTituloInserir);

		JLabel lblTentativas = new JLabel("Tentativas:");
		lblTentativas.setBounds(123, 115, 178, 14);
		contentPane.add(lblTentativas);

		lblAviso = new JLabel("");
		lblAviso.setBounds(10, 205, 414, 14);
		contentPane.add(lblAviso);

		JLabel lblTituloPalavra = new JLabel("PALAVRA");
		lblTituloPalavra.setBounds(10, 11, 103, 14);
		contentPane.add(lblTituloPalavra);

		textAreaPalavra.setBounds(10, 36, 291, 54);
		contentPane.add(textAreaPalavra);

		System.out.println(palavra.getPalavra());
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private void endGame(String status, PalavraRepository palavraRepository) {
		JOptionPane.showMessageDialog(null, "Você " + status + "!");
		UIMain uim = new UIMain(palavraRepository);
		uim.setVisible(true);
		setVisible(false);
	}
}
