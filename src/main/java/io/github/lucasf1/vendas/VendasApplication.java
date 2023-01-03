package io.github.lucasf1.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.lucasf1.vendas.domain.entity.Cliente;
import io.github.lucasf1.vendas.domain.entity.Pedido;
import io.github.lucasf1.vendas.domain.repository.ClienteRepository;
import io.github.lucasf1.vendas.domain.repository.PedidoRepository;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClienteRepository clienteRepository,
            @Autowired PedidoRepository pedidoRepository) {

        return args -> {
            System.out.println("Salvando clientes");
            Cliente c1 = new Cliente("Lucas");
            clienteRepository.save(c1);

            Pedido p = new Pedido();
            p.setCliente(c1);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            pedidoRepository.save(p);

            Cliente cliente = clienteRepository.findClienteFetchPedidos(c1.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

            pedidoRepository.findByCliente(c1).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
