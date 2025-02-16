package api.catalogo.produtos;

import api.catalogo.produtos.infra.gateways.mensageria.mensagens.NovoPedidoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@EnableKafka
@SpringBootApplication
public class MicrosservicoApplication {



	public static void main(String[] args) {
		SpringApplication.run(MicrosservicoApplication.class, args);
	}



}
