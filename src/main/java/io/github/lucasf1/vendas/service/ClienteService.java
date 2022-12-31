package io.github.lucasf1.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.lucasf1.vendas.model.Cliente;
import io.github.lucasf1.vendas.repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public void salvarCliente(Cliente cliente) {

    }

    public void validarCliente(Cliente cliente) {

    }
}
