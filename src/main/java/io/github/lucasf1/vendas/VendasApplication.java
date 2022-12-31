package io.github.lucasf1.vendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.lucasf1.vendas.domain.entity.Cliente;
import io.github.lucasf1.vendas.domain.repository.ClienteRepository;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository repository) {

        return args -> {
            System.out.println("Salvando clientes");
            repository.salvar(new Cliente("Lucas"));
            repository.salvar(new Cliente("Erika"));

            System.out.println("Listando clientes");
            List<Cliente> todosClientes = repository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizdo");
                repository.atualizar(c);
            });

            System.out.println("Buscando clientes por nome");
            repository.buscarPorNome("clie").forEach(System.out::println);

            System.out.println("Deletando clientes");
            repository.obterTodos().forEach(c -> {
                repository.deletar(c);
            });

            todosClientes = repository.obterTodos();
            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado");
            } else {
                todosClientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
