package br.ifsul.jogodaforca.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class UIAdicionar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private JRadioButton rdbtnFacil;
	private JRadioButton rdbtnNormal;

	private JLabel lblAviso;

	public UIAdicionar() {
		setTitle("Adicionar Palavra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Insira a palavra:");
		lblNewLabel.setBounds(10, 22, 208, 14);
		contentPane.add(lblNewLabel);

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
					// TO DO: Adicionar ao banco
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
	}
}
