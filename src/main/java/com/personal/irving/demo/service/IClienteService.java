package com.personal.irving.demo.service;

import com.personal.irving.demo.model.dto.ClienteDto;
import com.personal.irving.demo.model.entity.Cliente;

public interface IClienteService {
    Cliente save(ClienteDto cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    Boolean existById(Integer id); //
    Iterable<Cliente> findAll();
}

