package br.ifsul.lp3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SuppressWarnings("unused")
@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplicationBuilder sab = new SpringApplicationBuilder(Main.class);
		ConfigurableApplicationContext app = sab.run(args);
		Main instance = (Main) app.getBean("main");
	}
}
