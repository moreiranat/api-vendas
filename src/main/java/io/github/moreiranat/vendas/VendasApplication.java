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

			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

			System.out.println("Atualizando Clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.save(c);
			});

			todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando Clientes");
			clientes.findByNomeLike("Cli").forEach(System.out::println);

			System.out.println("Deletando Clientes");
			clientes.findAll().forEach(c -> {
				clientes.delete(c);
			});
			todosClientes = clientes.findAll();
			if(todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado.");
			} else {
				todosClientes.forEach(System.out::println);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
