package io.github.moreiranat.vendas;

import io.github.moreiranat.vendas.domain.entity.Cliente;
import io.github.moreiranat.vendas.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init (@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando Clientes");
			clientes.save(new Cliente("Nataly"));
			clientes.save(new Cliente("Outro Cliente"));

			boolean existe = clientes.existsByNome("Nataly");
			System.out.println("Existe um cliente com o nome Nataly? " + existe);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
