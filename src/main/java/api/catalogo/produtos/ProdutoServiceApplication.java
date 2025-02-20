package api.catalogo.produtos;

import api.catalogo.produtos.config.DocumentationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ProdutoServiceApplication implements DocumentationConfig {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoServiceApplication.class, args);


	}
}
