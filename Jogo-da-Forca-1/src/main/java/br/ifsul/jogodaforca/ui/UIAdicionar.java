package br.ifsul.jogodaforca.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ifsul.jogodaforca.model.Palavra;
import br.ifsul.jogodaforca.model.PalavraRepository;

public class UIAdicionar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private JRadioButton rdbtnFacil;
	private JRadioButton rdbtnNormal;

	private JLabel lblAviso;
	private JButton btnVoltar;

	public UIAdicionar(PalavraRepository palavraRepository) {
		setTitle("Adicionar Palavra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Insira a palavra:");
		lblTitulo.setBounds(10, 22, 208, 14);
		contentPane.add(lblTitulo);

		textField = new JTextField();
		textField.setBounds(10, 45, 208, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnLimpar.setBounds(10, 157, 208, 23);
		contentPane.add(btnLimpar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavra = textField.getText();

				lblAviso.setText("");

				Boolean dif = null;

				if (palavra.isEmpty()) {
					lblAviso.setText("Insira uma palavra");
					return;
				}

				if (rdbtnFacil.isSelected()) {
					if (palavra.length() != 4) {
						lblAviso.setText("Palavras faceis devem conter 4 caracteres");
						return;
					}
					dif = false;

				} else if (rdbtnNormal.isSelected()) {
					if (palavra.length() != 6) {
						lblAviso.setText("Palavras normais devem conter 6 caracteres");
						return;
					}
					dif = true;

				} else {
					lblAviso.setText("Selecione uma dificuldade");
				}

				if (dif != null) {
					Palavra novaPalavra = new Palavra(null, palavra.toUpperCase(), dif);
					palavraRepository.save(novaPalavra);
					lblAviso.setText("Palavra adicionada");
				}

			}
		});
		btnAdicionar.setBounds(228, 157, 196, 23);
		contentPane.add(btnAdicionar);

		rdbtnFacil = new JRadioButton("Fácil");
		rdbtnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNormal.isSelected()) {
					rdbtnNormal.setSelected(false);
				}
			}
		});
		rdbtnFacil.setBounds(10, 72, 109, 23);
		contentPane.add(rdbtnFacil);

		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFacil.isSelected()) {
					rdbtnFacil.setSelected(false);
				}
			}
		});
		rdbtnNormal.setBounds(10, 98, 109, 23);
		contentPane.add(rdbtnNormal);

		lblAviso = new JLabel("");
		lblAviso.setBounds(10, 128, 414, 14);
		contentPane.add(lblAviso);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIMain uim = new UIMain(palavraRepository);
				uim.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 191, 414, 23);
		contentPane.add(btnVoltar);
	}
}
