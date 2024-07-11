package com.personal.irving.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.irving.demo.model.dto.ClienteDto;
import com.personal.irving.demo.model.entity.Cliente;
import com.personal.irving.demo.service.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente() throws Exception {
        ClienteDto clienteDto = ClienteDto.builder()
                .nombre("John")
                .apellido("Doe")
                .correo("john.doe@example.com")
                .build();

        Cliente clienteMock = Cliente.builder()
                .idCliente(1)
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(clienteService.save(any(ClienteDto.class))).thenReturn(clienteMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clienteDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente").value(1))
                .andExpect(jsonPath("$.nombre").value("John"))
                .andExpect(jsonPath("$.apellido").value("Doe"))
                .andExpect(jsonPath("$.correo").value("john.doe@example.com"));
    }

    @Test
    void testUpdateCliente() throws Exception {
        int idCliente = 1;
        ClienteDto clienteDto = ClienteDto.builder()
                .idCliente(idCliente)
                .nombre("Jane")
                .apellido("Doe")
                .correo("jane.doe@example.com")
                .build();

        Cliente clienteMock = Cliente.builder()
                .idCliente(idCliente)
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(clienteService.existById(idCliente)).thenReturn(true);
        when(clienteService.save(any(ClienteDto.class))).thenReturn(clienteMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cliente/{id}", idCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clienteDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCliente").value(idCliente))
                .andExpect(jsonPath("$.nombre").value("Jane"))
                .andExpect(jsonPath("$.apellido").value("Doe"))
                .andExpect(jsonPath("$.correo").value("jane.doe@example.com"));
    }

    @Test
    void testDeleteCliente() throws Exception {
        int idCliente = 1;
        Cliente clienteMock = Cliente.builder()
                .idCliente(idCliente)
                .nombre("John")
                .apellido("Doe")
                .correo("john.doe@example.com")
                .fechaRegistro(LocalDateTime.now())
                .build();

        when(clienteService.findById(idCliente)).thenReturn(clienteMock);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cliente/{id}", idCliente))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).delete(any(Cliente.class));
    }
}
