package br.ifsul.jogodaforca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import br.ifsul.jogodaforca.model.PalavraRepository;
import br.ifsul.jogodaforca.ui.UIMain;

@SpringBootApplication
public class Main {

	@Autowired
	public PalavraRepository palavraRepository;

	public static void main(String[] args) {
		SpringApplicationBuilder sab = new SpringApplicationBuilder(Main.class);

		sab.headless(false);

		ConfigurableApplicationContext app = sab.run(args);

		Main instancia = (Main) app.getBean("main");

		UIMain uim = new UIMain(instancia.palavraRepository);
		uim.setVisible(true);
	}
}
