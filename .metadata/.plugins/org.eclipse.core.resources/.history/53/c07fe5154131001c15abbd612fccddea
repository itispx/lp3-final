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

public class UIAdicionar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		btnLimpar.setBounds(10, 141, 208, 23);
		contentPane.add(btnLimpar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String palavra = textField.getText();
			}
		});
		btnAdicionar.setBounds(228, 141, 196, 23);
		contentPane.add(btnAdicionar);
	}
}
