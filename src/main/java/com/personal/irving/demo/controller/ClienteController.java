package com.personal.irving.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.irving.demo.model.dto.ClienteDto;
import com.personal.irving.demo.model.entity.Cliente;
import com.personal.irving.demo.service.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = clienteService.save(clienteDto);
        return Cliente.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombre())
                .apellido(clienteSave.getApellido())
                .correo(clienteSave.getCorreo())
                .fechaRegistro(clienteSave.getFechaRegistro())
                .build();

    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ClienteDto clienteDto) {
        try {
            
            if (clienteService.existById(id) && clienteDto.getIdCliente()==id) {
                Cliente clienteUpdate = clienteService.save(clienteDto);
                Cliente updatedCliente = Cliente.builder()
                    .idCliente(clienteUpdate.getIdCliente())
                    .nombre(clienteUpdate.getNombre())
                    .apellido(clienteUpdate.getApellido())
                    .correo(clienteUpdate.getCorreo())
                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                    .build();
                    return ResponseEntity.status(HttpStatus.CREATED).body(updatedCliente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("The record you are trying to update was not found."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    


    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            if (clienteDelete != null) {
                clienteService.delete(clienteDelete);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (DataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        try {
            Cliente cliente = clienteService.findById(id);
            Cliente clienteSearch=Cliente.builder()
            .idCliente(cliente.getIdCliente())
            .nombre(cliente.getNombre())
            .apellido(cliente.getApellido())
            .correo(cliente.getCorreo())
            .fechaRegistro(cliente.getFechaRegistro())
            .build();
            if (clienteSearch != null) {
                return ResponseEntity.ok(clienteSearch);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Something went wrong %s",e.getMessage()));
        }
    }

    @GetMapping("/clientes")
public Iterable<ClienteDto> all() {
    Iterable<Cliente> clientes = clienteService.findAll(); // Suponiendo que tienes un servicio que devuelve todos los clientes
    
    List<ClienteDto> clientesDto = new ArrayList<>();
    for (Cliente cliente : clientes) {
        ClienteDto clienteDto = ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .correo(cliente.getCorreo())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
        clientesDto.add(clienteDto);
    }
    
    return clientesDto;
}
}
