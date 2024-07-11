package com.personal.irving.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.irving.demo.model.dao.ClienteDao;
import com.personal.irving.demo.model.dto.ClienteDto;
import com.personal.irving.demo.model.entity.Cliente;
import com.personal.irving.demo.service.IClienteService;

@Service
public class ClienteImpl implements IClienteService{
    
    @Autowired
    private ClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente =Cliente.builder()
            .idCliente(clienteDto.getIdCliente())
            .nombre(clienteDto.getNombre())
            .apellido(clienteDto.getApellido())
            .correo(clienteDto.getCorreo())
            .fechaRegistro(clienteDto.getFechaRegistro())
            .build();
        return clienteDao.save(cliente);
    }


    @Transactional
    @Override
    public void delete(Cliente cliente) {
           
        clienteDao.delete(cliente);
        
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

   
    @Override
    public Iterable<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    public Boolean existById(Integer id) {
        return clienteDao.existsById(id);
    }


}
