package api.catalogo.produtos;

import api.catalogo.produtos.config.DocumentationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableKafka
@EnableWebMvc
@SpringBootApplication
public class ProdutoServiceApplication implements DocumentationConfig {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoServiceApplication.class, args);


	}
}
