package io.github.moreiranat.vendas.service;

import io.github.moreiranat.vendas.model.Cliente;
import io.github.moreiranat.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        clienteRepository.persistir(cliente);
    }

    private void validarCliente(Cliente cliente) {
        //aplica validações
    }
}
